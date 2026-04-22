/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.decision;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;

/**
 * Online decision.
 *
 * @since 0.3.0
 */
public final class OnlineDecision {

    /**
     * Decision reason.
     */
    private final String reason;

    /**
     * Enabled flag.
     */
    private final boolean enabled;

    /**
     * Ctor.
     *
     * @param mode Enabled flag
     * @param text Decision reason
     */
    public OnlineDecision(final boolean mode, final String text) {
        this.enabled = mode;
        this.reason = text;
    }

    public OnlineDecision inverted() {
        return new OnlineDecision(!this.enabled, this.reason);
    }

    public ConditionEvaluationResult asJUnit() {
        final ConditionEvaluationResult result;
        if (this.enabled) {
            result = ConditionEvaluationResult.enabled(this.reason);
        } else {
            result = ConditionEvaluationResult.disabled(this.reason);
        }
        return result;
    }
}
