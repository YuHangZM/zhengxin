package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.sysloan.dataready.develop.dto.BuildDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.BuildExportDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.BuildExportHeadDTO;

/**
 * @author ly TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class build_expDataSource implements ExportDateSourceInterface {
  
  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList bulidExportList1 = new ArrayList();
    BuildExportHeadDTO buildExportHeadDTO = new BuildExportHeadDTO();
    buildExportHeadDTO.setDeveloperId("�����̺�");
    buildExportHeadDTO.setDeveloperName("����������");
    buildExportHeadDTO.setFloorId("¥�̺�");
    buildExportHeadDTO.setFloorName("¥������");

    bulidExportList1.add(buildExportHeadDTO);
    
    ArrayList bulidExportList2 = new ArrayList();
    BuildExportDTO buildExportDTO = new BuildExportDTO();
   // buildExportDTO.setBuildId("¥�����");
   // buildExportDTO.setFloorId("¥�̱��");
    
    buildExportDTO.setBuildNum("¥����");
    buildExportDTO.setBuildAdd("¥����ַ");
    buildExportDTO.setBuild_s("�������");
    buildExportDTO.setFundStatus("�Ƿ񲦿�(1��,0��)");
    buildExportDTO.setReserved("��ע");
    
    bulidExportList2.add(buildExportDTO);
    try {
      BuildExportHeadDTO buildExportHeadDTO1 = new BuildExportHeadDTO();
      buildExportHeadDTO1.setDeveloperId((String)explist.get(0));
      buildExportHeadDTO1.setDeveloperName((String)explist.get(1));
      buildExportHeadDTO1.setFloorId((String)explist.get(2));
      buildExportHeadDTO1.setFloorName((String)explist.get(3));
      bulidExportList1.add(buildExportHeadDTO1);
      
      List list = (List)explist.get(4);
      for(int i=0;i<list.size();i++){
        BuildExportDTO buildExportDTO1 = new BuildExportDTO();
        BuildDTO buildDTO = (BuildDTO)list.get(i);
       // buildExportDTO1.setBuildId(buildDTO.getBuildId());
      //  buildExportDTO1.setFloorId(buildDTO.getFloorId());
        buildExportDTO1.setBuildNum(buildDTO.getBuildNum());
        buildExportDTO1.setBuildAdd(buildDTO.getBuildAdd());
       
        buildExportDTO1.setBuild_s(buildDTO.getBuild_s().toString());
        buildExportDTO1.setFundStatus(buildDTO.getFundStatus());
        buildExportDTO1.setReserved(buildDTO.getReserved());
        bulidExportList2.add(buildExportDTO1);
      }
      Factory faxtory = new Factory();
      Map demoExportMap = new HashMap();
      demoExportMap.put("BuildExportHead", bulidExportList1);
      demoExportMap.put("BuildExport", bulidExportList2);
      faxtory.setInfomation(xmlfile, xlsFile, demoExportMap,
          "org.xpup.hafmis.sysloan.dataready.develop.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }

}
