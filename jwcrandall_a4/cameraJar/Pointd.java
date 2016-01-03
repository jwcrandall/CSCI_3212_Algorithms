import java.text.*;

/**
 * Pointd class objects define points in the x,y plane
 * with specification of the point x and y coordinates.
 *
 * @author William Edison
 * @version 1.02 September 2015
 */
public class Pointd {
    /**
     * Class variable providing for inexact point comparison.
     */
    private static final double EPSILON = .00000001;

    /**
     * Class variables specifying a point instance.
     */
    double x;
    double y;

    /**
     * Constructs a Pointd object
     *
     * @param	x	double value specifying the x coordinate of the point
     * @param	y	double value specifying the y coordinate of the point
     */
    public Pointd(double x, double y)
    {
	this.x = x;
	this.y = y;
    }

    /**
     * Accessor for x-coordinate.
     *
     * @return x	double value specifying the x coordinate of the point
     */
    public final double getx()
    {
	return x;
    }

    /**
     * Accessor for y-coordinate.
     *
     * @return y	double value specifying the y coordinate of the point
     */
    public final double gety()
    {
	return y;
    }

    /**
     * Return string representation of Pointd instance.
     *
     * @return	String value describing the point
     */
    public String toString()
    {
	NumberFormat nf = NumberFormat.getNumberInstance();
	nf.setMaximumFractionDigits(16);
	return ("(" + nf.format(x) + ", " + nf.format(y) + ")");
    }

    /**
     * Return true if the point instance equals point p.
     * Inexact comparison is supported.
     *
     * @param	p	Pointd specification of point to be compared
     *
     * @return	boolean value
     * 				true if points are equal, else false
     */
    public final boolean equals(Pointd p)
    {
	if ( (Math.abs(this.x - p.getx()) < EPSILON) &&
	     (Math.abs(this.y - p.gety()) < EPSILON) )
	    return true;
	else
	    return false;
    }
}
