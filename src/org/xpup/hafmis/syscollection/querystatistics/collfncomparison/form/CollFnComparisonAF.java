package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollInfoFindDTO;

public class CollFnComparisonAF extends ActionForm {
  
  /** ��װ�˲�ѯ������DTO */
  OrgCollInfoFindDTO dto = new OrgCollInfoFindDTO();

  /** ��ѯ�б� */
  private List list = new ArrayList();

  public OrgCollInfoFindDTO getDto() {
    return dto;
  }

  public void setDto(OrgCollInfoFindDTO dto) {
    this.dto = dto;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
}
