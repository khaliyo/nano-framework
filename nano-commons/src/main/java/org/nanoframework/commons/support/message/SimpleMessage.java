/*
 * Copyright (c) 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nanoframework.commons.support.message;

/**
 * The simplest possible implementation of Message. It just returns the String given as the constructor argument.
 * @author yanghe
 * @since 1.4.0
 */
public class SimpleMessage implements Message {
    private static final long serialVersionUID = -8398002534962715992L;

    private final String message;

    /**
     * Basic constructor.
     */
    public SimpleMessage() {
        this(null);
    }

    /**
     * Constructor that includes the message.
     * @param message The String message.
     */
    public SimpleMessage(final String message) {
        this.message = message;
    }

    /**
     * Returns the message.
     * @return the message.
     */
    @Override
    public String getFormattedMessage() {
        return message;
    }

    /**
     * Returns the message.
     * @return the message.
     */
    @Override
    public String getFormat() {
        return message;
    }

    /**
     * Returns null since there are no parameters.
     * @return null.
     */
    @Override
    public Object[] getParameters() {
        return null;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final SimpleMessage that = (SimpleMessage) o;

        return !(message != null ? !message.equals(that.message) : that.message != null);
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SimpleMessage[message=" + message + ']';
    }

    /**
     * Always returns null.
     *
     * @return null
     */
    @Override
    public Throwable getThrowable() {
        return null;
    }
}