package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto.CheckQueryPlFnTBFindDTO;

public class CheckQueryPlFnTBAF extends ActionForm {
  
  
  
  
  private String empId="";//�����ְ�����
  private String empName="";//�����ְ������
  private String startTime="";//������ʼʱ��
  private String endTime="";//��������ʱ��
  private String contractId="";//��ͬ���
  private CheckQueryPlFnTBFindDTO checkQueryPlFnTBFindDTO = new CheckQueryPlFnTBFindDTO();

  private List list=new ArrayList();

  public CheckQueryPlFnTBFindDTO getCheckQueryPlFnTBFindDTO() {
    return checkQueryPlFnTBFindDTO;
  }

  public void setCheckQueryPlFnTBFindDTO(
      CheckQueryPlFnTBFindDTO checkQueryPlFnTBFindDTO) {
    this.checkQueryPlFnTBFindDTO = checkQueryPlFnTBFindDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
}
