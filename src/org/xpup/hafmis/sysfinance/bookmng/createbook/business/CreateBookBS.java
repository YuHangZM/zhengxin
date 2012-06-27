package org.xpup.hafmis.sysfinance.bookmng.createbook.business;

import org.xpup.hafmis.sysfinance.bookmng.createbook.bsinterface.ICreateBookBS;
import org.xpup.hafmis.sysfinance.bookmng.createbook.dto.CreateBookDTO;
import org.xpup.hafmis.sysfinance.common.dao.BookDAO;

/**
 * Copy Right Information   : ��������
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-13-2007
 * @author Administrator
 *
 */
public class CreateBookBS implements ICreateBookBS {

  private BookDAO bookDAO = null ;
  
  public BookDAO getBookDAO() {
    return bookDAO;
  }

  public void setBookDAO(BookDAO bookDAO) {
    this.bookDAO = bookDAO;
  }

  /**
   * �������� ����
   */
  public void createBook(CreateBookDTO createBookDTO) throws Exception {
    // TODO Auto-generated method stub
    try {
      bookDAO.createBook(createBookDTO);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

}
