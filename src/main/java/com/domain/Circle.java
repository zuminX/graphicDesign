package com.domain;

import com.utils.Information;
import com.utils.Point;

import static com.utils.PublicUtils.pointsToString;
import static com.utils.ShapeUtils.checkCircleLegality;
import static java.lang.Math.PI;

/**
 * 圆形
 */
public class Circle implements Shape {
    /**
     * 圆心
     */
    private Point[] points;
    /**
     * 半径
     */
    private double radius;
    /**
     * 圆的面积
     */
    private Double area;
    /**
     * 圆的周长
     */
    private Double perimeter;

    /**
     * 圆形的构造函数
     *
     * @param point  圆心
     * @param radius 半径
     */
    public Circle(Point point, double radius) {
        //检查圆形的合法性
        if (!checkCircleLegality(radius)) {
            throw new RuntimeException(Information.createCircleError);
        }
        this.points = new Point[]{point};
        this.radius = radius;
    }

    /**
     * 获取圆形的半径
     *
     * @return 半径
     */
    public double getRadius() {
        return radius;
    }

    /**
     * 利用公式pi*r^2计算圆的面积
     *
     * @return 圆形面积
     */
    @Override
    public double getArea() {
        if (area == null) {
            area = PI * radius * radius;
        }
        return area;
    }

    /**
     * 利用公式2*pi*r计算圆的周长
     *
     * @return 圆形周长
     */
    @Override
    public double getPerimeter() {
        if (perimeter == null) {
            perimeter = 2 * PI * radius;
        }
        return perimeter;
    }

    /**
     * 获取圆形的名字
     *
     * @return 圆形的名字
     */
    @Override
    public String getShapeName() {
        return Information.circleName;
    }

    /**
     * 获取圆心的点
     *
     * @return 圆心
     */
    @Override
    public Point[] getPoints() {
        return points;
    }

    /**
     * 将圆形的基本数据转化为字符串
     *
     * @return 圆形基本数据的字符串
     */
    @Override
    public String getString() {
        return getShapeName() + "：半径 = " + radius + " ，圆心 = " + pointsToString(points);
    }
}
