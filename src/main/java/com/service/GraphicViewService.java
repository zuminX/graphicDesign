package com.service;

/**
 * 图形页面业务层的接口
 * 接收控制层的数据
 * 返回数据给控制层
 */
public interface GraphicViewService {
    /**
     * 显示错误信息的提示框
     *
     * @param err 错误信息
     */
    void showErrorInformation(String err);

    /**
     * 设置图形数据对话框的可见性
     *
     * @param b 是否可见
     */
    void setGraphicDataDialogVisible(boolean b);

    /**
     * 设置图形数据对话框的可见性
     *
     * @param shapeName 图形名字
     * @param b         是否可见
     */
    void setGraphicDataDialogVisible(String shapeName, boolean b);

    /**
     * 重绘图形面板
     */
    void repaintGraphicPanel();

    /**
     * 显示当前图形面板和图形最小尺寸的比率
     */
    void showNowShapeRadio();

    /**
     * 显示信息的提示框
     *
     * @param message 信息
     * @param title   标题
     */
    void showMessageDialog(String message, String title);

    /**
     * 显示确认对话框
     *
     * @param content 文本内容
     * @param title   标签
     * @return 0为确定, 1为取消,-1为关闭窗口
     */
    int showConfirmDialog(String content, String title);

    /**
     * 添加图形下拉选
     */
    void addShapeItemData();

    /**
     * 显示删除图形的确认对话框
     *
     * @param index 删除图形的下标
     * @return 0为确定, 1为取消，-1为关闭窗口
     */
    int showDeleteShapeConfirmDialog(int index);

    /**
     * 删除指定下标的图形下拉选
     *
     * @param index 下标
     */
    void deleteShapeItem(int index);

    /**
     * 显示图形的详细数据
     *
     * @param index 下标
     */
    void showShapeDetails(int index);

}
