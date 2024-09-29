package Model;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    private static Model model;
    private static Data data1;
    private static Data data2;

    @BeforeAll // Muss static anscheinend
    public static void setUp() throws Exception {
        data1 = new Data("Example 1", "http://example.com/1");
        data2 = new Data("Example 2", "http://example.com/2");

        List<Data> dataList = new ArrayList<>();
        dataList.add(data1);
        dataList.add(data2);

        model = new Model(dataList); // verwendet den test.ndjson
    }

    @Test
    public void testAddData() {
        Data newData = new Data("Example 3", "http://example.com/3");
        model.addData(newData);
        assertTrue(model.getDataList().contains(newData));
    }

    @Test
    public void testRemoveData() {
        model.removeData(data1);
        assertFalse(model.getDataList().contains(data1));
    }

    @Test
    public void testGetRandomData() {
        Data randomData = model.getRandomData();
        assertTrue(model.getDataList().contains(randomData));
    }

    @Test
    public void testGetSpecificData() {
        Data specificData = model.getSpecificData(1);
        assertEquals(data2, specificData);
    }

    @Test
    public void testAddDataToJson() throws Exception {
        Data newData = new Data("Example 3", "http://example.com/3");
        model.addDataToJson(newData);

        List<Data> readDataList = model.readDataFromJson();
        assertTrue(readDataList.stream().anyMatch(data -> data.getName().equals("Example 3")));
    }


    // fix this
    @Test
    public void testReadDataFromJson() throws Exception {
        Data newData = new Data("Example 3", "http://example.com/3");
        model.addData(newData);
        List<Data> dataList = model.readDataFromJson();
        assertTrue(dataList.contains(newData));
    }

    @Test
    public void testCompareDatalist() {
        List<Data> dataList = new ArrayList<>();
        dataList.add(data1);
        dataList.add(data2);

        assertTrue(model.compareDatalist(dataList));

        dataList.remove(data1);
        assertFalse(model.compareDatalist(dataList));
    }
}
