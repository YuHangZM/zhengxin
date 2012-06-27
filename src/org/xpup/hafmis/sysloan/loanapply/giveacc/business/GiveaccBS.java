package org.xpup.hafmis.sysloan.loanapply.giveacc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.GiveAccDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.GiveAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanapply.giveacc.bsinterface.IGiveaccBS;
import org.xpup.hafmis.sysloan.loanapply.giveacc.dto.GiveaccModifyDTO;
import org.xpup.hafmis.sysloan.loanapply.giveacc.dto.HouseListDTO;

public class GiveaccBS implements IGiveaccBS {
  private HousesDAO housesDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private GiveAccDAO giveAccDAO = null;

  private SecurityDAO securityDAO = null;

  /**
   * ���ݺ�ͬ��Ų�ѯ�����ʺ��޸���Ϣ
   * 
   * @param contractId ��ͬ���
   * @param houseType ס������
   * @return GiveaccModifyDTO
   * @throws Exception
   * @author wsh
   */
  public GiveaccModifyDTO findGiveaccInfo(String contractId, String houseType)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    GiveaccModifyDTO giveaccModifyDTO = new GiveaccModifyDTO();
    try {
      giveaccModifyDTO = housesDAO.queryHousesInfo_wsh(contractId.toString(),
          houseType);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return giveaccModifyDTO;
  }

  /**
   * ���������ͬ��Ų�ѯ��ͬ����Ƿ����
   * 
   * @param contractId ��ͬ���
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void findGiveaccInfoExist(String contractId, List loanBankList)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    int count = 0;
    try {
      count = borrowerAccDAO.queryIdExist1_wsh(contractId, loanBankList)
          .intValue();
      // ��������˵�������ͬ��Ų�����
      if (count == 0) {
        be = new BusinessException("��ͬ��Ų����ڻ����û�Ȩ���£�");
        throw be;
      }
    } catch (BusinessException e) {
      // TODO: handle exception
      e.printStackTrace();
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  /**
   * �޸Ļ����ʺ�
   * 
   * @param contractId ��ͬ���
   * @throws Exception
   * @author wsh
   */
  public String findHouseType(String contractId) throws Exception {
    // TODO Auto-generated method stub
    String houseType = "";
    try {
      houseType = housesDAO.findHouseType_wsh(contractId);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return houseType;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  /**
   * �޸Ļ����ʺ�
   * 
   * @param contractId ��ͬ���
   * @param newLoanKouAcc �¿ۿ��ʺ�
   * @param oldBankAcc �ɿۿ��ʺ�
   * @param securityInfo Ȩ��
   * @throws Exception
   * @author wsh
   */
  public void modifyGiveAccInfo(String contractId, String oldSellerPayBank,
      String oldPayBankAcc, String newSellerPayBank, String newPayBankAcc,
      String houseType, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    try {
      Houses house = housesDAO.queryById(contractId);
      if ("01".equals(houseType)) {
        house.setDeveloperPaybank(newSellerPayBank);
        house.setDeveloperPaybankAAcc(newPayBankAcc);
      } else {
        house.setBargainorPaybank(newSellerPayBank);
        house.setBargainorPaybankAcc(newPayBankAcc);
      }
      GiveAcc giveAcc = new GiveAcc();
      giveAcc.setContractId(contractId);
      giveAcc.setModifyDate(securityInfo.getUserInfo().getPlbizDate());
      giveAcc.setNewPokeBank(newSellerPayBank);
      giveAcc.setNewPokeBankAcc(newPayBankAcc);
      giveAcc.setOldPokeBank(oldSellerPayBank);
      giveAcc.setOldPokeBankAcc(oldPayBankAcc);
      giveAcc.setOprator(securityInfo.getUserName());
      giveAcc.setOpTime(new Date());
      giveAccDAO.insert(giveAcc);
      PlOperateLog plOperateLog = new PlOperateLog();
      // ������־PL021
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN
          + ""));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_GATHERINGACC_DO));
      plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      plOperateLog.setOpBizId(new BigDecimal(giveAcc.getPokeBankModifyId()
          .toString()));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public void setGiveAccDAO(GiveAccDAO giveAccDAO) {
    this.giveAccDAO = giveAccDAO;
  }

