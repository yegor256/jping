/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Probe threads.
 *
 * @since 0.3.0
 */
public final class ProbeThreads {

    /**
     * Number of threads.
     */
    private final int total;

    /**
     * Ctor.
     *
     * @param size Number of threads
     */
    public ProbeThreads(final int size) {
        this.total = size;
    }

    public ExecutorService service() {
        final int size;
        if (this.total == 0) {
            size = 1;
        } else {
            size = this.total;
        }
        return Executors.newFixedThreadPool(size);
    }
}
