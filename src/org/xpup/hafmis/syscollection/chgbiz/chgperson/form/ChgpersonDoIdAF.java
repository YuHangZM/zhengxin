package org.xpup.hafmis.syscollection.chgbiz.chgperson.form;

import java.util.List;
import javax.servlet.ServletRequest;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.IdAF;

public class ChgpersonDoIdAF extends IdAF {   

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String chgDate="";//�������
  private List list ;

  public String getChgDate() {
    return chgDate;
  }

  public void setChgDate(String chgDate) {
    this.chgDate = chgDate;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    
    this.chgDate="";//�������
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
}
