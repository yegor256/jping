/*
 * SPDX-FileCopyrightText: Copyright (c) 2023 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.yegor256;

/**
 * Request strategy.
 *
 * @since 0.3.0
 */
public enum RequestStrategy {
    /**
     * Mandatory request.
     */
    MANDATORY,

    /**
     * Optional request.
     */
    OPTIONAL
}
