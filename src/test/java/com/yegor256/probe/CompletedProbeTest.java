/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

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
    void takesCompletedResult() throws Exception {
        try (
            ProbeExecution execution = new ProbeExecution(Executors.newSingleThreadExecutor())
        ) {
            execution.submit(
                () -> new ProbeResult("https://example.com", new Reachability(true, "ok"))
            );
            MatcherAssert.assertThat(
                new CompletedProbe(execution.completion()).result().successful(),
                Matchers.is(true)
            );
        }
    }
}
