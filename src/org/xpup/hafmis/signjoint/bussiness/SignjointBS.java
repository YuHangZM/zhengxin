package org.xpup.hafmis.signjoint.bussiness;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.xpup.hafmis.signjoint.util.SignTools;
import org.apache.commons.beanutils.BeanUtils;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.signjoint.dao.SignDAO;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.dto.BaseEmpInfoDTO;

import org.xpup.hafmis.signjoint.dto.HistoryDTO;
import org.xpup.hafmis.signjoint.dto.LogDTO;
import org.xpup.hafmis.signjoint.dto.RecieveFileDTO;
import org.xpup.hafmis.signjoint.dto.RequestSignDTO;
import org.xpup.hafmis.signjoint.dto.SignImportBodyDTO;
import org.xpup.hafmis.signjoint.dto.SignImportHeadDTO;
import org.xpup.hafmis.signjoint.dto.BaseInfoDTO;
import org.xpup.hafmis.signjoint.dto.TempDTO;
import org.xpup.hafmis.signjoint.entity.Sign;
import org.xpup.hafmis.signjoint.util.SignTools;

public class SignjointBS implements ISignjointBS{

  private static final String SIGN_UP_NUMBER="1001";//ǩԼ������
  private static final String QUERY_BALANCE_NUMBER="1003";//�������ʻ���������
  private static final String QUERY_LIST_BALANCE_NUBBER="1004";//�������ʻ������ϸ������
  private static final String QUERY_BORROW_BALANCE_NUMBER="1005";//������������
  private static final String QUERY_BORROW_LIST_BALANCE="1006";//���������ϸ������
  private SignDAO signdao=null;
  public SignDAO getSigndao() {
    return signdao;
  }
  public void setSigndao(SignDAO signdao) {
    this.signdao = signdao;
  }
  /**
   * ����Sign����
   * @param dto �����ǩԼ��Ϣ��SignDTO
   * @return ����ǩԼ���
   */
  public String saveSign(RequestSignDTO dto)
  {
   try{
    if(dto.getCardnum().length()==18){//���Ϊ18λ���֤��
      String card15=SignTools.conversionTo15DigitCard_num(dto.getCardnum());//ת��Ϊ15λ���֤��
      if(signdao.isHaveUserInfo(dto.getEmpid(), dto.getName(), dto.getCardnum())){
        return sign(dto);
      }else if(signdao.isHaveUserInfo(dto.getEmpid(), dto.getName(),card15)){
        dto.setCardnum(card15);
        return sign(dto);
      }else{
        return SignTools.getInfo_07(SIGN_UP_NUMBER);
      }
    }else {//���Ϊ15λ���֤��
      String card18=signdao.to18Card_num(dto.getCardnum());//ת��Ϊ18λ���֤��
      if(signdao.isHaveUserInfo(dto.getEmpid(), dto.getName(), dto.getCardnum())){
        return sign(dto);
      }else if(signdao.isHaveUserInfo(dto.getEmpid(), dto.getName(),card18)){
        dto.setCardnum(card18);
        return sign(dto);
      }else{
        return SignTools.getInfo_07(SIGN_UP_NUMBER);
      }
    }
   }//try catch
   catch(Exception e){
     e.printStackTrace();
     return SignTools.getFailedInfo(SIGN_UP_NUMBER);
   }
 }
  
