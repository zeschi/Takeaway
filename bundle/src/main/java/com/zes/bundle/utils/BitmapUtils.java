package com.zes.bundle.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;
import android.view.View;
import com.zes.bundle.app.MyApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Bitmap工具类
 *
 * @author zes
 */
@SuppressLint("NewApi")
public class BitmapUtils {

    private BitmapUtils() {
    }

    public static Drawable createDrawableFromRes(Context context, int resId) {
        return convertBitmap2Drawable(createBitmapFromRes(context, resId));
    }

    /**
     * 从资源文件中读取
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap createBitmapFromRes(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.ARGB_8888;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
//        InputStream is = context.getResources().openRawResource(resId);
//        return getBitmapByStream(is);
    }

    /**
     * 将Bitmap转化成字节数组
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        return bitmap2Bytes(bitmap, 80);
    }

    /**
     * 将Bitmap转化成字节数组
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int quality) {
        if (bitmap == null)
            return null;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, quality, output);
        return output.toByteArray();
    }

    public static byte[] bitmap2Bytes(final Bitmap bmp, int quality, final boolean needRecycle) {
        if (bmp == null)
            return null;

        float imgW = bmp.getWidth();
        float imgH = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap((int) (150 * imgW / imgH), 150,
                Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bmp, new Rect(0, 0, bmp.getWidth(), bmp.getHeight()),
                new Rect(0, 0, (int) (150 * imgW / imgH), 150), null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, quality, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] bitmap2Bytes(final Bitmap bmp, final boolean needRecycle) {
        return bitmap2Bytes(bmp, 80, needRecycle);
    }

    /**
     * 建立圆角图片
     *
     * @param bitmap  需要处理的Bitmap对象
     * @param cornerx 圆角半径大小
     * @return
     */
    public static Bitmap createCornerBitmap(Bitmap bitmap, float cornerx) {
        Bitmap newbitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        RectF rect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawRoundRect(rect, cornerx, cornerx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// 取绘制交集部分上层
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return newbitmap;
    }

    public static Bitmap maskBitmap(Bitmap bitmap, Bitmap mask, int width,
                                    int height) {
        if (null == bitmap || mask == null) {
            return null;
        }
        // 定义期望大小的bitmap
        Xfermode mode = new PorterDuffXfermode(Mode.SRC_IN);
        Bitmap dstBmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        // 定义一个画布
        Canvas canvas = new Canvas(dstBmp);
        // 创建一个取消锯齿画笔
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 定义需要绘制的某图片上的那一部分矩形空间
        Rect src = new Rect(0, 0, mask.getWidth(), mask.getHeight());
        // 定义需要将上面的矩形绘制成新的矩形大小
        Rect dst = new Rect(0, 0, width, height);
        // 将蒙版图片绘制成imageview本身的大小,这样从大小才会和UE标注的一样大
        canvas.drawBitmap(mask, src, dst, paint);
        // 设置两张图片的相交模式
        // 至于这个函数介绍参考网址:http://blog.csdn.net/wm111/article/details/7299294
        paint.setXfermode(mode);
        // 将src修改为需要添加mask的bitmap大小,因为是要将此bitmap整个添加上蒙版
        src.right = bitmap.getWidth();
        src.bottom = bitmap.getHeight();
        // 在已经绘制的mask上叠加bitmap
        canvas.drawBitmap(bitmap, src, dst, paint);
        return dstBmp;
    }

    /**
     * 压缩图片
     *
     * @param bitmap 原图片
     * @param w      生成新图片的宽度
     * @param h      生成新图片的高度
     * @return 新图片
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidht, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
    }

    /**
     * 压缩图片
     *
     * @param path
     * @return
     */
    public static Bitmap createBitmap(String path) {
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            // 这里是整个方法的关键，inJustDecodeBounds设为true时将不为图片分配内存。
            BitmapFactory.decodeFile(path, opts);
            int srcWidth = opts.outWidth;// 获取图片的原始宽度
            int srcHeight = opts.outHeight;// 获取图片原始高度
            // 缩放的比例
            float ratio = srcWidth / srcHeight;
            float width = 0, height = 0;
            if (srcWidth > srcHeight) {
                width = 480;
                height = srcHeight * 480 / srcWidth;
            } else {
                height = 480;
                width = srcWidth * 480 / srcHeight;
            }

            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            // 缩放的比例，缩放是很难按准备的比例进行缩放的，目前我只发现只能通过inSampleSize来进行缩放，其值表明缩放的倍数，SDK中建议其值是2的指数值
            if (srcWidth <= 480) {
                newOpts.inSampleSize = 1;
            }
            if (srcWidth > 480 && srcWidth <= 960) {
                newOpts.inSampleSize = 1;
            }
            if (srcWidth > 960 && srcWidth <= 1920) {
                newOpts.inSampleSize = 2;
            }
            if (srcWidth > 1920) {
                newOpts.inSampleSize = 4;
            }
            // inJustDecodeBounds设为false表示把图片读进内存中
            newOpts.inJustDecodeBounds = false;
            // 设置大小，这个一般是不准确的，是以inSampleSize的为准，但是如果不设置却不能缩放
            newOpts.outHeight = (int) height;
            newOpts.outWidth = (int) width;
            return BitmapFactory.decodeFile(path, newOpts);

            // 获取缩放后图片
            /*
             * Bitmap srcBitmap= BitmapFactory.decodeFile(path); int
			 * srcWidth=srcBitmap.getWidth(); QLLog.e("my_tag",
			 * "width是：---->"+srcWidth); int srcHeight=srcBitmap.getHeight();
			 * QLLog.e("my_tag", "height是：---->"+srcHeight); float
			 * ratio=srcWidth/srcHeight; float width=0,height=0;
			 * if(srcWidth>srcHeight){ width=480; height=srcHeight*480/srcWidth;
			 * }else{ height=480; width=srcWidth*480/srcHeight; }
			 * QLLog.e("my_tag", "width是：---->"+width+"height====>"+height);
			 * //Bitmap tarBitmap=Bitmap.createScaledBitmap(srcBitmap,
			 * (int)width,(int) height, false); QLLog.e("my_tag",
			 * tarBitmap==null?"是空的":"非空啦"); return tarBitmap;
			 */
        } catch (Exception e) {
            return null;
        }
    }

