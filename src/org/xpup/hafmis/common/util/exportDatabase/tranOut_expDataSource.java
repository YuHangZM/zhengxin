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
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutHeadExportDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutInfoDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutListExportDTO;

/*ת����λ��š�ת����λ���ơ�ת�뵥λ��š�ת�뵥λ���ơ�Ʊ�ݱ�š�ְ����š�ְ��������֤�����͡�֤�����롢�Ƿ��Ϣ��*/
/**
 * @author ly TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class tranOut_expDataSource implements ExportDateSourceInterface {

  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {

    ArrayList tranoutHeadlist = new ArrayList();
    ArrayList tranoutlist = new ArrayList();
    TranoutHeadExportDTO tranoutHeadExportDTO = new TranoutHeadExportDTO();
    TranoutListExportDTO tranoutListExportDTO = new TranoutListExportDTO();

    tranoutHeadExportDTO.setOrgOutid("ת����λ���");
    tranoutHeadExportDTO.setOrgOutName("ת����λ����");
    tranoutHeadExportDTO.setOrgInid("ת�뵥λ���");
    tranoutHeadExportDTO.setOrgInName("ת�뵥λ����");
    tranoutHeadExportDTO.setNoteNum("Ʊ�ݱ��");
    tranoutHeadExportDTO.setExpalanation("(�Ƿ��Ϣ˵����1����Ϣ 2������Ϣ)");
    

    tranoutListExportDTO.setEmpId("ְ�����");
    tranoutListExportDTO.setEmpName("ְ������");
    tranoutListExportDTO.setCard_king("֤������");
    tranoutListExportDTO.setCard_num("֤������");
    tranoutListExportDTO.setIssettinrest("�Ƿ��Ϣ");
    tranoutListExportDTO.setTranReason("ת��ԭ��");
    tranoutListExportDTO.setTranin_empid("ת��ְ�����");
    tranoutlist.add(tranoutListExportDTO);
    tranoutHeadlist.add(tranoutHeadExportDTO);

    try {

      TranoutHeadExportDTO tranoutHeadExportDTO1 = new TranoutHeadExportDTO();
      TranoutInfoDTO tranoutInfoDTO1 = (TranoutInfoDTO) explist.get(0);
      
      String orgOutid=tranoutInfoDTO1.getOrgOutid();   
      tranoutHeadExportDTO1.setOrgOutid(BusiTools.convertSixNumber(orgOutid));
      tranoutHeadExportDTO1.setOrgOutName(tranoutInfoDTO1.getOrgOutName()); 
      String orgInid=tranoutInfoDTO1.getOrgInid();   
      if(orgInid==null||orgInid.equals("")){
        tranoutHeadExportDTO1.setOrgInid(tranoutInfoDTO1.getOrgInid());   
      }else{
        tranoutHeadExportDTO1.setOrgInid(BusiTools.convertSixNumber(orgInid));    
      }

      tranoutHeadExportDTO1.setOrgInName(tranoutInfoDTO1.getOrgInName());
      tranoutHeadExportDTO1.setNoteNum(tranoutInfoDTO1.getNoteNum());
      tranoutHeadlist.add(tranoutHeadExportDTO1);

      for (int i = 0; i < explist.size(); i++) {

        TranoutListExportDTO tranoutListExportDTO1 = new TranoutListExportDTO();
        TranoutInfoDTO tranoutInfoDTO = (TranoutInfoDTO) explist.get(i);

        String empId=tranoutInfoDTO.getEmpId();        
        tranoutListExportDTO1.setEmpId(BusiTools.convertSixNumber(empId));
        tranoutListExportDTO1.setEmpName(tranoutInfoDTO.getEmpName());
        tranoutListExportDTO1.setCard_king(tranoutInfoDTO.getCard_king());
        tranoutListExportDTO1.setCard_num(tranoutInfoDTO.getCard_num());
        tranoutListExportDTO1.setIssettinrest(tranoutInfoDTO.getIssettinrest());
        tranoutListExportDTO1.setTranReason(tranoutInfoDTO.getTranReason());

        tranoutlist.add(tranoutListExportDTO1);

      }
      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("TranoutHeadExport", tranoutHeadlist);
      addpayExportMap.put("TranoutListExport", tranoutlist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,
          "org.xpup.hafmis.syscollection.tranmng.tranout.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }

}
