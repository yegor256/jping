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
 * Test case for {@link ParallelProbing}.
 *
 * @since 0.3.0
 */
final class ParallelProbingTest {

    /**
     * First URL.
     */
    private static final String ONE = "https://one";

    /**
     * Second URL.
     */
    private static final String TWO = "https://two";

    @Test
    void succeedsWhenAllMandatoryRequestsSucceed() {
        MatcherAssert.assertThat(
            new ParallelProbing(
                new FakeProbe()
                    .with(
                        ParallelProbingTest.ONE,
                        new ProbeResult(
                            ParallelProbingTest.ONE,
                            new Reachability(true, "ok")
                        )
                    )
                    .with(
                        ParallelProbingTest.TWO,
                        new ProbeResult(
                            ParallelProbingTest.TWO,
                            new Reachability(false, "off")
                        )
                    ),
                new Expectation(ParallelProbingTest.ONE, RequestStrategy.MANDATORY),
                new Expectation(ParallelProbingTest.TWO, RequestStrategy.OPTIONAL)
            ).outcome().decision().asJUnit().isDisabled(),
            Matchers.is(false)
        );
    }

    @Test
    void failsWhenMandatoryRequestFails() {
        MatcherAssert.assertThat(
            new ParallelProbing(
                new FakeProbe().with(
                    ParallelProbingTest.ONE,
                    new ProbeResult(
                        ParallelProbingTest.ONE,
                        new Reachability(false, "off")
                    )
                ),
                new Expectation(ParallelProbingTest.ONE, RequestStrategy.MANDATORY)
            ).outcome().decision().asJUnit().isDisabled(),
            Matchers.is(true)
        );
    }
}
