/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.OnlineMeans;
import com.yegor256.probe.ProbeResult;
import com.yegor256.probe.ProbeTimeouts;
import com.yegor256.probe.Reachability;
import com.yegor256.support.FakeProbe;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.AnnotationUtils;
import org.junit.platform.commons.util.ReflectionUtils;

/**
 * Test case for {@link AnnotationConfiguration}.
 *
 * @since 0.3.0
 */
final class AnnotationConfigurationTest {

    /**
     * URL in tests.
     */
    private static final String URL = "https://example.com";

    @Test
    void usesFallbackToKeepCurrentConfiguration() {
        MatcherAssert.assertThat(
            new AnnotationConfiguration(this.annotation("mandatory"))
                .fallback(new MissingConfiguration()),
            Matchers.instanceOf(AnnotationConfiguration.class)
        );
    }

    @Test
    @SuppressWarnings("PMD.UnitTestContainsTooManyAsserts")
    void checksWithConfiguredTimeouts() {
        final OnlineMeans annotation = this.annotation("mandatory");
        final FakeProbe probe = new FakeProbe().with(
            AnnotationConfigurationTest.URL,
            new ProbeResult(
                AnnotationConfigurationTest.URL,
                new Reachability(true, "reachable")
            )
        );
        new AnnotationConfiguration(annotation).checkedBy(probe);
        MatcherAssert.assertThat(
            probe.withTimeouts(
                new ProbeTimeouts(
                    annotation.connectTimeout(),
                    annotation.readTimeout()
                )
            ).probe(AnnotationConfigurationTest.URL).successful(),
            Matchers.is(true)
        );
    }

    @Test
    @SuppressWarnings("PMD.UnitTestContainsTooManyAsserts")
    void invertsDecisionWhenOfflineModeIsEnabled() {
        MatcherAssert.assertThat(
            new AnnotationConfiguration(this.annotation("offline")).checkedBy(
                new FakeProbe().with(
                    AnnotationConfigurationTest.URL,
                    new ProbeResult(
                        AnnotationConfigurationTest.URL,
                        new Reachability(true, "reachable")
                    )
                )
            ).asJUnit().isDisabled(),
            Matchers.is(true)
        );
    }

    private OnlineMeans annotation(final String name) {
        return AnnotationUtils.findAnnotation(
            ReflectionUtils.getRequiredMethod(AnnotationConfigurationTest.class, name),
            OnlineMeans.class
        ).get();
    }

    @SuppressWarnings("unused")
    @OnlineMeans(
        url = AnnotationConfigurationTest.URL,
        connectTimeout = 5,
        readTimeout = 7
    )
    private void mandatory() {
        // used in reflection
    }

    @SuppressWarnings("unused")
    @OnlineMeans(url = AnnotationConfigurationTest.URL, offline = true)
    private void offline() {
        // used in reflection
    }
}
