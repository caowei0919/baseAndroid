package com.zj.wz.wbyx.baseandroid.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileName: ImageUtils
 * Author: 曹伟
 * Date: 2019/9/29 17:37
 * Description:
 */

public class ImageUtils {
    private ImageUtils() {

    }

    public static boolean compressImage(File srcFile, File outFile, float maxWidth
            , float maxHeight, int quality, Bitmap.CompressFormat compressFormat) {
        return compressImage(srcFile.getAbsolutePath(), outFile, maxWidth
                , maxHeight, quality, compressFormat);
    }

    public static boolean compressImage(String srcFile, File outFile, float maxWidth
            , float maxHeight, int quality, Bitmap.CompressFormat compressFormat) {
        boolean result = false;
        FileOutputStream out = null;
        try {
            //write the compressed bitmap at the destination specified by filename.
            Bitmap bitmap = ImageUtils.getScaledBitmap(srcFile, maxWidth, maxHeight);

            File parent = outFile.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            out = new FileOutputStream(outFile);
            bitmap.compress(compressFormat, quality, out);

            result = true;
        } catch (FileNotFoundException e) {
            PLog.e("compressImage  has  catch");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignored) {
            }
        }
        return result;
    }

    public static Bitmap getScaledBitmap(String imagePath, float maxWidth, float maxHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
        //you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        //setting inSampleSize value allows to load a scaled down version of the original image
        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);
        //inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;
        //this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        Bitmap scaledBitmap = BitmapFactory.decodeFile(imagePath, options);

        float imgRatio = 1;
        //width and height values are set maintaining the aspect ratio of the image
        float maxResult = maxHeight > maxWidth ? maxHeight : maxWidth;
        float maxBitmap = options.outHeight > options.outWidth ?
                options.outHeight : options.outWidth;
        if (maxBitmap > maxResult) {
            imgRatio = maxResult / maxBitmap;
        }

        final Matrix matrix = new Matrix();
        matrix.setScale(imgRatio, imgRatio, 0, 0);
        //check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(imagePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
            if (orientation == 6) {
                matrix.postRotate(90);
            } else if (orientation == 3) {
                matrix.postRotate(180);
            } else if (orientation == 8) {
                matrix.postRotate(270);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0
                    , options.outWidth, options.outHeight, matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledBitmap;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options
            , float reqWidth, float reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / reqHeight);
            final int widthRatio = Math.round((float) width / reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;

        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }
        return inSampleSize;
    }
}