  /**
   * �����ݿ�������� ��saveSign����ʹ��
   * @param dto �����ǩԼ��Ϣ��SignDTO
   * @return ����ǩԼ���
   */
  private String sign(RequestSignDTO dto){
    List list_sign=new ArrayList();
    //��ȡ����
    Sign sign=new Sign();
    sign.setBank_card(dto.getBanknum());
    sign.setCard_num(dto.getCardnum());
    sign.setEmpid(dto.getEmpid());
    sign.setName(dto.getName());
    Integer i=signdao.save(sign);//��������
    if(i.intValue()!=0){//�Ƿ����ɹ�
      list_sign.add(i.toString());
      return SignTools.getSuccessInfo(SIGN_UP_NUMBER,list_sign);
    }
    else{//ʧ��
      return SignTools.getFailedInfo(SIGN_UP_NUMBER);
    }
  }
  
  
  
  
  
  
  /**
   * ����Ψһ��ʶ������ǩԼ��Ϣ
   * @param sign Ψһ��ʶ
   * @return ����ǩԼ����
   */
  public Sign querySignBySignNum(String sign) {
    Sign s=null;
    List list=signdao.getBySign(sign);
    if((list.size()!=0)&&(list!=null))
     return s=(Sign)list.get(0);
    return s;
  }
  /**
   * ��ѯ�������ʻ����
   * @param dto ����Ż�����Ϣ��DTO
   * @return ���������Ϣ
   */
  public String queryBalance(BaseInfoDTO dto) {
    List list_1=new ArrayList();
    List list_2=new ArrayList();
    List list_3=new ArrayList();
    List list_4=new ArrayList();
    List list_5=new ArrayList();
    List list_main=new ArrayList();
    List list_subordination=new ArrayList();
    String empid=null;
    empid=dto.getEmpid();
    String startdate=SignTools.getCheckOutAccrual()[0].trim();
    String enddate=SignTools.getCheckOutAccrual()[1].trim();
    if(empid!=null)//���Ψһ��ʶû�в�ѯʧ��
    {
      if(!signdao.isHaveEmp_id(empid)){//���ݿ����Ƿ����ְ�����
        return SignTools.getInfo_08(QUERY_BALANCE_NUMBER);
      }
      list_1=signdao.getBalancePartOne(empid);
      list_2=signdao.getBalancePartTwo(empid);
      list_3=signdao.getBalancePartThree(empid, startdate, enddate);
      list_4=signdao.getBalancePartFour(empid);
      list_5=signdao.getBalancePartFive(empid);
      //�����Ҫ��Ϣ
      list_main.add(list_5.get(0));//���
      list_main.add(list_5.get(1));//����
      list_main.add(list_5.get(2));//֤������
      list_main.add(list_5.get(3));//��λ����
      list_main.add(list_2.get(0));//�½ɴ��
      //��Ӹ�����Ϣ
      list_subordination.add(list_5.get(4));//�����ʺ�
      list_subordination.add(list_5.get(5));//��λ�ʺ�
      list_subordination.add(list_5.get(6));//֤������
      list_subordination.add(list_5.get(7));//�ʻ�״̬
      list_subordination.add(list_5.get(8));//�������
      list_subordination.add(list_5.get(9));//�������
      list_subordination.add(list_1.get(0));//�������
      list_subordination.add(list_1.get(1));//������Ϣ
      list_subordination.add(list_2.get(0));//�½ɴ��
      list_subordination.add(list_2.get(1));//���и��˲���
      list_subordination.add(list_4.get(0));//�ϴνɴ�����
      list_subordination.add(list_5.get(10));//�´��½ɴ��
      list_subordination.add(list_5.get(11));//���и��˲���
      list_subordination.add(list_3.get(0));//�����ɺϼ�
      list_subordination.add(list_3.get(1));//���겹�ɺϼ�                                     
      list_subordination.add(list_3.get(2));//����֧ȡ�ϼ�
      list_subordination.add(list_5.get(12));//��������
      list_subordination.add(SignTools.getNextXMonth((String)list_4.get(0), 1));//�´�Ӧ���·�
      return SignTools.getSuccessInfo(QUERY_BALANCE_NUMBER,list_main,list_subordination);
    }//empid!=null
    else{//Ψһ��ʶ��ѯʧ��
      return SignTools.getFailedInfo(QUERY_BALANCE_NUMBER);
    }
  }
  /**
   * ��ѯ�����ϸ
   * @param dto ����Ż�����Ϣ��DTO,startdate��ʼʱ��,enddate����ʱ��
   * @return ���������ϸ��Ϣ
   */
  public String queryListBalance(BaseInfoDTO dto,String startdate,String enddate){
    /*ת�����ڵķ���
    try {
      BusiTools.dateToString(BusiTools.stringToUDate("2007-02-29", BusiConst.PUB_LONG_YMD_PATTERN),BusiConst.PUB_LONG_YMD_PATTERN);
    } catch (ParseException e) {
      e.printStackTrace();
    }*/
    try {
     //String start=BusiTools.dateToString(BusiTools.stringToUDate(startdate, BusiConst.PUB_LONG_DATE_PATTERN),BusiConst.PUB_LONG_YMD_PATTERN);
     //String end=BusiTools.dateToString(BusiTools.stringToUDate(enddate, BusiConst.PUB_LONG_DATE_PATTERN),BusiConst.PUB_LONG_YMD_PATTERN);
     String start=startdate;
     String end=enddate;
     int startint=Integer.parseInt(start);
     int endint=Integer.parseInt(end);
     int isbigger=endint-startint;
     start=Integer.toString(startint);
     end=Integer.toString(endint);
     //int isbigger=Integer.getInteger(end).intValue()-Integer.getInteger(start).intValue();//��ʼ�����Ƿ�С�ڽ�������
     List temp=new ArrayList();
     List list_balance=new ArrayList();
     StringBuffer buf=new StringBuffer();
     String empid=null;
     
     List list_main=new ArrayList();//�����'|'�ָ�����Ҫ��Ϣ
     List list_subordination=new ArrayList();//�����','�ָ��ĸ�����Ϣ
     
     empid=dto.getEmpid();
     if((empid!=null)&&(isbigger>=0))//���Ψһ��ʶû�в�ѯʧ��
     {
       if(SignTools.dateBiggerThanDateXMonth(enddate,startdate)>6){//�����ѯ��Χ����6����
         return SignTools.getInfo_03(QUERY_LIST_BALANCE_NUBBER);
       }
       if(!signdao.isHaveEmp_id(empid)){//���ݿ����Ƿ����ְ�����
         return SignTools.getInfo_08(QUERY_LIST_BALANCE_NUBBER);
       }

       list_balance=signdao.getListBalance(empid, start, end);
       
       String empinfo=SignTools.combinationWithAllComma(signdao.getEmpInfoByEmpID(empid));//�Ȼ�õ�λ�ʺţ���λ���ƣ������ʺţ�����
       empinfo=empinfo.substring(0, empinfo.length()-1);
       
       temp.add(empinfo);
       temp.add(list_balance.get(0));
       
       buf.append(SignTools.getSuccessInfo(QUERY_LIST_BALANCE_NUBBER,temp));
       if(Integer.parseInt(((String)list_balance.get(0)).trim())!=0){
          temp.clear();
       int size=list_balance.size();
        for(int i=1;i<size;i++){
          temp=(List)list_balance.get(i);
          list_subordination.add(temp.get(0));//����
          list_subordination.add(temp.get(1));//ժҪ
          list_subordination.add(temp.get(2));//������
          list_subordination.add(temp.get(3));//���
          //����Ϊ������Ϣ
          //list_subordination.add(temp.get(4));//��λ�ʺ�
          //list_subordination.add(temp.get(5));//��λ����
          //list_subordination.add(temp.get(6));//�����ʺ�
          //list_subordination.add(temp.get(7));//����        
          list_subordination.add(temp.get(8));//�����ʶ
          list_subordination.add(temp.get(9));//������ж���
          list_subordination.add(temp.get(10));//�������
          buf.append(SignTools.Combination(list_main));
          buf.append(SignTools.combinationWithAllComma(list_subordination));
          temp.clear();
          //list_main.clear();
          list_subordination.clear();
         }
        String s=buf.toString();
        return s.substring(0, s.length()-1)+"|";
       }//�����ϸ��ĿΪ0
       else{
        buf.append("|");
        return buf.toString();
       }
     }//empid!=null isbigger>=0
     else{//��ѯʧ��
       return SignTools.getFailedInfo(QUERY_LIST_BALANCE_NUBBER);
     }
    } catch (Exception e) {
      e.printStackTrace();
      return SignTools.getFailedInfo(QUERY_LIST_BALANCE_NUBBER);
    }
  }


