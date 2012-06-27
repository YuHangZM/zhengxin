package org.xpup.hafmis.signjoint.dao;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.signjoint.dto.ExportDTO;
import org.xpup.hafmis.signjoint.dto.HistoryDTO;
import org.xpup.hafmis.signjoint.dto.LogDTO;
import org.xpup.hafmis.signjoint.dto.RecieveFileDTO;
import org.xpup.hafmis.signjoint.dto.SendFileDTO;
import org.xpup.hafmis.signjoint.dto.SignImportBodyDTO;
import org.xpup.hafmis.signjoint.dto.TempDTO;
import org.xpup.hafmis.signjoint.entity.Sign;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.Date;
import java.sql.Timestamp;
import org.xpup.hafmis.signjoint.util.BatchSignTools;
import org.xpup.hafmis.signjoint.util.SignTools;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.BusiConst;
public class SignDAO extends HibernateDaoSupport{
  /**
   * ����ǩԼ����
   * @param sign ��ʵ��������ݿ�
   * @return Ψһ��ʶ
   */
  public Integer save (Sign sign)
  {
    try{
      buildSign(sign);//����Ψһ��ʶ
      getHibernateTemplate().save(sign);
      return sign.getSign();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      return new Integer(0);
    }
  }
  /**
   * ����Ψһ��ʶ
   * @param sign ����Ψһ��ʶ��ǩԼʵ����
   * @return ����Ψһ��ʶ���ʵ����
   */
  private Sign buildSign(Sign sign)
  {
    List list=getHibernateTemplate().find("select max(sign.sign) from Sign sign");
    int i=0;
    if(list.get(0)!=null){
      i=((Integer)list.get(0)).intValue()+1;
      sign.setSign(new Integer(i));
    }
    else{
      sign.setSign(new Integer(0));
    }
    return sign;
  }
  
