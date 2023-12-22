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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * JUnit5 Execution Condition to check that a public Internet connection is online.
 *
 * <p>Use in together with {@link ExecutionCondition}:</p>
 *
 * <code><pre> import com.yegor256.WeAreOnline;
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
        ConditionEvaluationResult ret;
        try {
            if (WeAreOnline.ping()) {
                ret = ConditionEvaluationResult.enabled("We are online!");
            } else {
                ret = ConditionEvaluationResult.disabled("We are offline");
            }
        } catch (final IOException ex) {
            ret = ConditionEvaluationResult.disabled(
                String.format("Failed to check online status: %s", ex.getMessage())
            );
        }
        return ret;
    }

    /**
     * Ping.
     * @return TRUE if we are online
     * @throws IOException In case of check failure
     */
    private static boolean ping() throws IOException {
        boolean online = true;
        try {
            final URLConnection conn = new URI("https://www.google.com").toURL().openConnection();
            conn.connect();
            conn.getInputStream().close();
        } catch (final IOException ignored) {
            online = false;
        } catch (final URISyntaxException ex) {
            throw new IllegalStateException(ex);
        }
        return online;
    }

}
