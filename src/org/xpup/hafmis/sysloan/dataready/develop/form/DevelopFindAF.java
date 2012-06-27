package org.xpup.hafmis.sysloan.dataready.develop.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.dataready.develop.dto.DevelopTbFindDTO;

/**
 * ������ά��ҳ��ActionForm
 * 
 * @author ���Ʒ�
 */
public class DevelopFindAF extends ActionForm {

  /** ��ѯ���� */
  private DevelopTbFindDTO developTbFindDTO = new DevelopTbFindDTO();
  
  private String buyhouseorgid; // ���������ƣ�������ѯ��

  private String floorName; // ¥������

  private String floorNum; // ¥��
  
  /** �б����� */
  private List list;

  /** ������״̬Map */
  private Map developerStMap = new HashMap();

  public Map getDeveloperStMap() {
    return developerStMap;
  }

  public void setDeveloperStMap(Map developerStMap) {
    this.developerStMap = developerStMap;
  }

  public DevelopTbFindDTO getDevelopTbFindDTO() {
    return developTbFindDTO;
  }

  public void setDevelopTbFindDTO(DevelopTbFindDTO developTbFindDTO) {
    this.developTbFindDTO = developTbFindDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }

  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
  }

  public String getFloorName() {
    return floorName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
  }

  public String getFloorNum() {
    return floorNum;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }

}
