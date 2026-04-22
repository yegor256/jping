/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import java.util.concurrent.ExecutorService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link ProbeThreads}.
 *
 * @since 0.3.0
 */
final class ProbeThreadsTest {

    @Test
    @SuppressWarnings("PMD.CloseResource")
    void createsExecutor() {
        final ExecutorService service = new ProbeThreads(0).service();
        try {
            MatcherAssert.assertThat(service.isShutdown(), Matchers.is(false));
        } finally {
            service.shutdownNow();
        }
    }
}
