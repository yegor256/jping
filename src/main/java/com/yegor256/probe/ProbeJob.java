/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import com.yegor256.expectation.Expectation;
import java.util.concurrent.Callable;

/**
 * Probe job.
 *
 * @since 0.3.0
 */
public final class ProbeJob implements Callable<ProbeResult> {

    /**
     * Probe to use.
     */
    private final Probe probe;

    /**
     * Expectation to check.
     */
    private final Expectation expectation;

    /**
     * Ctor.
     *
     * @param exp Expectation to check
     * @param prb Probe to use
     */
    public ProbeJob(final Expectation exp, final Probe prb) {
        this.expectation = exp;
        this.probe = prb;
    }

    @Override
    public ProbeResult call() {
        return this.expectation.checkedBy(this.probe);
    }
}
