package org.xpup.hafmis.signjoint.disposal;

import java.util.List;

import javax.servlet.ServletContext;
import org.xpup.hafmis.signjoint.util.Filter;
import org.xpup.hafmis.signjoint.util.SignTools;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.disposalinterface.IDisposal;
import org.xpup.hafmis.signjoint.dto.BaseInfoDTO;
import org.xpup.hafmis.signjoint.entity.Sign;
/**
 * ��ѯ���������ϸ������
 * @author yinchao
 *
 */
public class QueryBorrowListDisposal extends QueryBase implements IDisposal{
  
  private static final String QUERY_BORROW_LIST_BALANCE="1006";//���������ϸ������

  
  public QueryBorrowListDisposal(List list) {
    super(list);
  }
 /**
  * ������
  */
  public String disposal(ServletContext sc) {
    if(!Filter.doFilter(list, 4)){
      return SignTools.getInfo_06(QUERY_BORROW_LIST_BALANCE);
    }
    String startdate=((String)list.get(1)).trim();
    String enddate=((String)list.get(2)).trim();
    String sign=((String)list.get(3)).trim();
    if((!SignTools.isRightDate(startdate))||(!SignTools.isRightDate(enddate))){
      return SignTools.getInfo_06(QUERY_BORROW_LIST_BALANCE);
    }
    if(!SignTools.isRigntSignid(sign)){//����ǷǷ�ǩԼID
      return SignTools.getInfo_06(QUERY_BORROW_LIST_BALANCE);
    }
    
    ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",sc);
    Sign signed=bs.querySignBySignNum(sign);
    if(isHaveSign(signed)){//����Ѿ�ǩԼ
      BaseInfoDTO req=getRequest(signed);
      return bs.queryBorrowListBalance(req, startdate, enddate);
    }
    else{
      return SignTools.getInfo_04(QUERY_BORROW_LIST_BALANCE);
    }

  } 
}
