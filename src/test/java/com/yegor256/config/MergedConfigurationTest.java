/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.decision.OnlineDecision;
import com.yegor256.support.FakeConfiguration;
import com.yegor256.support.FakeConfigurationSource;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link MergedConfiguration}.
 *
 * @since 0.3.0
 */
final class MergedConfigurationTest {

    @Test
    void keepsPrimaryConfiguration() {
        MatcherAssert.assertThat(
            new MergedConfiguration(
                new FakeConfigurationSource(
                    new FakeConfiguration(new OnlineDecision(true, "primary"))
                ),
                new FakeConfigurationSource(new MissingConfiguration()),
                new FakeConfigurationSource(new MissingConfiguration())
            ).configuration(),
            Matchers.instanceOf(FakeConfiguration.class)
        );
    }

    @Test
    void fallsBackWhenPrimaryIsMissing() {
        MatcherAssert.assertThat(
            new MergedConfiguration(
                new FakeConfigurationSource(new MissingConfiguration()),
                new FakeConfigurationSource(
                    new FakeConfiguration(new OnlineDecision(true, "secondary"))
                ),
                new FakeConfigurationSource(new MissingConfiguration())
            ).configuration(),
            Matchers.instanceOf(FakeConfiguration.class)
        );
    }
}
