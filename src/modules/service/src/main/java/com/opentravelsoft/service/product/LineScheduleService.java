package com.opentravelsoft.service.product;

import java.util.List;

import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.entity.LineTraffic;

public interface LineScheduleService
{
    public List<LineSchedule> getLineSchedule(String lineNo);

    public Line findLine(String lineNo);

    public int txInsertLineSchedule(String lineNo);

    public int txDeleteLineSchedule(LineSchedule schedule);

    public int txSaveLineSchedule(List<LineSchedule> list,
            List<LineTraffic> list1);

    public List<LineTraffic> roGetLineTraffic(String lineNo);

}
