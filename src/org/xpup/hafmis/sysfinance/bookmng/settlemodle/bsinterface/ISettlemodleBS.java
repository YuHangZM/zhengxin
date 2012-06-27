package org.xpup.hafmis.sysfinance.bookmng.settlemodle.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.settlemodle.dto.SettlemodleDTO;

/**
 * Copy Right Information   : ���㷽ʽ
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-24-2007
 */
public interface ISettlemodleBS {
  //���ز�ѯ���(List) ���㷽ʽ��Ϣ
  public List findSettlemodleList(Pagination pagination,String bookId)throws Exception;
  //���ز�ѯ�����¼��
  public int querySettlemodleCount(String bookId)throws Exception;
  //�ж�����Ľ��㷽ʽ��FN102.PARAM_NUM=3�ļ�¼��PARAM_EXPLAIN�Ƿ����(���ڲ���)
  public boolean is_SettlemodleParamExplainInsert(SettlemodleDTO settlemodleDTO)throws Exception;
  //�ж�����Ľ��㷽ʽ��FN102.PARAM_NUM=3�ļ�¼��PARAM_EXPLAIN�Ƿ����(�޸���)
  public boolean is_SettlemodleParamExplainUpdate(SettlemodleDTO settlemodleDTO) throws Exception;
  //����FN102 PARAM_EXPLAIN=����Ľ��㷽ʽ ,����FN311
  public void updateSettlemodleInfo(SettlemodleDTO settlemodleDTO, SecurityInfo securityInfo) throws Exception;
  //���� FN102 FN311��
  public void insertSettlemodleInfo(SettlemodleDTO settlemodleDTO,SecurityInfo securityInfo)throws Exception;
  //����paraId �ж�FN102�����Ƿ��м�¼
  public boolean isSettlemodleById(String paraId) throws Exception;
  //����ID ��ѯ���㷽ʽ
  public SettlemodleDTO querySettlemodleParamExplainInfo(String paraId) throws Exception;
  //�жϸü�¼��FN102.PARA_ID��FN201.SETT_TYPE or FN210.SETT_TYPE���Ƿ����
  public boolean isSettlemodleByParamValue(String paraId,String bookId) throws Exception;
  //ɾ�� FN102���е� paraId ��¼  ����FN311��־
  public void deleteSettlemodleInfo(String paraId,SecurityInfo securityInfo) throws Exception;
}
