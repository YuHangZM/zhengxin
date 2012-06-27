package org.xpup.hafmis.sysloan.dataready.bank.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.bank.dto.BankAFDTO;

public interface IBankBS {
  /**
   * name ����
   * ����׼��:����ά��--��ʾ�б�
   * return list
   */
  public List findBankList(Pagination pagination) throws Exception, BusinessException;
  /**
   * name ����
   * �ж�������Ϣ�� �Ƿ��Ѿ����� ���´� ����ID��ͬ�ļ�¼ (**���и�**)
   * return boolean
   */
  public boolean isCheckBank(BankAFDTO bankAFDTO)throws Exception; 
  /**
   * name ����
   * ���������� ������Ϣ�� (**���и�**)
   */
  public void insertBank(BankAFDTO bankAFDTO,SecurityInfo securityInfo)throws Exception;
  /**
   * name ����
   * ͨ��������Ϣ�� ��������
   */
  public BankAFDTO queryBank(String id)throws Exception;
  
  /**
   * name ����
   * ����idɾ��������Ϣ���¼
   */
  public String deleteBank_YM(Integer id,SecurityInfo securityInfo)throws Exception;
  /**
   * name ����
   * ������Ϣ�� ���ù���
   */
  public String useBank_YM(Integer id,SecurityInfo securityInfo)throws Exception;
  /**
   * name ����
   * ���ݰ��´�,���� ��ô�����¼ (**���и�**)
   */
  public void updateBank_YM(BankAFDTO bankAFDTO,SecurityInfo securityInfo)throws Exception;
  /**
   * �õ����´��µĹ鼯����
   * @param office
   * @return CollBankList
   */
  public List queryCollBank(String office);
  /**
   * name ����
   *����id �ж��Ƿ��м�¼
   */
  public boolean is_bank_YM(Integer id);
  /**
   * �����������  ����
   * @return
   */
  public List getCollBankList();
}
