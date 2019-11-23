package com.service;

import com.domain.Shape;

/**
 * 图形数据业务层的接口
 * 接收控制层的数据
 * 返回数据给控制层
 */
public interface GraphicDataService {
    /**
     * 保存图形数据
     *
     * @return 保存成功返回true，发生异常返回null
     */
    Boolean saveGraphicData();

    /**
     * 清空图形数据对话框中的数据
     */
    void cleanGraphicDataDialogData();

    /**
     * 删除指定下标的图形数据
     *
     * @param index 下标
     */
    void deleteShapeData(int index);

    /**
     * 根据下标填充对话框的数据
     *
     * @param index 下标
     */
    void fillNowData(int index);

    /**
     * 通过下标获取该下标的图形
     *
     * @param index 下标
     * @return 图形
     */
    Shape getShapeByIndex(int index);

    /**
     * 修改图形数据
     *
     * @return 修改成功返回true，发生异常返回null
     */
    Boolean modifyGraphicData();

    /**
     * 获取修改图形的下标
     *
     * @return 下标
     */
    int getModifyIndex();

    /**
     * 设置修改图形在集合中的下标
     *
     * @param index 下标
     */
    void setModifyIndex(int index);
}
