/*
 *
 *   Copyright (C) 2015-2017 Monogram Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package io.metagraph.auth.exception;

import java.io.Serializable;


/**
 * @author ZhaoPeng
 */
public class MetagraphAuthException extends RuntimeException implements Serializable {

    protected int statusCode;//http status code
    protected String code;//error code;
    protected String message;//error message
    protected String description;//description

    public MetagraphAuthException() {
    }

    public MetagraphAuthException(Throwable cause) {
        super(cause);
    }

    public MetagraphAuthException(String message) {
        super(message);
        this.message = message;
    }

    public MetagraphAuthException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public MetagraphAuthException setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MetagraphAuthException setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public MetagraphAuthException setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public MetagraphAuthException setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

}