    public static Bitmap getBitmapByStream(InputStream stream) {
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            // 这里是整个方法的关键，inJustDecodeBounds设为true时将不为图片分配内存。
            BitmapFactory.decodeStream(stream, null, opts);
            int srcWidth = opts.outWidth;// 获取图片的原始宽度
            int srcHeight = opts.outHeight;// 获取图片原始高度
            // 缩放的比例
//            float ratio = srcWidth / srcHeight;
            float width, height;
            if (srcWidth > srcHeight) {
                width = 480;
                height = srcHeight * 480 / srcWidth;
            } else {
                height = 480;
                width = srcWidth * 480 / srcHeight;
            }

            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            // 缩放的比例，缩放是很难按准备的比例进行缩放的，目前我只发现只能通过inSampleSize来进行缩放，其值表明缩放的倍数，SDK中建议其值是2的指数值
            if (srcWidth <= 480) {
                newOpts.inSampleSize = 1;
            }
            if (srcWidth > 480 && srcWidth <= 960) {
                newOpts.inSampleSize = 2;
            }
            if (srcWidth > 960 && srcWidth <= 1920) {
                newOpts.inSampleSize = 4;
            }
            if (srcWidth > 1920) {
                newOpts.inSampleSize = 8;
            }
            // inJustDecodeBounds设为false表示把图片读进内存中
            newOpts.inJustDecodeBounds = false;
            // 设置大小，这个一般是不准确的，是以inSampleSize的为准，但是如果不设置却不能缩放
            newOpts.outHeight = (int) height;
            newOpts.outWidth = (int) width;
            return BitmapFactory.decodeStream(stream, null, newOpts);

            // 获取缩放后图片
            /*
             * Bitmap srcBitmap= BitmapFactory.decodeFile(path); int
			 * srcWidth=srcBitmap.getWidth(); QLLog.e("my_tag",
			 * "width是：---->"+srcWidth); int srcHeight=srcBitmap.getHeight();
			 * QLLog.e("my_tag", "height是：---->"+srcHeight); float
			 * ratio=srcWidth/srcHeight; float width=0,height=0;
			 * if(srcWidth>srcHeight){ width=480; height=srcHeight*480/srcWidth;
			 * }else{ height=480; width=srcWidth*480/srcHeight; }
			 * QLLog.e("my_tag", "width是：---->"+width+"height====>"+height);
			 * //Bitmap tarBitmap=Bitmap.createScaledBitmap(srcBitmap,
			 * (int)width,(int) height, false); QLLog.e("my_tag",
			 * tarBitmap==null?"是空的":"非空啦"); return tarBitmap;
			 */
        } catch (Exception e) {
            return BitmapFactory.decodeStream(stream);
        }
    }

    /**
     * 将定义的view装换成 bitmap格式
     *
     * @param view
     * @return
     */
    public static Bitmap convertView2Bitmap(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
        /*
         * view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
		 * MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		 * view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		 * view.buildDrawingCache(); Bitmap bitmap = view.getDrawingCache();
		 * //return bitmap;
		 */
        // return BitmapUtils.getTransparentBitmap(bitmap,80);
    }

    /**
     * 将图片透明化 mumber 0-100
     *
     * @param sourceImg
     * @param number
     * @return
     */
    public static Bitmap getTransparentBitmap(Bitmap sourceImg, int number) {
        if (sourceImg == null)
            return null;

        int[] argb = new int[sourceImg.getWidth() * sourceImg.getHeight()];
        sourceImg.getPixels(argb, 0, sourceImg.getWidth(), 0, 0, sourceImg
                .getWidth(), sourceImg.getHeight());// 获得图片的ARGB值
        number = number * 255 / 100;
        for (int i = 0; i < argb.length; i++) {
            argb[i] = (number << 24) | (argb[i] & 0x00FFFFFF);
        }
        sourceImg = Bitmap.createBitmap(argb, sourceImg.getWidth(), sourceImg
                .getHeight(), Config.ARGB_8888);
        return sourceImg;
    }

    /**
     * t透明图片描边
     *
     * @param bitmap
     * @return
     */
    public static Bitmap addCenterImage(Bitmap bitmap, int width, int height,
                                        int borderColor) {

        Bitmap srcBmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        // int offsetX = (srcBmp.getWidth() - bitmap.getWidth()) / 2;
        // int offsetY = (srcBmp.getHeight() - bitmap.getHeight()) / 2;
        bitmap = zoomBitmap(bitmap, width, height);
        int offsetX = 0;
        int offsetY = 0;

        Paint paint1 = new Paint();
        paint1.setAntiAlias(true);
        int bmpW = bitmap.getWidth();
        int bmpH = bitmap.getHeight();

        Canvas ca = new Canvas(bitmap);
        Path path = new Path();

        for (int i = 0; i < bmpW; i++)
            for (int j = 0; j < bmpH; j++)
                // 这点不透明而且左右上下四点至少有一点是透明的，那这点就是边缘
                if (bitmap.getPixel(i, j) != Color.TRANSPARENT
                        && (i > 0
                        && bitmap.getPixel(i - 1, j) == Color.TRANSPARENT
                        || i < bmpW - 1
                        && bitmap.getPixel(i + 1, j) == Color.TRANSPARENT
                        || j > 0
                        && bitmap.getPixel(i, j - 1) == Color.TRANSPARENT
                        || j < bmpH - 1
                        && bitmap.getPixel(i, j + 1) == Color.TRANSPARENT || ((i == 0) && bitmap
                        .getPixel(i, j) != Color.TRANSPARENT))
                        || ((j == 0) && bitmap.getPixel(i, j) != Color.TRANSPARENT)
                        || ((i == bmpW - 1) && bitmap.getPixel(i, j) != Color.TRANSPARENT)
                        || ((j == bmpH - 1) && bitmap.getPixel(i, j) != Color.TRANSPARENT))
                    path.addRect(i - 1, j - 1, i, j, Path.Direction.CCW); // 搜集边缘
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(0.8f);

        ca.drawPath(path, paint); // 画出边缘
        Canvas canvas = new Canvas(srcBmp);

        canvas.drawBitmap(bitmap, offsetX, offsetY, paint1);

        return srcBmp;
    }

    /**
     * 旋转图片，使图片保持正确的方向。
     *
     * @param bitmap  原始图片
     * @param degrees 原始图片的角度
     * @return Bitmap 旋转后的图片
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        if (degrees == 0 || null == bitmap) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return bmp;
    }

    /**
     * 将bitmap保存到本地
     *
     * @param f
     * @param mBitmap
     */
    public static void saveBitmapToLocal(File f, Bitmap mBitmap,
                                         boolean isCompress) {
        try {
            f.createNewFile();
        } catch (IOException e) {
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (mBitmap == null || fOut == null)
            return;

        int quality = 100;
        if (isCompress) {
            quality = 80;
        }

        mBitmap.compress(CompressFormat.PNG, quality, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 高斯模糊
     *
     * @param bitmap
     * @return
     */
    @SuppressLint("NewApi")
    public static Bitmap blurBitmap(Bitmap bitmap) {

        // Let's create an empty bitmap with the same size of the bitmap we want
        // to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);

        // Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(MyApplication
                .getAppContext());

        // Create an Intrinsic Blur Script using the Renderscript U8_4(rs)
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs,
                Element.U8_4(rs));

        // Create the Allocations (in/out) with the Renderscript and the in/out
        // bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        // Set the radius of the blur
        blurScript.setRadius(25.f);

        // Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        // Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        // recycle the original bitmap
        // bitmap.recycle();

        // After finishing everything, we destroy the Renderscript.
        rs.destroy();
        return outBitmap;
    }

    public static Bitmap fastBlurBitmap(Bitmap sentBitmap, int radius,
                                        boolean canReuseInBitmap) {

        // Stack Blur v1.0 from
        // http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html
        //
        // Java Author: Mario Klingemann <mario at quasimondo.com>
        // http://incubator.quasimondo.com
        // created Feburary 29, 2004
        // Android port : Yahel Bouaziz <yahel at kayenko.com>
        // http://www.kayenko.com
        // ported april 5th, 2012

        // This is a compromise between Gaussian Blur and Box blur
        // It creates much better looking blurs than Box Blur, but is
        // 7x faster than my Gaussian Blur implementation.
        //
        // I called it Stack Blur because this describes best how this
        // filter works internally: it creates a kind of moving stack
        // of colors whilst scanning through the image. Thereby it
        // just has to add one new block of color to the right side
        // of the stack and remove the leftmost color. The remaining
        // colors on the topmost layer of the stack are either added on
        // or reduced by one, depending on if they are on the right or
        // on the left side of the stack.
        //
        // If you are using this algorithm in your code please add
        // the following line:
        //
        // Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>

        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
                        | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

    /**
     * bitmap转换成drawable
     *
     * @param bitmap
     * @return
     */

    public static Drawable convertBitmap2Drawable(Bitmap bitmap) {
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        // 因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。
        return bd;
    }

    /**
     * 转换成圆形bitmap
     *
     * @param bitmap
     * @return
     */
    public static Bitmap convertCircledPhoto(Bitmap bitmap) {

        Paint p = new Paint();
        p.setAntiAlias(true); // 去锯齿
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        Canvas canvas = new Canvas(bitmap); // bitmap就是我们原来的图,比如头像
        p.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); // 因为我们先画了图所以DST_IN
        int radius = bitmap.getWidth(); // 假设图片是正方形的
        canvas.drawCircle(radius, radius, radius, p); // r=radius, 圆心(r,r)
        return bitmap;
    }

    /**
     * 通过Base32将Bitmap转换成Base64字符串
     *
     * @param bit
     * @return
     */
    public static String bitmap2StrByBase64(Bitmap bit, int compassParams) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(CompressFormat.JPEG, compassParams, bos);// 参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * 通过Base32将bytes转换成Base64字符串
     *
     * @param bytes
     * @return
     */
    public static String byte2StrByBase64(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static Bitmap byte2Bitmap(byte[] bytes) {
        return byte2Bitmap(bytes, 0);
    }

    public static Bitmap byte2Bitmap(byte[] bytes, int offset) {
        return BitmapFactory.decodeByteArray(bytes, offset, bytes.length);
    }
}
