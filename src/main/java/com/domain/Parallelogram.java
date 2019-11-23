package com.domain;

import com.utils.Information;
import com.utils.Point;

import java.util.Arrays;
import java.util.List;

import static com.utils.PublicUtils.compareTwoDoubleSize;
import static com.utils.ShapeUtils.*;
import static java.lang.Math.PI;
import static java.lang.Math.sin;

/**
 * 平行四边形
 */
public class Parallelogram implements Shape {
    /**
     * 组成平行四边形的点
     */
    private Point[] points;
    /**
     * 平行四边形的一对临边边长
     */
    private double[] lengths;
    /**
     * 平行四边形的一对临边的夹角
     */
    private double angle;
    /**
     * 平行四边形的面积
     */
    private Double area;
    /**
     * 平行四边形的周长
     */
    private Double perimeter;

    /**
     * 平行四边形的构造方法
     *
     * @param points 组成平行四边形的点
     */
    public Parallelogram(Point[] points) {
        //检查三角形的合法性
        if (!checkParallelogramLegality(points)) {
            throw new RuntimeException(Information.createParallelogramError);
        }
        this.points = points;
        initLengthsAndAngle();
    }

    /**
     * 初始化边长和角度
     */
    private void initLengthsAndAngle() {
        lengths = new double[2];
        List<Point> pointList = Arrays.asList(points[1], points[2], points[3]);
        //对pointList进行基于与point[0]距离进行排序
        pointList.sort((p1, p2) -> compareTwoDoubleSize(getTwoPointsDistance(points[0], p1), getTwoPointsDistance(points[0], p2)));

        //获取与point[0]相连的两个顶点
        final Point point1 = pointList.get(0);
        final Point point2 = pointList.get(1);

        //获取两条相邻边长
        lengths[0] = getTwoPointsDistance(points[0], point1);
        lengths[1] = getTwoPointsDistance(points[0], point2);

        //计算某两条相邻边夹角的角度
        angle = calculateAngle(points[0], point1, point2);
    }

    /**
     * 用公式S=a*b*sinα（a,b为相邻边,α为其夹角）计算平行四边形的面积
     *
     * @return 平行四边形的面积
     */
    @Override
    public double getArea() {
        if (area == null) {
            area = lengths[0] * lengths[1] * sin(angle / 180 * PI);
        }
        return area;
    }

    /**
     * 利用公式C=2(a+b)（a,b为相邻边）计算平行四边形的周长
     *
     * @return 平行四边形的周长
     */
    @Override
    public double getPerimeter() {
        if (perimeter == null) {
            perimeter = 2 * (lengths[0] + lengths[1]);
        }
        return perimeter;
    }

    /**
     * 获取平行四边形的名字
     *
     * @return 平行四边形的名字
     */
    @Override
    public String getShapeName() {
        return Information.parallelogramName;
    }

    /**
     * 获取组成平行四边形的顶点
     *
     * @return 组成平行四边形的顶点
     */
    @Override
    public Point[] getPoints() {
        return points;
    }

}
