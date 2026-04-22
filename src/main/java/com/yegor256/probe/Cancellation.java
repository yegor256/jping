/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Task cancellation.
 *
 * @since 0.3.0
 */
public final class Cancellation {

    /**
     * Futures to cancel.
     */
    private final Future<?>[] futures;

    /**
     * Service to stop.
     */
    private final ExecutorService service;

    /**
     * Ctor.
     *
     * @param executor Service to stop
     * @param items Futures to cancel
     */
    public Cancellation(final ExecutorService executor, final Future<?>... items) {
        this.service = executor;
        this.futures = items.clone();
    }

    public void now() {
        for (final Future<?> future : this.futures) {
            future.cancel(true);
        }
        this.service.shutdownNow();
    }
}
