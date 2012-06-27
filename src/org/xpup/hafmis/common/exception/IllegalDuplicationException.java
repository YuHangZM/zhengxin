package org.xpup.hafmis.common.exception;

import org.xpup.common.exception.BusinessException;


/**
 * �Ƿ����ظ��쳣��<br>
 * ��Ҫ�������������ݴ�������У������������֮һ��
 * <li>�����Ѿ�������ϵͳ��
 * <li>�����еĲ��ֲ������ظ�����������ظ�<br>
 * ���׳����쳣��
 */
public class IllegalDuplicationException extends BusinessException {

  private static final long serialVersionUID = -2872718954679760392L;

  /**
   * ����һ���Ƿ����ظ��쳣��
   * 
   * @param message �쳣��������Ϣ��
   * @param cause ������쳣��ԭʼ�쳣��
   */
  public IllegalDuplicationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * ����һ���Ƿ����ظ��쳣��
   * 
   * @param message �쳣��������Ϣ��
   */
  public IllegalDuplicationException(String message) {
    super(message);
  }

  /**
   * ����һ���Ƿ����ظ��쳣��
   * 
   * @param cause ������쳣��ԭʼ�쳣��
   */
  public IllegalDuplicationException(Throwable cause) {
    super(cause);
  }
}
