package org.xpup.hafmis.syscollection.collloanback.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.collloanback.bsinterface.ICollLoanbackBS;
import org.xpup.hafmis.syscollection.collloanback.dto.CollLoanbackTaListDTO;
import org.xpup.hafmis.syscollection.collloanback.dto.CollLoanbackTbListDTO;
import org.xpup.hafmis.syscollection.collloanback.dto.CollLoanbackTcListDTO;
import org.xpup.hafmis.syscollection.collloanback.form.CollLoanbackTaAF;
import org.xpup.hafmis.syscollection.common.dao.CollLoanbackHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.CollLoanbackTailDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumMaintainDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDrawingDAO;
import org.xpup.hafmis.syscollection.common.dao.PickBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.PickTailDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.CollLoanbackHead;
import org.xpup.hafmis.syscollection.common.domain.entity.CollLoanbackTail;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowDrawing;
import org.xpup.hafmis.syscollection.common.domain.entity.PickBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;
import org.xpup.hafmis.sysloan.common.dao.CollLoanbackParaDAO;
import org.xpup.hafmis.sysloan.common.dao.FundloanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.FundloanInfo;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BankExportsDTO;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;

public class CollLoanbackBS implements ICollLoanbackBS {
  private CollLoanbackHeadDAO collLoanbackHeadDAO = null;

  private CollLoanbackTailDAO collLoanbackTailDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  private FundloanInfoDAO fundloanInfoDAO = null;

  private CollBankDAO collBankDAO = null;

  private CollLoanbackParaDAO collLoanbackParaDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private DocNumMaintainDAO docNumMaintainDAO = null;

  private PickHeadDAO pickHeadDAO = null;

  private PickTailDAO pickTailDAO = null;

  private PickBizActivityLogDAO pickBizActivityLogDAO = null;

  private EmpHAFAccountFlowDAO empHAFAccountFlowDAO = null;

  private OrgDAO orgDAO = null;

  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  private OrgHAFAccountFlowDrawingDAO orgHAFAccountFlowDrawingDAO = null;

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public String LoanKouAcc_ = "";
  
  public BigDecimal per_z = new BigDecimal(0.00);

  public BigDecimal cur_z = new BigDecimal(0.00);

  public BigDecimal kou_z = new BigDecimal(0.00);// ��¼����������۽��.

  public BigDecimal kou_f = new BigDecimal(0.00);// ��¼������������۽�� .

  public boolean falg_kou = true;// �ж��Ƿ��ٿۿ�ı�ʶ.

  public boolean falg_kou_z = true;// �ж��Ƿ��ٿۿ�ı�ʶ.

  public boolean falg_kou_f = true;// �ж��Ƿ��ٿۿ�ı�ʶ.

  public static String LoanKouAcc_kou_ = "";// �ж��Ƿ��ٿۿ�ʹ�õĿۿ��˺�.
  public static String contract_yg = "";
  public static String yearmonth_yg = "";

