package org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.EvaluateAFDTO;

public interface IEvaluateBS {
    /**
     * name ����
     * ������˾ά��-��ʾ�б�
     */
    public List findEvaluateList(Pagination pagination) throws Exception, BusinessException;
  /**
   * name ����
   * ������˾ά�� �����¼�¼  (**���и�**)
   */
    public void insertEvaluateList(EvaluateAFDTO evaluateAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
    /**
     * name ����
     * ������˾ά�� ͨ��id��ѯ���������޸�
     */
    public EvaluateAFDTO findEvaluateID(Integer id)throws Exception,BusinessException;
    /**
     * name ����
     * ������˾ά��,�޸�����  (**���и�**)
     */
    public void updateEvaluate(EvaluateAFDTO evaluateAFDTO,SecurityInfo securityInfo)throws Exception,BusinessException;
    /**
     * name ����
     * ����idɾ��PL007����
     */
    public String deleteEvaluate(Integer id,SecurityInfo securityInfo)throws Exception,BusinessException;
    /**
     * name ����
     *����id �ж��Ƿ��м�¼
     *true �д˼�¼
     *false �޴˼�¼
     */
    public boolean is_Evaluate_YM(Integer id)throws Exception,BusinessException;


}
