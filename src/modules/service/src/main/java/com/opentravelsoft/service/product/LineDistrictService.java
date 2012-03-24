package com.opentravelsoft.service.product;

import java.util.List;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Province;

public interface LineDistrictService
{

    /**
     * 
     * @deprecated
     * @param lineNo
     * @return
     */
    public List<District> getDomesticLineDistrict(String lineNo);

    public List<District> getOverseaLineDistrict(String lineNo);

    public List<District> findOverseaLineDistrict(String countryNo,
            String provinceNo, String lineNo);

    public List<Province> getProvinceList();

    public List<Country> getCountry();

    public void txSaveLineDistrict(List<District> list, String lineNo);
}
