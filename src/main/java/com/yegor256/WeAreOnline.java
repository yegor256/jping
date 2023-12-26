/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.yegor256;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.Optional;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.AnnotationUtils;

/**
 * JUnit5 Execution Condition to check that a public Internet connection is online.
 *
 * <p>Use in together with {@link ExecutionCondition}:</p>
 *
 * <code><pre> import com.yegor256.WeAreOnline;
 * import org.junit.jupiter.api.extension.ExtendWith;
 *
 * &#64;ExtendWith(WeAreOnline.class)
 * final class MyTest {
 *   // Your test methods
 * }</pre></code>
 *
 * <p>This will guarantee you that the test methods inside this class
 * will only be executed if a public Internet connection is available.</p>
 *
 * @since 0.0.1
 */
public final class WeAreOnline implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(
        final ExtensionContext context) {
        final WeAreOnlineOverride overrides = WeAreOnline.retrieveSettings(context);
        ConditionEvaluationResult ret;
        try {
            if (WeAreOnline.ping(overrides)) {
                ret = ConditionEvaluationResult.enabled("We are online!");
            } else {
                ret = ConditionEvaluationResult.disabled("We are offline");
            }
        } catch (final IOException ex) {
            ret = ConditionEvaluationResult.disabled(
                String.format(
                    "Failed to check online status: %s",
                    ex.getMessage()
                )
            );
        }
        if (overrides.offline()) {
            if (ret.isDisabled()) {
                ret = ConditionEvaluationResult.enabled(ret.getReason().orElse(null));
            } else {
                ret = ConditionEvaluationResult.disabled(ret.getReason().orElse(null));
            }
        }
        return ret;
    }

    /**
     * Find settings for ping method. If settings not found, then return default.
     * @param context Test context
     * @return Settings for ping
     */
    private static WeAreOnlineOverride retrieveSettings(final ExtensionContext context) {
        return Optional.ofNullable(context)
            .flatMap(ExtensionContext::getElement)
            .flatMap(
                element -> AnnotationUtils.findAnnotation(element, WeAreOnlineOverride.class)
            )
            .orElse(new DefaultSettings());
    }

    /**
     * Ping.
     * @param overrides Override default ping settings.
     * @return TRUE if we are online
     * @throws IOException In case of check failure
     * @since 0.2.0
     */
    private static boolean ping(final WeAreOnlineOverride overrides) throws IOException {
        boolean online = true;
        try {
            final URLConnection conn = new URI(overrides.url()).toURL().openConnection();
            conn.setConnectTimeout(overrides.connectTimeout());
            conn.setReadTimeout(overrides.readTimeout());
            conn.connect();
            conn.getInputStream().close();
        } catch (final IOException ignored) {
            online = false;
        } catch (final URISyntaxException ex) {
            throw new IllegalStateException(ex);
        }
        return online;
    }

    /**
     * Default settings if WeAreOnlineOverride is not present.
     * @since 0.2.0
     */
    private static class DefaultSettings implements Annotation, WeAreOnlineOverride {

        @Override
        public String url() {
            return "https://www.google.com";
        }

        @Override
        public int connectTimeout() {
            return 300;
        }

        @Override
        public int readTimeout() {
            return 1000;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return WeAreOnlineOverride.class;
        }

        @Override
        public boolean offline() {
            return false;
        }
    }
}
