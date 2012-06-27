package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class EmpOpenExpNoteDTO  implements ExpDto {
  
  private String note = null;
  private String docmun=null;

  public String getDocmun() {
    return docmun;
  }

  public void setDocmun(String docmun) {
    this.docmun = docmun;
  }

  /**
   * @return ���� orgunitcode��
   */
  public String getNote() {
    return note;
  }

  /**
   * @param orgunitcode Ҫ���õ� orgunitcode��
   */
  public void setNote(String note) {
    this.note = note;
  }



  public String getInfo(String info) {
    if (info.equals("note")) {
      return this.note;

    }
    if (info.equals("docmun")) {
      return this.docmun;

    }
    return null;

  }




}
