/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import java.util.AbstractList;
import java.util.concurrent.Future;

/**
 * Futures as list.
 *
 * @since 0.3.0
 */
public final class FuturesAsList extends AbstractList<Future<?>> {

    /**
     * Futures.
     */
    private final Future<?>[] items;

    /**
     * Ctor.
     *
     * @param items Futures
     */
    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    public FuturesAsList(final Future<?>... items) {
        this.items = items;
    }

    @Override
    public Future<?> get(final int pos) {
        return this.items[pos];
    }

    @Override
    public int size() {
        return this.items.length;
    }
}
