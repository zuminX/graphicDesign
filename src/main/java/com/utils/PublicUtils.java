package com.utils;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Arrays;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.util.stream.Collectors.joining;

/**
 * 公共的工具类
 */
public class PublicUtils {
    /**
     * 浮点数误差
     */
    public static final double DOUBLE_ERROR = 1e-6;
    /**
     * 角度误差
     */
    public static final double ANGLE_ERROR = 1e-4;

    /**
     * 正五边形的角度
     */
    public static final int REGULAR_PENTAGON_ANGLE = 108;

    /**
     * 浮点数格式化对象
     */
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.000000");

    /**
     * 比较两个浮点数的大小关系
     *
     * @param number1 比较数
     * @param number2 被比较数
     * @return 数1大于数2返回1；数1等于数2返回0；否则返回-1
     */
    public static int compareTwoDoubleSize(double number1, double number2) {
        return compareTwoDoubleNumber(number1, number2, DOUBLE_ERROR);
    }

    /**
     * 比较两个角度的大小关系
     *
     * @param angle1 比较角度
     * @param angle2 被比较角度
     * @return 角度1大于角度2返回1；角度1等于角度2返回0；否则返回-1
     */
    public static int compareTwoAngleSize(double angle1, double angle2) {
        return compareTwoDoubleNumber(angle1, angle2, ANGLE_ERROR);
    }

    /**
     * 比较两个浮点数在指定误差下的大小关系
     *
     * @param number1       比较数
     * @param number2       被比较数
     * @param errorStandard 误差
     * @return 数1大于数2返回1；数1等于数2返回0；否则返回-1
     */
    public static int compareTwoDoubleNumber(double number1, double number2, double errorStandard) {
        final double difference = number1 - number2;
        return (difference > DOUBLE_ERROR ? 1 : (abs(difference) < errorStandard ? 0 : -1));
    }

    /**
     * 判断浮点数是否为零
     *
     * @param doubles 浮点数
     * @return 有为零的浮点数返回true；否则返回false
     */
    public static boolean isDoubleZero(double... doubles) {
        return Arrays.stream(doubles).anyMatch(d -> abs(d) < DOUBLE_ERROR);
    }

    /**
     * 尝试将字符串转换为浮点数
     * 若转换不成功则抛出异常
     *
     * @param str 存放浮点数的字符串
     * @return 浮点数
     */
    public static double transformStringToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new RuntimeException(Information.inputNumberIsIllegal);
        }
    }

    /**
     * 获取两个尺寸比率的最小值
     *
     * @param dimension1 比较的尺寸
     * @param dimension2 被比较的尺寸
     * @return 高度比率和宽度比率之间最小者
     */
    public static double getTwoDimensionRatio(@NotNull Dimension dimension1, @NotNull Dimension dimension2) {
        final double heightRatio = dimension1.getHeight() / dimension2.getHeight();
        final double widthRatio = dimension1.getWidth() / dimension2.getWidth();
        return min(heightRatio, widthRatio);
    }

    /**
     * 将参数的点集转换为(x1,y1), (x2,y2)...的字符串形式
     *
     * @param points 点集
     * @return 点集的字符串形式
     */
    public static String pointsToString(Point... points) {
        return Arrays.stream(points).map(point -> "(" + point.getAbscissa() + "," + point.getOrdinate() + ")").collect(joining(", "));
    }
}
