/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link Reachability}.
 *
 * @since 0.3.0
 */
final class ReachabilityTest {

    @Test
    void exposesAvailability() {
        MatcherAssert.assertThat(
            new Reachability(true, "reachable").available(),
            Matchers.is(true)
        );
    }

    @Test
    void exposesDescription() {
        MatcherAssert.assertThat(
            new Reachability(false, "offline").description(),
            Matchers.equalTo("offline")
        );
    }
}
