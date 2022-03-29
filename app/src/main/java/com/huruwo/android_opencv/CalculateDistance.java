package com.huruwo.android_opencv;

import android.graphics.Bitmap;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CalculateDistance {
    /**
     读取大小图片准备进行距离计算,
     首先需要获取相应dll的绝对路径，
     随后对图像进行Canny处理、灰度化处理
     计算缺口距离，并且按照比例折算成需要的数值
     外部调用的接口为calcuLateDistance.calculateDistance()
     **/
    public static double calculateDistance(Bitmap bgBitMap,Bitmap slideBitMap) throws Exception{

        Mat origin_bg = bitmapToMat(bgBitMap);
        Mat origin_slide = bitmapToMat(slideBitMap);

        Log.e("xxxx",origin_bg.width()+" "+origin_bg.height()+" ");

        //Canny 进行边缘处理
        Mat canny_bg =   CalculateDistance.Canny(origin_bg);
        Mat canny_slide = CalculateDistance.Canny(origin_slide);

        //灰度值处理
        Mat cvt_bg =   CalculateDistance.cvtColor(canny_bg);
        Mat cvt_slide = CalculateDistance.cvtColor(canny_slide);

        // 这个缩放的比例需要自己安装实际情况换算
        return MatchingDistance(cvt_bg, cvt_slide)/2.4;
    }


    //Canny图像
    public static Mat Canny(Mat origin_mat) {


        // 存储处理后的图片数据
        Mat dst = new Mat();

        //高斯模糊处理
        Imgproc.Canny(origin_mat, dst,100, 200);

        return dst;
    }

    public static Mat cvtColor(Mat origin_mat){
        //灰度化处理
        // 存储处理后的图片数据
        Mat dst = new Mat();

        //灰度模糊处理
        Imgproc.cvtColor(origin_mat, dst, Imgproc.COLOR_GRAY2BGR);

        return dst;
    }

    /**
     *
     * @param source 大图
     * @param target 小图，也就是缺口
     * @return double类型的距离值
     */
    private static double MatchingDistance(Mat source, Mat target){
        // 处理结果
        Mat result = new Mat();

        // 匹配结果
        Imgproc.matchTemplate(source, target, result, Imgproc.TM_CCORR_NORMED);

        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // 获取匹配距离
        Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result);
        org.opencv.core.Point maxLoc = minMaxLocResult.maxLoc;

        //返回距离
        return maxLoc.x;
    }

    public static Bitmap matToBitmap(Mat mat){
        Bitmap resultBitmap = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat, resultBitmap);
        return resultBitmap;
    }

    public static Mat bitmapToMat(Bitmap imageBitmap){
        Mat originalMat = new  Mat(imageBitmap.getWidth(),imageBitmap.getHeight(), CvType.CV_8UC4);
        Utils.bitmapToMat(imageBitmap, originalMat);
        return originalMat;
    }
}
