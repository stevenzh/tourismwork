package com.opentravelsoft.service.product;

import java.util.List;

import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.entity.ProductItem;

public interface LineTraitService
{

    public List<LineDescription> getLineTrait(String lineNo, String type);

    public int txSaveLineTrait(List<LineDescription> list, String lineNo,
            String type);

    public List<LineDescription> roGetExpense(String lineNo);

    public int txSaveExpense(Line line, List<LineDescription> list);

    public ProductItem getProductItem(String code);

}
