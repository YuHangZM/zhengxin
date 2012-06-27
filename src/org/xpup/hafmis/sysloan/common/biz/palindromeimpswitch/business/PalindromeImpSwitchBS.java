package org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.BeanUtils;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.bsinterface.IPalindromeImpSwitchBS;
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.*;
import org.xpup.hafmis.sysloan.common.dao.BankpalindromeDAO;
import org.xpup.hafmis.sysloan.common.dao.PalindromFormatHeadDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Bankpalindrome;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;

public class PalindromeImpSwitchBS implements IPalindromeImpSwitchBS {

  private PalindromFormatHeadDAO palindromFormatHeadDAO = null;// PL011
  private BankpalindromeDAO bankpalindromeDAO = null;// PL010 ���л��ĸ�ʽ����

  public void setBankpalindromeDAO(BankpalindromeDAO bankpalindromeDAO) {
    this.bankpalindromeDAO = bankpalindromeDAO;
  }
  // ���ĸ�ʽ��Ӧ����ͷ��

  public void setPalindromFormatHeadDAO(
      PalindromFormatHeadDAO palindromFormatHeadDAO) {
    this.palindromFormatHeadDAO = palindromFormatHeadDAO;
  }

  /**
   * �����ļ���ʽת��
   */
  public List switchImpList(String bankId,List headList, List impList,String type) throws Exception,
      BusinessException {
    /**
     * �����ж�type��������TXT ����xls
     * �����TXT���ö���
     * ���ж�ΪXLS�м���LIST �е�ͷ��Ϣ
     */
    //********************************************
//    Bankpalindrome bankpalindrome = null;
//    String rowNum = null;
//    if(bankId!=null && !"".equals(bankId)){
//      bankpalindrome = bankpalindromeDAO.queryById(new Integer(bankId));
//    }
//    rowNum = bankpalindrome.getRowNum();//��������
    //********************************************
    // TODO Auto-generated method stub
    List list = new ArrayList();
     String loanId = "";//�����ʺ� 1
     String name = "";//���� 2
     String payDate = "";//�������� 3
     String realCorpus = "";// ʵ���������� 4
     String realOverdueCorpus = "";//ʵ�����ڱ��� 5
     String realInterest = "";// ʵ��������Ϣ 6
     String realOverdueInterest = "";// ʵ��������Ϣ 7
     String realPunishInterest = "";// ʵ�۷�Ϣ 8
     String nobackCorpus = "";// δ���������� 9
     String nobackOverdueCorpus = "";// δ�����ڱ��� 10
     String nobackInterest = "";// δ��������Ϣ 11 
     String nobackOverdueInterest = "";// δ��������Ϣ  12
     String nobackPunishInterest = "";// δ����Ϣ 13
     String deadLine = "";// ��ǰ�����ʣ������ 14

     loanId = palindromFormatHeadDAO.queryByBankId(bankId, "1");
     name = palindromFormatHeadDAO.queryByBankId(bankId, "2");
     payDate = palindromFormatHeadDAO.queryByBankId(bankId, "3");
     realCorpus = palindromFormatHeadDAO.queryByBankId(bankId, "4");
     realOverdueCorpus = palindromFormatHeadDAO.queryByBankId(bankId, "5");
     realInterest = palindromFormatHeadDAO.queryByBankId(bankId, "6");
     realOverdueInterest = palindromFormatHeadDAO.queryByBankId(bankId, "7");
     realPunishInterest = palindromFormatHeadDAO.queryByBankId(bankId, "8");
     nobackCorpus = palindromFormatHeadDAO.queryByBankId(bankId, "9");
     nobackOverdueCorpus = palindromFormatHeadDAO.queryByBankId(bankId, "10");
     nobackInterest = palindromFormatHeadDAO.queryByBankId(bankId, "11");
     nobackOverdueInterest = palindromFormatHeadDAO.queryByBankId(bankId, "12");
     nobackPunishInterest = palindromFormatHeadDAO.queryByBankId(bankId, "13");
     deadLine = palindromFormatHeadDAO.queryByBankId(bankId, "14");

    try {
      if("txt".equals(type)){//�ж��Ƿ�Ϊtxt
       if (impList.size() != 0) {
        for (int i = 0; i < impList.size(); i++) {
          Object[] obj = null;
          obj = (Object[]) impList.get(i);
     
          String value1 = null;
          String value2 = null;
          String value3 = null;
          String value4 = null;
          String value5 = null;
          String value6 = null;
          String value7 = null;
          String value8 = null;
          String value9 = null;
          String value10 = null;
          String value11 = null;
          String value12 = null;
          String value13 = null;
          String value14 = null;

          value1 = this.getFormula(loanId, obj);
          value2 = this.getFormula(name, obj);
          value3 = this.getFormula(payDate, obj);
          value4 = this.getFormula(realCorpus, obj);
          value5 = this.getFormula(realOverdueCorpus, obj);
          value6 = this.getFormula(realInterest, obj);
          value7 = this.getFormula(realOverdueInterest, obj);
          value8 = this.getFormula(realPunishInterest, obj);
          value9 = this.getFormula(nobackCorpus, obj);
          value10 = this.getFormula(nobackOverdueCorpus, obj);
          value11 = this.getFormula(nobackInterest, obj);
          value12 = this.getFormula(nobackOverdueInterest, obj);
          value13 = this.getFormula(nobackPunishInterest, obj);
          value14 = this.getFormula(deadLine, obj);

          Object[] newObj = new Object[14];
          
            newObj[0] = value1;
         
            newObj[1] = value2;
        
            newObj[2] = value3;
         
            newObj[3] = value4;
         
            newObj[4] = value5;
        
            newObj[5] = value6;
          
            newObj[6] = value7;
          
            newObj[7] = value8;
            
            newObj[8] = value9;
            
            newObj[9] = value10;
            
            newObj[10] = value11;
            
            newObj[11] = value12;
            
            newObj[12] = value13;
            
            newObj[13] = value14;
            
         
          list.add(newObj);
        }
      }
     }else{//xls�ļ�
       /**
        * ��������LIST   ͷβLIST
        * �ϲ�һ��LIST???????
        * new һ��LIST �ȷ���ͷ�� Ȼ�����β��
        *
        */
       PalindromeImpSwitchHeadDTO palindromeImpSwitchHeadDTO = new PalindromeImpSwitchHeadDTO();
       palindromeImpSwitchHeadDTO = (PalindromeImpSwitchHeadDTO) headList.get(1);
       List newList = new ArrayList();
//       
//       impList = new ArrayList();
//       impList.add(list);
        
           OneTailDTO oneTailDTO = null;
           TwoTailDTO twoTailDTO = null;
           String rowNumber = null;
           Bankpalindrome bankpalindrome = bankpalindromeDAO.queryById(new Integer(bankId));//��ѯ��������
           if(bankpalindrome!=null){
             rowNumber = bankpalindrome.getRowNum();
           }
           int number = Integer.parseInt(rowNumber);
           System.out.println("��ֵ:"+number);
           for(int i=1;i<number;i++){
             if(i==impList.size()){
               break;
             }
             TestA testA=new TestA();
             System.out.println("i:"+i);
             if("1".equals(bankId)){
             oneTailDTO = (OneTailDTO)impList.get(i);
             BeanUtils.copyProperties(testA,oneTailDTO); //testA
             }
             if("2".equals(bankId)){
               twoTailDTO = (TwoTailDTO)impList.get(i);
               BeanUtils.copyProperties(testA,twoTailDTO); //testA
             }
             Object[] obj = new Object[100];
            
               String s1 = testA.getOne();
               System.out.println("s1:"+s1);
               obj[0]=s1;
            
               String s2 = testA.getTwo();
               System.out.println("s2:"+s2);
               obj[1]=s2;
             
               String s3 = testA.getThree();
               System.out.println("s3:"+s3);
               obj[2]=s3;
            
               String s4 = testA.getFour();
               System.out.println("s4:"+s4);
               obj[3]=s4;
            
               String s5 = testA.getFive();
               System.out.println("s5:"+s5);
               obj[4]=s5;
             
               String s6 = testA.getSix();
               System.out.println("s6:"+s6);
               obj[5]=s6;
             
               String s7 = testA.getSeven();
               System.out.println("s7:"+s7);
               obj[6]=s7;
            
               String s8 = testA.getEight();
               System.out.println("s8:"+s8);
               obj[7]=s8;
            
               String s9 = testA.getNine();
               System.out.println("s9:"+s9);
               obj[8]=s9;
            
               String s10 = testA.getTen();
               System.out.println("s10:"+s10);
               obj[9]=s10;
             
               String s11 = testA.getEleven();
               System.out.println("s11:"+s11);
               obj[10]=s11;
            
               String s12 = testA.getTwelve();
               System.out.println("s12:"+s12);
               obj[11]=s12;
             
               String s13 = testA.getThirteen();
               System.out.println("s13:"+s13);
               obj[12]=s13;
             
               String s14 = testA.getFourteen();
               System.out.println("s14:"+s14);
               obj[13]=s14;
               
               String s15 = testA.getFifteen();
               System.out.println("s15=====:"+s15);
               obj[14]=s15;
               
               String s16 = testA.getSixteen();
               System.out.println("s16=====:"+s16);
               obj[15]=s16;
               
               String s17 = testA.getSeventeen();
               System.out.println("s17=====:"+s17);
               obj[16]=s17;
               
               String s18 = testA.getEighteen();
               System.out.println("s18=====:"+s18);
               obj[17]=s18;
               
               String s19 = testA.getNineteen();
               System.out.println("s19=====:"+s19);
               System.out.println("____________________________________");
               obj[18]=s19;
               
               String s20 = testA.getTwenty();
               System.out.println("s20=====:"+s20);
               obj[19]=s20;
               newList.add(obj);
           }
        
       if (newList.size() != 0) {
         for (int i = 0; i < newList.size(); i++) {
           Object[] obj = null;
           obj = (Object[]) newList.get(i);
      
           String value1 = null;//�����ʺ� 1
           String value2 = null;;//���� 2
           String value3 = null;//�������� 3
           String value4 = null;// ʵ���������� 4
           String value5 = null;//ʵ�����ڱ��� 5
           String value6 = null;// ʵ��������Ϣ 6
           String value7 = null;// ʵ��������Ϣ 7
           String value8 = null;// ʵ�۷�Ϣ 8
           String value9 = null;// δ���������� 9
           String value10 = null;// δ�����ڱ��� 10
           String value11 = null;// δ��������Ϣ 11 
           String value12 = null;// δ��������Ϣ  12
           String value13 = null;// δ����Ϣ 13
           String value14 = null;// ��ǰ�����ʣ������ 14

           value1 = this.getFormula(loanId, obj);//�����ʺ� 1
           value2 = this.getFormula(name, obj);
           value3 = this.getFormula(payDate, obj);
           value4 = this.getFormula(realCorpus, obj);
           value5 = this.getFormula(realOverdueCorpus, obj);
           value6 = this.getFormula(realInterest, obj);
           value7 = this.getFormula(realOverdueInterest, obj);
           value8 = this.getFormula(realPunishInterest, obj);
           value9 = this.getFormula(nobackCorpus, obj);
           value10 = this.getFormula(nobackOverdueCorpus, obj);
           value11 = this.getFormula(nobackInterest, obj);
           value12 = this.getFormula(nobackOverdueInterest, obj);
           value13 = this.getFormula(nobackPunishInterest, obj);
           value14 = this.getFormula(deadLine, obj);
           System.out.println("===="+value1);
           LoancallbackTaImportDTO dto = new LoancallbackTaImportDTO();
           dto.setBizDate(palindromeImpSwitchHeadDTO.getPayDate().trim()); // �ۿ�����
           dto.setLoanBankId(palindromeImpSwitchHeadDTO.getBankId().trim()); // �����к�
           dto.setBizType(palindromeImpSwitchHeadDTO.getBizType().trim());// ҵ������
           dto.setLoanKouAcc(value1); // �ۿ��ʺ�
           dto.setBorrowerName(value2); // ���������
           dto.setYearMonth(value3); // ��������
           dto.setRealCorpus(value4);// ʵ����������
           dto.setRealOverdueCorpus(value5);// ʵ�����ڱ���
           dto.setRealInterest(value6);// ʵ��������Ϣ
           dto.setRealOverdueInterest(value7);// ʵ��������Ϣ
           dto.setRealPunishInterest(value8);// ʵ�۷�Ϣ
           dto.setNobackCorpus(value9);// δ����������
           dto.setNobackOverdueCorpus(value10);// δ�����ڱ���
           dto.setNobackInterest(value11);// δ��������Ϣ
           dto.setNobackOverdueInterest(value12);// δ��������Ϣ
           dto.setNobackPunishInterest(value13);// δ����Ϣ
           dto.setDeadLine(value14);// ��ǰ�����ʣ������
           list.add(dto);
         }
       }
     }     
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return list;
  }

  public String getFormula(String string,Object[] obj){
    String result = null; 
    int val = 0;
    int value = 0;
    try{
    if(string!=null && !"".equals(string)){
    StringTokenizer substr = new StringTokenizer(string, "+");
    while (substr.hasMoreTokens()) {
      String temp_str = substr.nextToken();
      StringTokenizer substr2 = new StringTokenizer(temp_str, "-");
      int num = substr2.countTokens();
      if (num > 1) {
        int j = 0;
        while (substr2.hasMoreTokens()) {
          String index = substr2.nextToken();
          j++;
          if (j == 1) {
            String str = null;
            if(obj[Integer.parseInt(index)-1]!=null){
             str = obj[Integer.parseInt(index)-1].toString();
            }
            if(str!=null && !"".equals(str)){
              val = new Integer(str).intValue();
     
              value = value+val;
            }
          } else {
            String str = null;
            if(obj[Integer.parseInt(index)-1]!=null){
             str = obj[Integer.parseInt(index)-1].toString();
            }
            if(str!=null && !"".equals(str)){
              val = new Integer(str).intValue();
    
              value = value-val;
            }
          }
        }
      }else{
        String str = null;
        String token = substr2.nextToken();
        int i = Integer.parseInt(token);
        if(obj[i-1]!=null){
         str = obj[i-1].toString();
        }
        if(str!=null && !"".equals(str)){
          val = new Integer(str).intValue();
   
          value = value+val;
        }
      }
    }
    result = new Integer(value).toString();
    }
    }catch(Exception e){
      e.printStackTrace();
    }
    if("".equals(result) || result == null){
      result="0";
    }
    return result;
  }
}
