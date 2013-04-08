package com.purplefrog.jwavefrontobj;

/**
 * What a pain in my ass.  Java doesn't have one of these until version 1.7
 *
 * Created with IntelliJ IDEA.
 * User: thoth
 * Date: 4/8/13
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Point3D
{
    public double x;
    public double y;
    public double z;

    public Point3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    @Override
    public int hashCode()
    {
        return new Double(x*771+y*31+z).hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Point3D) {
            Point3D arg = (Point3D) obj;
            return arg.x==x
                && arg.y==y
                && arg.z==z;
        } else {
            return false;
        }
    }
}
