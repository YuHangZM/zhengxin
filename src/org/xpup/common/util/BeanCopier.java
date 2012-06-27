package org.xpup.common.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.Validate;
import org.xpup.common.exception.SystemException;

/**
 * Bean ���ԵĿ������ߣ��ṩ��ָ���� Bean ���ԵĿ�����
 * 
 * @author $Author$
 * @version $Revision$,$Date$
 */
public class BeanCopier {
  public static void copyCertainProperty(Object destBean, String descPropName,
      Object sourceBean, String sourcePorpName) {
    Validate.notNull(destBean, "Υ��ǰ��Լ�������� destBean ����Ϊ null");
    Validate.notNull(descPropName, "Υ��ǰ��Լ�������� descPropName ����Ϊ null");
    Validate.notNull(sourceBean, "Υ��ǰ��Լ�������� sourceBean ����Ϊ null");
    Validate.notNull(sourcePorpName, "Υ��ǰ��Լ�������� sourcePorpName ����Ϊ null");
    Validate.notEmpty(descPropName, "Υ��ǰ��Լ�������� descPropName ����Ϊ���ַ���");
    Validate.notEmpty(sourcePorpName, "Υ��ǰ��Լ�������� sourcePorpName ����Ϊ���ַ���");
    try {
      Object o = PropertyUtils.getProperty(sourceBean, sourcePorpName);
      BeanUtils.copyProperty(destBean, descPropName, o);
    } catch (Exception ex) {
      throw new SystemException("���� " + sourceBean + "." + sourcePorpName
          + " �� " + destBean + "." + descPropName + " �����쳣", ex);
    }
  }

  public static void copyProperties(Object destBean, String[] descPropNames,
      Object sourceBean) {
    Validate.notNull(descPropNames, "Υ��ǰ��Լ�������� descPropNames ����Ϊ null");
    for (int i = 0; i < descPropNames.length; i++) {
      copyCertainProperty(destBean, descPropNames[i], sourceBean,
          descPropNames[i]);
    }
  }

  public static void copyProperties(Object destBean, String[] descPropNames,
      Object sourceBean, String[] sourcePropNames) {
    Validate.notNull(descPropNames, "Υ��ǰ��Լ�������� descPropNames ����Ϊ null");
    Validate.notNull(sourcePropNames, "Υ��ǰ��Լ�������� sourcePropNames ����Ϊ null");
    if (descPropNames.length != sourcePropNames.length)
      throw new IllegalArgumentException("Υ��ǰ��Լ������Ҫ������ Bean �а����в�ͬ������ Bean ����");
    for (int i = 0; i < descPropNames.length; i++) {
      copyCertainProperty(destBean, descPropNames[i], sourceBean,
          descPropNames[i]);
    }
  }

  public static void copyProperties(Object destBean, Object sourceBean) {
    Validate.notNull(destBean, "Υ��ǰ��Լ�������� destBean ����Ϊ null");
    Validate.notNull(sourceBean, "Υ��ǰ��Լ�������� sourceBean ����Ϊ null");
    try {
      BeanUtils.copyProperties(destBean, sourceBean);
    } catch (Exception ex) {
      throw new SystemException(
          "���� " + sourceBean + " �� " + destBean + " �����쳣", ex);
    }
  }
}
