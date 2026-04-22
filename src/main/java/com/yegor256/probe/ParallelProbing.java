/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import com.yegor256.decision.ProbingOutcome;
import com.yegor256.expectation.Expectation;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Parallel probing.
 *
 * @since 0.3.0
 */
public final class ParallelProbing {

    /**
     * Probe to use.
     */
    private final Probe probe;

    /**
     * Expectations to check.
     */
    private final Expectation[] expectations;

    /**
     * Ctor.
     *
     * @param prb Probe to use
     * @param exps Expectations to check
     */
    public ParallelProbing(final Probe prb, final Expectation... exps) {
        this.expectations = exps.clone();
        this.probe = prb;
    }

    @SuppressWarnings("PMD.CloseResource")
    public ProbingOutcome outcome() {
        final ExecutorService service = new ProbeThreads(this.expectations.length).service();
        final CompletionService<ProbeResult> completion = new ExecutorCompletionService<>(service);
        final Future<?>[] futures = new Future<?>[this.expectations.length];
        boolean success = false;
        boolean failed = false;
        ProbingOutcome outcome = new ProbingOutcome(false, "No request succeeded");
        try {
            this.submitAll(completion, futures);
            for (final Expectation ignored : this.expectations) {
                final ProbeResult result = new CompletedProbe(completion).result();
                if (!result.acceptable()) {
                    new Cancellation(service, futures).now();
                    failed = true;
                    outcome = new ProbingOutcome(false, result.explanation());
                    break;
                }
                if (result.successful()) {
                    success = true;
                }
            }
            if (success && !failed) {
                outcome = new ProbingOutcome(true, "Connectivity expectations satisfied");
            }
        } finally {
            service.shutdownNow();
        }
        return outcome;
    }

    private void submitAll(
        final CompletionService<ProbeResult> completion,
        final Future<?>... futures
    ) {
        for (int idx = 0; idx < this.expectations.length; idx += 1) {
            futures[idx] = completion.submit(
                new ProbeJob(this.expectations[idx], this.probe)
            );
        }
    }
}