  /**
   * author wsh ���ݺ�ͬ���,��������������ţ��۷��˲�ѯPL131�м�¼���� 2007-9-26
   * 
   * @param contractId ��ͬ���
   * @param borrowerName ���������
   * @param cardNum ����
   * @param sellerName �۷���
   * @return int
   */
  public int findHouseAccCount(String contractId, String borrowerName,
      String cardNum, String sellerName, List loanBankList) throws Exception {
    // TODO Auto-generated method stub
    int count = 0;
    try {
      count = housesDAO.queryHouseCount_wsh(contractId, borrowerName, cardNum,
          sellerName, loanBankList).intValue();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return count;
  }

  /**
   * ��ѯ�����ʺ�ά����Ҫ��ӡ�ĵ�����Ϣ����ҳ������ʾ����Ϣ
   * 
   * @param pagination
   * @throws Exception
   * @return List
   * @author wsh
   */
  public List findHouseAccList(Pagination pagination, List loanBankList)
      throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      String contractId = "";
      String borrowerName = "";
      String cardNum = "";
      String sellerName = "";
      if (pagination.getQueryCriterions().get("contractId") != null) {
        contractId = (String) pagination.getQueryCriterions().get("contractId");
      }
      if (pagination.getQueryCriterions().get("borrowerName") != null) {
        borrowerName = (String) pagination.getQueryCriterions().get(
            "borrowerName");
      }
      if (pagination.getQueryCriterions().get("cardNum") != null) {
        cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      }
      if (pagination.getQueryCriterions().get("sellerName") != null) {
        sellerName = (String) pagination.getQueryCriterions().get("sellerName");
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      list = housesDAO.queryHouseList_wsh(contractId, borrowerName, cardNum,
          sellerName, orderBy, order, start, pageSize, loanBankList);
      for (int i = 0; i < list.size(); i++) {
        HouseListDTO houseListDTO = (HouseListDTO) list.get(i);
        // ת����ʵ����
        if (houseListDTO.getOperator() != null
            && !"".equals(houseListDTO.getOperator()))
          houseListDTO.setOperator(getUserRealName(houseListDTO.getOperator()));
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ��ѯ�����ʺ�ά����Ҫ��ӡ�б���Ϣ
   * 
   * @param pagination
   * @throws Exception
   * @return List
   * @author wsh
   */
  public List findHouseAccPrintList(Pagination pagination, List loanBankList)
      throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      String contractId = "";
      String borrowerName = "";
      String cardNum = "";
      String sellerName = "";
      if (pagination.getQueryCriterions().get("contractId") != null) {
        contractId = (String) pagination.getQueryCriterions().get("contractId");
      }
      if (pagination.getQueryCriterions().get("borrowerName") != null) {
        borrowerName = (String) pagination.getQueryCriterions().get(
            "borrowerName");
      }
      if (pagination.getQueryCriterions().get("cardNum") != null) {
        cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      }
      if (pagination.getQueryCriterions().get("sellerName") != null) {
        cardNum = (String) pagination.getQueryCriterions().get("sellerName");
      }
      list = housesDAO.queryHousePrintList_wsh(contractId, borrowerName,
          cardNum, sellerName, loanBankList);
      for (int i = 0; i < list.size(); i++) {
        HouseListDTO houseListDTO = (HouseListDTO) list.get(i);
        // ת����ʵ����
        if (houseListDTO.getOperator() != null
            && !"".equals(houseListDTO.getOperator()))
          houseListDTO.setOperator(getUserRealName(houseListDTO.getOperator()));
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  public String getUserRealName(String name) throws Exception {
    // TODO Auto-generated method stub
    String realName = "";
    try {

      realName = securityDAO.queryByUserid(name);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return realName;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }
}
