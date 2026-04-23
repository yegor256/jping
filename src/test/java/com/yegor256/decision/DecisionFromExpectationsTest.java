/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.decision;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link DecisionFromExpectations}.
 *
 * @since 0.3.0
 */
final class DecisionFromExpectationsTest {

    @Test
    void delegatesToOutcomeDecision() {
        MatcherAssert.assertThat(
            new DecisionFromExpectations(new ProbingOutcome(false, "failed"))
                .decision().asJUnit().isDisabled(),
            Matchers.is(true)
        );
    }
}
