package com.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 直角坐标系的点
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    /**
     * 横坐标
     */
    private double abscissa;
    /**
     * 纵坐标
     */
    private double ordinate;

    /**
     * 对当前点与参数的点进行加和运算
     *
     * @param point 被加的点
     * @return 两点相加的点
     */
    public Point add(Point point) {
        return new Point(abscissa + point.abscissa, ordinate + point.ordinate);
    }

    /**
     * 将当前点的坐标进行乘法运算后的返回该坐标的点
     *
     * @param coefficient 系数
     * @return 经过乘法运算后的点
     */
    public Point multiply(double coefficient) {
        return new Point(abscissa * coefficient, ordinate * coefficient);
    }

    /**
     * 对点进行上下翻转
     *
     * @param height 高度
     * @return 翻转后的点
     */
    public Point flipUpAndDown(double height) {
        return new Point(abscissa, height - ordinate);
    }
}
