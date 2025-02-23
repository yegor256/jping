/*
 * The MIT License (MIT)
 *
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExecutableInvoker;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstances;
import org.junit.jupiter.api.parallel.ExecutionMode;

/**
 * Extension context for tests.
 *
 * @since 0.2.0
 */
final class WeAreOnlineDefaultContext implements ExtensionContext {

    /**
     * Annotated element for context.
     */
    private AnnotatedElement element;

    @Override
    public Optional<ExtensionContext> getParent() {
        return Optional.empty();
    }

    @Override
    public ExtensionContext getRoot() {
        return null;
    }

    @Override
    public String getUniqueId() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public Set<String> getTags() {
        return Collections.emptySet();
    }

    @Override
    public Optional<AnnotatedElement> getElement() {
        return Optional.ofNullable(this.element);
    }

    @Override
    public Optional<Class<?>> getTestClass() {
        return Optional.empty();
    }

    @Override
    public Optional<TestInstance.Lifecycle> getTestInstanceLifecycle() {
        return Optional.empty();
    }

    @Override
    public Optional<Object> getTestInstance() {
        return Optional.empty();
    }

    @Override
    public Optional<TestInstances> getTestInstances() {
        return Optional.empty();
    }

    @Override
    public Optional<Method> getTestMethod() {
        return Optional.empty();
    }

    @Override
    public Optional<Throwable> getExecutionException() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getConfigurationParameter(final String key) {
        return Optional.empty();
    }

    @Override
    public <T> Optional<T> getConfigurationParameter(final String key,
        final Function<String, T> transformer) {
        return Optional.empty();
    }

    @Override
    public void publishReportEntry(final Map<String, String> map) {
        // nothing
    }

    @Override
    public Store getStore(final Namespace namespace) {
        return null;
    }

    @Override
    public ExecutionMode getExecutionMode() {
        return null;
    }

    @Override
    public ExecutableInvoker getExecutableInvoker() {
        return null;
    }

    /**
     * Create context with custom annotated element.
     * @param element Annotated element
     * @return Context with annotated element
     */
    static WeAreOnlineDefaultContext withElement(final AnnotatedElement element) {
        final WeAreOnlineDefaultContext context = new WeAreOnlineDefaultContext();
        context.element = element;
        return context;
    }
}
