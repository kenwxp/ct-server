package com.cloudtimes.app.models;

import lombok.Data;

@Data
public class WsTaskListData {
    /**
     * 任务编号
     */
    private String taskId;
    /**
     * 门店号
     */
    private String storeId;
    /**
     * 店铺名称(内存冗余)
     */
    private String storeName;

    /**
     * 联系人姓名(内存冗余)
     */
    private String contactName;
    /**
     * 联系电话(内存冗余)
     */
    private String contactPhone;

    /**
     * 值守员工号
     */
    private String staffCode;

    /**
     * 值守区域
     */
    private String superviseArea;

    /**
     * 状态
     * 进行中	0
     * 已结束	1
     */
    private String state;

}
