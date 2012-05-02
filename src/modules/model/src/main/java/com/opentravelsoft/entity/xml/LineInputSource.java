package com.opentravelsoft.entity.xml;

import org.xml.sax.InputSource;

import com.opentravelsoft.entity.Line;

/**
 * 
 * @author zhangst
 * 
 */
public class LineInputSource extends InputSource
{
    private Line line;

    public LineInputSource(Line route)
    {
        this.line = route;
    }

    public Line getRoute()
    {
        return line;
    }

    public void setRoute(Line route)
    {
        this.line = route;
    }
}
