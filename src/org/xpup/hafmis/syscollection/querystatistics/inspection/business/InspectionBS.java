package org.xpup.hafmis.syscollection.querystatistics.inspection.business;

import java.math.BigDecimal;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.common.dao.InspectionDAO;
import org.xpup.hafmis.syscollection.querystatistics.inspection.bsinterface.IInspectionBS;
import org.xpup.hafmis.syscollection.querystatistics.inspection.form.InspectionFindAF;
import org.xpup.hafmis.syscollection.querystatistics.inspection.form.InspectionShowAF;

public class InspectionBS implements IInspectionBS{
  protected InspectionDAO inspectionDAO=null;

  public void setInspectionDAO(InspectionDAO inspectionDAO) {
    this.inspectionDAO = inspectionDAO;
  }

  
  public InspectionShowAF querygjjpayrate(Pagination pagination){
    InspectionShowAF af=new InspectionShowAF();
    InspectionFindAF iaf=(InspectionFindAF) pagination.getQueryCriterions().get("search");
    String date=iaf.getDate();
    String officeid=iaf.getOfficecode();
    String date1=iaf.getDate();
    String officeid1=iaf.getOfficecode();
    int lastyeardate=Integer.parseInt(date)-1;
    String subjectCode = "";
    String subjectCode2 = "";
    String officeName = "";
    if(officeid!=null && "".equals(officeid)){//�б���
      subjectCode = "1210101";//�б�����Ŀ����
      subjectCode2 = "31101";
    }
    if(officeid!=null && "".equals(officeid)){//��ʯ��
      subjectCode = "1210102";//��ʯ�ſ�Ŀ����
      subjectCode2 = "31102";
    }
    if(officeid!=null && "".equals(officeid)){//����Ȧ
      subjectCode = "1210103";//����Ȧ��Ŀ����
      subjectCode2 = "31103";
    }
    if(officeid!=null && "".equals(officeid)){//����
      subjectCode = "12120104";//���ݿ�Ŀ����
      subjectCode2 = "31104";
    }
    String lastdate=new Integer(lastyeardate).toString();//����
    String actualpay=inspectionDAO.actualpay(date, officeid,subjectCode);//����ס��������ʵ�ʽɴ��
    String baseshouldpay = inspectionDAO.querythisyearshouldpay(date, officeid);//(����ס��������Ӧ�ɴ�� ����δ��12)
//  ����ס��������Ӧ�ɴ��
    String thisyearshouldpay=(new BigDecimal(baseshouldpay).multiply(new BigDecimal(12))).toString();
//    String yearlastpersonloanbalance=inspectionDAO.yearlastpersonloanbalance(date, officeid, date1, officeid1);//��ĩס����������˴������
    String yearlastpersonloanbalance=inspectionDAO.yearlastpersonloanbalance_(date, officeid, subjectCode);//��ĩס����������˴������
    //System.out.println("���˴�����"+yearlastpersonloanbalance);
//    String personloangivemoney=inspectionDAO.personloangivemoney(date, officeid);//����ס����������˴���Ŷ�
    String personloangivemoney=inspectionDAO.personloangivemoney_(date, officeid);//(����ס����������˴���Ŷ�)
    //System.out.println("����ס����������˴���Ŷ�:"+personloangivemoney);
//    String personloanoverduepay=inspectionDAO.personloanoverduepay(date, officeid);//��ĩס����������˴������ڶ�
    String personloanoverduepay=inspectionDAO.personloanoverduepay_(date, officeid);//  (��ĩס����������˴������ڶ�)
//    String incrementincome=inspectionDAO.incrementincome(date, officeid);//����ס����������ֵ����
    String incrementincome=inspectionDAO.incrementincome_(date, officeid);//(����ס����������ֵ����)
//    String monthpaybalance=inspectionDAO.monthpaybalance(date, officeid);//����ס����������ƽ���ɴ����
    String monthpaybalance = baseshouldpay;//(����ס����������ƽ���ɴ����)
    String lastactualpay=inspectionDAO.actualpay(lastdate, officeid,subjectCode);//���깫����ʵ�ʽɴ��
    String lastpersonloangivemoney=inspectionDAO.personloangivemoney(lastdate, officeid);//����ס����������˴���Ŷ�
    //System.out.println("����ס����������˴���Ŷ�:"+lastpersonloangivemoney);
    String yearlastpaybalance=inspectionDAO.yearlastpaybalance(date, officeid,subjectCode);//��ĩס��������ɴ����
    //System.out.println("��ĩס��������ɴ���"+yearlastpaybalance);
    if(officeid!=null && !"".equals(officeid)){
      officeName = inspectionDAO.queryOfficeName(officeid);
    }
    //���ס��������ɴ���
    BigDecimal a1=new BigDecimal(actualpay);//����ɴ��
    BigDecimal a2=new BigDecimal(thisyearshouldpay);
    BigDecimal yearpayrate=new BigDecimal(0.00);
    if(a1!=null && a2.intValue()!=0){
      yearpayrate = a1.divide(a2, 2, BigDecimal.ROUND_HALF_UP);
    }
    // ס��������ɴ��������
    BigDecimal a3=new BigDecimal(lastactualpay);
    BigDecimal gjjpayaddrate=new BigDecimal(0.00);
    if(a1!=null && a3.intValue()!=0){
      gjjpayaddrate = a1.divide(a3, 2, BigDecimal.ROUND_HALF_UP);
    }
    // ס����������˴������
    BigDecimal b1=new BigDecimal(yearlastpersonloanbalance);
    BigDecimal b2=new BigDecimal(yearlastpaybalance);
    BigDecimal personloanrate=new BigDecimal(0.0);
    if(b1!=null && b2.intValue()!=0){
      personloanrate = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }
    //ס����������˴���������
    BigDecimal c1=new BigDecimal(personloangivemoney);
    BigDecimal c2=new BigDecimal(lastpersonloangivemoney);
   
    BigDecimal personloanaddrate=new BigDecimal(0.00);   
    if(c1!=null && c2.intValue()!=0){
      personloanaddrate = c1.divide(c2, 2, BigDecimal.ROUND_HALF_UP);
    }
    // ס����������˴���������
    BigDecimal d1=new BigDecimal(personloanoverduepay);
    BigDecimal d2=new BigDecimal(yearlastpersonloanbalance);
   
    BigDecimal personloanoverduerate=new BigDecimal(0.00);   
    if(d1!=null && d2.intValue()!=0){
      personloanoverduerate = d1.divide(d2, 2, BigDecimal.ROUND_HALF_UP);
    }
    // ס����������ֵ������
    BigDecimal e1=new BigDecimal(incrementincome);
    BigDecimal e2=new BigDecimal(thisyearshouldpay);
    
    e2 = e2.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
    BigDecimal incermentincomerate=new BigDecimal(0.00);
    if(e1!=null && e2.intValue()!=0){
      incermentincomerate = e1.divide(e2, 2, BigDecimal.ROUND_HALF_UP);
    }
    
    //ס��������������׼���������
    BigDecimal f1=new BigDecimal(incrementincome);
    BigDecimal f2=new BigDecimal(monthpaybalance);
    
    BigDecimal loanmoneyforriskready=new BigDecimal(0.00);
    if(f1!=null && f2.intValue()!=0){
      loanmoneyforriskready = f1.divide(f2, 2, BigDecimal.ROUND_HALF_UP);
    }
    
    af.setGjjaddincome((incermentincomerate.multiply(new BigDecimal(100))).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setGjjpayaddrate((gjjpayaddrate.subtract(new BigDecimal(1))).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setGjjpayrate((yearpayrate.multiply(new BigDecimal(100))).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setPersonloan((personloanrate.multiply(new BigDecimal(100))).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setPersonloanaddrate((personloanaddrate.subtract(new BigDecimal(1))).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setPersonloanoverduerate((personloanoverduerate.multiply(new BigDecimal(100))).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setLoanmoneyforriskready((loanmoneyforriskready.multiply(new BigDecimal(100))).setScale(0,BigDecimal.ROUND_HALF_UP)+"%");
    af.setOfficeName(officeName);
    af.setDate(date);
    return af;
  }
}
