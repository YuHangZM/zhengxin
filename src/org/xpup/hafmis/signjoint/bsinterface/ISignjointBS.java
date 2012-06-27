package org.xpup.hafmis.signjoint.bsinterface;

import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.signjoint.dto.BaseInfoDTO;
import org.xpup.hafmis.signjoint.dto.LogDTO;
import org.xpup.hafmis.signjoint.dto.RequestSignDTO;
import org.xpup.hafmis.signjoint.dto.TempDTO;
import org.xpup.hafmis.signjoint.entity.Sign;


public interface ISignjointBS {
  //����ǩԼ��Ϣ
  public String saveSign(RequestSignDTO dto);
  //����Ψһ��ʶ��ѯ������ǩԼ����
  public Sign querySignBySignNum(String sign);
  //��ѯ���
  public String queryBalance(BaseInfoDTO dto);
  //��ѯ�����ϸ
  public String queryListBalance(BaseInfoDTO dto,String startdate,String enddate);
  //��ѯ�������
  public String queryBorrowBalance(BaseInfoDTO dto);
  //��ѯ���������ϸ
  public String queryBorrowListBalance(BaseInfoDTO dto,String startdate,String enddate);
  //ִ���ϱ����±�����ͬ��
  public void execSynProcdure();
//------------------------------------------------���¿�
  //����ǩԼ��Ϣ
  public String saveNewSign(RequestSignDTO dto);
  //��ѯ���
  public String queryNewBalance(BaseInfoDTO dto);
  //��ѯ�����ϸ
  public String queryNewListBalance(BaseInfoDTO dto,String startdate,String enddate);
  //��ѯ�������
  public String queryNewBorrowBalance(BaseInfoDTO dto);
  //��ѯ���������ϸ
  public String queryNewBorrowListBalance(BaseInfoDTO dto,String startdate,String enddate);
//------------------------------------------------���¿�
  
//------------------------------------------------����ǩԼ

  //�г����и���ҵ��Ա����Ϣ
  public List queryAllEmpInfo(Pagination pagination);
  //�������ı������������ݿ�
  public void signImpBatch(List headlist,List bodylist) throws BusinessException;
  //����Ա����ź����п��Ż���ݴ���е�Ա����Ϣ
  public RequestSignDTO getSingleEmpInfoByEmpidAndCardnum(String empid,String bankcardid);
  //����Ա����ź����п���ɾ���ݴ���е�Ա����Ϣ
  public void deleteUserInfo(String orgid,String empid,String bankcardid) throws BusinessException ;
  //ɾ�����ݴ���е�һ����λ�µ�����Ա��
  public void deleteAll(String orgid) throws BusinessException;
  //��ѯ���ݴ���иõ�λ�µ�����Ա����Ϣ,������
  public List queryEmpListAll(Pagination pagination) throws BusinessException;
  //���ݵ�λ��Ų�ѯ��λ����
  public String getOrgnameByOrgID(String orgid);
  //���û���Ϣ������ʱ��
  public void addUserInfo(TempDTO dto) throws BusinessException;
  //�޸��ݴ���е�ְ����Ϣ
  public void modifyUserInfo(TempDTO newdto,TempDTO olddto) throws BusinessException;
  //���ݵ�λ��ţ�ְ����ţ����п��ţ����ݴ���в�ѯԱ����Ϣ
  public TempDTO queryUserInfo(String orgid,String empid,String bankcardid) throws BusinessException;
  //��ҳ��ѯ������ʷ�������
  public List queryHistoryList(Pagination pagination) throws BusinessException;
  //��ҳ��ѯ�ļ���־
  public List queryLog(Pagination pagination) throws BusinessException;
  
  //�����ļ�ʱ����ѯ�����û���Ϣ
  public List getAllUserInfo();
  

  
  public List prepareSendFile();
  
  public void prepareReceiveFile(List list)  throws Exception;
  
  public void logFile(LogDTO dto)throws Exception;
  
  public void clearTemp();
  
//------------------------------------------------����ǩԼ
}
