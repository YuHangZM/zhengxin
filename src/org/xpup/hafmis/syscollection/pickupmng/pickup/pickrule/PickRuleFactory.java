package org.xpup.hafmis.syscollection.pickupmng.pickup.pickrule;
public class PickRuleFactory {//��ȡ����Ĺ�����..����ԭ����õ�ʵ�ʵ���
    public static DrawRulesInterface getDrawRule(String pickType){
      if(pickType.equals("1"))
        return new SomePick();
      if(pickType.equals("2"))
        return new AllPick();
      return null;
    }
}
