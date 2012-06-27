package org.xpup.hafmis.orgstrct.domain.enums;

import java.util.List;

import org.apache.commons.lang.enums.ValuedEnum;

public class ValueTypeEnum extends ValuedEnum {

  private static final long serialVersionUID = -6029125728976406079L;

  public static final ValueTypeEnum STRING = new ValueTypeEnum("�ַ���", 1);

  public static final ValueTypeEnum INTEGER = new ValueTypeEnum("����", 2);

  public static final ValueTypeEnum DOUBLE = new ValueTypeEnum("������", 3);

  public static final ValueTypeEnum DATE = new ValueTypeEnum("����", 4);

  public static final ValueTypeEnum ENUM = new ValueTypeEnum("ö��", 5);

  protected ValueTypeEnum(String name, int value) {
    super(name, value);
  }

  public static List getEnumList() {
    return getEnumList(ValueTypeEnum.class);
  }

  public static ValueTypeEnum getEnum(int valueType) {
    return (ValueTypeEnum) getEnum(ValueTypeEnum.class, valueType);
  }
}
