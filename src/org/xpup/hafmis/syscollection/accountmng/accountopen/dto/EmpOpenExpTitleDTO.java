package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

/**
 * ְ������ͷ��Ϣ����ģ��
 * 
 * @author ���� 2007-7-11
 */
public class EmpOpenExpTitleDTO implements ExpDto {
  
  private String orgunitcode = null;


  private String orgunitname = null;
  public String getInfo(String info) {
    if (info.equals("orgunitcode")) {
      return this.orgunitcode;

    }
    if (info.equals("orgunitname")) {
      return this.orgunitname;

    }



    return null;

  }

  /**
   * @return ���� orgunitcode��
   */
  public String getOrgunitcode() {
    return orgunitcode;
  }

  /**
   * @param orgunitcode Ҫ���õ� orgunitcode��
   */
  public void setOrgunitcode(String orgunitcode) {
    this.orgunitcode = orgunitcode;
  }





  /**
   * @return ���� orgunitname��
   */
  public String getOrgunitname() {
    return orgunitname;
  }

  /**
   * @param orgunitname Ҫ���õ� orgunitname��
   */
  public void setOrgunitname(String orgunitname) {
    this.orgunitname = orgunitname;
  }



}
