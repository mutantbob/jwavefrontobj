package com.purplefrog.jwavefrontobj;

/**
 * Created with IntelliJ IDEA.
 * User: thoth
 * Date: 4/4/13
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Face
{
    public final String materialName;
    public final VSpec[] vertices;

    public Face(int[] vertexIndices)
    {
        this(null, wrap(vertexIndices));
    }

    public static VSpec[] wrap(int[] vertexIndices)
    {
        VSpec[] vertices = new VSpec[vertexIndices.length];
        for (int i = 0; i < vertexIndices.length; i++) {
            vertices[i] = new VSpec(vertexIndices[i]);
        }
        return vertices;
    }

    public Face(String mtlName, VSpec[] vertices)
    {
        materialName = mtlName;
        this.vertices = vertices;
    }

    public String toString()
    {
        StringBuilder rval = new StringBuilder("f ");
        for (VSpec spec : vertices) {
            rval.append(" "+spec);
        }

        rval.append("\n");
        return rval.toString();

    }

    public static class VSpec
    {
        public int vertexIndex;
        private int textureCoordIndex=0;

        public VSpec(int vertexIndex)
        {

            this.vertexIndex = vertexIndex;
        }

        public VSpec(int vIdx, int textureCoordIndex)
        {
            vertexIndex = vIdx;

            this.textureCoordIndex = textureCoordIndex;
        }

        public String toString()
        {
            StringBuilder rval = new StringBuilder();
            rval.append(vertexIndex);
            if (textureCoordIndex>0)
                rval.append("/"+textureCoordIndex);
            return rval.toString();
        }
    }
}
