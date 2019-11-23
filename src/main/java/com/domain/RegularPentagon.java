package com.domain;

import com.utils.Information;
import com.utils.Point;

import static com.utils.ShapeUtils.checkRegularPentagonLegality;
import static com.utils.ShapeUtils.getTwoPointsDistance;

/**
 * 正五边形
 */
public class RegularPentagon implements Shape {
    /**
     * 计算正五边形面积的数据：（5/4*tan54°)
     */
    public static final double AREA_FORMULA_DATA = 1.720477401;
    /**
     * 组成正五边形的点
     */
    private Point[] points;
    /**
     * 正五边形的边长
     */
    private double length;
    /**
     * 正五边形的面积
     */
    private Double area;
    /**
     * 正五边形的周长
     */
    private Double perimeter;

    /**
     * 正五边形的构造方法
     *
     * @param points 组成正五边形的顶点
     */
    public RegularPentagon(Point[] points) {
        //检查正五边形的合法性
        if (!checkRegularPentagonLegality(points)) {
            throw new RuntimeException(Information.createRegularPentagonError);
        }
        this.points = points;
        initLength();
    }

    /**
     * 初始化边长
     */
    private void initLength() {
        length = getTwoPointsDistance(points[0], points[1]);
    }

    /**
     * 获取正五边形的面积
     *
     * @return 正五边形的面积
     */
    @Override
    public double getArea() {
        if (area == null) {
            area = AREA_FORMULA_DATA * length * length;
        }
        return area;
    }

    /**
     * 获取正五边形的周长
     *
     * @return 正五边形的周长
     */
    @Override
    public double getPerimeter() {
        if (perimeter == null) {
            perimeter = 5 * length;
        }
        return perimeter;
    }

    /**
     * 获取正五边形的名字
     *
     * @return 正五边形的名字
     */
    @Override
    public String getShapeName() {
        return Information.regularPentagonName;
    }

    /**
     * 组成正五边形的顶点
     *
     * @return 组成正五边形的顶点
     */
    @Override
    public Point[] getPoints() {
        return points;
    }
}
