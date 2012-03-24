package com.opentravelsoft.service.operator;

import java.util.List;

import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.finance.Outcome;

public interface TicketService
{
    List<Outcome> listParcels(String supplierId, int userId, short carryStatus);

    List<Customer> getAirSuppliers();

    int txStartParcel(Outcome billhead);

    int txCompleteParcel(Outcome billhead);

}
