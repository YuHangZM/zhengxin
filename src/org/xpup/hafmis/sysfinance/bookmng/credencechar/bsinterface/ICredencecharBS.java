package org.xpup.hafmis.sysfinance.bookmng.credencechar.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.credencechar.dto.CredencecharDTO;

/**
 * Copy Right Information   : ƾ֤��
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-22-2007
 */
public interface ICredencecharBS {
  //���ز�ѯ���(List) ƾ֤����Ϣ
  public List findCredencecharList(Pagination pagination,String bookId)throws Exception;
  //���ز�ѯ�����¼��
  public int queryCredencecharCount(String bookId)throws Exception;
  //�ж������ƾ֤����FN102.PARAM_NUM=2�ļ�¼��PARAM_EXPLAIN�Ƿ����(���ڲ���)
  public boolean is_CredencecharParamExplainInsert(CredencecharDTO credencecharDTO)throws Exception;
  //�ж������ƾ֤����FN102.PARAM_NUM=2�ļ�¼��PARAM_EXPLAIN�Ƿ����(�����޸�)
  public boolean is_CredencecharParamExplainUpdate(CredencecharDTO credencecharDTO)throws Exception;
  //�����FN311��FN102
  public void insertCredencecharInfo(CredencecharDTO credencecharDTO,SecurityInfo securityInfo)throws Exception;
  //����ID ��ѯ��ƾ֤�� �� ƾ֤������
  public CredencecharDTO queryCredencecharParamExplainInfo(String paraId)throws Exception;
  //���� FN102 ����FN311
  public void updateCredencecharInfo(CredencecharDTO credencecharDTO,SecurityInfo securityInfo)throws Exception;
  //�ж�FN102�Ƿ� �д���PARAID �ļ�¼
  public boolean isCredencecharById(String paraId)throws Exception;
  //�жϸü�¼��FN102.paraId��FN201.CREDENCE_CHARACTER or FN210.CREDENCE_CHARACTER���Ƿ����
  public boolean isCredencecharByParamValue(final String paraId,String bookId)throws Exception;
  //ɾ�� FN102���е� paraId ��¼    ����FN311��־
  public void deleteCredencecharInfo(String paraId,SecurityInfo securityInfo) throws Exception;
}
