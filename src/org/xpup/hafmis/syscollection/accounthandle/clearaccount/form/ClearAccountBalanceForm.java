package org.xpup.hafmis.syscollection.accounthandle.clearaccount.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * 
 * @author ����
 *2007-6-27
 */
public class ClearAccountBalanceForm extends ValidatorActionForm{
 
  private static final long serialVersionUID = 0L;
//����
  private Integer org_payment_count=new Integer(0);//��λ���  ����
  private BigDecimal org_payment_balance=new BigDecimal(0.00);//��λ���  ������
  private Integer org_add_payment_count=new Integer(0);//��λ����  ����
  private BigDecimal org_add_payment_balance=new BigDecimal(0.00);//��λ����  ������
  private Integer emp_add_payment_count=new Integer(0);//���˲���  ����
  private BigDecimal emp_add_payment_balance=new BigDecimal(0.00);//���˲���  ������
  private Integer org_over_pay_count=new Integer(0);//��λ���� ����
  private BigDecimal org_over_paybalance=new BigDecimal(0.00);//��λ���� ������
  private Integer org_tranin_count=new Integer(0);//��λת�� ����
  private BigDecimal org_tranin_paybalance=new BigDecimal(0.00);//��λת�� ������
  private Integer adjustaccout_credit_count=new Integer(0);//���˵���--���ɴ� ����
  private BigDecimal adjustaccout_credit_paybalance=new BigDecimal(0.00);//���˵���--���ɴ� ������
  private Integer adjustaccoutPayment_credit_count=new Integer(0);//���˵���--���ɴ� ����
  private BigDecimal adjustaccoutPayment_credit_paybalance=new BigDecimal(0.00);//���˵���--���ɴ� ������
  private Integer adjustaccoutPick_credit_count=new Integer(0);//���˵���--����ȡ ����
  private BigDecimal adjustaccoutPick_credit_paybalance=new BigDecimal(0.00);//���˵���--����ȡ ������
  private Integer adjustaccoutOther_credit_count=new Integer(0);//���˵���--������ ����
  private BigDecimal adjustaccoutOthert_credit_paybalance=new BigDecimal(0.00);//���˵���--������ ������
  private Integer clearinteres_count=new Integer(0);//��Ϣ ����
  private BigDecimal clearinteres_paybalance=new BigDecimal(0.00);//���ڽ�Ϣ ������
  private BigDecimal clearinteres_paybalance_1=new BigDecimal(0.00);//���ڽ�Ϣ ������
  private Integer credit_count=new Integer(0);//�����ϼ� ����
  private BigDecimal credit_paybalance=new BigDecimal(0.00);//�����ϼ� ������
  private BigDecimal xiaoji1_credit_paybalance=new BigDecimal(0.00);//�����ϼ� ������
  private BigDecimal xiaoji2_credit_paybalance=new BigDecimal(0.00);//�����ϼ� ������
  private BigDecimal xiaoji3_credit_paybalance=new BigDecimal(0.00);//�����ϼ� ������
  private BigDecimal pre_rest_paybalance=new BigDecimal(0.00);//�ڳ���� ������
  private BigDecimal cur_rest_paybalance=new BigDecimal(0.00);//��ĩ��� ������
  private Integer xiaoji1_credit_count=new Integer(0);//���˵���--���ɴ� ����
  private Integer xiaoji2_credit_count=new Integer(0);//���˵���--���ɴ� ����
  //�跽
  private Integer org_tranout_count=new Integer(0);//��λת��  ����
  private BigDecimal org_tranout_balance=new BigDecimal(0.00);//��λת��  ������
  private Integer pick_count=new Integer(0);//��ȡ  ����
  private BigDecimal pick_balance=new BigDecimal(0.00);//��ȡ  ������
  private Integer pick_count_xiaohu=new Integer(0);//��ȡ  ����
  private BigDecimal pick_balance_xiaohu=new BigDecimal(0.00);//��ȡ  ������
  //ld_�޸�
  private Integer pick_payload_count=new Integer(0);//���У�����  ����(�������»���)
  private BigDecimal pick_payload_balance=new BigDecimal(0.00);//���У�����  ������������»�����
  private Integer pick_payload_count_ld=new Integer(0);//���У�����  ������������һ���Ի����
  private BigDecimal pick_payload_balance_ld=new BigDecimal(0.00);//���У�����  �����������һ���Ի����
  
