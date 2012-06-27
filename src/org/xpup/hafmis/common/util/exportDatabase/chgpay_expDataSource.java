package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayHeadExportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.ChgpayListExportDTO;





/*��λ��š���λ���ơ���������*//*ְ����š�ְ��������֤�����롢ԭ��λ�ɶԭְ���ɶ�µ�λ�ɶ�գ�����ְ���ɶ�գ�*/
/**
 * @author ly TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class chgpay_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList chgpayHeadlist = new ArrayList();
    ArrayList chgpaylist = new ArrayList();
    ChgpayHeadExportDTO chgpayHeadExportDTO = new ChgpayHeadExportDTO();
    ChgpayListExportDTO chgpayListExportDTO = new ChgpayListExportDTO();

    chgpayHeadExportDTO.setOrgId("��λ���");
    chgpayHeadExportDTO.setOrgName("��λ����");
    chgpayHeadExportDTO.setChgMonth("��������");
    chgpayHeadExportDTO.setPayMode("����˵��:");
    chgpayHeadExportDTO.setPayMode1("�ɴ淽ʽ,1:ְ���ɶ�;2:��һ����");
    
    chgpayListExportDTO.setEmpId("ְ�����");
    chgpayListExportDTO.setEmpName("ְ������");
    chgpayListExportDTO.setCardNum("֤������");
    chgpayListExportDTO.setOldOrgPay("ԭ��λ�ɶ�");
    chgpayListExportDTO.setOldEmpPay("ԭְ���ɶ�");
    chgpayListExportDTO.setEmpPay("�µ�λ�ɶ�");
    chgpayListExportDTO.setOrgPay("��ְ���ɶ�");
    chgpayListExportDTO.setSalaryBase("���ʻ���");
   
    
    chgpaylist.add(chgpayListExportDTO);
    chgpayHeadlist.add(chgpayHeadExportDTO);
    
    try {
      
      
      ChgpayHeadExportDTO chgpayHeadExportDTO1 = new ChgpayHeadExportDTO();
      ChgpayInfoDTO chgpayInfoDTO1 = (ChgpayInfoDTO) explist.get(0);     
      String orgid=chgpayInfoDTO1.getOrgId();   
      chgpayHeadExportDTO1.setOrgId(BusiTools.convertSixNumber(orgid));
      chgpayHeadExportDTO1.setOrgName(chgpayInfoDTO1.getOrgName());
      chgpayHeadExportDTO1.setChgMonth(chgpayInfoDTO1.getChgMonth());
      chgpayHeadExportDTO1.setPayMode("");
      chgpayHeadExportDTO1.setPayMode1("");
      
      chgpayHeadlist.add(chgpayHeadExportDTO1);

      for (int i = 0; i < explist.size(); i++) { 
        ChgpayListExportDTO chgpayListExportDTO1 = new ChgpayListExportDTO();  
        ChgpayInfoDTO chgpayInfoDTO = (ChgpayInfoDTO) explist.get(i);    
        String empId=chgpayInfoDTO.getEmpId();        
        chgpayListExportDTO1.setEmpId(BusiTools.convertSixNumber(empId));
        chgpayListExportDTO1.setEmpName(chgpayInfoDTO.getEmpName());
        chgpayListExportDTO1.setCardNum(chgpayInfoDTO.getCardNum());
        chgpayListExportDTO1.setOldOrgPay(chgpayInfoDTO.getOldOrgPay());
        chgpayListExportDTO1.setOldEmpPay(chgpayInfoDTO.getOldEmpPay());
        chgpayListExportDTO1.setEmpPay(chgpayInfoDTO.getEmpPay());
        chgpayListExportDTO1.setOrgPay(chgpayInfoDTO.getOrgPay());
        chgpayListExportDTO1.setSalaryBase(chgpayInfoDTO.getSalaryBase());
        
        chgpaylist.add(chgpayListExportDTO1);
        
       
      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("ChgpayHeadExport", chgpayHeadlist);
      addpayExportMap.put("ChgpayListExport", chgpaylist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.syscollection.chgbiz.chgpay.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
