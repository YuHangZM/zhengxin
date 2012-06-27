package org.xpup.hafmis.signjoint.util;
import java.io.*;

import javax.servlet.ServletContext;
import javax.xml.parsers.*;
import org.xpup.hafmis.signjoint.dto.ConfigDTO;
import java.io.*;
import org.w3c.dom.*;
/**
 * @author yinchao
 * ��ȡXML�ĵ�����
 */
public class XmlIO {
  private DocumentBuilderFactory factory = null;
  private DocumentBuilder builder=null;
  private Document doc=null;
  private String port=null;
  private String mark=null;
  private String threadnum=null;
  
  public XmlIO(ServletContext sc)
  {
    try {
      factory = DocumentBuilderFactory.newInstance();
      builder = factory.newDocumentBuilder();
      doc = builder.parse(new File(sc.getRealPath("/")+"/WEB-INF/classes/org/xpup/hafmis/signjoint/conf/Config.xml"));//���������ĵ�
    } catch (Exception e) {
      System.out.println("");
      e.printStackTrace();
    }

  }
  /**
   * ��ȡ�洢��������Ϣ��DTO����
   * @return �����������ϢDTO
   */
  public ConfigDTO getConfig()
  {
    Element root =doc.getDocumentElement();
    NodeList nl=root.getChildNodes();
    port=nl.item(0).getFirstChild().getNodeValue().trim();
    //System.out.println(port);
    mark=nl.item(1).getFirstChild().getNodeValue().trim();
    //System.out.println(mark);
    threadnum=nl.item(2).getFirstChild().getNodeValue().trim();
    //System.out.println(threadnum);
    ConfigDTO content = new ConfigDTO();
    content.setMark(mark);
    content.setPort(Integer.parseInt(port));
    content.setThreadnum(Integer.parseInt(threadnum));
    return content;  
  }
  /**
   * ���������ĵ���Ϣ
   * @param content
   */
  public void setConfig(ConfigDTO content)
  {
    Element root =doc.getDocumentElement();
    NodeList nl=root.getChildNodes();
    nl.item(0).setNodeValue(Integer.toString(content.getPort()));
    nl.item(1).setNodeValue(content.getMark());
    nl.item(2).setNodeValue(Integer.toString(content.getThreadnum()));
  }

}
