package org.xpup.hafmis.syscollection.common.biz.clearinginterest;

import org.xpup.hafmis.syscollection.dto.ClearingInterestDTO;
import org.xpup.hafmis.syscollection.dto.InterestDto;
/**
 * @author ����
 * 2007-6-26
 * �÷�������ʵ�ּ���������Ϣ�Ľӿڣ�������Ϣ��������Ϣ��
 */
public interface ClearingInterestInterface {

  //����������Ϣ
  public InterestDto getClearinginterest(ClearingInterestDTO clearingInterestDTO);

}
