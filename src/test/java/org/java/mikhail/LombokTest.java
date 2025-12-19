package org.java.mikhail;

import org.java.mikhail.lombok.LombokDestination;
import org.java.mikhail.lombok.LombokMapper;
import org.java.mikhail.lombok.SimpleDestination;
import org.java.mikhail.lombok.SimpleSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class LombokTest {

    private final LombokMapper lombokMapper = Mappers.getMapper(LombokMapper.class);

    private String name;

    @BeforeEach
    void setUp() {
        name = "Misha";
    }

    @Test
    void test() {
        String expectedName = "Misha";
        assertThat(name).isEqualTo(expectedName);
    }

    @Test
    void whenDestinationIsMapped_thenIsSuccessful() {
        SimpleSource simpleSource = new SimpleSource();
        simpleSource.setName("file");
        simpleSource.setDescription("A text file.");

        SimpleDestination simpleDestination = lombokMapper.sourceToDestination(simpleSource);
        Assertions.assertNotNull(simpleDestination);
        Assertions.assertEquals(simpleSource.getName(), simpleDestination.getName());
        Assertions.assertEquals(simpleSource.getDescription(), simpleDestination.getDescription());

        LombokDestination lombokDestination = lombokMapper.sourceToLombokDestination(simpleSource);
        Assertions.assertNotNull(lombokDestination);
        Assertions.assertEquals(simpleSource.getName(), lombokDestination.getName());
        Assertions.assertEquals(simpleSource.getDescription(), lombokDestination.getDescription());
    }
}