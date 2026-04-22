/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.decision;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link OnlineDecision}.
 *
 * @since 0.3.0
 */
final class OnlineDecisionTest {

    @Test
    void convertsEnabledDecisionToJUnit() {
        MatcherAssert.assertThat(
            new OnlineDecision(true, "ok").asJUnit().isDisabled(),
            Matchers.is(false)
        );
    }

    @Test
    void invertsDecision() {
        MatcherAssert.assertThat(
            new OnlineDecision(true, "ok").inverted().asJUnit().isDisabled(),
            Matchers.is(true)
        );
    }
}
