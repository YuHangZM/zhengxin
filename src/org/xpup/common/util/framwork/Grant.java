/*
 * Created on 2005-6-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.xpup.common.util.framwork;

import java.io.Serializable;
import java.util.List;
import java.util.Map;



/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Grant implements Serializable{
    //�û�����
    private String username;
    //�û�����
    private String passwd;
    //��λ�б�
    private List orgunitList;
    //�������б�
    private List subcenterList;
    //����
    private List center;
    //��ɫ�б�
    private Map rolemap;
    //����
    private String role="";
    //Ӧ�ó������
    private String appcode;
    //��ǰ��ɫ
    private String currole;
    //��½�û���Ӧ��ʵ���
    private String realname;
    
    public Grant(){
        
    }
    

    /**
     * @return Returns the passwd.
     */
    public String getPasswd() {
        return passwd;
    }
    /**
     * @param passwd The passwd to set.
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    /**
     * @return Returns the currole.
     */
    public String getCurrole() {
        return currole;
    }
    /**
     * @param currole The currole to set.
     */
    public void setCurrole(String currole) {
        this.currole = currole;
    }
    /**
     * @return Returns the appcode.
     */
    public String getAppcode() {
        return appcode;
    }
    /**
     * @param appcode The appcode to set.
     */
    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }
    /**
     * @return Returns the role.
     */
    public String getRole() {
        return role;
    }
    /**
     * @param role The role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }
    /**
     * @return Returns the center.
     */
    public List getCenter() {
        return center;
    }
    /**
     * @param center The center to set.
     */
    public void setCenter(List center) {
        this.center = center;
    }
    /**
     * @return Returns the orgunitList.
     */
    public List getOrgunitList() {
        return orgunitList;
    }
    /**
     * @param orgunitList The orgunitList to set.
     */
    public void setOrgunitList(List orgunitList) {
        this.orgunitList = orgunitList;
    }
    
     
    /**
     * @return Returns the subcenterList.
     */
    public List getSubcenterList() {
        return subcenterList;
    }
    /**
     * @param subcenterList The subcenterList to set.
     */
    public void setSubcenterList(List subcenterList) {
        this.subcenterList = subcenterList;
    }
    /**
     * @return Returns the rolemap.
     */
    public Map getRolemap() {
        return rolemap;
    }
    /**
     * @param rolemap The rolemap to set.
     */
    public void setRolemap(Map rolemap) {
        this.rolemap = rolemap;
    }
    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
	/**
	 * @return ���� realname��
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * @param realname Ҫ���õ� realname��
	 */
	public void setRealname(String realname) {
		this.realname = realname; 
	}
}
