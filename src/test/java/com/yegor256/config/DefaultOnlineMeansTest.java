/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.OnlineMeans;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link DefaultOnlineMeans}.
 *
 * @since 0.3.0
 */
final class DefaultOnlineMeansTest {

    @Test
    void returnsDefaultUrl() {
        MatcherAssert.assertThat(
            new DefaultOnlineMeans().url(),
            Matchers.equalTo("https://www.google.com")
        );
    }

    @Test
    void returnsEmptyRequests() {
        MatcherAssert.assertThat(
            new DefaultOnlineMeans().requests().length,
            Matchers.equalTo(0)
        );
    }

    @Test
    void returnsDefaultConnectTimeout() {
        MatcherAssert.assertThat(
            new DefaultOnlineMeans().connectTimeout(),
            Matchers.equalTo(300)
        );
    }

    @Test
    void returnsDefaultReadTimeout() {
        MatcherAssert.assertThat(
            new DefaultOnlineMeans().readTimeout(),
            Matchers.equalTo(1000)
        );
    }

    @Test
    void returnsDefaultOfflineMode() {
        MatcherAssert.assertThat(
            new DefaultOnlineMeans().offline(),
            Matchers.is(false)
        );
    }

    @Test
    void returnsAnnotationType() {
        MatcherAssert.assertThat(
            new DefaultOnlineMeans().annotationType(),
            Matchers.equalTo(OnlineMeans.class)
        );
    }
}
