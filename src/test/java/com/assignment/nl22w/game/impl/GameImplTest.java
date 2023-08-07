package com.assignment.nl22w.game.impl;

import com.assignment.nl22w.game.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameImplTest {

    @Autowired
    private Game game;

    @MockBean
    private Resource resource;

    @Test
    public void escapeFromTheWoods_ValidMap_ReturnsElevenSteps() throws IOException {
        int expectedSteps = 11;

        resource = new ClassPathResource("valid_map3.txt");

        int steps = game.escapeFromTheWoods(resource);
        assertEquals(expectedSteps, steps);
    }

    @Test
    public void escapeFromTheWoods_ValidMap2_ReturnsThirteenSteps() throws IOException {
        int expectedSteps = 13;

        resource = new ClassPathResource("valid_map2.txt");

        int steps = game.escapeFromTheWoods(resource);

        assertEquals(expectedSteps, steps);
    }

    @Test
    public void escapeFromTheWoods_ValidMap1_ReturnsFourSteps() throws IOException {
        int expectedSteps = 4;

        resource = new ClassPathResource("valid_map1.txt");

        int steps = game.escapeFromTheWoods(resource);

        assertEquals(expectedSteps, steps);
    }

    @Test
    public void escapeFromTheWoods_EmptyMap_ReturnsZeroSteps() throws IOException {
        resource = new ClassPathResource("empty_map.txt");

        int steps = game.escapeFromTheWoods(resource);

        assertEquals(0, steps);
    }

    @Test
    public void escapeFromTheWoods_NoExitMap_ReturnsZeroSteps() throws IOException {
        resource = new ClassPathResource("no_exit_map.txt");

        int steps = game.escapeFromTheWoods(resource);

        assertEquals(0, steps);
    }

    @Test
    public void escapeFromTheWoods_NoStartPosition_ReturnsZeroSteps() throws IOException {
        resource = new ClassPathResource("invalid_map_without_start.txt");

        int steps = game.escapeFromTheWoods(resource);

        assertEquals(0, steps);
    }

    @Test
    public void escapeFromTheWoods_InvalidMapSymbols_ReturnsZeroSteps() throws IOException {
        resource = new ClassPathResource("invalid_map_symbols.txt");

        int steps = game.escapeFromTheWoods(resource);

        assertEquals(0, steps);
    }

    @Test
    public void escapeFromTheWoods_InvalidMapShape_ReturnsZeroSteps() throws IOException {
        resource = new ClassPathResource("invalid_map_shape.txt");

        int steps = game.escapeFromTheWoods(resource);

        assertEquals(0, steps);
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid_map_large.txt", "invalid_map_small.txt"})
    public void escapeFromTheWoods_InvalidMapSize_ReturnsZeroSteps(String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource(filename);

        int steps = game.escapeFromTheWoods(resource);

        assertEquals(0, steps, "Corrupted file: " + filename);
    }

    @Test
    public void escapeFromTheWoods_ResourceReadFailure_ReturnsZeroSteps() throws IOException {
        resource = new ClassPathResource("no_such_resource.txt");

        int steps = game.escapeFromTheWoods(resource);

        assertEquals(0, steps);
    }
}