  /**
   * ����Ψһ��ʶȡ������
   * @param signΨһ��ʶ
   * @return ȡ�õ�����
   */
  public List getBySign(String sign)
  {
    List list=new ArrayList();
    try{
     list=getHibernateTemplate().find("from Sign sign where sign.sign="+sign);
     return list;
    }
    catch(Exception e)
    {//�����ѯʧ�ܻ�����쳣
      return list;
    }
  }
  /**
   * ����empid��ѯ�����Ϣ
   * @param empid
   * @return ����Ų�ѯ�����List
   */
  public List getBalancePartOne(final String empid)
  {
    List relist=new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          //������������Ϣ
          String sql1="select tete.lastbalance,tete.lastinterest "+
                      "from (select a316.id, "+
                      "a318.bef_pre_balance + a318.bef_cur_balance lastbalance, "+
                      "a318.pre_interest+a318.cur_interest lastinterest, "+
                      "a316.org_id orgiid, "+
                      "a318.emp_id empiid, "+
                      "row_number() over(order by a316.id desc) rn "+
                      "from aa316 a316, aa318 a318, aa002 a002 "+
                      "where a316.id = a318.sett_head_id "+
                      "and a316.org_id = a002.org_id "+
                      "and a002.id = a318.emp_id "+
                      "and a002.id = ? "+
                      "and a002.pre_balance + a002.cur_balance = "+
                      "(select max(a.pre_balance + a.cur_balance) "+
                      "from AA002 a "+
                      "where a.id = ?)) tete "+
                      "where rn = 1 ";
          Vector parameters = new Vector(); 
          parameters.add(Integer.valueOf(empid));//���Ӳ�ѯ����
          parameters.add(Integer.valueOf(empid));
          Query query = session.createSQLQuery(sql1);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list_balance=query.list();
          if(list_balance.size()==0){//���û�м�¼
            list_balance.add("0");
            list_balance.add("0");
            return list_balance;
          }
          List list=new ArrayList();
          Object [] obj=(Object [])list_balance.get(0);
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[0])).trim());
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[1])).trim());
          // BigDecimal dec=new BigDecimal(list_balance.get(0));
          return list;
        }//doInHibernate   
      });//executeFind
    }catch(Exception e)
    {//��ѯʧ�ܻ�����쳣
      e.printStackTrace();
      return null;
    }
    return relist;
  }
  /**
   * ����empid��ѯ�����Ϣ
   * @param empid
   * @return ����Ų�ѯ�����List
   */
  public List getBalancePartTwo(final String empid)
  {
    List relist=new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          //�½ɴ����и��˲���
          String sql2="select monthpay.monthjce,monthpay.empbf"+
                      " from (select a301.org_id orgidd,"+
                      " a303.emp_id empidd,"+
                      " a303.emp_should_pay + a303.org_real_pay monthjce,"+
                      " a303.emp_should_pay empbf,"+
                      " row_number() over(order by a302.pay_month desc) rn"+
                      " from aa301 a301, aa302 a302, aa303 a303, aa002 a002"+
                      " where a301.id = a302.pay_head_id"+
                      " and a302.id = a303.month_pay_head_id"+
                      " and a301.org_id = a002.org_id"+
                      " and a301.pay_status = 5 "+
                      " and a303.emp_id = a002.id"+
                      " and a002.id = ?"+
                      " and a002.pre_balance + a002.cur_balance ="+
                      " (select max(a.pre_balance + a.cur_balance)"+
                      " from AA002 a"+
                      " where a.id = ?)) monthpay"+
                      " where rn = 1";        
          Vector parameters = new Vector(); 
          parameters.add(Integer.valueOf(empid));//���Ӳ�ѯ����
          parameters.add(Integer.valueOf(empid));
          Query query = session.createSQLQuery(sql2);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list_balance=query.list();
          if(list_balance.size()==0){//���û�м�¼
            list_balance.add("0");
            list_balance.add("0");
            return list_balance;
          }
          List list=new ArrayList();
          Object [] obj=(Object [])list_balance.get(0);
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[0])).trim());
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[1])).trim());

          return list;
        }//doInHibernate   
      });//executeFind
    }catch(Exception e)
    {//��ѯʧ�ܻ�����쳣
      e.printStackTrace();
      return null;
    }
    return relist;
  }
  /**
   * ����empid��ѯ�����Ϣ
   * @param empid
   * @return ����Ų�ѯ�����List
   */
  public List getBalancePartThree(final String empid,final String startdate,final String enddate)
  {
    List relist=new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          //�����ɺϼƣ����겹�ɺϼƣ�����֧ȡ�ϼ�
          String sql3="select  a101.biz_type biztype,sum(a102.debit + a102.credit) fsje "+
                "from aa101 a101, aa102 a102, aa002 a002 "+
                "where a101.id = a102.org_flow_id "+
                "and a102.emp_id = a002.id "+
                "and a101.org_id = a002.org_id "+
                "and a101.biz_status = 5 "+
                "and a002.id = ? "+
                "and a002.pre_balance + a002.cur_balance = "+
                "(select max(a.pre_balance + a.cur_balance) "+
                "from AA002 a "+
                "where a.id = ?) "+
                "and a101.biz_type in ('A','B','M','D') "+
                "and a101.sett_date between ? and ? "+
                "group by a101.biz_type"; 
          Vector parameters = new Vector(); 
          parameters.add(Integer.valueOf(empid));//���Ӳ�ѯ����
          parameters.add(Integer.valueOf(empid));
          parameters.add(startdate);
          parameters.add(enddate);
          BigDecimal b=new BigDecimal(0);//����
          BigDecimal m=new BigDecimal(0);//����
          BigDecimal a=new BigDecimal(0);//���
          BigDecimal d=new BigDecimal(0);//֧ȡ
          String str="";
          Query query = session.createSQLQuery(sql3);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list_balance=query.list();
          if(list_balance.size()==0){//���û�м�¼
            list_balance.add("0");
            list_balance.add("0");
            list_balance.add("0");
            return list_balance;
          }
          
          Iterator iter=list_balance.iterator();
          while(iter.hasNext()){
            Object [] objarray=(Object [])iter.next();
            str=SignTools.parseNull((String)objarray[0]).trim();
            if(str.equalsIgnoreCase("A")){
              a=SignTools.parseNull((BigDecimal)objarray[1]);
            }
            if(str.equalsIgnoreCase("B")){
              b=SignTools.parseNull((BigDecimal)objarray[1]);
            }
            if(str.equalsIgnoreCase("M")){
              m=SignTools.parseNull((BigDecimal)objarray[1]);
            }
            if(str.equalsIgnoreCase("D")){
              d=SignTools.parseNull((BigDecimal)objarray[1]);
            }
          }
          List list=new ArrayList();
          list.add(SignTools.BigDecimaltoLang(a).trim());
          list.add(SignTools.BigDecimaltoLang(b.add(m)).trim());
          list.add(SignTools.BigDecimaltoLang(d).trim());          
          return list;
        }//doInHibernate   
     });//executeFind
    }catch(Exception e)
    {//��ѯʧ�ܻ�����쳣
      e.printStackTrace();
      return null;
    }
    return relist;
  }
  /**
   * ����empid��ѯ�����Ϣ
   * @param empid
   * @return ����Ų�ѯ�����List
   */
  public List getBalancePartFour(final String empid)
  {
    List relist=new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
           //�ϴνɴ�����
           String sql4="select max(a302.pay_month) "+
                       "from aa301 a301, aa302 a302, aa303 a303, aa002 a002 "+
                       "where a301.id = a302.pay_head_id "+
                       "and a302.id = a303.month_pay_head_id "+
                       "and a301.org_id = a002.org_id "+
                       "and a303.emp_id = a002.id "+
                       "and a301.pay_status = 5 "+
                       "and a002.id = ? "+
                       "and a002.pre_balance + a002.cur_balance = "+
                       "(select max(a.pre_balance + a.cur_balance) "+
                       "from AA002 a "+
                       "where a.id = ?)"; 
           Vector parameters = new Vector(); 
           parameters.add(Integer.valueOf(empid));//���Ӳ�ѯ����
           parameters.add(Integer.valueOf(empid));
           Query query = session.createSQLQuery(sql4);
           for (int i = 0; i < parameters.size(); i++) {
             query.setParameter(i, parameters.get(i));
           }
           List list_balance=query.list();
           if(list_balance.size()==0){//���û�м�¼
             list_balance.add("");
             return list_balance;
           }
           
           List list=new ArrayList();
           list.add(SignTools.parseNull((String)list_balance.get(0)));
           return list;
        }//doInHibernate   
     });//executeFind
    }catch(Exception e)
     {//��ѯʧ�ܻ�����쳣
       e.printStackTrace();
       return null;
     }
     return relist;
  }
  /**
   * ����empid��ѯ�����Ϣ
   * @param empid
   * @return ����Ų�ѯ�����List
   */   
  public List getBalancePartFive(final String empid)
  {
    List relist=new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
        SQLException {
          /*���*//*����*//*֤������*//*��λ����*//*�����˺�*//*��λ�˺�*//*֤������*/
          /*�˻�״̬*//*�������*//*�������*//*�´�Ӧ�ɴ��*/
          /*�´�Ӧ�ɴ������и��˲���*//*��������*/
          String sql6="select tabletemp.balance zye,"+
                      "tabletemp.empname xm, "+
                      "tabletemp.card_num zjhm, "+
                      "tabletemp.orgname dwmc, "+
                      "tabletemp.empid grzh, "+
                      "tabletemp.orgid dwzh, "+
                      "tabletemp.card_kind zjzl, "+
                      "tabletemp.account_status zhzt, "+
                      "tabletemp.pre_balance dqye, "+
                      "tabletemp.cur_balance hqye, "+
                      "tabletemp.pay xcyjce, "+
                      "tabletemp.emppay qzgrbf, "+
                      "tabletemp.opendate khrq "+
                      "from (select aa002.id empid, "+
                      "ba002.name empname, "+
                      "ba002.card_kind card_kind, "+
                      "ba002.card_num card_num, "+
                      "aa002.pay_status account_status, "+
                      "(aa002.org_pay + aa002.emp_pay) pay, "+
                      "aa002.org_id orgid, "+
                      "(aa002.pre_balance + aa002.cur_balance) balance, "+
                      "aa002.pre_balance pre_balance, "+
                      "aa002.cur_balance cur_balance, "+
                      "aa002.emp_pay emppay, "+
                      "ba001.name orgname, "+
                      "ba002.opendate opendate "+
                      "from AA002 aa002, BA002 ba002, AA001 aa001, BA001 ba001 "+
                      "where aa002.id = ? "+
                      "and ba002.id = aa002.emp_info_id "+
                      "and aa001.orginfo_id = ba001.id "+
                      "and aa002.org_id = aa001.id) tabletemp "+
                      "where tabletemp.balance = (select max(a.pre_balance + a.cur_balance) "+
                      "from AA002 a where a.id = ?) ";
          Vector parameters = new Vector(); 
          parameters.add(Integer.valueOf(empid));//���Ӳ�ѯ����
          parameters.add(Integer.valueOf(empid));
          Query query = session.createSQLQuery(sql6);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list_balance=query.list();
          if(list_balance.size()==0){//���û�м�¼
            list_balance.add("0");
            list_balance.add("");
            list_balance.add("");
            list_balance.add("");
            list_balance.add("0");
            list_balance.add("0");
            list_balance.add("");
            list_balance.add("");
            list_balance.add("0");
            list_balance.add("0");
            list_balance.add("0");
            list_balance.add("0");
            list_balance.add("");
            return list_balance;
          }
          List list=new ArrayList();
          Object [] obj=(Object [])list_balance.get(0);
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[0])).trim());
          list.add(SignTools.parseNull((String)obj[1]));
          list.add(SignTools.parseNull((String)obj[2]));
          list.add(SignTools.parseNull((String)obj[3]));
          list.add(SignTools.parseNull((BigDecimal)obj[4]).toString().trim());
          list.add(SignTools.parseNull((BigDecimal)obj[5]).toString().trim());
          try {
            list.add(BusiTools.getBusiValue(SignTools.parseNull((BigDecimal)obj[6]).intValue(), BusiConst.DOCUMENTSSTATE));
            list.add(BusiTools.getBusiValue(SignTools.parseNull((BigDecimal)obj[7]).intValue(), BusiConst.OLDPAYMENTSTATE));
          } catch (Exception e) {
            e.printStackTrace();
            return null;
          }
          
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[8])).trim());
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[9])).trim());
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[10])).trim());
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[11])).trim());
          list.add(SignTools.parseNull((String)obj[12]));
          return list;
       }//doInHibernate   
    });//executeFind
   }catch(Exception e)
    {//��ѯʧ�ܻ�����쳣
      e.printStackTrace();
      return null;
    }
    return relist;
  }   
  /**
   * �����ϸ��ѯ
   * @param empid ְ����
   * @param start ��ʼ����
   * @param end ��������
   * @return �����ϸ����
   */
  public List getListBalance(final String empid,final String start,final String end)
  {
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
        String sql="select tempt.rq, /*����*/"+"tempt.zy, /*ժҪ*/ "+"tempt.fse, /*������*/ "+
          "tempt.balance, /*���*/ "+"tempt.dwzh, /*��λ�˺�*/ "+"tempt.dwmc, /*��λ����*/ "+
          "tempt.grzh, /*�����˺�*/ "+"tempt.xm, /*����*/ "+"tempt.jdbs, /*�����ʶ*/ "+
          "tempt.dqye, /*�������*/ "+"tempt.hjny /*�������*/ "+"from (select a002.id empid, "+
          "a002.pre_balance + a002.cur_balance empbalance,"+"a101.id a101id,"+
          "a101.sett_date rq, /*����*/ "+"a101.biz_type zy, /*ժҪ*/ "+
          "a102.credit - a102.debit fse, /*������*/ "+
          "(select (sum(a.credit) - sum(a.debit)) from aa102 a, aa101 b "+
          "where a.id <= a102.id "+" and a.org_flow_id = b.id "+
          " and a.emp_id = a102.emp_id "+" and b.org_id = a101.org_id) balance, /*���*/ "+
          " a101.org_id dwzh, /*��λ�˺�*/ "+" b001.name dwmc, /*��λ����*/ "+
          " a002.id grzh, /*�����˺�*/ "+ " b002.name xm, /*����*/ "+"'��' jdbs, /*�����ʶ*/ "+
          " a002.pre_balance dqye, /*������ж���*/ "+" '' hjny /*�������*/ "+
          " from aa101 a101,"+"aa102 a102,"+"aa002 a002,"+"ba002 b002,"+"ba001 b001,"+"aa001 a001 "+
          " where a101.id = a102.org_flow_id "+"and a101.org_id = a002.org_id "+
          " and a101.biz_status = 5 "+" and a102.emp_id = a002.id "+
          " and a002.emp_info_id = b002.id "+" and a001.id = a002.org_id "+
          " and a001.orginfo_id = b001.id "+"and (a102.credit >= 0 and a102.debit = 0) "+
          "and a101.biz_type in ('B', 'F', 'H', 'I', 'K', 'L', 'G', 'M') "+
          "union "+
          "select a002.id empid, "+"a002.pre_balance + a002.cur_balance empbalance,"+
          "a101.id a101id,"+
          "a101.sett_date rq, /*����*/ "+"a101.biz_type zy, /*ժҪ*/"+"a102.credit - a102.debit fse, /*������*/ "+
          "(select (sum(a.credit) - sum(a.debit)) "+"from aa102 a, aa101 b "+
          "where a.id <= a102.id "+"and a.org_flow_id = b.id "+"and a.emp_id = a102.emp_id "+
          "and b.org_id = a101.org_id) balance, /*���*/ "+"a101.org_id dwzh, /*��λ�˺�*/ "+
          "b001.name dwmc, /*��λ����*/ "+"a002.id grzh, /*�����˺�*/ "+
          "b002.name xm, /*����*/ "+"'��' jdbs, /*�����ʶ*/ "+
          "a002.pre_balance dqye, /*������ж���*/ "+"'' hjny /*�������*/ "+
          "from aa101 a101, "+"aa102 a102, "+"aa002 a002, "+"ba002 b002, "+
          "ba001 b001, "+"aa001 a001 "+
          "where a101.id = a102.org_flow_id "+"and a101.org_id = a002.org_id "+
          "and a101.biz_status = 5 "+"and a102.emp_id = a002.id "+
          "and a002.emp_info_id = b002.id "+"and a001.id = a002.org_id "+
          "and a001.orginfo_id = b001.id "+"and (a102.debit >= 0 and a102.credit = 0) "+
          "and a101.biz_type in ('K', 'L', 'G') "+
          "union "+
          "select a002.id empid,"+"a002.pre_balance + a002.cur_balance empbalance,"+
          "(select a1.id from aa101 a1 where a1.biz_id = a301.id and a1.biz_type = 'A') a101id,"+
          "a301.sett_date rq, /*����*/ "+"'A' zy, /*ժҪ*/ "+
          "a303.emp_real_pay + a303.org_real_pay fse, /*������*/ "+
          "(select (sum(a.credit) - sum(a.debit)) "+"from aa102 a, aa101 b "+
          "where b.id <= a101.id "+"and a.org_flow_id = b.id "+
          "and a.emp_id = a002.id "+"and b.org_id = a301.org_id) balance, /*���*/ "+
          "a301.org_id dwzh, /*��λ�˺�*/"+"b001.name dwmc, /*��λ����*/ "+
          "a002.id grzh, /*�����˺�*/"+"b002.name xm, /*����*/ "+
          "'��' jdbs, /*�����ʶ*/"+"a002.pre_balance dqye, /*������ж���*/ "+
          "a302.pay_month hjny /*�������*/"+
          "from aa301 a301, "+"aa302 a302,"+"aa303 a303,"+"aa002 a002,"+"ba002 b002,"+
          "ba001 b001, "+"aa001 a001, "+"aa101 a101 "+
          "where a301.id = a302.pay_head_id "+"and a302.id = a303.month_pay_head_id  "+
          "and a301.pay_status = 5 "+"and a101.biz_id = a301.id "+
          "and a301.org_id = a002.org_id "+"and a303.emp_id = a002.id "+
          "and a002.emp_info_id = b002.id "+"and a001.id = a002.org_id "+
          "and a001.orginfo_id = b001.id "+"and a101.biz_type = 'A' "+
          "union "+
          "select a002.id empid, "+"a002.pre_balance + a002.cur_balance empbalance,"+
          "(select a1.id from aa101 a1 where a1.biz_id = a306.id and a1.biz_type = 'D') a101id,"+
          "a306.sett_date rq, /*����*/ "+"'D' zy, /*ժҪ*/ "+
          "a307.pick_pre_balance + a307.pick_cur_balance fse, /*������*/ "+
          "(select (sum(a.credit) - sum(a.debit)) "+"from aa102 a, aa101 b "+
          "where b.id <= a101.id "+"and a.org_flow_id = b.id "+
          "and a.emp_id = a002.id "+"and b.org_id = a306.org_id) balance, /*���*/ "+
          "a306.org_id dwzh, /*��λ�˺�*/"+"b001.name dwmc, /*��λ����*/ "+
          "a002.id grzh, /*�����˺�*/ "+"b002.name xm, /*����*/ "+
          "'��' jdbs, /*�����ʶ*/ "+"a002.pre_balance - a307.pick_pre_balance dqye, /*������ж���*/ "+
          "'' hjny /*�������*/ "+
          "from aa306 a306,"+"aa307 a307,"+"aa002 a002,"+"ba002 b002, "+
          "ba001 b001, "+"aa001 a001, "+"aa101 a101 "+
          "where a306.id = a307.pickhead_id "+"and a101.biz_id = a306.id "+
          "and a306.pick_satatus = 5 "+"and a306.org_id = a002.org_id "+
          "and a307.emp_id = a002.id "+"and a002.emp_info_id = b002.id "+
          "and a001.id = a002.org_id "+"and a001.orginfo_id = b001.id "+
          "and a101.biz_type = 'D' "+
          "union "+
          "select a002.id empid,"+"a002.pre_balance + a002.cur_balance empbalance,"+
          "(select a1.id from aa101 a1 where a1.biz_id = a309.id and a1.biz_type = 'E') a101id,"+
          "a309.sett_date rq, /*����*/"+"'E' zy, /*ժҪ*/ "+
          "a310.pre_balance + a310.cur_balance fse, /*������*/ "+
          "(select (sum(a.credit) - sum(a.debit)) "+"from aa102 a, aa101 b  "+
          "where b.id <= a101.id "+"and a.org_flow_id = b.id "+
          "and a.emp_id = a002.id "+"and b.org_id = a309.out_org_id) balance, /*���*/ "+
          "a309.out_org_id dwzh, /*��λ�˺�*/ "+"b001.name dwmc, /*��λ����*/ "+
          "a002.id grzh, /*�����˺�*/ "+"b002.name xm, /*����*/ "+"'��' jdbs, /*�����ʶ*/ "+
          "a002.pre_balance - a310.pre_balance dqye, /*������ж���*/"+"'' hjny /*�������*/ "+
          "from aa309 a309,"+"aa310 a310, "+"aa002 a002, "+"ba002 b002, "+
          "ba001 b001,"+"aa001 a001,"+"aa101 a101 "+
          "where a309.id = a310.tran_out_head_id "+"and a101.biz_id = a309.id "+
          "and a309.out_org_id = a002.org_id "+"and a309.tran_status = 5 "+
          "and a310.emp_id = a002.id "+"and a002.emp_info_id = b002.id "+
          "and a001.id = a002.org_id "+"and a001.orginfo_id = b001.id "+
          "and a101.biz_type = 'E') tempt "+
          "where tempt.empid = ? "+"and tempt.empbalance =" +
          "(select max(a.pre_balance + a.cur_balance) from AA002 a where a.id = ?)" +
          " and tempt.rq between ? and ?"+
          "order by  tempt.a101id  asc";
          Vector parameters = new Vector();
          parameters.add(Integer.valueOf(empid));//���Ӳ�ѯ����
          parameters.add(Integer.valueOf(empid));
          parameters.add(start);
          parameters.add(end);
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          int size=0;
          List list_balance=query.list();
          List list=new ArrayList();//���ص�List
          
          size=list_balance.size();
          list.add(new Integer(size).toString());
          if(size==0){
            return list;
          }
          Iterator inter=list_balance.iterator();
          while(inter.hasNext())
          {
            Object [] obj=(Object [])inter.next();
            List list_subordination=new ArrayList();
            list_subordination.add(SignTools.parseNull((String)obj[0]));
            try {
              list_subordination.add(BusiTools.getBusiValue_WL((String)obj[1], BusiConst.CLEARACCOUNTBUSINESSTYPE));
            } catch (NumberFormatException e) {
              e.printStackTrace();
              return null;
            } catch (Exception e) {
              e.printStackTrace();
              return null;
            }
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[2])).trim());
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[3])).trim());            
            list_subordination.add(SignTools.parseNull((BigDecimal)obj[4]).toString().trim()); 
            list_subordination.add(SignTools.parseNull((String)obj[5]));
            list_subordination.add(SignTools.parseNull((BigDecimal)obj[6]).toString().trim()); 
            list_subordination.add(SignTools.parseNull((String)obj[7]));

            list_subordination.add(((Object)obj[8]).toString());
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[9])).trim());            
            list_subordination.add(SignTools.parseNull((String)obj[10]));
            list.add(list_subordination);
          }       
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
     }    
    return relist;
  }
  /**
   * ��ѯ�Ǵ�������ѯ��������Ϊ������������Ϊ��
   * @return 1������Ϊ����2������Ϊ��,0Ϊ��ѯʧ��
   */
  public int getBorrowBalanceByBankORCenter(){
    List relist=new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select c.value from bb104 a, bb103 b, bb102 c, bb101 d "+
                 "where a.template_id = b.id and a.inner_name = 'LOAN_RETURN_TYPE' "+
                  "and c.oup_item_id = a.id and c.org_unit_id = d.id and d.ou_type = 1 ";
          Query query = session.createSQLQuery(sql);
          List list_balance=query.list();
          return list_balance;
        }//doInHibernate   
      });//executeFind
    }catch(Exception e)
    {//��ѯʧ�ܻ�����쳣
      e.printStackTrace();
      return 0;
    }
    return  Integer.parseInt(((String)relist.get(0)).trim());
  }
  
  /**
   * ��ѯ�ý���˵Ŀۿ���
   * @param name ְ������
   * @param card_num ���֤��
   * @return �ۿ���
   */
  public String getLoanRepayDay(final String name,final String card_num){
    
    List relist=new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select kkr.loan_repay_day from (select t.loan_repay_day loan_repay_day, "+
                 "row_number() over(order by t.overplus_loan_money desc) rn from pl111 t, pl110 s "+
                 "where s.contract_id = t.contract_id and s.borrower_name = ? "+
                 "and s.card_num = ?) kkr where rn = 1"; 
          Vector parameters = new Vector();
          parameters.add((String)name);//���Ӳ�ѯ����
          parameters.add((String)card_num);
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list=query.list();
          return list;
        }//doInHibernate   
      });//executeFind
    }catch(Exception e)
    {//��ѯʧ�ܻ�����쳣
      e.printStackTrace();
      return "";
    }
    if(relist.size()>0){
     return ((BigDecimal)relist.get(0)).toString().trim();
    }else{
     return "";
    }
  }
  
  
  /**
   * ������Ϊ����ѯ�������
   * @param name ְ������
   * @param card_num ���֤��
   * @param 
   * @return ���������Ϣ
   */
  public List getBorrowBalanceByCenter(final String name,final String card_num,final String date){
    List relist=new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select tb2.balance ye /*���*/,tb2.bname xm /*����*/, "+
                 "tb2.idnum sfzh /*���֤��*/,tb2.orgname dwmc /*��λ����*/,tb2.yhbx yhje /*������*/, "+
                 "tb2.dkzh dkzh /*�����ʺ�*/,tb2.yqye yqye /*�������*/,tb2.dkbj dkbj /*�����*/, "+
                 "tb2.dkbj - tb2.balance ljhke, /*�ۼƻ����*/tb2.yqqs yqqs, /*��������*/ "+
                 "tb2.syqx syqx, /*ʣ������*/tb2.xqkkr xqkkr, /*���ڿۿ���*/tb2.yhbx xqhkje /*���ڻ�����*/ "+
                 "from (select t.overplus_loan_money balance,s.borrower_name bname, "+
                 "s.card_num idnum,s.org_name orgname, "+
                 "(select pp.should_corpus + pp.should_interest from pl201 pp  "+
                 " where pp.loan_kou_yearmonth = "+
                 "(select max(pp201.loan_kou_yearmonth) from pl201 pp201 "+
                 " where pp201.contract_id = t.contract_id) and pp.contract_id = t.contract_id) yhbx, "+
                 " t.loan_kou_acc dkzh, "+
                 "(select sum(p201.should_corpus - p201.real_corpus)" +
                 "+ sum(p201.should_interest - p201.real_interest)+sum(p201.punish_interest)" +
                 " from pl201 p201 "+
                 " where p201.loan_kou_yearmonth <= ? "+
                 " and p201.contract_id = t.contract_id) yqye,t.loan_money dkbj, "+
                 "(select count(p201.loan_kou_yearmonth) from pl201 p201 "+
                 " where p201.loan_kou_yearmonth <= ? "+
                 "  and p201.contract_id = t.contract_id "+
                 "  and (p201.should_corpus <> p201.real_corpus or "+
                 " p201.should_interest <> p201.real_interest or "+
                 " p201.punish_interest <> 0)) yqqs,t.overplus_limite syqx, "+
                 " t.loan_repay_day xqkkr,row_number() over(order by t.overplus_loan_money desc) mm "+
                 "from pl111 t, pl110 s "+
                 "where s.contract_id = t.contract_id "+
                 " and s.borrower_name = ? and s.card_num = ? "+
                 " and t.contract_st in (7, 8, 10, 11,12)) tb2 where mm = 1"; 
          Vector parameters = new Vector();
          parameters.add(date);//���Ӳ�ѯ����
          parameters.add(date);
          parameters.add(name);
          parameters.add(card_num);
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list_balance=query.list();
          List list=new ArrayList();
          Object [] obj=(Object [])list_balance.get(0);
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[0])).trim());
          list.add(SignTools.parseNull((String)obj[1]));
          list.add(SignTools.parseNull((String)obj[2]));
          list.add(SignTools.parseNull((String)obj[3]));
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[4])).trim());

          list.add(SignTools.parseNull((String)obj[5]));
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[6])).trim());
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[7])).trim());
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[8])).trim());

          list.add(SignTools.parseNull(((BigDecimal)obj[9])).toString().trim());
          list.add(SignTools.parseNull((String)obj[10]));
          list.add(SignTools.parseNull(((BigDecimal)obj[11])).toString().trim());
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[12])).trim());
          return list;
        }//doInHibernate   
      });//executeFind
    }catch(Exception e)
    {//��ѯʧ�ܻ�����쳣
      e.printStackTrace();
      return null;
    }
    return relist;
  }
  
  /**
   * ������Ϊ����ѯ�������
   * @param name ְ������
   * @param card_num ���֤��
   * @param date 
   * @return ���������Ϣ
   */
  public List getBorrowBalanceByBank(final String name,final String card_num,final String date){
    List relist=new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
                                                       
          String sql="select tb2.balance ye /*���*/, "+
                    "tb2.bname xm /*����*/, "+
                    "tb2.idnum sfzh /*���֤��*/, "+
                    "tb2.orgname dwmc /*��λ����*/, "+
                    "tb2.yhbx yhje /*������*/, "+
                    "tb2.dkzh dkzh /*�����ʺ�*/, "+
                    "tb2.yqye yqye /*�������*/, "+
                    "tb2.dkbj dkbj /*�����*/, "+
                    "tb2.dkbj - tb2.balance ljhke, /*�ۼƻ����*/ "+
                    "tb2.yqqs yqqs, /*��������*/ "+
                    "tb2.syqx syqx, /*ʣ������*/ "+
                    "tb2.xqkkr xqkkr, /*���ڿۿ���*/ "+
                    "tb2.yhbx xqhkje /*���ڻ�����*/ "+
                    "from (select t.overplus_loan_money balance, "+
                            "s.borrower_name bname, "+
                            "s.card_num idnum, "+
                            "s.org_name orgname, "+
                            "(select pp.should_corpus + pp.should_interest "+
                               "from pl203 pp "+
                              "where pp.flow_tail_id = "+
                                    "(select max(pp203.flow_tail_id) "+
                                       "from pl203 pp203, pl202 pp202 "+
                                      "where pp203.contract_id = t.contract_id "+
                                        "and pp203.flow_head_id = pp202.flow_head_id "+
                                        "and pp202.biz_st = 6 "+
                                        "and pp202.biz_type in (2, 5, 6, 7)) "+
                                "and pp.contract_id = t.contract_id) yhbx, "+
                            " t.loan_kou_acc dkzh, "+
                            "(select p205.owe_corpus+p205.owe_interest+p205.punish_interest "+
                               "from pl205 p205 "+
                              "where p205.owe_date like ? "+
                                "and p205.contract_id = t.contract_id) yqye, "+
                            "t.loan_money dkbj, "+
                            "(select p205.owe_month "+
                               "from pl205 p205 "+
                              "where p205.owe_date like ? "+
                                "and p205.contract_id = t.contract_id) yqqs, "+
                            "t.overplus_limite syqx, "+
                            "t.loan_repay_day xqkkr, "+
                            "row_number() over(order by t.overplus_loan_money desc) mm "+
                       "from pl111 t, pl110 s "+
                      "where s.contract_id = t.contract_id "+
                        "and s.borrower_name = ? "+
                        "and s.card_num = ? "+
                        "and t.contract_st in (7, 8, 10, 11, 12)) tb2 "+
                       "where mm = 1";
                                                  
          Vector parameters = new Vector();
          //        ���Ӳ�ѯ����
          parameters.add(date+"%");//ģ����ѯ
          parameters.add(date+"%");
          parameters.add(name);
          parameters.add(card_num);
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list_balance=query.list();//��ѯ���
          List list=new ArrayList();//����
          if(list_balance.size()==0){//������Ϊ��
            list.add("0");
            list.add("");
            list.add("");
            list.add("");
            list.add("0");
            list.add("");
            list.add("0");
            list.add("0");
            list.add("0");
            list.add("");
            list.add("");
            list.add("");
            list.add("0");           
          }
          
          Object [] obj=(Object [])list_balance.get(0);
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[0])).trim());
          list.add(SignTools.parseNull((String)obj[1]));
          list.add(SignTools.parseNull((String)obj[2]));
          list.add(SignTools.parseNull((String)obj[3]));
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[4])).trim());
          list.add(SignTools.parseNull((String)obj[5]));
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[6])).trim());
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[7])).trim());
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[8])).trim());
          list.add(SignTools.parseNull((String)obj[9]));
          list.add(SignTools.parseNull((String)obj[10]));
          list.add(SignTools.parseNull((String)obj[11]));
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[12])).trim());
          return list;
        }//doInHibernate   
      });//executeFind
    }catch(Exception e)
    {//��ѯʧ�ܻ�����쳣
      e.printStackTrace();
      return null;
    }
    return relist;
  }  
  /**
   * ���������ϸ��ѯ
   * @param name ְ������
   * @param card_num ���֤��
   * @param start ��ʼʱ��
   * @param end ����ʱ��
   * @return ��ѯ���
   */
  public List getBorrowListBalance(final String name,final String card_num,final String start,final String end){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="select s.biz_date rq, /*����*/ "+
                     "s.biz_type zy, /*ժҪ*/  "+
                     "t.real_corpus hsbj, /*���ձ���*/ "+
                     "t.real_interest + t.real_punish_interest hslx, /*������Ϣ*/ "+
                     "t.loan_kou_acc dkzh, /*�����ʺ�*/ "+
                     "t.real_corpus + t.real_interest + t.real_punish_interest hkje, /*������*/ "+
                     
//                     "(select p1.loan_money "+
//                     "    from pl111 p1 "+
//                     "   where p1.contract_id = t.contract_id) - "+
//                     " (select sum(p203.real_corpus) "+
//                     "    from pl203 p203 "+
//                     "  where p203.flow_tail_id <= t.flow_tail_id "+
//                     "     and p203.loan_kou_acc = t.loan_kou_acc) sddkye /*ʱ��������*/ "+
                     " t.flow_tail_id tid "+
                     "   from pl203 t, pl202 s "+
                     "     where s.flow_head_id = t.flow_head_id "+
                     "            and t.contract_id = (select tb2.conid "+
                     "                    from (select t.overplus_loan_money balance, "+
                     "                           t.contract_id conid, "+
                     "                            row_number() over(order by t.overplus_loan_money desc) mm "+
                     "                            from pl111 t, pl110 s "+
                     "                           where s.contract_id = t.contract_id "+
                     "                             and s.borrower_name = ? "+
                     "                             and s.card_num = ? "+
                     "                            and t.contract_st in (7, 8, 10, 11, 12)) tb2 "+
                     "                           where mm = 1) "+
                     "                            and s.biz_type in (2, 3, 4, 5, 6, 7) "+
                     "                            and s.biz_st = 6 "+
                     "                            and s.biz_date between ? and ? "+
                                                  " order by s.flow_head_id asc ";

          
          
          String sql_child="select (select p1.loan_money from pl111 p1 where p1.loan_kou_acc = ? ) - "+
            "(select sum(p203.real_corpus) "+
            "   from pl203 p203 "+
            " where p203.flow_tail_id <= ? "+
            "    and p203.loan_kou_acc = ? ) "+
            "from dual ";
          Vector parameters = new Vector();
          parameters.add(name);//���Ӳ�ѯ����
          parameters.add(card_num);
          parameters.add(start);
          parameters.add(end);
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          int size=0;
          List list_balance=query.list();
          List list=new ArrayList();//���ص�List
          
          size=list_balance.size();
          list.add(new Integer(size).toString());
          if(size==0){
            return list;
          }
          
          Iterator inter=list_balance.iterator();
          while(inter.hasNext())
          {
            Object [] obj=(Object [])inter.next();
            List list_subordination=new ArrayList();
            list_subordination.add(SignTools.parseNull((String)obj[0]));
            try {
              list_subordination.add(BusiTools.getBusiValue(Integer.parseInt(obj[1].toString()), BusiConst.PLBUSINESSTYPE));
            } catch (NumberFormatException e) {
              e.printStackTrace();
              return null;
            } catch (Exception e) {
              e.printStackTrace();
              return null;
            }
            //������|������|��ϸ����|����,ժҪ,���ձ���,������Ϣ,�����ʺ�,������,ʱ��������|
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[2])).trim());
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[3])).trim());
            String loan=SignTools.parseNull((String)obj[4]);
            list_subordination.add(loan);
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[5])).trim());
            //list_subordination.add(SignTools.parseNull((BigDecimal)obj[6]).toString().trim());
            String flow_tail_id=SignTools.parseNull((BigDecimal)obj[6]).toString().trim();
            Vector par = new Vector();
            par.add(loan);
            par.add(flow_tail_id);
            par.add(loan);
            Query q = session.createSQLQuery(sql_child);
            for (int i = 0; i < par.size(); i++) {
              q.setParameter(i, par.get(i));
            }
            List list_child=q.list();
            if(list_child.size()>0){
              list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)list_child.get(0))).trim());
            }else{
              list_subordination.add("0");
            }
            list.add(list_subordination);
          }       
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
     }    
    return relist;
  }
  /**
   * ���ݿ����Ƿ����ְ�����������֤��
   * @param name ְ������
   * @param card_num ���֤��
   * @return true���� false������
   */
  public boolean isHaveNameAndCard_num(final String name,final String card_num){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql= "select p110.emp_id "+
                     "from pl110 p110 "+
                     "where p110.borrower_name = ? "+
                     "and p110.card_num = ? ";
          Vector parameters = new Vector();
          parameters.add(name);//���Ӳ�ѯ����
          parameters.add(card_num);
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return false;
    }    
    return relist.size()>0?true:false;//�Ƿ����ְ�����������֤��
  }
  /**
   * ���ݿ����Ƿ����ְ�����
   * @param empid ְ�����
   * @return true���� false������
   */
  
  public boolean isHaveEmp_id(final String empid){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="select a.id "+
                     "from AA002 a "+
                     "where a.id = ? ";
          Vector parameters = new Vector();
          parameters.add(empid);//���Ӳ�ѯ����
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return false;
    }    
    return relist.size()>0?true:false;//�Ƿ����ְ�����������֤��
    
  }
  

  
  /**
   * ͨ��empid��õ�λ�ʺţ���λ���ƣ�ְ���ʺţ�ְ������
   * @param empid
   * @return �����е�λ�ʺţ���λ���ƣ�ְ���ʺţ�ְ��������List
   */
  public List getEmpInfoByEmpID(final String empid){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql=  "select a002.org_id , b001.name, a002.id, b002.name empname "+
          "from aa002 a002, ba002 b002, ba001 b001, aa001 a001 "+
          "where a002.emp_info_id = b002.id "+
          "  and a002.org_id = a001.id "+
          "  and a001.orginfo_id = b001.id "+
          "  and a002.id = ? "+
          "  and a002.pre_balance + a002.cur_balance = "+
          "      (select max(a2.pre_balance + a2.cur_balance) "+
          "         from aa002 a2 "+
          "        where a2.id = ? ) ";
          Vector parameters = new Vector();
          parameters.add(empid);//���Ӳ�ѯ����
          parameters.add(empid);
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          query.setParameter(1, parameters.get(1));
          List list =query.list();
          List list_return=new ArrayList();
          Object [] obj=(Object [])list.get(0);
          /*
          list_return.add(SignTools.parseNull((BigDecimal)list.get(0)).toString().trim());
          list_return.add(SignTools.parseNull((String)list.get(1)));
          list_return.add(SignTools.parseNull((BigDecimal)list.get(2)).toString().trim()); 
          list_return.add(SignTools.parseNull((String)list.get(3)));
          */

          list_return.add(SignTools.parseNull((BigDecimal)obj[0]).toString().trim());
          list_return.add(SignTools.parseNull((String)obj[1]));
          list_return.add(SignTools.parseNull((BigDecimal)obj[2]).toString().trim()); 
          list_return.add(SignTools.parseNull((String)obj[3]));
          System.out.println((String)list_return.get(0));
          System.out.println((String)list_return.get(1));
          System.out.println((String)list_return.get(2));
          System.out.println((String)list_return.get(3));
          return list_return;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
    }    
    return relist;  
  }
  
  /**
   * ͨ��ְ��������ְ�����֤�Ż�ô����ʺ�
   * @param name ְ������
   * @param card_num ���֤��
   * @return ���������ʺŵ�List
   */
  public List getLoanIDByNameAndCard_num(final String name,final String card_num){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {  
         
          String sql= "select tb2.lacc "+
                      "from (select t.overplus_loan_money balance, "+
                      "  t.loan_kou_acc lacc, "+
                      "  row_number() over(order by t.overplus_loan_money desc) mm "+
                      "from pl111 t, pl110 s "+
                      "where s.contract_id = t.contract_id "+
                      " and s.borrower_name = ? "+
                      "  and s.card_num = ? "+
                      "  and t.contract_st in (7, 8, 10, 11, 12)) tb2 "+
                      "  where mm = 1 ";
          Vector parameters = new Vector();
          parameters.add(name);//���Ӳ�ѯ����
          parameters.add(card_num);
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          query.setParameter(1, parameters.get(1));
          List list =query.list();
          List list_return=new ArrayList();
          list_return.add(SignTools.parseNull((String)list.get(0)));
          return list_return;
          
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
    }    
    return relist;  
  }
  /**
   * ��ѯϵͳ�Ƿ���ָ���û���Ϣ
   * @param empid ְ�����
   * @param name ְ������
   * @param card_num ְ�����֤
   * @return trueΪ����ָ���û���Ϣ falseΪ������ָ���û���Ϣ
   */
  public boolean isHaveUserInfo(final String empid,final String name,final String card_num){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="select a002.id "+
                "from BA002 b002, aa002 a002 "+
                "where b002.name = ? "+
                "  and b002.card_num = ? "+
                "  and a002.emp_info_id = b002.id "+
                "  and a002.id=? ";
          Vector parameters = new Vector();
          parameters.add(name);//���Ӳ�ѯ����
          parameters.add(card_num);
          parameters.add(empid);
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          query.setParameter(1, parameters.get(1));
          query.setParameter(2, parameters.get(2));
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return false;
    }    
    return relist.size()>0?true:false;//�Ƿ����ְ�����������֤��
    
  }
  /**
   * ��15λ���֤��ת��Ϊ18λ���֤��
   * @param card_num ����
   * @return ת����Ŀ���
   */
  public String to18Card_num(final String card_num){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="select to_pid18(?) from dual";
          Vector parameters = new Vector();
          parameters.add(card_num);
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return "";
    }
    return (String)relist.get(0);
    
   }
