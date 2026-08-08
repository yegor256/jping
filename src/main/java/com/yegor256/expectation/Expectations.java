/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.expectation;

import com.yegor256.decision.DecisionFromExpectations;
import com.yegor256.decision.OnlineDecision;
import com.yegor256.probe.ParallelProbing;
import com.yegor256.probe.Probe;
import java.util.List;

/**
 * Expectations.
 *
 * @since 0.3.0
 */
public final class Expectations {

    /**
     * All expectations.
     */
    private final List<Expectation> items;

    /**
     * Ctor.
     *
     * @param exps Expectations
     */
    // @checkstyle ConstructorsCodeFreeCheck (1 line)
    public Expectations(final Expectation... exps) {
        this(new ExpectationsAsList(exps));
    }

    /**
     * Ctor.
     *
     * @param exps Expectations
     */
    public Expectations(final List<Expectation> exps) {
        this.items = exps;
    }

    public OnlineDecision checkedBy(final Probe probe) {
        return new DecisionFromExpectations(
            new ParallelProbing(probe, this.items).outcome()
        ).decision();
    }
}
