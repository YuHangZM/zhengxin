package org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.dto.ReportMngDTO;
/**
 * 
 * @author ����
 * 2007-10-16
 */
public class CreateReportAF extends ActionForm {
  
  private static final long serialVersionUID = -2138600478238560629L;
  
  private String tableid="";
  private String tablenamedef="";//����ı������� 
  private String colspandef="";//����ı�������
  private String rowspandef="";//����ı�������
  
  private String tablenamequery="";//��ѯ�ı�������
  private String createtimestart="";//��ѯ�ı�������ʼ����
  private String createtimeend="";//��ѯ�ı�������ֹ����
  
  private ReportMngDTO reportMngDTO=new ReportMngDTO();
  private List list=new ArrayList();//��ʾ���б�
  private String actionflag="0";//������ʶ��0��ȷ����1���޸�
  private String savemethod="0";//������ʶ��1��ȷ��
  

  public String getTableid() {
    return tableid;
  }
  public void setTableid(String tableid) {
    this.tableid = tableid;
  }
  public String getActionflag() {
    return actionflag;
  }
  public void setActionflag(String actionflag) {
    this.actionflag = actionflag;
  }
  public String getColspandef() {
    return colspandef;
  }
  public void setColspandef(String colspandef) {
    this.colspandef = colspandef;
  }
  public String getCreatetimeend() {
    return createtimeend;
  }
  public void setCreatetimeend(String createtimeend) {
    this.createtimeend = createtimeend;
  }
  public String getCreatetimestart() {
    return createtimestart;
  }
  public void setCreatetimestart(String createtimestart) {
    this.createtimestart = createtimestart;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public ReportMngDTO getReportMngDTO() {
    return reportMngDTO;
  }
  public void setReportMngDTO(ReportMngDTO reportMngDTO) {
    this.reportMngDTO = reportMngDTO;
  }
  public String getRowspandef() {
    return rowspandef;
  }
  public void setRowspandef(String rowspandef) {
    this.rowspandef = rowspandef;
  }
  public String getTablenamedef() {
    return tablenamedef;
  }
  public void setTablenamedef(String tablenamedef) {
    this.tablenamedef = tablenamedef;
  }
  public String getTablenamequery() {
    return tablenamequery;
  }
  public void setTablenamequery(String tablenamequery) {
    this.tablenamequery = tablenamequery;
  }
  public String getSavemethod() {
    return savemethod;
  }
  public void setSavemethod(String savemethod) {
    this.savemethod = savemethod;
  }

}
