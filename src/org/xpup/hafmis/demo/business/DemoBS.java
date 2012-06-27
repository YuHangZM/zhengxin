package org.xpup.hafmis.demo.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.dao.DemoDAO;
import org.xpup.hafmis.demo.dao.DemoDwDAO;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.dto.DemoImportDTO;

/**
 * 
 * @author ����
 *2007-5-31
 */
public class DemoBS implements IDemoBS{

  private DemoDAO demoDAO = null;
  private DemoDwDAO demoDwDAO = null;
  
  public void setDemoDwDAO(DemoDwDAO demoDwDAO) {
    this.demoDwDAO = demoDwDAO;
  }

  public void setDemoDAO(DemoDAO demoDAO) {
    this.demoDAO = demoDAO;
  }
  
  /**
   * ����������ѯdemo��¼�����룩
   * @param pagination
   * @return
   * @throws BusinessException 
   */    
  public List modifyDemoBatch(List demoExportList) throws Exception,BusinessException
  {
    List demoList=new ArrayList();
    try{
      for(int i=0;i<demoExportList.size();i++){
        DemoImportDTO demoImportDto=(DemoImportDTO)demoExportList.get(i);
        Demo demo=new Demo();//update��ʱ�����ע�͵�
//      Demo demo=demoDAO.queryById(new Integer(demoImportDto.getId()));
        UtilRule utilRule=new UtilRule();
        if(utilRule.moneyRule(demoImportDto.getSalary(), 2, 2)==false){
//          BeanUtils.copyProperties(demo, demoImportDto);
//          demoList.add(demo);
//          continue;
        }else if(BusiTools.getBusiKey(demoImportDto.getSex(),BusiConst.SEX)==999){
          BeanUtils.copyProperties(demo, demoImportDto);
          demoList.add(demo);
          continue;
        }
        demo.setId(new Integer(demoImportDto.getId()));//update��ʱ�����ע�͵�
        demo.setName(demoImportDto.getName());
        demo.setIdcard(demoImportDto.getIdcard());
        demo.setBirthday(demoImportDto.getBirthday());
        demo.setSalary(new BigDecimal(demoImportDto.getSalary()));
        demo.setSex(""+BusiTools.getBusiKey(demoImportDto.getSex(),BusiConst.SEX));
        demoDAO.insert(demo);//update��ʱ�����ע�͵�
      }
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("�޸�ʧ��!");
    }
    return demoList;
  }
  /**
   * ����������ѯdemo��¼
   * @param pagination
   * @return
   * @throws BusinessException 
   */  
  public List findDemoListByCriterions(Pagination pagination) throws Exception,BusinessException{
    List list=new ArrayList();
    
    String id=(String) pagination.getQueryCriterions().get("demo.id");
    String name=(String) pagination.getQueryCriterions().get("demo.name");
    
    String orderBy=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    int page = pagination.getPage(); 
    
    list=demoDAO.queryDemoListByCriterions(id, name, orderBy, order, start, pageSize,page);
    
    //ת����Ů
    List returnlist=new ArrayList();   
    if(list!=null){
    for(int i=0;i<list.size();i++){
        Demo demo=(Demo)list.get(i);  
        demo.setSex(BusiTools.getBusiValue(Integer.parseInt(demo.getSex()), BusiConst.SEX));
        returnlist.add(demo);
      }
    }
    int count = demoDAO.queryDemoCountByCriterions(id, name);
    pagination.setNrOfElements(count);
//    if(list.size()==0){
//      throw new BusinessException("�����ڼ�¼");
//    }
    return returnlist;
    
   }
  
  /**
   * ����������ѯdemo��¼�������м�¼(����ʹ��)
   * @param pagination
   * @return
   * @throws BusinessException 
   */  
  public List findDemoListAll(Pagination pagination) throws Exception,BusinessException{
    List list=new ArrayList();
    String id=(String) pagination.getQueryCriterions().get("demo.id");
    String name=(String) pagination.getQueryCriterions().get("demo.name");
    String orderBy=(String) pagination.getOrderBy();;
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    list=demoDAO.queryDemoList(id, name, orderBy, order, start, pageSize);
    //ת����Ů
    List returnlist=new ArrayList();   
    if(list!=null){
    for(int i=0;i<list.size();i++){
      Demo demo=(Demo)list.get(i);  
      demo.setSex(BusiTools.getBusiValue(Integer.parseInt(demo.getSex()), BusiConst.SEX));
      returnlist.add(demo);
    }
    }
    int count = demoDAO.queryDemoCountByCriterions(id, name);
    pagination.setNrOfElements(count);
//    if(list.size()==0){
//      throw new BusinessException("�����ڼ�¼");
//    }
    return returnlist;
    
   }
  
