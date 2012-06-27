package org.xpup.hafmis.sysloan.dataready.collloanbackpara.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.CollLoanbackParaDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.CollLoanbackPara;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBankPara;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.dataready.collloanbackpara.bsinterface.ICollLoanbackParaBS;
import org.xpup.hafmis.sysloan.dataready.collloanbackpara.dto.CollLoanbackParaDTO;
import org.xpup.security.common.domain.Userslogincollbank;

public class CollLoanbackParaBS implements ICollLoanbackParaBS{
  private CollLoanbackParaDAO collLoanbackParaDAO=null;
  private PlOperateLogDAO plOperateLogDAO=null;
  /**
   * �����𻹴���������
   * @author ���ƽ
   * 2007-12-17
   * ���ݰ��´���pl009������
   * ��ѯ������office
   */
  public CollLoanbackParaDTO findCollLoanbackParaInfo(String office) throws Exception{
    // TODO Auto-generated method stub
    CollLoanbackParaDTO collLoanbackParaDTO=new CollLoanbackParaDTO();
    List list=null;
    try{
      CollLoanbackPara collLoanbackPara=null;
      list=collLoanbackParaDAO.queryCollLoanbackParaByOffice(office);
      for (int i = 0; i < list.size(); i++) {
        collLoanbackPara=(CollLoanbackPara)list.get(i);
        char paramNum=collLoanbackPara.getParamNum().charAt(0);
        switch(paramNum){
          case '1':{
            collLoanbackParaDTO.setPickMoneyType(collLoanbackPara.getParamValue());
            char paramValue;
            if(collLoanbackPara.getParamValue().length()>0){
              paramValue=collLoanbackPara.getParamValue().charAt(0);
            }else{
              paramValue='X';
            }
            switch (paramValue) {
              case 'A':{
                collLoanbackParaDTO.setBalance(new BigDecimal(collLoanbackPara.getParamExplain()));
                break;
              }
              case 'B':{
                collLoanbackParaDTO.setMonthMoney(collLoanbackPara.getParamExplain());
                break;
              }
              case 'C':{
                collLoanbackParaDTO.setMonthPayMoney(collLoanbackPara.getParamExplain());
                break;
              }
            }
            break;
          }
          case '2':{
            collLoanbackParaDTO.setIsDeduct(collLoanbackPara.getParamValue());
//            if(collLoanbackPara.getParamValue().equals("0")){
//              collLoanbackParaDTO.setIsOverPay(collLoanbackPara.getParamExplain());
//            }
            break;
          }
          case '3':{
            collLoanbackParaDTO.setIsPreOnly(collLoanbackPara.getParamValue());
            break;
          }
          case '4':{
            collLoanbackParaDTO.setIsPickLessThanPay(collLoanbackPara.getParamValue());
            break;
          }
          case '5':{
            collLoanbackParaDTO.setIsOtherDeduct(collLoanbackPara.getParamValue());
            break;
          }
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return collLoanbackParaDTO;
  }
  /**
   * �����𻹴���������
   * @author ���ƽ
   * 2007-12-17
   * ȷ��
   */
  public void saveCollLoanbackParaInfo(CollLoanbackParaDTO collLoanbackParaDTO,SecurityInfo securityInfo) throws Exception{
    try{
      String office=collLoanbackParaDTO.getOffice();
      List officeList = securityInfo.getOfficeList();
      if("100".equals(office)){
        OfficeDto office_1 = null;
        OfficeDto officeDto=new OfficeDto();
        officeDto.setOfficeCode("100");
        officeDto.setOfficeName("ȫ��");
        officeList.add(officeDto);
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          office_1 = (OfficeDto) itr1.next();
          office=office_1.getOfficeCode();
         // ɾ��pl009
          collLoanbackParaDAO.deleteCollLoanbackPara(office);
          //����pl009
          CollLoanbackPara collLoanbackPara=null;
          collLoanbackPara=new CollLoanbackPara();
          collLoanbackPara.setOffice(office);
          collLoanbackPara.setParamNum("1");
          collLoanbackPara.setParamDescrip("��ȡ���A.������B.�����»���Ϣ�� C.�����½ɴ�");
          collLoanbackPara.setParamValue(collLoanbackParaDTO.getPickMoneyType());
          if(collLoanbackParaDTO.getPickMoneyType().equals("A")){
            collLoanbackPara.setParamExplain(collLoanbackParaDTO.getBalance().toString());
          }else if(collLoanbackParaDTO.getPickMoneyType().equals("B")){
            collLoanbackPara.setParamExplain(collLoanbackParaDTO.getMonthMoney().toString());
          }else if(collLoanbackParaDTO.getPickMoneyType().equals("C")){
            collLoanbackPara.setParamExplain(collLoanbackParaDTO.getMonthPayMoney().toString());
          }
          collLoanbackParaDAO.insert(collLoanbackPara);
          collLoanbackPara=new CollLoanbackPara();
          collLoanbackPara.setOffice(office);
          collLoanbackPara.setParamNum("2");
          collLoanbackPara.setParamDescrip("�ɿ۹���������ʱ�Ƿ�ۿ�:��Ϊ0����Ϊ1��");
          collLoanbackPara.setParamValue(collLoanbackParaDTO.getIsDeduct());
//          if(collLoanbackParaDTO.getIsDeduct().equals("0")){
//            collLoanbackPara.setParamExplain(collLoanbackParaDTO.getIsOverPay());
//          }
          collLoanbackParaDAO.insert(collLoanbackPara);
          collLoanbackPara=new CollLoanbackPara();
          collLoanbackPara.setOffice(office);
          collLoanbackPara.setParamNum("3");
          collLoanbackPara.setParamDescrip("ֻ���������:��Ϊ0����Ϊ1");
          collLoanbackPara.setParamValue(collLoanbackParaDTO.getIsPreOnly());
          collLoanbackParaDAO.insert(collLoanbackPara);
          collLoanbackPara=new CollLoanbackPara();
          collLoanbackPara.setOffice(office);
          collLoanbackPara.setParamNum("4");
          collLoanbackPara.setParamDescrip("����ȡ������½ɴ��:��Ϊ0����Ϊ1");
          collLoanbackPara.setParamValue(collLoanbackParaDTO.getIsPickLessThanPay());
          collLoanbackParaDAO.insert(collLoanbackPara);
          collLoanbackPara=new CollLoanbackPara();
          collLoanbackPara.setOffice(office);
          collLoanbackPara.setParamNum("5");
          collLoanbackPara.setParamDescrip("����������Ƿ���Կۿ�:��Ϊ0����Ϊ1");
          collLoanbackPara.setParamValue(collLoanbackParaDTO.getIsOtherDeduct());
          collLoanbackParaDAO.insert(collLoanbackPara);
          // ������־PL021
          PlOperateLog plOperateLog = new PlOperateLog();
          plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
          plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_COLLLOANBACKPARA));
          plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
          plOperateLog.setOpIp(securityInfo.getUserIp());
          plOperateLog.setOpTime(new Date());
          plOperateLog.setOperator(securityInfo.getUserName());
          plOperateLogDAO.insert(plOperateLog);
        }
        officeList.remove(officeList.size()-1);
      }else{
        String count ="";
//      ɾ��pl009
        collLoanbackParaDAO.deleteCollLoanbackPara(office);
        //����pl009
        CollLoanbackPara collLoanbackPara=null;
        collLoanbackPara=new CollLoanbackPara();
        collLoanbackPara.setOffice(office);
        collLoanbackPara.setParamNum("1");
        collLoanbackPara.setParamDescrip("��ȡ���A.������B.�����»���Ϣ�� C.�����½ɴ�");
        collLoanbackPara.setParamValue(collLoanbackParaDTO.getPickMoneyType());
        if(collLoanbackParaDTO.getPickMoneyType().equals("A")){
          collLoanbackPara.setParamExplain(collLoanbackParaDTO.getBalance().toString());
        }else if(collLoanbackParaDTO.getPickMoneyType().equals("B")){
          collLoanbackPara.setParamExplain(collLoanbackParaDTO.getMonthMoney().toString());
        }else if(collLoanbackParaDTO.getPickMoneyType().equals("C")){
          collLoanbackPara.setParamExplain(collLoanbackParaDTO.getMonthPayMoney().toString());
        }
        count=collLoanbackParaDAO.queryParamValueCount_wsh(office, "1");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //���¶�Ӧ��ploo3�е�����Ϊ""
          collLoanbackParaDAO.updatePl009_wsh("", "100","1");
        }
        if("1".equals(count)){
          String value="";
          value=collLoanbackParaDAO.queryParamValue_LJ(new Integer("100"), "1");
          if(!collLoanbackParaDTO.getPickMoneyType().equals(value)){
            collLoanbackParaDAO.updatePl009_wsh("", "100","1");
          }
        }
        collLoanbackParaDAO.insert(collLoanbackPara);
        collLoanbackPara=new CollLoanbackPara();
        collLoanbackPara.setOffice(office);
        collLoanbackPara.setParamNum("2");
        collLoanbackPara.setParamDescrip("�ɿ۹���������ʱ�Ƿ�ۿ�:��Ϊ0����Ϊ1��");
        collLoanbackPara.setParamValue(collLoanbackParaDTO.getIsDeduct());
//        if(collLoanbackParaDTO.getIsDeduct().equals("0")){
//          collLoanbackPara.setParamExplain(collLoanbackParaDTO.getIsOverPay());
//        }
        count=collLoanbackParaDAO.queryParamValueCount_wsh(office, "2");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //���¶�Ӧ��ploo3�е�����Ϊ""
          collLoanbackParaDAO.updatePl009_wsh("", "100","2");
        }
        if("1".equals(count)){
          String value="";
          value=collLoanbackParaDAO.queryParamValue_LJ(new Integer("100"), "2");
          if(!collLoanbackParaDTO.getIsDeduct().equals(value)){
            collLoanbackParaDAO.updatePl009_wsh("", "100","2");
          }
        }
        collLoanbackParaDAO.insert(collLoanbackPara);
        collLoanbackPara=new CollLoanbackPara();
        collLoanbackPara.setOffice(office);
        collLoanbackPara.setParamNum("3");
        collLoanbackPara.setParamDescrip("ֻ���������:��Ϊ0����Ϊ1");
        collLoanbackPara.setParamValue(collLoanbackParaDTO.getIsPreOnly());
        count=collLoanbackParaDAO.queryParamValueCount_wsh(office, "3");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //���¶�Ӧ��ploo3�е�����Ϊ""
          collLoanbackParaDAO.updatePl009_wsh("", "100","3");
        }
        if("1".equals(count)){
          String value="";
          value=collLoanbackParaDAO.queryParamValue_LJ(new Integer("100"), "3");
          if(!collLoanbackParaDTO.getIsPreOnly().equals(value)){
            collLoanbackParaDAO.updatePl009_wsh("", "100","3");
          }
        }
        collLoanbackParaDAO.insert(collLoanbackPara);
        collLoanbackPara=new CollLoanbackPara();
        collLoanbackPara.setOffice(office);
        collLoanbackPara.setParamNum("4");
        collLoanbackPara.setParamDescrip("����ȡ������½ɴ��:��Ϊ0����Ϊ1");
        collLoanbackPara.setParamValue(collLoanbackParaDTO.getIsPickLessThanPay());
        count=collLoanbackParaDAO.queryParamValueCount_wsh(office, "4");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //���¶�Ӧ��ploo3�е�����Ϊ""
          collLoanbackParaDAO.updatePl009_wsh("", "100","4");
        }
        if("1".equals(count)){
          String value="";
          value=collLoanbackParaDAO.queryParamValue_LJ(new Integer("100"), "4");
          if(!collLoanbackParaDTO.getIsPickLessThanPay().equals(value)){
            collLoanbackParaDAO.updatePl009_wsh("", "100","4");
          }
        }
        collLoanbackParaDAO.insert(collLoanbackPara);
        collLoanbackPara=new CollLoanbackPara();
        collLoanbackPara.setOffice(office);
        collLoanbackPara.setParamNum("5");
        collLoanbackPara.setParamDescrip("����������Ƿ���Կۿ�:��Ϊ0����Ϊ1");
        collLoanbackPara.setParamValue(collLoanbackParaDTO.getIsOtherDeduct());
        count=collLoanbackParaDAO.queryParamValueCount_wsh(office, "5");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //���¶�Ӧ��ploo3�е�����Ϊ""
          collLoanbackParaDAO.updatePl009_wsh("", "100","5");
        }
        if("1".equals(count)){
          String value="";
          value=collLoanbackParaDAO.queryParamValue_LJ(new Integer("100"), "5");
          if(!collLoanbackParaDTO.getIsOtherDeduct().equals(value)){
            collLoanbackParaDAO.updatePl009_wsh("", "100","5");
          }
        }
        collLoanbackParaDAO.insert(collLoanbackPara);
        // ������־PL021
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
        plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_COLLLOANBACKPARA));
        plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLogDAO.insert(plOperateLog);
      }
      
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  public void setCollLoanbackParaDAO(CollLoanbackParaDAO collLoanbackParaDAO) {
    this.collLoanbackParaDAO = collLoanbackParaDAO;
  }
  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }
}
