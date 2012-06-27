package org.xpup.hafmis.syscollection.common.biz.clearinginterest;

import java.math.BigDecimal;

import org.xpup.hafmis.syscollection.dto.ClearingInterestDTO;
import org.xpup.hafmis.syscollection.dto.InterestDto;

/**
 * @author ����
 * 2007-6-26
 * �÷�������ʵ�ּ���������Ϣ��������Ϣ��������Ϣ��
 */
public class ClearingInterestBS implements ClearingInterestInterface {
  
  public InterestDto getClearinginterest(ClearingInterestDTO clearingInterestDTO){
    
    InterestDto interestDto = new InterestDto();
    
    BigDecimal  curInterest = new BigDecimal(0.00);//������Ϣ
    BigDecimal  preInterest = new BigDecimal(0.00);//������Ϣ

    BigDecimal TEMP_curIntegral = clearingInterestDTO.getCurIntegral();//��ʱ�������
    BigDecimal TEMP_preIntegral = clearingInterestDTO.getPreIntegral();//��ʱ�������
    BigDecimal TEMP_curRate = clearingInterestDTO.getCurRate();//��ʱ��������
    BigDecimal TEMP_preRate = clearingInterestDTO.getPreRate();//��ʱ��������
    
    //������Ϣ=�������*��������/365��������Ϣ=�������*��������/365
    curInterest = curInterest.add(TEMP_curIntegral.multiply(TEMP_curRate).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//�汾����Ϣ
    preInterest = preInterest.add(TEMP_preIntegral.multiply(TEMP_preRate).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//��������Ϣ
    
    BigDecimal  TEMP_curIntegealSubA = clearingInterestDTO.getCurIntegealSubA();//��ʱ�ֶα������a
    BigDecimal  TEMP_preIntegealSubA = clearingInterestDTO.getPreIntegealSubA();//��ʱ�ֶ��������a
    BigDecimal  TEMP_curRateA = clearingInterestDTO.getCurRateA();//��ʱ��������a
    BigDecimal  TEMP_preRateA = clearingInterestDTO.getPreRateA();//��ʱ��������a
    
    BigDecimal  TEMP_curIntegealSubB = clearingInterestDTO.getCurIntegealSubB();//��ʱ�ֶα������b
    BigDecimal  TEMP_preIntegealSubB = clearingInterestDTO.getPreIntegealSubB();//��ʱ�ֶ��������b
    BigDecimal  TEMP_curRateB = clearingInterestDTO.getCurRateB();//��ʱ��������b
    BigDecimal  TEMP_preRateB = clearingInterestDTO.getPreRateB();//��ʱ��������b
    
    BigDecimal  TEMP_curIntegealSubC = clearingInterestDTO.getCurIntegealSubC();//��ʱ�ֶα������c
    BigDecimal  TEMP_preIntegealSubC = clearingInterestDTO.getPreIntegealSubC();//��ʱ�ֶ��������c
    BigDecimal  TEMP_curRateC = clearingInterestDTO.getCurRateC();//��ʱ��������c
    BigDecimal  TEMP_preRateC = clearingInterestDTO.getPreRateC();//��ʱ��������c
    
    
    if(!TEMP_curRateA.equals(new BigDecimal(0))){//�����������a!=0:�������=�ֶα������a���������=�ֶ��������a��������Ϣ=�������*��������a/365��������Ϣ=�������*��������a/365
      
      curInterest = curInterest.add(TEMP_curIntegealSubA.multiply(TEMP_curRateA).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//�汾����Ϣ
      preInterest = preInterest.add(TEMP_preIntegealSubA.multiply(TEMP_preRateA).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//��������Ϣ
      
      if(!TEMP_curRateB.equals(new BigDecimal(0))){//�����������b!=0�����һ����¼�������=�ֶα������b���������=�ֶ��������b��������Ϣ=�������*��������b/365��������Ϣ=�������*��������b/365
       
        curInterest = curInterest.add(TEMP_curIntegealSubB.multiply(TEMP_curRateB).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//�汾����Ϣ
        preInterest = preInterest.add(TEMP_preIntegealSubB.multiply(TEMP_preRateB).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//��������Ϣ
       
        if(!TEMP_curRateC.equals(new BigDecimal(0))){//�����������c!=0�����һ����¼�������=�ֶα������c���������=�ֶ��������c��������Ϣ=�������*��������c/365��������Ϣ=�������*��������c/365
          
          curInterest = curInterest.add(TEMP_curIntegealSubC.multiply(TEMP_curRateC).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//�汾����Ϣ
          preInterest = preInterest.add(TEMP_preIntegealSubC.multiply(TEMP_preRateC).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP));//��������Ϣ
        }
      }
    }
    
    interestDto.setCurInterest(curInterest);
    interestDto.setPreInterest(preInterest);
    
    return interestDto;
  }

}
