package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.empolderquery.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;

public interface IEmpolderQueryBS {
  /**
   * ��ѯ�������б�ķ���
   * @param pagination ��ѯ����
   * @return
   * @throws Exception
   */
  public List findDevelopList(Pagination pagination) throws Exception ;
}
