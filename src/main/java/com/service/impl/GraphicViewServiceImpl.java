package com.service.impl;

import com.domain.Circle;
import com.domain.NowShapeCollectionData;
import com.domain.Shape;
import com.domain.ViewComponent;
import com.service.GraphicViewService;
import com.utils.GraphicDataDialog;
import com.utils.Information;
import com.utils.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.utils.PublicUtils.getTwoDimensionRatio;
import static com.utils.ShapeUtils.getPointsNumberByGraphicName;
import static com.utils.ShapeUtils.sortPoints;
import static java.util.stream.Collectors.toList;

/**
 * 图形页面的业务层
 * 接收控制层的数据
 * 返回数据给控制层
 */
@Service
public class GraphicViewServiceImpl implements GraphicViewService {

    private final ViewComponent viewComponent;

    private final GraphicDataDialog graphicDataDialog;

    private final NowShapeCollectionData shapeCollectionData;

    /**
     * 注入成员变量
     */
    @Autowired
    public GraphicViewServiceImpl(ViewComponent viewComponent, GraphicDataDialog graphicDataDialog, NowShapeCollectionData shapeCollectionData) {
        this.viewComponent = viewComponent;
        this.graphicDataDialog = graphicDataDialog;
        this.shapeCollectionData = shapeCollectionData;
    }

    /**
     * 显示错误信息的提示框
     *
     * @param err 错误信息
     */
    @Override
    public void showErrorInformation(String err) {
        JOptionPane.showMessageDialog(null, err, "错误", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * 设置图形数据对话框的可见性
     *
     * @param b 是否可见
     */
    @Override
    public void setGraphicDataDialogVisible(boolean b) {
        setGraphicDataDialogVisible((String) viewComponent.getAddShapeComboBox().getSelectedItem(), b);
    }

    /**
     * 设置图形数据对话框的可见性
     *
     * @param shapeName 图形名字
     * @param b         是否可见
     */
    @Override
    public void setGraphicDataDialogVisible(String shapeName, boolean b) {
        if (b) {
            //根据名字获取组成该图形的点
            Assert.notNull(shapeName, "shapeName is null");
            final int number = getPointsNumberByGraphicName(shapeName);

            if (number == 0) {
                return;
            }

            //根据点的数量设置对话框组件的数量
            graphicDataDialog.setRadiusComponentVisible(number == 1);
            graphicDataDialog.setPointsComponentVisible(number);
        }
        graphicDataDialog.setVisible(b);
    }

    /**
     * 重绘图形面板
     */
    @Override
    public void repaintGraphicPanel() {
        final JPanel graphicPanel = viewComponent.getGraphicPanel();

        //移除图形面板上的所有组件
        graphicPanel.removeAll();

        //根据图形数据绘制图形
        JPanel graphic = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                final Dimension graphicPanelSize = graphicPanel.getSize();
                //获取图形面板和图形最小尺寸的比率
                final double ratio = getTwoDimensionRatio(graphicPanelSize, shapeCollectionData.getShapeCollectionMinimumSize());

                //绘制每一个图形
                for (Shape shape : shapeCollectionData.getShapes()) {
                    //对圆形进行特殊处理
                    if (shape.getShapeName().equals(Information.circleName)) {
                        final Circle circle = (Circle) shape;
                        drawCircleByCenterOfMindAndRadius(g, graphicPanelSize.getHeight(), ratio, circle.getPoints()[0], circle.getRadius());
                    } else {
                        drawLineByPoints(g, graphicPanelSize.getHeight(), ratio, shape.getPoints());
                    }
                }
            }
        };

        //将绘制好的图形加在图形面板上
        graphicPanel.add(graphic);
        //刷新图形面板
        graphicPanel.updateUI();
        graphicPanel.repaint();
    }

    /**
     * 显示当前图形面板和图形最小尺寸的比率
     */
    @Override
    public void showNowShapeRadio() {
        viewComponent.getRatioLabel()
                .setToolTipText(String.valueOf(
                        getTwoDimensionRatio(viewComponent.getGraphicPanel().getSize(), shapeCollectionData.getShapeCollectionMinimumSize())));
    }

    /**
     * 添加图形下拉选
     */
    @Override
    public void addShapeItemData() {
        final List<Shape> shapes = shapeCollectionData.getShapes();
        viewComponent.getOperateShapeComboBox().addItem(shapes.size() + ":" + shapes.get(shapes.size() - 1).getShapeName());
    }

    /**
     * 显示删除图形的确认对话框
     *
     * @param index 删除图形的下标
     * @return 0为确定, 1为取消，-1为关闭窗口
     */
    @Override
    public int showDeleteShapeConfirmDialog(int index) {
        final Shape willDeleteShape = shapeCollectionData.getShapes().get(index);
        return JOptionPane.showConfirmDialog(viewComponent.getGraphicPanel().getRootPane(), "您确定要删除该图形吗？\n" + willDeleteShape.getString(), "删除提示",
                JOptionPane.YES_NO_OPTION);
    }

    /**
     * 删除指定下标的图形下拉选
     *
     * @param index 下标
     */
    @Override
    public void deleteShapeItem(int index) {
        viewComponent.getOperateShapeComboBox().removeItemAt(index);
    }

    /**
     * 显示图形的详细数据
     *
     * @param index 下标
     */
    @Override
    public void showShapeDetails(int index) {
        final Shape shape = shapeCollectionData.getShapes().get(index);
        JOptionPane.showMessageDialog(viewComponent.getGraphicPanel().getRootPane(), shape.getDetailedString(), "图形数据",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 根据参数绘制圆形
     *
     * @param g            图形的抽象基类
     * @param panelHeight  面板的高度
     * @param ratio        比率
     * @param centerOfMind 圆形
     * @param radius       半径
     */
    private void drawCircleByCenterOfMindAndRadius(Graphics g, double panelHeight, double ratio, Point centerOfMind, double radius) {
        int newRadius = (int) (radius * ratio);
        int x = (int) (ratio * (centerOfMind.getAbscissa() - shapeCollectionData.getMinAbscissa()));
        int y = (int) (panelHeight - (ratio * (centerOfMind.getOrdinate() - shapeCollectionData.getMinOrdinate())));
        g.drawOval(x - newRadius, y - newRadius, newRadius * 2, newRadius * 2);
    }

    /**
     * 根据参数绘制直线
     *
     * @param g           图形的抽象基类
     * @param panelHeight 面板的高度
     * @param ratio       比率
     * @param points      组成该图形的顶点
     */
    private void drawLineByPoints(Graphics g, double panelHeight, double ratio, Point... points) {
        //对点集进行排序，使其的顶点是连续的
        final List<Point> sortPoints = sortPoints(points);
        //偏移点
        final Point offsetPoint = new Point(-shapeCollectionData.getMinAbscissa(), -shapeCollectionData.getMinOrdinate());
        //对点集的点进行偏移、放缩、上下翻转
        final List<Point> pointList = sortPoints.stream()
                .map(point -> point.add(offsetPoint).multiply(ratio).flipUpAndDown(panelHeight))
                .collect(toList());

        //对相邻的两个点进行绘制直线
        for (int i = 0; i < pointList.size(); ++i) {
            final Point point1 = pointList.get(i);
            final Point point2 = pointList.get((i + 1) % pointList.size());

            g.drawLine((int) point1.getAbscissa(), (int) point1.getOrdinate(), (int) point2.getAbscissa(), (int) point2.getOrdinate());
        }
    }

}


