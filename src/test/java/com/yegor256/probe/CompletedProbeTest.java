/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link CompletedProbe}.
 *
 * @since 0.3.0
 */
final class CompletedProbeTest {

    @Test
    @SuppressWarnings("PMD.CloseResource")
    void takesCompletedResult() throws Exception {
        final ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            final CompletionService<ProbeResult> completion =
                new ExecutorCompletionService<>(service);
            completion.submit(
                () -> new ProbeResult("https://example.com", new Reachability(true, "ok"))
            );
            MatcherAssert.assertThat(
                new CompletedProbe(completion).result().successful(),
                Matchers.is(true)
            );
        } finally {
            service.shutdownNow();
        }
    }
}
