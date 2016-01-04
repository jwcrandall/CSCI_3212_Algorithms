
public class TwoFunction {

    public static void main (String[] argv)
    {
        Function f = new Function ("F");
        Function g = new Function ("G");
        for (int n=0; n<=100; n+=10) {
            f.add (n, 9*n*n + 1000*n);
            g.add (n, n*n*n);
        }
        Function.show (f,g);
    }

}
