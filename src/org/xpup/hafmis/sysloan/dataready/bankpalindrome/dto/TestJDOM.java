package org.xpup.hafmis.sysloan.dataready.bankpalindrome.dto;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
/**
 * ����ʹ��
 * @author Administrator
 *
 */
public class TestJDOM {

  public static void main(String[] args) {
    String rowNum = "5";//�ڴ�Ψһδ֪�ɱ��
    int intRowNum = Integer.parseInt(rowNum);
    Element rootElement = new Element("root");//(��Ԫ��)���е�XMLԪ�ض��� Element ��ʵ������Ԫ��Ҳ�����⣺��
    Document document = new Document(rootElement);//�Ը�Ԫ����Ϊ��������Document����һ��Documentֻ��һ��������rootԪ�ء�
    rootElement.setAttribute("file", "1");
    Element rootElement2 = new Element("file");//�����ڵ�
    rootElement.setContent(rootElement2);//���νڵ���ӵ����ڵ���
    Attribute attribute1 = new Attribute("name","OrgaddpayHeadImport");
    Attribute attribute2 = new Attribute("col",rowNum);//5��
    Attribute attribute3 = new Attribute("space","0");//����ռ�
    Attribute attribute4 = new Attribute("row","2");//����ռ�
    rootElement2.setAttribute(attribute1);//��������ӵ��νڵ���
    rootElement2.setAttribute(attribute2);
    rootElement2.setAttribute(attribute3);
    rootElement2.setAttribute(attribute4);
    
    for(int i=0;i<intRowNum;i++){
      Element rootElement3 = new Element("column");//�����ڵ�
      rootElement2.addContent(rootElement3);
      Element rootElement4 = new Element("name");//�ļ��ڵ�
      rootElement3.addContent(rootElement4);
      rootElement4.addContent(new Integer(i+1).toString());
      Element rootElement4_ = new Element("typle");//ƽ�е���һ���ļ��ڵ�
      rootElement3.addContent(rootElement4_);
      rootElement4_.addContent("java.lang.String");
    }
    XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
    try {
      xmlOut.output(document, System.out);//�ڿ���̨��ӡ���XML�ļ�
      xmlOut.output(document, new FileWriter("c:/testJDOM.xml"));//��C��������XML�ļ�
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
