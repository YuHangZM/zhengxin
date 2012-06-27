package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.dto.OverdueExpDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.dto.OverdueInfoDTO;

public class overdue_expDataSource implements ExportDateSourceInterface {
  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    try {
      Factory faxtory = new Factory();
      Map map = new HashMap();
      List list = new ArrayList();
      for (int i = 0; i < explist.size(); i++) {
        OverdueInfoDTO dto = (OverdueInfoDTO) explist.get(i);
        StringBuffer sb = new StringBuffer();
        OverdueExpDTO d = new OverdueExpDTO();
        d.setMobile(dto.getBorrowerMobile());
        sb.append(dto.getBorrowerMobile()).append(",����������ͬ").append(
            dto.getContractId()).append(dto.getBorrowerName()).append("������")
            .append(dto.getRepayMonth()).append("��Ƿ��").append(
                dto.getOverdueMonths()).append("���£������").append(
                dto.getShouldPayMoney().add(dto.getPunishInterest())).append(
                "Ԫ��");
        d.setContent(sb.toString());
        list.add(d);
      }
      map.put("OverdueExp", list);
      faxtory
          .setInfomation(xmlfile, xlsFile, map,
              "org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
