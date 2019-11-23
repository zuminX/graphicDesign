package com.controller;

/**
 * 控制层的接口
 * <p>
 * 接收视图层的数据,数据传递给业务层
 * 接受业务层返回的数据并返回给视图层
 */
public interface GraphicController {
    /**
     * 显示输入图形数据的对话框
     */
    void showGraphicDataDialog();

    /**
     * 保存图形数据
     */
    void saveGraphicData();

    /**
     * 关闭输入图形数据的对话框
     */
    void closeGraphicDataDialog();

    /**
     * 点击图形下拉选时进行的操作
     */
    void operateShapeComboBoxActionPerformed();
}
