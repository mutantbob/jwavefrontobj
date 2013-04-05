package com.purplefrog.jwavefrontobj;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: thoth
 * Date: 4/4/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class MaterialLib
{
    private final Set<String> materialNames;

    public MaterialLib(File mtlFile)
        throws IOException
    {
        this.materialNames = grepMaterialNames(mtlFile);
    }

    public static Set<String> grepMaterialNames(File mtlFile)
        throws IOException
    {
        Set<String> materialNames = new TreeSet<String>();
        FileReader r = new FileReader(mtlFile);
        BufferedReader br = new BufferedReader(r);

        while (true) {
            String line = br.readLine();
            if (null==line)
                break;

            line = line.trim();
            if (line.startsWith("newmtl ")) {
                String mn = line.substring(7).trim();
                materialNames.add(mn);
            }
        }
        return materialNames;
    }

    public boolean hasMaterial(String rval)
    {
        return materialNames.contains(rval);
    }
}
