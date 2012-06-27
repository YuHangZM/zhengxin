package org.xpup.hafmis.sysloan.querystatistics.querystatistics.querycongeallog.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IQueryCongeallogBS {

  //��ö������Ϣ
  public List queryCongeallogList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;

  //��ö������Ϣ��¼��
  public int queryCongeallogListCount(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;
  
  public List queryCongeallogAllList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;
}
