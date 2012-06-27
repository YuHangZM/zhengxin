package org.xpup.hafmis.demo.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.demo.domain.entity.Demo;


/**
 * 
 * @author ����
 *2007-5-31
 */
public interface IDemoBS {
  //����������ѯdemo��¼
  public List findDemoListByCriterions(Pagination pagination) throws Exception,BusinessException;
  //����id��ѯdemo��¼ 
  public Demo findDemoById(Integer id)throws Exception;
  //��Ӽ�¼
  public Integer addDemo(Demo demo) throws BusinessException;
  //�޸ļ�¼
  public void updateDemo(Demo demo) throws BusinessException;
  //ɾ��������¼
  public void deleteDemo(Integer id) throws BusinessException;
  //��ѡɾ��
  public void deleteDemoMultibox(String[] rowArray) throws BusinessException;
  //ɾ����ǰҳ
  public void deletePageList(List list) throws BusinessException;
  //��ѯdemo���м�¼
  public List findDemoListAll(Pagination pagination) throws Exception,BusinessException;
  //�����޸�
  public List modifyDemoBatch(List demoExportList) throws Exception,BusinessException;
}
