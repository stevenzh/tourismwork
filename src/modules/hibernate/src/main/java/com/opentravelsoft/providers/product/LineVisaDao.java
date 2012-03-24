package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.entity.LineVisa;
import com.opentravelsoft.providers.GenericDao;

/**
 * 线路签证
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
public interface LineVisaDao extends GenericDao<LineVisa, String>
{
    public List<LineVisa> getVisaList(String lineNo);

    public List<LineVisa> getByCountry(String country);

    public int saveVisa(String lineNo, List<LineVisa> visaList);
}
