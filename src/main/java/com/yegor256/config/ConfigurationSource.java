/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

/**
 * Configuration source.
 *
 * @since 0.3.0
 */
@FunctionalInterface
public interface ConfigurationSource {

    /**
     * Configuration from source.
     *
     * @return Configuration
     */
    OnlineConfiguration configuration();
}
