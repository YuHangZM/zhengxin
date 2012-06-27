package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbFindDTO;

/**
 * Copy Right Information : �Զ�ת��ҳ��ActionForm Goldsoft Project :
 * CredenceFillinTbAF
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.10.17
 */
public class CredenceFillinTbAF extends ActionForm {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /** ��ѯ���� */
  private CredenceFillinTbFindDTO credenceFillinTbFindDTO = new CredenceFillinTbFindDTO();

  /** �б����� */
  private List list;

  private BigDecimal moneyall = new BigDecimal(0.00);

  /** ҵ��״̬Map */
  private Map bizStMap = new HashMap();

  /** ҵ������Map */
  private Map bizTypeMap = new HashMap();

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

  public CredenceFillinTbFindDTO getCredenceFillinTbFindDTO() {
    return credenceFillinTbFindDTO;
  }

  public void setCredenceFillinTbFindDTO(
      CredenceFillinTbFindDTO credenceFillinTbFindDTO) {
    this.credenceFillinTbFindDTO = credenceFillinTbFindDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public BigDecimal getMoneyall() {
    return moneyall;
  }

  public void setMoneyall(BigDecimal moneyall) {
    this.moneyall = moneyall;
  }

}
