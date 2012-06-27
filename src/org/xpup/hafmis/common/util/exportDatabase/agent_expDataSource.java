package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentExportDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentExportHeadDTO;
import org.xpup.hafmis.syscollection.paymng.agent.dto.AgentExportTitleDTO;

public class agent_expDataSource implements ExportDateSourceInterface{

  public void makeFile(File xmlfile, String xlsFile, String para, List explist) throws Exception {
    try {
      ArrayList exportList1 = new ArrayList();
      AgentExportTitleDTO AgentExportTitleDTO = new AgentExportTitleDTO();
      AgentExportTitleDTO.setAgentYearMonth("��������");
      AgentExportTitleDTO.setNoteNum("�����");
      AgentExportTitleDTO.setPayMode("���㷽ʽ");
      AgentExportTitleDTO.setAgentType("��������");
      exportList1.add(AgentExportTitleDTO);
      
      List exportList = new ArrayList();

      AgentExportDTO AgentExportDTO = new AgentExportDTO();
      AgentExportDTO.setOrgAgentNum("��λ���ۺ�");
      AgentExportDTO.setOrgName("��λ����");
      AgentExportDTO.setEmpAgentNum("ְ�����ۺ�");
      AgentExportDTO.setEmpName("ְ������");
      AgentExportDTO.setCardNum("���֤��");
      AgentExportDTO.setAgentOrgPay("��λ�ɴ��");
      AgentExportDTO.setAgentEmpPay("ְ���ɴ��");
      AgentExportDTO.setAgentEmpSalary("����");
      exportList.add(AgentExportDTO);
      
      List exportList2 = new ArrayList();
      AgentExportHeadDTO agentExportHeadDTO = new AgentExportHeadDTO();
      agentExportHeadDTO.setExplain("����˵�������㷽ʽ��1���� 2�ɵ�λ 3��ְ��   �������ͣ�1�������� 2Ƿ�ɴ���");
      exportList2.add(agentExportHeadDTO);
      
      Factory faxtory = new Factory();
      Map map = new HashMap();
      
      map.put("AgentExportHead", exportList2);
      map.put("AgentExportTitle", exportList1);
      map.put("AgentExport", exportList);

      faxtory.setInfomation(xmlfile, xlsFile, map,
          "org.xpup.hafmis.syscollection.paymng.agent.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
