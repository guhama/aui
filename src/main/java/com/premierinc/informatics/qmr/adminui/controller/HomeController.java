package com.premierinc.informatics.qmr.adminui.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Class HomeController.
 *
 * @author crowland
 */
@Controller
public class HomeController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

  /**
   * Base. Serves the starting page.
   *
   * @param lmsId the lmsId
   * @param request the request
   * @param model the model
   * @return the string
   */
  @RequestMapping("/")
  public String base(@RequestParam(value = "lmsId", required = false) Long lmsId,
      HttpServletRequest request, Model model) {

    LOGGER.trace("Serving /file-uploads/npi-facility-tranmission.");
    
    return "redirect:npi-facility-transmission/file-upload";
  }

  /**
   * Home. Convenience method that maps /home to /.
   *
   * @param lmsId the lmsId
   * @param request the request
   * @param model the model
   * @return the string
   */
  @RequestMapping("/home")
  public String home(@RequestParam(value = "lmsId", required = false) Long lmsId,
      HttpServletRequest request, Model model) {
    return base(lmsId, request, model);
  }

}
