/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Probe execution.
 *
 * @since 0.3.0
 */
public final class ProbeExecution implements AutoCloseable {

    /**
     * Executor service.
     */
    private final ExecutorService service;

    /**
     * Completion service.
     */
    private final CompletionService<ProbeResult> answers;

    /**
     * Ctor.
     *
     * @param executor Executor service
     */
    public ProbeExecution(final ExecutorService executor) {
        this.service = executor;
        this.answers = new ExecutorCompletionService<>(this.service);
    }

    /**
     * Completion service.
     *
     * @return Completion service
     */
    public CompletionService<ProbeResult> completion() {
        return this.answers;
    }

    /**
     * Submit one job.
     *
     * @param job Probe job
     * @return Future
     */
    public Future<?> submit(final Callable<ProbeResult> job) {
        return this.answers.submit(job);
    }

    /**
     * Submit one action.
     *
     * @param action Action
     * @return Future
     */
    public Future<?> submit(final Runnable action) {
        return this.service.submit(action);
    }

    /**
     * Create cancellation.
     *
     * @param items Futures
     * @return Cancellation
     */
    public Cancellation cancellation(final Future<?>... items) {
        return new Cancellation(this.service, items);
    }

    /**
     * Check shutdown state.
     *
     * @return Shutdown flag
     */
    public boolean shutdown() {
        return this.service.isShutdown();
    }

    @Override
    public void close() {
        this.service.shutdownNow();
    }
}
