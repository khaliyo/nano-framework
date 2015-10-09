/*
 * Copyright 1999-2101 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nanoframework.commons.support.logging;

import java.lang.reflect.Constructor;

@SuppressWarnings("rawtypes")
public class LoggerFactory {

    private static Constructor logConstructor;

    static {
        // TODO add slf4j logging

        // 优先选择log4j,而非Apache Common Logging. 因为后者无法设置真实Log调用者的信息
        tryImplementation("org.apache.log4j.Logger", "org.nanoframework.commons.support.logging.Log4jImpl");
        tryImplementation("org.apache.logging.log4j.Logger", "org.nanoframework.commons.support.logging.Log4j2Impl");
        tryImplementation("org.slf4j.Logger", "org.nanoframework.commons.support.logging.SLF4JImpl");
        tryImplementation("org.apache.commons.logging.LogFactory", "org.nanoframework.commons.support.logging.JakartaCommonsLoggingImpl");
        tryImplementation("java.util.logging.Logger", "org.nanoframework.commons.support.logging.Jdk14LoggingImpl");

        if (logConstructor == null) {
            try {
                logConstructor = NoLoggingImpl.class.getConstructor(String.class);
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void tryImplementation(String testClassName, String implClassName) {
        if (logConstructor != null) {
            return;
        }

        try {
            Resources.classForName(testClassName);
            Class implClass = Resources.classForName(implClassName);
            logConstructor = implClass.getConstructor(new Class[] { String.class });

            Class<?> declareClass = logConstructor.getDeclaringClass();
            if (!Logger.class.isAssignableFrom(declareClass)) {
                logConstructor = null;
            }

            try {
                if (null != logConstructor) {
                    logConstructor.newInstance(LoggerFactory.class.getName());
                }
            } catch (Throwable t) {
                logConstructor = null;
            }

        } catch (Throwable t) {
            // skip
        }
    }

    public static Logger getLogger(Class clazz) {
        return getLogger(clazz.getName());
    }

    public static Logger getLogger(String loggerName) {
        try {
            return (Logger) logConstructor.newInstance(loggerName);
        } catch (Throwable t) {
            throw new RuntimeException("Error creating logger for logger '" + loggerName + "'.  Cause: " + t, t);
        }
    }

    @SuppressWarnings("unchecked")
    public static synchronized void selectLog4JLogging() {
        try {
            Resources.classForName("org.apache.log4j.Logger");
            Class implClass = Resources.classForName("com.alibaba.druid.support.logging.Log4jImpl");
            logConstructor = implClass.getConstructor(new Class[] { String.class });
        } catch (Throwable t) {
            //ignore
        }
    }

    @SuppressWarnings("unchecked")
    public static synchronized void selectJavaLogging() {
        try {
            Resources.classForName("java.util.logging.Logger");
            Class implClass = Resources.classForName("com.alibaba.druid.support.logging.Jdk14LoggingImpl");
            logConstructor = implClass.getConstructor(new Class[] { String.class });
        } catch (Throwable t) {
            //ignore
        }
    }
}