//--------------------------------------------------------------------
//�����±� START
//--------------------------------------------------------------------
  /**
   * ���ݿ����Ƿ����ְ�����
   * @param empid ְ�����
   * @return true���� false������
   */
  public boolean isNewHaveEmp_id(final String empid){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="select t.empid "+
                     " from lmk_gjjye t "+
                     " where t.empid = ?";
          Vector parameters = new Vector();
          parameters.add(empid);//���Ӳ�ѯ����
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return false;
    }    
    return relist.size()>0?true:false;//�Ƿ����ְ����� 
  }

  
  /**
   * ��ѯ�û������֤
   * @param empid ְ�����
   * @param name ְ������
   * @return ְ�����֤
   */
  public String getCardNum(final String empid,final String name){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="select t.card_num "+
                     " from lmk_gjjye t "+
                     " where t.empid = ? "+
                     " and t.emp_name = ? ";
          Vector parameters = new Vector();
          //���Ӳ�ѯ����
          parameters.add(empid);
          parameters.add(name);
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          query.setParameter(1, parameters.get(1));
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return "";
    }    
    return relist.size()==0?"":(String)relist.get(0);//��ѯ�������֤��
  }
  

  
  /**
   * ����empid��ѯ�����Ϣ
   * @param empid
   * @return ����Ų�ѯ�����List
   */   
  public List getNewBalance(final String empid)
  {
    List relist=new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
        SQLException {
          String sql="select t.*  "+
                     " from lmk_gjjye t "+
                     " where t.empid = ? "+
                     " and t.balance = "+
                     "(select max(s.balance) from lmk_gjjye s where s.empid = ? )";
          Vector parameters = new Vector(); 
          parameters.add(Integer.valueOf(empid));//���Ӳ�ѯ����
          parameters.add(Integer.valueOf(empid));
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list_balance=query.list();
          if(list_balance.size()==0){//���û�м�¼
            list_balance.add("0");//���
            list_balance.add("");//����
            list_balance.add("");//֤������
            list_balance.add("");//��λ����
            list_balance.add("0");//�½ɽ��
            list_balance.add("");//�����˺�
            list_balance.add("");//��λ�˺�
            list_balance.add("");//֤������
            list_balance.add("");//�˻�״̬
            list_balance.add("0");//�������
            list_balance.add("0");//�������
            list_balance.add("0");//�������
            list_balance.add("0");//������Ϣ
            list_balance.add("0");//�½ɽ��
            list_balance.add("0");//���и��˲���
            list_balance.add("");//�ϴνɴ�����
            list_balance.add("0");//�´��½ɴ��
            list_balance.add("0");//���и��˲���
            list_balance.add("0");//�����ɺϼ�
            list_balance.add("0");//���겹�ɺϼ�
            list_balance.add("0");//����֧ȡ�ϼ�
            list_balance.add("");//��������
            list_balance.add("");//�´�Ӧ���·�           
            return list_balance;
          }
          //|���|����|֤������|��λ����|�½ɽ��|�����˺�,��λ�˺�,֤������,
          //�˻�״̬,�������,�������,�������,������Ϣ,�½ɽ��,���и��˲���,
          //�ϴνɴ�����,�´��½ɴ��,���и��˲���,�����ɺϼ�,���겹�ɺϼ�,
          //����֧ȡ�ϼ�,��������,�´�Ӧ���·�|
          List list=new ArrayList();
          Object [] obj=(Object [])list_balance.get(0);
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[1])).trim());//���
          list.add(SignTools.parseNull((String)obj[2]).trim());//����
          list.add(SignTools.parseNull((String)obj[3]).trim());//֤������
          list.add(SignTools.parseNull((String)obj[4]).trim());//��λ����
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[5])).trim());//�½ɽ��
          list.add(SignTools.parseNull((String)obj[6]).trim());//�����ʺ�
          list.add(SignTools.parseNull((String)obj[7]).trim());//��λ�ʺ�
          list.add(SignTools.parseNull((String)obj[8]).trim());//֤������
          list.add(SignTools.parseNull((String)obj[9]).trim());//�ʻ�״̬

          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[10])).trim());//�������
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[11])).trim());//�������
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[12])).trim());//�������
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[13])).trim());//������Ϣ
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[5])).trim());//�½ɴ��
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[14])).trim());//���и��˲���
          list.add(SignTools.parseNull((String)obj[15]).trim());//�ϴνɴ�����
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[16])).trim());//�´��½ɴ��
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[17])).trim());//���и��˲���
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[18])).trim());//�����ɺϼ�
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[19])).trim());//���겹�ɺϼ�
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[20])).trim());//����֧ȡ�ϼ�
          list.add(SignTools.parseNull((String)obj[21]).trim());//��������      
          list.add(SignTools.parseNull((String)obj[22]).trim());//�´�Ӧ���·�
          return list;
       }//doInHibernate   
    });//executeFind
   }catch(Exception e)
    {//��ѯʧ�ܻ�����쳣
      e.printStackTrace();
      return null;
    }
    return relist;
  }
  /**
   * �������ѯ��ǰ����Ϣ����λ�ʺţ���λ���ƣ�ְ���ʺţ�ְ��������
   * @param empid ְ�����
   * @return
   */
  public List getNewListBalanceInfo(final String empid){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select orgid,Org_name,empid,Emp_name from lmk_gjjye where empid= ? ";
          Vector parameters = new Vector();
          parameters.add(empid);//���Ӳ�ѯ����
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          List list =query.list();
          List list_return=new ArrayList();
          Object [] obj=(Object [])list.get(0);
          list_return.add(SignTools.parseNull((String)obj[0]).trim());
          list_return.add(SignTools.parseNull((String)obj[1]).trim());
          list_return.add(SignTools.parseNull((String)obj[2]).trim()); 
          list_return.add(SignTools.parseNull((String)obj[3]).trim());
          return list_return;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
    }    
    return relist;  
  }
  
  
  
  /**
   * �������ϸ��ѯ
   * @param empid ְ����
   * @param start ��ʼ����
   * @param end ��������
   * @return �����ϸ����
   */
  public List getNewListBalance(final String empid,final String start,final String end)
  {
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
        String sql="select t.* "+
                   "  from lmk_gjjmx t "+
                   "  where t.empid = ? "+
                   "  and t.sett_date between ? and ? ";
          Vector parameters = new Vector();
          parameters.add(Integer.valueOf(empid));//���Ӳ�ѯ����
          parameters.add(start);
          parameters.add(end);
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          int size=0;
          List list_balance=query.list();
          List list=new ArrayList();//���ص�List
          
          size=list_balance.size();
          list.add(new Integer(size).toString());
          if(size==0){
            return list;
          }
          Iterator inter=list_balance.iterator();
          while(inter.hasNext())
          {
            Object [] obj=(Object [])inter.next();
            List list_subordination=new ArrayList();
            //��ϸ����|
            //����,ժҪ,������,���,�����ʶ,������ж���,�������|
            list_subordination.add(SignTools.parseNull((String)obj[5]).trim());//����
            list_subordination.add(((String)obj[6]).trim());//ժҪ
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[7])).trim());//������
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[8])).trim());//���            
            list_subordination.add(SignTools.parseNull((String)obj[9]).trim());//�����ʶ 
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[10])).trim());//������ж���            
            list_subordination.add(SignTools.parseNull((String)obj[11]).trim());//�������
            list.add(list_subordination);
          }       
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
     }    
    return relist;
  }
  
  /**
   * ��ѯ�´������
   * @param name ְ������
   * @param card_num ���֤��
   * @param date 
   * @return ���������Ϣ
   */
  public List getNewBorrowBalance(final String name,final String card_num){
    List relist=new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
                                                       
          String sql="select p.* "+
                     " from lmk_dkye p "+
                     " where p.emp_name = ? "+
                     "   and p.card_num = ? "+
                     "   and p.over_loanmoney = " +
                     " (select max(s.over_loanmoney) "+
                     "    from lmk_dkye s "+
                     "   where s.emp_name = ? "+
                     "     and s.card_num = ?)";
                                                  
          Vector parameters = new Vector();
          //        ���Ӳ�ѯ����
          parameters.add(name);
          parameters.add(card_num);
          parameters.add(name);
          parameters.add(card_num);
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list_balance=query.list();//��ѯ���
          List list=new ArrayList();//����
          if(list_balance.size()==0){//������Ϊ��
            list.add("0");
            list.add("");
            list.add("");
            list.add("");
            list.add("0");
            list.add("");
            list.add("0");
            list.add("0");
            list.add("0");
            list.add("");
            list.add("");
            list.add("");
            list.add("0");           
          }
          //�������|����|���֤|��λ����|�»����|�����ʺ�,�������,�����,�ۼƻ����,��������,ʣ������,���ڿۿ���,���ڻ�����|
          Object [] obj=(Object [])list_balance.get(0);
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[1])).trim());//�������
          list.add(SignTools.parseNull((String)obj[2]).trim());//����
          list.add(SignTools.parseNull((String)obj[3]).trim());//���֤
          list.add(SignTools.parseNull((String)obj[4]).trim());//��λ����
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[5])).trim());//�»����
          list.add(SignTools.parseNull((String)obj[6]).trim());//�����ʺ�
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[7])).trim());//�������
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[8])).trim());//�����
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[9])).trim());//�ۼƻ����
          list.add(SignTools.parseNull((BigDecimal)obj[10]).toString());//��������
          list.add(SignTools.parseNull((String)obj[11]).trim());//ʣ������
          list.add(SignTools.parseNull((String)obj[12]).trim());//���ڿۿ���
          list.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[13])).trim());//���ڻ����
          return list;
        }//doInHibernate   
      });//executeFind
    }catch(Exception e)
    {//��ѯʧ�ܻ�����쳣
      e.printStackTrace();
      return null;
    }
    return relist;
  } 
  /**
   * ��ô����ʺ�
   * @param empid ְ�����
   * @return
   */
  public List getNewLoanAccount(final String name,final String card_num){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select Loan_kou_acc from lmk_dkye where emp_name=? and Card_num=? ";
          Vector parameters = new Vector();
          parameters.add(name);//���Ӳ�ѯ����
          parameters.add(card_num);
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          query.setParameter(1, parameters.get(1));
          List list =query.list();
          List list_return=new ArrayList();
          Object obj=(Object )list.get(0);
          list_return.add(SignTools.parseNull((String)obj).trim());
          return list_return;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
    }    
    return relist;  
  }

  /**
   * �´��������ϸ��ѯ
   * @param name ְ������
   * @param card_num ���֤��
   * @param start ��ʼʱ��
   * @param end ����ʱ��
   * @return ��ѯ���
   */
  public List getNewBorrowListBalance(final String name,final String card_num,final String start,final String end){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="  select t.* "+
                     "  from lmk_dkhsmx t, lmk_dkye p "+
                     "  where t.sett_date between ? and ? "+
                     "  and t.loan_kou_acc = p.loan_kou_acc "+
                     "  and p.emp_name = ? "+
                     "  and p.card_num = ? "+
                     "  and p.over_loanmoney = (select max(s.over_loanmoney) "+
                     "  from lmk_dkye s "+
                     "  where s.emp_name = ? "+
                     "  and s.card_num = ?) ";
          Vector parameters = new Vector();
          parameters.add(start);
          parameters.add(end);
          parameters.add(name);
          parameters.add(card_num);
          parameters.add(name);
          parameters.add(card_num);
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          int size=0;
          List list_balance=query.list();
          List list=new ArrayList();//���ص�List
          
          size=list_balance.size();
          list.add(new Integer(size).toString());
          if(size==0){
            return list;
          }
          Iterator inter=list_balance.iterator();
          while(inter.hasNext())
          {
            Object [] obj=(Object [])inter.next();
            List list_subordination=new ArrayList();
            //��ϸ����|����,ժҪ,���ձ���,������Ϣ,������,ʱ��������|
            list_subordination.add(SignTools.parseNull((String)obj[2]).trim());//����
            list_subordination.add(SignTools.parseNull(((String)obj[3])).trim());//ժҪ
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[4])).trim());//���ձ���
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[5])).trim());//������Ϣ
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[6])).trim());//������
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[7])).trim());//ʱ��������
            list.add(list_subordination);
          }       
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
     }    
    return relist;
  }
  
  
  /**
   * ���ݿ����Ƿ����ְ�����������֤��
   * @param name ְ������
   * @param card_num ���֤��
   * @return true���� false������
   */
  public boolean isNewHaveNameAndCard_num(final String name,final String card_num){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select t.Loan_kou_acc "+
                     " from lmk_dkye t "+
                     " where t.Card_num = ? "+
                     " and t.emp_name = ? ";
          Vector parameters = new Vector();
          //���Ӳ�ѯ����
          parameters.add(card_num);
          parameters.add(name);
          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return false;
    }    
    return relist.size()>0?true:false;//�Ƿ����ְ�����������֤��
  }
  /**
   * ��ѯϵͳ�Ƿ���ָ���û���Ϣ
   * @param empid ְ�����
   * @param name ְ������
   * @param card_num ְ�����֤
   * @return trueΪ����ָ���û���Ϣ falseΪ������ָ���û���Ϣ
   */
  public boolean isNewHaveUserInfo(final String empid,final String name,final String card_num){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="select t.emplyacc "+
                     "from tb_emply_mng t "+
                     "where t.emplyacc = ? "+
                     "  and t.emplyname = ? "+
                     "  and t.emplyid = ? ";


          Vector parameters = new Vector();
          parameters.add(empid);
          parameters.add(name);
          parameters.add(card_num);
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, parameters.get(0));
          query.setParameter(1, parameters.get(1));
          query.setParameter(2, parameters.get(2));
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return false;
    }    
    return relist.size()>0?true:false;//�Ƿ����ְ�����������֤��
    
  }
  
  
