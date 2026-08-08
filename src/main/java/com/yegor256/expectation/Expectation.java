/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.expectation;

import com.yegor256.RequestStrategy;
import com.yegor256.probe.Probe;
import com.yegor256.probe.ProbeResult;
import com.yegor256.probe.Reachability;

/**
 * One expectation.
 *
 * @since 0.3.0
 */
public final class Expectation {

    /**
     * Target URL.
     */
    private final String target;

    /**
     * Target strategy.
     */
    private final RequestStrategy strategy;

    /**
     * Ctor.
     *
     * @param url Target URL
     * @param mode Target strategy
     */
    public Expectation(final String url, final RequestStrategy mode) {
        this.target = url;
        this.strategy = mode;
    }

    public ProbeResult checkedBy(final Probe probe) {
        return probe.probe(this.target).matchedAgainst(this);
    }

    public boolean satisfiedBy(final Reachability reachability) {
        final boolean accepted;
        if (this.strategy == RequestStrategy.MANDATORY) {
            accepted = reachability.available();
        } else {
            accepted = true;
        }
        return accepted;
    }

    public String mismatchAgainst(final Reachability reachability) {
        return String.format(
            "\"%s\" is %s while %s was required",
            this.target,
            reachability.description(),
            this.strategy
        );
    }

    public String url() {
        return this.target;
    }
}
