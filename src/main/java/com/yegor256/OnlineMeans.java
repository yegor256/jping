/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Override ping options.
 * @author ishchenko
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface OnlineMeans {
    /**
     * URL to ping.
     * @return Public URL
     */
    String url() default "https://www.google.com";

    /**
     * Connection timeout.
     * @return Timeout in milliseconds
     */
    int connectTimeout() default 300;

    /**
     * Read timeout.
     * @return Timeout in milliseconds
     */
    int readTimeout() default 1000;

    /**
     * Set true if test should run only then offline.
     * @return Offline mode
     */
    boolean offline() default false;
}
