/*
 * Copyright 2015-2016 the original author or authors.
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
package org.nanoframework.core.status;

import org.nanoframework.commons.entity.BaseEntity;

/**
 * Http 返回消息对象
 * @author yanghe
 * @since 1.0
 */
@Deprecated
public class ResultMap extends BaseEntity {
    private static final long serialVersionUID = -4525859189036534494L;

    /** 描述 */
    private String info;
    /** 状态码 */
    private int status;
    /** 消息内容 */
    private String message;

    public static final String INFO = "info";
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";

    private ResultMap(int status, String message, String info) {
        this.status = status;
        this.message = message;
        this.info = info;
    }

    public static ResultMap create(int status, String message, String info) {
        return new ResultMap(status, message, info);
    }

    public String getInfo() {
        return info;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
