/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.decision;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link ProbingOutcome}.
 *
 * @since 0.3.0
 */
final class ProbingOutcomeTest {

    @Test
    void makesEnabledDecisionFromSuccessfulOutcome() {
        MatcherAssert.assertThat(
            new ProbingOutcome(true, "ok").decision().asJUnit().isDisabled(),
            Matchers.is(false)
        );
    }
}
