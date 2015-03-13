package com.premierinc.informatics.qmr.adminui.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.UserInfo;
import com.premierinc.informatics.qmr.adminui.domain.ae.repository.UserInfoRepo;
import com.premierinc.informatics.qmr.adminui.util.Constants;

/**
 * The Class SecurityInterceptor.
 *
 * @author crowland
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private UserInfoRepo userInfoRepo;

  private UserInfo user;

  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);

  /**
   * Pre-handle. Before the request is passed to the controller this will check for an existing
   * session. If no session is present, check for UserInfo based on the passed in lmsId.
   *
   * @param request the request
   * @param response the response
   * @param handler the handler
   * @return true, if successful
   * @throws Exception the exception
   */
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    user = (UserInfo) request.getSession().getAttribute(Constants.USER);
    String lmsId = request.getParameter(Constants.LMS_ID);
    String httpUser = request.getHeader("HTTP_USER");
    String remoteUser = request.getHeader("REMOTE_USER");

    if (user == null) {
      return createSession(httpUser, remoteUser, lmsId, request, response);
    } else {

      String headerUser = null;

      if (!StringUtils.hasText(httpUser)) {
        if (StringUtils.hasText(remoteUser)) {
          LOGGER.debug("[Access Control] Using REMOTE_USER=[" + remoteUser + "] as HTTP_USER");
          headerUser = remoteUser;
        }
      } else {
        headerUser = httpUser;
      }

      if (StringUtils.hasText(headerUser)) {
        if (!headerUser.trim().equalsIgnoreCase(user.getLdapUsername())) {
          LOGGER.error("[Access Control] HTTP_USER supplied [" + headerUser
              + "] does not match the user stored in session [" + user.getLdapUsername()
              + "]! Removing the session user, forcing the Action to re-evaluate the user.");
          request.getSession().removeAttribute(Constants.USER);
          return createSession(httpUser, remoteUser, lmsId, request, response);

        } else {
          LOGGER.debug("[Access Control] Verified HTTP_USER [" + headerUser
              + "] is the user stored in session");
        }
      }

      LOGGER.info("[Access Control] User: " + user.getId().getUserId() + " Request: "
          + request.getRequestURI() + " APPROVED");
      return true;
    }
  }

  /**
   * After completion.
   *
   * @param request the request
   * @param response the response
   * @param handler the handler
   * @param ex the ex
   */
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {}

  /**
   * Gets the user info repo.
   *
   * @return the user info repo
   */
  public UserInfoRepo getUserInfoRepo() {
    return userInfoRepo;
  }

  /**
   * Sets the user info repo.
   *
   * @param userInfoRepo the new user info repo
   */
  public void setUserInfoRepo(UserInfoRepo userInfoRepo) {
    this.userInfoRepo = userInfoRepo;
  }

  /**
   * Creates the session.
   *
   * @param httpUser the http user
   * @param remoteUser the remote user
   * @param lmsId the lms id
   * @param request the request
   * @param response the response
   * @return true, if successful
   * @throws Exception the exception
   */
  private boolean createSession(String httpUser, String remoteUser, String lmsId,
      HttpServletRequest request, HttpServletResponse response) throws Exception {

    // Check the HTTP_USER header first
    if (StringUtils.hasText(httpUser)) {
      LOGGER.info("[Access Control] Creating session for HTTP_USER: " + httpUser);
      user = userInfoRepo.findByldapUsername(httpUser);

      if (user == null) {
        LOGGER.error("[Access Control] Unable to find user data from USER_INFO for LDAP_USERNAME: "
            + httpUser);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
            "Unable to authenticate your request. Please contact support. ERROR CODE: 00004");
        return false;
      }
    } else if (StringUtils.hasText(remoteUser)) { // REMOTE_USER second
      LOGGER.info("[Access Control] Creating session for REMOTE_USER: " + remoteUser);
      user = userInfoRepo.findByldapUsername(remoteUser);

      if (user == null) {
        LOGGER.error("[Access Control] Unable to find user data from USER_INFO for LDAP_USERNAME: "
            + remoteUser);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
            "Unable to authenticate your request. Please contact support. ERROR CODE: 00005");
        return false;
      }
    } else { // If neither headers are present, check for a passed in lmsId
      LOGGER.info("[Access Control] Creating session for lmsId: " + lmsId);
      if (lmsId == null) {
        LOGGER.error("[Access Control] No credentials were provided. Request will be denied.");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
            "Unable to authenticate your request. Please contact support. ERROR CODE: 00001");
        return false;
      }

      try {
        user = userInfoRepo.findByLmsId(Long.parseLong(lmsId));
      } catch (NumberFormatException e) {
        LOGGER.error("[Access Control] Invalid lmsId provided: " + lmsId);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
            "Unable to authenticate your request. Please contact support. ERROR CODE: 00002");
        return false;
      }

      if (user == null) {
        LOGGER
            .error("[Access Control] Unable to find user data from USER_INFO for lmsId: " + lmsId);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
            "Unable to authenticate your request. Please contact support. ERROR CODE: 00003");
        return false;
      }
    }
    request.getSession().setAttribute(Constants.USER, user);
    LOGGER.info("[Access Control] User: " + user.getId().getUserId() + " Request: "
        + request.getRequestURI() + " APPROVED");
    return true;
  }

}
