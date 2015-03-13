package com.premierinc.informatics.qmr.adminui.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.UserInfo;
import com.premierinc.informatics.qmr.adminui.util.Constants;

/**
 * The Class LogoutController.
 *
 * @author crowland
 */
@Controller
public class LogoutController {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);

  /**
   * Logout. Invalidates the session and redirects user.
   *
   * @param request the request
   * @return the model and view
   */
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request) {

    LOGGER.trace("Processing logout request.");
    LOGGER.info("Invalidating session for "
        + ((UserInfo) request.getSession().getAttribute(Constants.USER)).getId().getUserId());

    request.getSession().invalidate();
    return new ModelAndView("redirect:https://www.premierinc.com");
  }
}
