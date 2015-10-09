/**
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 			http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nanoframework.ssh.exception;

/**
 * 
 * @author yanghe
 * @date 2015年8月21日 上午11:00:54
 * @since 1.1
 */
public class UnSupportedSSHException extends RuntimeException {
	private static final long serialVersionUID = -2385374239061292209L;

	public UnSupportedSSHException() {

	}

	public UnSupportedSSHException(String message) {
		super(message);
	}
	
	public UnSupportedSSHException(Throwable cause) {
		super(cause);
	}

	public UnSupportedSSHException(String message, Throwable cause) {
		super(message, cause);
	}
}
