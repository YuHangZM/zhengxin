package org.xpup.hafmis.signjoint.disposal;

import java.util.List;
import org.xpup.hafmis.signjoint.util.Filter;
import org.xpup.hafmis.signjoint.util.SignTools;
import javax.servlet.ServletContext;

import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.disposalinterface.IDisposal;
import org.xpup.hafmis.signjoint.dto.RequestSignDTO;
/**
 * ǩԼ������
 * @author yinchao
 */
public class SignupDisposal implements IDisposal {

  private static final String SIGN_UP_NUMBER="1001";//ǩԼ������
  
  private List list;
  public SignupDisposal(List list)
  {
    this.list=list;
  }
  /**
   * ��װ��������
   * @param list �����������ݵ�List
   * @return �������
   */
  private RequestSignDTO getRequest(List list)
  {
    return new RequestSignDTO(
           ((String)list.get(1)).trim(),
           ((String)list.get(2)).trim(),
           ((String)list.get(3)).trim(),
           ((String)list.get(4)).trim());
  }
  /**
   * ����ǩԼ���󣬵���ҵ���߼�����
   * @return ������
   */
  public String disposal(ServletContext sc)
  {
    
    if(!Filter.doFilter(list, 5)){
      return SignTools.getFailedInfo(SIGN_UP_NUMBER);
    }

    RequestSignDTO req=getRequest(list);
    if((req.getCardnum().length()!=18)&&(req.getCardnum().length()!=15)){//������֤λ�����Ϸ�
      return SignTools.getInfo_06(SIGN_UP_NUMBER);
    }
    ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",sc);
    return bs.saveSign(req);
  }
  

}
