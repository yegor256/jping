/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import com.yegor256.expectation.Expectation;

/**
 * Probe result.
 *
 * @since 0.3.0
 */
public final class ProbeResult {

    /**
     * Checked URL.
     */
    private final String url;

    /**
     * Bound expectation.
     */
    private final Expectation expectation;

    /**
     * Actual reachability.
     */
    private final Reachability reachability;

    /**
     * New result.
     *
     * @param target Checked URL
     * @param state Actual reachability
     */
    public ProbeResult(final String target, final Reachability state) {
        this(target, state, null);
    }

    /**
     * New result.
     *
     * @param target Checked URL
     * @param state Actual reachability
     * @param exp Bound expectation
     */
    public ProbeResult(
        final String target,
        final Reachability state,
        final Expectation exp
    ) {
        this.url = target;
        this.reachability = state;
        this.expectation = exp;
    }

    public ProbeResult matchedAgainst(final Expectation exp) {
        return new com.yegor256.probe.ProbeResult(this.url, this.reachability, exp);
    }

    public boolean acceptable() {
        return this.expectation.satisfiedBy(this.reachability);
    }

    public boolean successful() {
        return this.reachability.available();
    }

    public String explanation() {
        final String text;
        if (this.acceptable()) {
            text = String.format("\"%s\" satisfied expectation", this.url);
        } else {
            text = this.expectation.mismatchAgainst(this.reachability);
        }
        return text;
    }
}
