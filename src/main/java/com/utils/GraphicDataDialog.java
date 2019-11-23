package com.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 输入图形数据的对话框
 */
@Component
public class GraphicDataDialog extends JDialog {
    /**
     * 取消按钮
     */
    @Getter
    private JButton cancelButton;
    /**
     * 确认按钮
     */
    @Getter
    private JButton confirmButton;
    /**
     * 半径名称的标签
     */
    private JLabel radiusNameLabel;
    /**
     * 提示半径的标签
     */
    private JLabel radiusTipLabel;
    /**
     * 输入半径的文本框
     */
    @Getter
    private JTextField radiusTextField;
    /**
     * 所有点的组件
     */
    @Getter
    private PointComponent[] pointComponentList;

    /**
     * 初始化对话框
     */
    @Autowired
    public GraphicDataDialog() {
        super(JOptionPane.getRootFrame());

        //======== this ========
        this.setTitle("GraphicData");
        this.setMinimumSize(new Dimension(400, 300));
        var thisContentPane = this.getContentPane();
        thisContentPane.setLayout(new GridBagLayout());
        ((GridBagLayout) thisContentPane.getLayout()).columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        ((GridBagLayout) thisContentPane.getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout) thisContentPane.getLayout()).columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 1.0E-4};
        ((GridBagLayout) thisContentPane.getLayout()).rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0E-4};

        //---- radiusNameLabel ----
        radiusNameLabel = new JLabel();
        radiusNameLabel.setText("\u534a\u5f84\uff1a");
        thisContentPane.add(radiusNameLabel,
                new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- radiusTipLabel ----
        radiusTipLabel = new JLabel();
        radiusTipLabel.setText("r=");
        thisContentPane.add(radiusTipLabel,
                new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        radiusTextField = new JTextField();
        thisContentPane.add(radiusTextField,
                new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- point1NameLabel ----
        JLabel point1NameLabel = new JLabel();
        point1NameLabel.setText("\u70b91\uff1a");
        thisContentPane.add(point1NameLabel,
                new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- point1AbscissaTipLabel ----
        JLabel point1AbscissaTipLabel = new JLabel();
        point1AbscissaTipLabel.setText("x=");
        thisContentPane.add(point1AbscissaTipLabel,
                new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        JTextField point1AbscissaTextField = new JTextField();
        thisContentPane.add(point1AbscissaTextField,
                new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- point1OrdinateTipLabel ----
        JLabel point1OrdinateTipLabel = new JLabel();
        point1OrdinateTipLabel.setText("y=");
        thisContentPane.add(point1OrdinateTipLabel,
                new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        JTextField point1OrdinateTextField = new JTextField();
        thisContentPane.add(point1OrdinateTextField,
                new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //---- point2NameLabel ----
        JLabel point2NameLabel = new JLabel();
        point2NameLabel.setText("\u70b92\uff1a");
        thisContentPane.add(point2NameLabel,
                new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- point2AbscissaTipLabel ----
        JLabel point2AbscissaTipLabel = new JLabel();
        point2AbscissaTipLabel.setText("x=");
        thisContentPane.add(point2AbscissaTipLabel,
                new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        JTextField point2AbscissaTextField = new JTextField();
        thisContentPane.add(point2AbscissaTextField,
                new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- point2OrdinateTipLabel ----
        JLabel point2OrdinateTipLabel = new JLabel();
        point2OrdinateTipLabel.setText("y=");
        thisContentPane.add(point2OrdinateTipLabel,
                new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        JTextField point2OrdinateTextField = new JTextField();
        thisContentPane.add(point2OrdinateTextField,
                new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //---- point3NameLabel ----
        JLabel point3NameLabel = new JLabel();
        point3NameLabel.setText("\u70b93\uff1a");
        thisContentPane.add(point3NameLabel,
                new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- point3AbscissaTipLabel ----
        JLabel point3AbscissaTipLabel = new JLabel();
        point3AbscissaTipLabel.setText("x=");
        thisContentPane.add(point3AbscissaTipLabel,
                new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        JTextField point3AbscissaTextField = new JTextField();
        thisContentPane.add(point3AbscissaTextField,
                new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- point3OrdinateTipLabel ----
        JLabel point3OrdinateTipLabel = new JLabel();
        point3OrdinateTipLabel.setText("y=");
        thisContentPane.add(point3OrdinateTipLabel,
                new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        JTextField point3OrdinateTextField = new JTextField();
        thisContentPane.add(point3OrdinateTextField,
                new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //---- point4NameLabel ----
        JLabel point4NameLabel = new JLabel();
        point4NameLabel.setText("\u70b94\uff1a");
        thisContentPane.add(point4NameLabel,
                new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- point4AbscissaTipLabel ----
        JLabel point4AbscissaTipLabel = new JLabel();
        point4AbscissaTipLabel.setText("x=");
        thisContentPane.add(point4AbscissaTipLabel,
                new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        JTextField point4AbscissaTextField = new JTextField();
        thisContentPane.add(point4AbscissaTextField,
                new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- point4OrdinateTipLabel ----
        JLabel point4OrdinateTipLabel = new JLabel();
        point4OrdinateTipLabel.setText("y=");
        thisContentPane.add(point4OrdinateTipLabel,
                new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        JTextField point4OrdinateTextField = new JTextField();
        thisContentPane.add(point4OrdinateTextField,
                new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //---- point5NameLabel ----
        JLabel point5NameLabel = new JLabel();
        point5NameLabel.setText("\u70b95\uff1a");
        thisContentPane.add(point5NameLabel,
                new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- point5AbscissaTipLabel ----
        JLabel point5AbscissaTipLabel = new JLabel();
        point5AbscissaTipLabel.setText("x=");
        thisContentPane.add(point5AbscissaTipLabel,
                new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        JTextField point5AbscissaTextField = new JTextField();
        thisContentPane.add(point5AbscissaTextField,
                new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- point5OrdinateTipLabel ----
        JLabel point5OrdinateTipLabel = new JLabel();
        point5OrdinateTipLabel.setText("y=");
        thisContentPane.add(point5OrdinateTipLabel,
                new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
        JTextField point5OrdinateTextField = new JTextField();
        thisContentPane.add(point5OrdinateTextField,
                new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //======== panel4 ========
        JPanel panel4 = new JPanel();
        {
            panel4.setBorder(new EmptyBorder(5, 5, 5, 5));
            panel4.setLayout(new GridBagLayout());
            ((GridBagLayout) panel4.getLayout()).columnWidths = new int[]{0, 0, 0};
            ((GridBagLayout) panel4.getLayout()).rowHeights = new int[]{0, 0};
            ((GridBagLayout) panel4.getLayout()).columnWeights = new double[]{1.0, 1.0, 1.0E-4};
            ((GridBagLayout) panel4.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};

            //---- addButton ----
            confirmButton = new JButton();
            confirmButton.setText("确认");
            panel4.add(confirmButton,
                    new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

            //---- cancelButton ----
            cancelButton = new JButton();
            cancelButton.setText("取消");
            panel4.add(cancelButton,
                    new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        thisContentPane.add(panel4,
                new GridBagConstraints(0, 6, 5, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        this.pack();
        this.setLocationRelativeTo(this.getOwner());

        //将组件放入集合中
        pointComponentList = new PointComponent[5];
        pointComponentList[0] = new PointComponent(point1NameLabel, point1AbscissaTipLabel, point1AbscissaTextField, point1OrdinateTipLabel,
                point1OrdinateTextField);
        pointComponentList[1] = new PointComponent(point2NameLabel, point2AbscissaTipLabel, point2AbscissaTextField, point2OrdinateTipLabel,
                point2OrdinateTextField);
        pointComponentList[2] = new PointComponent(point3NameLabel, point3AbscissaTipLabel, point3AbscissaTextField, point3OrdinateTipLabel,
                point3OrdinateTextField);
        pointComponentList[3] = new PointComponent(point4NameLabel, point4AbscissaTipLabel, point4AbscissaTextField, point4OrdinateTipLabel,
                point4OrdinateTextField);
        pointComponentList[4] = new PointComponent(point5NameLabel, point5AbscissaTipLabel, point5AbscissaTextField, point5OrdinateTipLabel,
                point5OrdinateTextField);
    }

    /**
     * 设置点的组件可见的数量
     *
     * @param pointsNumber 点的数量
     */
    public void setPointsComponentVisible(int pointsNumber) {
        boolean isVisible = true;
        for (int i = 0; i < pointComponentList.length; i++) {
            //其他点的组件设置为不可见
            if (i == pointsNumber) {
                isVisible = false;
            }
            final PointComponent p = pointComponentList[i];
            p.getPointNameLabel().setVisible(isVisible);
            p.getPointAbscissaTipLabel().setVisible(isVisible);
            p.getPointAbscissaTextField().setVisible(isVisible);
            p.getPointOrdinateTipLabel().setVisible(isVisible);
            p.getPointOrdinateTextField().setVisible(isVisible);
        }
    }

    /**
     * 设置半径的组件的可见性
     *
     * @param isVisible 可见性
     */
    public void setRadiusComponentVisible(boolean isVisible) {
        radiusNameLabel.setVisible(isVisible);
        radiusTipLabel.setVisible(isVisible);
        radiusTextField.setVisible(isVisible);
    }

    /**
     * 将数据填充到输入框中
     *
     * @param radius 半径
     * @param points 组成该图形的点
     */
    public void fillData(double radius, @NotNull Point... points) {
        radiusTextField.setText(radius + "");
        for (int i = 0; i < points.length; i++) {
            pointComponentList[i].pointAbscissaTextField.setText(points[i].getAbscissa() + "");
            pointComponentList[i].pointOrdinateTextField.setText(points[i].getOrdinate() + "");
        }
    }

    /**
     * 清空所有输入数据
     */
    public void cleanAllData() {
        for (PointComponent pointComponent : pointComponentList) {
            pointComponent.getPointAbscissaTextField().setText("");
            pointComponent.getPointOrdinateTextField().setText("");
        }
        radiusTextField.setText("");
    }

    /**
     * 点的组件
     */
    @AllArgsConstructor
    @Data
    public static class PointComponent {
        /**
         * 点的名称的标签
         */
        private JLabel pointNameLabel;
        /**
         * 提示点的横坐标的标签
         */
        private JLabel pointAbscissaTipLabel;
        /**
         * 输入点的横坐标的文本框
         */
        private JTextField pointAbscissaTextField;
        /**
         * 提示点的纵坐标的标签
         */
        private JLabel pointOrdinateTipLabel;
        /**
         * 输入点的纵坐标的文本框
         */
        private JTextField pointOrdinateTextField;
    }
}
