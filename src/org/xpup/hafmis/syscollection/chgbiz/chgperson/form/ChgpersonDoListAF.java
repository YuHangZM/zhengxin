package org.xpup.hafmis.syscollection.chgbiz.chgperson.form;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
/**
 * 
 * @author ����
 *2007-6-27
 */
public class ChgpersonDoListAF extends ActionForm {   
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private ChgPersonTail chgPersonTail=new ChgPersonTail();
  private String id="";//��λ����
  private String name="";//��λ����
  private String date="";//�������
  private List list;//�б�
  private String type = "1";
  private String flag1="1";//�ı����Ƿ�ֻ��

  private FormFile theFile = null;//�����ļ�
  private String url="";
  
  
  public String getType() {   
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public ChgPersonTail getChgPersonTail() {
    return chgPersonTail;
  }
  public void setChgPersonTail(ChgPersonTail chgPersonTail) {
    this.chgPersonTail = chgPersonTail;
  }
  public FormFile getTheFile() {
    return theFile;
  }
  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getFlag1() {
    return flag1;
  }
  public void setFlag1(String flag1) {
    this.flag1 = flag1;
  }
  public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    this.id="";
    this.name="";  
    this.date="";//�������
    this.type = "1";
    this.theFile = null;//�����ļ�
    this.flag1="1";
    this.url="";
    this.chgPersonTail=new ChgPersonTail();
    super.reset(arg0, arg1);
  }
}
