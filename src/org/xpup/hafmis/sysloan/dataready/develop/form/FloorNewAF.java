package org.xpup.hafmis.sysloan.dataready.develop.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.common.domain.entity.DevelopProject;

/**
 * ���¥����Ϣ��ActionForm
 * 
 * @author fuyunfeng
 */
public class FloorNewAF extends ActionForm {

  /**
   * ¥����Ϣ
   */
  private DevelopProject developProject = new DevelopProject();

  /**
   * ���ڵ�����map
   */
  private Map regionMap = new HashMap();

  /**
   * �ô�״̬�������޸������
   */
  private String type = "";

  public Map getRegionMap() {
    return regionMap;
  }

  public void setRegionMap(Map regionMap) {
    this.regionMap = regionMap;
  }

  public DevelopProject getDevelopProject() {
    return developProject;
  }

  public void setDevelopProject(DevelopProject developProject) {
    this.developProject = developProject;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
