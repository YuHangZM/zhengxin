package org.xpup.hafmis.sysloan.loanaccord.printplan.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanaccord.printplan.form.PrintplanShowAF;

public interface IPrintplanBS {
  // ���Һ�ͬ�����pl111�Ķ�Ӧ��201�е���Ϣ��������������Ϊ�˷�ҳ������
  public PrintplanShowAF findPrintplanList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException;
}
