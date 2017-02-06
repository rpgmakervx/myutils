package org.easyarch.myutils.test.db;

import org.easyarch.myutils.jdbc.annotation.entity.Column;
import org.easyarch.myutils.jdbc.annotation.entity.Table;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description :
 * Created by xingtianyu on 16-12-22
 * 上午10:34
 * description: 订单和资金池对账结果
 */
@Table(tableName = "check_result_order_cashpool")
public class CashPoolCheck {

    /**
     * 主键
     */
    @Column(name = "pk_id")
    private long pkId;
    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private long orderId;
    /**
     * 标识哪两方对账（用于区分哪两个平台之间对账，有不同的标识）
     */
    @Column(name = "check_system_type")
    private int checkSystemType;
    /**
     * 对账批次号（check_system_type+_年-月-日 时：分：秒）
     */
    @Column(name = "check_batch_no")
    private String checkBatchNo;
    /**
     *对账日期（年-月-日），对账的是哪一天的数据
     */
    @Column(name = "check_date")
    private Date checkDate;
    /**
     * 对账结果（1:成功（不会记录），2:错账，3:多账，4:短账）
     */
    @Column(name = "check_result_code")
    private int checkResultCode;
    /**
     * 对账结果描述(具体原因)
     */
    @Column(name = "check_result_desc")
    private String checkResultDesc;

    /**
     * 更新对账假删除标志位的时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 对账结果创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 对账结果状态（1正常  2系统逻辑删除,3.人工逻辑删除）
     */
    @Column(name = "status")
    private int status;
    /**
     * 订单中心订单状态
     */
    @Column(name = "status_order")
    private int statusOrder;

    /**
     * 订单中心订单总价(三方平台公式)
     */
    @Column(name = "money_order")
    private BigDecimal moneyOrder;
    /**
     * 订单中心订单创建时间
     */
    @Column(name = "create_time_order")
    private Date createTimeOrder;

    /**
     * 订单中心订单完成时间
     */
    @Column(name = "finish_time_order")
    private Date finishTimeOrder;
    /**
     * 订单中心订单取消时间
     */
    @Column(name = "cancel_time_order")
    private Date cancelTimeOrder;
    /**
     * 订单中心业务线ID（三方接入：111  新业务：112）
     */
    @Column(name = "line_id_order")
    private int lineIdOrder;
    /**
     *  订单中心三方的服务ID （service_key/org_id）
     *  （三方订单系统存在部分商家不需要记资金流水，不能将这部分商家流水记录到库, 资金池中就不存在，需要根据service_key去映射这部分商家，此类商家订单不需要对）
     */
    @Column(name = "third_party_id_order")
    private long thirdPartyIdOrder;
    /**
     * 资金池流水结算金额
     */
    @Column(name = "money_cashpool")
    private BigDecimal moneyCashPool;

    /**
     *  处理人
     */
    @Column(name = "fix_operator")
    private String fixOperator;
    /**
     * 处理备注
     */
    @Column(name = "fix_comment")
    private String fixComment;

    public long getPkId() {
        return pkId;
    }

    public void setPkId(long pkId) {
        this.pkId = pkId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getCheckSystemType() {
        return checkSystemType;
    }

    public void setCheckSystemType(int checkSystemType) {
        this.checkSystemType = checkSystemType;
    }

    public String getCheckBatchNo() {
        return checkBatchNo;
    }

    public void setCheckBatchNo(String checkBatchNo) {
        this.checkBatchNo = checkBatchNo;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public int getCheckResultCode() {
        return checkResultCode;
    }

    public void setCheckResultCode(int checkResultCode) {
        this.checkResultCode = checkResultCode;
    }

    public String getCheckResultDesc() {
        return checkResultDesc;
    }

    public void setCheckResultDesc(String checkResultDesc) {
        this.checkResultDesc = checkResultDesc;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(int statusOrder) {
        this.statusOrder = statusOrder;
    }

    public BigDecimal getMoneyOrder() {
        return moneyOrder;
    }

    public void setMoneyOrder(BigDecimal moneyOrder) {
        this.moneyOrder = moneyOrder;
    }

    public Date getCreateTimeOrder() {
        return createTimeOrder;
    }

    public void setCreateTimeOrder(Date createTimeOrder) {
        this.createTimeOrder = createTimeOrder;
    }

    public Date getFinishTimeOrder() {
        return finishTimeOrder;
    }

    public void setFinishTimeOrder(Date finishTimeOrder) {
        this.finishTimeOrder = finishTimeOrder;
    }

    public Date getCancelTimeOrder() {
        return cancelTimeOrder;
    }

    public void setCancelTimeOrder(Date cancelTimeOrder) {
        this.cancelTimeOrder = cancelTimeOrder;
    }

    public int getLineIdOrder() {
        return lineIdOrder;
    }

    public void setLineIdOrder(int lineIdOrder) {
        this.lineIdOrder = lineIdOrder;
    }

    public long getThirdPartyIdOrder() {
        return thirdPartyIdOrder;
    }

    public void setThirdPartyIdOrder(long thirdPartyIdOrder) {
        this.thirdPartyIdOrder = thirdPartyIdOrder;
    }

    public BigDecimal getMoneyCashPool() {
        return moneyCashPool;
    }

    public void setMoneyCashPool(BigDecimal moneyCashPool) {
        this.moneyCashPool = moneyCashPool;
    }

    public String getFixOperator() {
        return fixOperator;
    }

    public void setFixOperator(String fixOperator) {
        this.fixOperator = fixOperator;
    }

    public String getFixComment() {
        return fixComment;
    }

    public void setFixComment(String fixComment) {
        this.fixComment = fixComment;
    }


    @Override
    public String toString() {
        return "CashPoolCheck{" +
                "pkId=" + pkId +
                ", orderId=" + orderId +
                ", checkSystemType=" + checkSystemType +
                ", checkBatchNo='" + checkBatchNo + '\'' +
                ", checkDate=" + checkDate +
                ", checkResultCode=" + checkResultCode +
                ", checkResultDesc='" + checkResultDesc + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", status=" + status +
                ", statusOrder=" + statusOrder +
                ", moneyOrder=" + moneyOrder +
                ", createTimeOrder=" + createTimeOrder +
                ", finishTimeOrder=" + finishTimeOrder +
                ", cancelTimeOrder=" + cancelTimeOrder +
                ", lineIdOrder=" + lineIdOrder +
                ", thirdPartyIdOrder=" + thirdPartyIdOrder +
                ", moneyCashPool=" + moneyCashPool +
                ", fixOperator='" + fixOperator + '\'' +
                ", fixComment='" + fixComment + '\'' +
                '}';
    }
}
