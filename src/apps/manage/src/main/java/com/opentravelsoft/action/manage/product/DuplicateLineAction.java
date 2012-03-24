package com.opentravelsoft.action.manage.product;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.service.product.LineService;
import com.opentravelsoft.util.StringUtil;

/**
 * 复制线路
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class DuplicateLineAction extends ManageAction {
  private static final long serialVersionUID = -862029740373263941L;

  private LineService routeService;

  /** 线路特色 */
  private String copyFeature;

  /** 线路行程 */
  private String copySchedule;

  /** 线路价格 */
  private String copyPrice;

  /** 线路目的地 */
  private String copyDestination;

  /** 线路景点 */
  private String copySight;

  /** 线路签证 */
  private String copyVisa;

  private Line line;

  private String lineNo;

  private String newLineName;

  private List<LabelValueBean> status;

  // -------------------------------------------------------------------------
  // 检索条件

  /** 专管员 */
  private String kenUserId;

  /** 部门编码 */
  private String kenDepartmentNo;

  private String kenDestination;

  /** 线路名称 */
  private String kenLineName;

  private String kenClosekey;

  // -------------------------------------------------------------------------

  @Override
  public String input() throws Exception {
    line = routeService.getLine(lineNo);
    status = getCodeList("ebiz_yes_no");

    copyFeature = "Y";
    copySchedule = "Y";
    copyPrice = "N";
    copyDestination = "Y";
    copySight = "Y";
    copyVisa = "Y";

    return INPUT;
  }

  public String submit() throws EbizException {
    Employee user = getUser();
    String newLineNo = routeService.txDuplicateRoute(lineNo, newLineName,
        copyFeature, copySchedule, copyPrice, copyDestination, copySight,
        copyVisa, user.getUserId());

    if (StringUtil.hasLength(newLineNo)) {
      line = routeService.getLine(newLineNo);
    } else {
      addActionError("复制失败.");
      return INPUT;
    }

    return SUCCESS;
  }

  @Autowired
  public void setRouteService(LineService routeService) {
    this.routeService = routeService;
  }

  public Line getRoute() {
    return line;
  }

  public String getCopyFeature() {
    return copyFeature;
  }

  public void setCopyFeature(String copyFeature) {
    this.copyFeature = copyFeature;
  }

  public String getCopySchedule() {
    return copySchedule;
  }

  public void setCopySchedule(String copySchedule) {
    this.copySchedule = copySchedule;
  }

  public String getCopyPrice() {
    return copyPrice;
  }

  public void setCopyPrice(String copyPrice) {
    this.copyPrice = copyPrice;
  }

  public String getCopyDestination() {
    return copyDestination;
  }

  public void setCopyDestination(String copyDestination) {
    this.copyDestination = copyDestination;
  }

  public String getCopySight() {
    return copySight;
  }

  public void setCopySight(String copySight) {
    this.copySight = copySight;
  }

  public String getCopyVisa() {
    return copyVisa;
  }

  public void setCopyVisa(String copyVisa) {
    this.copyVisa = copyVisa;
  }

  public String getNewLineName() {
    return newLineName;
  }

  public void setNewLineName(String newLineName) {
    this.newLineName = newLineName;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public List<LabelValueBean> getStatus() {
    return status;
  }

  public String getKenUserId() {
    return kenUserId;
  }

  public void setKenUserId(String kenUserId) {
    this.kenUserId = kenUserId;
  }

  public String getKenDepartmentNo() {
    return kenDepartmentNo;
  }

  public void setKenDepartmentNo(String kenDepartmentNo) {
    this.kenDepartmentNo = kenDepartmentNo;
  }

  public String getKenRouteName() {
    return kenLineName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenLineName = kenRouteName;
  }

  public String getKenClosekey() {
    return kenClosekey;
  }

  public void setKenClosekey(String kenClosekey) {
    this.kenClosekey = kenClosekey;
  }

  public String getlineNo() {
    return lineNo;
  }

  public String getKenDestination() {
    return kenDestination;
  }

  public void setKenDestination(String kenDestination) {
    this.kenDestination = kenDestination;
  }

}
