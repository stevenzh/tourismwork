package com.opentravelsoft.report.view;

import java.io.*;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FOPException;
import org.apache.log4j.Logger;
import org.efs.openreports.ORException;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.Result;

/**
 * FopRenderer
 */
public class FopRenderer {
  protected static Logger logger = Logger.getLogger(FopRenderer.class);

  /**
   * Renders a PDF document from a FO script that is passed in and returns the
   * content as a ByteArrayOutputStream
   * 
   * @param writer a Writer stream that supplies the FO text to be rendered
   * @return ByteArrayOutputStream containing the binary representation of a PDF
   *         document
   * @throws GeneralException
   */
  public static ByteArrayOutputStream render(Writer writer) throws ORException {

    FopFactory fopFactory = ApacheFopFactory.instance();

    ByteArrayOutputStream out = new ByteArrayOutputStream();

    TransformerFactory transFactory = TransformerFactory.newInstance();

    try {
      Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
      Transformer transformer = transFactory.newTransformer();

      // set the input source (XSL-FO) and generate the PDF
      Reader reader = new StringReader(writer.toString());
      Source src = new StreamSource(reader);

      // Get handler that is used in the generation process
      Result res = new SAXResult(fop.getDefaultHandler());

      try {
        // Transform the FOP XML source into a PDF, hopefully...
        transformer.transform(src, res);

        // We don't want to cache the images that get loaded by the FOP
        // engine
        fopFactory.getImageManager().getCache().clearCache();

        return out;

      } catch (TransformerException e) {
        logger.error("FOP transform failed:", e);
        throw new ORException("Unable to transform FO to PDF", e);
      }

    } catch (TransformerConfigurationException e) {
      logger.error("FOP TransformerConfiguration Exception ", e);
      throw new ORException("Transformer Configuration Error", e);
    } catch (FOPException e) {
      logger.error("FOP Exception ", e);
      throw new ORException("FOP Error", e);
    }
  }
}
