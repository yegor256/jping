/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.expectation;

import com.yegor256.OnlineMeans;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.AnnotationUtils;
import org.junit.platform.commons.util.ReflectionUtils;

/**
 * Test case for {@link ExpectationsFrom}.
 *
 * @since 0.3.0
 */
final class ExpectationsFromTest {

    @Test
    void usesLegacyUrlWhenRequestsAreAbsent() {
        MatcherAssert.assertThat(
            new ExpectationsFrom(this.annotation("legacy")).expectations(),
            Matchers.instanceOf(Expectations.class)
        );
    }

    @Test
    void usesRequestsWhenPresent() {
        MatcherAssert.assertThat(
            new ExpectationsFrom(this.annotation("requests")).expectations(),
            Matchers.instanceOf(Expectations.class)
        );
    }

    private OnlineMeans annotation(final String name) {
        return AnnotationUtils.findAnnotation(
            ReflectionUtils.getRequiredMethod(ExpectationsFromTest.class, name),
            OnlineMeans.class
        ).get();
    }

    @SuppressWarnings("unused")
    @OnlineMeans(url = "https://legacy.example.com")
    private void legacy() {
        // used in reflection
    }

    @SuppressWarnings("unused")
    @OnlineMeans(requests = {@com.yegor256.Request(url = "https://example.com")})
    private void requests() {
        // used in reflection
    }
}
