package org.xpup.hafmis.signjoint.disposal;

import java.util.List;
import org.xpup.hafmis.signjoint.dto.BaseInfoDTO;
import org.xpup.hafmis.signjoint.entity.Sign;

/**
 * ��ѯ��������̳��Դ���
 * @author yinchao
 */

public class QueryBase {

  protected List list;//��������
  public QueryBase(List list)
  {
    this.list=list;
  }
  
  
  /**
   * ������Sign����ȡ������װ��DTO
   * @param signǩԼ����
   * @return ��װ���DTO
   */
  protected BaseInfoDTO getRequest(Sign sign)
  {
    return new BaseInfoDTO(sign.getName(),
               sign.getCard_num(),
               sign.getEmpid(),
               sign.getBank_card());            
  }
  /**
   * �жϲ�ѯ����ǩԼ��Ϣ�Ƿ�Ϊ��
   * @param sign ǩԼ��Ϣ����
   * @return �Ƿ�Ϊ��
   */
  protected boolean isHaveSign(Sign sign){
    if(sign!=null)
      return true;
    else
      return false;
  }
}
