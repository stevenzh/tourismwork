package com.opentravelsoft.service.product;

import java.util.List;

import com.opentravelsoft.entity.Sight;

public interface LineSightService
{
    public int txSaveSights(List<Sight> list, String lineNo);

    public List<Sight> roGetLineSights(String lineNo);

    public List<Sight> roGetDestinationSights(String lineNo);
}
