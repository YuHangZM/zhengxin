package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class ChgpersonExpNoteDTO implements ExpDto {
/**
 * ��Ա�������˵��DTO
 */
  private String note="";

  public String getInfo(String info) {
    if (info.equals("note")) {
      return this.note;

    }
    return null;

  }
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
