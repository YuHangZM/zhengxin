package org.xpup.hafmis.sysloan.common.biz.contractpop.business;

import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.biz.contractpop.bsinterface.IContractpopBS;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;

/**
 * @author yuqf
 */
public class ContractpopBS implements IContractpopBS {

  private BorrowerDAO borrowerDAO = null;

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public List findContractpopList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int count = 0;
    try {
      String afterLoanaccord = (String) pagination.getQueryCriterions().get(
          "afterLoanaccord");
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String empId = (String) pagination.getQueryCriterions().get("empId");
      List empSt = (List) pagination.getQueryCriterions().get("status");
      if (!"1".equals(afterLoanaccord)) {// ˵���Ƿ���֪ͨ��֮���,Ȩ��Ϊ���е� ��0��
        list = borrowerDAO.queryListByConditionYU_(contractId, borrowerName,
            cardNum, empId, empSt, start, pageSize, orderBy, order,
            securityInfo);
        count = borrowerDAO.queryListCountByConditionYU_(contractId,
            borrowerName, cardNum, empId, empSt, start, pageSize, orderBy,
            order, securityInfo);
        pagination.setNrOfElements(count);
      } else {// ����֪ͨ��֮ǰ�ģ�Ȩ��Ϊ����Ա�� ��1��
        list = borrowerDAO.queryListByConditionYU(contractId, borrowerName,
            cardNum, empId, empSt, start, pageSize, orderBy, order,
            securityInfo);
        count = borrowerDAO.queryListCountByConditionYU(contractId,
            borrowerName, cardNum, empId, empSt, start, pageSize, orderBy,
            order, securityInfo);
        pagination.setNrOfElements(count);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

}
