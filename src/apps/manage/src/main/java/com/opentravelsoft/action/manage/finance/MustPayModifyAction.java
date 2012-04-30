package com.opentravelsoft.action.manage.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.operator.TourService;

/**
 * 修改预订单应收款
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:53 $
 */
public class MustPayModifyAction extends ManageAction {
  private static final long serialVersionUID = -8062368253187733483L;

  @Autowired
  private TourService tourService;

  private List<Booking> bookList = new ArrayList<Booking>();

  private String tourNo;

  /** 部门 */
  private String kenDepartmentId;

  /** 专管员 */
  private String kenEmployeeId;

  /** 线路名 */
  private String kenRouteName;

  /** 出团日期 -开始 */
  private Date kenStartDate;

  /** 出团日期 -截止 */
  private Date kenEndDate;

  /** 建团日期 -开始 */
  private Date kenBuildStartDate;

  /** 建团日期 -截止 */
  private Date kenBuildEndDate;

  /** 建团人 */
  private String kenCreator;

  /**
   * 修改应收款初始化
   * 
   * @return
   */
  public String input() {
    bookList = tourService.roGetBookList(tourNo);

    return INPUT;
  }

  /**
   * 修改应收款
   * 
   * @return
   */
  public String mustPayModify() {
    Employee user = getUser();
    bookList = tourService.txMustPayModify(bookList, user.getUserId());
    int i = 0;
    for (Booking book : bookList) {
      if (book.getIsSuccess() == 1)
        i++;

    }
    if (i > 0)
      addActionMessage("修改保存成功！");
    else
      addActionMessage("修改保存失败！");

    return SUCCESS;
  }

  public List<Booking> getBookList() {
    return bookList;
  }

  public void setBookList(List<Booking> bookList) {
    this.bookList = bookList;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public String getKenDepartmentId() {
    return kenDepartmentId;
  }

  public void setKenDepartmentId(String kenDepartmentId) {
    this.kenDepartmentId = kenDepartmentId;
  }

  public String getKenEmployeeId() {
    return kenEmployeeId;
  }

  public void setKenEmployeeId(String kenEmployeeId) {
    this.kenEmployeeId = kenEmployeeId;
  }

  public String getKenRouteName() {
    return kenRouteName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenRouteName = kenRouteName;
  }

  public Date getKenStartDate() {
    return kenStartDate;
  }

  public void setKenStartDate(Date kenStartDate) {
    this.kenStartDate = kenStartDate;
  }

  public Date getKenEndDate() {
    return kenEndDate;
  }

  public void setKenEndDate(Date kenEndDate) {
    this.kenEndDate = kenEndDate;
  }

  public Date getKenBuildStartDate() {
    return kenBuildStartDate;
  }

  public void setKenBuildStartDate(Date kenBuildStartDate) {
    this.kenBuildStartDate = kenBuildStartDate;
  }

  public Date getKenBuildEndDate() {
    return kenBuildEndDate;
  }

  public void setKenBuildEndDate(Date kenBuildEndDate) {
    this.kenBuildEndDate = kenBuildEndDate;
  }

  public String getKenCreator() {
    return kenCreator;
  }

  public void setKenCreator(String kenCreator) {
    this.kenCreator = kenCreator;
  }

}
