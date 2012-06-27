package org.xpup.hafmis.syscollection.pickupmng.pickup.pickrule;
import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;
/**
 * ���ĺ�..������������һ����
 */
public class AllPick implements DrawRulesInterface {
  DrawRuleDAO drawRuleDAO = new DrawRuleDAO();
  /**
   * ���ĺơ�������ȡ�������..
   */
  public BigDecimal getMaxDarwMoney(int orgId,int empId,String reason){
    return drawRuleDAO.getDistroyPickMoney(orgId, empId, reason);
  }
  /**
   * ���ĺ�..����Ƿ�����ȡ
   */
  public boolean isDraw(String reason, Serializable empid) {
    if(drawRuleDAO.findEmployeePickupCount(reason,empid)>=3)
      return false;
    return true;
  }
  public void setDrawRuleDAO(DrawRuleDAO drawRuleDAO) {
    this.drawRuleDAO = drawRuleDAO;
  }
  public DrawRuleDAO getDrawRuleDAO() {
    return drawRuleDAO;
  }
}
