package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.demo.dao.DemoDAO;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.dto.DemoExportDTO;


/**
 * @author ly TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class demo_expDataSource 
    implements ExportDateSourceInterface {
  
  protected DemoDAO demoDAO;

  public void setEmpPaymentAgreementDAO(
      DemoDAO demoDAO) {
    this.demoDAO = demoDAO;
  }

  public void setOrgPaymentAgreementDAO(
      DemoDAO demoDAO) {
    this.demoDAO = demoDAO;
  }

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList demoExportList1 = new ArrayList();
    DemoExportDTO demoExportDTO = new DemoExportDTO();
    demoExportDTO.setId("ְ�����");
    demoExportDTO.setName("ְ������");
    demoExportDTO.setIdcard("ְ�����֤");
    demoExportDTO.setSex("�Ա�");
    demoExportDTO.setBirthday("��������");
    demoExportDTO.setSalary("����");
    demoExportDTO.setBalance("�ܼ�"); 
    demoExportList1.add(demoExportDTO);
    try {
      for (int i = 0; i < explist.size(); i++) {
        DemoExportDTO demoExportDTO1 = new DemoExportDTO();
        Demo demo = (Demo) explist.get(i);
        demoExportDTO1.setId(""+demo.getId());
        demoExportDTO1.setName(demo.getName());
        demoExportDTO1.setIdcard(demo.getIdcard());
        demoExportDTO1.setSex(demo.getSex());
        demoExportDTO1.setBirthday(demo.getBirthday());
        demoExportDTO1.setSalary(demo.getSalary().toString());
        demoExportDTO1.setBalance(demo.getBalance().toString());
        demoExportList1.add(demoExportDTO1);
      }
      Factory faxtory = new Factory();
      Map demoExportMap = new HashMap();
      demoExportMap.put("DemoExport", demoExportList1);
      faxtory.setInfomation(xmlfile, xlsFile, demoExportMap,"org.xpup.hafmis.demo.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
