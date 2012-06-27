/**
 * Copy Right Information   : Goldsoft 
 * Project                  : AddPayTailDAODW
 * @version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-12-17
 **/
package org.xpup.hafmis.syscollection.common.daoDW;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpaddpayListImportDTO;

/**
 * ���˲���_���İ�_������λ��
 * 
 * @author wangy 2007-12-17
 */
public class AddPayTailDAODW extends HibernateDaoSupport {

  /**
   * Description ���˲���
   * 
   * @author wangy 2007-12-17
   * @param ����ɴ�_��ȡ����_������λ��AA304.PAY_HEAD_ID��ѯAA304�еļ�¼
   * @param minid ��С��DA001.ORG_HEAD_ID
   * @param ��PersonAddPayBS����
   * @return list
   */
  public List queryAddPayTailPickUpList(final String minid) {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select addPayTail.empId,addPayTail.empName,sum(addPayTail.orgAddMoney),sum(addPayTail.empAddMoney),"
            + " min(addPayTail.addMonht),max(addPayTail.addMonht),addPayTail.addReason "
            + " from AddPayTail addPayTail where addPayTail.paymentHead.id = ? "
            + " group by addPayTail.empId,addPayTail.addReason";
        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(minid));
        Iterator it = query.list().iterator();
        List temp_list = new ArrayList();
        EmpaddpayListImportDTO empaddpayListImportDTO1 = new EmpaddpayListImportDTO();
        temp_list.add(empaddpayListImportDTO1);
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            EmpaddpayListImportDTO empaddpayListImportDTO = new EmpaddpayListImportDTO();
            if (obj[0] != null) {
              empaddpayListImportDTO.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              empaddpayListImportDTO.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              empaddpayListImportDTO.setOrgPay(obj[2].toString());
            }
            if (obj[3] != null) {
              empaddpayListImportDTO.setEmpPay(obj[3].toString());
            }
            if (obj[4] != null) {
              empaddpayListImportDTO.setBeginMonth(obj[4].toString());
            }
            if (obj[5] != null) {
              empaddpayListImportDTO.setEndMonth(obj[5].toString());
            }
            if (obj[6] != null) {
              empaddpayListImportDTO.setReason(obj[6].toString());
            }
            temp_list.add(empaddpayListImportDTO);
          }
        }
        return temp_list;
      }
    });
    return list;
  }
}