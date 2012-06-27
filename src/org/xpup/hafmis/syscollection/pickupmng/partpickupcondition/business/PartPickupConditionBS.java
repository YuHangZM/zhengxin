package org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.business;

import org.xpup.hafmis.syscollection.common.dao.PartPickupConditionDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.PartPickupCondition;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.bsinterface.IPartPickupConditionBS;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.dto.PartPickupConditionDTO;


public class PartPickupConditionBS implements IPartPickupConditionBS{
  private PartPickupConditionDAO partPickupConditionDAO=null;
  /**
   * ������ȡǰ��¼��
   * @author ���ƽ
   * 2007-12-6
   * ��aa401������
   */
  public PartPickupConditionDTO findPartPickupConditionInfo() throws Exception{
    PartPickupConditionDTO partPickupConditionDTO=null;
    try{
      partPickupConditionDTO=partPickupConditionDAO.queryPartPickupConditionInfo();
    }catch(Exception e){
      e.printStackTrace();
    }
    return partPickupConditionDTO;
  }
  /**
   * ������ȡǰ��¼��
   * ȷ����ť
   * @author ���ƽ
   * 2007-12-6
   */
  public void savePartPickupConditionInfo(PartPickupConditionDTO partPickupConditionDTO) throws Exception{
    try{
      partPickupConditionDAO.deletePartPickupCondition();
      PartPickupCondition partPickupCondition=new PartPickupCondition();
      partPickupCondition.setPickMoneyMax(partPickupConditionDTO.getPickMoneyMax());
      partPickupCondition.setPickTimeMax(partPickupConditionDTO.getPickTimeMax());
      partPickupCondition.setLeavingsBalance(partPickupConditionDTO.getLeavingsBalance());
      partPickupCondition.setMultiple(partPickupConditionDTO.getMultiple());
      partPickupConditionDAO.insert(partPickupCondition);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  public void setPartPickupConditionDAO(
      PartPickupConditionDAO partPickupConditionDAO) {
    this.partPickupConditionDAO = partPickupConditionDAO;
  }
}
