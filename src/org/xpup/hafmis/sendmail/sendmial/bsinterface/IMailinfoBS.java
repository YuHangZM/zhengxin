package org.xpup.hafmis.sendmail.sendmial.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sendmail.sendmial.form.MailinfoAF;

public interface IMailinfoBS {
  /** ��������ʹ�õ��ʼ�������Ϣ */
  MailinfoAF queryMailinfo()throws BusinessException;
  /**����µ��ʼ�������Ϣ��ͬʱ�������õ��ʼ���Ϣ*/
   String addMailinfo(String mailId, String addresserMail, String addresserPassword,
      String addresseeA, String addresseeB, String subjectName,
      SecurityInfo securityInfo)throws BusinessException;
}
