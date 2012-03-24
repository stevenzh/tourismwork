package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.entity.Sight;
import com.opentravelsoft.providers.GenericDao;

/**
 * 景点
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
public interface LineSightDao extends GenericDao<Sight, String>
{

    int saveSights(List<Sight> list, String lineNo);

    List<Sight> getDestinationSights(String lineNo);

    List<Sight> getLineSights(String lineNo);

}
