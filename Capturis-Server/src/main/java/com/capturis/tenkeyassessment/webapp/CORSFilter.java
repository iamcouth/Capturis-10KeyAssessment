package com.capturis.tenkeyassessment.webapp;

import javax.inject.Singleton;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**

 * This Filter is to allow for CORs requests while in development mode. Placing the appropriate headers on each response.

 */

@Singleton

public class CORSFilter implements Filter {



  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

    //nothing to do

  }



  @Override

  public void destroy() {

    //nothing to do

  }



  @Override

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    if (servletResponse instanceof HttpServletResponse && shouldAddHeaders()) {

      HttpServletResponse alteredResponse = (HttpServletResponse) servletResponse;

      addHeadersFor200Response(alteredResponse);

    }

    filterChain.doFilter(servletRequest, servletResponse);

  }



  private boolean shouldAddHeaders() {

    return true;
    //return Development.isDevelopmentModeEnabled();

  }



  /**

   * Adds the required headers to the response

   *

   * @param response HttpServletResponse

   */

  private void addHeadersFor200Response(HttpServletResponse response) {

    response.addHeader("Access-Control-Allow-Origin", "*");

    response.addHeader("Access-Control-Allow-Credentials", "true");

    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With");

    response.addHeader("Access-Control-Allow-Headers", "GET, PUT, OPTIONS, X-XSRF-TOKEN, Authorization, Origin, X-Requested-With, Content-Type, Accept");

  }

}
