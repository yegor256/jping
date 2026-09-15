/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Request expectation.
 *
 * @since 0.3.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Request {

    /**
     * URL to check.
     *
     * @return URL
     */
    String url();

    /**
     * Request strategy.
     *
     * @return Strategy
     */
    RequestStrategy strategy() default RequestStrategy.MANDATORY;
}
