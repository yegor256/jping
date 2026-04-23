/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

/**
 * Probe.
 *
 * @since 0.3.0
 */
public interface Probe {

    ProbeResult probe(String url);

    Probe withTimeouts(ProbeTimeouts timeouts);
}