  private Integer pick_otherreason_count=new Integer(0);//���У�����  ����
  private BigDecimal pick_otherreason_balance=new BigDecimal(0.00);//���У�����  ������
  private Integer pick_sumcount=new Integer(0);//��ȡ  ����
  private BigDecimal pick_sumbalance=new BigDecimal(0.00);//��ȡ  ������
  private Integer tranoutinterest_count=new Integer(0);//ת����Ϣ ����
  private BigDecimal tranoutinterest_paybalance=new BigDecimal(0.00);//ת����Ϣ ������
  private Integer adjustaccout_debit_count=new Integer(0);//���˵��� ����
  private BigDecimal adjustaccout_debit_paybalance=new BigDecimal(0.00);//���˵��� ������
  private Integer adjustaccoutPayment_debit_count=new Integer(0);//���˵���--���ɴ� ����
  private BigDecimal adjustaccoutPayment_debit_paybalance=new BigDecimal(0.00);//���˵���--���ɴ� ������
  private Integer adjustaccoutPick_debit_count=new Integer(0);//���˵���--����ȡ ����
  private BigDecimal adjustaccoutPick_debit_paybalance=new BigDecimal(0.00);//���˵���--����ȡ ������
  private Integer adjustaccoutOther_debit_count=new Integer(0);//���˵���--������ ����
  private BigDecimal adjustaccoutOther_debit_paybalance=new BigDecimal(0.00);//���˵���--������ ������
  private Integer deleteaccount_interest_count=new Integer(0);//������Ϣ ����
  private BigDecimal deleteaccount_interest_paybalance=new BigDecimal(0.00);//������Ϣ ������
  private Integer debit_count=new Integer(0);//�跽�ϼ� ����
  private BigDecimal debit_paybalance=new BigDecimal(0.00);//�跽�ϼ� ������
  private Integer debit_count_xiaoji=new Integer(0);//�跽�ϼ� ����
  private BigDecimal debit_paybalance_xiaoji=new BigDecimal(0.00);//�跽�ϼ� ������
  private Integer debit_count_xiaoji_1=new Integer(0);//�跽�ϼ� ����
  private BigDecimal debit_paybalance_xiaoji_1=new BigDecimal(0.00);//�跽�ϼ� ������
  private Integer debit_interest_count=new Integer(0);//��Ϣ�ϼ�  ����
  private BigDecimal debit_interest_paybalance=new BigDecimal(0.00);//��Ϣ�ϼ� ������
  private BigDecimal pre_debit_paybalance=new BigDecimal(0.00);//���ڷ����� ������
  private BigDecimal cur_debit_paybalance=new BigDecimal(0.00);//������� ������
  private BigDecimal org_overpay_sum=new BigDecimal(0.00);//������� ������
  
