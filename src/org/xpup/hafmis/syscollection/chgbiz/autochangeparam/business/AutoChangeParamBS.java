package org.xpup.hafmis.syscollection.chgbiz.autochangeparam.business;

import java.io.Serializable;
import java.util.Date;

import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.autochangeparam.bsinterface.IAutoChangeParamBS;
import org.xpup.hafmis.syscollection.chgbiz.autochangeparam.dto.AutoChangeParamDTO;
import org.xpup.hafmis.syscollection.common.dao.OfficeParaDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.OfficePara;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

/**
 * Copy Right Information : �Զ�����������õ�BS Goldsoft Project : AutoChangeParamSaveAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2008.3.17
 */
public class AutoChangeParamBS implements IAutoChangeParamBS {

  private OfficeParaDAO officeParaDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  public void setOfficeParaDAO(OfficeParaDAO officeParaDAO) {
    this.officeParaDAO = officeParaDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void saveAutoChangeParam(AutoChangeParamDTO autoChangeParamDTO,
      SecurityInfo securityInfo) throws Exception{
    // ɾ��aa412�У�������ŵ���10��
    officeParaDAO.deleteOfficeParaByParamNum("10");
    // ����aa412
    OfficePara officePara = null;
    officePara = new OfficePara();
    officePara.setParamNum("10");
    officePara.setOffice(" ");
    officePara.setParamDescrip("�Ƿ������Զ������0.�����ã� 1.���ã�");
    officePara.setParamValue(autoChangeParamDTO.getIsAutoChange());

    Serializable id = officeParaDAO.insert(officePara);

    HafOperateLog hafOperateLog = new HafOperateLog();
    // ������־BA003
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO);
    hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
    hafOperateLog.setOpBizId(new Integer(id.toString()));
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOrgId(new Integer(id.toString()));
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLogDAO.insert(hafOperateLog);
  }
  
  public String findAutoChangeParam() throws Exception{
    String param = officeParaDAO.queryAutoChangeParamByParamNum("10");
    return param;
  }
}
