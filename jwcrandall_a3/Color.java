import java.util.*;
import java.lang.*;

//ANSI excape codes
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";

public class Color{
	public void red(String s){
		System.out.println(ANSI_RED + "s" + ANSI_RESET);
	}
}

	// public Color color;
	// public void setColor(Color newcolor){

	// }