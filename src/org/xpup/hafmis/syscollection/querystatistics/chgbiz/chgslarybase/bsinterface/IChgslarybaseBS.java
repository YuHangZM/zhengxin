package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.from.ChgslarybaseListAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.from.ChgslarybaseTbListAF;




public interface IChgslarybaseBS {
//����������ѯChgslarybaseListAF��¼
  public ChgslarybaseListAF findChgslarybaseList(Pagination pagination) throws Exception,BusinessException;
//����������ѯChgslarybaseTbListAF��¼
  public ChgslarybaseTbListAF findEmpChgslarybaseList(Pagination pagination) throws Exception,BusinessException;
}
