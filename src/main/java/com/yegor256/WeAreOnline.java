/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256;

import com.yegor256.config.ConfigurationFrom;
import com.yegor256.decision.DecisionFrom;
import com.yegor256.probe.HttpProbe;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

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
        final ExtensionContext context
    ) {
        return new DecisionFrom(
            new ConfigurationFrom(context),
            new HttpProbe()
        ).result();
    }
}
