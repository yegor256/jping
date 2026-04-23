/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.OnlineMeans;
import com.yegor256.Request;
import java.lang.annotation.Annotation;

/**
 * Default annotation.
 *
 * @since 0.3.0
 */
public final class DefaultOnlineMeans implements OnlineMeans {

    @Override
    public String url() {
        return "https://www.google.com";
    }

    @Override
    public Request[] requests() {
        return new Request[0];
    }

    @Override
    public int connectTimeout() {
        return 300;
    }

    @Override
    public int readTimeout() {
        return 1000;
    }

    @Override
    public boolean offline() {
        return false;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return OnlineMeans.class;
    }
}
