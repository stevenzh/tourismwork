package com.opentravelsoft.providers.mixed;

import java.util.List;

import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Line;

public interface CbIndexDao
{

    public Line getLineIndex(String lineNo);

    public List<Line> getAllLineIndex();

    /**
     * @deprecated
     * @return
     */
    public List<Destination> getRegionList();

}
