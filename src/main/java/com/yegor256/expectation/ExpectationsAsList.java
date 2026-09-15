/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.expectation;

import java.util.AbstractList;

/**
 * Expectations as list.
 *
 * @since 0.3.0
 */
public final class ExpectationsAsList extends AbstractList<Expectation> {

    /**
     * Expectations.
     */
    private final Expectation[] items;

    /**
     * Ctor.
     *
     * @param exps Expectations
     */
    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    public ExpectationsAsList(final Expectation... exps) {
        this.items = exps;
    }

    @Override
    public Expectation get(final int pos) {
        return this.items[pos];
    }

    @Override
    public int size() {
        return this.items.length;
    }
}
