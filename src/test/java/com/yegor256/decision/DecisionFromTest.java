/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.decision;

import com.yegor256.support.FakeConfiguration;
import com.yegor256.support.FakeConfigurationSource;
import com.yegor256.support.FakeProbe;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link DecisionFrom}.
 *
 * @since 0.3.0
 */
final class DecisionFromTest {

    @Test
    void buildsJUnitDecisionFromSource() {
        MatcherAssert.assertThat(
            new DecisionFrom(
                new FakeConfigurationSource(
                    new FakeConfiguration(new OnlineDecision(true, "ok"))
                ),
                new FakeProbe()
            ).result().isDisabled(),
            Matchers.is(false)
        );
    }
}
