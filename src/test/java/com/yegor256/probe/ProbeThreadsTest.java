/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

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
    void createsExecutor() {
        final ProbeExecution execution = new ProbeExecution(new ProbeThreads(0).service());
        try {
            MatcherAssert.assertThat(execution.shutdown(), Matchers.is(false));
        } finally {
            execution.close();
        }
    }
}
