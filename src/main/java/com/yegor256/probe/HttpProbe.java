/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.probe;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;

/**
 * HTTP probe.
 *
 * @since 0.3.0
 */
public final class HttpProbe implements Probe {

    /**
     * Probe timeouts.
     */
    private final ProbeTimeouts timeouts;

    /**
     * Ctor.
     */
    public HttpProbe() {
        this(new ProbeTimeouts(300, 1000));
    }

    /**
     * Ctor.
     *
     * @param limits Probe timeouts
     */
    public HttpProbe(final ProbeTimeouts limits) {
        this.timeouts = limits;
    }

    @Override
    public ProbeResult probe(final String url) {
        ProbeResult result;
        try {
            final URLConnection connection = new URI(url).toURL().openConnection();
            connection.setConnectTimeout(this.timeouts.connect());
            connection.setReadTimeout(this.timeouts.read());
            connection.connect();
            connection.getInputStream().close();
            result = new ProbeResult(url, new Reachability(true, "reachable"));
        } catch (final IOException | URISyntaxException err) {
            final String text;
            if (err.getMessage() == null || err.getMessage().isEmpty()) {
                text = err.getClass().getSimpleName();
            } else {
                text = err.getMessage();
            }
            result = new ProbeResult(url, new Reachability(false, text));
        }
        return result;
    }

    @Override
    public Probe withTimeouts(final ProbeTimeouts limits) {
        return new com.yegor256.probe.HttpProbe(limits);
    }
}