  /**
   * ��ѯ�������
   * @param dto ����Ż�����Ϣ��DTO
   * @return ���ش��������Ϣ
   */
  public String queryBorrowBalance(BaseInfoDTO dto){
   try{
    List list_balance=new ArrayList();
    String card_num=dto.getCard_num();
    String name=dto.getName();
    List list_main=new ArrayList();
    List list_subordination=new ArrayList();
    
    if((card_num!=null)&&(name!=null))//���Ψһ��ʶû�в�ѯʧ��
    {
      if(!signdao.isHaveNameAndCard_num(name,card_num)){//ְ�����������֤���Ƿ����
        return SignTools.getInfo_02(QUERY_BORROW_BALANCE_NUMBER);
      }
      String date=SignTools.getLoanRepayYearMonth(signdao.getLoanRepayDay(name, card_num));
      if(date.equalsIgnoreCase("")){//����û�û����
        return SignTools.getInfo_02(QUERY_BORROW_BALANCE_NUMBER);
      }
      int corb=signdao.getBorrowBalanceByBankORCenter();
      if(corb==0){
        return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
      }
      else if(corb==1){//������Ϊ��
        list_balance=signdao.getBorrowBalanceByCenter(name, card_num, date);
      }
      else {//������Ϊ��
        //��õ�ǰ����
        Date d = new Date();
        String year = Integer.toString(d.getYear()+1900);
        String month = Integer.toString(d.getMonth()+1);
        if(month.length()==1) month="0"+month;
        
        list_balance=signdao.getBorrowBalanceByBank(name, card_num, SignTools.getPreviousXMonth(year+month, 1));
      }
      //�������|����|���֤|��λ����|�»����|�����ʺ�,�������,�����,�ۼƻ����,��������,ʣ������,���ڿۿ���,���ڻ�����|
      
      
      if((list_balance.size()!=0)&&(list_balance!=null)){//������û�в�ѯʧ��
        if(Integer.parseInt((String)list_balance.get(0))==0){//��������ѻ���
      //������������������������ڿۿ��ա����ڿۿ�����Ϊ0
          list_main.add(list_balance.get(0));//���
          list_main.add(list_balance.get(1));//����
          list_main.add(list_balance.get(2));//���֤
          list_main.add(list_balance.get(3));//��λ����
          list_main.add("0");//�»����
          
          list_subordination.add(list_balance.get(5));//�����ʺ�
          list_subordination.add("0");//�������
          list_subordination.add(list_balance.get(7));//�����
          list_subordination.add(list_balance.get(8));//�ۼƻ����
          list_subordination.add("0");//��������
          list_subordination.add(list_balance.get(10));//ʣ������
          list_subordination.add("0");//���ڿۿ���
          list_subordination.add("0");//���ڻ�����
          return SignTools.getSuccessInfo(QUERY_BORROW_BALANCE_NUMBER, list_main, list_subordination);
        }
        list_main.add(list_balance.get(0));//���
        list_main.add(list_balance.get(1));//����
        list_main.add(list_balance.get(2));//���֤
        list_main.add(list_balance.get(3));//��λ����
        list_main.add(list_balance.get(4));//�»����

        list_subordination.add(list_balance.get(5));//�����ʺ�
        list_subordination.add(list_balance.get(6));//�������
        list_subordination.add(list_balance.get(7));//�����
        list_subordination.add(list_balance.get(8));//�ۼƻ����
        list_subordination.add(list_balance.get(9));//��������
        list_subordination.add(list_balance.get(10));//ʣ������
        list_subordination.add(list_balance.get(11));//���ڿۿ���
        list_subordination.add(list_balance.get(12));//���ڻ�����
        return SignTools.getSuccessInfo(QUERY_BORROW_BALANCE_NUMBER, list_main, list_subordination);       
      }
      else{//����ѯʧ��
        return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
      }
    }//card_num!=null name!=null
    else{//��ѯʧ��
      return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
    }
   }catch(Exception e)
   {
     e.printStackTrace();
     return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
   }
  }
  
