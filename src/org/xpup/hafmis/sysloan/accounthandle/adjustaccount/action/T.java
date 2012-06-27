package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

public class T {
  static public double log(double value, double base) {
    return Math.log(value) / Math.log(base);
  }

  // ����100����10Ϊ�׵Ķ����ͱ�Ϊ�ǳ����ˣ�
  double log1 = T.log(100, 10); // log is 2.0
  // 512����2Ϊ�׵Ķ����ǣ�

  double log2 = T.log(512, 2); // log is 9.0
  // ����������򵥵ķ���Ҳ���Ǻ����õģ�

  static public double log2(double value) {
    return log(value, 2.0);
  }

  static public double log10(double value) {
    return log(value, 10.0);
  }

  public void main() {
    System.out.println(log1);
  }
}