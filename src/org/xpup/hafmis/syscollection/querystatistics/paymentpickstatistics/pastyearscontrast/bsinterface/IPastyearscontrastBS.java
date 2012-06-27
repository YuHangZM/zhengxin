package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.form.PastyearscontrasAF;

public interface IPastyearscontrastBS {
  //��ѯ
  public PastyearscontrasAF queryList(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
  //���ݰ��´��������ѯ�鼯����,���ڵ���������
  public PastyearscontrasAF fingTree(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
  //���ݰ��´���Ų�����
  public String findOfficeName(String officeId)throws Exception,BusinessException;
  //�������б�Ų�ѯ����
  public String findBankName(String bankId)throws Exception,BusinessException;
}
