/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.OnlineMeans;
import com.yegor256.decision.Inversion;
import com.yegor256.decision.OnlineDecision;
import com.yegor256.expectation.ExpectationsFrom;
import com.yegor256.probe.Probe;
import com.yegor256.probe.ProbeTimeouts;

/**
 * Annotation configuration.
 *
 * @since 0.3.0
 */
public final class AnnotationConfiguration implements OnlineConfiguration {

    /**
     * Source annotation.
     */
    private final OnlineMeans annotation;

    /**
     * Ctor.
     *
     * @param annt Source annotation
     */
    public AnnotationConfiguration(final OnlineMeans annt) {
        this.annotation = annt;
    }

    @Override
    public OnlineConfiguration fallback(final OnlineConfiguration other) {
        return this;
    }

    @Override
    public OnlineDecision checkedBy(final Probe probe) {
        return new Inversion(
            this.annotation.offline(),
            new ExpectationsFrom(this.annotation)
                .expectations()
                .checkedBy(
                    probe.withTimeouts(
                        new ProbeTimeouts(
                            this.annotation.connectTimeout(),
                            this.annotation.readTimeout()
                        )
                    )
                )
        ).decision();
    }
}
