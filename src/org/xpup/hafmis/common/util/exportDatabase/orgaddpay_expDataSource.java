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
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.AddpayInfoDto;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayHeadExportDTO;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayListExportDTO;

/*��λ��š���λ���ơ��������¡�Ʊ�ݱ�š�ְ����š�ְ����������λӦ�ɽ�ְ��Ӧ�ɽ���λ���ɽ�ְ�����ɽ�������ʱ����һ����0��*/
/**
 * @author ly TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class orgaddpay_expDataSource
    implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList orgaddpayHeadlist = new ArrayList();
    ArrayList orgaddpaylist = new ArrayList();
    OrgaddpayHeadExportDTO orgaddpayHeadExportDto = new OrgaddpayHeadExportDTO();
    OrgaddpayListExportDTO orgaddpayListExportDto = new OrgaddpayListExportDTO();
    orgaddpayHeadExportDto.setOrgid("��λ���");
    orgaddpayHeadExportDto.setOrgName("��λ����");
    orgaddpayHeadExportDto.setAddStartPayMonth("������ʼ����");
    orgaddpayHeadExportDto.setAddpayMonth("������ֹ����");
    orgaddpayHeadExportDto.setNoteNum("�����");
    orgaddpayListExportDto.setEmpId("ְ�����");
    orgaddpayListExportDto.setEmpName("ְ������");
    orgaddpayListExportDto.setOrgShouldpay("��λӦ�ɽ��");
    orgaddpayListExportDto.setEmpShouldpay("ְ��Ӧ�ɽ��");
    orgaddpayListExportDto.setOrgAddpayMoney("��λ���ɽ��");
    orgaddpayListExportDto.setEmpAddpayMoney("ְ�����ɽ��");
    orgaddpayListExportDto.setEmpPayStatus("ְ��״̬");
    orgaddpayListExportDto.setStartPayMonth("������ʼ����");
    orgaddpayListExportDto.setEndPayMonth("������ֹ����");
    orgaddpayListExportDto.setSalaryBase("���ʻ���");
    orgaddpayListExportDto.setOrgRate("��λ����");
    orgaddpayListExportDto.setEmpRate("ְ������");
    orgaddpaylist.add(orgaddpayListExportDto);
    orgaddpayHeadlist.add(orgaddpayHeadExportDto);
    try {
      for (int i = 0; i < explist.size(); i++) {
        OrgaddpayHeadExportDTO orgaddpayHeadExportDto1 = new OrgaddpayHeadExportDTO();
        OrgaddpayListExportDTO orgaddpayListExportDto1 = new OrgaddpayListExportDTO();
        AddpayInfoDto addpayInfoDto = (AddpayInfoDto) explist.get(i);
        orgaddpayHeadExportDto1.setOrgid(BusiTools.convertTenNumber(addpayInfoDto.getOrgid()));
        orgaddpayHeadExportDto1.setOrgName(addpayInfoDto.getOrgName());
        orgaddpayHeadExportDto1.setAddStartPayMonth(addpayInfoDto.getAddStartPayMonth());
        orgaddpayHeadExportDto1.setAddpayMonth(addpayInfoDto.getAddpayMonth());
        orgaddpayHeadExportDto1.setNoteNum(addpayInfoDto.getNoteNum());
        orgaddpayListExportDto1.setEmpId(BusiTools.convertSixNumber(addpayInfoDto.getEmpId()));
        orgaddpayListExportDto1.setEmpName(addpayInfoDto.getEmpName());
        orgaddpayListExportDto1.setOrgShouldpay(addpayInfoDto.getOrgShouldpay());
        orgaddpayListExportDto1.setEmpShouldpay(addpayInfoDto.getEmpShouldpay());
        orgaddpayListExportDto1.setOrgAddpayMoney(addpayInfoDto.getOrgAddpayMoney());
        orgaddpayListExportDto1.setEmpAddpayMoney(addpayInfoDto.getEmpAddpayMoney());  
        orgaddpayListExportDto1.setEmpPayStatus(addpayInfoDto.getEmpPayStatus());
        orgaddpayListExportDto1.setStartPayMonth(addpayInfoDto.getAddStartPayMonth());
        orgaddpayListExportDto1.setEndPayMonth(addpayInfoDto.getAddpayMonth());
        orgaddpayListExportDto1.setSalaryBase(addpayInfoDto.getSalaryBase());
        orgaddpayListExportDto1.setOrgRate(addpayInfoDto.getOrgRate());
        orgaddpayListExportDto1.setEmpRate(addpayInfoDto.getEmpRate());
        orgaddpaylist.add(orgaddpayListExportDto1);
        orgaddpayHeadlist.add(orgaddpayHeadExportDto1);
      }
      Factory faxtory = new Factory();   
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("OrgaddpayHeadExport", orgaddpayHeadlist);
      addpayExportMap.put("OrgaddpayListExport", orgaddpaylist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,"org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
  
}
