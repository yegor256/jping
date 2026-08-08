/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import com.yegor256.decision.ProbingOutcome;
import com.yegor256.expectation.Expectation;
import com.yegor256.expectation.ExpectationsAsList;
import java.util.List;
import java.util.concurrent.CompletionService;
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
    private final List<Expectation> expectations;

    /**
     * Ctor.
     *
     * @param prb Probe to use
     * @param exps Expectations to check
     */
    // @checkstyle ConstructorsCodeFreeCheck (1 line)
    public ParallelProbing(final Probe prb, final Expectation... exps) {
        this(prb, new ExpectationsAsList(exps));
    }

    /**
     * Ctor.
     *
     * @param prb Probe to use
     * @param exps Expectations to check
     */
    public ParallelProbing(final Probe prb, final List<Expectation> exps) {
        this.expectations = exps;
        this.probe = prb;
    }

    public ProbingOutcome outcome() {
        boolean success = false;
        boolean failed = false;
        ProbingOutcome outcome = new ProbingOutcome(false, "No request succeeded");
        try (
            ProbeExecution execution = new ProbeExecution(
                new ProbeThreads(this.expectations.size()).service()
            )
        ) {
            final CompletionService<ProbeResult> completion = execution.completion();
            final Future<?>[] futures = new Future<?>[this.expectations.size()];
            this.submitAll(execution, futures);
            for (final Expectation ignored : this.expectations) {
                final ProbeResult result = new CompletedProbe(completion).result();
                if (!result.acceptable()) {
                    execution.cancellation(futures).now();
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
        }
        return outcome;
    }

    private void submitAll(
        final ProbeExecution execution,
        final Future<?>... futures
    ) {
        for (int idx = 0; idx < this.expectations.size(); idx += 1) {
            futures[idx] = execution.submit(
                new ProbeJob(this.expectations.get(idx), this.probe)
            );
        }
    }
}
