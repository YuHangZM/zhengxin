
package org.xpup.hafmis.common.util.enumerate;

import java.util.TreeMap;
/**
 * @author ����
 *2007-6-2
 */
abstract class AbsBusiProMap extends TreeMap {

  private static final long serialVersionUID = -498746610184746795L;

  protected void putValues(Integer[] keys,String[] values)
	{
		for (int i = 0;i<keys.length;i++)
		{
			this.put(keys[i],values[i]);
		}
	}
  /**
   * @author ����
   * 2007-6-22
   * @param keys
   * @param values
   * ת������λ������ҵ���õ�
   */
  protected void putValues_Str(String[] keys,String[] values)
  {
    for (int i = 0;i<keys.length;i++)
    {
      this.put(keys[i],values[i]);
    }
  }

}
