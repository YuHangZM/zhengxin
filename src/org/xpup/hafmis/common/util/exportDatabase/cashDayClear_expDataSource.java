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
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseHeadExportDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.ChgslarybaseListExportDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcExportDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcShowListDTO;



/*��λ��š���λ���ơ��������¡�ְ����š�ְ��������֤�����롢����ǰ���ʻ������������ʻ������գ���*/
/**
 * @author ly TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class cashDayClear_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
//    ArrayList chgslarybaseHeadlist = new ArrayList();
    ArrayList chgslarybaselist = new ArrayList();
//    ChgslarybaseHeadExportDTO chgslarybaseHeadExportDto = new ChgslarybaseHeadExportDTO();
    CashDayClearTcExportDTO cashDayClearTcExportDTO = new CashDayClearTcExportDTO();
    
    
//    chgslarybaseHeadExportDto.setOrgId("��λ���");
//    chgslarybaseHeadExportDto.setOrgName("��λ����");
//    chgslarybaseHeadExportDto.setChgMonth("��������");
//    chgslarybaseHeadExportDto.setPayMode("����˵��:");
//    chgslarybaseHeadExportDto.setPayMode1("�ɴ淽ʽ,1:ְ���ɶ�;2:��һ����");
    
//    chgslarybaseListExportDto.setEmpId("ְ�����");
//    chgslarybaseListExportDto.setEmpName("ְ������");
//    chgslarybaseListExportDto.setCardNum("֤������");
//    chgslarybaseListExportDto.setOldSalaryBase("����ǰ���ʻ���");
//    chgslarybaseListExportDto.setSalaryBase("�������ʻ���");
    
    
    cashDayClearTcExportDTO.setCredenceDate("����");
    cashDayClearTcExportDTO.setCredenceChaNum("ƾ֤�ֺ�");
    cashDayClearTcExportDTO.setTemp_summary("ժҪ");
    cashDayClearTcExportDTO.setSubjectCode("��Ŀ");
    cashDayClearTcExportDTO.setSubjectName("��Ŀ����");
    cashDayClearTcExportDTO.setDebit("����");
    cashDayClearTcExportDTO.setCredit("�跽");
    cashDayClearTcExportDTO.setBalance("���");
    cashDayClearTcExportDTO.setTemp_settType("���㷽ʽ");
    cashDayClearTcExportDTO.setSettNum("�����");
    cashDayClearTcExportDTO.setDopsn("������");
    cashDayClearTcExportDTO.setMakebill("�Ƶ���");
    cashDayClearTcExportDTO.setCredenceSt("״̬");
    chgslarybaselist.add(cashDayClearTcExportDTO);
//    chgslarybaseHeadlist.add(chgslarybaseHeadExportDto);
    
    try {
      
      
     

      for (int i = 0; i < explist.size(); i++) {
       
        CashDayClearTcExportDTO cashDayClearTcExportDTO1 = new CashDayClearTcExportDTO();
        
        
        CashDayClearTcShowListDTO cashDayClearTcExport = (CashDayClearTcShowListDTO) explist.get(i);
        
        
        cashDayClearTcExportDTO1.setCredenceDate(cashDayClearTcExport.getCredenceDate());
        cashDayClearTcExportDTO1.setCredenceChaNum(cashDayClearTcExport.getCredenceChaNum());
        cashDayClearTcExportDTO1.setTemp_summary(cashDayClearTcExport.getTemp_summary());
        cashDayClearTcExportDTO1.setSubjectCode(cashDayClearTcExport.getSubjectCode());
        cashDayClearTcExportDTO1.setSubjectName(cashDayClearTcExport.getSubjectName());
        cashDayClearTcExportDTO1.setDebit(cashDayClearTcExport.getDebit().toString());
        cashDayClearTcExportDTO1.setCredit(cashDayClearTcExport.getCredit().toString());
        cashDayClearTcExportDTO1.setBalance(cashDayClearTcExport.getBalance().toString());
        cashDayClearTcExportDTO1.setTemp_settType(cashDayClearTcExport.getTemp_settType());
        cashDayClearTcExportDTO1.setSettNum(cashDayClearTcExport.getSettNum());
        cashDayClearTcExportDTO1.setDopsn(cashDayClearTcExport.getDopsn());
        cashDayClearTcExportDTO1.setMakebill(cashDayClearTcExport.getMakebill());
        cashDayClearTcExportDTO1.setCredenceSt(cashDayClearTcExport.getCredenceSt());
   
        
        chgslarybaselist.add(cashDayClearTcExportDTO1);
        
       
      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
     
      addpayExportMap.put("CashDayClearTcExport", chgslarybaselist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
