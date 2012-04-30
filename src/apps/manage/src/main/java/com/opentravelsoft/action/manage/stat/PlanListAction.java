package com.opentravelsoft.action.manage.stat;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.portal.PlanListService;

/**
 * 出团计划查询-按地区
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:51 $
 */
public class PlanListAction extends ManageAction {

  private static final long serialVersionUID = -7625855842902512964L;

  @Autowired
  private PlanListService planListService;

  /** 地区 */
  private String regionId;

  /** 国内地区 */
  private List<List<LabelValueBean>> insideList;

  /** 国外地区 */
  private List<List<LabelValueBean>> abroadList;

  private List<List<LabelValueBean>> subRegions;

  private List<Plan> plans;

  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }

  public List<List<LabelValueBean>> getInsideList() {
    return insideList;
  }

  public List<List<LabelValueBean>> getAbroadList() {
    return abroadList;
  }

  public List<List<LabelValueBean>> getSubRegions() {
    return subRegions;
  }

  public List<Plan> getPlans() {
    return plans;
  }

  public String execute() throws Exception {
    insideList = planListService.getRegionGroupList();
    abroadList = planListService.getRegionGroupList();

    // 取得子分类
    subRegions = planListService.roGetSubRegions(regionId);
    // 出团计划
    plans = planListService.getPlanList(regionId, true);

    currentPage(plans.size());
    return SUCCESS;
  }
}
