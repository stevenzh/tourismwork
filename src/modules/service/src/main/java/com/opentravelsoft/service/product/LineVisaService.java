package com.opentravelsoft.service.product;

import java.util.List;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.LineVisa;

public interface LineVisaService
{
    public List<LineVisa> getVisaList(String lineNo);

    public List<Country> getCountrys();

    public List<LineVisa> getVisaByCountry(String countryCode);

    public int txSaveVisa(String lineNo, List<LineVisa> visaList);
}
