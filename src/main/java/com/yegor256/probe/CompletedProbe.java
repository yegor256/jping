/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;

/**
 * Completed probe.
 *
 * @since 0.3.0
 */
public final class CompletedProbe {

    /**
     * Completion service.
     */
    private final CompletionService<ProbeResult> completion;

    /**
     * Ctor.
     *
     * @param cpl Completion service
     */
    public CompletedProbe(final CompletionService<ProbeResult> cpl) {
        this.completion = cpl;
    }

    public ProbeResult result() {
        try {
            return this.completion.take().get();
        } catch (final InterruptedException err) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(err);
        } catch (final ExecutionException err) {
            throw new IllegalStateException(err);
        }
    }
}
