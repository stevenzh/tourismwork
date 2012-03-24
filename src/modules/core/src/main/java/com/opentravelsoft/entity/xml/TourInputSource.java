package com.opentravelsoft.entity.xml;

import org.xml.sax.InputSource;

import com.opentravelsoft.entity.Plan;

public class TourInputSource extends InputSource
{
    private Plan tour;

    public TourInputSource(Plan tour)
    {
        this.tour = tour;
    }

    public Plan getTour()
    {
        return tour;
    }

    public void setTour(Plan tour)
    {
        this.tour = tour;
    }
}
