package org.java.mikhail.lombok;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LombokDestination {
    private String name;
    private String description;
}
