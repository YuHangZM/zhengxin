package org.xpup.hafmis.sysloan.dataready.develop.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorDevelopInfoDTO;

/**
 * ¥����Ϣ��ActionForm
 * 
 * @author ���Ʒ�
 */
public class FloorFindAF extends ActionForm {
  /**
   * ��װ�˿�������Ϣ��DTO
   */
  private FloorDevelopInfoDTO floorDevelopInfoDTO = new FloorDevelopInfoDTO();
  
  /**
   * ��װ���б���Ϣ��List
   */
  private List list;
  /**
   * �����ж��Ƿ��һ���Ǳ�ͷ����Ϣ
   */
  private String flag = "";

  public FloorDevelopInfoDTO getFloorDevelopInfoDTO() {
    return floorDevelopInfoDTO;
  }

  public void setFloorDevelopInfoDTO(FloorDevelopInfoDTO floorDevelopInfoDTO) {
    this.floorDevelopInfoDTO = floorDevelopInfoDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }
}
