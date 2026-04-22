/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.expectation;

import com.yegor256.OnlineMeans;
import com.yegor256.Request;
import com.yegor256.RequestStrategy;

/**
 * Expectations from annotation.
 *
 * @since 0.3.0
 */
public final class ExpectationsFrom {

    /**
     * Source annotation.
     */
    private final OnlineMeans annotation;

    /**
     * Ctor.
     *
     * @param annt Source annotation
     */
    public ExpectationsFrom(final OnlineMeans annt) {
        this.annotation = annt;
    }

    public Expectations expectations() {
        final Expectations all;
        if (this.annotation.requests().length == 0) {
            all = new Expectations(
                new Expectation(
                    this.annotation.url(),
                    RequestStrategy.MANDATORY
                )
            );
        } else {
            final Request[] requests = this.annotation.requests();
            final Expectation[] items = new Expectation[requests.length];
            for (int idx = 0; idx < requests.length; idx += 1) {
                items[idx] = new Expectation(
                    requests[idx].url(),
                    requests[idx].strategy()
                );
            }
            all = new Expectations(items);
        }
        return all;
    }
}
