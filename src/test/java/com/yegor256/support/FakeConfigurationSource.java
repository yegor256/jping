/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.support;

import com.yegor256.config.ConfigurationSource;
import com.yegor256.config.OnlineConfiguration;

/**
 * Fake configuration source.
 *
 * @since 0.3.0
 */
public final class FakeConfigurationSource implements ConfigurationSource {

    /**
     * Configuration.
     */
    private final OnlineConfiguration config;

    /**
     * Ctor.
     *
     * @param cfg Configuration
     */
    public FakeConfigurationSource(final OnlineConfiguration cfg) {
        this.config = cfg;
    }

    @Override
    public OnlineConfiguration configuration() {
        return this.config;
    }
}
