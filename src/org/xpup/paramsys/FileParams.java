package org.xpup.paramsys;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.xpup.common.exception.SystemException;

public class FileParams extends AbstractParams {

  private Properties properties = new Properties();

  private List files = new ArrayList();

  public FileParams(List files) {
    Validate.notNull(files, "Υ��ǰ��Լ�������� filePaths ����Ϊ null��");
    this.files = files;
    load();
  }

  protected Properties getProperties() {
    return properties;
  }

  private void load() {
    InputStream input = null;
    try {
      try {
        for (Iterator itor = files.iterator(); itor.hasNext();) {
          String path = (String) itor.next();
          path = StringUtils.trimToEmpty(path);
          input = getClass().getResourceAsStream(path);

          if (input == null) {
            throw new SystemException("�Ҳ���·��Ϊ " + path
                + " �������ļ�����ȷ��·����ȷ������ / ��ͷ");
          }

          Properties temp = new Properties();
          temp.load(input);
          input.close();

          properties.putAll(temp);
        }
      } finally {
        if (input != null) {
          input.close();
        }
      }
    } catch (IOException ex) {
      throw new SystemException("���ز��������ļ������쳣", ex);
    }
  }

}
