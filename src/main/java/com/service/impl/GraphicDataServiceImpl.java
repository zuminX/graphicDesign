package com.service.impl;

import com.domain.Circle;
import com.domain.NowShapeCollectionData;
import com.domain.Shape;
import com.domain.ViewComponent;
import com.service.GraphicDataService;
import com.utils.GraphicDataDialog;
import com.utils.Information;
import com.utils.Point;
import com.utils.ShapeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import static com.utils.PublicUtils.transformStringToDouble;

/**
 * 图形数据的业务层
 * 接收控制层的数据
 * 返回数据给控制层
 */
@Service
public class GraphicDataServiceImpl implements GraphicDataService {

    private final ViewComponent viewComponent;

    private final NowShapeCollectionData shapeCollectionData;

    private final GraphicDataDialog graphicDataDialog;

    /**
     * 注入成员变量
     */
    @Autowired
    public GraphicDataServiceImpl(ViewComponent viewComponent, NowShapeCollectionData shapeCollectionData, GraphicDataDialog graphicDataDialog) {
        this.viewComponent = viewComponent;
        this.shapeCollectionData = shapeCollectionData;
        this.graphicDataDialog = graphicDataDialog;
    }

    /**
     * 保存图形数据
     *
     * @return 保存成功返回true，失败返回null
     */
    @Override
    public Boolean saveGraphicData() {
        final String graphicName = (String) viewComponent.getAddShapeComboBox().getSelectedItem();
        final Shape shape = getShape(graphicName);

        //将图形对象添加到集合中
        shapeCollectionData.addShape(shape);
        return true;
    }

    /**
     * 获取图形对象
     *
     * @param graphicName 图形名字
     * @return 图形对象
     */
    private Shape getShape(String graphicName) {
        //根据图形名称获取图形数据对话框中的数据
        Assert.notNull(graphicName, "graphicName is null");
        final Point[] points = transformPointComponentDataToPoints(graphicName, graphicDataDialog.getPointComponentList());

        //获取半径，若为空则为0
        final String radiusStr = graphicDataDialog.getRadiusTextField().getText();
        final double radius = StringUtils.isEmpty(radiusStr) ? 0 : Double.parseDouble(radiusStr);

        //根据图形名称、半径、点集获取该图形对象
        Assert.notNull(points, "points is null");

        final Shape shape = ShapeUtils.createShapeDomain(graphicName, radius, points);

        //该对象为空，说明所给数据不合法，则抛出异常
        if (shape == null) {
            throw new RuntimeException(Information.theParameterIsInvalid);
        }

        return shape;
    }

    /**
     * 清空图形数据对话框中的数据
     */
    @Override
    public void cleanGraphicDataDialogData() {
        graphicDataDialog.cleanAllData();
    }

    /**
     * 删除指定下标的图形数据
     *
     * @param index 下标
     */
    @Override
    public void deleteShapeData(int index) {
        shapeCollectionData.removeShape(index);
    }

    /**
     * 根据下标填充对话框的数据
     *
     * @param index 下标
     */
    @Override
    public void fillNowData(int index) {
        final Shape shape = shapeCollectionData.getShapes().get(index);
        double radius = 0;

        //如果是圆形，则获取其半径
        if (shape.getShapeName().equals(Information.circleName)) {
            final Circle circle = (Circle) shape;
            radius = circle.getRadius();
        }

        graphicDataDialog.fillData(radius, shape.getPoints());
    }

    /**
     * 通过下标获取该下标的图形
     *
     * @param index 下标
     * @return 图形
     */
    @Override
    public Shape getShapeByIndex(int index) {
        return shapeCollectionData.getShapes().get(index);
    }

    /**
     * 修改图形数据
     *
     * @return 修改成功返回true，发生异常返回null
     */
    @Override
    public Boolean modifyGraphicData() {
        final int modifyIndex = shapeCollectionData.getModifyIndex();
        final Shape shape = getShape(shapeCollectionData.getShapes().get(modifyIndex).getShapeName());

        //移除旧数据
        shapeCollectionData.removeShape(modifyIndex);
        //添加新数据
        shapeCollectionData.addShape(shape);

        return true;
    }

    /**
     * 获取修改图形的下标
     *
     * @return 下标
     */
    @Override
    public int getModifyIndex() {
        return shapeCollectionData.getModifyIndex();
    }

    /**
     * 设置修改图形在集合中的下标
     *
     * @param index 下标
     */
    @Override
    public void setModifyIndex(int index) {
        shapeCollectionData.setModifyIndex(index);
    }

    /**
     * 获取图形数据对话框中的数据
     * 将其转换为点集
     *
     * @param graphicName        图形名称
     * @param pointComponentList 点的组件的集合
     * @return 点集
     */
    @Nullable
    private Point[] transformPointComponentDataToPoints(@NotNull String graphicName, @NotNull GraphicDataDialog.PointComponent[] pointComponentList) {
        //获取该图形名称的组成点的个数
        final int number = ShapeUtils.getPointsNumberByGraphicName(graphicName);
        if (number == 0) {
            return null;
        }

        //获取文本框的字符串，将其转换为浮点数，创建该数据的点
        final Point[] points = new Point[number];
        for (int i = 0; i < number; i++) {
            final double abscissa = transformStringToDouble(pointComponentList[i].getPointAbscissaTextField().getText());
            final double ordinate = transformStringToDouble(pointComponentList[i].getPointOrdinateTextField().getText());
            points[i] = new Point(abscissa, ordinate);
        }

        return points;
    }
}
