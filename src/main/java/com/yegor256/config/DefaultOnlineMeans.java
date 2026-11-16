/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.OnlineMeans;
import com.yegor256.Request;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Default annotation.
 *
 * @since 0.3.0
 */
public final class DefaultOnlineMeans implements OnlineMeans {

    /**
     * Ctor.
     */
    public DefaultOnlineMeans() {
        // nothing to initialize
    }

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

    @Override
    public boolean equals(final Object other) {
        return other instanceof OnlineMeans
            && this.url().equals(((OnlineMeans) other).url())
            && this.connectTimeout() == ((OnlineMeans) other).connectTimeout()
            && this.readTimeout() == ((OnlineMeans) other).readTimeout()
            && this.offline() == ((OnlineMeans) other).offline()
            && Arrays.equals(this.requests(), ((OnlineMeans) other).requests());
    }

    @Override
    public int hashCode() {
        int hash = (127 * "url".hashCode()) ^ this.url().hashCode();
        hash += (127 * "connectTimeout".hashCode()) ^ Integer.hashCode(this.connectTimeout());
        hash += (127 * "readTimeout".hashCode()) ^ Integer.hashCode(this.readTimeout());
        hash += (127 * "offline".hashCode()) ^ Boolean.hashCode(this.offline());
        hash += (127 * "requests".hashCode()) ^ Arrays.hashCode(this.requests());
        return hash;
    }
}
