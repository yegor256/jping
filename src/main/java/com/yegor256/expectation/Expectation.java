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
     * @param url Target URL
     * @param mode Target strategy
     */
    public Expectation(final String url, final RequestStrategy mode) {
        this.target = url;
        this.strategy = mode;
    }

    /**
     * Check this expectation with a probe.
     * @param probe Probe to use
     * @return Probe result
     */
    public ProbeResult checkedBy(final Probe probe) {
        return probe.probe(this.target).matchedAgainst(this);
    }

    /**
     * Check whether the reachability satisfies this expectation.
     * @param reachability Reachability to check
     * @return TRUE if satisfied
     */
    public boolean satisfiedBy(final Reachability reachability) {
        final boolean accepted;
        if (this.strategy == RequestStrategy.MANDATORY) {
            accepted = reachability.available();
        } else {
            accepted = true;
        }
        return accepted;
    }

    /**
     * Explain the mismatch against the given reachability.
     * @param reachability Reachability that failed to satisfy this expectation
     * @return Explanation text
     */
    public String mismatchAgainst(final Reachability reachability) {
        return String.format(
            "\"%s\" is %s while %s was required",
            this.target,
            reachability.description(),
            this.strategy
        );
    }

    /**
     * Target URL.
     * @return Target URL
     */
    public String url() {
        return this.target;
    }
}