//--------------------------------------------------------------------
//�����±� END
//--------------------------------------------------------------------
  /**
   * ִ������ͬ���洢����
   */
  public void execSynProcdural(){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          System.out.println("start~");
          String sql="{call initializtion()}";
          CallableStatement cs = session.connection().prepareCall(sql);
          cs.execute();
          System.out.println("OK~");
          return null;
        }
      });//HibernateCallback
    }catch(Exception e){
      System.out.println("�洢����ִ�д���");
      e.printStackTrace();
      return;
    }
    return;
  }
  /**
   * ִ�зֲ��洢����
   */
  public void execSynPartProcdural(final String empid,final String name,final String card_num,final int method){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="{call initializtion_apart(?,?,?,?)}";
          CallableStatement cs = session.connection().prepareCall(sql);
          cs.setString(1, name);
          cs.setString(2, empid);
          cs.setString(3, card_num);
          cs.setBigDecimal(4,new BigDecimal(method));
          cs.execute();
          return null;
        }
      });//HibernateCallback
    }catch(Exception e){
      System.out.println("�洢����ִ�д���");
      e.printStackTrace();
      return;
    }
    return;
  }
//--------------------------------------------------------------------
//����ǩԼ START
//--------------------------------------------------------------------
  /**
   * ����˾��Ų�ѯ��˾��Ϣ
   * @param num ��˾���
   * @return ��˾��Ϣ
   */
  public List getCompanyInfoByNum(final String num){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="  select t.* "+
                     "  from lmk_dkhsmx t, lmk_dkye p "+
                     "  where t.sett_date between ? and ? "+
                     "  and t.loan_kou_acc = p.loan_kou_acc "+
                     "  and p.emp_name = ? "+
                     "  and p.card_num = ? "+
                     "  and p.over_loanmoney = (select max(s.over_loanmoney) "+
                     "  from lmk_dkye s "+
                     "  where s.emp_name = ? "+
                     "  and s.card_num = ?) ";
          Vector parameters = new Vector();
          parameters.add(num);

          Query query = session.createSQLQuery(sql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          int size=0;
          List list_balance=query.list();
          List list=new ArrayList();//���ص�List
          
          size=list_balance.size();
          list.add(new Integer(size).toString());
          if(size==0){
            return list;
          }
          Iterator inter=list_balance.iterator();
          while(inter.hasNext())
          {
            Object [] obj=(Object [])inter.next();
            List list_subordination=new ArrayList();
            //��ϸ����|����,ժҪ,���ձ���,������Ϣ,������,ʱ��������|
            list_subordination.add(SignTools.parseNull((String)obj[2]).trim());//����
            list_subordination.add(SignTools.parseNull(((String)obj[3])).trim());//ժҪ
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[4])).trim());//���ձ���
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[5])).trim());//������Ϣ
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[6])).trim());//������
            list_subordination.add(SignTools.BigDecimaltoLang(SignTools.parseNull((BigDecimal)obj[7])).trim());//ʱ��������
            list.add(list_subordination);
          }       
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
     }    
    return relist;
  }
  /**
   * ����λ��Ų�ѯ�õ�λ������ʱ���е�����ְ����Ϣ
   * @param orgid
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public List queryEmpInfoByOrgIDFromTemp(final String orgid,final String orderBy, final String order, final int start,
      final int pageSize,final int page){
    List relist = new ArrayList();
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         //empid ְ����ţ�empname ְ��������cardnum���֤�ţ�bankcardid ���п���
          String sql="select empid,empname,cardnum,bankcardid from lmk_temp_sign_table where orgid=?";
          sql=sql+"order by "+orderBy+" "+order;          
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new BigDecimal(orgid));
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);     
          List queryList=query.list();       
          //��ҳ����
          if(queryList==null||queryList.size()==0){           
            query.setFirstResult(pageSize*(page-2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList=query.list();
          }
          List list=new ArrayList();//���ص�List          
          Iterator inter=queryList.iterator();
          while(inter.hasNext())
          {
            Object [] obj=(Object [])inter.next();
            List list_subordination=new ArrayList();
            list_subordination.add((SignTools.parseNull((BigDecimal)obj[0])).toString().trim());//ְ�����
            list_subordination.add(SignTools.parseNull((String)obj[1]).trim());//ְ������
            list_subordination.add(SignTools.parseNull((String)obj[2]).trim());//���֤��
            list_subordination.add(SignTools.parseNull((String)obj[3]).trim());//���п���
            list.add(list_subordination);
          }       
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
     }    
    return relist;
  }

  /**
   * ��������Ϣ�������ݿ�
   * @param importdto �����dto
   * @throws SQLException
   */
  public void importEmpInfo(final SignImportBodyDTO importdto) throws SQLException{
    List relist = new ArrayList();
    String empid=importdto.getEmpid();
    String empname=importdto.getEmpname();
    String cardnum=importdto.getCardnum();
    String bankcardid=importdto.getBankcardid();
    //orgid ��λ��ţ�orgname ��λ���ƣ�empid ְ����ţ�
    //empname ְ��������cardnum���֤�ţ�bankcardid ���п��ţ�
    //biz_date �������ڣ�operater ������Ա
    String sql="inseet into lmk_temp_sign_table(orgid,orgname,empid,empname,cardnum,bankcardid,biz_date,operater)"+
    " VALUES (?,?,?,?,?,?,?,?)";
    //��õ�ǰsession�����ݿ�����
    Connection conn   = getSession().connection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setBigDecimal(0,new BigDecimal(empid));
    ps.setString(1,empname);
    ps.setString(2,cardnum);
    ps.setString(3,bankcardid);
    //ִ�в�ѯ
    ps.execute();
    //�رոö���
    ps.close();
    conn.close();
    getSession().flush();
  }
  
  /**
   * ��������Ϣ�������ݿ�
   * @param importdto �����dto
   * @throws SQLException
   */
  public void insertEmpInfo(final TempDTO importdto) throws SQLException{
    String orgid=importdto.getOrgid();
    String orgname=importdto.getOrgname();
    String empid=importdto.getEmpid();
    String empname=importdto.getEmpname();
    String cardnum=importdto.getCardnum();
    String bankcardid=importdto.getBankcardid();
    String bizdate=importdto.getBiz_date();
    String operater=importdto.getOperater();
    //orgid ��λ��ţ�orgname ��λ���ƣ�empid ְ����ţ�
    //empname ְ��������cardnum���֤�ţ�bankcardid ���п��ţ�
    //biz_date �������ڣ�operater ������Ա
    String sql="insert into lmk_temp_sign_table(orgid,orgname,empid,empname,cardnum,bankcardid,biz_date,operater)"+
    " VALUES (?,?,?,?,?,?,?,?)";
    //��õ�ǰsession�����ݿ�����
    Connection conn   = getSession().connection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setBigDecimal(1,new BigDecimal(orgid));

    ps.setString(2,orgname);
    ps.setBigDecimal(3,new BigDecimal(empid));
    
    ps.setString(4,empname);
    ps.setString(5,cardnum);
    ps.setString(6,bankcardid);

    ps.setString(7,bizdate);
    ps.setString(8,operater);
    
    //ִ�в�ѯ
    ps.execute();
    //�رոö���
    ps.close();
    conn.close();
    getSession().flush();
  }
  
  
  /**
   * �޸��ݴ���е��û���Ϣ
   * @param newdto �µ��û���Ϣ
   * @param olddto �ϵ��û���Ϣ
   * @throws SQLException
   */
  public void updateEmpInfo(final TempDTO newdto,final TempDTO olddto) throws SQLException{

    //orgid ��λ��ţ�orgname ��λ���ƣ�empid ְ����ţ�
    //empname ְ��������cardnum���֤�ţ�bankcardid ���п��ţ�
    //biz_date �������ڣ�operater ������Ա
    String sql="update lmk_temp_sign_table set bankcardid= ? where orgid=? and empid=? and bankcardid=?";
    //��õ�ǰsession�����ݿ�����
    Connection conn   = getSession().connection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1,newdto.getBankcardid());
    ps.setBigDecimal(2,new BigDecimal(olddto.getOrgid().trim()));

    ps.setBigDecimal(3,new BigDecimal(olddto.getEmpid().trim()));

    ps.setString(4,olddto.getBankcardid().trim());

    //ִ�в�ѯ
    ps.execute();
    //�رոö���
    ps.close();
    conn.close();
    getSession().flush();
  }
  
  
  
  
  
  
  
  
  
  /**
   * ����λ��Ų�ѯ�õ�λ������Ա����Ϣ
   * @param orgid ��λ���
   * @return
   */
  public List queryEmpInfoByOrgID(final String orgid,final String orgname){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         //empid ְ����ţ�empname ְ��������cardnum���֤�ţ�bankcardid ���п���
          String sql="select a002.id, b002.name, b002.card_num "+
                     "from aa002 a002, ba002 b002 "+
                     "where a002.emp_info_id = b002.id "+
                     "and a002.org_id = ? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new BigDecimal(orgid));
          List querylist=query.list();
          List list=new ArrayList();//���ص�List          
          Iterator inter=querylist.iterator();
          
          ExportDTO dto1=new ExportDTO();
          dto1.setOrgid("1");
          dto1.setOrgname("1");
          dto1.setEmpid("1");//ְ�����
          dto1.setEmpname("1");//ְ������
          dto1.setCardnum("1");//���֤��
          list.add(dto1);
          
          while(inter.hasNext())
          {
            Object [] obj=(Object [])inter.next();
            ExportDTO dto=new ExportDTO();
            dto.setOrgid(orgid);
            dto.setOrgname(orgname);
            dto.setEmpid(((SignTools.parseNull((BigDecimal)obj[0])).toString().trim()));//ְ�����
            dto.setEmpname((SignTools.parseNull((String)obj[1]).trim()));//ְ������
            dto.setCardnum((SignTools.parseNull((String)obj[2]).trim()));//���֤��
            list.add(dto);
          }       
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
     }    
    return relist;
  }

  /**
   * �õ�λ���Ƿ������ְ��
   * @param empid
   * @param orgid
   * @return
   */
  public boolean isHaveUserInfoByOrg(final String empid,final String orgid){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="select a002.id "+
                     "from aa002 a002 "+
                     "where a002.id = ? "+
                     "and a002.org_id =? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new BigDecimal(empid));
          query.setParameter(1, new BigDecimal(orgid));
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return false;
    }    
    return relist.size()>0?true:false;//�Ƿ����ְ�����������֤��
  }

  /**
   * �Ƿ��иõ�λ�ĵ�λ�����Ϣ
   * @param orgid ��λ���
   * @return
   */
  public boolean isHaveOrg(final String orgid){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="select a001.id from aa001 a001 where a001.id = ?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new BigDecimal(orgid));
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return false;
    }    
    return relist.size()>0?true:false;//�Ƿ����ְ�����������֤��
  }

  
  /**
   * ��ѯ�õ�λ�¾��ж�����Ա����Ϣ
   * @param orgid ��λ���
   * @return
   */
  public int queryEmpCountByOrgid(final String orgid){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         //empid ְ����ţ�empname ְ��������cardnum���֤�ţ�bankcardid ���п���
          String sql="select empid,empname,cardnum,bankcardid from lmk_temp_sign_table where orgid=?";       
          Query query = session.createSQLQuery(sql);
          if (orgid != null||!orgid.equals("")) {
             return new ArrayList();
          }
          query.setParameter(0, new BigDecimal(orgid)); 
          List queryList=query.list();        
          return queryList;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return 0;
     }    
    return relist.size();
  }
  

  /**
   * ��ѯ��ʷ��Ϣ
   * @param orgid
   * @param empid
   * @param transactdatastart
   * @param transactdataend
   * @param affirmdatastart
   * @param affirmdataend
   * @param issccueed
   * @return
   */
  public int queryEmpHistoryCount(final String orgid, final String empid, final String transactdatastart,final String transactdataend,final String affirmdatastart,final String affirmdataend,final String issccueed){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select id,orgid,orgname,empid,empname,cardnum,bankcardid,succ_fail,biz_date,SIGN_DATE,OPERATOR,sign from lmk_history_sign ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgid != null&&!orgid.equals("")) {
            criterion += "orgid = ?  and ";
            parameters.add(new BigDecimal(orgid));
          }

          if (empid != null&&!empid.equals("")) {
            criterion += "empid = ?  and ";
            parameters.add(new BigDecimal(empid));
          }
          
          if (transactdatastart != null&&!transactdatastart.equals("")) {
            criterion += "biz_date >= ?  and ";
            parameters.add(transactdatastart);
          }
          if (transactdataend != null&&!transactdataend.equals("")) {
            criterion += "biz_date <= ?  and ";
            parameters.add(transactdataend);
          }
          if (affirmdatastart != null&&!affirmdatastart.equals("")) {
            criterion += "SIGN_DATE >= ?  and ";
            parameters.add(affirmdatastart);
          }
          if (affirmdataend != null&&!affirmdataend.equals("")) {
            criterion += "SIGN_DATE <= ?  and ";
            parameters.add(affirmdataend);
          }
          if (issccueed != null&&!issccueed.equals("")&&!issccueed.equalsIgnoreCase("2")) {
            criterion += "succ_fail = ?  and ";
            parameters.add(issccueed);
          }
          if (criterion.length() != 0){
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }
          
          sql = sql + criterion;
          System.out.println("The Execute sql is: "+sql);

          Query query = session.createSQLQuery(sql);
          for(int i=0;i<parameters.size();i++){
            query.setParameter(i,parameters.get(i));   
          }
          List queryList=query.list();        
          return queryList;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return 0;
     }    
    return relist.size();
    
    
  }

  /**
   * ����ְ����ź����п��Ų�ѯְ����Ϣ
   * @param empid ְ�����
   * @param bankcardid ���п���
   * @return
   */
  public List queryEmpInfoByEmpidAndBank(final String empid,final String bankcardid){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         //empid ְ����ţ�empname ְ��������cardnum���֤�ţ�bankcardid ���п���
          String sql="select empid,empname,cardnum,bankcardid from lmk_temp_sign_table where empid=? and bankcardid=? ";       
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new BigDecimal(empid));
          query.setParameter(1,bankcardid);          
          List queryList=query.list();          
          return queryList;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
     }    
    return relist;
  }
  
  /**
   * ����ְ����ź����п���ɾ��ְ����Ϣ
   * @param empid ְ�����
   * @param bankcardid ���п���
   * @return
   * @throws SQLException 
   */
  public void deleteByEmpidAndBank(final String orgid,final String empid,final String bankcardid) throws SQLException{
    //orgid ��λ��ţ�orgname ��λ���ƣ�empid ְ����ţ�
    //empname ְ��������cardnum���֤�ţ�bankcardid ���п��ţ�
    //biz_date �������ڣ�operater ������Ա
    String sql="delete from lmk_temp_sign_table where orgid=? and empid=? and bankcardid=? ";
    //��õ�ǰsession�����ݿ�����
    Connection conn   = getSession().connection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setBigDecimal(1,new BigDecimal(orgid));
    ps.setBigDecimal(2,new BigDecimal(empid));
    ps.setString(3,bankcardid);
    //ִ�в�ѯ
    ps.execute();
    //�رոö���
    ps.close();
    conn.close();
    getSession().flush();
  }
  /**
   * ���ݵ�λ���ɾ���õ�λ�µ����ݴ���е�������Ϣ
   * @param orgid ��λ���
   * @throws SQLException
   */
  public void deleteAllByOrgId(final String orgid) throws SQLException{
    String sql="delete from lmk_temp_sign_table where orgid=? ";
    //��õ�ǰsession�����ݿ�����
    Connection conn   = getSession().connection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setBigDecimal(1,new BigDecimal(orgid));
    //ִ��
    ps.execute();
    //�رոö���
    ps.close();
    conn.close();
    getSession().flush();
  }
  
