package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.entity.LineTraffic;
import com.opentravelsoft.providers.GenericDao;

/**
 * 线路行程
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
public interface LineScheduleDao extends GenericDao<LineSchedule, Long>
{
    public List<LineSchedule> getLineSchedule(String lineNo);

    public int insertLineSchedule(String lineNo);

    public int deleteLineSchedule(LineSchedule schedule);

    /**
     * 保存线路行程
     * 
     * @param list
     * @return
     */
    public int saveLineSchedule(List<LineSchedule> list, List<LineTraffic> list1);

    public List<LineTraffic> getLineTraffic(String lineNo);

}