   /** ��ѯ���������ϸ
    * @param dto ����Ż�����Ϣ��DTO,startdate��ʼʱ��,enddate����ʱ��
    * @return ���ش��������ϸ��Ϣ
    */
  public String queryBorrowListBalance(BaseInfoDTO dto,String startdate,String enddate){
    try {
      String start=startdate;
      String end=enddate;
      String card_num=dto.getCard_num();
      String name=dto.getName();
      int startint=Integer.parseInt(start);
      int endint=Integer.parseInt(end);
      int isbigger=endint-startint;
      StringBuffer buf=new StringBuffer();
      start=Integer.toString(startint);
      end=Integer.toString(endint);
      //int isbigger=Integer.getInteger(end).intValue()-Integer.getInteger(start).intValue();//��ʼ�����Ƿ�С�ڽ�������
      List list_balance=new ArrayList();
      if((name!=null)&&(card_num!=null)&&(isbigger>=0))//���Ψһ��ʶû�в�ѯʧ��
      {
        
        if(!signdao.isHaveNameAndCard_num(name,card_num)){//ְ�����������֤���Ƿ����
          return SignTools.getInfo_02(QUERY_BORROW_LIST_BALANCE);
        }
        String date=SignTools.getLoanRepayYearMonth(signdao.getLoanRepayDay(name, card_num));
        if(date.equalsIgnoreCase("")){//����û�û����
          return SignTools.getInfo_02(QUERY_BORROW_LIST_BALANCE);
        }
        if(SignTools.dateBiggerThanDateXMonth(enddate,startdate)>6){//�����ѯ��Χ����6����
          return SignTools.getInfo_03(QUERY_BORROW_LIST_BALANCE);
        }
        

        list_balance=signdao.getBorrowListBalance(name,card_num,start,end);
        
        List list_main=new ArrayList();//�����'|'�ָ�����Ҫ��Ϣ
        List list_subordination=new ArrayList();//�����','�ָ��ĸ�����Ϣ
        List temp=new ArrayList();//��ʱ��Ų���ʱ������
        
        temp.add(signdao.getLoanIDByNameAndCard_num(name, card_num).get(0));//��ô����ʺ�
        
        //������|������|��ϸ����|����,ժҪ,���ձ���,������Ϣ,�����ʺ�,������,ʱ��������|

        temp.add(list_balance.get(0));
        buf.append(SignTools.getSuccessInfo(QUERY_BORROW_LIST_BALANCE,temp));
        if(Integer.parseInt(((String)list_balance.get(0)).trim())!=0){
        temp.clear();
        int size=list_balance.size();
         for(int i=1;i<size;i++){
           temp=(List)list_balance.get(i);
           list_subordination.add(temp.get(0));//����
           list_subordination.add(temp.get(1));//ժҪ
           list_subordination.add(temp.get(2));//���ձ���
           list_subordination.add(temp.get(3));//������Ϣ
           //list_subordination.add(temp.get(4));//�����ʺ�
           list_subordination.add(temp.get(5));//������
           list_subordination.add(temp.get(6));//ʱ��������
           buf.append(SignTools.Combination(list_main));
           buf.append(SignTools.combinationWithAllComma(list_subordination));
           temp.clear();
           list_subordination.clear();
          }
         String s=buf.toString();
         return s.substring(0, s.length()-1)+"|";
        }//�����ϸ��ĿΪ0
        else{
          buf.append("|");
          return buf.toString();
        }
      }//empid!=null isbigger>=0
      else{//��ѯʧ��
        return SignTools.getFailedInfo(QUERY_BORROW_LIST_BALANCE);
      }
     } catch (Exception e) {
       e.printStackTrace();
       return SignTools.getFailedInfo(QUERY_BORROW_LIST_BALANCE);
     }
  }
  
//--------------------------------------------------------------------
//��ҵ���߼� START
//--------------------------------------------------------------------


