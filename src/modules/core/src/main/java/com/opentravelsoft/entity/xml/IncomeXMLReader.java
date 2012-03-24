package com.opentravelsoft.entity.xml;

import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.util.AbstractObjectReader;

public class IncomeXMLReader extends AbstractObjectReader
{

    @Override
    public void parse(InputSource input) throws IOException, SAXException
    {
        if (input instanceof IncomeInputSource)
        {
            parse(((IncomeInputSource) input).getIncome());
        } else
        {
            throw new SAXException("Unsupported InputSource specified. "
                    + "Must be a ProjectTeamInputSource");
        }
    }

    private void parse(Income income) throws SAXException
    {
        if (income == null)
        {
            throw new NullPointerException(
                    "Parameter projectTeam must not be null");
        }
        if (handler == null)
        {
            throw new IllegalStateException("ContentHandler not set");
        }

        handler.startDocument();
        generateFor(income);
        handler.endDocument();
    }
    
    private void generateFor(Income income) throws SAXException
    {
        if (income == null)
        {
            throw new NullPointerException(
                    "Parameter projectTeam must not be null");
        }

        if (handler == null)
        {
            throw new IllegalStateException("ContentHandler not set");
        }
        
        handler.startElement("income");
        
        handler.endElement("income");
    }
}
