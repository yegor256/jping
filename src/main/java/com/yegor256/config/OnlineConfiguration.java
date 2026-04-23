/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.decision.OnlineDecision;
import com.yegor256.probe.Probe;

/**
 * Online configuration.
 *
 * @since 0.3.0
 */
public interface OnlineConfiguration {

    /**
     * Use this configuration or another one.
     *
     * @param other The other configuration
     * @return Chosen configuration
     */
    OnlineConfiguration fallback(OnlineConfiguration other);

    /**
     * Make decision with a probe.
     *
     * @param probe The probe
     * @return Decision
     */
    OnlineDecision checkedBy(Probe probe);
}
