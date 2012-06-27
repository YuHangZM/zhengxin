package org.xpup.common.util;

import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.struts.DelegatingActionUtils;

public final class BSUtils {

  private final static String FILE_APPLICATION_CONTEXT = "applicationContext.xml";

  private static XmlBeanFactory bf = null;

  public static void initApplicationContext(String contextFileName) {
    InputStream is = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(contextFileName);
    if (is == null) {
      log.error("δ�ܳɹ������ļ���Ϊ " + contextFileName + " �����ļ���");
    }
    try {
      Resource resource = new InputStreamResource(is);
      bf = new XmlBeanFactory(resource);
    } catch (Throwable ex) {
      log.error("����bean����ʧ�ܣ�ϵͳ���޷�����ʹ��ҵ�����", ex);
    }
  }

  public static void initApplicationContext() {
    initApplicationContext(FILE_APPLICATION_CONTEXT);
  }

  public static Object getBusinessService(String businessServiceName) {
    if (businessServiceName == null) {
      throw new IllegalArgumentException("���� businessServiceName ����Ϊ null");
    }

    Object bs = null;
    if (bf == null) {
      log.error("applicationContextû�б���ʼ����ɹ���ʼ����");
    }
    try {
      bs = bf.getBean(businessServiceName);
    } catch (BeansException ex) {
      log.error("��ȡҵ�������� '" + businessServiceName + "' ʧ��!!!", ex);
      throw ex;
    } catch (IllegalStateException ex) {
      log.error("��ȡҵ�������� '" + businessServiceName + "' ʧ��!!!", ex);
      throw ex;
    }
    return bs;
  }

  public static Object getBusinessService(String businessServiceName,
      ServletContext servletContext) {
    if (servletContext == null) {
      throw new IllegalArgumentException("���� servletContext ����Ϊ null");
    }
    if (businessServiceName == null) {
      throw new IllegalArgumentException("���� businessServiceName ����Ϊ null");
    }

    Object bs = null;
    try {
      WebApplicationContext wac = WebApplicationContextUtils
          .getRequiredWebApplicationContext(servletContext);
      bs = wac.getBean(businessServiceName);
    } catch (BeansException ex) {
      log.error("��ȡҵ�������� '" + businessServiceName + "' ʧ��!!!", ex);
      throw ex;
    } catch (IllegalStateException ex) {
      log.error("��ȡҵ�������� '" + businessServiceName + "' ʧ��!!!", ex);
      throw ex;
    }
    return bs;
  }

  public static Object getBusinessService(String businessServiceName,
      Action action) {
    if (action == null) {
      throw new IllegalArgumentException("���� action ����Ϊ null");
    }
    if (businessServiceName == null) {
      throw new IllegalArgumentException("���� businessServiceName ����Ϊ null");
    }

    Object bs = null;
    try {
      ServletContext servletContext = action.getServlet().getServletContext();
      WebApplicationContext wac = WebApplicationContextUtils
          .getRequiredWebApplicationContext(servletContext);
      bs = wac.getBean(businessServiceName);
    } catch (BeansException ex) {
      log.error("��ȡҵ�������� '" + businessServiceName + "' ʧ��!!!", ex);
      throw ex;
    } catch (IllegalStateException ex) {
      log.error("��ȡҵ�������� '" + businessServiceName + "' ʧ��!!!", ex);
      throw ex;
    }
    return bs;
  }

  /**
   * �÷���������Action�ķ����У���ȡҵ�������󡣸÷���֧��struts�Ķ�ģ�鿪����������һ��
   * ���ܻỮ��Ϊ��ĸ�ģ�����ͬ�Ŀ���С��Ĺ��̣���Ӧ��ʹ�ô˷�����������ʹģ�����׼��ɵ�һ ��һWEBӦ���С�
   * 
   * @param action ���ø÷����ķ������ڵ�Action
   * @param moduleConfig ���ø÷����ķ�����ModuleConfig
   * @param businessServiceName ��spring�����ļ��У����õ�ҵ����������
   * @return ҵ��������
   */
  public static Object getBusinessService(String businessServiceName,
      Action action, ModuleConfig moduleConfig) {

    return getBusinessService(businessServiceName, action.getServlet(),
        moduleConfig);

  }

  public static Object getBusinessService(String businessServiceName,
      ActionServlet servlet, ModuleConfig moduleConfig) {
    if (businessServiceName == null) {
      throw new IllegalArgumentException("���� businessServiceName ����Ϊ null");
    }
    if (moduleConfig == null) {
      throw new IllegalArgumentException("���� moduleConfig ����Ϊ null");
    }

    Object bs = null;
    try {
      WebApplicationContext wac = DelegatingActionUtils
          .getRequiredWebApplicationContext(servlet, moduleConfig);
      bs = wac.getBean(businessServiceName);
    } catch (BeansException ex) {
      log.error("��ȡҵ�������� '" + businessServiceName + "' ʧ��!!!", ex);
      throw ex;
    } catch (IllegalStateException ex) {
      log.error("��ȡҵ�������� '" + businessServiceName + "' ʧ��!!!", ex);
      throw ex;
    }
    return bs;
  }

  private static final Log log = LogFactory.getLog(BSUtils.class);
}