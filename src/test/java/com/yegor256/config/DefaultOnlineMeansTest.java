/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.OnlineMeans;
import java.util.Collections;
import java.util.HashSet;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link DefaultOnlineMeans}.
 *
 * @since 0.3.0
 */
@OnlineMeans
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

    @Test
    void equalsRuntimeAnnotation() {
        MatcherAssert.assertThat(
            new DefaultOnlineMeans(),
            Matchers.equalTo(DefaultOnlineMeansTest.class.getAnnotation(OnlineMeans.class))
        );
    }

    @Test
    void runtimeAnnotationEqualsDefault() {
        MatcherAssert.assertThat(
            DefaultOnlineMeansTest.class.getAnnotation(OnlineMeans.class),
            Matchers.equalTo(new DefaultOnlineMeans())
        );
    }

    @Test
    void matchesRuntimeAnnotationHashCode() {
        MatcherAssert.assertThat(
            new DefaultOnlineMeans().hashCode(),
            Matchers.equalTo(
                DefaultOnlineMeansTest.class.getAnnotation(OnlineMeans.class).hashCode()
            )
        );
    }

    @Test
    void findsRuntimeAnnotationInDefaultSet() {
        MatcherAssert.assertThat(
            new HashSet<OnlineMeans>(Collections.singleton(new DefaultOnlineMeans()))
                .contains(DefaultOnlineMeansTest.class.getAnnotation(OnlineMeans.class)),
            Matchers.is(true)
        );
    }

    @Test
    void findsDefaultInRuntimeAnnotationSet() {
        MatcherAssert.assertThat(
            new HashSet<>(
                Collections.singleton(DefaultOnlineMeansTest.class.getAnnotation(OnlineMeans.class))
            ).contains(new DefaultOnlineMeans()),
            Matchers.is(true)
        );
    }
}
