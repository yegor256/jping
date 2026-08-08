/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link DefaultConfiguration}.
 *
 * @since 0.3.0
 */
final class DefaultConfigurationTest {

    @Test
    void buildsDefaultAnnotationConfiguration() {
        MatcherAssert.assertThat(
            new DefaultConfiguration().configuration(),
            Matchers.instanceOf(AnnotationConfiguration.class)
        );
    }
}
