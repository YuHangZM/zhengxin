package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;

import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccExpHeadDTO;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccExpNoteDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;

public class bankcheckacc_expDataSource implements ExportDateSourceInterface {
  public void makeFile(File xmlfile, String xlsFile, String para, List explist)
      throws Exception {
    ArrayList bankcheckaccHeadlist = new ArrayList();
    BankCheckAccExpHeadDTO bankCheckAccExpHeadDTO = new BankCheckAccExpHeadDTO();
    
    bankCheckAccExpHeadDTO.setSettDate("��������");
    bankCheckAccExpHeadDTO.setSubjectCode("��Ŀ");
    bankCheckAccExpHeadDTO.setDebit("�跽���");
    bankCheckAccExpHeadDTO.setCredit("�������");
    bankCheckAccExpHeadDTO.setSettType("���㷽ʽ");
    bankCheckAccExpHeadDTO.setSettNum("�����");
    bankCheckAccExpHeadDTO.setSummary("ժҪ");
    bankCheckAccExpHeadDTO.setDopsn("������");

    
    bankcheckaccHeadlist.add(bankCheckAccExpHeadDTO);
    List settTypeList=new ArrayList();
    List summaryList=new ArrayList();
    settTypeList=(List)explist.get(0);
    summaryList=(List)explist.get(1);
    String note="";
    String summaryNote="";
    for(int i=0;i<settTypeList.size();i++){
      BookParameterDTO bookParameterDTO = (BookParameterDTO) settTypeList.get(i);
      note=note+bookParameterDTO.getBookParameterId()+bookParameterDTO.getBookParameterName()+" ";
    }
    for(int i=0;i<summaryList.size();i++){
      BookParameterDTO bookParameterDTO = (BookParameterDTO) summaryList.get(i);
      summaryNote=summaryNote+bookParameterDTO.getBookParameterId()+bookParameterDTO.getBookParameterName()+" ";
    }
    List notelist=new ArrayList();
    BankCheckAccExpNoteDTO noteDto = new BankCheckAccExpNoteDTO();
    noteDto.setNote("���㷽ʽ˵����"+note+";   "+"ժҪ˵����"+summaryNote);
    notelist.add(noteDto);

    try {

      Factory faxtory = new Factory();
      Map addpayExportMap = new HashMap();
      addpayExportMap.put("BankCheckAccExpNote", notelist);
      addpayExportMap.put("BankCheckAccExpHead", bankcheckaccHeadlist);
      faxtory.setInfomation(xmlfile, xlsFile, addpayExportMap,
          "org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.");
    } catch (SystemException e) {
      e.printStackTrace();
    }
  }
}
