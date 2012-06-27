package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.Credit;

public class CreditDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public Credit queryById(Serializable id) {
    Validate.notNull(id);
    return (Credit) getHibernateTemplate().get(Credit.class, id);
  }

  /**
   * �����¼
   * 
   * @param Credit
   * @return
   */
  public Serializable insert(Credit credit) {
    Serializable id = null;
    try {
      Validate.notNull(credit);
      id = getHibernateTemplate().save(credit);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  public List getCreditList(final String shujutiquriqi,
      final String baowenshengchengriqi, final String officeCode,
      final String loanBankName, final String yewuhao,
      final String jiluzhuangtai, final int start, final String orderBy,
      final String order, final int pageSize, final int page) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select credit from Credit credit";
          Vector parameters = new Vector();
          String criterion = "";

          if (shujutiquriqi != null) {
            criterion += "  credit.shujutiquriqi=? and ";
            parameters.add(shujutiquriqi);
          }
          if (baowenshengchengriqi != null) {
            criterion += "  credit.baowenshengchengriqi=? and ";
            parameters.add(baowenshengchengriqi);
          }
          if (officeCode != null) {
            criterion += "  credit.office=? and ";
            parameters.add(officeCode);
          }
          if (loanBankName != null) {
            criterion += "  credit.loan_bank_id=? and ";
            parameters.add(new BigDecimal(loanBankName));
          }
          if (yewuhao != null) {
            criterion += "  credit.yewuhao like ? and ";
            parameters.add("%" + yewuhao + "%");
          }
          if (jiluzhuangtai != null) {
            criterion += "  credit.jiluzhuangtai=? and ";
            parameters.add(new BigDecimal(jiluzhuangtai));
          }

          if (criterion.length() != 0) {
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf(" and "));
          }
          String ob = orderBy;
          if (ob == null)
            ob = " credit.id ";

          String od = order;
          if (od == null)
            od = " DESC";
          hql = hql + criterion + " order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public int getCreditListAll(final String shujutiquriqi,
      final String baowenshengchengriqi, final String officeCode,
      final String loanBankName, final String yewuhao,
      final String jiluzhuangtai, final int start, final String orderBy,
      final String order, final int pageSize, final int page) {
    Integer n = new Integer(0);
    try {
      n = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select count(credit.id) from Credit credit";
          Vector parameters = new Vector();
          String criterion = "";

          if (shujutiquriqi != null) {
            criterion += "  credit.shujutiquriqi=? and ";
            parameters.add(shujutiquriqi);
          }
          if (baowenshengchengriqi != null) {
            criterion += "  credit.baowenshengchengriqi=? and ";
            parameters.add(baowenshengchengriqi);
          }
          if (officeCode != null) {
            criterion += "  credit.office=? and ";
            parameters.add(officeCode);
          }
          if (loanBankName != null) {
            criterion += "  credit.loan_bank_id=? and ";
            parameters.add(new BigDecimal(loanBankName));
          }
          if (yewuhao != null) {
            criterion += "  credit.yewuhao like ? and ";
            parameters.add("%" + yewuhao + "%");
          }
          if (jiluzhuangtai != null) {
            criterion += "  credit.jiluzhuangtai=? and ";
            parameters.add(new BigDecimal(jiluzhuangtai));
          }

          if (criterion.length() != 0) {
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf(" and "));
          }
          String ob = orderBy;
          if (ob == null)
            ob = " credit.id ";

          String od = order;
          if (od == null)
            od = " DESC";
          hql = hql + criterion + " order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return new Integer(query.uniqueResult().toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return n.intValue();
  }

  public ResultSet exportNormal(final String shujutiquriqi) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      // ����ͷ
      sql
          .append(" select varcharformat(c001.sjgsbbh, 3) || varcharformat(c001.jrjgdm, 14) ||");
      sql
          .append(" to_char(sysdate, 'YYYYMMDDHHMMSS') || varcharformat(c001.scbwbbh, 3) || '1' || '1' ||");
      sql
          .append(" (select numformat(count(c101.id), 10) || min(c101.yinghuankuanriqi) ||");
      sql.append(" max(c101.yinghuankuanriqi)");
      sql.append(" from cr101 c101");
      sql.append(" where c101.shujutiquriqi = '" + shujutiquriqi + "'");
      sql.append(" and c101.jiluzhuangtai = 0) ||");
      sql
          .append(" varcharformat(c001.lxr, 30) || varcharformat(c001.lxdh, 25) ||");
      sql.append(" varcharformat('', 30) || '\r\n' as data");
      sql.append(" from cr001 c001 ");
      sql.append(" union all ");
      // ������
      sql.append("select '0983' ||");// --�˻���¼����
      sql.append("'A' ||");// --��Ϣ��𣨻����ξ���A��
      sql.append("varcharformat(c001.jrjgdm, 14) ||");// --���ڻ�������
      sql.append("'1' ||");// --ҵ������,1-���
      sql.append("'13' ||");// --ҵ������ϸ��,13-����ס����������
      sql.append("varcharformat(c101.yewuhao, 40) ||");// --ҵ���
      sql.append("numformat(c001.fsdd, 6) ||");// --�����ص�
      sql.append("numformat(c101.kaihuriqi, 8) ||");// --��������
      sql.append("numformat(c101.daoqiriqi, 8) ||");// --��������
      sql.append("varcharformat(c001.bz, 3) ||");// --����
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --���Ŷ��
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --�������Ŷ��
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --���ծ��
      sql.append("'2' ||");// --������ʽ
      sql.append("'03' ||");// --����Ƶ��
      sql.append("varcharformat(c101.huankuanyueshu, 3) ||");// --��������
      sql.append("varcharformat(c101.shengyuqixian, 3) ||");// --ʣ�໹������
      sql.append("numformat(c101.yinghuankuanriqi, 8) ||");// --����/Ӧ��������
      sql.append("numformat(c101.shijihuankuanriqi, 8) ||");// --���һ��ʵ�ʻ�������
      sql.append("numformat(round(c101.yinghuankuanjine), 10) ||");// --����Ӧ������
      sql.append("numformat(round(c101.shijihuankuanjine), 10) ||");// --ʵ�ʻ�����
      sql.append("numformat(round(c101.daikuanyue), 10) ||");// --���
      sql.append("numformat(c101.dangqianyuqiqishu, 2) ||");// --��ǰ��������
      sql.append("numformat(round(c101.dangqianyuqizonge), 10) ||");// --��ǰ�����ܶ�
      sql.append("numformat(round(c101.yuqiyigeyue), 10) ||");// --����31-60��δ�黹�����
      sql.append("numformat(round(c101.yuqilianggeyue), 10) ||");// --����61-90��δ�黹�����
      sql.append("numformat(round(c101.yuqisangeyue), 10) ||");// --����91-180��δ�黹�����
      sql.append("numformat(round(c101.yuqiliugeyue), 10) ||");// --����180������δ�黹�����
      sql.append("numformat(c101.weiyuecishu, 3) ||");// --ΥԼ����
      sql.append("numformat(c101.zuigaoyuqiqishu, 2) ||");// --�����������
      sql.append("'9' ||");// --�弶����״̬
      sql.append("numformat(c101.zhanghuzhuangtai, 1) ||");// --�˻�״̬
      sql.append("varcharformat(c101.ershisigeyue, 24) ||");// --24���£��˻�������״̬
      sql.append("'0000000000' ||");// --͸֧180������δ�����
      sql.append("numformat(c101.zhanghuxinxitishi, 1) ||");// --�˻�ӵ������Ϣ��ʾ
      sql.append("varcharformat(c101.xingming, 30) ||");// --����
      sql.append("numformat(c101.zhengjianleixing, 1) ||");// --֤������
      sql.append("varcharformat(c101.zhengjianhaoma, 18) ||");// --֤������
      sql.append("varcharformat('', 30) ||");// --Ԥ���ֶ�

      sql.append("'B' ||");// --��Ϣ��������Ϣ�ξ���B��
      sql.append("numformat(c102.sex, 1) ||");// --�Ա�
      sql.append("numformat(c102.birthday, 8) ||");// --��������
      sql.append("numformat(c102.marriage_st, 2) ||");// --����״��
      sql.append("numformat(c102.degree, 2) ||");// --���ѧ��
      sql.append("'9' ||");// --���ѧλ
      sql.append("varcharformat(c102.house_tel, 25) ||");// --סլ�绰
      sql.append("varcharformat(c102.home_mobile, 16) ||");// --�ֻ�����
      sql.append("varcharformat(c102.org_tel, 25) ||");// --��λ�绰
      sql.append("varcharformat('', 30) ||");// --��������
      sql.append("varcharformat(c102.home_addr, 60) ||");// --סլ��ַ��ͨѶ��ַ
      sql.append("numformat(c102.home_mail, 6) ||");// --ͨѶ��ַ��������
      sql.append("varcharformat(c102.native_place, 60) ||");// --������ַ
      sql.append("varcharformat(c102.p_name, 30) ||");// --��ż����
      sql.append("varcharformat(c102.p_card_kind, 1) ||");// --��ż֤������
      sql.append("varcharformat(c102.p_card_num, 18) ||");// --��ż֤������
      sql.append("varcharformat(c102.p_org_name, 60) ||");// --��ż������λ
      sql.append("varcharformat(c102.p_home_mobile, 25) ||");// --��ż������λ

      sql.append("'C' ||");// --��Ϣ���ְҵ��Ϣ�ξ���C��
      sql.append("'Z' ||");// --ְҵ,Z-δ֪��
      sql.append("varcharformat(c102.org_name, 60) ||");// --��λ����
      sql.append("'Z' ||");// --��λ������ҵ,Z-δ֪��
      sql.append("varcharformat(c102.org_addr, 60) ||");// --��λ����
      sql.append("numformat(c102.org_mail, 6) ||");// --��λ��ַ��������
      sql.append("'0000' ||");// --����λ������ʼ���
      sql.append("'9' ||");// --ְ��,9-δ֪��
      sql.append("numformat(c102.title, 1) ||");// --ְ��
      sql.append("numformat(round(c102.year_salary), 10) ||");// --������
      sql.append("varcharformat('', 40) ||");// --�����˺�
      sql.append("varcharformat('', 14) ||");// --�����˻���������

      sql.append("'D' ||");// --��Ϣ��𣨾�ס��ַ�ξ���D��
      sql.append("varcharformat(c102.home_addr, 60) ||");// --��ס��ַ
      sql.append("numformat(c102.home_mail, 6) ||");// --ͨѶ��ַ��������
      sql.append("'9' ");// --��ס״��,9-δ֪��

      sql.append(" as data");// ������������
      sql.append(" from cr101 c101, cr001 c001, cr102 c102");
      sql.append(" where c101.yewuhao = c102.contract_id");
      sql.append(" and c101.isexport = 0");//
      sql.append(" and c101.shujutiquriqi = '" + shujutiquriqi + "'");
      sql.append(" and c101.jiluzhuangtai = 0");

      sql.append(" union all ");

      sql.append(" select '0345' ||");// --�˻���¼����
      sql.append("'A' ||");// --��Ϣ��𣨻����ξ���A��
      sql.append("varcharformat(c001.jrjgdm, 14) ||");// --���ڻ�������
      sql.append("'1' ||");// --ҵ������,1-���
      sql.append("'13' ||");// --ҵ������ϸ��,13-����ס����������
      sql.append("varcharformat(c101.yewuhao, 40) ||");// --ҵ���
      sql.append("numformat(c001.fsdd, 6) ||");// --�����ص�
      sql.append("numformat(c101.kaihuriqi, 8) ||");// --��������
      sql.append("numformat(c101.daoqiriqi, 8) ||");// --��������
      sql.append("varcharformat(c001.bz, 3) ||");// --����
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --���Ŷ��
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --�������Ŷ��
      sql.append("numformat(round(c101.daikuanjine), 10) ||");// --���ծ��
      sql.append("'2' ||");// --������ʽ
      sql.append("'03' ||");// --����Ƶ��
      sql.append("varcharformat(c101.huankuanyueshu, 3) ||");// --��������
      sql.append("varcharformat(c101.shengyuqixian, 3) ||");// --ʣ�໹������
      sql.append("numformat(c101.yinghuankuanriqi, 8) ||");// --����/Ӧ��������
      sql.append("numformat(c101.shijihuankuanriqi, 8) ||");// --���һ��ʵ�ʻ�������
      sql.append("numformat(round(c101.yinghuankuanjine), 10) ||");// --����Ӧ������
      sql.append("numformat(round(c101.shijihuankuanjine), 10) ||");// --ʵ�ʻ�����
      sql.append("numformat(round(c101.daikuanyue), 10) ||");// --���
      sql.append("numformat(c101.dangqianyuqiqishu, 2) ||");// --��ǰ��������
      sql.append("numformat(round(c101.dangqianyuqizonge), 10) ||");// --��ǰ�����ܶ�
      sql.append("numformat(round(c101.yuqiyigeyue), 10) ||");// --����31-60��δ�黹�����
      sql.append("numformat(round(c101.yuqilianggeyue), 10) ||");// --����61-90��δ�黹�����
      sql.append("numformat(round(c101.yuqisangeyue), 10) ||");// --����91-180��δ�黹�����
      sql.append("numformat(round(c101.yuqiliugeyue), 10) ||");// --����180������δ�黹�����
      sql.append("numformat(c101.weiyuecishu, 3) ||");// --ΥԼ����
      sql.append("numformat(c101.zuigaoyuqiqishu, 2) ||");// --�����������
      sql.append("'9' ||");// --�弶����״̬
      sql.append("numformat(c101.zhanghuzhuangtai, 1) ||");// --�˻�״̬
      sql.append("varcharformat(c101.ershisigeyue, 24) ||");// --24���£��˻�������״̬
      sql.append("'0000000000' ||");// --͸֧180������δ�����
      sql.append("numformat(c101.zhanghuxinxitishi, 1) ||");// --�˻�ӵ������Ϣ��ʾ
      sql.append("varcharformat(c101.xingming, 30) ||");// --����
      sql.append("numformat(c101.zhengjianleixing, 1) ||");// --֤������
      sql.append("varcharformat(c101.zhengjianhaoma, 18) ||");// --֤������
      sql.append("varcharformat('', 30)");// --Ԥ���ֶ�
      sql.append(" as data ");// ������������
      sql.append(" from cr101 c101, cr001 c001");
      sql.append(" where c101.isexport = 1");
      sql.append(" and c101.shujutiquriqi = '" + shujutiquriqi + "'");
      sql.append(" and c101.jiluzhuangtai = 0");
      // System.out.println(sql.toString());
      ResultSet rs = st.executeQuery(sql.toString());
      return rs;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public ResultSet exportDelete(final String shujutiquriqi,
      final String[] rowArray) {
    String row = "";
    for (int i = 0; i < rowArray.length; i++) {
      row = row.concat("'").concat(rowArray[i]).concat("',");
    }
    row = row.substring(0, row.lastIndexOf(","));
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      // ����ͷ
      sql
          .append("select varcharformat(c101.sjgsbbh, 3) || to_char(sysdate, 'YYYYMMDDHHMMSS') || varcharformat(c101.jrjgdm, 14) || '8' || numformat('"
              + rowArray.length
              + "',8) || varcharformat(c101.lxr, 30) || varcharformat(c101.lxdh, 25) || varcharformat('', 30) || '\r\n' as data from cr001 c101");
      sql.append(" union all ");
      // ������
      sql
          .append("select varcharformat(c001.jrjgdm, 14) || varcharformat(c101.yewuhao, 40) || numformat(c101.yinghuankuanriqi, 8) as data from cr101 c101, cr001 c001");
      sql.append(" where c101.yewuhao in (" + row + ")");

      ResultSet rs = st.executeQuery(sql.toString());
      return rs;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void dealWithContract(final String[] rowArray, final String status) {
    String row = "";
    for (int i = 0; i < rowArray.length; i++) {
      row = row.concat("'").concat(rowArray[i]).concat("',");
    }
    row = row.substring(0, row.lastIndexOf(","));
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      sql.append("update cr101 c101 set c101.jiluzhuangtai = "
          + new Integer(status));
      sql.append(" where c101.yewuhao in (" + row + ")");

      st.executeUpdate(sql.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void isContractExport(final String shujutiquriqi) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      sql
          .append("update cr101 c101 set c101.baowenshengchengriqi = to_char(sysdate,'yyyyMMdd'), c101.isexport = "
              + new Integer(1));
      sql.append(" where c101.shujutiquriqi ='" + shujutiquriqi + "'");

      st.executeUpdate(sql.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int getCreditCountByDate(final String shujutiquriqi) {
    Integer n = new Integer(0);
    try {
      n = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select count(credit.id) from Credit credit where credit.shujutiquriqi=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, shujutiquriqi);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return n.intValue();
  }

  public void createCredit(final String shujutiquriqi) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      sql.append("call credit('" + shujutiquriqi + "')");

      st.executeUpdate(sql.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteCredit(final String shujutiquriqi) {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      StringBuffer sql = new StringBuffer("");
      sql
          .append("delete cr101 t where t.shujutiquriqi='" + shujutiquriqi
              + "'");

      st.executeUpdate(sql.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
