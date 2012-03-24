package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.providers.GenericDao;

/**
 * 线路辅助说明
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
public interface LineTraitDao extends GenericDao<LineDescription, Long>
{
    public List<LineDescription> getLineTrait(String lineNo, String traitType);

    public int saveLineTrait(List<LineDescription> list, String lineNo,
            String type);

    public List<LineDescription> getExpense(String lineNo);

    public int saveExpense(Line line, List<LineDescription> list);

    public List<LabelValueBean> getNote(String lineNo, String type);

    public List<LineDescription> searchTips(String lineNo);
}
