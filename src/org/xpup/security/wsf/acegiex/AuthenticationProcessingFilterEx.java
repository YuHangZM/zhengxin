/**
 * Created on : 2006-6-12 14:37:46
 * Created by : ��Ұ
 * Email      : tian.ye@neusoft.com
 * Copyright  : www.xpup.org
 */
package org.xpup.security.wsf.acegiex;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xpup.hafmis.orgstrct.bizsrvc.impl.OrgStructureBS;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.paramsys.IParams;
import org.xpup.security.common.dao.UserDAO;
import org.xpup.security.common.domain.User;

import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.AuthenticationException;
import net.sf.acegisecurity.UserDetails;
import net.sf.acegisecurity.providers.dao.AuthenticationDao;
import net.sf.acegisecurity.ui.webapp.AuthenticationProcessingFilter;

/**
 * У�������ɹ�����
 * 
 * @author $Author$
 * @version $Revision$,$Date$
 */
public class AuthenticationProcessingFilterEx extends
    AuthenticationProcessingFilter {
  // ~ Static fields/initializers =============================================

  public static final String ACEGI_SECURITY_FORM_VALIDATECODE_KEY_IN_SESSION = "validatecode_in_session_attribute";

  public static final String ACEGI_SECURITY_FORM_VALIDATECODE_KEY_IN_REQUEST = "j_validatecode";

  public static final String ACEGI_SECURITY_EXCEPTION_COUNT = "ACEGI_SECURITY_EXCEPTION_COUNT";

  public static final int ACEGI_SECURITY_SHOW_VALIDATECODE_COUNT = 0;

  // ~ Methods ================================================================

  public Authentication attemptAuthentication(HttpServletRequest request)
      throws AuthenticationException {

    HttpSession session = request.getSession();
    try {

      // ����ʹ�õĵ�¼�û�����������ʾ
      session.setAttribute(ACEGI_SECURITY_LAST_USERNAME_KEY,
          obtainUsername(request));
      
     
        
      boolean validate = params.getBoolean("useValidationCode", true);
      if (validate) {
        // ���session
        checkSession(session);  
        // ���У����
        checkValidatecode(request, session);
      }
      
      UserDetails user = securityDAO.queryByUsername(obtainUsername(request));
      if (user!= null) {
        try{
        SecurityInfo securityInfo=securityDAO.getSecurityInfo(obtainUsername(request), request.getRemoteAddr()); 
        session.setAttribute("SecurityInfo", securityInfo);
        }catch(Exception e){
          //e.printStackTrace();
        }
      }

      return super.attemptAuthentication(request);

    } catch (RuntimeException e) {
      // ֻҪ���쳣�����ۼӼ�����
      int exceptionCount = getExceptionCountFromSession(session);

      session.setAttribute(ACEGI_SECURITY_EXCEPTION_COUNT, new Integer(
          exceptionCount + 1));

      throw e;
    }
    
   
  }

  /**
   * ��session�л�ȡУ��ʧ�ܴ���
   * 
   * @param session
   * @return
   */
  protected int getExceptionCountFromSession(HttpSession session) {
    Integer exceptionCountInSession = (Integer) session
        .getAttribute(ACEGI_SECURITY_EXCEPTION_COUNT);
    int exceptionCount = exceptionCountInSession == null ? 0
        : exceptionCountInSession.intValue();
    return exceptionCount;
  }

  /**
   * У����֤��
   * 
   * @param request
   * @param session
   * @return
   */
  protected void checkValidatecode(HttpServletRequest request,
      HttpSession session) {
    int exceptionCount = getExceptionCountFromSession(session);

    if (exceptionCount >= ACEGI_SECURITY_SHOW_VALIDATECODE_COUNT) {

      String validateCodeInSession = (String) session
          .getAttribute(ACEGI_SECURITY_FORM_VALIDATECODE_KEY_IN_SESSION);
      String validatecodeInRequest = request
          .getParameter(ACEGI_SECURITY_FORM_VALIDATECODE_KEY_IN_REQUEST);

      if (validatecodeInRequest == null
          || (!validatecodeInRequest.equals(validateCodeInSession))) {

        class BadValidatecodeException extends AuthenticationException {

          private static final long serialVersionUID = -978578145830156755L;

          public BadValidatecodeException(String msg) {
            super(msg);
          }
        }

        throw new BadValidatecodeException("��֤�벻ƥ�䣬���������룡");
      }
    }
  }

  /**
   * У��session
   * 
   * @param session
   */
  protected void checkSession(HttpSession session) {
    // ���session�����ڣ�ֱ���׳��쳣����ʾ����������
    if (session == null) {
      class OverdueException extends AuthenticationException {

        private static final long serialVersionUID = 8404846643035600807L;

        public OverdueException(String msg) {
          super(msg);
        }
      }

      throw new OverdueException("����������ҳ�棡");
    }
  }

  protected void successfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication)
      throws IOException {
    super.successfulAuthentication(request, response, authentication);
  }

  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException exception)
      throws IOException {
    String isAjax = request.getHeader("AJAX");
    if ("TRUE".equals(isAjax)) {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception
          .getMessage()); // 401
    } else {
      super.unsuccessfulAuthentication(request, response, exception);
    }
  }

  private IParams params = null;

  public void setParams(IParams params) {  
    this.params = params;
  }

  
  private SecurityDAO securityDAO = null;  

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }
}  
