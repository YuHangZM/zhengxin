package org.xpup.common.exception;

/**
 * ʵ��δ�ҵ��쳣����Ҫ�������������ݴ�������У��Ҳ�����Ҫ��ʵ�����ݣ��������޷������Ӧ�Ĳ�����
 */
public class EntityNotFoundException extends BusinessException {

  private static final long serialVersionUID = 8134140267657909357L;

  /**
   * ����һ��ʵ��δ�ҵ��쳣��
   * 
   * @param message �쳣��������Ϣ��
   */
  public EntityNotFoundException(String message) {
    super(message);
  }

  /**
   * ����һ��ʵ��δ�ҵ��쳣��
   * 
   * @param message �쳣��������Ϣ��
   * @param cause ������쳣��ԭʼ�쳣��
   */
  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * ����һ��ʵ��δ�ҵ��쳣��
   * 
   * @param cause ������쳣��ԭʼ�쳣��
   */
  public EntityNotFoundException(Throwable cause) {
    super(cause);
  }
}
