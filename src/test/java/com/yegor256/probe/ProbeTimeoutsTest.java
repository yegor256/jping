/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link ProbeTimeouts}.
 *
 * @since 0.3.0
 */
final class ProbeTimeoutsTest {

    @Test
    void exposesConnectTimeout() {
        MatcherAssert.assertThat(
            new ProbeTimeouts(5, 7).connect(),
            Matchers.equalTo(5)
        );
    }

    @Test
    void exposesReadTimeout() {
        MatcherAssert.assertThat(
            new ProbeTimeouts(5, 7).read(),
            Matchers.equalTo(7)
        );
    }
}
