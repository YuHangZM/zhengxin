package org.xpup.hafmis.sysfinance.bookmng.createbook.bsinterface;

import org.xpup.hafmis.sysfinance.bookmng.createbook.dto.CreateBookDTO;

/**
 * Copy Right Information   : ��������
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-13-2007
 * @author Administrator
 *
 */
public interface ICreateBookBS {
  //��������
  public void createBook(CreateBookDTO createBookDTO)throws Exception;
}
