package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.LineDistrict;
import com.opentravelsoft.providers.GenericDao;

/**
 * 线路目的地
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
public interface LineDistrictDao extends GenericDao<LineDistrict, Long>
{
    public List<District> getDomesticLineDistrict(String lineNo);

    public List<District> getOverseaLineDistrict(String lineNo);

    public List<District> findOverseaLineDistrict(String countryNo,
            String provinceNo, String lineNo);

    public void saveLineDistrict(List<District> list, String lineNo);
}
