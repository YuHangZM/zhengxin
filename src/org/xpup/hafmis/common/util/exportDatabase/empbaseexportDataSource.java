package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.dto.EmpBaseExportsEmpDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.dto.EmpBaseExportsOrgDTO;

public class empbaseexportDataSource implements ExportDateSourceInterface {
  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    try {
      ArrayList exportList1 = new ArrayList();
      EmpBaseExportsOrgDTO empBaseExportsOrgDTO = new EmpBaseExportsOrgDTO();
      empBaseExportsOrgDTO.setOrgId("��λ���");
      empBaseExportsOrgDTO.setOrgName("��λ����");
      exportList1.add(empBaseExportsOrgDTO);

      String orgId = (String) explist.get(0);
      String orgName = (String) explist.get(1);
      EmpBaseExportsOrgDTO empBaseExportsOrgDTO1 = new EmpBaseExportsOrgDTO();
      empBaseExportsOrgDTO1.setOrgId(BusiTools.convertTenNumber(orgId));
      empBaseExportsOrgDTO1.setOrgName(orgName);
      exportList1.add(empBaseExportsOrgDTO1);

      ArrayList exportList2 = new ArrayList();
      EmpBaseExportsEmpDTO empBaseExportsEmpDTO = new EmpBaseExportsEmpDTO();
      empBaseExportsEmpDTO.setEmpId("ְ�����");
      empBaseExportsEmpDTO.setEmpName("ְ������");
      empBaseExportsEmpDTO.setCardNum("֤������");
      empBaseExportsEmpDTO.setSalaryBase("���ʻ���");
      empBaseExportsEmpDTO.setOrgPay("��λ�ɶ�");
      empBaseExportsEmpDTO.setEmpPay("ְ���ɶ�");
      empBaseExportsEmpDTO.setAllPay("�ɶ�ϼ�");
      empBaseExportsEmpDTO.setPayStatus("�ɴ�״̬");
      empBaseExportsEmpDTO.setOrg_pay_month("��λ��������");
      empBaseExportsEmpDTO.setEmp_pay_month("ְ����������");
      empBaseExportsEmpDTO.setBalance("�˻����");
      exportList2.add(empBaseExportsEmpDTO);

      Factory faxtory = new Factory();
      Map map = new HashMap();
      map.put("EmpBaseExportsOrg", exportList1);
      map.put("EmpBaseExportsEmp", exportList2);
      map.put("EmpBaseExports", explist.subList(2, explist.size()));

      faxtory
          .setInfomation(
              xmlfile,
              xlsFile,
              map,
              "org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
