/**
 * Created on : 2006-6-12 14:37:46
 * Created by : ��Ұ
 * Email      : tian.ye@neusoft.com
 * Copyright  : www.xpup.org
 */
package org.xpup.security.wsf.acegiex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * У�������ɹ�����
 * 
 * @author $Author$
 * @version $Revision$,$Date$
 */
public class GenerateImageServlet extends HttpServlet {

  private static final long serialVersionUID = -5923184087385523815L;

  private static final Logger logger = Logger
      .getLogger(GenerateImageServlet.class);

  private int count = 6;

  private int width = 90, height = 20;

  private String strategy = "safe";

  private Font font = new Font("Times New Roman", Font.PLAIN, 18);

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("image/jpeg"); // ��������ContentTypeΪimage/jpeg
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    // ����ָ��λ��������ַ���
    String randomChars = RandomStringUtils.random(count, '0', 'Z', true, true)
        .toLowerCase();

    // ����֤�����뵽session
    HttpSession session = request.getSession(true);
    session
        .setAttribute(
            AuthenticationProcessingFilterEx.ACEGI_SECURITY_FORM_VALIDATECODE_KEY_IN_SESSION,
            randomChars);

    try {
      OutputStream out = response.getOutputStream();

      // Ӧ���㷨
      Class cls = this.getClass();
      Method algorithmMethod = cls.getDeclaredMethod(strategy, new Class[] {
          String.class, OutputStream.class });
      algorithmMethod.invoke(this, new Object[] { randomChars, out });

    } catch (Exception e) {
      logger.info("ͼƬ����ʧ��", e);
    }
  }

  /**
   * ����jpegͼƬ��д�뵽����
   * <p>
   * ��ȫ�ԣ��������ܣ�ǿ
   * 
   * @param randomChars ͼƬ����
   * @param out ��дͼƬ����
   */
  protected void fastest(String randomChars, OutputStream out) throws Exception {
    // �����ڴ�ͼ��
    BufferedImage bi = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D g = bi.createGraphics();
    g.clearRect(0, 0, width, height);
    g.setColor(Color.CYAN);
    g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
    g.drawString(randomChars, 8, 16);

    // ʹ��JPEG���룬�����response�������
    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
    JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);

    param.setQuality(1.0f, false);

    encoder.setJPEGEncodeParam(param);

    encoder.encode(bi);
  }

  /**
   * ����jpegͼƬ��д�뵽����
   * <p>
   * ��ȫ�ԣ��У����ܣ���
   * 
   * @param randomChars ͼƬ����
   * @param out ��дͼƬ����
   */
  protected void faster(String randomChars, OutputStream out) throws Exception {
    // ���ڴ��д���ͼ��
    BufferedImage image = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);

    // ��ȡͼ��������
    Graphics g = image.getGraphics();

    // �趨����ɫ
    g.setColor(new Color(0xDCDCDC));
    g.fillRect(0, 0, width, height);

    // ���߿�
    g.setColor(Color.black);
    g.drawRect(0, 0, width - 1, height - 1);

    // ����֤����ʾ��ͼ����
    g.setColor(Color.black);

    g.setFont(font);

    // ȡ�����������֤��
    char[] chars = randomChars.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      g.drawString(String.valueOf(chars[i]), 13 * i + 6, 16);
    }

    // �������������ŵ㣬ʹͼ���е���֤�벻�ױ���������̽�⵽
    Random random = new Random();
    for (int i = 0; i < 75; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      g.drawOval(x, y, 0, 0);
    }

    // ͼ����Ч
    g.dispose();

    // ���ͼ��ҳ��
    ImageIO.write(image, "JPEG", out);
  }

  /**
   * ����jpegͼƬ��д�뵽����
   * <p>
   * ��ȫ�ԣ�ǿ�����ܣ���
   * 
   * @param randomChars ͼƬ����
   * @param out ��дͼƬ����
   */
  protected void safe(String randomChars, OutputStream out) throws Exception {
    // ���ڴ��д���ͼ��
    BufferedImage image = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    // ��ȡͼ��������
    Graphics g = image.getGraphics();
    // ���������
    Random random = new Random();
    // �趨����ɫ
    g.setColor(getRandColor(200, 250));
    g.fillRect(0, 0, width, height);
    // �趨����
    g.setFont(font);

    // ���߿�
    g.setColor(getRandColor(20, 130));
    g.drawRect(0, 0, width - 1, height - 1);

    // ����������������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
    g.setColor(getRandColor(160, 200));
    for (int i = 0; i < 150; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      g.drawLine(x, y, x + xl, y + yl);
    }

    // ȡ�����������֤��
    char[] chars = randomChars.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      // ����֤����ʾ��ͼ����
      g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
          20 + random.nextInt(110)));

      // ���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
      g.drawString(String.valueOf(chars[i]), 13 * i + 6, 16);
    }

    // ͼ����Ч
    g.dispose();
    // ���ͼ��ҳ��
    ImageIO.write(image, "JPEG", out);
  }

  /**
   * ����ָ����Χ�ڵ����ɫ
   * 
   * @param start
   * @param end
   * @return
   */
  protected Color getRandColor(int start, int end) {// ������Χ��������ɫ
    Random random = new Random();

    int r = start + random.nextInt(end - start);
    int g = start + random.nextInt(end - start);
    int b = start + random.nextInt(end - start);

    return new Color(r, g, b);
  }

  public void init(ServletConfig servletConfig) throws ServletException {
    super.init(servletConfig);

    // ����ͼƬ�е��������λ��
    String numLength = getInitParameter("randomNumber.length");
    this.count = Integer.valueOf(numLength).intValue();

    // ����ͼƬ�Ĳ����㷨
    this.strategy = getInitParameter("randomImage.strategy");

  }

}
