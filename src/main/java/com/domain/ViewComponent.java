package com.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * 封装显示的组件
 */
@Component("viewComponent")
@Data
public class ViewComponent {
    /**
     * 添加图形的复合框
     */
    private JComboBox<String> addShapeComboBox;
    /**
     * 操作图形的复合框
     */
    private JComboBox<String> operateShapeComboBox;
    /**
     * 图形面板
     */
    private JPanel graphicPanel;
    /**
     * 输入图形数据的对话框
     */
    private JDialog graphicDataDialog;
    /**
     * 显示比率的标签
     */
    private JLabel ratioLabel;
    /**
     * 删除的单选按钮
     */
    private JRadioButton deleteRadioButton;
    /**
     * 查看的单选按钮
     */
    private JRadioButton peekRadioButton;

    /**
     * 修改的单选按钮
     */
    private JRadioButton modifyRadioButton;
}
