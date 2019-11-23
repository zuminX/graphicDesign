package com.utils;

import com.domain.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import static com.utils.PublicUtils.*;
import static java.lang.Math.*;

/**
 * 图形的工具类
 */
public class ShapeUtils {

    /**
     * 计算从中心点与两个点形成的两条边的夹角
     *
     * @param centerPoint 中心点
     * @param point1      点1
     * @param point2      点2
     * @return 角度（角度制）
     */
    public static double calculateAngle(@NotNull Point centerPoint, @NotNull Point point1, @NotNull Point point2) {
        double crossProduct = (point1.getAbscissa() - centerPoint.getAbscissa()) * (point2.getAbscissa() - centerPoint.getAbscissa()) +
                              (point1.getOrdinate() - centerPoint.getOrdinate()) * (point2.getOrdinate() - centerPoint.getOrdinate());
        double vectorModule = getTwoPointsDistance(centerPoint, point1) * getTwoPointsDistance(centerPoint, point2);
        return acos(crossProduct / vectorModule) / 2 / PI * 360;
    }

    /**
     * 利用公式sqrt((x1-x2)^2 + (y1-y2)^2)计算两点间的距离
     *
     * @param point1 第一个点
     * @param point2 第二个点
     * @return 两点间距
     */
    public static double getTwoPointsDistance(@NotNull Point point1, @NotNull Point point2) {
        return sqrt(pow(point1.getAbscissa() - point2.getAbscissa(), 2) + pow(point1.getOrdinate() - point2.getOrdinate(), 2));
    }

    /**
     * 检查三角形的合法性
     *
     * @param points 组成三角形的点
     * @return 合法返回true；非法返回false
     */
    public static boolean checkTriangleLegality(@NotNull Point... points) {
        if (points.length != 3) {
            return false;
        }

        double[] lengths = new double[3];
        lengths[0] = getTwoPointsDistance(points[0], points[1]);
        lengths[1] = getTwoPointsDistance(points[1], points[2]);
        lengths[2] = getTwoPointsDistance(points[0], points[2]);

        //如果两边之和小于第三边或有边为零，则为非法
        return !(lengths[0] + lengths[1] <= lengths[2]) && !(lengths[0] + lengths[2] <= lengths[1]) && !(lengths[1] + lengths[2] <= lengths[0]);
    }

    /**
     * 检查平行四边形的合法性
     *
     * @param points 组成平行四边形的点
     * @return 合法返回true；非法返回false
     */
    public static boolean checkParallelogramLegality(@NotNull Point... points) {
        if (points.length != 4) {
            return false;
        }

        //若四个点中有点重合，则为非法
        for (int i = 0; i < 4; ++i) {
            if (isDoubleZero(getTwoPointsDistance(points[i], points[(i + 1) % 4]))) {
                return false;
            }
        }

        List<Point> pointList = Arrays.asList(points[1], points[2], points[3]);
        pointList.sort((p1, p2) -> compareTwoDoubleSize(getTwoPointsDistance(points[0], p1), getTwoPointsDistance(points[0], p2)));
        //求得该点与对角边上对应的点的中点
        Point centerPoint = pointList.get(2).add(points[0]).multiply(0.5);

        //若中点到对角线两点的距离不相等，则为非法
        return compareTwoDoubleSize(getTwoPointsDistance(centerPoint, points[0]), getTwoPointsDistance(centerPoint, pointList.get(2))) == 0 &&
               compareTwoDoubleSize(getTwoPointsDistance(centerPoint, pointList.get(0)), getTwoPointsDistance(centerPoint, pointList.get(1))) == 0;
    }

    /**
     * 检查圆形的合法性
     *
     * @param radius 圆形的半径
     * @return 合法返回true；非法返回false
     */
    public static boolean checkCircleLegality(double radius) {
        return radius > 0;
    }

    /**
     * 检查正五边形的合法性
     *
     * @param points 组成正五边形的点
     * @return 合法返回true；非法返回false
     */
    public static boolean checkRegularPentagonLegality(@NotNull Point... points) {
        if (points.length != 5) {
            return false;
        }

        //存放与其他点距离和该点的优先级队列
        PriorityQueue<Map.Entry<Double, Point>> pointToOtherPointsDistance = new PriorityQueue<>(4,
                (o1, o2) -> compareTwoDoubleSize(o1.getKey(), o2.getKey()));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                //存放该点与其他点的距离
                if (j != i) {
                    pointToOtherPointsDistance.offer(Map.entry(getTwoPointsDistance(points[i], points[j]), points[j]));
                }
            }

            //取出最小的两个条目
            final Map.Entry<Double, Point> entry1 = pointToOtherPointsDistance.poll();
            final Map.Entry<Double, Point> entry2 = pointToOtherPointsDistance.poll();

            Assert.notNull(entry1, "entry1 is null");
            Assert.notNull(entry2, "entry2 is null");
            //如果相邻两边不等或有边为零，则为非法
            if (compareTwoDoubleSize(entry1.getKey(), entry2.getKey()) != 0 || isDoubleZero(entry1.getKey(), entry2.getKey())) {
                return false;
            }

            //正五边形的角都为108°，若不是则为非法
            final double angle = calculateAngle(points[i], entry1.getValue(), entry2.getValue());
            if (compareTwoAngleSize(angle, REGULAR_PENTAGON_ANGLE) != 0) {
                return false;
            }

            //清理优先级队列的数据
            pointToOtherPointsDistance.clear();
        }
        return true;
    }

    /**
     * 根据图形名获取组成该图形的点的个数
     * 若没有该图形名，则返回0
     *
     * @param graphicName 图形名
     * @return 组成该图形的点的个数
     */
    public static int getPointsNumberByGraphicName(String graphicName) {
        int number = 0;
        if (Information.circleName.equals(graphicName)) {
            number = 1;
        } else if (Information.triangleName.equals(graphicName)) {
            number = 3;
        } else if (Information.parallelogramName.equals(graphicName)) {
            number = 4;
        } else if (Information.regularPentagonName.equals(graphicName)) {
            number = 5;
        }
        return number;
    }

    /**
     * 根据图形名和所给的数据尝试创建给图形的对象
     *
     * @param graphicName 图形名
     * @param radius      半径
     * @param points      点集
     * @return 图形对象
     */
    public static Shape createShapeDomain(String graphicName, double radius, @NotNull Point... points) {
        Shape shape = null;
        if (Information.circleName.equals(graphicName) && checkCircleLegality(radius)) {
            shape = new Circle(points[0], radius);
        } else if (Information.triangleName.equals(graphicName) && checkTriangleLegality(points)) {
            shape = new Triangle(points);
        } else if (Information.parallelogramName.equals(graphicName) && checkParallelogramLegality(points)) {
            shape = new Parallelogram(points);
        } else if (Information.regularPentagonName.equals(graphicName) && checkRegularPentagonLegality(points)) {
            shape = new RegularPentagon(points);
        }
        return shape;
    }

    /**
     * 对点集进行排序，使其在组成图形上是连续的
     *
     * @param points 点集
     * @return 排序后的点的集合
     */
    public static List<Point> sortPoints(Point... points) {
        final List<Point> sortPointList = Arrays.asList(points);
        sortPointList.sort((o1, o2) -> -(int) ((o1.getAbscissa() - points[0].getAbscissa()) * (o2.getOrdinate() - points[0].getOrdinate()) -
                                               (o2.getAbscissa() - points[0].getAbscissa()) * (o1.getOrdinate() - points[0].getOrdinate())));
        return sortPointList;
    }

}
