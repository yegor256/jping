/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.support;

import com.yegor256.probe.Probe;
import com.yegor256.probe.ProbeResult;
import com.yegor256.probe.ProbeTimeouts;
import java.util.HashMap;
import java.util.Map;

/**
 * Fake probe for tests.
 *
 * @since 0.3.0
 */
public final class FakeProbe implements Probe {

    /**
     * Results by URL.
     */
    private final Map<String, ProbeResult> results;

    /**
     * Timeouts.
     */
    private final ProbeTimeouts timeouts;

    /**
     * Ctor.
     */
    public FakeProbe() {
        this(new HashMap<>(0), new ProbeTimeouts(300, 1000));
    }

    /**
     * Ctor.
     *
     * @param items  Results
     * @param limits Timeouts
     */
    public FakeProbe(final Map<String, ProbeResult> items, final ProbeTimeouts limits) {
        this.results = new HashMap<>(items);
        this.timeouts = limits;
    }

    @Override
    public ProbeResult probe(final String url) {
        return this.results.get(url);
    }

    @Override
    public Probe withTimeouts(final ProbeTimeouts limits) {
        return new com.yegor256.support.FakeProbe(this.results, limits);
    }

    /**
     * Add one result.
     *
     * @param url    URL
     * @param result Result
     * @return New fake probe
     */
    public FakeProbe with(final String url, final ProbeResult result) {
        final Map<String, ProbeResult> items = new HashMap<>(this.results);
        items.put(url, result);
        return new com.yegor256.support.FakeProbe(items, this.timeouts);
    }

    /**
     * Connect timeout.
     *
     * @return Timeout
     */
    public int connectTimeout() {
        return this.timeouts.connect();
    }

    /**
     * Read timeout.
     *
     * @return Timeout
     */
    public int readTimeout() {
        return this.timeouts.read();
    }
}
