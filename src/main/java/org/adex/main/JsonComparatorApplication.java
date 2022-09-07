package org.adex.main;

import org.adex.services.JsonComparator;
import org.adex.services.impl.JsonComparatorImpl;

public class JsonComparatorApplication {

    public static void main(String[] args) {
        System.out.println("JsonComparatorApplication started");


        String sampleObj = "{\"1\": \"A\", \"2\":\"B\"}";
        String sampleObj1 = "{\"1\": \"A\", \"2\":\"B\"}";
        String sampleObj2 = "{\"1\": \"A\", \"2\":\"C\"}";
        String sampleObj3 = "{\"1\": \"A\", \"2\":\"C\", \"3\":\"C\"}";
        String complexObj = "{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}, \"2\":[0,1,2]}";

        String array = "[0, 1, 2]";
        String array1 = "[0, 1, 2]";
        String array2 = "[0, 1, 3]";
        String array3 = "[0, 1, 2, 3]";

        JsonComparator jsonComparator = new JsonComparatorImpl();
        System.out.println(jsonComparator.assertEquals(sampleObj,sampleObj1));
        System.out.println(jsonComparator.assertEquals(sampleObj,sampleObj2));
        System.out.println(jsonComparator.assertEquals(sampleObj,sampleObj3));
        System.out.println(jsonComparator.assertEquals(sampleObj,array));

        System.out.println(jsonComparator.assertEquals(array,array1));
        System.out.println(jsonComparator.assertEquals(array,array2));
        System.out.println(jsonComparator.assertEquals(array,array3));
        System.out.println(jsonComparator.assertEquals(array,sampleObj));

        System.out.println(jsonComparator.assertEquals("4", "4"));
        System.out.println(jsonComparator.assertEquals("4", "5"));
        System.out.println(jsonComparator.assertEquals("4", "{45"));
        System.out.println(jsonComparator.assertEquals("4", array));
        System.out.println(jsonComparator.assertEquals("4", sampleObj));

    }

}
