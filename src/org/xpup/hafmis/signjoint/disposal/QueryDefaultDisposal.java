package org.xpup.hafmis.signjoint.disposal;
import java.util.List;
import org.xpup.hafmis.signjoint.util.SignTools;
import javax.servlet.ServletContext;
import org.xpup.hafmis.signjoint.disposalinterface.IDisposal;
import org.xpup.hafmis.signjoint.util.Filter;
/**
 * �Ƿ������봦����
 * @author yinchao
 *
 */

public class QueryDefaultDisposal extends QueryBase implements IDisposal{

  
  
  
  public QueryDefaultDisposal(List list) {
    super(list);
  }

  /**
   * ������
   */
  public String disposal(ServletContext sc) {
    String req=((String)list.get(0)).trim();
    return SignTools.getInfo_06(req);
  }
 
  
}
