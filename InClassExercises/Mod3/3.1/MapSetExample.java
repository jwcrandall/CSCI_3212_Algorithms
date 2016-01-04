
import java.util.*;

public class MapSetExample {

    public static void main (String[] argv)
    {
        setExample ();
        mapExample ();
    }

    static void setExample ()
    {
        // Example: use strings or numbers.

        // INSERT YOUR CODE HERE.
        Set <String> test = new HashSet <String>();
        test.add("joe");
        test.add("tom");
        test.add("warren");



        System.out.println(test);
    }

    static void mapExample ()
    {
        // Example: use a map from numbers to strings.

        // INSERT YOUR CODE HERE.
        //key, then value
        Map <String, String> test2 = new HashMap <String,String>();
        test2.put("thing1", "joe");
        test2.put("thing2", "tom");
        test2.put("thing3", "warren");

        System.out.println(test2);
    }

}