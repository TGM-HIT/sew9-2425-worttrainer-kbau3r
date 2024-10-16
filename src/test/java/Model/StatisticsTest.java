package Model;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class StatisticsTest {

    private Statistics statsDefault;
    private Statistics statsParam;

    @BeforeEach
    public void setUp() {
        statsDefault = new Statistics();
        statsParam = new Statistics(5, 3);
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, statsDefault.getCorrectAttempts());
        assertEquals(0, statsDefault.getWrongAttempts());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(5, statsParam.getCorrectAttempts());
        assertEquals(3, statsParam.getWrongAttempts());
    }

    @Test
    public void testUpdateCorrect() {
        statsDefault.update(true);
        assertEquals(1, statsDefault.getCorrectAttempts());
        assertEquals(0, statsDefault.getWrongAttempts());
    }

    @Test
    public void testUpdateWrong() {
        statsDefault.update(false);
        assertEquals(0, statsDefault.getCorrectAttempts());
        assertEquals(1, statsDefault.getWrongAttempts());
    }

    @Test
    public void testGetStatistics() {
        assertEquals("Richtig: 0, Falsch: 0", statsDefault.getStatistics());
        statsParam.update(true);
        statsParam.update(false);
        assertEquals("Richtig: 6, Falsch: 4", statsParam.getStatistics());
    }
}
