package Model;

import org.junit.jupiter.api.Test;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    @Test
    void testAddition() {
        assertEquals(2, 1 + 1, "1 + 1 should equal 2");
    }

    @Test
    void testAddName() {
        Data data = new Data("Example", "http://example.com");
        data.addName("New Name");
        assertEquals("New Name", data.getName(), "Name should be updated to 'New Name'");
    }

    @Test
    void testCheckName() {
        Data data = new Data("Example", "http://example.com");
        assertTrue(data.checkName("Example"), "The name 'Example' should match (ignoring case)");
        assertFalse(data.checkName("Different"), "The name 'Different' should not match");
    }

    @Test
    void testAddUrlValid() {
        Data data = new Data("Example", "http://example.com");
        boolean result = data.addUrl("http://newurl.com");
        assertTrue(result, "Adding a valid URL should return true");
        assertEquals("http://newurl.com", data.getUrl().toString(), "URL should be updated to 'http://newurl.com'");
    }

    @Test
    void testAddUrlInvalid() {
        Data data = new Data("Example", "http://example.com");
        boolean result = data.addUrl("invalid-url");
        assertFalse(result, "Adding an invalid URL should return false");
    }

    @Test
    void testCheckUrlValid() {
        Data data = new Data("Example", "http://example.com");
        boolean result = data.checkUrl("http://example.com");
        assertTrue(result, "A valid URL should return true in checkUrl");
    }

    @Test
    void testCheckUrlInvalid() {
        Data data = new Data("Example", "http://example.com");
        boolean result = data.checkUrl("invalid-url");
        assertFalse(result, "An invalid URL should return false in checkUrl");
    }
}
