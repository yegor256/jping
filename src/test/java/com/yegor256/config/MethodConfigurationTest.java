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
 * Test case for {@link MethodConfiguration}.
 *
 * @since 0.3.0
 */
final class MethodConfigurationTest {

    @Test
    void readsConfigurationFromMethod() {
        MatcherAssert.assertThat(
            new MethodConfiguration(
                new WeAreOnlineDefaultContext(
                    ReflectionUtils.getRequiredMethod(
                        MethodConfigurationTest.class, "annotated"
                    )
                )
            ).configuration(),
            Matchers.instanceOf(AnnotationConfiguration.class)
        );
    }

    @SuppressWarnings("unused")
    @OnlineMeans(url = "https://example.com")
    private void annotated() {
        // used in reflection
    }
}
