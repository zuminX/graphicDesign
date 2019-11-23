package com.domain;

import com.utils.Point;

import static com.utils.PublicUtils.DECIMAL_FORMAT;
import static com.utils.PublicUtils.pointsToString;

/**
 * 图形的抽象类
 */
public interface Shape {
    /**
     * 获取面积
     *
     * @return 面积大小
     */
    double getArea();

    /**
     * 获取周长
     *
     * @return 周长
     */
    double getPerimeter();

    /**
     * 获取图形的名字
     *
     * @return 名字
     */
    String getShapeName();

    /**
     * 获取组成该点的集合
     *
     * @return 所有点
     */
    Point[] getPoints();

    /**
     * 将图形的基本数据转化为字符串
     *
     * @return 图形基本数据字符串
     */
    default String getString() {
        return getShapeName() + "：组成的点 = " + pointsToString(getPoints());
    }

    /**
     * 将图形的详细数据转化为字符串
     *
     * @return 图形详细数据字符串
     */
    default String getDetailedString() {
        return getString() + "\n" + "周长 = " + DECIMAL_FORMAT.format(getPerimeter()) + " ，面积 = " + DECIMAL_FORMAT.format(getArea()) + " 。";
    }
}
