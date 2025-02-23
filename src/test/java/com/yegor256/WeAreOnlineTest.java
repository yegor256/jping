/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256;

import java.lang.reflect.AnnotatedElement;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.ReflectionUtils;

/**
 * Test case for {@link WeAreOnline}.
 *
 * @since 0.1.0
 */
@ExtendWith(WeAreOnline.class)
final class WeAreOnlineTest {

    @Test
    void checksOnlineStatus() {
        MatcherAssert.assertThat(
            new WeAreOnline().evaluateExecutionCondition(null).isDisabled(),
            Matchers.is(false)
        );
    }

    @Test
    void checkOfflineStatus() {
        final AnnotatedElement element = ReflectionUtils.getRequiredMethod(
            WeAreOnlineTest.class, "overrideTimeout"
        );
        final ExtensionContext context = WeAreOnlineDefaultContext.withElement(element);
        MatcherAssert.assertThat(
            new WeAreOnline().evaluateExecutionCondition(context).isDisabled(),
            Matchers.is(true)
        );
    }

    @Test
    void checkInvertOnlineStatus() {
        final AnnotatedElement element = ReflectionUtils.getRequiredMethod(
            WeAreOnlineTest.class, "overrideOfflineMode"
        );
        final ExtensionContext context = WeAreOnlineDefaultContext.withElement(element);
        MatcherAssert.assertThat(
            new WeAreOnline().evaluateExecutionCondition(context).isDisabled(),
            Matchers.is(true)
        );
    }

    @SuppressWarnings("unused")
    @OnlineMeans(connectTimeout = 1, readTimeout = 1)
    private void overrideTimeout() {
        // empty method for test override annotation
    }

    @SuppressWarnings("unused")
    @OnlineMeans(offline = true)
    private void overrideOfflineMode() {
        // empty method for test override annotation
    }
}
