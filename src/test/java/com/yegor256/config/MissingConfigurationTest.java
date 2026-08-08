/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.decision.OnlineDecision;
import com.yegor256.support.FakeConfiguration;
import com.yegor256.support.FakeProbe;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link MissingConfiguration}.
 *
 * @since 0.3.0
 */
final class MissingConfigurationTest {

    @Test
    void fallsBackToOtherConfiguration() {
        MatcherAssert.assertThat(
            new MissingConfiguration().fallback(
                new FakeConfiguration(new OnlineDecision(true, "yes"))
            ),
            Matchers.instanceOf(FakeConfiguration.class)
        );
    }

    @Test
    void failsWhenChecked() {
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalStateException.class,
            () -> new MissingConfiguration().checkedBy(new FakeProbe())
        );
    }
}