//////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  /**
   * ����λ��Ų�ѯ�õ�λ������ʷ���е�����ְ����Ϣ
   * @param orgid
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public List queryEmpInfoByOrgIdFromTemp(final String orgid,final String empid,final String orderBy, final String order, final int start,
      final int pageSize,final int page){
    //ok
    //orgid ��λ��ţ�orgname ��λ���ƣ�empid ְ����ţ�empname ְ��������
    //cardnum���֤�ţ�bankcardid ���п��ţ�biz_date �������ڣ�operater ������Ա
    List relist = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select orgid,orgname,empid,empname,cardnum,bankcardid,biz_date,operater "+
          " from lmk_temp_sign_table ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgid != null&&!orgid.equals("")) {
            criterion += "orgid = ?  and ";
            parameters.add(new BigDecimal(orgid));
          }else{
            return new ArrayList();
          }
          
          if (empid != null&&!empid.equals("")) {
            criterion += "empid = ?  and ";
            parameters.add(new BigDecimal(empid));
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderBy;
          if (ob == null)
            ob = "empid";
          String od = order;
          if (od == null)
            od = "ASC";
          sql = sql + criterion + "order by " + ob + " " + od;
          System.out.println(sql);
          Query query = session.createSQLQuery(sql);
          for(int i=0;i<parameters.size();i++){
            query.setParameter(i,parameters.get(i));   
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);     
          List queryList=query.list();
          
          if(queryList==null||queryList.size()==0){           
            query.setFirstResult(pageSize*(page-2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList=query.list();
          }
          List listreturn=new ArrayList();
          Iterator inter=queryList.iterator();
          while(inter.hasNext())
          {
            Object [] obj=(Object [])inter.next();
            TempDTO dto =new TempDTO();
            //orgid ��λ��ţ�orgname ��λ���ƣ�empid ְ����ţ�empname ְ��������
            //cardnum���֤�ţ�bankcardid ���п��ţ�biz_date �������ڣ�operater ������Ա
            dto.setOrgid((SignTools.parseNull((BigDecimal)obj[0])).toString().trim());
            dto.setOrgname(SignTools.parseNull((String)obj[1]).trim());
            dto.setEmpid((SignTools.parseNull((BigDecimal)obj[2])).toString().trim());
            dto.setEmpname(SignTools.parseNull((String)obj[3]).trim());
            dto.setCardnum(SignTools.parseNull((String)obj[4]).trim());
            dto.setBankcardid(SignTools.parseNull((String)obj[5]).trim());
            dto.setBiz_date(SignTools.parseNull((String)obj[6]).trim());
            dto.setOperater(SignTools.parseNull((String)obj[7]).trim());
            listreturn.add(dto);
          }       
          return listreturn;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
     }    
    return relist;    
  }

   
  /**
   * ����λ��Ų�ѯ�õ�λ������ʷ����ĳʱ��ε�����ְ����Ϣ
   * @param orgid
   * @param time yyyymmdd
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public List queryLogBetweenTime(final String orgid,final String starttime,final String endtime,final String orderBy, final String order, final int start,
      final int pageSize,final int page){
    
    //id ������file_name �ļ����ƣ�operation_tyoe �������ͣ�0�������ļ���1�յ����ģ���date ʱ��
    
    List relist = new ArrayList();
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         //empid ְ����ţ�empname ְ��������cardnum���֤�ţ�bankcardid ���п���
          String sql="select empid,empname,cardnum,bankcardid from lmk_temp_sign_table where orgid=?";
          sql=sql+"order by "+orderBy+" "+order;          
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new BigDecimal(orgid));
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);     
          List queryList=query.list();       
          //��ҳ����
          if(queryList==null||queryList.size()==0){           
            query.setFirstResult(pageSize*(page-2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList=query.list();
          }
          List list=new ArrayList();//���ص�List          
          Iterator inter=queryList.iterator();
          while(inter.hasNext())
          {
            Object [] obj=(Object [])inter.next();
            List list_subordination=new ArrayList();
            list_subordination.add((SignTools.parseNull((BigDecimal)obj[0])).toString().trim());//ְ�����
            list_subordination.add(SignTools.parseNull((String)obj[1]).trim());//ְ������
            list_subordination.add(SignTools.parseNull((String)obj[2]).trim());//���֤��
            list_subordination.add(SignTools.parseNull((String)obj[3]).trim());//���п���
            list.add(list_subordination);
          }       
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
     }    
    return relist;
  }
  
  /**
   * ���ݴ���в�ѯְ����Ϣ
   * @param empid
   * @param orgid
   * @param bankcardid
   * @return
   */
  public List queryUserInfo(final String empid,final String orgid,final String bankcardid){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         //empid ְ����ţ�empname ְ��������cardnum���֤�ţ�bankcardid ���п���
          String sql="select empid,empname,cardnum,bankcardid from lmk_temp_sign_table where orgid=? and empid=? and bankcardid=? ";        
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new BigDecimal(orgid));
          query.setParameter(1, new BigDecimal(empid));
          query.setParameter(2, bankcardid);
          List list=query.list();         
          Iterator inter=list.iterator();
          Object [] obj=(Object [])inter.next();
          List list_subordination=new ArrayList();
          list_subordination.add((SignTools.parseNull((BigDecimal)obj[0])).toString().trim());//ְ�����
          list_subordination.add(SignTools.parseNull((String)obj[1]).trim());//ְ������
          list_subordination.add(SignTools.parseNull((String)obj[2]).trim());//���֤��
          list_subordination.add(SignTools.parseNull((String)obj[3]).trim());//���п���      
          return list_subordination;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
     }    
    
    return relist;
  }
  
