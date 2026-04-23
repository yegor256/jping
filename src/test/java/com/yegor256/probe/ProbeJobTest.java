/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import com.yegor256.RequestStrategy;
import com.yegor256.expectation.Expectation;
import com.yegor256.support.FakeProbe;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link ProbeJob}.
 *
 * @since 0.3.0
 */
final class ProbeJobTest {

    @Test
    void callsProbeWhenExecuted() throws Exception {
        MatcherAssert.assertThat(
            new ProbeJob(
                new Expectation("https://example.com", RequestStrategy.MANDATORY),
                new FakeProbe().with(
                    "https://example.com",
                    new ProbeResult(
                        "https://example.com",
                        new Reachability(true, "reachable")
                    )
                )
            ).call().successful(),
            Matchers.is(true)
        );
    }
}
