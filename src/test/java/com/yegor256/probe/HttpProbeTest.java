/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Test case for {@link HttpProbe}.
 *
 * @since 0.3.0
 */
final class HttpProbeTest {

    @Test
    void probesReachableFileUri(@TempDir final Path temp) throws Exception {
        final Path file = temp.resolve("one.txt");
        Files.write(file, Collections.singleton("data"), StandardCharsets.UTF_8);
        MatcherAssert.assertThat(
            new HttpProbe().probe(file.toUri().toString()).successful(),
            Matchers.is(true)
        );
    }

    @Test
    void returnsNewProbeWithTimeouts() {
        MatcherAssert.assertThat(
            new HttpProbe().withTimeouts(new ProbeTimeouts(1, 2)),
            Matchers.instanceOf(HttpProbe.class)
        );
    }
}
