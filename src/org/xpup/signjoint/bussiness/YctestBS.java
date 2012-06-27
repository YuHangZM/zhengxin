package org.xpup.signjoint.bussiness;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;

import org.xpup.signjoint.bsinterface.ISignjointBS;
import org.xpup.signjoint.dao.YctestDAO;
import org.xpup.signjoint.entity.Yctest;


public class YctestBS implements ISignjointBS{

  private YctestDAO yctestdao = null;
  /**
   * ����id��ѯdemo��¼ 
   */
  public Yctest findDemoById(Integer id){
    return yctestdao.queryById(id);
  }
  public void setYctestdao(YctestDAO yctestdao) {
    this.yctestdao = yctestdao;
  }
}


