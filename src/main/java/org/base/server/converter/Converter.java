/*
 *
 *  *
 *  *  * Copyright 2018 King's College London
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *  *
 *  *
 *
 */

package org.base.server.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Converter<T, S> {

    T dtoToEntity(S s);

    S entityToDto(T t);

    default List<T> dtosToEntities(Collection<S> ss) {
        return ss.parallelStream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    default List<S> entitiesToDtos(Collection<T> ts) {
        return ts.parallelStream().map(this::entityToDto).collect(Collectors.toList());
    }
}