  /**
   * ��ѯ�������ʻ����
   * @param dto ����Ż�����Ϣ��DTO
   * @return ���������Ϣ
   */
  public String queryNewBalance(BaseInfoDTO dto){
    String empid=dto.getEmpid();
    
    if(empid!=null&&(!"".equalsIgnoreCase(empid)))//���Ψһ��ʶû�в�ѯʧ��
    {
      //    ִ������ͬ����������ͬ������ʱ��
      signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 1);
      if(!signdao.isNewHaveEmp_id(empid)){//���ݿ����Ƿ����ְ�����
        return SignTools.getInfo_08(QUERY_BALANCE_NUMBER);
      }
      List list=signdao.getNewBalance(empid);
      List main=new ArrayList();//�����'|'�ָ�����Ҫ��Ϣ
      List subsidiary=new ArrayList();//�����','�ָ��ĸ�����Ϣ
      main.add(list.get(0));
      main.add(list.get(1));
      main.add(list.get(2));
      main.add(list.get(3));
      main.add(list.get(4));
      
      subsidiary.add(list.get(5));
      subsidiary.add(list.get(6));
      subsidiary.add(list.get(7));
      subsidiary.add(list.get(8));
      subsidiary.add(list.get(9));
      subsidiary.add(list.get(10));
      subsidiary.add(list.get(11));
      subsidiary.add(list.get(12));
      subsidiary.add(list.get(13));
      subsidiary.add(list.get(14));
      subsidiary.add(list.get(15));
      subsidiary.add(list.get(16));
      subsidiary.add(list.get(17));
      subsidiary.add(list.get(18));
      subsidiary.add(list.get(19));
      subsidiary.add(list.get(20));
      subsidiary.add(list.get(21));
      subsidiary.add(list.get(22));
      return SignTools.getSuccessInfo(QUERY_BALANCE_NUMBER,main,subsidiary);
    }//empid!=null
    else{//Ψһ��ʶ��ѯʧ��
      return SignTools.getFailedInfo(QUERY_BALANCE_NUMBER);
    }
  }
  /**
   * ��ѯ�����ϸ
   * @param dto ����Ż�����Ϣ��DTO,startdate��ʼʱ��,enddate����ʱ��
   * @return ���������ϸ��Ϣ
   */
  public String queryNewListBalance(BaseInfoDTO dto,String startdate,String enddate){
    try {
      String start=startdate;
      String end=enddate;
      int startint=Integer.parseInt(start);
      int endint=Integer.parseInt(end);
      int isbigger=endint-startint;
      start=Integer.toString(startint);
      end=Integer.toString(endint);

      List temp=new ArrayList();
      List list_balance=new ArrayList();
      StringBuffer buf=new StringBuffer();
      String empid=null;
      
      List list_main=new ArrayList();//�����'|'�ָ�����Ҫ��Ϣ
      List list_subordination=new ArrayList();//�����','�ָ��ĸ�����Ϣ
      
      empid=dto.getEmpid();
      if((empid!=null)&&(isbigger>=0))//���Ψһ��ʶû�в�ѯʧ��
      {
        if(SignTools.dateBiggerThanDateXMonth(enddate,startdate)>6){//�����ѯ��Χ����6����
          return SignTools.getInfo_03(QUERY_LIST_BALANCE_NUBBER);
        }
        //ִ������ͬ����������ͬ������ʱ��
        signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 1);
        signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 2);
        if(!signdao.isNewHaveEmp_id(empid)){//���ݿ����Ƿ����ְ�����
          return SignTools.getInfo_08(QUERY_LIST_BALANCE_NUBBER);
        }
        list_balance=signdao.getNewListBalance(empid, start, end);
        
        String empinfo=SignTools.combinationWithAllComma(signdao.getNewListBalanceInfo(empid));//�Ȼ�õ�λ�ʺţ���λ���ƣ������ʺţ�����
        empinfo=empinfo.substring(0, empinfo.length()-1);
        
        temp.add(empinfo);
        temp.add(list_balance.get(0));
        
        buf.append(SignTools.getSuccessInfo(QUERY_LIST_BALANCE_NUBBER,temp));
        if(Integer.parseInt(((String)list_balance.get(0)).trim())!=0){
           temp.clear();
        int size=list_balance.size();
         for(int i=1;i<size;i++){
           temp=(List)list_balance.get(i);
           list_subordination.add(temp.get(0));//����
           list_subordination.add(temp.get(1));//ժҪ
           list_subordination.add(temp.get(2));//������
           list_subordination.add(temp.get(3));//���      
           list_subordination.add(temp.get(4));//�����ʶ
           list_subordination.add(temp.get(5));//������ж���
           list_subordination.add(temp.get(6));//�������
           buf.append(SignTools.Combination(list_main));
           buf.append(SignTools.combinationWithAllComma(list_subordination));
           temp.clear();
           //list_main.clear();
           list_subordination.clear();
          }
         String s=buf.toString();
         return s.substring(0, s.length()-1)+"|";
        }//�����ϸ��ĿΪ0
        else{
         buf.append("|");
         return buf.toString();
        }
      }//empid!=null isbigger>=0
      else{//��ѯʧ��
        return SignTools.getFailedInfo(QUERY_LIST_BALANCE_NUBBER);
      }
     } catch (Exception e) {
       e.printStackTrace();
       return SignTools.getFailedInfo(QUERY_LIST_BALANCE_NUBBER);
     }
    
  }
  /**
   * ��ѯ�������
   * @param dto ����Ż�����Ϣ��DTO
   * @return ���ش��������Ϣ
   */
  public String queryNewBorrowBalance(BaseInfoDTO dto){
    try{
      List list_balance=new ArrayList();
      String card_num=dto.getCard_num();
      String name=dto.getName();
      List list_main=new ArrayList();
      List list_subordination=new ArrayList();
      
      if((card_num!=null)&&(name!=null))//���Ψһ��ʶû�в�ѯʧ��
      {
        //      ִ������ͬ����������ͬ������ʱ��
        signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 3);
        if(!signdao.isNewHaveNameAndCard_num(name,card_num)){//ְ�����������֤���Ƿ����
          return SignTools.getInfo_02(QUERY_BORROW_BALANCE_NUMBER);
        }
        list_balance=signdao.getNewBorrowBalance(name, card_num);      
        if((list_balance.size()!=0)&&(list_balance!=null)){//������û�в�ѯʧ��
          if(((String)list_balance.get(0)).trim().equalsIgnoreCase("0")){//��������ѻ���
        //������������������������ڿۿ��ա����ڿۿ�����Ϊ0
            list_main.add(list_balance.get(0));//���
            list_main.add(list_balance.get(1));//����
            list_main.add(list_balance.get(2));//���֤
            list_main.add(list_balance.get(3));//��λ����
            list_main.add("0");//�»����
            
            list_subordination.add(list_balance.get(5));//�����ʺ�
            list_subordination.add("0");//�������
            list_subordination.add(list_balance.get(7));//�����
            list_subordination.add(list_balance.get(8));//�ۼƻ����
            list_subordination.add("0");//��������
            list_subordination.add(list_balance.get(10));//ʣ������
            list_subordination.add("0");//���ڿۿ���
            list_subordination.add("0");//���ڻ�����
            return SignTools.getSuccessInfo(QUERY_BORROW_BALANCE_NUMBER, list_main, list_subordination);
          }
          list_main.add(list_balance.get(0));//���
          list_main.add(list_balance.get(1));//����
          list_main.add(list_balance.get(2));//���֤
          list_main.add(list_balance.get(3));//��λ����
          list_main.add(list_balance.get(4));//�»����

          list_subordination.add(list_balance.get(5));//�����ʺ�
          list_subordination.add(list_balance.get(6));//�������
          list_subordination.add(list_balance.get(7));//�����
          list_subordination.add(list_balance.get(8));//�ۼƻ����
          list_subordination.add(list_balance.get(9));//��������
          list_subordination.add(list_balance.get(10));//ʣ������
          list_subordination.add(list_balance.get(11));//���ڿۿ���
          list_subordination.add(list_balance.get(12));//���ڻ�����
          return SignTools.getSuccessInfo(QUERY_BORROW_BALANCE_NUMBER, list_main, list_subordination);       
        }
        else{//����ѯʧ��
          return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
        }
      }//card_num!=null name!=null
      else{//��ѯʧ��
        return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
      }
     }catch(Exception e)
     {
       e.printStackTrace();
       return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
     }
    
    
  }
  /** ��ѯ���������ϸ
   * @param dto ����Ż�����Ϣ��DTO,startdate��ʼʱ��,enddate����ʱ��
   * @return ���ش��������ϸ��Ϣ
   */
  public String queryNewBorrowListBalance(BaseInfoDTO dto,String startdate,String enddate){
    try {
      String start=startdate;
      String end=enddate;
      String card_num=dto.getCard_num();
      String name=dto.getName();
      int startint=Integer.parseInt(start);
      int endint=Integer.parseInt(end);
      int isbigger=endint-startint;
      StringBuffer buf=new StringBuffer();
      start=Integer.toString(startint);
      end=Integer.toString(endint);
      //int isbigger=Integer.getInteger(end).intValue()-Integer.getInteger(start).intValue();//��ʼ�����Ƿ�С�ڽ�������
      List list_balance=new ArrayList();
      if((name!=null)&&(card_num!=null)&&(isbigger>=0))//���Ψһ��ʶû�в�ѯʧ��
      {
        if(SignTools.dateBiggerThanDateXMonth(enddate,startdate)>6){//�����ѯ��Χ����6����
          return SignTools.getInfo_03(QUERY_BORROW_LIST_BALANCE);
        }
        //ִ������ͬ����������ͬ������ʱ��
        signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 3);
        signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 4);
        if(!signdao.isNewHaveNameAndCard_num(name,card_num)){//ְ�����������֤���Ƿ����
          return SignTools.getInfo_02(QUERY_BORROW_LIST_BALANCE);
        }
        list_balance=signdao.getNewBorrowListBalance(name,card_num,start,end);
        List list_main=new ArrayList();//�����'|'�ָ�����Ҫ��Ϣ
        List list_subordination=new ArrayList();//�����','�ָ��ĸ�����Ϣ
        List temp=new ArrayList();//��ʱ��Ų���ʱ������
        List account=signdao.getNewLoanAccount(name, card_num);
        temp.add(account.get(0));//��ô����ʺ�
        //������|������|��ϸ����|����,ժҪ,���ձ���,������Ϣ,�����ʺ�,������,ʱ��������|
        temp.add(list_balance.get(0));
        buf.append(SignTools.getSuccessInfo(QUERY_BORROW_LIST_BALANCE,temp));
        if(Integer.parseInt(((String)list_balance.get(0)).trim())!=0){
        temp.clear();
        int size=list_balance.size();
         for(int i=1;i<size;i++){
           temp=(List)list_balance.get(i);
           list_subordination.add(temp.get(0));//����
           list_subordination.add(temp.get(1));//ժҪ
           list_subordination.add(temp.get(2));//���ձ���
           list_subordination.add(temp.get(3));//������Ϣ
           list_subordination.add(temp.get(4));//������
           list_subordination.add(temp.get(5));//ʱ��������
           buf.append(SignTools.Combination(list_main));
           buf.append(SignTools.combinationWithAllComma(list_subordination));
           temp.clear();
           list_subordination.clear();
          }
         String s=buf.toString();
         return s.substring(0, s.length()-1)+"|";
        }//�����ϸ��ĿΪ0
        else{
          buf.append("|");
          return buf.toString();
        }
      }//empid!=null isbigger>=0
      else{//��ѯʧ��
        return SignTools.getFailedInfo(QUERY_BORROW_LIST_BALANCE);
      }
     } catch (Exception e) {
       e.printStackTrace();
       return SignTools.getFailedInfo(QUERY_BORROW_LIST_BALANCE);
     }
  }
  
  /**
   * ����Sign����
   * @param dto �����ǩԼ��Ϣ��SignDTO
   * @return ����ǩԼ���
   */
  public String saveNewSign(RequestSignDTO dto)
  {

    if(dto.getCardnum().length()==18){//���Ϊ18λ���֤��
      String card15=SignTools.conversionTo15DigitCard_num(dto.getCardnum());//ת��Ϊ15λ���֤��
      if(signdao.isNewHaveUserInfo(dto.getEmpid(), dto.getName(), dto.getCardnum())){
        return newSign(dto);
      }else if(signdao.isNewHaveUserInfo(dto.getEmpid(), dto.getName(),card15)){
        dto.setCardnum(card15);
        return newSign(dto);
      }else{
        return SignTools.getFailedInfo(SIGN_UP_NUMBER);
      }
    }else {//���Ϊ15λ���֤��
      String card18=signdao.to18Card_num(dto.getCardnum());//ת��Ϊ18λ���֤��
      if(signdao.isNewHaveUserInfo(dto.getEmpid(), dto.getName(), dto.getCardnum())){
        return newSign(dto);
      }else if(signdao.isNewHaveUserInfo(dto.getEmpid(), dto.getName(),card18)){
        dto.setCardnum(card18);
        return newSign(dto);
      }else{
        return SignTools.getFailedInfo(SIGN_UP_NUMBER);
      }
    }
  }
  
  /**
   * �����ݿ�������� ��saveSign����ʹ��
   * @param dto �����ǩԼ��Ϣ��SignDTO
   * @return ����ǩԼ���
   */
  private String newSign(RequestSignDTO dto){
    List list_sign=new ArrayList();
    //��ȡ����
    Sign sign=new Sign();
    sign.setBank_card(dto.getBanknum());
    sign.setCard_num(dto.getCardnum());
    sign.setEmpid(dto.getEmpid());
    sign.setName(dto.getName());
    Integer i=signdao.save(sign);//��������
    if(i.intValue()!=0){//�Ƿ����ɹ�
      list_sign.add(i.toString());
      return SignTools.getSuccessInfo(SIGN_UP_NUMBER,list_sign);
    }
    else{//ʧ��
      return SignTools.getFailedInfo(SIGN_UP_NUMBER);
    }
  }

