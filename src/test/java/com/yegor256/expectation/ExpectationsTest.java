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
 * Test case for {@link Expectations}.
 *
 * @since 0.3.0
 */
final class ExpectationsTest {

    /**
     * URL in tests.
     */
    private static final String URL = "https://example.com";

    @Test
    @SuppressWarnings("PMD.UnitTestContainsTooManyAsserts")
    void makesEnabledDecisionWhenMandatoryRequestSucceeds() {
        MatcherAssert.assertThat(
            new Expectations(
                new Expectation(ExpectationsTest.URL, RequestStrategy.MANDATORY)
            ).checkedBy(
                new FakeProbe().with(
                    ExpectationsTest.URL,
                    new ProbeResult(
                        ExpectationsTest.URL,
                        new Reachability(true, "reachable")
                    )
                )
            ).asJUnit().isDisabled(),
            Matchers.is(false)
        );
    }
}
