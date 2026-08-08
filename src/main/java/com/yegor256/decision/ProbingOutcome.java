/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.decision;

/**
 * Probing outcome.
 *
 * @since 0.3.0
 */
public final class ProbingOutcome {

    /**
     * Decision reason.
     */
    private final String reason;

    /**
     * Success flag.
     */
    private final boolean success;

    /**
     * Ctor.
     *
     * @param mode Success flag
     * @param text Decision reason
     */
    public ProbingOutcome(final boolean mode, final String text) {
        this.success = mode;
        this.reason = text;
    }

    public OnlineDecision decision() {
        return new OnlineDecision(this.success, this.reason);
    }
}
