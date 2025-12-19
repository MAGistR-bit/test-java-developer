package org.java.mikhail.lombok;

import org.mapstruct.Mapper;

@Mapper
public interface LombokMapper {
    SimpleDestination sourceToDestination(SimpleSource source);
    LombokDestination sourceToLombokDestination(SimpleSource source);
}
