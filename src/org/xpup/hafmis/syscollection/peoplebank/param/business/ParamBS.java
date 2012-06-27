package org.xpup.hafmis.syscollection.peoplebank.param.business;


import java.util.Date;
import java.util.List;

import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OfficeParaDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.OfficePara;
import org.xpup.hafmis.syscollection.peoplebank.param.bsinterface.IParamBS;
import org.xpup.hafmis.syscollection.peoplebank.param.dto.ParamDTO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;


public class ParamBS implements IParamBS{
  private OfficeParaDAO officeParaDAO=null;
  private HafOperateLogDAO hafDao=null;
  /**
   * ��������
   * @author ��˶
   * 2008-2-15
   * ��aa412������
   */
  public ParamDTO findParamInfo() throws Exception{
    // TODO Auto-generated method stub
    ParamDTO paramDTO=new ParamDTO();
    List list=null;
    try{
      OfficePara officePara=null;
      list=officeParaDAO.queryParamlist();
      if(list.size()!=0){
        if(list.size()==1){
          officePara=(OfficePara)list.get(0);
          paramDTO.setSettingType(officePara.getSettingType());
          paramDTO.setMark(officePara.getParamValue());
        }else{
          officePara=(OfficePara)list.get(0);
          paramDTO.setLength10(officePara.getParamValue());
          officePara=(OfficePara)list.get(1);
          paramDTO.setAlign10(officePara.getParamValue());
          officePara=(OfficePara)list.get(2);
          paramDTO.setFormat10(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(3);
          paramDTO.setLength11(officePara.getParamValue());
          officePara=(OfficePara)list.get(4);
          paramDTO.setAlign11(officePara.getParamValue());
          officePara=(OfficePara)list.get(5);
          paramDTO.setFormat11(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(6);
          paramDTO.setLength12(officePara.getParamValue());
          officePara=(OfficePara)list.get(7);
          paramDTO.setAlign12(officePara.getParamValue());
          officePara=(OfficePara)list.get(8);
          paramDTO.setFormat12(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(9);
          paramDTO.setLength13(officePara.getParamValue());
          officePara=(OfficePara)list.get(10);
          paramDTO.setAlign13(officePara.getParamValue());
          officePara=(OfficePara)list.get(11);
          paramDTO.setFormat13(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(12);
          paramDTO.setLength14(officePara.getParamValue());
          officePara=(OfficePara)list.get(13);
          paramDTO.setAlign14(officePara.getParamValue());
          officePara=(OfficePara)list.get(14);
          paramDTO.setFormat14(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(15);
          paramDTO.setLength15(officePara.getParamValue());
          officePara=(OfficePara)list.get(16);
          paramDTO.setAlign15(officePara.getParamValue());
          officePara=(OfficePara)list.get(17);
          paramDTO.setFormat15(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(18);
          paramDTO.setLength16(officePara.getParamValue());
          officePara=(OfficePara)list.get(19);
          paramDTO.setAlign16(officePara.getParamValue());
          officePara=(OfficePara)list.get(20);
          paramDTO.setFormat16(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(21);
          paramDTO.setLength17(officePara.getParamValue());
          officePara=(OfficePara)list.get(22);
          paramDTO.setAlign17(officePara.getParamValue());
          officePara=(OfficePara)list.get(23);
          paramDTO.setFormat17(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(24);
          paramDTO.setLength18(officePara.getParamValue());
          officePara=(OfficePara)list.get(25);
          paramDTO.setAlign18(officePara.getParamValue());
          officePara=(OfficePara)list.get(26);
          paramDTO.setFormat18(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(27);
          paramDTO.setLength19(officePara.getParamValue());
          officePara=(OfficePara)list.get(28);
          paramDTO.setAlign19(officePara.getParamValue());
          officePara=(OfficePara)list.get(29);
          paramDTO.setFormat19(officePara.getParamValue());
          
          
          officePara=(OfficePara)list.get(30);
          paramDTO.setSettingType(officePara.getSettingType());
          paramDTO.setLength1(officePara.getParamValue());
          officePara=(OfficePara)list.get(31);
          paramDTO.setAlign1(officePara.getParamValue());
          officePara=(OfficePara)list.get(32);
          paramDTO.setFormat1(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(33);
          paramDTO.setLength20(officePara.getParamValue());
          officePara=(OfficePara)list.get(34);
          paramDTO.setAlign20(officePara.getParamValue());
          officePara=(OfficePara)list.get(35);
          paramDTO.setFormat20(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(36);
          paramDTO.setLength2(officePara.getParamValue());
          officePara=(OfficePara)list.get(37);
          paramDTO.setAlign2(officePara.getParamValue());
          officePara=(OfficePara)list.get(38);
          paramDTO.setFormat2(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(39);
          paramDTO.setLength3(officePara.getParamValue());
          officePara=(OfficePara)list.get(40);
          paramDTO.setAlign3(officePara.getParamValue());
          officePara=(OfficePara)list.get(41);
          paramDTO.setFormat3(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(42);
          paramDTO.setLength4(officePara.getParamValue());
          officePara=(OfficePara)list.get(43);
          paramDTO.setAlign4(officePara.getParamValue());
          officePara=(OfficePara)list.get(44);
          paramDTO.setFormat4(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(45);
          paramDTO.setLength5(officePara.getParamValue());
          officePara=(OfficePara)list.get(46);
          paramDTO.setAlign5(officePara.getParamValue());
          officePara=(OfficePara)list.get(47);
          paramDTO.setFormat5(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(48);
          paramDTO.setLength6(officePara.getParamValue());
          officePara=(OfficePara)list.get(49);
          paramDTO.setAlign6(officePara.getParamValue());
          officePara=(OfficePara)list.get(50);
          paramDTO.setFormat6(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(51);
          paramDTO.setLength7(officePara.getParamValue());
          officePara=(OfficePara)list.get(52);
          paramDTO.setAlign7(officePara.getParamValue());
          officePara=(OfficePara)list.get(53);
          paramDTO.setFormat7(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(54);
          paramDTO.setLength8(officePara.getParamValue());
          officePara=(OfficePara)list.get(55);
          paramDTO.setAlign8(officePara.getParamValue());
          officePara=(OfficePara)list.get(56);
          paramDTO.setFormat8(officePara.getParamValue());
          
          officePara=(OfficePara)list.get(57);
          paramDTO.setLength9(officePara.getParamValue());
          officePara=(OfficePara)list.get(58);
          paramDTO.setAlign9(officePara.getParamValue());
          officePara=(OfficePara)list.get(59);
          paramDTO.setFormat9(officePara.getParamValue());
          
         
         
        }
      }
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return paramDTO;
  }

  /**
   * ��������
   * ȷ����ť
   * @author ��˶
   * 2008-02-18
   */
  public void saveParamInfo(ParamDTO paramDTO,SecurityInfo securityInfo) throws Exception{
    try{
      ParamDTO paramDTO1=paramDTO;
      String settingType="";
      settingType=paramDTO1.getSettingType();
      OfficePara officePara=null;
      if(settingType.equals("A")){
        officeParaDAO.deleteOfficePara();
        officePara=new OfficePara();
        officePara.setParamDescrip("�ָ�����");
        officePara.setParamValue(paramDTO.getMark().trim());
        officePara.setParamNum("1");
        officePara.setSettingType("A");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara); 
      }else{
        officeParaDAO.deleteOfficePara();
        officePara=new OfficePara();
        officePara.setParamDescrip("���ݸ�ʽ�汾��_����");
        officePara.setParamValue(paramDTO.getLength1().trim());
        officePara.setParamNum("1_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("���ݸ�ʽ�汾��_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign1().trim());
        officePara.setParamNum("1_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("���ݸ�ʽ�汾��_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat1().trim());
        officePara.setParamNum("1_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�����ϱ�����_����");
        officePara.setParamValue(paramDTO.getLength2().trim());
        officePara.setParamNum("2_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�����ϱ�����_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign2().trim());
        officePara.setParamNum("2_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�����ϱ�����_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat2().trim());
        officePara.setParamNum("2_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�˻���¼����_����");
        officePara.setParamValue(paramDTO.getLength3().trim());
        officePara.setParamNum("3_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�˻���¼����_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign3().trim());
        officePara.setParamNum("3_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�˻���¼����_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat3().trim());
        officePara.setParamNum("3_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������ʱ��_����");
        officePara.setParamValue(paramDTO.getLength4().trim());
        officePara.setParamNum("4_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������ʱ��_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign4().trim());
        officePara.setParamNum("4_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������ʱ��_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat4().trim());
        officePara.setParamNum("4_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��ϵ��_����");
        officePara.setParamValue(paramDTO.getLength5().trim());
        officePara.setParamNum("5_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��ϵ��_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign5().trim());
        officePara.setParamNum("5_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��ϵ��_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat5().trim());
        officePara.setParamNum("5_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��ϵ�绰_����");
        officePara.setParamValue(paramDTO.getLength6().trim());
        officePara.setParamNum("6_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��ϵ�绰_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign6().trim());
        officePara.setParamNum("6_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��ϵ�绰_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat6().trim());
        officePara.setParamNum("6_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("����ʶ�����_����");
        officePara.setParamValue(paramDTO.getLength7().trim());
        officePara.setParamNum("7_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("����ʶ�����_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign7().trim());
        officePara.setParamNum("7_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("����ʶ�����_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat7().trim());
        officePara.setParamNum("7_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�����ص�_����");
        officePara.setParamValue(paramDTO.getLength8().trim());
        officePara.setParamNum("8_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�����ص�_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign8().trim());
        officePara.setParamNum("8_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�����ص�_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat8().trim());
        officePara.setParamNum("8_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("���ݷ�������_����");
        officePara.setParamValue(paramDTO.getLength9().trim());
        officePara.setParamNum("9_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("���ݷ�������_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign9().trim());
        officePara.setParamNum("9_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("���ݷ�������_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat9().trim());
        officePara.setParamNum("9_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�����˺�_����");
        officePara.setParamValue(paramDTO.getLength10().trim());
        officePara.setParamNum("10_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�����˺�_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign10().trim());
        officePara.setParamNum("10_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�����˺�_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat10().trim());
        officePara.setParamNum("10_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("����_����");
        officePara.setParamValue(paramDTO.getLength11().trim());
        officePara.setParamNum("11_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("����_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign11().trim());
        officePara.setParamNum("11_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("����_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat11().trim());
        officePara.setParamNum("11_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("���֤��_����");
        officePara.setParamValue(paramDTO.getLength12().trim());
        officePara.setParamNum("12_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("���֤��_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign12().trim());
        officePara.setParamNum("12_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("���֤��_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat12().trim());
        officePara.setParamNum("12_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��λ����_����");
        officePara.setParamValue(paramDTO.getLength13().trim());
        officePara.setParamNum("13_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��λ����_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign13().trim());
        officePara.setParamNum("13_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��λ����_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat13().trim());
        officePara.setParamNum("13_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��λ����_����");
        officePara.setParamValue(paramDTO.getLength14().trim());
        officePara.setParamNum("14_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��λ����_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign14().trim());
        officePara.setParamNum("14_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��λ����_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat14().trim());
        officePara.setParamNum("14_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������_����");
        officePara.setParamValue(paramDTO.getLength15().trim());
        officePara.setParamNum("15_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign15().trim());
        officePara.setParamNum("15_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat15().trim());
        officePara.setParamNum("15_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������_����");
        officePara.setParamValue(paramDTO.getLength16().trim());
        officePara.setParamNum("16_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign16().trim());
        officePara.setParamNum("16_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat16().trim());
        officePara.setParamNum("16_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������_����");
        officePara.setParamValue(paramDTO.getLength17().trim());
        officePara.setParamNum("17_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign17().trim());
        officePara.setParamNum("17_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("��������_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat17().trim());
        officePara.setParamNum("17_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("������ɽ�״̬_����");
        officePara.setParamValue(paramDTO.getLength18().trim());
        officePara.setParamNum("18_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("������ɽ�״̬_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign18().trim());
        officePara.setParamNum("18_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("������ɽ�״̬_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat18().trim());
        officePara.setParamNum("18_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("���һ�νɽ�����_����");
        officePara.setParamValue(paramDTO.getLength19().trim());
        officePara.setParamNum("19_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("���һ�νɽ�����_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign19().trim());
        officePara.setParamNum("19_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("���һ�νɽ�����_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat19().trim());
        officePara.setParamNum("19_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�½ɴ��_����");
        officePara.setParamValue(paramDTO.getLength20().trim());
        officePara.setParamNum("20_1");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�½ɴ��_���뷽ʽ:1������룬2���Ҷ���");
        officePara.setParamValue(paramDTO.getAlign20().trim());
        officePara.setParamNum("20_2");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        officePara=new OfficePara();
        officePara.setParamDescrip("�½ɴ��_�ĵ���ʽ:1������2��Ӣ��");
        officePara.setParamValue(paramDTO.getFormat20().trim());
        officePara.setParamNum("20_3");
        officePara.setSettingType("B");
        officePara.setOffice(" ");
        officeParaDAO.insert(officePara);
        
        
      }
      HafOperateLog haf = new HafOperateLog();
      haf.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));// ס��������鼯ϵͳ
      haf.setOpModel(BusiLogConst.OP_MODE_BANKDATAEXP_RECORDPARAMSETTING+"");// ����ģ��
      haf.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD+"");// ����
      haf.setOpIp(securityInfo.getUserIp());// IP��ַ
      haf.setOrgId(new Integer(0));
      haf.setOpTime(new Date(new java.util.Date().getTime()));// ʱ��
      haf.setOperator(securityInfo.getUserInfo().getRealName());
      hafDao.insert(haf);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public void setOfficeParaDAO(OfficeParaDAO officeParaDAO) {
    this.officeParaDAO = officeParaDAO;
  }
  public void setHafDao(HafOperateLogDAO hafDao) {
    this.hafDao = hafDao;
  }

}
