package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgpay.bsinterface;



import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgpay.from.ChgpayListAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgpay.from.ChgpayTbListAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.from.ChgslarybaseTbListAF;
 
 


public interface IChgpayBS {
   
//����������ѯChgpayListAF��¼
  public ChgpayListAF findChgpayList(Pagination pagination) throws Exception,BusinessException;
//����������ѯChgslarybaseTbListAF��¼
  public ChgpayTbListAF findEmpChgpayList(Pagination pagination) throws Exception,BusinessException;
 
}
