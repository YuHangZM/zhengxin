/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.dataready.develop.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.dataready.develop.dto.BuildDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorDevelopInfoDTO;

/** 
 * MyEclipse Struts
 * Creation date: 06-20-2008
 * 
 * XDoclet definition:
 * @struts.form name="buildAF"
 */
public class BuildAF extends ActionForm {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private BuildDTO buildDTO = new BuildDTO();
  private FloorDevelopInfoDTO floorDevelopInfoDTO = new FloorDevelopInfoDTO();
  private List list = new ArrayList();
  
  public BuildDTO getBuildDTO() {
    return buildDTO;
  }
  public void setBuildDTO(BuildDTO buildDTO) {
    this.buildDTO = buildDTO;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public FloorDevelopInfoDTO getFloorDevelopInfoDTO() {
    return floorDevelopInfoDTO;
  }
  public void setFloorDevelopInfoDTO(FloorDevelopInfoDTO floorDevelopInfoDTO) {
    this.floorDevelopInfoDTO = floorDevelopInfoDTO;
  }
}