/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

/**
 * Merged configuration.
 *
 * @since 0.3.0
 */
public final class MergedConfiguration implements ConfigurationSource {

    /**
     * Primary source.
     */
    private final ConfigurationSource primary;

    /**
     * Fallback source.
     */
    private final ConfigurationSource fallback;

    /**
     * Secondary source.
     */
    private final ConfigurationSource secondary;

    /**
     * Ctor.
     *
     * @param first Primary source
     * @param second Secondary source
     * @param third Fallback source
     */
    public MergedConfiguration(
        final ConfigurationSource first,
        final ConfigurationSource second,
        final ConfigurationSource third
    ) {
        this.primary = first;
        this.secondary = second;
        this.fallback = third;
    }

    @Override
    public OnlineConfiguration configuration() {
        return this.primary.configuration()
            .fallback(this.secondary.configuration())
            .fallback(this.fallback.configuration());
    }
}
