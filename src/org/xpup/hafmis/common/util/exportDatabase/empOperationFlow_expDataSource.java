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
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto.EmpOperationFlowDTO;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto.EmpOperationFlowExportDTO;

/**
 * @author ly TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class empOperationFlow_expDataSource
    implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList empOperationFlowlist = new ArrayList();
    EmpOperationFlowExportDTO empOperationFlowExportDTO = new EmpOperationFlowExportDTO();
    empOperationFlowExportDTO.setNoteNum("Ʊ�ݱ��");
    empOperationFlowExportDTO.setDocNum("ƾ֤��");
    empOperationFlowExportDTO.setOrgid("��λ���");
    empOperationFlowExportDTO.setOrgname("��λ����");
    empOperationFlowExportDTO.setEmpid("ְ�����");
    empOperationFlowExportDTO.setEmpname("ְ������");
    empOperationFlowExportDTO.setOpType("ҵ������");
    empOperationFlowExportDTO.setOpDate("��������");
    empOperationFlowExportDTO.setOpMoney("�������");
    empOperationFlowExportDTO.setOpInterest("������Ϣ");
    empOperationFlowExportDTO.setOpDirection("��������");
    empOperationFlowExportDTO.setOpStatus("ҵ��״̬");
    empOperationFlowlist.add(empOperationFlowExportDTO);
    try {
      for (int i = 0; i < explist.size(); i++) {
        EmpOperationFlowExportDTO empOperationFlowExportDTO1 = new EmpOperationFlowExportDTO();
        EmpOperationFlowDTO empOperationFlowDTO = (EmpOperationFlowDTO) explist.get(i);
        empOperationFlowExportDTO1.setNoteNum(empOperationFlowDTO.getNoteNum());
        empOperationFlowExportDTO1.setDocNum(empOperationFlowDTO.getDocNum());
        empOperationFlowExportDTO1.setOrgid(BusiTools.convertSixNumber(empOperationFlowDTO.getOrgid().toString()));
        empOperationFlowExportDTO1.setOrgname(empOperationFlowDTO.getOrgname());
        empOperationFlowExportDTO1.setEmpid(BusiTools.convertSixNumber(empOperationFlowDTO.getEmpid().toString()));
        empOperationFlowExportDTO1.setEmpname(empOperationFlowDTO.getEmpname());
        empOperationFlowExportDTO1.setOpType(empOperationFlowDTO.getOpType());
        empOperationFlowExportDTO1.setOpDate(empOperationFlowDTO.getOpDate());
        empOperationFlowExportDTO1.setOpMoney(empOperationFlowDTO.getOpMoney());
        empOperationFlowExportDTO1.setOpInterest(empOperationFlowDTO.getOpInterest());
        empOperationFlowExportDTO1.setOpDirection(empOperationFlowDTO.getOpDirection());
        empOperationFlowExportDTO1.setOpStatus(empOperationFlowDTO.getOpStatus());
        
        empOperationFlowlist.add(empOperationFlowExportDTO1);
      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("EmpOperationFlowExport", empOperationFlowlist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
