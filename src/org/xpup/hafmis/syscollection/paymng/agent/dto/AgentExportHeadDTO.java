package org.xpup.hafmis.syscollection.paymng.agent.dto;
import org.xpup.common.util.exp.domn.interfaces.ExpDto;
/**
 * Copy Right Information : ����ģ�浼���ͷ˵����DTO Goldsoft Project : AgentImpShowAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.17
 */
public class AgentExportHeadDTO implements ExpDto{
  
  /** ���۵���˵��*/
  private String explain = "";
  
  public String getInfo(String info) {
    if (info.equals("explain")) {
      return this.explain;
    }
    return null;
  }

  public String getExplain() {
    return explain;
  }

  public void setExplain(String explain) {
    this.explain = explain;
  }
}
