/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.decision;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link Inversion}.
 *
 * @since 0.3.0
 */
final class InversionTest {

    @Test
    void keepsDecisionWhenNotInverted() {
        MatcherAssert.assertThat(
            new Inversion(false, new OnlineDecision(true, "ok"))
                .decision().asJUnit().isDisabled(),
            Matchers.is(false)
        );
    }

    @Test
    void flipsDecisionWhenInverted() {
        MatcherAssert.assertThat(
            new Inversion(true, new OnlineDecision(true, "ok"))
                .decision().asJUnit().isDisabled(),
            Matchers.is(true)
        );
    }
}
