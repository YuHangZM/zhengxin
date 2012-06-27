package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.AddPayExpDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpAddPayHeadExportDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpExpInfoDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.ExplainExpDTO;



public class empaddpay_expDataSource implements ExportDateSourceInterface {
   

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList empaddpayHeadlist = new ArrayList();
    ArrayList empaddpaylist = new ArrayList();
    ArrayList empaddpayExplainExplist = new ArrayList();
    AddPayExpDTO addPayExpDTO = new AddPayExpDTO();
    EmpAddPayHeadExportDTO empAddPayHeadExportDTO = new EmpAddPayHeadExportDTO();
    ExplainExpDTO explainExpDTO = new ExplainExpDTO();
    explainExpDTO.setExplain("�������ͣ�1��������2����3�����ڲ���");
    empAddPayHeadExportDTO.setOrgId("��λ���");
    empAddPayHeadExportDTO.setOrgName("��λ����"); 
    empAddPayHeadExportDTO.setNoteNum("�����");
    addPayExpDTO.setEmpId("ְ�����");
    addPayExpDTO.setEmpName("ְ������");
    addPayExpDTO.setOrgPay("��λ���ɽ��");
    addPayExpDTO.setEmpPay("ְ�����ɽ��");
    addPayExpDTO.setBeginMonth("������ʼ����");
    addPayExpDTO.setEndMonth("������ֹ����");
    addPayExpDTO.setReason("����ԭ��");
    addPayExpDTO.setAddPayType("��������");
    empaddpaylist.add(addPayExpDTO);
    empaddpayHeadlist.add(empAddPayHeadExportDTO);
    empaddpayExplainExplist.add(explainExpDTO);
    try {
      for (int i = 0; i < explist.size(); i++) {
        EmpAddPayHeadExportDTO empAddPayHeadExportDTO1 = new EmpAddPayHeadExportDTO();
        AddPayExpDTO addPayExpDTO1 = new AddPayExpDTO();
        EmpExpInfoDTO empExpInfoDTO = (EmpExpInfoDTO) explist.get(i);
        empAddPayHeadExportDTO1.setOrgId(empExpInfoDTO.getOrgId());
        empAddPayHeadExportDTO1.setOrgName(empExpInfoDTO.getOrgName());    
        empAddPayHeadExportDTO1.setNoteNum(empExpInfoDTO.getNoteNum());
        addPayExpDTO1.setEmpId(empExpInfoDTO.getEmpId());
        addPayExpDTO1.setEmpName(empExpInfoDTO.getEmpName());      
        addPayExpDTO1.setOrgPay(empExpInfoDTO.getOrgPay());
        addPayExpDTO1.setEmpPay(empExpInfoDTO.getEmpPay());         
        addPayExpDTO1.setBeginMonth(empExpInfoDTO.getBeginMonth());
        addPayExpDTO1.setEndMonth(empExpInfoDTO.getEndMonth()); 
        addPayExpDTO1.setReason(empExpInfoDTO.getReason()); 
        addPayExpDTO1.setAddPayType(empExpInfoDTO.getAddPayType());
        empaddpaylist.add(addPayExpDTO1);
        empaddpayHeadlist.add(empAddPayHeadExportDTO1);
      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("EmpAddPayHeadExport", empaddpayHeadlist);
      addpayExportMap.put("AddPayExp", empaddpaylist);
      addpayExportMap.put("ExplainExp", empaddpayExplainExplist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.syscollection.paymng.personaddpay.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