  private BigDecimal gjjYuE=new BigDecimal(0.00);//���������

  
  private String type="";
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public Integer getAdjustaccout_credit_count() {
    return adjustaccout_credit_count;
  }
  public void setAdjustaccout_credit_count(Integer adjustaccout_credit_count) {
    this.adjustaccout_credit_count = adjustaccout_credit_count;
  }
  public BigDecimal getAdjustaccout_credit_paybalance() {
    return adjustaccout_credit_paybalance;
  }
  public void setAdjustaccout_credit_paybalance(
      BigDecimal adjustaccout_credit_paybalance) {
    this.adjustaccout_credit_paybalance = adjustaccout_credit_paybalance;
  }
  public Integer getAdjustaccoutOther_credit_count() {
    return adjustaccoutOther_credit_count;
  }
  public void setAdjustaccoutOther_credit_count(
      Integer adjustaccoutOther_credit_count) {
    this.adjustaccoutOther_credit_count = adjustaccoutOther_credit_count;
  }
  public BigDecimal getAdjustaccoutOthert_credit_paybalance() {
    return adjustaccoutOthert_credit_paybalance;
  }
  public void setAdjustaccoutOthert_credit_paybalance(
      BigDecimal adjustaccoutOthert_credit_paybalance) {
    this.adjustaccoutOthert_credit_paybalance = adjustaccoutOthert_credit_paybalance;
  }
  public Integer getAdjustaccoutPayment_credit_count() {
    return adjustaccoutPayment_credit_count;
  }
  public void setAdjustaccoutPayment_credit_count(
      Integer adjustaccoutPayment_credit_count) {
    this.adjustaccoutPayment_credit_count = adjustaccoutPayment_credit_count;
  }
  public BigDecimal getAdjustaccoutPayment_credit_paybalance() {
    return adjustaccoutPayment_credit_paybalance;
  }
  public void setAdjustaccoutPayment_credit_paybalance(
      BigDecimal adjustaccoutPayment_credit_paybalance) {
    this.adjustaccoutPayment_credit_paybalance = adjustaccoutPayment_credit_paybalance;
  }
  public Integer getAdjustaccoutPick_credit_count() {
    return adjustaccoutPick_credit_count;
  }
  public void setAdjustaccoutPick_credit_count(
      Integer adjustaccoutPick_credit_count) {
    this.adjustaccoutPick_credit_count = adjustaccoutPick_credit_count;
  }
  public BigDecimal getAdjustaccoutPick_credit_paybalance() {
    return adjustaccoutPick_credit_paybalance;
  }
  public void setAdjustaccoutPick_credit_paybalance(
      BigDecimal adjustaccoutPick_credit_paybalance) {
    this.adjustaccoutPick_credit_paybalance = adjustaccoutPick_credit_paybalance;
  }
  public Integer getAdjustaccout_debit_count() {
    return adjustaccout_debit_count;
  }
  public void setAdjustaccout_debit_count(Integer adjustaccout_debit_count) {
    this.adjustaccout_debit_count = adjustaccout_debit_count;
  }
  public BigDecimal getAdjustaccout_debit_paybalance() {
    return adjustaccout_debit_paybalance;
  }
  public void setAdjustaccout_debit_paybalance(
      BigDecimal adjustaccout_debit_paybalance) {
    this.adjustaccout_debit_paybalance = adjustaccout_debit_paybalance;
  }
  public Integer getClearinteres_count() {
    return clearinteres_count;
  }
  public void setClearinteres_count(Integer clearinteres_count) {
    this.clearinteres_count = clearinteres_count;
  }
  public BigDecimal getClearinteres_paybalance() {
    return clearinteres_paybalance;
  }
  public void setClearinteres_paybalance(BigDecimal clearinteres_paybalance) {
    this.clearinteres_paybalance = clearinteres_paybalance;
  }
  public Integer getCredit_count() {
    return credit_count;
  }
  public void setCredit_count(Integer credit_count) {
    this.credit_count = credit_count;
  }
  public BigDecimal getCredit_paybalance() {
    return credit_paybalance;
  }
  public void setCredit_paybalance(BigDecimal credit_paybalance) {
    this.credit_paybalance = credit_paybalance;
  }
  public BigDecimal getCur_debit_paybalance() {
    return cur_debit_paybalance;
  }
  public void setCur_debit_paybalance(BigDecimal cur_debit_paybalance) {
    this.cur_debit_paybalance = cur_debit_paybalance;
  }
  public BigDecimal getCur_rest_paybalance() {
    return cur_rest_paybalance;
  }
  public void setCur_rest_paybalance(BigDecimal cur_rest_paybalance) {
    this.cur_rest_paybalance = cur_rest_paybalance;
  }
  public Integer getDebit_count() {
    return debit_count;
  }
  public void setDebit_count(Integer debit_count) {
    this.debit_count = debit_count;
  }
  public Integer getDebit_interest_count() {
    return debit_interest_count;
  }
  public void setDebit_interest_count(Integer debit_interest_count) {
    this.debit_interest_count = debit_interest_count;
  }
  public BigDecimal getDebit_interest_paybalance() {
    return debit_interest_paybalance;
  }
  public void setDebit_interest_paybalance(BigDecimal debit_interest_paybalance) {
    this.debit_interest_paybalance = debit_interest_paybalance;
  }
  public BigDecimal getDebit_paybalance() {
    return debit_paybalance;
  }
  public void setDebit_paybalance(BigDecimal debit_paybalance) {
    this.debit_paybalance = debit_paybalance;
  }
  public Integer getDeleteaccount_interest_count() {
    return deleteaccount_interest_count;
  }
  public void setDeleteaccount_interest_count(Integer deleteaccount_interest_count) {
    this.deleteaccount_interest_count = deleteaccount_interest_count;
  }
  public BigDecimal getDeleteaccount_interest_paybalance() {
    return deleteaccount_interest_paybalance;
  }
  public void setDeleteaccount_interest_paybalance(
      BigDecimal deleteaccount_interest_paybalance) {
    this.deleteaccount_interest_paybalance = deleteaccount_interest_paybalance;
  }
  public BigDecimal getEmp_add_payment_balance() {
    return emp_add_payment_balance;
  }
  public void setEmp_add_payment_balance(BigDecimal emp_add_payment_balance) {
    this.emp_add_payment_balance = emp_add_payment_balance;
  }
  public Integer getEmp_add_payment_count() {
    return emp_add_payment_count;
  }
  public void setEmp_add_payment_count(Integer emp_add_payment_count) {
    this.emp_add_payment_count = emp_add_payment_count;
  }
  public BigDecimal getOrg_add_payment_balance() {
    return org_add_payment_balance;
  }
  public void setOrg_add_payment_balance(BigDecimal org_add_payment_balance) {
    this.org_add_payment_balance = org_add_payment_balance;
  }
  public Integer getOrg_add_payment_count() {
    return org_add_payment_count;
  }
  public void setOrg_add_payment_count(Integer org_add_payment_count) {
    this.org_add_payment_count = org_add_payment_count;
  }
  public Integer getOrg_over_pay_count() {
    return org_over_pay_count;
  }
  public void setOrg_over_pay_count(Integer org_over_pay_count) {
    this.org_over_pay_count = org_over_pay_count;
  }
  public BigDecimal getOrg_over_paybalance() {
    return org_over_paybalance;
  }
  public void setOrg_over_paybalance(BigDecimal org_over_paybalance) {
    this.org_over_paybalance = org_over_paybalance;
  }
  public BigDecimal getOrg_payment_balance() {
    return org_payment_balance;
  }
  public void setOrg_payment_balance(BigDecimal org_payment_balance) {
    this.org_payment_balance = org_payment_balance;
  }
  public Integer getOrg_payment_count() {
    return org_payment_count;
  }
  public void setOrg_payment_count(Integer org_payment_count) {
    this.org_payment_count = org_payment_count;
  }
  public Integer getOrg_tranin_count() {
    return org_tranin_count;
  }
  public void setOrg_tranin_count(Integer org_tranin_count) {
    this.org_tranin_count = org_tranin_count;
  }
  public BigDecimal getOrg_tranin_paybalance() {
    return org_tranin_paybalance;
  }
  public void setOrg_tranin_paybalance(BigDecimal org_tranin_paybalance) {
    this.org_tranin_paybalance = org_tranin_paybalance;
  }
  public BigDecimal getOrg_tranout_balance() {
    return org_tranout_balance;
  }
  public void setOrg_tranout_balance(BigDecimal org_tranout_balance) {
    this.org_tranout_balance = org_tranout_balance;
  }
  public Integer getOrg_tranout_count() {
    return org_tranout_count;
  }
  public void setOrg_tranout_count(Integer org_tranout_count) {
    this.org_tranout_count = org_tranout_count;
  }
  public BigDecimal getPick_balance() {
    return pick_balance;
  }
  public void setPick_balance(BigDecimal pick_balance) {
    this.pick_balance = pick_balance;
  }
  public Integer getPick_count() {
    return pick_count;
  }
  public void setPick_count(Integer pick_count) {
    this.pick_count = pick_count;
  }
  public BigDecimal getPick_otherreason_balance() {
    return pick_otherreason_balance;
  }
  public void setPick_otherreason_balance(BigDecimal pick_otherreason_balance) {
    this.pick_otherreason_balance = pick_otherreason_balance;
  }
  public Integer getPick_otherreason_count() {
    return pick_otherreason_count;
  }
  public void setPick_otherreason_count(Integer pick_otherreason_count) {
    this.pick_otherreason_count = pick_otherreason_count;
  }
  public BigDecimal getPick_payload_balance() {
    return pick_payload_balance;
  }
  public void setPick_payload_balance(BigDecimal pick_payload_balance) {
    this.pick_payload_balance = pick_payload_balance;
  }
  public Integer getPick_payload_count() {
    return pick_payload_count;
  }
  public void setPick_payload_count(Integer pick_payload_count) {
    this.pick_payload_count = pick_payload_count;
  }
  public BigDecimal getPre_debit_paybalance() {
    return pre_debit_paybalance;
  }
  public void setPre_debit_paybalance(BigDecimal pre_debit_paybalance) {
    this.pre_debit_paybalance = pre_debit_paybalance;
  }
  public BigDecimal getPre_rest_paybalance() {
    return pre_rest_paybalance;
  }
  public void setPre_rest_paybalance(BigDecimal pre_rest_paybalance) {
    this.pre_rest_paybalance = pre_rest_paybalance;
  }
  public Integer getTranoutinterest_count() {
    return tranoutinterest_count;
  }
  public void setTranoutinterest_count(Integer tranoutinterest_count) {
    this.tranoutinterest_count = tranoutinterest_count;
  }
  public BigDecimal getTranoutinterest_paybalance() {
    return tranoutinterest_paybalance;
  }
  public void setTranoutinterest_paybalance(BigDecimal tranoutinterest_paybalance) {
    this.tranoutinterest_paybalance = tranoutinterest_paybalance;
  }
  public BigDecimal getPick_sumbalance() {
    return pick_sumbalance;
  }
  public void setPick_sumbalance(BigDecimal pick_sumbalance) {
    this.pick_sumbalance = pick_sumbalance;
  }
  public Integer getPick_sumcount() {
    return pick_sumcount;
  }
  public void setPick_sumcount(Integer pick_sumcount) {
    this.pick_sumcount = pick_sumcount;
  }
  public Integer getAdjustaccoutOther_debit_count() {
    return adjustaccoutOther_debit_count;
  }
  public void setAdjustaccoutOther_debit_count(
      Integer adjustaccoutOther_debit_count) {
    this.adjustaccoutOther_debit_count = adjustaccoutOther_debit_count;
  }
  public BigDecimal getAdjustaccoutOther_debit_paybalance() {
    return adjustaccoutOther_debit_paybalance;
  }
  public void setAdjustaccoutOther_debit_paybalance(
      BigDecimal adjustaccoutOther_debit_paybalance) {
    this.adjustaccoutOther_debit_paybalance = adjustaccoutOther_debit_paybalance;
  }
  public Integer getAdjustaccoutPayment_debit_count() {
    return adjustaccoutPayment_debit_count;
  }
  public void setAdjustaccoutPayment_debit_count(
      Integer adjustaccoutPayment_debit_count) {
    this.adjustaccoutPayment_debit_count = adjustaccoutPayment_debit_count;
  }
  public BigDecimal getAdjustaccoutPayment_debit_paybalance() {
    return adjustaccoutPayment_debit_paybalance;
  }
  public void setAdjustaccoutPayment_debit_paybalance(
      BigDecimal adjustaccoutPayment_debit_paybalance) {
    this.adjustaccoutPayment_debit_paybalance = adjustaccoutPayment_debit_paybalance;
  }
  public Integer getAdjustaccoutPick_debit_count() {
    return adjustaccoutPick_debit_count;
  }
  public void setAdjustaccoutPick_debit_count(Integer adjustaccoutPick_debit_count) {
    this.adjustaccoutPick_debit_count = adjustaccoutPick_debit_count;
  }
  public BigDecimal getAdjustaccoutPick_debit_paybalance() {
    return adjustaccoutPick_debit_paybalance;
  }
  public void setAdjustaccoutPick_debit_paybalance(
      BigDecimal adjustaccoutPick_debit_paybalance) {
    this.adjustaccoutPick_debit_paybalance = adjustaccoutPick_debit_paybalance;
  }
  public BigDecimal getPick_payload_balance_ld() {
    return pick_payload_balance_ld;
  }
  public void setPick_payload_balance_ld(BigDecimal pick_payload_balance_ld) {
    this.pick_payload_balance_ld = pick_payload_balance_ld;
  }
  public Integer getPick_payload_count_ld() {
    return pick_payload_count_ld;
  }
  public void setPick_payload_count_ld(Integer pick_payload_count_ld) {
    this.pick_payload_count_ld = pick_payload_count_ld;
  }
  public Integer getXiaoji1_credit_count() {
    return xiaoji1_credit_count;
  }
  public void setXiaoji1_credit_count(Integer xiaoji1_credit_count) {
    this.xiaoji1_credit_count = xiaoji1_credit_count;
  }
  public Integer getXiaoji2_credit_count() {
    return xiaoji2_credit_count;
  }
  public void setXiaoji2_credit_count(Integer xiaoji2_credit_count) {
    this.xiaoji2_credit_count = xiaoji2_credit_count;
  }
  public BigDecimal getXiaoji1_credit_paybalance() {
    return xiaoji1_credit_paybalance;
  }
  public void setXiaoji1_credit_paybalance(BigDecimal xiaoji1_credit_paybalance) {
    this.xiaoji1_credit_paybalance = xiaoji1_credit_paybalance;
  }
  public BigDecimal getXiaoji2_credit_paybalance() {
    return xiaoji2_credit_paybalance;
  }
  public void setXiaoji2_credit_paybalance(BigDecimal xiaoji2_credit_paybalance) {
    this.xiaoji2_credit_paybalance = xiaoji2_credit_paybalance;
  }
  public BigDecimal getPick_balance_xiaohu() {
    return pick_balance_xiaohu;
  }
  public void setPick_balance_xiaohu(BigDecimal pick_balance_xiaohu) {
    this.pick_balance_xiaohu = pick_balance_xiaohu;
  }
  public Integer getPick_count_xiaohu() {
    return pick_count_xiaohu;
  }
  public void setPick_count_xiaohu(Integer pick_count_xiaohu) {
    this.pick_count_xiaohu = pick_count_xiaohu;
  }
  public Integer getDebit_count_xiaoji() {
    return debit_count_xiaoji;
  }
  public void setDebit_count_xiaoji(Integer debit_count_xiaoji) {
    this.debit_count_xiaoji = debit_count_xiaoji;
  }
  public BigDecimal getDebit_paybalance_xiaoji() {
    return debit_paybalance_xiaoji;
  }
  public void setDebit_paybalance_xiaoji(BigDecimal debit_paybalance_xiaoji) {
    this.debit_paybalance_xiaoji = debit_paybalance_xiaoji;
  }
  public BigDecimal getClearinteres_paybalance_1() {
    return clearinteres_paybalance_1;
  }
  public void setClearinteres_paybalance_1(BigDecimal clearinteres_paybalance_1) {
    this.clearinteres_paybalance_1 = clearinteres_paybalance_1;
  }
  public BigDecimal getXiaoji3_credit_paybalance() {
    return xiaoji3_credit_paybalance;
  }
  public void setXiaoji3_credit_paybalance(BigDecimal xiaoji3_credit_paybalance) {
    this.xiaoji3_credit_paybalance = xiaoji3_credit_paybalance;
  }
  public Integer getDebit_count_xiaoji_1() {
    return debit_count_xiaoji_1;
  }
  public void setDebit_count_xiaoji_1(Integer debit_count_xiaoji_1) {
    this.debit_count_xiaoji_1 = debit_count_xiaoji_1;
  }
  public BigDecimal getDebit_paybalance_xiaoji_1() {
    return debit_paybalance_xiaoji_1;
  }
  public void setDebit_paybalance_xiaoji_1(BigDecimal debit_paybalance_xiaoji_1) {
    this.debit_paybalance_xiaoji_1 = debit_paybalance_xiaoji_1;
  }
  public BigDecimal getOrg_overpay_sum() {
    return org_overpay_sum;
  }
  public void setOrg_overpay_sum(BigDecimal org_overpay_sum) {
    this.org_overpay_sum = org_overpay_sum;
  }
  public BigDecimal getGjjYuE() {
    return gjjYuE;
  }
  public void setGjjYuE(BigDecimal gjjYuE) {
    this.gjjYuE = gjjYuE;
  }
 
  
}
