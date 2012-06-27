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



/*��λ��š���λ���ơ��������¡�ְ����š�ְ��������֤�����롢����ǰ���ʻ������������ʻ������գ���*/
/**
 * @author ly TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class chgslarybase_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList chgslarybaseHeadlist = new ArrayList();
    ArrayList chgslarybaselist = new ArrayList();
    ChgslarybaseHeadExportDTO chgslarybaseHeadExportDto = new ChgslarybaseHeadExportDTO();
    ChgslarybaseListExportDTO chgslarybaseListExportDto = new ChgslarybaseListExportDTO();
    
    
    chgslarybaseHeadExportDto.setOrgId("��λ���");
    chgslarybaseHeadExportDto.setOrgName("��λ����");
    chgslarybaseHeadExportDto.setChgMonth("��������");
    chgslarybaseHeadExportDto.setPayMode("  ");
    chgslarybaseHeadExportDto.setPayMode1("  ");
    
    chgslarybaseListExportDto.setEmpId("ְ�����");
    chgslarybaseListExportDto.setEmpName("ְ������");
    chgslarybaseListExportDto.setCardNum("֤������");
    chgslarybaseListExportDto.setOldSalaryBase("����ǰ���ʻ���");
    chgslarybaseListExportDto.setSalaryBase("�������ʻ���");
   
    
    chgslarybaselist.add(chgslarybaseListExportDto);
    chgslarybaseHeadlist.add(chgslarybaseHeadExportDto);
    
    try {
      
      
      ChgslarybaseHeadExportDTO chgslarybaseHeadExportDto1 = new ChgslarybaseHeadExportDTO();
      ChgslarybaseInfoDTO chgslarybaseInfoDTO1 = (ChgslarybaseInfoDTO) explist.get(0);
      String orgid=chgslarybaseInfoDTO1.getOrgId();
      chgslarybaseHeadExportDto1.setOrgId(BusiTools.convertTenNumber(orgid));
      chgslarybaseHeadExportDto1.setOrgName(chgslarybaseInfoDTO1.getOrgName());
      chgslarybaseHeadExportDto1.setChgMonth(chgslarybaseInfoDTO1.getChgMonth());
      chgslarybaseHeadlist.add(chgslarybaseHeadExportDto1);

      for (int i = 0; i < explist.size(); i++) {
       
        ChgslarybaseListExportDTO chgslarybaseListExportDto1 = new ChgslarybaseListExportDTO();
        
        
        ChgslarybaseInfoDTO chgslarybaseInfoDTO = (ChgslarybaseInfoDTO) explist.get(i);
        
        String empId=chgslarybaseInfoDTO.getEmpId();
        chgslarybaseListExportDto1.setEmpId(BusiTools.convertSixNumber(empId));
        chgslarybaseListExportDto1.setEmpName(chgslarybaseInfoDTO.getEmpName());
        chgslarybaseListExportDto1.setCardNum(chgslarybaseInfoDTO.getCardNum());
        chgslarybaseListExportDto1.setOldSalaryBase(chgslarybaseInfoDTO.getOldSalaryBase());
        chgslarybaseListExportDto1.setSalaryBase(chgslarybaseInfoDTO.getSalaryBase());
   
        
        chgslarybaselist.add(chgslarybaseListExportDto1);
        
       
      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("ChgslarybaseHeadExport", chgslarybaseHeadlist);
      addpayExportMap.put("ChgslarybaseListExport", chgslarybaselist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
