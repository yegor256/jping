/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

/**
 * Probe timeouts.
 *
 * @since 0.3.0
 */
public final class ProbeTimeouts {

    /**
     * Read timeout.
     */
    private final int reading;

    /**
     * Connect timeout.
     */
    private final int connection;

    /**
     * Ctor.
     *
     * @param cto Connect timeout
     * @param rto Read timeout
     */
    public ProbeTimeouts(final int cto, final int rto) {
        this.connection = cto;
        this.reading = rto;
    }

    public int connect() {
        return this.connection;
    }

    public int read() {
        return this.reading;
    }
}
