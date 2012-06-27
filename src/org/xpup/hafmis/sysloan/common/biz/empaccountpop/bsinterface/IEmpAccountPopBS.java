/**
 * Copy Right Information   : Goldsoft 
 * Project                  : IEmpAccountPopBS
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-11-02
 * �޸�����                   :2007-11-13ͨ�������������֤�������ѯְ����ϸ�ˣ����Ӳ�ѯ������ְ����š���λ���
 **/
package org.xpup.hafmis.sysloan.common.biz.empaccountpop.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.common.biz.empaccountpop.form.EmpAccountPopAF;

public interface IEmpAccountPopBS {

  // ����������ѯְ����ϸ���б�
  public EmpAccountPopAF queryEmpAccountListByCriterions(String borrowerName,
      String cardNum, Pagination pagination)
      throws Exception, BusinessException;
  public EmpAccountPopAF queryEmpAccountListByCriterions_yg(Pagination pagination)
  throws Exception, BusinessException;
}
