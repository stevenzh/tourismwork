package com.opentravelsoft.service;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.TblUserPayOL;
import com.opentravelsoft.entity.finance.Income;

public interface PaymentService
{

    public List<Income> roGetGatheringList(int customerId,
            Date paymentDateStart, Date paymentDateEnd, double payGatherStart,
            double payGatherEnd);

    public Income roGetGathering(int incomeId);

    void insertPayer(TblUserPayOL payer);
}
