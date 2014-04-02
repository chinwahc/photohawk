/*******************************************************************************
 * Copyright 2010-2014 Vienna University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package at.ac.tuwien.photohawk.dcraw;

/**
 * Exception for DCRaw image reader.
 */
public class DcrawException extends RuntimeException {
    /**
     * Creates a new exception with the provided message.
     *
     * @param message the message
     */
    public DcrawException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the provided message and cause.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DcrawException(String message, Throwable cause) {
        super(message, cause);
    }
}
