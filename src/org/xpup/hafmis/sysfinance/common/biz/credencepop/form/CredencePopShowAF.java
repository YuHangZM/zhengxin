package org.xpup.hafmis.sysfinance.common.biz.credencepop.form;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopInfoDTO;

/**
 * Copy Right Information : ��ʾƾ֤������ActionForm Goldsoft Project :
 * CredencePopShowAF
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.11.3
 */
public class CredencePopShowAF extends ActionForm {
  private String check="";
  /** ƾ֤��Ϣ */
  private CredencePopInfoDTO credencePopInfoDTO = new CredencePopInfoDTO();

  private String credenceId = "";
  /** �б����� */
  private List list;

  /** ���ϼ� */
  private BigDecimal sumDebit = new BigDecimal(0.00);

  /** ����ϼ� */
  private BigDecimal sumCredit = new BigDecimal(0.00);

  public BigDecimal getSumCredit() {
    return sumCredit;
  }

  public void setSumCredit(BigDecimal sumCredit) {
    this.sumCredit = sumCredit;
  }

  public BigDecimal getSumDebit() {
    return sumDebit;
  }

  public void setSumDebit(BigDecimal sumDebit) {
    this.sumDebit = sumDebit;
  }

  public CredencePopInfoDTO getCredencePopInfoDTO() {
    return credencePopInfoDTO;
  }

  public void setCredencePopInfoDTO(CredencePopInfoDTO credencePopInfoDTO) {
    this.credencePopInfoDTO = credencePopInfoDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
  public String getCredenceId() {
    return credenceId;
  }

  public void setCredenceId(String credenceId) {
    this.credenceId = credenceId;
  }
  public String getCheck() {
    return check;
  }

  public void setCheck(String check) {
    this.check = check;
  }
}
