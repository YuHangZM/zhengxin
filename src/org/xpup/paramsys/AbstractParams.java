package org.xpup.paramsys;

import java.util.Properties;

import org.apache.commons.lang.Validate;

public abstract class AbstractParams implements IParams {

  public boolean getBoolean(String key) {
    String value = handlePropsGetting(key);
    if (value == null)
      throw new NullPointerException("û����key���Ӧ�Ĳ������á�");
    return Boolean.valueOf(value).booleanValue();
  }

  public boolean getBoolean(String key, boolean defaultValue) {
    String value = handlePropsGetting(key);
    if (value == null)
      return defaultValue;
    return Boolean.valueOf(value).booleanValue();
  }

  public double getDouble(String key) {
    String value = handlePropsGetting(key);
    if (value == null)
      throw new NullPointerException("û����key���Ӧ�Ĳ������á�");
    return Double.valueOf(value).doubleValue();
  }

  public double getDouble(String key, double defaultValue) {
    String value = handlePropsGetting(key);
    if (value == null)
      return defaultValue;
    return Double.valueOf(value).doubleValue();
  }

  public int getInt(String key) {
    String value = handlePropsGetting(key);
    if (value == null)
      throw new NullPointerException("û����key���Ӧ�Ĳ������á�");
    return Integer.valueOf(value).intValue();
  }

  public int getInt(String key, int defaultValue) {
    String value = handlePropsGetting(key);
    if (value == null)
      return defaultValue;
    return Integer.valueOf(value).intValue();
  }

  public String getString(String key) {
    String value = handlePropsGetting(key);
    if (value == null)
      throw new NullPointerException("û����key���Ӧ�Ĳ������á�");
    return value;
  }

  public String getString(String key, String defaultValue) {
    String value = handlePropsGetting(key);
    if (value == null)
      return defaultValue;
    return value;
  }

  protected abstract Properties getProperties();

  private String handlePropsGetting(String key) {
    Properties props = getProperties();
    Validate.notNull(props, "��ʼ�����󣺷���getProperties()����null��");
    return props.getProperty(key);
  }

}
