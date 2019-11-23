package com.domain;

import com.utils.Information;
import com.utils.Point;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Double.*;
import static java.lang.Math.abs;
import static java.lang.Math.ceil;

/**
 * 当前图形数据的集合
 */
@Component
@Getter
public class NowShapeCollectionData {
    /**
     * 图形数据
     */
    private List<Shape> shapes;
    /**
     * 图形集合最大的横坐标
     */
    private double maxAbscissa;
    /**
     * 图形集合最小的横坐标
     */
    private double minAbscissa;
    /**
     * 图形集合最大的纵坐标
     */
    private double maxOrdinate;
    /**
     * 图形集合最小的纵坐标
     */
    private double minOrdinate;
    /**
     * 修改图形的下标
     */
    @Setter
    private int modifyIndex;

    /**
     * 图形数据集合的构造方法
     */
    public NowShapeCollectionData() {
        shapes = new ArrayList<>();
        initBoundsNumber();
    }

    /**
     * 初始化边界数值
     */
    private void initBoundsNumber() {
        maxAbscissa = maxOrdinate = MIN_VALUE;
        minAbscissa = minOrdinate = MAX_VALUE;
    }

    /**
     * 增加图形数据
     *
     * @param shape 图形
     */
    public void addShape(@NotNull Shape shape) {
        //修改边界数据
        findBoundsCoordinate(shape.getPoints(), shape);
        shapes.add(shape);
    }

    /**
     * 删除指定下标的数据
     *
     * @param index 下标
     */
    public void removeShape(int index) {
        //下标越界
        if (index >= shapes.size() || index < 0) {
            throw new ArrayIndexOutOfBoundsException(Information.indexOutOfBounds);
        }
        shapes.remove(index);

        //重新创建边界数据
        initBoundsNumber();
        shapes.forEach(shape -> findBoundsCoordinate(shape.getPoints(), shape));
    }

    /**
     * 获取该图形集合最小所需的尺寸
     *
     * @return 尺寸
     */
    public Dimension getShapeCollectionMinimumSize() {
        return new Dimension((int) abs(ceil(maxAbscissa - minAbscissa)), (int) abs(ceil(maxOrdinate - minOrdinate)));
    }

    /**
     * 增添该图形数据后，寻炸该集合最大、小的横、纵坐标
     *
     * @param points 组成该图形的点
     * @param shape  图形
     */
    private void findBoundsCoordinate(Point[] points, Shape shape) {
        double shapeMaxAbscissa;
        double shapeMinAbscissa;
        double shapeMaxOrdinate;
        double shapeMinOrdinate;

        //对圆形进行特殊处理
        if (shape.getShapeName().equals(Information.circleName)) {
            final Circle circle = (Circle) shape;
            final double radius = circle.getRadius();

            shapeMaxAbscissa = points[0].getAbscissa() + radius;
            shapeMinAbscissa = points[0].getAbscissa() - radius;
            shapeMaxOrdinate = points[0].getOrdinate() + radius;
            shapeMinOrdinate = points[0].getOrdinate() - radius;
        } else {
            final var abscissaSummaryStatistics = Arrays.stream(points).mapToDouble(Point::getAbscissa).summaryStatistics();
            final var ordinateSummaryStatistics = Arrays.stream(points).mapToDouble(Point::getOrdinate).summaryStatistics();

            shapeMaxAbscissa = abscissaSummaryStatistics.getMax();
            shapeMinAbscissa = abscissaSummaryStatistics.getMin();
            shapeMaxOrdinate = ordinateSummaryStatistics.getMax();
            shapeMinOrdinate = ordinateSummaryStatistics.getMin();
        }

        maxAbscissa = max(maxAbscissa, shapeMaxAbscissa);
        minAbscissa = min(minAbscissa, shapeMinAbscissa);
        maxOrdinate = max(maxOrdinate, shapeMaxOrdinate);
        minOrdinate = min(minOrdinate, shapeMinOrdinate);
    }
}
