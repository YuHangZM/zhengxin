package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.demo.dao.DemoDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInTailDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.tranmng.tranin.dto.TraninExportDTO;
import org.xpup.hafmis.syscollection.tranmng.tranin.dto.TraninExportExplainDTO;
import org.xpup.hafmis.syscollection.tranmng.tranin.dto.TraninExportHeadDTO;

/**
 * @author ly TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class tranin_expDataSource implements ExportDateSourceInterface {
  protected OrgDAO orgDAO;

  public void setEmpPaymentAgreementDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setOrgPaymentAgreementDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    
    ArrayList traninExportExplainList = new ArrayList();
    TraninExportExplainDTO traninExportExplainDTO = new TraninExportExplainDTO();
    traninExportExplainDTO.setExplain("֤�����ͣ�0���֤  1���ڲ�  2����  3����֤  4ʿ��֤  5�۰ľ��������ڵ�ͨ��֤  6̨��ͬ�������ڵ�ͨ��֤" 
        +"  7��ʱ���֤  8����˾���֤  9����֤");
    traninExportExplainList.add(traninExportExplainDTO);
    
    ArrayList traninExportList1 = new ArrayList();
    TraninExportHeadDTO traninExportHeadDTO = new TraninExportHeadDTO();
    traninExportHeadDTO.setInOrgId("ת�뵥λ���");
    traninExportHeadDTO.setInOrgName("ת�뵥λ����");
    traninExportHeadDTO.setNoteNum("Ʊ�ݱ��");

    traninExportList1.add(traninExportHeadDTO);
    ArrayList traninExportList2 = new ArrayList();
    TraninExportDTO traninExportDTO = new TraninExportDTO();
    Map map = (Map) explist.get(0);

    traninExportDTO.setName("ְ������");
    traninExportDTO.setCardKind("֤������");
    traninExportDTO.setCardNum("֤������");
    traninExportDTO.setBirthday("��������");
    traninExportDTO.setSex("�Ա�(0δ֪1��2Ů)");
    traninExportDTO.setSalaryBase("���ʻ���");
    traninExportDTO.setPreBalance("�������");
    traninExportDTO.setCurBalance("�������");
    traninExportDTO.setMonthIncome("ְ��������");
    traninExportDTO.setCurInterest("��Ϣ");
    traninExportDTO.setTel("��ͥ�绰");
    traninExportDTO.setMobileTel("�ƶ��绰");
    if (((String) map.get("payMode")).equals("2")) {
      traninExportDTO.setOrgPay("��λ�ɶ�");
      traninExportDTO.setEmpPay("ְ���ɶ�");
    }
    traninExportList2.add(traninExportDTO);
    try {
      for (int i = 0; i < explist.size(); i++) {
        Map map1 = (Map) explist.get(i);
        TraninExportHeadDTO traninExportHeadDTO1 = new TraninExportHeadDTO();
        traninExportHeadDTO1.setInOrgId(BusiTools.convertSixNumber((String) map1.get("inOrgId")));
        traninExportHeadDTO1.setInOrgName((String) map1.get("inOrgName"));
        traninExportHeadDTO1.setNoteNum("");
        // ������Ʊ�ݱ��
        // traninExportHeadDTO1.setNoteNum((String)map.get("noteNum"));
        traninExportList1.add(traninExportHeadDTO1);
      }
      TraninExportDTO traninExportDTO1 = new TraninExportDTO();
      traninExportList2.add(traninExportDTO1);
      Factory faxtory = new Factory();
      Map demoExportMap = new HashMap();
      demoExportMap.put("TraninExportExplain", traninExportExplainList);
      demoExportMap.put("TraninExportHead", traninExportList1);
      demoExportMap.put("TraninExport", traninExportList2);
      faxtory.setInfomation(xmlfile, xlsFile, demoExportMap,
          "org.xpup.hafmis.syscollection.tranmng.tranin.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }

}
