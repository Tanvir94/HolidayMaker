/*
 * Copyright 2020-2030 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.group.foctg.holidayMaker.exceptions;

/**
 *
 * @author Christoffer Hansen &lt;chris.hansen.ch@outlook.com&gt;
 */
@SuppressWarnings("serial")
public class AccommodationNotFoundException extends RuntimeException {
    
     public AccommodationNotFoundException(Long id) {
        super("Could not find card by id = { " + id + " }");
    }
    
    public AccommodationNotFoundException(Short distance) {
        super("Could not find Accommodation by distance = { " + distance + " }");
    }
    
    public AccommodationNotFoundException(Float rating) {
        super("Could not find Accommodation by rating = { " + rating + " }");
    }
    
}
