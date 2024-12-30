
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