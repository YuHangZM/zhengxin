package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckShowListDTO;

/**
 * Copy Right Information : ��ʾƾ֤¼��ά���б��ActionForm Goldsoft Project :
 * CredenceFillinTdAF
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.10.31
 */
public class CredenceFillinTdAF extends ActionForm {

  /** ��װ�˲�ѯ������ */
  private CredencecheckFindDTO credencecheckFindDTO = new CredencecheckFindDTO();

  /** ��װ���б������ */
  private CredencecheckShowListDTO credencecheckShowListDTO = new CredencecheckShowListDTO();
  
  private Map credenceStMap = new HashMap();

  private String credenceSt = "";
  
  private String type = "0";
  
  private List list;

  public CredencecheckFindDTO getCredencecheckFindDTO() {
    return credencecheckFindDTO;
  }

  public void setCredencecheckFindDTO(CredencecheckFindDTO credencecheckFindDTO) {
    this.credencecheckFindDTO = credencecheckFindDTO;
  }

  public CredencecheckShowListDTO getCredencecheckShowListDTO() {
    return credencecheckShowListDTO;
  }

  public void setCredencecheckShowListDTO(
      CredencecheckShowListDTO credencecheckShowListDTO) {
    this.credencecheckShowListDTO = credencecheckShowListDTO;
  }

  public String getCredenceSt() {
    return credenceSt;
  }

  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
  }

  public Map getCredenceStMap() {
    return credenceStMap;
  }

  public void setCredenceStMap(Map credenceStMap) {
    this.credenceStMap = credenceStMap;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
