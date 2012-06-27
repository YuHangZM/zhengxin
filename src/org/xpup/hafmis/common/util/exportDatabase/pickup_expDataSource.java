package org.xpup.hafmis.common.util.exportDatabase;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickEmpDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickOrgDTO;
public class pickup_expDataSource implements ExportDateSourceInterface{
  List org = new ArrayList();
  List empList = new ArrayList();
  public void makeFile(File xmlfile, String xlsfile, String orgunitcode, List list) throws Exception {
    Emp emp = (Emp)list.get(0);
    PickOrgDTO o = new PickOrgDTO();
    //����ͷ���ֵ
    o.setOrgId("��λ���");
    o.setOrgName("��λ����");
    o.setOrgNoteNumber("Ʊ�ݱ��");
    o.setOrgunitname("��ȡ���ͣ�1��������ȡ��2��������ȡ����ȡԭ������1�У�1��������2���������»�����3��������һ���Ի����4:�ش󼲲���5��������6��������ȡ����������2�У�7�����ݣ�8��������9���������ڣ�10��ʧҵ�¸����ꣻ11���Ǳ��л��ڽ����ͬ��12����������");
    o.setOrgunitcode("����˵��");
    PickOrgDTO orgDto = new PickOrgDTO();
    //���ͷ�����Ϣ
    orgDto.setOrgId(BusiTools.convertSixNumber("0"+emp.getOrg().getId().toString()));
    orgDto.setOrgName(emp.getOrg().getOrgInfo().getName());
    //��ż���...
    org.add(o);//�ű���ͷ
    org.add(orgDto);//�ž������Ϣ
    /*********************************************/
    PickEmpDTO empDto = new PickEmpDTO();
    empDto.setEmpId("ְ�����");
    empDto.setCardKind("֤������");
    empDto.setCardNumber("֤�����");
    empDto.setEmpName("ְ������");
    empDto.setPickBalance("��ȡ���");
    empDto.setPickReason("��ȡԭ��");
    empDto.setPickType("��ȡ����");
    empDto.setMaxPickMon("�����ȡ���");
    empDto.setHouseNum("���պ�");
    empList.add(empDto);//�ѱ�����뼯��..
    Iterator it = list.iterator();
    while(it.hasNext()){//ѭ����ȡ�ӱ������ֵ
      PickEmpDTO dto = new PickEmpDTO();
      Emp e = (Emp)it.next();
      dto.setEmpId(BusiTools.convertSixNumber(e.getEmpId().toString()));
      dto.setCardKind(BusiTools.getBusiValue(e.getEmpInfo().getCardKind().intValue(), BusiConst.DOCUMENTSSTATE));
      dto.setCardNumber(e.getEmpInfo().getCardNum());
      dto.setEmpName(e.getEmpInfo().getName());
      if(e.getPayOldYear()==null){
        e.setPayOldYear(e.getOrgPay().add(e.getEmpPay()));
      }
      int mul=e.getMul();
      Double maxMoney = new Double(e.getPreBalance().add(e.getCurBalance()).doubleValue()-e.getPayOldYear().doubleValue()*mul);
        if(maxMoney.compareTo(new Double(0))<=0){
          maxMoney=new Double(0);
        }
       
      dto.setMaxPickMon(new BigDecimal(maxMoney.toString()).setScale(2,BigDecimal.ROUND_DOWN).toString());
      empList.add(dto);
    }
    
//    ArrayList exportList3 = new ArrayList();
//    PickHeadExpDTO pickHeadExp=new PickHeadExpDTO();
//    pickHeadExp.setOrgunitname("��ȡ���ͣ�1��������ȡ��2��������ȡ����ȡԭ������1�У�1��������2��������3������������2�У�4������Ͷ���ͬ��5��������6������Ǩ��");
//    pickHeadExp.setOrgunitcode("����˵��");
//    exportList3.add(pickHeadExp);
    
    Factory factory = new Factory();
    Map map = new HashMap();
    map.put("PickOrg", org);//��Map��key�����DTOǰ���һ��..ҲҪ��xml file name����ֵ��һ����
    map.put("PickEmp", empList);
//    map.put("PickHeadExp", exportList3);
    factory.setInfomation(xmlfile, xlsfile, map,"org.xpup.hafmis.syscollection.pickupmng.pickup.dto.");
  }
}
