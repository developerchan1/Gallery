package com.android.cen.andrew.gallery;

import java.io.File;

public class ImageFileFilter
{
    private static final String[] okFileExtensions =  new String[] {"jpg", "png", "gif","jpeg"};

    public static boolean check(File file)
    {
        for (String extension : okFileExtensions)
        {
            if (file.getName().toLowerCase().endsWith(extension))
            {
                return true;
            }
        }
        return false;
    }

}
