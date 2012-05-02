package com.opentravelsoft.action.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.portal.PlanListService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 线路列表
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class LineListAction extends PortalAction {
  private static final long serialVersionUID = 1868553276414702703L;

  @Autowired
  private PlanListService planService;

  private List<Plan> lines;

  /** 显示行数 */
  private long row = 1;

  /** 区域 */
  private String region;

  public List<Plan> getLines() {
    return lines;
  }

  public String execute() throws Exception {
    lines = planService.getPlans(row, false, region);

    return SUCCESS;
  }

  public long getRow() {
    return row;
  }

  public void setRow(long row) {
    this.row = row;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

}
