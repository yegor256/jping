/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.expectation;

import com.yegor256.RequestStrategy;
import com.yegor256.probe.ProbeResult;
import com.yegor256.probe.Reachability;
import com.yegor256.support.FakeProbe;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link Expectation}.
 *
 * @since 0.3.0
 */
final class ExpectationTest {

    /**
     * URL in tests.
     */
    private static final String URL = "https://example.com";

    @Test
    void acceptsReachableMandatoryRequest() {
        MatcherAssert.assertThat(
            new Expectation(ExpectationTest.URL, RequestStrategy.MANDATORY)
                .satisfiedBy(new Reachability(true, "reachable")),
            Matchers.is(true)
        );
    }

    @Test
    void acceptsOptionalRequestEvenWhenUnavailable() {
        MatcherAssert.assertThat(
            new Expectation(ExpectationTest.URL, RequestStrategy.OPTIONAL)
                .satisfiedBy(new Reachability(false, "offline")),
            Matchers.is(true)
        );
    }

    @Test
    @SuppressWarnings("PMD.UnitTestContainsTooManyAsserts")
    void checksUrlWithProbe() {
        MatcherAssert.assertThat(
            new Expectation(ExpectationTest.URL, RequestStrategy.MANDATORY)
                .checkedBy(
                    new FakeProbe().with(
                        ExpectationTest.URL,
                        new ProbeResult(
                            ExpectationTest.URL,
                            new Reachability(true, "reachable")
                        )
                    )
                ).successful(),
            Matchers.is(true)
        );
    }
}
