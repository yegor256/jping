/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

/**
 * Probe.
 * @since 0.3.0
 */
public interface Probe {

    /**
     * Probe a URL.
     * @param url URL to probe
     * @return Probe result
     */
    ProbeResult probe(String url);

    /**
     * Copy with new timeouts.
     * @param timeouts Timeouts to use
     * @return Probe with the given timeouts
     */
    Probe withTimeouts(ProbeTimeouts timeouts);
}
