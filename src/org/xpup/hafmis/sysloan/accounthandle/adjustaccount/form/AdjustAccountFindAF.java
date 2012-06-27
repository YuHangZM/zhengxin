package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTbFindDTO;

public class AdjustAccountFindAF extends ActionForm {

  /**
   * ��װ�˲�ѯ������DTO
   */
  private AdjustAccountTbFindDTO adjustAccountTbFindDTO = new AdjustAccountTbFindDTO();

  /**
   * �б���Ϣ
   */
  private List List;

  /**
   * ���Ž��ϼ�
   */
  private BigDecimal sumOccurMoney = new BigDecimal(0.00);

  /**
   * ���ձ���ϼ�
   */
  private BigDecimal sumCallbackCorpus = new BigDecimal(0.00);

  /**
   * ������Ϣ�ϼ�
   */
  private BigDecimal sumCallbackInterest = new BigDecimal(0.00);

  /**
   * ��Ϣ��Ϣ�ϼ�
   */
  private BigDecimal sumPunishInterest = new BigDecimal(0.00);

  /**
   * ҵ������Map
   */
  Map bizTypeMap = new HashMap();

  /**
   * ҵ��״̬Map
   */
  Map bizStMap = new HashMap();

  public AdjustAccountTbFindDTO getAdjustAccountTbFindDTO() {
    return adjustAccountTbFindDTO;
  }

  public void setAdjustAccountTbFindDTO(
      AdjustAccountTbFindDTO adjustAccountTbFindDTO) {
    this.adjustAccountTbFindDTO = adjustAccountTbFindDTO;
  }

  public List getList() {
    return List;
  }

  public void setList(List list) {
    List = list;
  }

  public BigDecimal getSumCallbackCorpus() {
    return sumCallbackCorpus;
  }

  public void setSumCallbackCorpus(BigDecimal sumCallbackCorpus) {
    this.sumCallbackCorpus = sumCallbackCorpus;
  }

  public BigDecimal getSumCallbackInterest() {
    return sumCallbackInterest;
  }

  public void setSumCallbackInterest(BigDecimal sumCallbackInterest) {
    this.sumCallbackInterest = sumCallbackInterest;
  }

  public BigDecimal getSumOccurMoney() {
    return sumOccurMoney;
  }

  public void setSumOccurMoney(BigDecimal sumOccurMoney) {
    this.sumOccurMoney = sumOccurMoney;
  }

  public BigDecimal getSumPunishInterest() {
    return sumPunishInterest;
  }

  public void setSumPunishInterest(BigDecimal sumPunishInterest) {
    this.sumPunishInterest = sumPunishInterest;
  }

  public Map getBizStMap() {
    return bizStMap;
  }

  public void setBizStMap(Map bizStMap) {
    this.bizStMap = bizStMap;
  }

  public Map getBizTypeMap() {
    return bizTypeMap;
  }

  public void setBizTypeMap(Map bizTypeMap) {
    this.bizTypeMap = bizTypeMap;
  }

}
