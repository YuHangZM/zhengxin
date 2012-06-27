package org.xpup.hafmis.sysloan.specialbiz.bailenrol.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTaPrintDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTaAF;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTbAF;

/**
 * @author ��Ұ 2007-10-02
 */
public interface IBailenRolBS {

  // ͨ����ͬ��Ŵ���ҳ����Ϣ
  public BailenRolTaAF queryContractInfo(String id, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ���뱣֤��Ǽ������Ϣ��ҳ����Ϣ��
  public BailenRolTaPrintDTO saveBailenRol(BailenRolTaDTO bailenRolTaDTO,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ��֤��Ǽ��б�
  public BailenRolTbAF queryBailenRolListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ɾ����֤��Ǽ�ά���б�����¼
  public void deleteBailenRolInfo(String flowHeadId, String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ��֤��Ǽ�ά���б��ӡ
  public List findBailenRolTbPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception;

  // ת����������
  public String changeBank(String loanBankId) throws Exception;

  // ͨ������ID�����ſ������˺�
  public String queryBailenRolTaBankAccByBankId(String bankId) throws Exception;
}
