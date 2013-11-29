/*
 * Copyright 2012 Martin Winandy
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package org.pmw.tinylog.policies;

import java.io.File;

import org.pmw.tinylog.LoggingLevel;

/**
 * Policy for rolling log files once at startup.
 */
public final class StartupPolicy implements Policy {

	/**
	 * Returns the name of the policy.
	 * 
	 * @return "startup"
	 */
	public static String getName() {
		return "startup";
	}

	@Override
	public boolean initCheck(final File logFile) {
		return !logFile.exists();
	}

	@Override
	public boolean check(final LoggingLevel level, final String logEntry) {
		return true;
	}

	@Override
	public void reset() {
		// Do nothing
	}

}