//////////////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * ���ݵ�λ��Ų�ѯ��λ����
   * @param orgid ��λ���
   * @return
   */
  public String queryOrgnameByOrgID(final String orgid){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         //empid ְ����ţ�empname ְ��������cardnum���֤�ţ�bankcardid ���п���
          String sql="select b001.name "+
                "from aa001 a001, ba001 b001 "+
                "where a001.orginfo_id = b001.id "+
                "and a001.id = ? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new BigDecimal(orgid));
          List list=query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
    }    
    return relist.size()>0?SignTools.parseNull((String)relist.get(0)).trim():"";
  }
  /**
   * ��ѯ��ʷ��ļ�¼����
   * @param orgid ��λ���
   * @return
   */
  public int queryHistoryCount(final String orgid,final String empid,final String transactdatastart,final String transactdataend,final String affirmdatastart,final String affirmdataend,final String issccueed)throws Exception{

    //id ������orgid ��λ��ţ�orgname ��λ���ƣ�empid ְ����ţ�
    //empname ְ��������cardnum ���֤�ţ�bankcardid ���п��ţ�
    //succ_fail �ɹ���ʶ��biz_date ����ʱ�䣬bank_sure_date ����ȷ��ʱ�䣬
    //operater ������Ա
    List relist = null;

      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select id from lmk_history_sign ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgid != null&&!orgid.equals("")) {
            criterion += "orgid = ?  and ";
            parameters.add(new BigDecimal(orgid));
          }

          if (empid != null&&!empid.equals("")) {
            criterion += "empid = ?  and ";
            parameters.add(new BigDecimal(empid));
          }
          
          if (transactdatastart != null&&!transactdatastart.equals("")) {
            criterion += "biz_date >= ?  and ";
            parameters.add(transactdatastart);
          }
          if (transactdataend != null&&!transactdataend.equals("")) {
            criterion += "biz_date <= ?  and ";
            parameters.add(transactdataend);
          }
          if (affirmdatastart != null&&!affirmdatastart.equals("")) {
            criterion += "bank_sure_date >= ?  and ";
            parameters.add(affirmdatastart);
          }
          if (affirmdataend != null&&!affirmdataend.equals("")) {
            criterion += "bank_sure_date <= ?  and ";
            parameters.add(affirmdataend);
          }
          if (issccueed != null&&!issccueed.equals("")&&!issccueed.equalsIgnoreCase("2")) {
            criterion += "succ_fail = ?  and ";
            parameters.add(issccueed);
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          System.out.println("The Execute sql is: "+sql);

          Query query = session.createSQLQuery(sql);
          for(int i=0;i<parameters.size();i++){
            query.setParameter(i,parameters.get(i));   
          }   
          return query.list();
        }
      });//HibernateCallback
   
    return relist.size();    
  }
  /**
   * ����λ��Ų�ѯ�õ�λ������ʷ���е�����ְ����Ϣ
   * @param orgid
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public List queryHistoryInfo(final String orgid,final String empid,final String transactdatastart,final String transactdataend,final String affirmdatastart,final String affirmdataend,final String issccueed,final String orderby,final String order,final int start,final int pageSize,final int page)
    throws Exception
  {

    //id ������orgid ��λ��ţ�orgname ��λ���ƣ�empid ְ����ţ�
    //empname ְ��������cardnum ���֤�ţ�bankcardid ���п��ţ�
    //succ_fail �ɹ���ʶ��biz_date ����ʱ�䣬bank_sure_date ����ȷ��ʱ�䣬
    //operater ������Ա
    List relist = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select id,orgid,orgname,empid,empname,cardnum,bankcardid,succ_fail,biz_date,SIGN_DATE,OPERATOR,sign from lmk_history_sign ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgid != null&&!orgid.equals("")) {
            criterion += "orgid = ?  and ";
            parameters.add(new BigDecimal(orgid));
          }

          if (empid != null&&!empid.equals("")) {
            criterion += "empid = ?  and ";
            parameters.add(new BigDecimal(empid));
          }
          
          if (transactdatastart != null&&!transactdatastart.equals("")) {
            criterion += "biz_date >= ?  and ";
            parameters.add(transactdatastart);
          }
          if (transactdataend != null&&!transactdataend.equals("")) {
            criterion += "biz_date <= ?  and ";
            parameters.add(transactdataend);
          }
          if (affirmdatastart != null&&!affirmdatastart.equals("")) {
            criterion += "sign_date >= ?  and ";
            parameters.add(affirmdatastart);
          }
          if (affirmdataend != null&&!affirmdataend.equals("")) {
            criterion += "sign_date <= ?  and ";
            parameters.add(affirmdataend);
          }
          if (issccueed != null&&!issccueed.equals("")&&!issccueed.equalsIgnoreCase("2")) {
            criterion += "succ_fail = ?  and ";
            parameters.add(issccueed);
          }
          if (criterion.length() != 0){
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          }
          
          
          
          String ob = orderby;
          if (ob == null)
            ob = "orgid";
          String od = order;
          if (od == null)
            od = "ASC";
          sql = sql + criterion + "order by " + ob + " " + od;
          System.out.println("The Execute sql is: "+sql);

          Query query = session.createSQLQuery(sql);
          for(int i=0;i<parameters.size();i++){
            query.setParameter(i,parameters.get(i));   
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);     
          List queryList=query.list();
          
          if(queryList==null||queryList.size()==0){           
            query.setFirstResult(pageSize*(page-2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList=query.list();
          }
          List listreturn=new ArrayList();
          Iterator inter=queryList.iterator();
          while(inter.hasNext())
          {
            //select id,orgid,orgname,empid,empname,cardnum,bankcardid,
            //succ_fail,biz_date,SIGN_DATE,OPERATOR,sign
            Object [] obj=(Object [])inter.next();
            HistoryDTO dto =new HistoryDTO();

            dto.setId(SignTools.parseNull((BigDecimal)obj[0]).toString().trim());
            dto.setOrgid(SignTools.parseNull((BigDecimal)obj[1]).toString().trim());
            dto.setOrgname(SignTools.parseNull((String)obj[2]).trim());
            dto.setEmpid(SignTools.parseNull((BigDecimal)obj[3]).toString().trim());
            dto.setEmpname(SignTools.parseNull((String)obj[4]).trim());
            dto.setCardnum(SignTools.parseNull((String)obj[5]).trim());
            dto.setBankcardid(SignTools.parseNull((String)obj[6]).trim());
            dto.setSucc_fail(BatchSignTools.transformSucc_Fail(SignTools.parseNull((String)obj[7]).trim()));
            dto.setBiz_date(SignTools.parseNull((String)obj[8]).trim());
            dto.setBank_sure_date(SignTools.parseNull((String)obj[9]).trim());
            dto.setOperater(SignTools.parseNull((String)obj[10]).trim());
            dto.setSign(SignTools.parseNull((BigDecimal)obj[11]).toString().trim());
            listreturn.add(dto);
          }       
          return listreturn;
        }
      });//HibernateCallback
  
    return relist;    
  }
  /**
   * ����λ��Ų�ѯ�õ�λ������ʷ���е�����ְ����Ϣ
   * @param orgid
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return
   */
  public List queryLogInfo(final String type,final String starttime,final String endtime,final String orderby,final String order,final int start,final int pageSize,final int page)
    throws Exception
  {
    List relist = null;
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select id,FILE_NAME,OPERATION_TYPE,OP_DATE FROM LMK_OPERATION_TABLE ";
          Vector parameters = new Vector();
          String criterion = "";
           
          if (type != null&&!type.equals("")&&!type.equalsIgnoreCase("2")) {
            criterion += "OPERATION_TYPE = ?  and ";
            parameters.add(type);
          }
          if (starttime != null&&!starttime.equals("")) {
            criterion += "OP_DATE >= ?  and ";
            parameters.add(Timestamp.valueOf(starttime.substring(0,4)+"-"+starttime.substring(4,6)+"-"+starttime.substring(6,8)+" 0:0:0"));
          }
          if (endtime != null&&!endtime.equals("")) {
            criterion += "OP_DATE <= ?  and ";
            parameters.add(Timestamp.valueOf(endtime.substring(0,4)+"-"+endtime.substring(4,6)+"-"+endtime.substring(6,8)+" 23:59:59"));
          }
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          String ob = orderby;
          if (ob == null)
            ob = "FILE_NAME";
          String od = order;
          if (od == null)
            od = "ASC";
          sql = sql + criterion + "order by " + ob + " " + od;
          System.out.println("The Execute sql is: "+sql);

          Query query = session.createSQLQuery(sql);
          for(int i=0;i<parameters.size();i++){
            query.setParameter(i,parameters.get(i));   
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);     
          List queryList=query.list();
          
          if(queryList==null||queryList.size()==0){           
            query.setFirstResult(pageSize*(page-2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList=query.list();
          }
          List listreturn=new ArrayList();
          Iterator inter=queryList.iterator();
          while(inter.hasNext())
          {
            //ID FILE_NAME OPERATION_TYPE OP_DATE
            Object [] obj=(Object [])inter.next();
            LogDTO dto =new LogDTO();

            dto.setId(SignTools.parseNull((BigDecimal)obj[0]).toString().trim());
            dto.setFile_name(SignTools.parseNull((String)obj[1]).toString().trim());
            dto.setOperation_type(BatchSignTools.transformFile_Type(SignTools.parseNull((String)obj[2]).toString().trim()));
            String opdate=((Timestamp)obj[3]).toString().trim();
            dto.setOp_date(opdate.substring(0,opdate.length()-2));
            listreturn.add(dto);
          }       
          return listreturn;
        }
      });//HibernateCallback
 
    return relist;    
  }
  
  /**
   * ������������ѯ��־����Ŀ����
   * @param type
   * @param starttime
   * @param endtime
   * @return
   * @throws Exception
   */
  public int queryLogCount(final String type,final String starttime,final String endtime)
  throws Exception
  {
    List relist = null;
    relist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql="select id,FILE_NAME,OPERATION_TYPE,OP_DATE FROM LMK_OPERATION_TABLE ";
        Vector parameters = new Vector();
        String criterion = "";
         
        if (type != null&&!type.equals("")&&!type.equalsIgnoreCase("2")) {
          criterion += "OPERATION_TYPE = ?  and ";
          parameters.add(type);
        }
        if (starttime != null&&!starttime.equals("")) {
          criterion += "OP_DATE >= ?  and ";
          parameters.add(Timestamp.valueOf(starttime.substring(0,4)+"-"+starttime.substring(4,6)+"-"+starttime.substring(6,8)+" 0:0:0"));
        }
        if (endtime != null&&!endtime.equals("")) {
          criterion += "OP_DATE <= ?  and ";
          parameters.add(Timestamp.valueOf(endtime.substring(0,4)+"-"+endtime.substring(4,6)+"-"+endtime.substring(6,8)+" 0:0:0"));
        }
        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        
        sql=sql+criterion;
        System.out.println("The Execute sql is: "+sql);
        Query query = session.createSQLQuery(sql);
        for(int i=0;i<parameters.size();i++){
          query.setParameter(i,parameters.get(i));   
        }
        return query.list();
      }
    });//HibernateCallback

   return relist.size();    
  }

  /**
   * �ӻ�����ж�����
   * @return
   */
  public List readFromTemp(){
    //ok
    //orgid ��λ��ţ�orgname ��λ���ƣ�empid ְ����ţ�empname ְ��������
    //cardnum���֤�ţ�bankcardid ���п��ţ�biz_date �������ڣ�operater ������Ա
    List relist = null;
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql="select orgid,orgname,empid,empname,cardnum,bankcardid,biz_date,operater "+
          " from lmk_temp_sign_table ";
          //���|�ֿ��˿���|�ֿ�������|�ֿ������֤����|�������ʻ���|��ע|
          Query query = session.createSQLQuery(sql);
          List queryList=query.list();
          List listreturn=new ArrayList();
          Iterator inter=queryList.iterator();
          while(inter.hasNext())
          {
            Object [] obj=(Object [])inter.next();
            TempDTO dto=new TempDTO();
            //orgid,orgname,empid,empname,cardnum,bankcardid,biz_date,operater
            dto.setOrgid(SignTools.parseNull((BigDecimal)obj[0]).toString().trim());
            dto.setOrgname(SignTools.parseNull((String)obj[1]).trim());
            dto.setEmpid(SignTools.parseNull((BigDecimal)obj[2]).toString().trim());
            dto.setEmpname(SignTools.parseNull((String)obj[3]).trim());
            dto.setCardnum(SignTools.parseNull((String)obj[4]).trim());
            dto.setBankcardid(SignTools.parseNull((String)obj[5]).trim());
            dto.setBiz_date(SignTools.parseNull((String)obj[6]).trim());
            dto.setOperater(SignTools.parseNull((String)obj[7]).trim());
            listreturn.add(dto);
          }       
          return listreturn;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return null;
    }
    return relist;    
  }
  
  /**
   * ��������
   * @param list
   * @throws Exception
   */
  public void insertHistory(HistoryDTO dto)throws Exception{
    String sql="insert into lmk_history_sign(orgid,orgname,empid,empname,cardnum,bankcardid,succ_fail,biz_date,sign_date,operator,sign)"+
    " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    //��õ�ǰsession�����ݿ�����
    Connection conn   = getSession().connection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setBigDecimal(1,new BigDecimal(dto.getOrgid()));
    ps.setString(2,dto.getOrgname());
    ps.setBigDecimal(3,new BigDecimal(dto.getEmpid()));
    ps.setString(4,dto.getEmpname());
    ps.setString(5,dto.getCardnum());
    ps.setString(6,dto.getBankcardid());     
    ps.setString(7,dto.getSucc_fail());
    ps.setString(8,dto.getBiz_date());
    ps.setString(9,dto.getBank_sure_date());
    ps.setString(10,dto.getOperater());
    ps.setBigDecimal(11,new BigDecimal(dto.getSign()));
    ps.execute(); //ִ�в�ѯ 
    ps.close();//�رոö���
    conn.close();
    getSession().flush();
  }
  
  
  /**
   * ���ļ���־���������
   * @param dto
   * @throws Exception
   */
  public void insertLog(LogDTO dto)throws Exception{
    String sql="insert into LMK_OPERATION_TABLE(FILE_NAME,OPERATION_TYPE,OP_DATE)"+
    " VALUES (?,?,?)";
    //��õ�ǰsession�����ݿ�����
    Connection conn   = getSession().connection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1,dto.getFile_name());
    ps.setString(2,dto.getOperation_type());
    ps.setTimestamp(3,BatchSignTools.getTimestamp());
    ps.execute(); //ִ�в�ѯ 
    ps.close();//�رոö���
    conn.close();
    getSession().flush();
  }



  public void updateHistory(String sign,String s_f) throws Exception{
    //update lmk_temp_sign_table set bankcardid= ? where orgid=? and empid=? and bankcardid=?
    String sql="update lmk_history_sign set succ_fail=?,SIGN_DATE=? where sign= ? ";
    //��õ�ǰsession�����ݿ�����
    Connection conn   = getSession().connection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1,s_f.substring(1));
    ps.setString(2,BatchSignTools.getTodayDateString());
    ps.setBigDecimal(3,new BigDecimal(sign));
    ps.executeUpdate(); //ִ�в�ѯ 
    ps.close();//�رոö���
    conn.close();
    getSession().flush();
  }
  
  
  

  /**
   * ͨ��Ψһ��ʶ����ʷ�����Ƿ��и�ְ����Ϣ
   * @param sign
   * @return
   */
  public boolean isHaveSignInHistory(final String sign){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="select sign from LMK_HISTORY_SIGN where sign =? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new BigDecimal(sign));
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return false;
    }    
    return relist.size()>0?true:false;//��ʷ�����Ƿ���ڸ�ְ����Ϣ
    
  }
  
  /**
   * ����ݴ��
   * @throws Exception
   */
  public void deleteTemp() throws Exception{
    //update lmk_temp_sign_table set bankcardid= ? where orgid=? and empid=? and bankcardid=?
    String sql="delete from LMK_TEMP_SIGN_TABLE ";
    //��õ�ǰsession�����ݿ�����
    Connection conn   = getSession().connection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.execute(); //ִ�в�ѯ 
    ps.close();//�رոö���
    conn.close();
    getSession().flush();
  }
  
  /**
   * �ӻ����ɾ��ָ����Ŀ
   * @param empid
   * @param bankcardid
   * @throws Exception
   */
  public void deleteTemp(final String empid,final String bankcardid) throws Exception{
    //update lmk_temp_sign_table set bankcardid= ? where orgid=? and empid=? and bankcardid=?
    String sql="delete from LMK_TEMP_SIGN_TABLE where empid=? and bankcardid=?";
    //��õ�ǰsession�����ݿ�����
    Connection conn   = getSession().connection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setBigDecimal(1, new BigDecimal(empid));
    ps.setString(2, bankcardid);
    ps.execute(); //ִ�в�ѯ 
    ps.close();//�رոö���
    conn.close();
    getSession().flush();
  }
  /**
   * ɾ��֮ǰ��ǩԼ��Ϣ
   */
  public void deleteSignBySign(String sign) throws Exception{
    //update lmk_temp_sign_table set bankcardid= ? where orgid=? and empid=? and bankcardid=?
    String sql="delete from RQ001 where sign= ? ";
    //��õ�ǰsession�����ݿ�����
    Connection conn   = getSession().connection();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setBigDecimal(1,new BigDecimal(sign));
    ps.execute(); //ִ�в�ѯ 
    ps.close();//�رոö���
    conn.close();
    getSession().flush();
  }
  /**
   * �鿴������е���û��ָ����Ŀ
   * @param empid
   * @param bankcardid
   * @return
   */
  public boolean isHaveSignInTemp(final String empid,final String bankcardid){
    List relist = new ArrayList();
    try{
      relist = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
         
          String sql="select empid,bankcardid from lmk_temp_sign_table where empid =? and bankcardid=? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new BigDecimal(empid));
          query.setParameter(1, bankcardid);
          List list =query.list();
          return list;
        }
      });//HibernateCallback
    }catch(Exception e){
         e.printStackTrace();
         return false;
    }    
    return relist.size()>0?true:false;
  }
  

  /**
   * ɾ���ɹ���־Ϊ'δ֪'����ʷ�û���Ϣ
   */
  public void deleteHistory(){
    //update lmk_temp_sign_table set bankcardid= ? where orgid=? and empid=? and bankcardid=?
    try{
      String sql="delete from lmk_history_sign where succ_fail=2";
      //��õ�ǰsession�����ݿ�����
      Connection conn   = getSession().connection();
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.execute(); //ִ�в�ѯ 
      ps.close();//�رոö���
      conn.close();
      getSession().flush();
    }
    catch(SQLException e){
      e.printStackTrace();
    }
  }
  
  
  /**
   * ��ջ����
   */
  public void deleteAllTemp(){
    //update lmk_temp_sign_table set bankcardid= ? where orgid=? and empid=? and bankcardid=?
    try{
      String sql="delete from LMK_TEMP_SIGN_TABLE";
      //��õ�ǰsession�����ݿ�����
      Connection conn   = getSession().connection();
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.execute(); //ִ�в�ѯ 
      ps.close();//�رոö���
      conn.close();
      getSession().flush();
    }
    catch(SQLException e){
      e.printStackTrace();
    }
  }
  
  
//--------------------------------------------------------------------
//����ǩԼ END
//--------------------------------------------------------------------
  

}
