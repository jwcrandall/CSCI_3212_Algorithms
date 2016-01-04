
import java.util.*;
import java.io.*;

public class ex1 {

    public static void main (String[] argv) 
    {
        String[] words = getDictionary ("words");
        // Now words[i] has the i-th word in the dictionary.

        // Hint: you can call the hashCode() method in String to
        // get the hashvalue of any string.
        
	int[] first_letter = new int[ 26 ];
	int[] last_letter = new int[ 26 ];
	int[] hash = new int[ 26 ];
	
	for( int x = 0; x < words.length; x++ )
	{
		//first letter hash
		first_letter[ (int)(words[x].charAt(0) % 26) ]++;
		last_letter[ (int)(words[x].charAt( words[x].length()-1 ) % 26) ]++;
		hash[ Math.abs(words[x].hashCode()) % 26 ]++;
	}
	
	//print the results
	System.out.println("First letter:");
	print_buckets( first_letter );
	
	System.out.println("Last letter:");
	print_buckets( last_letter );
	
	System.out.println("String.hashCode():");
	print_buckets( hash );
	
    }

	private static void print_buckets(int[] buckets)
	{
		for( int x = 0; x < buckets.length; x++ )
			System.out.println("\ttable entry " +x+ ": " +buckets[x]);
	}


    /////////////////////////////////////////////////////////////////
    // You don't need to read below this line.

    static boolean isValidWord (String str)
    {
        for (int i=0; i < str.length(); i++)
            if (! (Character.isLetter(str.charAt(i))) )
                return false;
        return true;
    }
    
    
    static String[] getDictionary (String fullPathName)
    {
        try {
            File f = new File (fullPathName);
            LineNumberReader lnr = new LineNumberReader (new FileReader (f));
            String line = lnr.readLine();
            int count = 0;
            while (line != null) {
                String str = line.trim().toLowerCase();
                if (isValidWord (str))
                    count++;
                line = lnr.readLine();
            }
            lnr.close();
            
            System.out.println ("Number of words: " + count);
            // OK, now make the space.
            String[] strArray = new String [count];
            lnr = new LineNumberReader (new FileReader (f));
            count = 0;
            line = lnr.readLine();
            while (line != null) {
                String str = line.trim().toLowerCase();
                if (isValidWord (str)) {
                    strArray [count] = line.trim().toLowerCase();
                    count++;
                }
                line = lnr.readLine();
            }
            lnr.close();
            return strArray;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
