package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
      
import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.dto.DemoExportDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenExpContentDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenExpNoteDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenExpTitleDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenImpContentDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpContentDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpTitleDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;

public class empopen_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    try {
    ArrayList exportList1 = new ArrayList();
    EmpOpenExpTitleDTO titleDto1 = new EmpOpenExpTitleDTO();
    titleDto1.setOrgunitcode("��λ���");
    titleDto1.setOrgunitname("��λ����");
    exportList1.add(titleDto1);
    
    String orgId = (String) explist.get(0);
    String orgname = (String)explist.get(1);
    // �жϽɴ淽ʽ
    String type = (String)explist.get(2);
    EmpOpenExpTitleDTO titleDto2 = new EmpOpenExpTitleDTO();
    titleDto2.setOrgunitcode(orgId);
    titleDto2.setOrgunitname(orgname);
    exportList1.add(titleDto2);
    
    
    
    ArrayList exportList2 = new ArrayList();
    EmpOpenExpContentDTO contentDto1 = new EmpOpenExpContentDTO();
    // ְ����š�ְ��������֤�����롢���ʻ�������λ�ɶְ���ɶ�
    contentDto1.setEmpname("ְ������");
    contentDto1.setCardnum("֤������");
    contentDto1.setCardkind("֤������");
    contentDto1.setDept("���ڲ���");
    contentDto1.setTel("��ͥ�绰");
    contentDto1.setMobileTle("�ƶ��绰");
    contentDto1.setMonthIncome("ְ��������");
    contentDto1.setSalarybase("���ʻ���");
    if (type.equals("2")) {
      contentDto1.setOrgpay("��λ�ɶ�");
      contentDto1.setEmppay("ְ���ɶ�");
    }
    exportList2.add(contentDto1);
    if(explist.size()>3){
      for(int i=3;i<explist.size();i++){
        EmpOpenExpContentDTO empOpenImpContentDTO =new EmpOpenExpContentDTO();
        empOpenImpContentDTO=(EmpOpenExpContentDTO)explist.get(i);
        exportList2.add(i-2, empOpenImpContentDTO);
      }
    }
      

    ArrayList exportList3 = new ArrayList();
    EmpOpenExpNoteDTO noteDto3 = new EmpOpenExpNoteDTO();
    noteDto3.setNote("����˵��");
    noteDto3.setDocmun("֤�����ͣ�0���֤  1���ڲ�  2����  3����֤  4ʿ��֤  5�۰ľ��������ڵ�ͨ��֤  6̨��ͬ�������ڵ�ͨ��֤" +
        "  7��ʱ���֤  8����˾���֤  9����֤");
    exportList3.add(noteDto3);
    
    
    
      Factory faxtory = new Factory();
      Map map = new HashMap();

      map.put("EmpOpenExpTitle", exportList1);
      map.put("EmpOpenExpContent", exportList2);
      map.put("EmpOpenExpNote", exportList3);

      faxtory.setInfomation(xmlfile, xlsFile, map,
          "org.xpup.hafmis.syscollection.accountmng.accountopen.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
