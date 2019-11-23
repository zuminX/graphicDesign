package com.domain;

import com.utils.Information;
import com.utils.Point;

import static com.utils.ShapeUtils.checkTriangleLegality;
import static com.utils.ShapeUtils.getTwoPointsDistance;

/**
 * 三角形
 */
public class Triangle implements Shape {
    /**
     * 组成三角形的点
     */
    private Point[] points;
    /**
     * 三角形的边长
     */
    private double[] lengths;
    /**
     * 三角形的面积
     */
    private Double area;
    /**
     * 三角形的周长
     */
    private Double perimeter;

    /**
     * 三角形的构造方法
     *
     * @param points 组成三角形的顶点
     */
    public Triangle(Point[] points) {
        //检查三角形的合法性
        if (!checkTriangleLegality(points)) {
            throw new RuntimeException("错误！无法构造该三角形。");
        }
        this.points = points;
        initLengths();
    }

    /**
     * 初始化三角形的三条边
     */
    private void initLengths() {
        lengths = new double[3];
        lengths[0] = getTwoPointsDistance(points[0], points[1]);
        lengths[1] = getTwoPointsDistance(points[1], points[2]);
        lengths[2] = getTwoPointsDistance(points[0], points[2]);
    }

    /**
     * 利用海伦公式计算面积
     *
     * @return 三角形的面积
     */
    @Override
    public double getArea() {
        if (area == null) {
            double halfPerimeter = getPerimeter() / 2;
            area = Math.sqrt(halfPerimeter * (halfPerimeter - lengths[0]) * (halfPerimeter - lengths[1]) * (halfPerimeter - lengths[2]));
        }
        return area;
    }

    /**
     * 三角形周长为三条边长之和
     *
     * @return 三角形的周长
     */
    @Override
    public double getPerimeter() {
        if (perimeter == null) {
            perimeter = lengths[0] + lengths[1] + lengths[2];
        }
        return perimeter;
    }

    /**
     * 获取三角形的名字
     *
     * @return 三角形的名字
     */
    @Override
    public String getShapeName() {
        return Information.triangleName;
    }

    /**
     * 获取组成三角形的顶点
     *
     * @return 组成三角形的顶点
     */
    @Override
    public Point[] getPoints() {
        return points;
    }
}
