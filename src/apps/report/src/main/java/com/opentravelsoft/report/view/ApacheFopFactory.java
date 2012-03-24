package com.opentravelsoft.report.view;

import org.apache.fop.apps.FopFactory;
import org.apache.fop.servlet.ServletContextURIResolver;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import java.io.File;

import javax.xml.transform.URIResolver;

/**
 * Apache FOP Factory used to provide a singleton instance of the FopFactory.
 * Best pratices recommended the reuse of the factory because of the startup
 * time.
 */
public class ApacheFopFactory {

  protected static Logger logger = Logger.getLogger(ApacheFopFactory.class);

  private static final FopFactory fopFactory;

  static {
    // Create the factory
    fopFactory = FopFactory.newInstance();

    // Limit the validation for backwards compatibility
    fopFactory.setStrictValidation(false);

    try {
      String ofbizHome = System.getProperty("gingkgo.home");

      URIResolver uriResolver = new ServletContextURIResolver(
          ServletActionContext.getServletContext());

      File userConfigFile = new File(ofbizHome + File.separatorChar + "WEB-INF"
          + File.separatorChar + "conf" + File.separatorChar + "fop.xconf");
      fopFactory.setUserConfig(userConfigFile);
      fopFactory.setURIResolver(uriResolver);
      String fopFontBaseUrl = "file:///" + ofbizHome + File.separatorChar
          + "WEB-INF" + File.separatorChar + "conf" + File.separatorChar;
      logger.info("FOP-FontBaseURL: " + fopFontBaseUrl);
      fopFactory.setFontBaseURL(fopFontBaseUrl.replace('\\', '/'));
    } catch (Exception e) {
      logger.warn("Error reading FOP configuration");
    }
  }

  public static FopFactory instance() {
    return fopFactory;
  }

}
