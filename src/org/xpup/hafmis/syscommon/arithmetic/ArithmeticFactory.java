package org.xpup.hafmis.syscommon.arithmetic;

import org.xpup.hafmis.common.util.BusiConst;

public class ArithmeticFactory{
  private static ArithmeticFactory arithmetic;
  ArithmeticFactory(){
      
    }
  public static synchronized ArithmeticFactory getArithmetic(){
    if (arithmetic == null) {
      arithmetic = new ArithmeticFactory();
    }
    return arithmetic;
  }
  /**
   * �����ض����㷨DAO��
   * @param paymentAccuracy
   * @return
   */
  public ArithmeticInterface getArithmeticDAO(int paymentAccuracy){
    ArithmeticInterface arithmeticInterface = null;
    switch (paymentAccuracy){
      case BusiConst.PAYMENTACCURACY_ROUNDTOYUAN://�������뵽Ԫ
        arithmeticInterface = new RoundtoYuanDAO();
        break;
      case BusiConst.PAYMENTACCURACY_DISCARDTOYUAN://��Ԫ����
        arithmeticInterface = new DiscardtoYuanDAO();
        break;
      case BusiConst.PAYMENTACCURACY_SEEKOKONYUAN://���ǽ�Ԫ
        arithmeticInterface = new SeekokonYuanDAO();
        break;
      case BusiConst.PAYMENTACCURACY_SEECENTSONKOK://���ֽ���
        arithmeticInterface = new SeeCenttoChiaoDAO();
        break;
      case BusiConst.PAYMENTACCURACY_ROUNDTOKOK://�������뵽��
        arithmeticInterface = new RounttoChiaoDAO();
        break;
      case BusiConst.PAYMENTACCURACY_DISCARDTOKOK://�������
        arithmeticInterface = new DiscardChiaoDAO();
        break;
      case BusiConst.PAYMENTACCURACY_SEEKOKCENTSONYUAN://���Ƿֽ�Ԫ
        arithmeticInterface = new SeekChiaoCenttoYuanDAO();
        break;
      case BusiConst.PAYMENTACCURACY_ROUNDTOCENT://�������뵽��
        arithmeticInterface = new RounttoCentDAO();
    } 
    return arithmeticInterface;
  }
}