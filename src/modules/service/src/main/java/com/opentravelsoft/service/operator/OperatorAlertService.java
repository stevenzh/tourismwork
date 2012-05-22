package com.opentravelsoft.service.operator;

import java.util.List;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.product.Remind;

public interface OperatorAlertService {
  List<Remind> roGetOperator(int userId);

  List<Booking> roGetNewBookings(int userId);

  List<Team> roGetDepartments(int userId, TeamType type);
}
