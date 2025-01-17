/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.runtime.security.token;

import org.apache.hadoop.security.Credentials;

/**
 * Manager for delegation tokens in a Flink cluster.
 *
 * <p>When delegation token renewal is enabled, this manager will make sure long-running apps can
 * run without interruption while accessing secured services. It must contact all the configured
 * secure services to obtain delegation tokens to be distributed to the rest of the application.
 */
public interface DelegationTokenManager {

    /**
     * Obtains new tokens in a one-time fashion and leaves it up to the caller to distribute them.
     */
    void obtainDelegationTokens(Credentials credentials) throws Exception;

    /**
     * Creates a re-occurring task which obtains new tokens and automatically distributes them to
     * task managers.
     */
    void start(DelegationTokenListener delegationTokenListener) throws Exception;

    /** Stops re-occurring token obtain task. */
    void stop();
}
