/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

/**
 * Default configuration.
 *
 * @since 0.3.0
 */
public final class DefaultConfiguration implements ConfigurationSource {

    @Override
    public OnlineConfiguration configuration() {
        return new AnnotationConfiguration(new DefaultOnlineMeans());
    }
}
