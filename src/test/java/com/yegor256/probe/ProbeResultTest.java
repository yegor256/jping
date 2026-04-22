/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import com.yegor256.RequestStrategy;
import com.yegor256.expectation.Expectation;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link ProbeResult}.
 *
 * @since 0.3.0
 */
final class ProbeResultTest {

    @Test
    void acceptsMatchingExpectation() {
        MatcherAssert.assertThat(
            new ProbeResult("https://example.com", new Reachability(true, "reachable"))
                .matchedAgainst(
                    new Expectation("https://example.com", RequestStrategy.MANDATORY)
                ).acceptable(),
            Matchers.is(true)
        );
    }

    @Test
    void explainsMismatch() {
        MatcherAssert.assertThat(
            new ProbeResult("https://example.com", new Reachability(false, "offline"))
                .matchedAgainst(
                    new Expectation("https://example.com", RequestStrategy.MANDATORY)
                ).explanation(),
            Matchers.containsString("MANDATORY")
        );
    }
}
