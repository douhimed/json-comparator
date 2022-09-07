package org.adex.main;

import org.adex.services.JsonComparator;
import org.adex.services.impl.JsonComparatorImpl;
import org.adex.utilities.JsonUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonComparatorApplication {

    public static void main(String[] args) {
        System.out.println("JsonComparatorApplication started");


        String sampleObj = "{\"1\": \"A\", \"2\":\"B\"}";
        String sampleObj1 = "{\"1\": \"A\", \"2\":\"B\"}";
        String sampleObj2 = "{\"1\": \"A\", \"2\":\"C\"}";
        String sampleObj3 = "{\"1\": \"A\", \"2\":\"C\", \"3\":\"C\"}";
        String complexObj = "{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}, \"2\":[0,1,2]}";

        JsonComparator jsonComparator = new JsonComparatorImpl();
        System.out.println(jsonComparator.assertEquals(sampleObj,sampleObj1));
        System.out.println(jsonComparator.assertEquals(sampleObj,sampleObj2));
        System.out.println(jsonComparator.assertEquals(sampleObj,sampleObj3));

        String array = "[0, 1, 2]";
        String array1 = "[0, 1, 2]";
        String array2 = "[0, 1, 3]";
        String array3 = "[0, 1, 2, 3]";
        System.out.println(jsonComparator.assertEquals(array,array1));
        System.out.println(jsonComparator.assertEquals(array,array2));
        System.out.println(jsonComparator.assertEquals(array,array3));

        System.out.println(jsonComparator.assertEquals("4", "4"));
        System.out.println(jsonComparator.assertEquals("4", "5"));
        System.out.println(jsonComparator.assertEquals("4", "{45"));

    }

}
