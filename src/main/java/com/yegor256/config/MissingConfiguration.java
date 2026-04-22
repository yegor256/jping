/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.decision.OnlineDecision;
import com.yegor256.probe.Probe;

/**
 * Missing configuration.
 *
 * @since 0.3.0
 */
public final class MissingConfiguration implements OnlineConfiguration {

    @Override
    public OnlineConfiguration fallback(final OnlineConfiguration other) {
        return other;
    }

    @Override
    public OnlineDecision checkedBy(final Probe probe) {
        throw new IllegalStateException("Missing configuration can't be checked");
    }
}