//--------------------------------------------------------------------
//��ҵ���߼� END
//--------------------------------------------------------------------
  /**
   * ִ���ϱ����±�����ͬ��
   */
  public void execSynProcdure(){
    signdao.execSynProcdural();
  }
//--------------------------------------------------------------------
//����ǩԼ START
//--------------------------------------------------------------------

  
   /**
   * ���ݵ�λ��Ų�ѯ�õ�λ��ҳ�����Ϣ
   */
  public List queryAllEmpInfo(Pagination pagination) {
    List list=new ArrayList();
    
    String orgid=(String) pagination.getQueryCriterions().get("orgid");
    String empid=(String) pagination.getQueryCriterions().get("empid");  
    String orderBy=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage()-1;
    int pageSize = pagination.getPageSize(); 
    int page = pagination.getPage();
    list=signdao.queryEmpInfoByOrgIdFromTemp(orgid, empid, orderBy, order, start, pageSize, page);
    int count = signdao.queryEmpCountByOrgid(orgid);
    pagination.setNrOfElements(count);
//    if(list.size()==0){
//      throw new BusinessException("�����ڼ�¼");
//    }
    return list;
  }
  
  /**
   * �����ı������������ݿ�
   * @param imp
   * @return
   * @throws BusinessException 
   */
  public void signImpBatch(List headlist,List bodylist) throws BusinessException{
    try{
      SignImportHeadDTO headdto=(SignImportHeadDTO)headlist.get(1);
      String orgid=headdto.getOrgid().trim();
      String orgname=headdto.getOrgname().trim();
      int impsize=bodylist.size();

      for(int i=1;i<impsize;i++){
        SignImportBodyDTO bodydto=(SignImportBodyDTO)bodylist.get(i);
        try{
          TempDTO temp=new TempDTO();
          temp.setOrgid(orgid.trim());
          temp.setOrgname(orgname.trim());
          temp.setEmpid(bodydto.getEmpid().trim());
          
          temp.setEmpname(bodydto.getEmpname().trim());
          temp.setCardnum(bodydto.getCardnum().trim());
          temp.setBankcardid(bodydto.getBankcardid().trim());
          if(signdao.isHaveSignInTemp(temp.getEmpid(), temp.getBankcardid())){
            signdao.deleteTemp(temp.getEmpid(),temp.getBankcardid());
          }
          signdao.insertEmpInfo(temp);
        }
        catch(SQLException e){
          e.printStackTrace();
          throw new BusinessException("����ʧ�ܣ�");
        }
      }
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("����ʧ�ܣ�");
    }
  }
  
  /**
   * ����Ա����ź����п��Ż���ݴ���е�Ա����Ϣ
   * @param empid Ա�����
   * @param bankcardid ���п���
   * @return
   */
  public RequestSignDTO getSingleEmpInfoByEmpidAndCardnum(String empid,String bankcardid){
    RequestSignDTO dto=new RequestSignDTO();
    List list=signdao.queryEmpInfoByEmpidAndBank(empid, bankcardid);
    if(list.size()!=0){
      Object [] obj=(Object [])list.get(0);
      dto.setEmpid(SignTools.parseNull((BigDecimal)obj[0]).toString().trim());
      dto.setName(SignTools.parseNull(((String)obj[1]).trim()));
      dto.setCardnum(SignTools.parseNull(((String)obj[2]).trim()));
      dto.setBanknum(SignTools.parseNull(((String)obj[3]).trim()));
      return dto;
    }else{
      return null;
    }
  }
  
  /**
   * ����ְ����ź����п���ɾ����Ա����Ϣ
   * @param empid Ա�����
   * @param bankcardid ���п���
   * @return
   */
  public void deleteUserInfo(String orgid,String empid,String bankcardid) throws BusinessException{
    try{
      signdao.deleteByEmpidAndBank(orgid, empid, bankcardid);
    }catch(Exception e){
      throw new BusinessException("ɾ��ʧ��");
    }
  }
  /**
   * ɾ���õ�λ�µ����ݴ���е�����Ա����Ϣ
   * @param orgid ��λ���
   * @return
   */
  public void deleteAll(String orgid) throws BusinessException {
    try{
      signdao.deleteAllByOrgId(orgid);
    }catch(Exception e){
      throw new BusinessException("����ɾ��ʧ��");
    }
  }
  /**
   * ��ѯ���ݴ���иõ�λ�µ�����Ա����Ϣ,������
   * @param orgid ��λ���
   * @return
   */
  public List queryEmpListAll(Pagination pagination) throws BusinessException {
    String orgid=(String)pagination.getQueryCriterions().get("orgid");
    String orgname=(String)pagination.getQueryCriterions().get("orgname");
    if(orgid==null||orgid.equalsIgnoreCase("")){
      throw new BusinessException("����ʧ��");
    }
    if(orgname==null){
      orgname="";
    }  
    List list=signdao.queryEmpInfoByOrgID(orgid.trim(),orgname.trim());
    if(list==null){
      throw new BusinessException("����ʧ��");
    }
    return list;
  }
  
  /**
   * ���ݵ�λ��Ų�ѯ��λ����
   */
  public String getOrgnameByOrgID(String orgid){
    return signdao.queryOrgnameByOrgID(orgid);
  }
  
  /**
   * �����û���Ϣ
   * @param dto �û���Ϣ
   * @throws BusinessException
   */
  public void addUserInfo(TempDTO dto) throws BusinessException {
    try{
      if(signdao.isHaveSignInTemp(dto.getEmpid(), dto.getBankcardid())){
        signdao.deleteTemp(dto.getEmpid(), dto.getBankcardid());
      }
      signdao.insertEmpInfo(dto);
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("����ʧ��");
    }
  }

  /**
   * �޸��û���Ϣ
   * @param dto �û���Ϣ
   * @throws BusinessException
   */
  public void modifyUserInfo(TempDTO newdto,TempDTO olddto) throws BusinessException {
    try{
      signdao.updateEmpInfo(newdto,olddto);
    }catch(SQLException e){
      e.printStackTrace();
      throw new BusinessException("�޸�ʧ��");
    }
  }
  
  /**
   * ���ݵ�λ��ţ�ְ����ţ����п��ţ����ݴ���в�ѯԱ����Ϣ
   */
  public TempDTO queryUserInfo(String orgid, String empid, String bankcardid) throws BusinessException {
    TempDTO dto=new TempDTO();
    List list=signdao.queryUserInfo(empid,orgid,bankcardid);
    try{
      //empid,empname,cardnum,bankcardid
      dto.setEmpid((String)list.get(0));
      dto.setEmpname((String)list.get(1));
      dto.setCardnum((String)list.get(2));
      dto.setBankcardid((String)list.get(3));
      
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("�޸��û�ʧ��");
    }
    return dto;
  }
  
  /**
   * ����������ҳ��ѯ����ǩԼ��ʷ��¼
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public List queryHistoryList(Pagination pagination) throws BusinessException {
    List list=new ArrayList();
    String orgid=(String) pagination.getQueryCriterions().get("orgid");
    String empid=(String) pagination.getQueryCriterions().get("empid");  
    String transactdatastart=(String)pagination.getQueryCriterions().get("transactdatastart");
    String transactdataend=(String)pagination.getQueryCriterions().get("transactdataend");
    String affirmdatastart=(String)pagination.getQueryCriterions().get("affirmdatastart");
    String affirmdataend=(String)pagination.getQueryCriterions().get("affirmdataend");
    String issccueed=(String)pagination.getQueryCriterions().get("issccueed");
    String orderby=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage()-1;
    int pageSize = pagination.getPageSize(); 
    int page = pagination.getPage();
    try{
    list=signdao.queryHistoryInfo(orgid, empid, transactdatastart,transactdataend,affirmdatastart,affirmdataend,issccueed,orderby, order, start, pageSize, page);
    int count = signdao.queryEmpHistoryCount(orgid, empid, transactdatastart,transactdataend,affirmdatastart,affirmdataend,issccueed);
    pagination.setNrOfElements(count);
//    if(list.size()==0){
//      throw new BusinessException("�����ڼ�¼");
//    }
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("��ѯʧ��");
    }
    return list;
  }
  
  
  
  
  public List queryLogList(Pagination pagination) throws BusinessException {
    List list=new ArrayList();
    String orgid=(String) pagination.getQueryCriterions().get("orgid");
    String empid=(String) pagination.getQueryCriterions().get("empid");  
    String transactdatastart=(String)pagination.getQueryCriterions().get("transactdatastart");
    String transactdataend=(String)pagination.getQueryCriterions().get("transactdataend");
    String affirmdatastart=(String)pagination.getQueryCriterions().get("affirmdatastart");
    String affirmdataend=(String)pagination.getQueryCriterions().get("affirmdataend");
    String issccueed=(String)pagination.getQueryCriterions().get("isucceed");
    String orderby=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage()-1;
    int pageSize = pagination.getPageSize(); 
    int page = pagination.getPage();
    try{
    list=signdao.queryHistoryInfo(orgid, empid, transactdatastart,transactdataend,affirmdatastart,affirmdataend,issccueed,orderby, order, start, pageSize, page) ;
    int count = signdao.queryHistoryCount(orgid, empid, transactdatastart,transactdataend,affirmdatastart,affirmdataend,issccueed);
    pagination.setNrOfElements(count);
    return list;
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("��ѯʧ�ܣ�");
    }
//    if(list.size()==0){
//      throw new BusinessException("�����ڼ�¼");
//    }
    
  }
  
  
  
  /**
   * �����־�ļ�����Ϣ
   */
  public List queryLog(Pagination pagination) throws BusinessException {
    List list=new ArrayList();
    //  ID FILE_NAME OPERATION_TYPE OP_DATE
    String file_type=(String) pagination.getQueryCriterions().get("filetype");
    String timestart=(String) pagination.getQueryCriterions().get("starttime");  
    String timeend=(String)pagination.getQueryCriterions().get("endtime");
    String orderby=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage()-1;
    int pageSize = pagination.getPageSize(); 
    int page = pagination.getPage();
    try {
      list=signdao.queryLogInfo(file_type, timestart, timeend, orderby, order, start, pageSize, page);
      int count = signdao.queryLogCount(file_type, timestart, timeend);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw new BusinessException("��ѯʧ�ܣ�");
    }
    
    return list;
  }
  
  
  /**
   * �ӻ�����ж�List
   */
  public List getAllUserInfo() {
    return signdao.readFromTemp();
  }
  
  
  /**
   * ΪǩԼ׼��
   */
  public List prepareSendFile() {
    List templist=signdao.readFromTemp();
    signdao.deleteHistory();
    List returnlist=new ArrayList();
    int size=templist.size();
    for(int i=0;i<size;i++){
      TempDTO dto=(TempDTO)templist.get(i);
      Sign sign=new Sign();
      sign.setName(dto.getEmpname());
      sign.setEmpid(dto.getEmpid());
      sign.setCard_num(dto.getCardnum());
      sign.setBank_card(dto.getBankcardid());
      
      Integer alone=signdao.save(sign);//��ǩԼ��������ݣ����Ψһ��ʶ
      
      HistoryDTO history=new HistoryDTO();
      history.setOrgid(dto.getOrgid());
      history.setOrgname(dto.getOrgname());
      history.setEmpid(dto.getEmpid());
      history.setEmpname(dto.getEmpname());
      history.setCardnum(dto.getCardnum());
      history.setBankcardid(dto.getBankcardid());
      history.setBiz_date(dto.getBiz_date());
      history.setOperater(dto.getOperater());
      history.setSign(alone.toString());
      history.setSucc_fail("2");
      try{
        signdao.insertHistory(history);
      }
      catch(Exception e){
        e.printStackTrace();
        return new ArrayList();
      }
      returnlist.add(history);
     }
     return returnlist;
  }
  
  
  /**
   * ��¼�ļ���Ϣ
   */
  public void logFile(LogDTO dto)throws Exception{
    signdao.insertLog(dto);
  }
  
  
  /**
   * ׼�������ļ�
   */
  public void prepareReceiveFile(List list) throws Exception{
    int size=list.size();
    for(int i=0;i<size;i++){
      RecieveFileDTO dto=(RecieveFileDTO)list.get(i);
      String sign=dto.getSign().trim();
      signdao.updateHistory(sign,dto.getS_f().trim());
      if(!dto.getS_f().trim().equalsIgnoreCase("00")){
        if(signdao.isHaveSignInHistory(sign))
          signdao.deleteSignBySign(sign);
      }  
    }
  }
  
  /**
   * ��ջ����
   */
  public void clearTemp(){
    signdao.deleteAllTemp();
  }
  
//--------------------------------------------------------------------
//����ǩԼ END
//--------------------------------------------------------------------



}


