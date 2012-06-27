package org.xpup.hafmis.signjoint.disposal;
import java.util.List;
import org.xpup.hafmis.signjoint.util.Filter;
import org.xpup.hafmis.signjoint.util.SignTools;
import javax.servlet.ServletContext;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.disposalinterface.*;
import org.xpup.hafmis.signjoint.dto.BaseInfoDTO;
import org.xpup.hafmis.signjoint.dto.RequestSignDTO;
import org.xpup.hafmis.signjoint.entity.Sign;

/**
 * ��ѯ������
 * @author yinchao
 */
public class QueryBalanceDisposal extends QueryBase implements IDisposal{


  private static final String QUERY_BALANCE_NUMBER="1003";//�������ʻ���������

  public QueryBalanceDisposal(List list) {
    super(list);
  }
  /**
   * ������
   */
  public String disposal(ServletContext sc) {

    if(!Filter.doFilter(list, 2)){//������ݸ�ʽ����
      return SignTools.getInfo_06(QUERY_BALANCE_NUMBER);
    }
    String sign=((String)list.get(1)).trim();
    if(!SignTools.isRigntSignid(sign)){//����ǷǷ�ǩԼID
      return SignTools.getInfo_06(QUERY_BALANCE_NUMBER);
    }
    ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",sc);
    Sign signed=bs.querySignBySignNum(sign);
    if(isHaveSign(signed)){//����Ѿ�ǩԼ
      BaseInfoDTO req=getRequest(signed);
      return bs.queryBalance(req);
    }
    else{
      return SignTools.getInfo_04(QUERY_BALANCE_NUMBER);
    }
  }

  
  
}