  /**
   * �����𻹴�--����ҳ��
   * 
   * @author ���ƽ 2007-12-18 ��ѯ�б��е��������
   */
  public CollLoanbackTaAF findCollLoanbackTaList(Pagination pagination) {
    CollLoanbackTaAF af = new CollLoanbackTaAF();
    Object[] obj = new Object[2];
    String bank = (String) pagination.getQueryCriterions().get("bank");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String batchNum = "";
    List resultlist = new ArrayList();
    int count = 0;
    List alllist = new ArrayList();
    BigDecimal m_sum = new BigDecimal(0.00);
    try {
      String headId = "";
      // �жϸð��´���aa410���е�״̬�Ƿ�Ϊ1
      List list = collLoanbackHeadDAO.queryStatusByOffice(bank, "1");
      if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          CollLoanbackHead collLoanbackHead = (CollLoanbackHead) list.get(i);
          batchNum = collLoanbackHead.getBatchNum();
          headId = collLoanbackHead.getId().toString();
        }
        resultlist = collLoanbackTailDAO.queryCollLoanbackTaListById(headId,
            orderBy, order, start, pageSize, page);
        alllist = collLoanbackTailDAO.queryCollLoanbackTaListCountById(headId);
        count = alllist.size();
        if (count > 0) {
          for (int i = 0; i < count; i++) {
            CollLoanbackTaListDTO collLoanbackTaListDTO = new CollLoanbackTaListDTO();
            collLoanbackTaListDTO = (CollLoanbackTaListDTO) alllist.get(i);
            if (collLoanbackTaListDTO.getShouldCorpus() != null
                && !"".equals(collLoanbackTaListDTO.getShouldCorpus())) {
              m_sum = m_sum.add(collLoanbackTaListDTO.getShouldCorpus());
            }
          }
        }
      }
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    obj[0] = batchNum;
    obj[1] = resultlist;
    // obj
    af.setBatchNum(batchNum);
    af.setList(resultlist);
    af.setP_count(new Integer(count).toString());
    af.setM_sum(m_sum);
    return af;
  }

  /**
   * �����𻹴�--ά��ҳ��
   * 
   * @author ���ƽ 2007-12-22 ��ѯ�б��е��������
   */
  public Object[] findCollLoanbackTbList(Pagination pagination) {
    Object[] obj = new Object[2];
    String bank = (String) pagination.getQueryCriterions().get("bank");
    String batchNum = (String) pagination.getQueryCriterions().get("batchNum");
    String officeCode = (String) pagination.getQueryCriterions().get("office");
    String startdate = (String) pagination.getQueryCriterions()
        .get("startdate");
    String enddate = (String) pagination.getQueryCriterions().get("enddate");

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    List resultlist = new ArrayList();
    try {
      int count = 0;
      List list = collLoanbackTailDAO.queryCollLoanbackTbList(officeCode, bank,
          batchNum, orderBy, order, start, pageSize, page, startdate, enddate);
      if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          CollLoanbackTbListDTO collLoanbackTbListDTO = (CollLoanbackTbListDTO) list
              .get(i);
          String offcie = this
              .findOffice(collLoanbackTbListDTO.getLoanBankId());
          collLoanbackTbListDTO.setOfficeCode(collLoanbackTailDAO
              .findOfficeNameByOrgInfoOfficeCode(offcie));
          collLoanbackTbListDTO.setStatus(BusiTools.getBusiValue(Integer
              .parseInt(collLoanbackTbListDTO.getStatus()),
              BusiConst.COLLLOANBACKSTATUS));
          CollBank dto = collLoanbackTailDAO
              .getCollBankByCollBankid(collLoanbackTbListDTO.getLoanBankId());
          collLoanbackTbListDTO.setLoanBankId(dto.getCollBankName());

          String real_count = collLoanbackTailDAO
              .getreal_count(collLoanbackTbListDTO.getBatchNum());
          String real_kou_money = collLoanbackTailDAO
              .getreal_kou_money(collLoanbackTbListDTO.getBatchNum());
          String no_count = collLoanbackTailDAO
          .getno_count(collLoanbackTbListDTO.getBatchNum());
          String no_kou_money = collLoanbackTailDAO
          .getno_kou_money(collLoanbackTbListDTO.getBatchNum());

          collLoanbackTbListDTO.setReal_count(real_count);
          collLoanbackTbListDTO.setNo_count(no_count);
          collLoanbackTbListDTO.setAll_count((Integer.parseInt(no_count)+Integer.parseInt(real_count))+"");
          collLoanbackTbListDTO
              .setReal_kou_money(new BigDecimal(real_kou_money));
          collLoanbackTbListDTO.setNo_kou_money(new BigDecimal(no_kou_money));
          collLoanbackTbListDTO.setAll_kou_money(new BigDecimal(no_kou_money).add(new BigDecimal(real_kou_money)));
          resultlist.add(collLoanbackTbListDTO);
        }
      }
      List countList = collLoanbackTailDAO.queryCollLoanbackTbListCount(
          officeCode, bank, batchNum, startdate, enddate);
      count = countList.size();
      pagination.setNrOfElements(count);
      obj[0] = resultlist;
      obj[1] = countList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-19 ����
   */
  public void imports(List importList, SecurityInfo securityInfo)
      throws Exception {
    String loanBankId = "";
    String paramValue = "";
    String headId = "";
    String batchNum = "";
    String bizDateim = "";
    // ---------------------------------------------------------------
    String loankouacc = "";// �����ӵ���ۿ����.
    // -----------------------------------------------------------------
    // String bizDate = securityInfo.getUserInfo().getBizDate();// ҵ������
    BigDecimal shouldMoney = new BigDecimal(0.00);
    // ѭ�����Ӧ�۽��磺�ڶ��������˵ĸý��=Ӧ�۽��-��һ���˵�ʵ�۽�����ѭ����
    BigDecimal temp_shouldMoney = new BigDecimal(0.00);
    if (!importList.isEmpty()) {
      BankExportsDTO dto = (BankExportsDTO) importList.get(0);
      loanBankId = String.valueOf(Integer.parseInt(dto.getLoanBankId()));
      batchNum = dto.getBatchNum();
      // �жϸð��´���aa410���е�״̬�Ƿ����״̬Ϊ1��2������
      // System.out.println("loanBankId=="+loanBankId);
      List list = collLoanbackHeadDAO.queryStatusByOffice(loanBankId, "2");
      // System.out.println("list=="+list.size());
      if (list.size() > 0) {
        throw new BusinessException("���������Ѿ�����δ���˵�ҵ��");
      } else {
        if (dto.getBatchNum() != null) {
          List batchNumList = collLoanbackHeadDAO.queryBatchNum_GJP(batchNum);
          if (batchNumList.size() > 0) {
            throw new BusinessException("�����ļ������κ��Ѿ����ڣ�");
          }
        }
        String messages = "";
        int k=0;
        for (int i = 1; i < importList.size(); i++) {
          BankExportsDTO dto1 = (BankExportsDTO) importList.get(i);
          List empIdlist = fundloanInfoDAO.queryEmpIdByLoanKouAcc(dto1
              .getLoanKouAcc());
          String contractId = collLoanbackTailDAO
              .queryContractIdByLoanKouAcc(dto1.getLoanKouAcc());
          if (contractId == null || contractId.equals("")) {
            if(dto1.getLoanKouAcc().equals("")){
              throw new BusinessException("������β�п�����ɾ�����ٵ���.");
            }
            throw new BusinessException("�ۿ��˺�Ϊ:" + dto1.getLoanKouAcc()
                + "�Ĵ����ͬ״̬�Ѿ����ǻ�����,���ܿۿ");
          }
          for (int j = 0; j < empIdlist.size(); j++) {
            FundloanInfo fundloanInfo = (FundloanInfo) empIdlist.get(j);
            int count = collLoanbackTailDAO.queryTranoutCount(fundloanInfo
                .getOrgId(), fundloanInfo.getEmpId());
            if (count != 0) {
              k++;
              messages += dto1.getLoanKouAcc() + ",";
              if(k%2==0){
                messages += "\\r";
              }
            }
            break;
          }
        }
        if(!"".equals(messages)){
          throw new BusinessException("�ۿ��˺�Ϊ\\r"+messages.substring(0,messages.lastIndexOf(","))
              + "��ְ������δ���˵�ת��ҵ��,���ܵ���!");
        }
        bizDateim = dto.getBizDate();// �����ļ��е�����
        String temp_bizDateim = bizDateim.substring(0, 4)
            + bizDateim.substring(5, 7) + bizDateim.substring(8, 10);
        // �ۿ������Ƿ�����������ͬ
        /*
         * String temp_bizDate = bizDate.substring(0, 4) + "-" +
         * bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8); if
         * (!temp_bizDate.equals(bizDateim)) { throw new
         * BusinessException("�����ļ��е������������ڲ�һ�£�"); }
         */
        // ����aa410
        CollLoanbackHead collLoanbackHead = new CollLoanbackHead();
        collLoanbackHead.setBatchNum(dto.getBatchNum());
        collLoanbackHead.setLoanBankId(loanBankId);
        collLoanbackHead.setStatus("1");
        collLoanbackHead.setBizDate(temp_bizDateim);
        Serializable id = collLoanbackHeadDAO.insert(collLoanbackHead);
        headId = id.toString();
        // �жϸô����˺ŵ��������������д������PL003������Ϊ��A:����������������Ϊ1�в���ֵ�Ƿ�=1:���ۿ�
        paramValue = this.getBackMode(loanBankId);
        // �жϸð��´��Ƿ�ֻ��������PL009������3��0Ϊ�ǣ�1Ϊ��
        // System.out.println("paramValue="+paramValue);
        String officeCode = this.findOffice(loanBankId);
        Object[] paramObj = collLoanbackParaDAO.queryParamValueByOffice("3",
            officeCode);
        // �������ʣ��Ľ�PL009������1��
        // System.out.println("paramObj="+paramObj);
        Object[] restBalance = collLoanbackParaDAO.queryParamValueByOffice("1",
            officeCode);
        // �ж�����ȡ���Ƿ�Ҫ�������½ɴ�PL009������4��
        // System.out.println("restBalance="+restBalance);
        Object[] param4 = collLoanbackParaDAO.queryParamValueByOffice("4",
            officeCode);
        // �жϿɿ۹���������ʱ�Ƿ�ۿPL009������2��
        // System.out.println("param4="+param4);
        Object[] param2 = collLoanbackParaDAO.queryParamValueByOffice("2",
            officeCode);
        
        for (int i = 1; i < importList.size(); i++) {
          BankExportsDTO dto1 = (BankExportsDTO) importList.get(i);
          List empIdlist = fundloanInfoDAO.queryEmpIdByLoanKouAcc(dto1
              .getLoanKouAcc());
          // System.out.println("empIdlist="+empIdlist.size());
          // ��PL111����ͨ�������˺Ų�ѯ��ͬ���
          String contractId = collLoanbackTailDAO
              .queryContractIdByLoanKouAcc(dto1.getLoanKouAcc());
          if (contractId == null || contractId.equals("")) {
            if(dto1.getLoanKouAcc().equals("")){
              throw new BusinessException("������β�п�����ɾ�����ٵ���.");
            }
            throw new BusinessException("�ۿ��˺�Ϊ:" + dto1.getLoanKouAcc()
                + "�Ĵ����ͬ״̬�Ѿ����ǻ�����,���ܿۿ");
          }
          
          // ----------------------------------------------------------------------------------------------
          // ����ۿ��˺Ÿı�˵�����˵Ŀۿ����,��֮��ʱ��¼��������븨������˵Ŀۿ������Ӧ�����³�ʼ��.
          if (loankouacc == "" || !loankouacc.equals(dto1.getLoanKouAcc())) {
            kou_z = new BigDecimal(0.00);
            kou_f = new BigDecimal(0.00);
            loankouacc = dto1.getLoanKouAcc();
          }
          // -----------------------------------------------------------------------------------------------

          // Ӧ�۽��
          shouldMoney = dto1.getShouldMoney();
          // ѭ�����Ӧ�۽��
          temp_shouldMoney = shouldMoney;
          //
          String orgId = "";
          // ���pl400��û�м�¼
          if (empIdlist.size() == 0) {
            CollLoanbackTail collLoanbackTail = this.addCollLoanbackTailFull(
                headId, dto1.getLoanKouAcc(), contractId, "", orgId, "0", dto1
                    .getShouldMoney(), new BigDecimal(0), new BigDecimal(0),
                paramValue, dto1.getLoanKouYearmonth(), dto1.getBorrowerName());
            collLoanbackTailDAO.insert(collLoanbackTail);
          }
         
          for (int j = 0; j < empIdlist.size(); j++) {
            FundloanInfo fundloanInfo = (FundloanInfo) empIdlist.get(j);
            Object[] obj = new Object[2];
            obj = fundloanInfoDAO.queryByLoanKouAcc(fundloanInfo.getEmpId(),
                batchNum);
            if (obj != null) {
              String pickStatus = obj[0].toString();
              orgId = obj[1].toString();
              // �жϸ�ְ���Ƿ����δ���˵���ȡҵ��,���Ǿ�����һ����¼���뵽aa411��
              if (!pickStatus.equals("5")) {
                System.out.println("����ȡ��¼");
                CollLoanbackTail collLoanbackTail = this
                    .addCollLoanbackTailFull(headId, dto1.getLoanKouAcc(),
                        contractId, fundloanInfo.getEmpId(), orgId,
                        fundloanInfo.getSeq(), dto1.getShouldMoney(),
                        new BigDecimal(0), new BigDecimal(0), paramValue, dto1
                            .getLoanKouYearmonth(), fundloanInfo.getEmpName());
                collLoanbackTailDAO.insert(collLoanbackTail);
                continue;
              }
            }
            Object[] balanceObj = collLoanbackTailDAO.queryBalanceByEmpId(
                fundloanInfo.getEmpId(), fundloanInfo.getOrgId());// ͨ��empid��aa002���в�ѯ��Ӧ����
            orgId = fundloanInfo.getOrgId();
            // �õ��½ɴ��
            BigDecimal monthPay = new BigDecimal(0);

            if (balanceObj[5] != null && balanceObj[5].toString() != "") {

              monthPay = new BigDecimal(balanceObj[5].toString());
            } else {

              monthPay = new BigDecimal(balanceObj[2].toString());
            }

            // ����������
            BigDecimal rest = new BigDecimal(0.00);
            if (restBalance[0].toString().equals("A")) {
              rest = new BigDecimal(restBalance[1].toString());
            } else if (restBalance[0].toString().equals("B")) {
              // �õ��»���Ϣ
              BigDecimal corpusInterest = new BigDecimal(collLoanbackTailDAO
                  .queryCorpusInterestByContractId(contractId).toString());
              rest = new BigDecimal(restBalance[1].toString()).multiply(
                  corpusInterest).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (restBalance[0].toString().equals("C")) {
              rest = new BigDecimal(restBalance[1].toString()).multiply(
                  monthPay).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            // --------------------------------------------------------------------------
            // �ѿۿ�����Ԥ�ۿ�����ڱ��������
            if (fundloanInfo.getSeq().equals("0")) {
              rest = rest.add(kou_z);
            } else {
              rest = rest.add(kou_f);
            }

            // ---------------------------------------------------------------------------
            if (paramObj[0].toString().equals("1")) {
              // �ð��´����������ͱ������
              // �õ��������ͱ������
              BigDecimal balance = new BigDecimal(balanceObj[0].toString());
              // �õ��������
              BigDecimal preBalance = new BigDecimal(balanceObj[1].toString());
              // �õ��������
              BigDecimal curBalance = new BigDecimal(balanceObj[3].toString());
              // �õ�һ��������ȡ�Ľ�������-ʣ���
              System.out.println("rest..."+rest);
              System.out.println("balance..."+balance);
              if (rest.compareTo(balance) == 1) {
                CollLoanbackTail collLoanbackTail = this
                    .addCollLoanbackTailFull(headId, dto1.getLoanKouAcc(),
                        contractId, fundloanInfo.getEmpId(), orgId,
                        fundloanInfo.getSeq(), dto1.getShouldMoney(),
                        new BigDecimal(0), new BigDecimal(0), paramValue, dto1
                            .getLoanKouYearmonth(), fundloanInfo.getEmpName());

                collLoanbackTailDAO.insert(collLoanbackTail);
              } else {
                BigDecimal balanceInUse = balance.subtract(rest);
                if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
                  // ���ۿ�
                  if (param4[0].toString().equals("0")) {
                    // ����ȡ������½ɴ��
                    if (dto1.getShouldMoney().compareTo(monthPay) == 1) {
                      // Ӧ�۽������½ɴ��
                      CollLoanbackTail collLoanbackTail = this
                          .addCollLoanbackTailFull(headId,
                              dto1.getLoanKouAcc(), contractId, fundloanInfo
                                  .getEmpId(), orgId, fundloanInfo.getSeq(),
                              dto1.getShouldMoney(), new BigDecimal(0),
                              new BigDecimal(0), paramValue, dto1
                                  .getLoanKouYearmonth(), fundloanInfo
                                  .getEmpName());

                      collLoanbackTailDAO.insert(collLoanbackTail);
                      break;
                    }
                  }
                }
                BigDecimal realPreBalance = new BigDecimal(0.00);// �����۵�������
                BigDecimal realCurBalance = new BigDecimal(0.00);// �����۵ı�����
                if (param2[0].toString().equals("1")
                    && (dto1.getShouldMoney().compareTo(balanceInUse) == 1)) {
                  // �ɿ۹���������ʱ�����Կۿ�
                  CollLoanbackTail collLoanbackTail = this
                      .addCollLoanbackTailFull(headId, dto1.getLoanKouAcc(),
                          contractId, fundloanInfo.getEmpId(), orgId,
                          fundloanInfo.getSeq(), dto1.getShouldMoney(),
                          new BigDecimal(0), new BigDecimal(0), paramValue,
                          dto1.getLoanKouYearmonth(), fundloanInfo.getEmpName());

                  collLoanbackTailDAO.insert(collLoanbackTail);
                } else {
                  // �ɿ۹���������ʱ���Կۿ�
                  // if (param2[1].toString().equals("0")) {
                  // ���Թ���
                  if (temp_shouldMoney.compareTo(balanceInUse) == 1) {
                    // Ӧ�ɽ��>����ȡ���
                    if (curBalance.compareTo(rest) == 1) {
                      // �������>�������
                      realCurBalance = curBalance.subtract(rest);
                      realPreBalance = preBalance;
                    } else {
                      // �������<�������
                      realCurBalance = new BigDecimal(0.00);
                      realPreBalance = balance.subtract(rest);
                    }
                  } else {
                    // Ӧ�ɽ��<����ȡ���
                    if (preBalance.compareTo(temp_shouldMoney) == 1) {
                      // �������>Ӧ�ɽ��
                      realCurBalance = new BigDecimal(0.00);
                      realPreBalance = temp_shouldMoney;
                    } else {
                      // �������<Ӧ�ɽ��
                      realCurBalance = temp_shouldMoney.subtract(preBalance);
                      realPreBalance = preBalance;
                    }
                  }
                  CollLoanbackTail collLoanbackTail = this
                      .addCollLoanbackTailFull(headId, dto1.getLoanKouAcc(),
                          contractId, fundloanInfo.getEmpId(), orgId,
                          fundloanInfo.getSeq(), dto1.getShouldMoney(),
                          realPreBalance, realCurBalance, paramValue, dto1
                              .getLoanKouYearmonth(), fundloanInfo.getEmpName());

                  collLoanbackTailDAO.insert(collLoanbackTail);
                  temp_shouldMoney = temp_shouldMoney.subtract(realPreBalance)
                      .subtract(realCurBalance);
                }
              }
            }

          }
        }
      }
    } else {
      throw new BusinessException("�����ļ�Ϊ�գ�");
    }
  }

  /**
   * ����һ����¼����׼������AA411�У�
   * 
   * @param headId ͷ��id
   * @param loanKouAcc �����˺�
   * @param contractId ��ͬ���
   * @param empId ְ�����
   * @param orgId ��λ���
   * @param seq ����˳��
   * @param shouldMoney Ӧ�۽��
   * @param preCorpus �ɿ�������
   * @param currentCorpus �ɿ۱�����
   * @param paramValue �ж������ۿ��ȫ��ۿ�
   * @param yearMonth ��������
   * @throws Exception return CollLoanbackTail
   */

  public CollLoanbackTail addCollLoanbackTailFull(String headId,
      String loanKouAcc, String contractId, String empId, String orgId,
      String seq, BigDecimal shouldMoney, BigDecimal preCorpus,
      BigDecimal currentCorpus, String paramValue, String yearMonth, String name)
      throws Exception {

    CollLoanbackTail collLoanbackTail = new CollLoanbackTail();
    collLoanbackTail.setHeadId(headId);
    collLoanbackTail.setLoanKouAcc(loanKouAcc);
    collLoanbackTail.setContractId(contractId);
    collLoanbackTail.setEmpId(empId);
    collLoanbackTail.setOrgId(orgId);
    collLoanbackTail.setReserveaA(name);
    collLoanbackTail.setShouldCorpus(shouldMoney);
    // ----------------------------------------------------------------------------
    // ���ۿ��˺Ÿı�ʱ,���´򿪿��ܱ����� falg_kou �Ŀۿ��ʶ, �����¿ۿ��˺Ÿ���ȫ�ֱ���.
    if (LoanKouAcc_kou_ == "" || !LoanKouAcc_kou_.equals(loanKouAcc)) {
      falg_kou = true;
      LoanKouAcc_kou_ = loanKouAcc;
      this.per_z = new BigDecimal(0.00);
      this.cur_z = new BigDecimal(0.00);
      falg_kou_z = true;
      falg_kou_f = true;
    //  System.out.println("����ϴ��,�µĿۿ��˺�:" + LoanKouAcc_kou_);
    }
    if(contract_yg.equals(contractId) && yearmonth_yg.equals(yearMonth)){
      falg_kou_f=true;
      falg_kou=true;
    }
    // -----------------------------------------------------------------------------
    if (falg_kou) {

      if (seq.equals("0")) {
        if(falg_kou_z){
        this.per_z = preCorpus;
        this.cur_z = currentCorpus;
        this.LoanKouAcc_=loanKouAcc;
        collLoanbackTail.setPreCorpus(preCorpus);
        collLoanbackTail.setCurrentCorpus(currentCorpus);
//--------------------------------------------------------------------------
        // ��¼������˿ۿ���.
        kou_z = kou_z.add(preCorpus).add(currentCorpus);
      //  System.out.println("������˿ۿ��ϼ�:" + kou_z+"--------���οۿ��:"+preCorpus.add(currentCorpus));

        if (preCorpus.intValue() > 0 || currentCorpus.intValue() > 0) {
          falg_kou_z = true;
          falg_kou_f = true;
        //  System.out.println("����˿ۿ�,����.");
        } else {
          falg_kou_z = false;
       //   System.out.println("�����������.");
        }
//----------------------------------------------------------------------------
       }else{
       //  System.out.println("����������ۿ�ģʽ");
         collLoanbackTail.setPreCorpus(new BigDecimal(0.00));
         collLoanbackTail.setCurrentCorpus(new BigDecimal(0.00));
       }
     }else {
       if(falg_kou_f){
        if ((this.per_z.intValue() > 0 || this.cur_z.intValue() > 0)
            && !seq.equals("0")&&this.LoanKouAcc_.equals(loanKouAcc)) {
          collLoanbackTail.setPreCorpus(new BigDecimal(0.00));
          collLoanbackTail.setCurrentCorpus(new BigDecimal(0.00));
        } else {
          collLoanbackTail.setPreCorpus(preCorpus);
          collLoanbackTail.setCurrentCorpus(currentCorpus);
          //----------------------------------------------------------------------------       
          // ��¼��������˿ۿ���.
          kou_f = kou_f.add(preCorpus).add(currentCorpus);
        //  System.out.println("��������˿ۿ��:" + kou_f+"-------���οۿ��:"+preCorpus.add(currentCorpus));
          
          if (preCorpus.intValue() > 0 || currentCorpus.intValue() > 0) {
            falg_kou_z = true;
            falg_kou_f = true;
         //   System.out.println("��������˿ۿ�,����.");
          } else {
            falg_kou_f = false;
        //    System.out.println("�������������.");
          }
//  -----------------------------------------------------------------------------
        }

       }else{
         //System.out.println("���븨������ۿ�ģʽ");
         collLoanbackTail.setPreCorpus(new BigDecimal(0.00));
         collLoanbackTail.setCurrentCorpus(new BigDecimal(0.00));
       }
     }

      // ----------------------------------------------------------------------

      // ˵���������û������,�����������˿ۿ�Ϊ0ʱ, �ۿ�����д˿ۿ��˺��µ�ʣ���·�(���ڴ��·ݵ�)Ҳ��Ӧ���ٿۿ���.
      if (!falg_kou_z && !falg_kou_f) {
        falg_kou = false;
      //  System.out.println("����˫������.");
      }
      // ---------------------------------------------------------------------

    } else {
      // ����˿ۿ��˺�����һ�·ݲ��ܿۿ�,֮����·ֲ��ٿ�.
      collLoanbackTail.setPreCorpus(new BigDecimal(0.00));
      collLoanbackTail.setCurrentCorpus(new BigDecimal(0.00));
    }

    if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
      // ���ۿ�
      collLoanbackTail.setFlag("0");
      collLoanbackTail.setYearMonth(yearMonth);
    }
    if (collLoanbackTail.getPreCorpus().intValue() == 0 && collLoanbackTail.getCurrentCorpus().intValue() == 0) {
      collLoanbackTail.setCollflag("0");
    } else {
      collLoanbackTail.setCollflag("1");
    }
    contract_yg=contractId;
    yearmonth_yg=yearMonth;
    return collLoanbackTail;
  }

  /**
   * ȡ���������� 2007.12.20
   * 
   * @param pagination
   * @param securityInfo
   * @return
   */
  public List findExportList(String loanBankId, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List exportlist = new ArrayList();
    String date = "";
    String batchNum = "";
    String headId = "";
    try {
      // ��ѯAA410�У��Ƿ���ڶ�Ӧ������״̬=1�����룩or =2���ۿor =3(����)�ļ�¼
      List list = collLoanbackHeadDAO.queryStatusByOffice(loanBankId, "");
      if (list.size() == 0) {
        throw new BusinessException("�������²����ڻ�����Ϣ��");
      } else {
        // for (int i = 0; i < list.size(); i++) {
        CollLoanbackHead collLoanbackHead = (CollLoanbackHead) list.get(0);
        List picklist = collLoanbackHeadDAO
            .queryStatusByBatchNum(collLoanbackHead.getBatchNum());
        if (picklist.size() > 0) {
          Iterator it = picklist.iterator();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            if (!obj[1].toString().equals("5")) {
              throw new BusinessException("����δ����ҵ��");
            }
          }
        } else {
          throw new BusinessException("����δ����ҵ��");
        }
        date = collLoanbackHead.getBizDate();
        date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
            + date.substring(6, 8);
        batchNum = collLoanbackHead.getBatchNum();
        headId = collLoanbackHead.getId().toString();
        // }
      }
      String paramValue = this.getBackMode(loanBankId);
      if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
        // ���ۿ�
        list = collLoanbackTailDAO.queryExportFlowTail(headId);
        if (!list.isEmpty()) {
          Vector vectorHead = new Vector();
          vectorHead.add(date.toString());
          vectorHead.add(loanBankId.toString());
          vectorHead.add(batchNum);
          vectorHead.add("|");
          vectorHead.add("");
          vectorHead.add("");
          exportlist.add(vectorHead);
          for (int i = 0; i < list.size(); i++) {
            Vector vector = new Vector();
            BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO) list.get(i);
            vector.add(dto2.getLoanKouAcc().toString());
            vector.add(dto2.getBorrowerName().toString());
            vector.add(dto2.getLoanKouYearmonth().toString());
            vector.add(dto2.getShouldCorpus().toString());
            if (dto2.getRealMoney().equals(dto2.getShouldCorpus())) {
              vector.add("1");
            } else {
              vector.add("0");
            }
            vector.add(dto2.getRealMoney().toString());
            exportlist.add(vector);
            HafOperateLog hafOperateLog = new HafOperateLog();
            // ����BA003
            hafOperateLog.setOpSys(new Integer(
                BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
            hafOperateLog.setOpModel(""
                + BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
            hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_EXP);
            hafOperateLog.setOpBizId(new Integer("0"));
            hafOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
            hafOperateLog.setOrgId(new Integer(dto2.getOrgId()));
            hafOperateLog.setOpTime(new Date());
            hafOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
            hafOperateLogDAO.insert(hafOperateLog);
          }
        }
        String str[]=collLoanbackTailDAO.queryCollLoanByInfo(headId);
        Vector vectorEnd = new Vector();
        vectorEnd.add("�����ϼƣ�" + str[0].toString()
            + "   ");
        vectorEnd.add("�����ϼƣ�" + str[1] + "   ");
        vectorEnd.add("���ϼƣ�" + str[2] + "   ");
       
        vectorEnd.add("");
        vectorEnd.add("");
        vectorEnd.add("");
        exportlist.add(vectorEnd);
        
      } else {
        // ȫ��ۿ�
        list = collLoanbackTailDAO.queryExportFlowTail(headId);
        if (!list.isEmpty()) {
          Vector vectorHead = new Vector();
          vectorHead.add(date.toString());
          vectorHead.add(loanBankId.toString());
          vectorHead.add(batchNum);
          vectorHead.add("|");
          vectorHead.add("|");
          vectorHead.add("|");
          exportlist.add(vectorHead);
          for (int i = 0; i < list.size(); i++) {
            Vector vector = new Vector();
            BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO) list.get(i);
            vector.add(dto2.getLoanKouAcc().toString());
            vector.add(dto2.getBorrowerName().toString());
            vector.add(dto2.getShouldCorpus().toString());
            vector.add(dto2.getRealMoney().toString());
            exportlist.add(vector);
            HafOperateLog hafOperateLog = new HafOperateLog();
            // ����BA003
            hafOperateLog.setOpSys(new Integer(
                BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
            hafOperateLog.setOpModel(""
                + BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
            hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_EXP);
            hafOperateLog.setOpBizId(new Integer("0"));
            hafOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
            hafOperateLog.setOrgId(new Integer(dto2.getOrgId()));
            hafOperateLog.setOpTime(new Date());
            hafOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
            hafOperateLogDAO.insert(hafOperateLog);
          }
        }
      }
      // �޸�AA410״̬=3��������
      if (!headId.equals("")) {
        CollLoanbackHead collLoanbackHead = collLoanbackHeadDAO
            .queryById(new Integer(headId));
        collLoanbackHead.setStatus("3");
      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("����ʧ�ܣ�");
    }
    return exportlist;
  }

  /**
   * ͨ�����в�ѯ���´�
   */
  public String findOffice(String bank) throws Exception, BusinessException {
    String officeCode = collBankDAO.getOfficeByBank(bank);
    return officeCode;
  }

  /**
   * �����´�����������
   */
  public List FindCollectionBankId(String officeCode, List collBankList)
      throws Exception, BusinessException {
    List returnList = collLoanbackHeadDAO.findCollectionBankId(officeCode,
        collBankList);
    return returnList;
  }

  /**
   * �õ��ۿʽ
   * 
   * @param loanBankId
   * @return
   */
  public String getBackMode(String loanBankId) throws Exception {
    String paramValue = "";
    try {
      paramValue = loanBankParaDAO.queryParamValue_LJ(new Integer(loanBankId),
          "A", "1");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }

  /**
   * �����𻹴�--����ҳ��--�ۿ� 2007.12.21
   */
  public void kouMoney(String batchNum, Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    try {
      String headId = "";
      String office = (String) pagination.getQueryCriterions().get("office");
      if (office == null) {
        String bank = (String) pagination.getQueryCriterions().get("bank");
        office = this.findOffice(bank);
      }
      String docNum = docNumMaintainDAO.getDocNumdocNum(office, securityInfo
          .getUserInfo().getBizDate().substring(0, 6));
      Map office1 = securityInfo.getOfficeInnerCodeMap();
      String officecode = office1.get(office).toString();
      if (officecode.length() == 1) {
        officecode = "0" + officecode;
      }
      docNum=officecode+securityInfo
      .getUserInfo().getBizDate().substring(0, 6)+docNum;
      List list = collLoanbackTailDAO.queryByBatchNum(batchNum);
      if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          CollLoanbackTail collLoanbackTail1 = (CollLoanbackTail) list.get(i);
          String orgId = collLoanbackTail1.getOrgId();
          String empId = collLoanbackTail1.getEmpId();
          headId = collLoanbackTail1.getHeadId();
          // ����aa306
          PickHead pickHead = new PickHead();
          pickHead.setOrg(orgDAO.queryById(new Integer(orgId)));
          pickHead.setNoteNum(batchNum);
          pickHead.setDocNum(docNum);
          pickHead.setPickSatatus(new BigDecimal("3"));
          pickHead.setBatchnum(batchNum);
          pickHead.setSettDate(securityInfo.getUserInfo().getBizDate());
          String pickHeadId = pickHeadDAO.insert(pickHead).toString();
          // ����aa307
          PickTail pickTail = new PickTail();
          pickTail.setEmpId(new Integer(empId));
          pickTail.setPickType(new BigDecimal("1"));
          pickTail.setPickPreBalance(collLoanbackTail1.getPreCorpus());
          pickTail.setPickCurBalance(collLoanbackTail1.getCurrentCorpus());
          pickTail.setPickCurInterest(new BigDecimal("0"));
          pickTail.setPickPreInterest(new BigDecimal("0"));
          pickTail.setPickReason(new BigDecimal(BusiConst.GIVEMONEYBYMON));// ������ȡ�еĻ���
          pickTail.setPickHead(pickHeadDAO.queryById(new Integer(pickHeadId)));
          pickTail.setLoankouacc(collLoanbackTail1.getLoanKouAcc());
          pickTail.setContractid(collLoanbackTail1.getContractId());
          pickTail.setSpecialPick(null);
          pickTailDAO.insert(pickTail);
          // ����aa101
          Org org = orgDAO.queryById(new Integer(orgId));
          OrgHAFAccountFlowDrawing orgHAFAccountFlowDrawing = new OrgHAFAccountFlowDrawing();
          orgHAFAccountFlowDrawing.setOrg(orgDAO.queryById(new Integer(orgId)));
          orgHAFAccountFlowDrawing.setDocNum(docNum);
          orgHAFAccountFlowDrawing.setDebit(collLoanbackTail1.getPreCorpus()
              .add(collLoanbackTail1.getCurrentCorpus()));
          orgHAFAccountFlowDrawing.setCredit(new BigDecimal("0"));
          orgHAFAccountFlowDrawing.setInterest(new BigDecimal("0"));
          orgHAFAccountFlowDrawing.setBizId(new BigDecimal(pickHeadId));
          orgHAFAccountFlowDrawing.setBizStatus(new BigDecimal("3"));
          orgHAFAccountFlowDrawing.setNoteNum(batchNum);
          orgHAFAccountFlowDrawing.setIsFinance(new BigDecimal("2"));
          orgHAFAccountFlowDrawing.setSpecailType("2");
          orgHAFAccountFlowDrawing.setSettDate(securityInfo.getUserInfo()
              .getBizDate());
          orgHAFAccountFlowDrawing.setReserveaA(securityInfo.getUserInfo()
              .getUsername());
          orgHAFAccountFlowDrawing.setSummary(BusiTools.getBusiValue(
              BusiConst.PICKUPTYPE_1, BusiConst.PICKUPTYPE));
          // ����������ʼ
          orgHAFAccountFlowDrawing.setOfficeCode(org.getOrgInfo()
              .getOfficecode());
          orgHAFAccountFlowDrawing.setMoneyBank(org.getOrgInfo()
              .getCollectionBankId());
          orgHAFAccountFlowDrawing.setPersonTotal(new Integer(1));
          // ����
          Serializable flowId = orgHAFAccountFlowDrawingDAO
              .insert(orgHAFAccountFlowDrawing);
          // ����aa102
          EmpHAFAccountFlow empHAFAccountFlow = new EmpHAFAccountFlow();
          empHAFAccountFlow.setEmpId(new Integer(empId));
          empHAFAccountFlow.setOrgHAFAccountFlow(orgHAFAccountFlowDAO
              .queryById(new Integer(flowId.toString())));
          empHAFAccountFlow.setDebit(collLoanbackTail1.getPreCorpus().add(
              collLoanbackTail1.getCurrentCorpus()));
          empHAFAccountFlow.setCredit(new BigDecimal("0"));
          empHAFAccountFlow.setInterest(new BigDecimal("0"));
          empHAFAccountFlow.setSummary(BusiTools.getBusiValue(
              BusiConst.PICKUPTYPE_1, BusiConst.PICKUPTYPE));
          empHAFAccountFlowDAO.insert(empHAFAccountFlow);
          // ����aa319
          PickBizActivityLog biz = new PickBizActivityLog();// ����aa319Log
          biz.setBizid(new Integer(pickHeadId));
          biz.setAction(new Integer(3));
          biz.setOpTime(new Date(new java.util.Date().getTime()));
          biz.setOperator(securityInfo.getUserInfo().getUsername());
          biz.setTypes("D");
          pickBizActivityLogDAO.insert(biz);
          // ����ba003
          HafOperateLog hafOperateLog = new HafOperateLog();
          hafOperateLog.setOpSys(new Integer(
              BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
          hafOperateLog.setOpModel(""
              + BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
          hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
          hafOperateLog.setOpBizId(new Integer(pickHeadId));
          hafOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
          hafOperateLog.setOrgId(new Integer(collLoanbackTail1.getOrgId()));
          hafOperateLog.setOpTime(new Date());
          hafOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
          hafOperateLogDAO.insert(hafOperateLog);
        }
      }
      // �޸�AA410״̬=2���ۿ
      if (!headId.equals("")) {
        CollLoanbackHead collLoanbackHead = collLoanbackHeadDAO
            .queryById(new Integer(headId));
        collLoanbackHead.setStatus("2");
      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * �����𻹴�--ά��ҳ��
   * 
   * @author ���ƽ 2007-12-22 ɾ��
   */
  public void collLoanbackDelete(String[] rowArray, SecurityInfo securityInfo)
      throws Exception {
    try {
      for (int i = 0; i < rowArray.length; i++) {
        CollLoanbackHead collLoanbackHead = collLoanbackHeadDAO
            .queryById(new Integer(rowArray[i]));
        if (collLoanbackHead == null) {
          continue;
        }
        List list = collLoanbackTailDAO
            .is_exist(collLoanbackHead.getBatchNum());
        if (list.size() > 0) {
          throw new BusinessException("�м�¼�����ˣ�ɾ��ʧ��!");
        }
        // ɾ��AA410��AA411
        collLoanbackTailDAO.deleteCollLoanback(rowArray[i]);
        // ͨ�����κŲ�ѯ306id
        List pickHeadIdlist = collLoanbackTailDAO.queryHeadId(collLoanbackHead
            .getBatchNum());
        for (int z = 0; z < pickHeadIdlist.size(); z++) {
          Object obj = (Object) pickHeadIdlist.get(z);
          String pickHeadId = obj.toString();
          // ɾ��aa307
          pickTailDAO.deleteTail(new Integer(pickHeadId));
          // ɾ��aa306
          pickHeadDAO.deleteById_LY(new Integer(pickHeadId));
          // ɾ��aa101,aa102
          collLoanbackTailDAO.deleteOrgFlowAndEmpFlow(pickHeadId);
          // ɾ��aa319
          collLoanbackTailDAO.deleteBizLog(pickHeadId);
          // ͨ��aa410��id��ѯaa411�������
          List list1 = collLoanbackTailDAO.queryByBatchNum(collLoanbackHead
              .getBatchNum());
          // ����ba003
          for (int j = 0; j < list1.size(); j++) {
            CollLoanbackTail collLoanbackTail = (CollLoanbackTail) list1.get(j);
            HafOperateLog hafOperateLog = new HafOperateLog();
            hafOperateLog.setOpSys(new Integer(
                BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
            hafOperateLog.setOpModel(""
                + BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK);
            hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
            hafOperateLog.setOpBizId(new Integer(pickHeadId));
            hafOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
            hafOperateLog.setOrgId(new Integer(collLoanbackTail.getOrgId()));
            hafOperateLog.setOpTime(new Date());
            hafOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
            hafOperateLogDAO.insert(hafOperateLog);
          }
        }

      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("ɾ��ʧ��!");
    }
  }

  public List findCollLoanbackTcList(Pagination pagination) throws Exception {
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String empId = (String) pagination.getQueryCriterions().get("empId");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String empName = (String) pagination.getQueryCriterions().get("empName");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String bizDate = (String) pagination.getQueryCriterions().get("bizDate");
    String officeName = (String) pagination.getQueryCriterions().get(
        "officeName");
    String bankName = (String) pagination.getQueryCriterions().get("bankName");

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    List listInfo = new ArrayList();
    listInfo = collLoanbackTailDAO.queryCollLoanbackTcList(officeName,
        bankName, orgId, empId, orgName, empName, contractId, bizDate, orderBy,
        order, start, pageSize, page);

    // List listCount = new ArrayList();
    // listCount = collLoanbackTailDAO.queryCollLoanbackTcCount(officeName,
    // bankName, orgId, empId, orgName, empName, contractId, bizDate, orderBy,
    // order, start, pageSize, page);
    // pagination.setNrOfElements(listCount.size());

    return listInfo;
  }

  public List findCollLoanbackTcCount(Pagination pagination) throws Exception {
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String empId = (String) pagination.getQueryCriterions().get("empId");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String empName = (String) pagination.getQueryCriterions().get("empName");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String bizDate = (String) pagination.getQueryCriterions().get("bizDate");
    String officeName = (String) pagination.getQueryCriterions().get(
        "officeName");
    String bankName = (String) pagination.getQueryCriterions().get("bankName");

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    //    
    // List listInfo = new ArrayList();
    // listInfo = collLoanbackTailDAO.queryCollLoanbackTcList(officeName,
    // bankName, orgId,
    // empId, orgName, empName, contractId, bizDate, orderBy, order, start,
    // pageSize, page);

    List listCount = new ArrayList();
    listCount = collLoanbackTailDAO.queryCollLoanbackTcCount(officeName,
        bankName, orgId, empId, orgName, empName, contractId, bizDate, orderBy,
        order, start, pageSize, page);
    pagination.setNrOfElements(listCount.size());

    return listCount;
  }

  public BigDecimal getCollLoanbackCheckMoney(List listCount) throws Exception {
    BigDecimal temp_sum = new BigDecimal("0.00");
    for (int i = 0; i < listCount.size(); i++) {
      CollLoanbackTcListDTO collLoanbackTcListDTO = (CollLoanbackTcListDTO) listCount
          .get(i);
      temp_sum = temp_sum.add(collLoanbackTcListDTO.getCheckMoney());
    }
    return temp_sum;
  }

  public void setCollLoanbackHeadDAO(CollLoanbackHeadDAO collLoanbackHeadDAO) {
    this.collLoanbackHeadDAO = collLoanbackHeadDAO;
  }

  public void setCollLoanbackTailDAO(CollLoanbackTailDAO collLoanbackTailDAO) {
    this.collLoanbackTailDAO = collLoanbackTailDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setFundloanInfoDAO(FundloanInfoDAO fundloanInfoDAO) {
    this.fundloanInfoDAO = fundloanInfoDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setCollLoanbackParaDAO(CollLoanbackParaDAO collLoanbackParaDAO) {
    this.collLoanbackParaDAO = collLoanbackParaDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setDocNumMaintainDAO(DocNumMaintainDAO docNumMaintainDAO) {
    this.docNumMaintainDAO = docNumMaintainDAO;
  }

  public void setPickHeadDAO(PickHeadDAO pickHeadDAO) {
    this.pickHeadDAO = pickHeadDAO;
  }

  public void setPickTailDAO(PickTailDAO pickTailDAO) {
    this.pickTailDAO = pickTailDAO;
  }

  public void setPickBizActivityLogDAO(
      PickBizActivityLogDAO pickBizActivityLogDAO) {
    this.pickBizActivityLogDAO = pickBizActivityLogDAO;
  }

  public void setEmpHAFAccountFlowDAO(EmpHAFAccountFlowDAO empHAFAccountFlowDAO) {
    this.empHAFAccountFlowDAO = empHAFAccountFlowDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setOrgHAFAccountFlowDrawingDAO(
      OrgHAFAccountFlowDrawingDAO orgHAFAccountFlowDrawingDAO) {
    this.orgHAFAccountFlowDrawingDAO = orgHAFAccountFlowDrawingDAO;
  }

  public String getCollBankDate(String loanBankId) throws Exception {
    // TODO Auto-generated method stub
    return orgDAO.findAA103_DayTime(loanBankId);
  }
}
