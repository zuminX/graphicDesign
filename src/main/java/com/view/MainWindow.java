/*
 * Created by JFormDesigner on Mon Oct 28 21:31:56 CST 2019
 */

package com.view;

import com.controller.GraphicController;
import com.domain.ViewComponent;
import com.utils.BaseHolder;
import com.utils.GraphicDataDialog;
import com.utils.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * 视图层，进行程序的显示和与用户交互的界面
 * 将数据传递给controller层实现指定方法
 * 获取controller层返回的数据并进行操作
 *
 * @author zumin
 */
@Component("viewWindow")
public class MainWindow extends JFrame {

    private final GraphicController graphicController;

    private final GraphicDataDialog graphicDataDialog;

    /**
     * 注入成员变量
     */
    @Autowired
    public MainWindow(GraphicController graphicController, GraphicDataDialog graphicDataDialog) {
        this.graphicDataDialog = graphicDataDialog;
        this.graphicController = graphicController;

        //初始化组件
        initComponents();

        //添加对话框按钮的监听器
        addGraphicDataDialogActionListener();

        //初始化图形复合框
        initShapeComboBox();
    }

    /**
     * 为添加按钮和取消按钮增加监听器
     */
    private void addGraphicDataDialogActionListener() {
        graphicDataDialog.getConfirmButton().addActionListener(e -> graphicController.saveGraphicData());
        graphicDataDialog.getCancelButton().addActionListener(e -> graphicController.closeGraphicDataDialog());
    }

    /**
     * 初始化图形复合框
     */
    private void initShapeComboBox() {
        ViewComponent viewComponent = BaseHolder.getBean("viewComponent", ViewComponent.class);
        final var addShapeComboBox = viewComponent.getAddShapeComboBox();
        final var operateShapeComboBox = viewComponent.getOperateShapeComboBox();

        //添加初始的项
        addShapeComboBox.addItem("---请选择---");
        addShapeComboBox.addItem(Information.circleName);
        addShapeComboBox.addItem(Information.triangleName);
        addShapeComboBox.addItem(Information.parallelogramName);
        addShapeComboBox.addItem(Information.regularPentagonName);
        operateShapeComboBox.addItem("---请选择---");

        //为添加图形复合框和操作图形复合框增加触发事件
        addShapeComboBox.addActionListener(e -> graphicController.showGraphicDataDialog());
        operateShapeComboBox.addActionListener(e -> graphicController.operateShapeComboBoxActionPerformed());
    }

    /**
     * 初始化组件
     */
    private void initComponents() {
        JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JComboBox<String> addShapeComboBox = new JComboBox<>();
        JPanel panel3 = new JPanel();
        JComboBox<String> operateShapeComboBox = new JComboBox<>();
        JRadioButton deleteRadioButton = new JRadioButton();
        JRadioButton peekRadioButton = new JRadioButton();
        JRadioButton modifyRadioButton = new JRadioButton();
        JLabel ratioLabel = new JLabel();
        JPanel graphicPanel = new JPanel();

        //======== this ========
        setTitle("GraphicDesign");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== mainPanel ========
        {
            mainPanel.setMinimumSize(new Dimension(100, 100));
            mainPanel.setPreferredSize(new Dimension(500, 500));
            mainPanel.setLayout(new BorderLayout());

            //======== panel1 ========
            {

                //======== panel2 ========
                {
                    panel2.setBorder(new TitledBorder(null, "\u6dfb\u52a0\u56fe\u5f62", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
                    panel2.setLayout(new GridBagLayout());
                    ((GridBagLayout) panel2.getLayout()).columnWidths = new int[]{0, 0};
                    ((GridBagLayout) panel2.getLayout()).rowHeights = new int[]{0, 0};
                    ((GridBagLayout) panel2.getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
                    ((GridBagLayout) panel2.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};
                    panel2.add(addShapeComboBox,
                            new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0),
                                    0, 0));
                }

                //======== panel3 ========
                {
                    panel3.setBorder(new TitledBorder(null, "\u64cd\u4f5c\u56fe\u5f62", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
                    panel3.setLayout(new GridBagLayout());
                    ((GridBagLayout) panel3.getLayout()).columnWidths = new int[]{0, 0};
                    ((GridBagLayout) panel3.getLayout()).rowHeights = new int[]{0, 0, 0, 0, 0};
                    ((GridBagLayout) panel3.getLayout()).columnWeights = new double[]{0.0, 1.0E-4};
                    ((GridBagLayout) panel3.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0E-4};
                    panel3.add(operateShapeComboBox,
                            new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0),
                                    0, 0));

                    //---- deleteRadioButton ----
                    deleteRadioButton.setText("\u5220\u9664");
                    deleteRadioButton.setSelected(true);
                    panel3.add(deleteRadioButton,
                            new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0),
                                    0, 0));

                    //---- peekRadioButton ----
                    peekRadioButton.setText("\u67e5\u770b");
                    panel3.add(peekRadioButton,
                            new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0),
                                    0, 0));

                    //---- modifyRadioButton ----
                    modifyRadioButton.setText("\u4fee\u6539");
                    panel3.add(modifyRadioButton,
                            new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0),
                                    0, 0));
                }

                //---- ratioLabel ----
                ratioLabel.setText("\u67e5\u770b\u5f53\u524d\u7f29\u653e\u6bd4\u4f8b");

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(panel1Layout.createParallelGroup()
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(ratioLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE));
                panel1Layout.setVerticalGroup(panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                                .addComponent(ratioLabel)));
            }
            mainPanel.add(panel1, BorderLayout.EAST);

            //======== graphicPanel ========
            {
                graphicPanel.setBorder(new LineBorder(Color.cyan));
                graphicPanel.setLayout(new BorderLayout());
            }
            mainPanel.add(graphicPanel, BorderLayout.CENTER);
        }
        contentPane.add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //---- buttonGroup2 ----
        var buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(deleteRadioButton);
        buttonGroup2.add(peekRadioButton);
        buttonGroup2.add(modifyRadioButton);

        //将组件放入viewComponent中
        ViewComponent viewComponent = BaseHolder.getBean("viewComponent", ViewComponent.class);
        viewComponent.setAddShapeComboBox(addShapeComboBox);
        viewComponent.setOperateShapeComboBox(operateShapeComboBox);
        viewComponent.setGraphicPanel(graphicPanel);
        viewComponent.setRatioLabel(ratioLabel);
        viewComponent.setDeleteRadioButton(deleteRadioButton);
        viewComponent.setPeekRadioButton(peekRadioButton);
        viewComponent.setModifyRadioButton(modifyRadioButton);
    }
}
