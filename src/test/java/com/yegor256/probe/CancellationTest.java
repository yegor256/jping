/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link Cancellation}.
 *
 * @since 0.3.0
 */
final class CancellationTest {

    @Test
    @SuppressWarnings("PMD.CloseResource")
    void cancelsFutures() {
        final ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            final Future<?> future = service.submit(
                () -> {
                    try {
                        Thread.sleep(1000L);
                    } catch (final InterruptedException ignored) {
                        Thread.currentThread().interrupt();
                    }
                }
            );
            new Cancellation(service, future).now();
            MatcherAssert.assertThat(future.isCancelled(), Matchers.is(true));
        } finally {
            service.shutdownNow();
        }
    }
}
