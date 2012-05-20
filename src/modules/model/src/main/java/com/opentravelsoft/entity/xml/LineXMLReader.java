package com.opentravelsoft.entity.xml;

import java.io.IOException;
import java.util.Iterator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.util.AbstractObjectReader;
import com.opentravelsoft.util.RowDataUtil;

/**
 * 
 * @author zhangst
 * 
 */
public class LineXMLReader extends AbstractObjectReader {

  @Override
  public void parse(InputSource input) throws IOException, SAXException {
    if (input instanceof LineInputSource) {
      parse(((LineInputSource) input).getRoute());
    } else {
      throw new SAXException("Unsupported InputSource specified. "
          + "Must be a ProjectTeamInputSource");
    }
  }

  private void parse(Line route) throws SAXException {
    if (route == null) {
      throw new NullPointerException("Parameter projectTeam must not be null");
    }
    if (handler == null) {
      throw new IllegalStateException("ContentHandler not set");
    }

    // Start the document
    handler.startDocument();

    // Generate SAX events for the ProjectTeam
    generateFor(route);

    // End the document
    handler.endDocument();
  }

  private void generateFor(Line route) throws SAXException {
    if (route == null) {
      throw new NullPointerException("Parameter projectTeam must not be null");
    }
    if (handler == null) {
      throw new IllegalStateException("ContentHandler not set");
    }

    handler.startElement("route");
    handler.element("route-name", route.getLineName());
    Iterator<LineSchedule> i = route.getSchedule().iterator();
    while (i.hasNext()) {
      LineSchedule schedule = (LineSchedule) i.next();
      generateFor(schedule);
    }

    Iterator<LineDescription> s = route.getExpenseIn().iterator();
    while (s.hasNext()) {
      LineDescription schedule = (LineDescription) s.next();
      generateFor(schedule);
    }
    handler.endElement("route");
  }

  private void generateFor(LineSchedule schedule) throws SAXException {
    if (schedule == null) {
      throw new NullPointerException("Parameter projectMember must not be null");
    }
    if (handler == null) {
      throw new IllegalStateException("ContentHandler not set");
    }

    handler.startElement("schedules");
    handler.element("day", RowDataUtil.intFormat(schedule.getId().getDay()));
    handler.element("traffic", schedule.getTraffic());
    handler.element("program", schedule.getProgram());
    handler.endElement("schedules");
  }

  private void generateFor(LineDescription schedule) throws SAXException {
    if (schedule == null) {
      throw new NullPointerException("Parameter projectMember must not be null");
    }
    if (handler == null) {
      throw new IllegalStateException("ContentHandler not set");
    }

    handler.startElement("expenseIn");
    handler.element("id", RowDataUtil.intFormat(schedule.getRefNo()));
    handler.element("item", schedule.getTraitDetail());
    handler.endElement("expenseIn");
  }
}
