/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

/**
 * Reachability.
 *
 * @since 0.3.0
 */
public final class Reachability {

    /**
     * Availability flag.
     */
    private final boolean status;

    /**
     * State description.
     */
    private final String text;

    /**
     * Ctor.
     *
     * @param value Availability flag
     * @param descr State description
     */
    public Reachability(final boolean value, final String descr) {
        this.status = value;
        this.text = descr;
    }

    public boolean available() {
        return this.status;
    }

    public String description() {
        return this.text;
    }
}
