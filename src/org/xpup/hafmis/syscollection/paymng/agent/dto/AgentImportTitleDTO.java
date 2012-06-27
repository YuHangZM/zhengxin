package org.xpup.hafmis.syscollection.paymng.agent.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

/**
 * Copy Right Information : ���۵����ͷDTO Goldsoft Project : AgentImportTitleDTO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.17
 */
public class AgentImportTitleDTO extends impDto {
  /** �������� */
  private String agentYearMonth = "";

  /** Ʊ�ݱ�� */
  private String noteNum = "";

  /** �ɴ淽ʽ */
  private String payMode = "";

  /** �������� */
  private String agentType = "";

  public String getAgentType() {
    return agentType;
  }

  public void setAgentType(String agentType) {
    this.agentType = agentType;
  }

  public String getAgentYearMonth() {
    return agentYearMonth;
  }

  public void setAgentYearMonth(String agentYearMonth) {
    this.agentYearMonth = agentYearMonth;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getPayMode() {
    return payMode;
  }

  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }
}
