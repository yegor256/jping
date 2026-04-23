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

/**
 * Test case for {@link ClassConfiguration}.
 *
 * @since 0.3.0
 */
@OnlineMeans(url = "https://example.com")
final class ClassConfigurationTest {

    @Test
    void readsConfigurationFromClass() {
        MatcherAssert.assertThat(
            new ClassConfiguration(
                new WeAreOnlineDefaultContext(ClassConfigurationTest.class)
            ).configuration(),
            Matchers.instanceOf(AnnotationConfiguration.class)
        );
    }
}
