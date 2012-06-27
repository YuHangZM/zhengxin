package org.xpup.hafmis.signjoint.util;

import java.util.List;

import org.xpup.hafmis.signjoint.disposal.*;
import org.xpup.hafmis.signjoint.disposalinterface.IDisposal;
public class SignjointFactory {

  /**
   * ��þ����������
   * @param request��������
   * @return ��������
   */
  public static IDisposal getDisposal(String request)
  {
    String num;//������
    List list=SignTools.Compart(request);
    num=((String)list.get(0)).trim();//��ȡ������
    return getMod(num,list);
  }
  /**
   * �ж�ʹ���ĸ�IDisposalʵ���ദ��,����
   * @param num ������
   * @param list ��������
   * @return ��������
   */
  private static IDisposal getMod(String num,List list)
  {
    if("1001".equalsIgnoreCase(num))
      return new SignupDisposal(list);
    else if("1003".equalsIgnoreCase(num))
      return new QueryBalanceDisposal(list);
    else if("1004".equalsIgnoreCase(num))
      return new QueryListBalanceDisposal(list);
    else if("1005".equalsIgnoreCase(num))
      return new QueryBorrowDisposal(list);
    else if("1006".equalsIgnoreCase(num))
      return new QueryBorrowListDisposal(list);
    else
      return new QueryDefaultDisposal(list);
  }
  
  
}
