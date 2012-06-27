package org.xpup.hafmis.sysloan.dataready.rate.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateNewAF;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateShowAF;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateUseAF;

public interface IRateBS {
  //����ά����ʾҳ��
  public RateShowAF findRateList(Pagination pagination)throws BusinessException;
  //����ά����ӷ���
  public void addRateInfo(RateNewAF rateNewAF,SecurityInfo securityInfo)throws BusinessException;
  //����ά�������޸ĵļ�¼
  public RateNewAF findRateInfo(String rateId)throws BusinessException;
  //����ά���޸ķ���
  public void updateRateInfo(RateNewAF rateNewAF,SecurityInfo securityInfo)throws BusinessException;
  //��������
  public void useRateInfo_sy(RateUseAF rateUseAF,SecurityInfo securityInfo)throws BusinessException;
  //����ȫ������
  public void useRateAll_sy(String appMode, SecurityInfo securityInfo)throws BusinessException;
  //�������������Ƿ���������
  public String checkUseInfo_sy(String rateId)throws BusinessException;
}
