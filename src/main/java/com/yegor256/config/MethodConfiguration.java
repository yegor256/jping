/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256.config;

import com.yegor256.OnlineMeans;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.AnnotationUtils;

/**
 * Configuration from method.
 *
 * @since 0.3.0
 */
public final class MethodConfiguration implements ConfigurationSource {

    /**
     * JUnit context.
     */
    private final ExtensionContext context;

    /**
     * Ctor.
     *
     * @param cnt JUnit context
     */
    public MethodConfiguration(final ExtensionContext cnt) {
        this.context = cnt;
    }

    @Override
    public OnlineConfiguration configuration() {
        return Optional.ofNullable(this.context)
            .flatMap(ExtensionContext::getElement)
            .flatMap(element -> AnnotationUtils.findAnnotation(element, OnlineMeans.class))
            .<OnlineConfiguration>map(AnnotationConfiguration::new)
            .orElseGet(MissingConfiguration::new);
    }
}
