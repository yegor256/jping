/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.decision;

import com.yegor256.config.ConfigurationSource;
import com.yegor256.probe.Probe;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;

/**
 * Decision from source.
 *
 * @since 0.3.0
 */
public final class DecisionFrom {

    /**
     * Probe to use.
     */
    private final Probe probe;

    /**
     * Source to read.
     */
    private final ConfigurationSource source;

    /**
     * Ctor.
     *
     * @param src Source to read
     * @param prb Probe to use
     */
    public DecisionFrom(
        final ConfigurationSource src,
        final Probe prb
    ) {
        this.source = src;
        this.probe = prb;
    }

    public ConditionEvaluationResult result() {
        return this.source
            .configuration()
            .checkedBy(this.probe)
            .asJUnit();
    }
}
