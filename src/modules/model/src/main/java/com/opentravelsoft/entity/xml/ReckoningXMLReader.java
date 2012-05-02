package com.opentravelsoft.entity.xml;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.opentravelsoft.entity.finance.Reckoning;
import com.opentravelsoft.entity.finance.ReckoningAcct;
import com.opentravelsoft.util.AbstractObjectReader;
import com.opentravelsoft.util.RowDataUtil;

public class ReckoningXMLReader extends AbstractObjectReader
{
    protected SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void parse(InputSource input) throws IOException, SAXException
    {
        if (input instanceof ReckoningInputSource)
        {
            parse(((ReckoningInputSource) input).getReckoning());
        } else
        {
            throw new SAXException("Unsupported InputSource specified. "
                    + "Must be a ProjectTeamInputSource");
        }
    }

    private void parse(Reckoning reckoning) throws SAXException
    {
        if (reckoning == null)
        {
            throw new NullPointerException(
                    "Parameter projectTeam must not be null");
        }
        if (handler == null)
        {
            throw new IllegalStateException("ContentHandler not set");
        }

        handler.startDocument();
        generateFor(reckoning);
        handler.endDocument();
    }

    private void generateFor(Reckoning reckoning) throws SAXException
    {
        if (reckoning == null)
        {
            throw new NullPointerException(
                    "Parameter projectTeam must not be null");
        }

        if (handler == null)
        {
            throw new IllegalStateException("ContentHandler not set");
        }

        handler.startElement("reckoning");

        handler.element("reckoningId", RowDataUtil.intFormat(reckoning
                .getReckoningId()));
        handler.element("version", RowDataUtil
                .intFormat(reckoning.getVersion()));

        if (null == reckoning.getCreateDate())
            handler.element("createDate", "0000-00-00");
        else
            handler
                    .element("createDate", SDF
                            .format(reckoning.getCreateDate()));

        handler.element("agent", reckoning.getClient());
        handler.element("contact", reckoning.getContact());
        handler.element("phone", reckoning.getPhone());
        handler.element("fax", reckoning.getFax());
        handler.element("routeName", reckoning.getRouteName());
        handler.element("tourNo", reckoning.getTourNo());

        if (null == reckoning.getOutDate())
            handler.element("outDate", "0000-00-00");
        else
            handler.element("outDate", SDF.format(reckoning.getOutDate()));

        handler.element("pax", RowDataUtil.intFormat(reckoning.getPax()));
        handler.element("leaderPax", RowDataUtil.intFormat(reckoning
                .getLeaderPax()));

        for (ReckoningAcct reckoningAcct : reckoning.getReckoningAcctList())
        {
            generateFor(reckoningAcct);
        }

        handler.element("allAmount", RowDataUtil.doubleFormat(reckoning
                .getAmount()));

        handler.element("createdby", reckoning.getCreatedByName());
        handler.element("printedCount", RowDataUtil.intFormat(reckoning
                .getPrintedCount()));

        if (null == reckoning.getPrintDate())
            handler.element("printDate", "0000-00-00");
        else
            handler.element("printDate", SDF.format(reckoning.getPrintDate()));

        handler.endElement("reckoning");

    }

    private void generateFor(ReckoningAcct reckoningAcct) throws SAXException
    {
        if (reckoningAcct == null)
        {
            throw new NullPointerException(
                    "Parameter projectTeam must not be null");
        }

        if (handler == null)
        {
            throw new IllegalStateException("ContentHandler not set");
        }

        handler.startElement("reckoningAcct");

        handler.element("itemId", RowDataUtil.intFormat(reckoningAcct
                .getItemId()));
        handler.element("description", reckoningAcct.getDescription());
        handler.element("unitPrice", RowDataUtil.doubleFormat(reckoningAcct
                .getUnitPrice()));
        handler.element("count", RowDataUtil
                .intFormat(reckoningAcct.getCount()));
        handler.element("unit", reckoningAcct.getUnit());
        handler.element("amount", RowDataUtil.doubleFormat(reckoningAcct
                .getAmount()));
        handler.element("name", reckoningAcct.getName());

        handler.endElement("reckoningAcct");
    }

}
