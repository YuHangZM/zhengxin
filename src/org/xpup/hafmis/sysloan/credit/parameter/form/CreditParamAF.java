package org.xpup.hafmis.sysloan.credit.parameter.form;

import org.apache.struts.action.ActionForm;

/**
 * @author ���
 */
public class CreditParamAF extends ActionForm {

  private static final long serialVersionUID = 1L;

  /** ���ݸ�ʽ�汾�� */
  private String sjgsbbh = null;

  /** ���ڻ������� */
  private String jrjgdm = null;

  /** �ϴ����İ汾�� */
  private String scbwbbh = null;

  /** �����ص� */
  private String fsdd = null;

  /** ���� */
  private String bz = null;

  /** ��ϵ�� */
  private String lxr = null;

  /** ��ϵ�绰 */
  private String lxdh = null;

  public String getJrjgdm() {
    return jrjgdm;
  }

  public void setJrjgdm(String jrjgdm) {
    this.jrjgdm = jrjgdm;
  }

  public String getScbwbbh() {
    return scbwbbh;
  }

  public void setScbwbbh(String scbwbbh) {
    this.scbwbbh = scbwbbh;
  }

  public String getSjgsbbh() {
    return sjgsbbh;
  }

  public void setSjgsbbh(String sjgsbbh) {
    this.sjgsbbh = sjgsbbh;
  }

  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getFsdd() {
    return fsdd;
  }

  public void setFsdd(String fsdd) {
    this.fsdd = fsdd;
  }

  public String getLxdh() {
    return lxdh;
  }

  public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
  }

  public String getLxr() {
    return lxr;
  }

  public void setLxr(String lxr) {
    this.lxr = lxr;
  }

}
