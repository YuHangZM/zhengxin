package org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface;

import java.nio.BufferUnderflowException;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAddAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF;

public interface ItranoutBS {
  public String queryTranOutHeadByTailId(String tailid) throws Exception;
  public void updateByTranOutHeadId(String headid) throws BusinessException, Exception;
  // ����������ѯdemo��¼
  // public List findtranoutByCriterions(String id) throws
  // Exception,BusinessException;
  public Org fingInOrgInfo(String orgid)throws BusinessException;
  // Ajax ��ѯת����λ����
  public TranAF findtranoutOrgName(String id, Pagination pagination,SecurityInfo securityInfo)throws Exception, BusinessException;
  // ��ѯ��λ��Ϣ
  public Org fingOrgInfo(String orgid,SecurityInfo securityInfo) throws BusinessException ;

  // ����ְ����Ų�ѯְ����Ϣ
  public TranAddAF findEmpInfo(String empid,String orgid,SecurityInfo securityInfo) throws Exception,BusinessException;

  // ɾ��������¼
  public void deleteTailInfo(String tailid, SecurityInfo securityInfo) throws Exception, BusinessException ;
     
  // ȫ��ɾ��
  public void deleteAll(List taillist, SecurityInfo securityInfo)throws Exception, BusinessException;

  // ת��ά������ѯ�б�
  public List queryOrgInfoTb(String outOrgid, String inOrgid,
      Pagination pagination) throws Exception, BufferUnderflowException;

  // ��ѯƱ�ݱ��
  public List FindNot_num(String orgid) throws Exception;
  //���ݵ�λ�����ˮID��ҵ�����Ͳ�ѯ�鼯��������
  public String queryCollBankName(String orgId,String bizId, String bizType, 
      SecurityInfo securityInfo) throws Exception;

  // ���:����Ա����Ϣ
  public void addTranTail(TranAddAF tranAddAF,SecurityInfo securityInfo) throws BusinessException,Exception;
  
  // ͨ��ת����λ��Ų�ѯ 3-9 Pk id
  public List FindOutPkid(String outOrgid) throws Exception, BusinessException;

  // ��ȡƾ֤��
  public String GetOfficeCode(String orgid) throws Exception;
  public String GetOfficeCode_yg(String orgid) throws Exception;
  // ���ת����ѯ�ܽ��
// public BigDecimal FindSumSal(String orgid) throws Exception;

  //����ת��-- ���ת�� ���� AA101
  public String updateOrgHafaccountFlow(String pkid,String docNum,String noteNum,SecurityInfo securityInfo)
  throws Exception ;
  
  //ת��ά���������ת��
  public void updateOrgHafaccountFlowTb(String pkid, String docNum,SecurityInfo securityInfo) throws Exception ;

  // ת��ά��--Ĭ�ϲ�ѯ
  public TranTbAF findTranListBydefaultWZQ(Pagination pagination,SecurityInfo securityInfos) throws Exception, BusinessException;
  public TranTbAF findTranListBydefaultWZQ_yg(Pagination pagination,SecurityInfo securityInfos) throws Exception, BusinessException;
  public String findCollBank(String collBankid) throws Exception, BusinessException ;
  // ת��ά��--ɾ����λ��Ϣ
  public void  DeleteOrg(String pkid,String ip,String username,String BizDate, SecurityInfo securityInfo)throws Exception,BufferUnderflowException ;
  
  // ת��ά�������޸�
  public TranOutHead FindOrgTb(String pkid)throws Exception ;
  
  // ת��ά��--����ת��
  public void UpdateTranHead(String pkid,String username,String ip,String setDates) throws Exception;
  public String queryMakerPara() throws Exception;
  //ת��ά��--���ת��
  public String GetOfficeCodeTb(String Headpkid) throws Exception;
  public String GetOfficeCodeTb_yg(String Headpkid) throws Exception;
  public String GetBankName_yg(String bankid) throws Exception;
  public String FindAA103_DayTime(String collbankid) throws Exception;
  public String queryNoteNum() throws Exception;
  // ��������
  public List findPaylistBatch(Pagination pagination) throws Exception;
  


  
  // ��������
  public List addTranoutListBatch(List addtranoutHeadImportList,List addtranoutListImportList,
      String orgOutid,String orgOutName,String orgInId,String orgInName,
      String noteNum,String ip,String name,String date,SecurityInfo securityInfo)
      throws BusinessException,Exception;
  public List getTranTailListPrint(String headid);
  //ƾ֤��ӡ(�״�)
  public TranTbPrintAF printCredence(String headid) throws BusinessException,Exception;
  //������ӡ
  public List printAll(Pagination pagination,SecurityInfo securityInfo) throws BusinessException,Exception;
  
  public TranAF findTranOutInfoMX(Pagination pagination)throws Exception;
  public List findTranOutInfoMXPrint(String headid)throws Exception;
  public void updateTranHeadTranIn(String pkid, String username, String ip,
      String setDates) throws Exception ;
  public boolean findAdjustWrongFAccountByOrgid(String orgid ,SecurityInfo securityInfo);
  public boolean check(String orgID);
  public String findtraninEmpInfo(String name,String cardNum,String empid, String orgid)throws Exception,BusinessException;
  public void referringDate(String pkid, String ip, String userName, String dates, SecurityInfo securityInfo, String temp_P) throws BusinessException;
  public void pprovalDate(String pkid, String ip, String userName, String dates, SecurityInfo securityInfo, String temp_P) throws BusinessException;
  public void pickUpData(String outOrgId, SecurityInfo securityInfo)throws BusinessException;
  public String[] queryOfficeBankNames(String orgId, String openStatus,
      String bizId, String bizType, SecurityInfo securityInfo) throws Exception ;
  
  // ��ѯת����Ӧת���Ѿ����ʵ��б�
  public List queryTransInIsFiveList(SecurityInfo securityInfo) throws Exception, BufferUnderflowException;
}
















