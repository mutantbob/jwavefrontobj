package com.purplefrog.jwavefrontobj;

import javafx.geometry.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: thoth
 * Date: 4/4/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class WavefrontOBJWriter
{

    protected List<Point3D> vertices = new ArrayList<Point3D>();
    private Map<Point3D, Integer> vertexMap = new HashMap<Point3D, Integer>();

    private List<Face> faces = new ArrayList<Face>();
    protected List<Point2D> textureCoords = new ArrayList<Point2D>();
    private Map<Point2D, Integer> textureCoordMap = new HashMap<Point2D, Integer>();

    public MaterialLib mLib;

    public WavefrontOBJWriter(MaterialLib mLib)
    {
        this.mLib = mLib;
    }

    public void addFace(int... vertexIndices)
    {
        faces.add(new Face(vertexIndices));
    }

    public int indexForVertex(double x, double y, double z)
    {
        Point3D p = new Point3D(x,y,z);

        return indexForVertex(p);
    }

    /**
     * the indices start at 1, not 0 like C folks are used to
     */
    public int indexForVertex(Point3D p)
    {
        Integer rval = vertexMap.get(p);
        if (null==rval) {
            vertices.add(p);
            rval = vertices.size();
            vertexMap.put(p, rval);
        }

        return rval;
    }

    public LazyIndex lazyIndexForVertex(double x, double y, double z)
    {
        Point3D p = new Point3D(x,y,z);

        return new LazyIndex(p);
    }

    public CharSequence dump()
    {
        StringBuilder rval = new StringBuilder();

        for (Point3D v : vertices) {
            rval.append("v "+v.getX()+" "+v.getY()+" "+v.getZ()+"\n");
        }

        rval.append("\n");

        for (Point2D textureCoord : textureCoords) {
            rval.append("vt "+textureCoord.getX()+" "+textureCoord.getY()+"\n");
        }

        rval.append("\n");

        String prevMN = null;
        for (Face face : faces) {

            String mn = face.materialName;
            if (mn==null)
                mn = "unknown";

            if (! mn.equals(prevMN)) {
                rval.append("usemtl "+mn+"\n");
                prevMN = mn;
            }

            rval.append(face.toString());
        }

        return rval;
    }

    public String toString()
    {
        return dump().toString();
    }

    public void addFaceTexture(String mtlName, IndexTexcoord ... vertices)
    {
        Face.VSpec[] vspecs = new Face.VSpec[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            vspecs[i] = new Face.VSpec(vertices[i].vIdx, getTextureCoordIndex(vertices[i].u, vertices[i].v));
        }
        faces.add(new Face(mtlName, vspecs));
    }

    private int getTextureCoordIndex(double u, double v)
    {
        Point2D key = new Point2D(u, v);

        Integer rval = textureCoordMap.get(key);
        if (null==rval) {
            textureCoords.add(key);
            rval = textureCoords.size();
            textureCoordMap.put(key, rval);
        }
        return rval;
    }

    public class LazyIndex
    {
        private Point3D p;
        Integer idx=null;

        public LazyIndex(Point3D p)
        {
            this.p = p;
        }

        public int get()
        {
            if (idx==null)
                idx = indexForVertex(p);

            return idx;
        }
    }
}
