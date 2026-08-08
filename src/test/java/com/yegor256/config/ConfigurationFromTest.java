/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.OnlineMeans;
import com.yegor256.WeAreOnlineDefaultContext;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

/**
 * Test case for {@link ConfigurationFrom}.
 *
 * @since 0.3.0
 */
@OnlineMeans(url = "https://class.example.com")
final class ConfigurationFromTest {

    @Test
    void usesDefaultWhenContextIsNull() {
        MatcherAssert.assertThat(
            new ConfigurationFrom(null).configuration(),
            Matchers.instanceOf(AnnotationConfiguration.class)
        );
    }

    @Test
    void prefersMethodOverClass() {
        MatcherAssert.assertThat(
            new ConfigurationFrom(
                new WeAreOnlineDefaultContext(
                    ReflectionUtils.getRequiredMethod(
                        ConfigurationFromTest.class, "methodAnnotated"
                    )
                )
            ).configuration(),
            Matchers.instanceOf(AnnotationConfiguration.class)
        );
    }

    @SuppressWarnings("unused")
    @OnlineMeans(url = "https://method.example.com")
    private void methodAnnotated() {
        // used in reflection
    }
}
