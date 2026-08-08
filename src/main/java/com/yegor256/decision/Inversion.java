/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.decision;

/**
 * Inverted decision.
 *
 * @since 0.3.0
 */
public final class Inversion {

    /**
     * Inversion mode.
     */
    private final boolean inverted;

    /**
     * Origin decision.
     */
    private final OnlineDecision source;

    /**
     * Ctor.
     *
     * @param mode Inversion mode
     * @param decision Origin decision
     */
    public Inversion(final boolean mode, final OnlineDecision decision) {
        this.inverted = mode;
        this.source = decision;
    }

    public OnlineDecision decision() {
        final OnlineDecision result;
        if (this.inverted) {
            result = this.source.inverted();
        } else {
            result = this.source;
        }
        return result;
    }
}
