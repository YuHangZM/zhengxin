package org.xpup.hafmis.common.util.exportDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.SystemException;
import org.xpup.common.util.exp.Factory;
import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.dto.CollectionstatisticsExportDTO;

/** 
 * ���´�
  �鼯����
  ��λ���
  ��λ����
  ��λ����
  ���ܲ���
  ���ڵ���
  ���¹鼯
  �����������
  ���µ�λ����
  ���¸��˲���
  ���µ�λ����
  ���µ��ɴ�
  ���¹鼯
  ����
 * @author yqf TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 *
 */
public class collectionstatistics_expDataSource implements ExportDateSourceInterface{


  public void makeFile(File xmlfile, String xlsFile, String para, List explist) throws Exception {
    // TODO Auto-generated method stub
    ArrayList collectlist = new ArrayList();
    OrgHAFAccountFlow orgHAFAccountFlow = null;
    CollectionstatisticsExportDTO collectionstatisticsExportDTO = new  CollectionstatisticsExportDTO();
    collectionstatisticsExportDTO.setOfficeCode("���´�");
    collectionstatisticsExportDTO.setCollectionBank("�鼯����");
    collectionstatisticsExportDTO.setOrgId("��λ���");
    collectionstatisticsExportDTO.setOrgName("��λ����");
    collectionstatisticsExportDTO.setOrgCharacter("��λ����");
    collectionstatisticsExportDTO.setDeptInCharge("���ܲ���");
    collectionstatisticsExportDTO.setRegion("���ڵ���");
    collectionstatisticsExportDTO.setLastMonthCollect("���¹鼯");
    collectionstatisticsExportDTO.setMonthPay("�������");
    collectionstatisticsExportDTO.setOrgAddPay("��λ����");
    collectionstatisticsExportDTO.setPersonAddPay("���˲���");
    collectionstatisticsExportDTO.setOrgOverPay("��λ����");
    collectionstatisticsExportDTO.setChgPay("���ɴ�");
    collectionstatisticsExportDTO.setThisMonthCollect("���¹鼯");
    collectionstatisticsExportDTO.setRate("����");
    collectlist.add(collectionstatisticsExportDTO);
    
    try{
      for(int i=0;i<explist.size();i++){
        CollectionstatisticsExportDTO collectionstatisticsExportDTO1 = new CollectionstatisticsExportDTO();
         orgHAFAccountFlow  = (OrgHAFAccountFlow)explist.get(i);
        if(orgHAFAccountFlow.getReserveaB() != null){
          collectionstatisticsExportDTO1.setOfficeCode(orgHAFAccountFlow.getReserveaB());
        }else{
        collectionstatisticsExportDTO1.setOfficeCode(null);
        }
        if(orgHAFAccountFlow.getReserveaC() != null){
          collectionstatisticsExportDTO1.setCollectionBank(orgHAFAccountFlow.getReserveaC());
        }else{
        collectionstatisticsExportDTO1.setCollectionBank(null);
        }
        if(orgHAFAccountFlow.getOrg().getId().toString() != null){
          collectionstatisticsExportDTO1.setOrgId(orgHAFAccountFlow.getOrg().getId().toString());
        }else{
        collectionstatisticsExportDTO1.setOrgId(null);
        }
        if(orgHAFAccountFlow.getOrg().getOrgInfo().getName() != null){
          collectionstatisticsExportDTO1.setOrgName(orgHAFAccountFlow.getOrg().getOrgInfo().getName());
        }else{
        collectionstatisticsExportDTO1.setOrgName(null);
        }
        if(orgHAFAccountFlow.getOrg().getOrgInfo().getCharacter() != null){
          collectionstatisticsExportDTO1.setOrgCharacter(orgHAFAccountFlow.getOrg().getOrgInfo().getCharacter());
        }else{
        collectionstatisticsExportDTO1.setOrgCharacter(null);
        }
        if(orgHAFAccountFlow.getOrg().getOrgInfo().getDeptInCharge() != null){
          collectionstatisticsExportDTO1.setDeptInCharge(orgHAFAccountFlow.getOrg().getOrgInfo().getDeptInCharge());
        }else{
        collectionstatisticsExportDTO1.setDeptInCharge(null);
        }
        if(orgHAFAccountFlow.getOrg().getOrgInfo().getRegion() != null){
          collectionstatisticsExportDTO1.setRegion(orgHAFAccountFlow.getOrg().getOrgInfo().getRegion());
        }else{
          collectionstatisticsExportDTO1.setRegion(null);
        }
        if(orgHAFAccountFlow.getLastMonthCollection()!= null){
          collectionstatisticsExportDTO1.setLastMonthCollect(orgHAFAccountFlow.getLastMonthCollection().toString());
        }else{
        collectionstatisticsExportDTO1.setLastMonthCollect(null);
        }
        if(orgHAFAccountFlow.getMonthPay()!= null){
          collectionstatisticsExportDTO1.setMonthPay(orgHAFAccountFlow.getMonthPay().toString());
        }else{
        collectionstatisticsExportDTO1.setMonthPay(null);
        }
        if(orgHAFAccountFlow.getOrgAddPay() != null){
          collectionstatisticsExportDTO1.setOrgAddPay(orgHAFAccountFlow.getOrgAddPay().toString());
        }else{
        collectionstatisticsExportDTO1.setOrgAddPay(null);
        }
        if(orgHAFAccountFlow.getPeronaddPay() != null){
          collectionstatisticsExportDTO1.setPersonAddPay(orgHAFAccountFlow.getPeronaddPay().toString());
        }else{
        collectionstatisticsExportDTO1.setPersonAddPay(null);
        }
        if(orgHAFAccountFlow.getOrgoverPay()!= null){
          collectionstatisticsExportDTO1.setOrgOverPay(orgHAFAccountFlow.getOrgoverPay().toString());
        }else{
        collectionstatisticsExportDTO1.setOrgOverPay(null);
        }
        if(orgHAFAccountFlow.getChgPay() != null){
          collectionstatisticsExportDTO1.setChgPay(orgHAFAccountFlow.getChgPay().toString());
        }else{
        collectionstatisticsExportDTO1.setChgPay(null);
        }
        if(orgHAFAccountFlow.getThisMonthCollection() != null){
          collectionstatisticsExportDTO1.setThisMonthCollect(orgHAFAccountFlow.getThisMonthCollection().toString());
        }else{
        collectionstatisticsExportDTO1.setThisMonthCollect(null);
        }
        if(orgHAFAccountFlow.getRate() != null){
          collectionstatisticsExportDTO1.setRate(new Double(orgHAFAccountFlow.getRate()).toString());
        }else{
        collectionstatisticsExportDTO1.setRate(null);
        }
        collectlist.add(collectionstatisticsExportDTO1);
      }
      Factory faxtory = new Factory();
      Map collectionsExportMap = new HashMap();
      collectionsExportMap.put("CollectionstatisticsExport", collectlist);
      faxtory.setInfomation(xmlfile, xlsFile, collectionsExportMap, "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.dto.");
    }catch(SystemException e){
      e.printStackTrace();
    }
  }

} 
