/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        MatcherAssert.assertThat(
            new WeAreOnline().evaluateExecutionCondition(
                new WeAreOnlineDefaultContext(
                    ReflectionUtils.getRequiredMethod(
                        WeAreOnlineTest.class, "overrideTimeout"
                    )
                )
            ).isDisabled(),
            Matchers.is(true)
        );
    }

    @Test
    void checkInvertOnlineStatus() {
        MatcherAssert.assertThat(
            new WeAreOnline().evaluateExecutionCondition(
                new WeAreOnlineDefaultContext(
                    ReflectionUtils.getRequiredMethod(
                        WeAreOnlineTest.class, "overrideOfflineMode"
                    )
                )
            ).isDisabled(),
            Matchers.is(true)
        );
    }

    @Test
    void keepsEnabledWhenOptionalRequestFails() {
        MatcherAssert.assertThat(
            new WeAreOnline().evaluateExecutionCondition(
                new WeAreOnlineDefaultContext(
                    ReflectionUtils.getRequiredMethod(
                        WeAreOnlineTest.class, "overrideOptionalRequest"
                    )
                )
            ).isDisabled(),
            Matchers.is(false)
        );
    }

    @Test
    void disablesWhenMandatoryRequestFails() {
        MatcherAssert.assertThat(
            new WeAreOnline().evaluateExecutionCondition(
                new WeAreOnlineDefaultContext(
                    ReflectionUtils.getRequiredMethod(
                        WeAreOnlineTest.class, "overrideMandatoryRequest"
                    )
                )
            ).isDisabled(),
            Matchers.is(true)
        );
    }

    @SuppressWarnings("unused")
    @OnlineMeans(url = "http://127.0.0.1:1")
    private void overrideTimeout() {
        // empty method for test override annotation
    }

    @SuppressWarnings("unused")
    @OnlineMeans(offline = true)
    private void overrideOfflineMode() {
        // empty method for test override annotation
    }

    @SuppressWarnings("unused")
    @OnlineMeans(
        requests = {
            @Request(url = "https://www.google.com"),
            @Request(
                url = "http://127.0.0.1:1",
                strategy = RequestStrategy.OPTIONAL
            )
        }
    )
    private void overrideOptionalRequest() {
        // empty method for test override annotation
    }

    @SuppressWarnings("unused")
    @OnlineMeans(
        requests = {
            @Request(url = "https://www.google.com"),
            @Request(url = "http://127.0.0.1:1")
        }
    )
    private void overrideMandatoryRequest() {
        // empty method for test override annotation
    }
}
