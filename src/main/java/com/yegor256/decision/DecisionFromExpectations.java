/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.decision;

/**
 * Decision from expectations.
 *
 * @since 0.3.0
 */
public final class DecisionFromExpectations {

    /**
     * Raw outcome.
     */
    private final ProbingOutcome outcome;

    /**
     * Ctor.
     *
     * @param raw Raw outcome
     */
    public DecisionFromExpectations(final ProbingOutcome raw) {
        this.outcome = raw;
    }

    public OnlineDecision decision() {
        return this.outcome.decision();
    }
}
