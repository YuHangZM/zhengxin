package org.xpup.hafmis.syscollection.peoplebank.documents.bsinterface;

import java.util.Vector;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.peoplebank.documents.dto.DocumentstopDTO;

/**
 * 
 * @author ����
 *
 */
public interface IDocumentBS {
  //����������ѯҪ������������������
  public Vector getAllInfo(String date,SecurityInfo securityInfo,DocumentstopDTO documentstopdto) throws Exception,BusinessException;
  //����������ѯҪ������������������ͷ��Ϣ
  public DocumentstopDTO gettopInfo(SecurityInfo securityInfo) throws Exception,BusinessException;
}
