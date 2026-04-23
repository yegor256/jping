/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.support;

import com.yegor256.config.OnlineConfiguration;
import com.yegor256.decision.OnlineDecision;
import com.yegor256.probe.Probe;

/**
 * Fake configuration.
 *
 * @since 0.3.0
 */
public final class FakeConfiguration implements OnlineConfiguration {

    /**
     * Decision.
     */
    private final OnlineDecision decision;

    /**
     * Ctor.
     *
     * @param outcome Decision
     */
    public FakeConfiguration(final OnlineDecision outcome) {
        this.decision = outcome;
    }

    @Override
    public OnlineConfiguration fallback(final OnlineConfiguration other) {
        return this;
    }

    @Override
    public OnlineDecision checkedBy(final Probe probe) {
        return this.decision;
    }
}
