package com.controller.impl;

import com.controller.GraphicController;
import com.domain.ViewComponent;
import com.service.GraphicDataService;
import com.service.GraphicViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;

/**
 * 控制层
 * <p>
 * 接收视图层的数据,数据传递给业务层
 * 接受业务层返回的数据并返回给视图层
 */
@Controller
public class GraphicControllerImpl implements GraphicController {

    private final GraphicViewService graphicViewService;

    private final GraphicDataService graphicDataService;

    private final ViewComponent viewComponent;

    /**
     * 注入成员变量
     */
    @Autowired
    public GraphicControllerImpl(GraphicViewService graphicViewService, GraphicDataService graphicDataService, ViewComponent viewComponent) {
        this.graphicViewService = graphicViewService;
        this.graphicDataService = graphicDataService;
        this.viewComponent = viewComponent;
    }

    /**
     * 显示输入图形数据的对话框
     */
    @Override
    public void showGraphicDataDialog() {
        if (isNotTipItem(viewComponent.getAddShapeComboBox())) {
            //清理对话框的数据
            graphicDataService.cleanGraphicDataDialogData();
            graphicViewService.setGraphicDataDialogVisible(true);
        }
    }

    /**
     * 保存图形数据
     */
    @Override
    public void saveGraphicData() {
        Boolean b;
        final boolean isModify = viewComponent.getModifyRadioButton().isSelected();

        //当前为修改模式
        if (isModify) {
            b = graphicDataService.modifyGraphicData();
            //当前为添加模式
        } else {
            b = graphicDataService.saveGraphicData();
        }

        //添加或修改成功
        if (b != null) {
            if (isModify) {
                graphicViewService.deleteShapeItem(graphicDataService.getModifyIndex() + 1);
            }
            graphicViewService.addShapeItemData();
            closeGraphicDataDialog();
        }
    }

    /**
     * 关闭输入图形数据的对话框
     */
    @Override
    public void closeGraphicDataDialog() {
        graphicViewService.setGraphicDataDialogVisible(false);
        //显示当前所有图形与显示面板的比率
        graphicViewService.showNowShapeRadio();
        //重绘图形面板
        graphicViewService.repaintGraphicPanel();
    }

    /**
     * 点击图形下拉选时进行的操作
     */
    @Override
    public void operateShapeComboBoxActionPerformed() {
        final int index = viewComponent.getOperateShapeComboBox().getSelectedIndex() - 1;   //选择图形的下标
        //选择的不是提示的项
        if (isNotTipItem(viewComponent.getOperateShapeComboBox())) {
            //选择删除
            if (viewComponent.getDeleteRadioButton().isSelected()) {
                //显示删除图形的确认提示
                final int result = graphicViewService.showDeleteShapeConfirmDialog(index);
                //选择确认删除
                if (result == 0) {
                    //删除数据
                    graphicDataService.deleteShapeData(index);
                    //删除项
                    graphicViewService.deleteShapeItem(index + 1);
                    //重绘面板
                    graphicViewService.repaintGraphicPanel();
                }
                //选择查看
            } else if (viewComponent.getPeekRadioButton().isSelected()) {
                graphicViewService.showShapeDetails(index);
                //选择修改
            } else if (viewComponent.getModifyRadioButton().isSelected()) {
                //填充数据
                graphicDataService.fillNowData(index);
                graphicDataService.setModifyIndex(index);
                graphicViewService.setGraphicDataDialogVisible(graphicDataService.getShapeByIndex(index).getShapeName(), true);
            }
        }
    }

    /**
     * 判断所选的项是否为提示项
     *
     * @param comboBox 图形数据组合框
     * @return 不是提示项返回true，否则返回false
     */
    private boolean isNotTipItem(JComboBox<?> comboBox) {
        return comboBox.getSelectedIndex() != 0;
    }
}