 /**
  * ����id��ѯdemo��¼ 
  */
  public Demo findDemoById(Integer id){
    return demoDAO.queryById(id);
  }
  
  /**
   * @throws BusinessException 
   * ���
   */
  public Integer addDemo(Demo demo) throws BusinessException{
    Integer id=null;
    try{
    demoDAO.insert(demo);
    id=demo.getId();
    }catch(Exception e){
      throw new BusinessException("���ʧ��!");
      
    }
    return id;
  }
  /**
   * �޸�
   * @param demo
   * @throws BusinessException
   */
  public void updateDemo(Demo demo) throws BusinessException{
    try{
      Demo modifyDemo = findDemoById(demo.getId());
      modifyDemo.setBalance(demo.getBalance());
      modifyDemo.setBirthday(demo.getBirthday());
      modifyDemo.setIdcard(demo.getIdcard());
      modifyDemo.setName(demo.getName());
      modifyDemo.setSalary(demo.getSalary());
      modifyDemo.setSex(demo.getSex());
      modifyDemo.setPhotoUrl(demo.getPhotoUrl());
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("�޸�ʧ��!");
    }
    
  }
  /**
   * ��ѡɾ��
   */
  public void deleteDemo(Integer id) throws BusinessException{
    try{
      Demo demo=demoDAO.queryById(id);
      demoDAO.delete(demo);
    }catch(Exception e){
      throw new BusinessException("ɾ��ʧ��!");
    }
  }
  /**
   * ��ѡɾ��
   */
  public void deleteDemoMultibox(String[] rowArray) throws BusinessException{
    try{
      for(int i=0;i<rowArray.length;i++){
        Integer id=new Integer(rowArray[i]);
        Demo demo=demoDAO.queryById(id);
        demoDAO.delete(demo);
      }
     
    }catch(Exception e){
      throw new BusinessException("ɾ��ʧ��!");
    }
  }
  
  /**
   * ɾ����ǰҳ
   */
  public void deletePageList(List list) throws BusinessException{
    try{
//      List deleteList=new ArrayList();
//      for(int i=0;i<list.size();i++){
//        DemoDTO demoDTO=(DemoDTO)list.get(i);
//        Demo demo=new Demo();
//        BeanUtils.copyProperties(demo, demoDTO);   
//        deleteList.add(demo);
//      }
      demoDAO.deleteList(list);
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("ɾ��ʧ��!");
    }
  }
//  public static void main(String[] args) {
//    try{
//      System.out.println("sss");
//      System.out.println("-->"+test.getDay("20070730"));
//    }catch(Exception s){
//      System.out.println("date appear error:"+s.getMessage());
//      s.printStackTrace();
//    }
//  }
}

//�����Ϣ��
//class test{
//  public static int getDay(String moneyDate){
//    java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
//    String year = moneyDate.substring(0,4);
//    String month = moneyDate.substring(4,6);
//    String day = moneyDate.substring(6,8);
//    Calendar convert = Calendar.getInstance();
//    Calendar result = Calendar.getInstance();
//    convert.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
//    //����Ϊ�����6��30��
//    result.set(Integer.parseInt(date.toString().substring(0,4)), 05,30);
//    if(convert.getTime().getTime()> result.getTime().getTime()){//����Ϊ�����
//      result.set(Integer.parseInt(date.toString().substring(0,4))+1, 05,30);
//    }
//    Timestamp one = new Timestamp(result.getTime().getTime());
//    Timestamp two = new Timestamp(convert.getTime().getTime());
//    int number = BusiTools.minusDate(one.toString().substring(0,10),two.toString().substring(0,10));
//    return number;
//  }
//  
//}
