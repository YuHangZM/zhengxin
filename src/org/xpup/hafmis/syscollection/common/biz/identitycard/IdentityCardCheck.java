package org.xpup.hafmis.syscollection.common.biz.identitycard;

import org.xpup.common.exception.BusinessException;
/**
 *  ���ĺ�..java��֤���֤�ĺ���.. 
 *  return Objectr[] [0]Ϊ����[1]Ϊ�Ա�
 */
public class IdentityCardCheck {
  public static Object[] identityCardCheck(String card)throws BusinessException{
    Object obj[] = new Object[2];
    String birthday = "";
    String month = "";
    String day = "";
    String sex="";
    try{
    if(card.length()!=15 && card.length()!=18){
      throw new BusinessException("���֤λ��������15��18λ:"+card.length()+",\ncard:"+card);
    }
    if(card.length() == 15){
      month = card.substring(8,10);
      day = card.substring(10,12);
      sex = card.substring(14,15);
      if(Integer.parseInt(month)>12)
        throw new BusinessException("���֤���·ݱ�����1-12֮��");
      if(Integer.parseInt(day)>31)
        throw new BusinessException("���֤�����ڱ�����1-31֮��");
      if(Integer.parseInt(sex)!=1 && Integer.parseInt(sex)!=2)
        throw new BusinessException("15λ���֤���Ա���ִ���");
      birthday = "19"+card.substring(6,8)+month+day;
      obj[0] = birthday;
      obj[1] = sex;
    }
    if(card.length() ==18){
      month = card.substring(10,12);
      day = card.substring(12,14);
      sex = card.substring(16,17);
      if(Integer.parseInt(month)>12)
        throw new BusinessException("���֤���·ݱ�����1-12֮��");
      if(Integer.parseInt(day)>31)
        throw new BusinessException("���֤�����ڱ�����1-31֮��");
      if(Integer.parseInt(sex)!=1 && Integer.parseInt(sex)!=2)
        throw new BusinessException("18λ���֤���Ա���ִ���");
      birthday = card.substring(6,10)+month+day;
      obj[0] = birthday;
      obj[1] = sex;
    }
    }catch(BusinessException b){
       throw b;
    }
    return obj;
  }
}
