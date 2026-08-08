/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Configuration from context.
 *
 * @since 0.3.0
 */
public final class ConfigurationFrom implements ConfigurationSource {

    /**
     * JUnit context.
     */
    private final ExtensionContext context;

    /**
     * Ctor.
     *
     * @param cnt JUnit context
     */
    public ConfigurationFrom(final ExtensionContext cnt) {
        this.context = cnt;
    }

    @Override
    public OnlineConfiguration configuration() {
        final OnlineConfiguration config;
        if (this.context == null) {
            config = new DefaultConfiguration().configuration();
        } else {
            config = new MergedConfiguration(
                new MethodConfiguration(this.context),
                new ClassConfiguration(this.context),
                new DefaultConfiguration()
            ).configuration();
        }
        return config;
    }
}
