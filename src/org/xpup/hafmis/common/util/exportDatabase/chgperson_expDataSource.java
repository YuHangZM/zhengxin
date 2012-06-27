package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpContentDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpNoteDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonExpTitleDTO;

public class chgperson_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {

    ChgpersonExpDTO emp = (ChgpersonExpDTO) explist.get(0);

    ArrayList exportList1 = new ArrayList();
    ChgpersonExpTitleDTO titleDto = new ChgpersonExpTitleDTO();
    titleDto.setOrgunitcode("��λ���");
    titleDto.setOrgunitcodecontent(BusiTools.convertTenNumber(emp.getOrgunitcodecontent()));
    titleDto.setOrgunitname("��λ����");
    titleDto.setOrgunitnamecontent(emp.getOrgunitnamecontent());
    titleDto.setOrgunitchgmonth("��������");
    titleDto.setOrgunitchgmonthcontent(emp.getOrgunitchgmonthcontent());
    exportList1.add(titleDto);

    ArrayList exportList2 = new ArrayList();
    ChgpersonExpContentDTO contentDto = new ChgpersonExpContentDTO();
    // ְ����š�ְ��������֤�����͡�֤�����롢�������ڡ��Ա����ڲ��š����ʻ�������λ�ɶְ���ɶ�Ƿ������
    contentDto.setEmpcode("ְ�����");
    contentDto.setEmpname("ְ������");
    contentDto.setCardkind("֤������");
    contentDto.setCardnum("֤������");
    contentDto.setBirthday("��������");
    contentDto.setSex("�Ա�");
    contentDto.setDept("���ڲ���");
    contentDto.setSalarybase("���ʻ���");
    if (emp.getPaymode().equals("2")) {
      contentDto.setOrgpay("��λ�ɶ�");
      contentDto.setEmppay("ְ���ɶ�");
    }
    contentDto.setChgtype("�������");
    contentDto.setPartin("�Ƿ������");
    contentDto.setChgreason("���ԭ��");
    contentDto.setPayStatus("ְ���ɴ�״̬");

    exportList2.add(contentDto);

    ArrayList exportList3 = new ArrayList();
    ChgpersonExpNoteDTO noteDto = new ChgpersonExpNoteDTO();
    noteDto.setNote("˵��:֤�����ͣ�0���֤  1���ڲ�  2����  3����֤  4ʿ��֤  5�۰ľ��������ڵ�ͨ��֤  6̨��ͬ�������ڵ�ͨ��֤  7��ʱ���֤  8����˾���֤  9����֤"+";;;;"
        +"�Ա𣺣�δ֪�Ա��У�Ů 9δ֪˵���Ա�"+";;;;"+"������ͣ������������⣳���"+";;;;"+"�Ƿ�����ɣ�0��,1��"+";;;;"+"���ԭ��1��������2��ת����3��ת�룻4������"+"ѡ��������Ϊ������������д�Ƿ�����ɣ���ѡ��������Ϊ�������ʱ�����Բ�����д�Ƿ�������ֶ�");
    exportList3.add(noteDto);
    
    try {
      for (int i = 0; i < explist.size(); i++) {
        contentDto = new ChgpersonExpContentDTO();
        ChgpersonExpDTO returnEmp = (ChgpersonExpDTO) explist.get(i);
        contentDto.setEmpcode(BusiTools.convertSixNumber(returnEmp.getEmpcode()));
        contentDto.setEmpname(returnEmp.getEmpname());
        contentDto.setCardkind(returnEmp.getCardkind());
        contentDto.setCardnum(returnEmp.getCardnum());
        contentDto.setBirthday(returnEmp.getBirthday());
        contentDto.setSex(returnEmp.getSex());
        if(returnEmp.getDept()==null){
          contentDto.setDept("");
        }else{
          contentDto.setDept(returnEmp.getDept());
        }
        contentDto.setSalarybase(returnEmp.getSalarybase());
        if (returnEmp.getPaymode().equals("2")) {
          contentDto.setOrgpay(returnEmp.getOrgpay());
          contentDto.setEmppay(returnEmp.getEmppay());
        }
        contentDto.setChgtype("");
        contentDto.setPartin("");
        contentDto.setChgreason("");

        contentDto.setPayStatus(returnEmp.getPayStatus());
        exportList2.add(contentDto);
      }

      Factory faxtory = new Factory();
      Map map = new HashMap();

      map.put("ChgpersonExpTitle", exportList1);
      map.put("ChgpersonExpNote", exportList3);
      map.put("ChgpersonExpContent", exportList2);

      faxtory.setInfomation(xmlfile, xlsFile, map,
          "org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
