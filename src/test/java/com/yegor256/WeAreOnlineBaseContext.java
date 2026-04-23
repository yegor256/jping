/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.function.ThrowingConsumer;

/**
 * Base stubs for extra methods introduced by JUnit 6 ExtensionContext.
 *
 * @since 0.2.0
 */
abstract class WeAreOnlineBaseContext implements ExtensionContext {

    @Override
    public void publishFile(
        final String name,
        final MediaType type,
        final ThrowingConsumer<Path> action
    ) {
        throw new UnsupportedOperationException("publishFile not supported");
    }

    @Override
    public void publishDirectory(final String name, final ThrowingConsumer<Path> action) {
        throw new UnsupportedOperationException("publishDirectory not supported");
    }

    @Override
    public List<Class<?>> getEnclosingTestClasses() {
        return Collections.emptyList();
    }

    @Override
    public Store getStore(final StoreScope scope, final Namespace namespace) {
        throw new UnsupportedOperationException("getStore not supported");
    }
}
