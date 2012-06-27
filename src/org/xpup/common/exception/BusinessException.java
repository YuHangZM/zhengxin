package org.xpup.common.exception;

/**
 * ҵ���쳣ʹ�÷��� ������ҵ�������Ҫ���ɿ�����Աֱ�Ӵ�����ҵ���쳣��Ӧ�������Աά��һ��ҵ���쳣�����
 * ҵ���쳣�������Ҫ����������뼰��Ӧ����Ϣ,����ҵ���쳣����ӦΪ��ֵ��
 * 
 * @author wangyh
 * @version 1.0
 */
public class BusinessException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 5087741407144981924L;

  /**
   * ����һ��ҵ���쳣��
   * 
   * @param message �쳣��������Ϣ��
   */
  public BusinessException(String message) {
    super(message);
  }

  /**
   * ����һ��ҵ���쳣��
   * 
   * @param message �쳣��������Ϣ��
   * @param cause ������쳣��ԭʼ�쳣��
   */
  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * ����һ��ҵ���쳣��
   * 
   * @param cause ������쳣��ԭʼ�쳣��
   */
  public BusinessException(Throwable cause) {
    super(cause);
  }
}
