package org.xpup.hafmis.sysloan.loanapply.specialapply.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.sysloan.common.domain.entity.DevelopProject;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;
import org.xpup.hafmis.sysloan.common.domain.entity.SpecialBorrower;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyDTO;

public interface ISpecialapplyBS {
  // ����id��ѯSpecialBorrower��¼
  public SpecialBorrower findSpecialBorrowerById(Integer id) throws Exception;

  // ����id��ѯEmp��¼
  public Emp findEmpById(String id, String orgId) throws Exception;

  // ����ְ����ź͵�λ��� ��ѯPL112���� �����״ֵ̬
  public SpecialBorrower findSpecialBorrowerStutasByEmpId(String id,
      String orgId) throws Exception;

  // ְ�������������֤������ ��ѯPL112���� �����״ֵ̬
  public SpecialBorrower findSpecialBorrowerStutasByNameAndNum(String name,
      String num) throws Exception;

  // ְ����� ��λ��� ��ѯPL112���� ROWNUM=1 ����˵�״ֵ̬
  public SpecialBorrower findSpecialBorrowerStutasByEmpIdTop1(String id,
      String orgId) throws Exception;

  // //ְ�������������֤������ ��ѯPL112���� ROWNUM=1 ����˵�״ֵ̬
  public SpecialBorrower findSpecialBorrowerStutasByNameAndNumTop1(String name,
      String num) throws Exception;

  // ����id��ѯSpecialapplyDTO����ļ�¼
  public SpecialapplyDTO findSpecialapplyInfoById(String id, String orgId)
      throws Exception;

  // ��������� �� ֤������ �Ƿ�һ��(PL112��) //����������Ϣ��PL112�Ƿ���ڽ����������֤���������¼��ļ�¼
  public Boolean isCheckNameANDCardNum_SpecialBorrower(String borrowerName,
      String cardNum) throws Exception;

  // ����EMPID��ѯ�Ƿ��м�¼ (pl112��)
  public Boolean isCheckSpecialBorrowerByEmpId(String empId) throws Exception;

  // ���ز�ѯ���(List) ����������Ϣ
  public List findSpecialapplyList(Pagination pagination, String operator)
      throws Exception, BusinessException;

  // ��ѯpl112�������ļ�¼����
  public int findSpecialapplyCount(String privilegeBorrowerId,
      String borrwerName, String cardNum, String operator) throws Exception,
      BusinessException;

  // ��ѯpl112���ܵļ�¼����
  public int findSpecialapplyAllCount() throws Exception, BusinessException;

  // ɾ��������¼
  public void deleteSpecialapply(Integer id, SecurityInfo securityInfo)
      throws BusinessException;

  public void deleteSpecialapply(String borrowerName, String cardNum,
      SecurityInfo securityInfo) throws BusinessException;

  // �������ݼ�¼
  public void updateSpecialapply(SpecialapplyDTO specialapplyDTO)
      throws BusinessException;

  // ���� ����������Ϣ��PL112�� ���룺������־PL021
  public void insertSpecialApplyInfo(SpecialapplyDTO specialapplyDTO,
      SecurityInfo securityInfo) throws BusinessException;

  public Developer queryDeveloperInfo(String headId) throws Exception,
      BusinessException;

  public DevelopProject queryFloorInfo(String floorId) throws Exception,
      BusinessException;
  public List queryFloorListByHeadid(String headId) throws Exception;
  
  public List queryFloorNumList(String headId, String floorName) throws Exception;
}
