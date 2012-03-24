package com.opentravelsoft.action.booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.util.StringUtils;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.util.ConvertUtils;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 订单查询
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:06 $
 */
public class BookSearchAction extends PortalAction
{

    private static final long serialVersionUID = -7625855842902512964L;

    protected static final Log logger = LogFactory
            .getLog(BookSearchAction.class);

    private BookingService bookService;

    private List<Booking> bookList = new ArrayList<Booking>();

    /** 线路名 */
    private String lineName;

    /** 出发日期 起始时间 */
    private Date startDatePeriod;

    /** 出发日期 截止时间 */
    private Date endDatePeriod;

    /** 预订日期 起始时间 */
    private Date reserveStartDatePeriod;

    /** 预订日期 截止时间 */
    private Date reserveEndDatePeriod;

    /** 合同号 */
    private String contractNo;

    /** 发票号 */
    private String invoiceNo;

    /** 客人姓名 */
    private String touristName;

    /**
     * 订单状态
     * <p>
     * 1:已审核 2:未审核 %:所有
     */
    private String bookState;

    /**
     * 取消标记
     * <p>
     * Y: 已取消 N:未取消 %:所有
     */
    private String cancelState;

    private List<LabelValueBean> bookStateList;

    private List<LabelValueBean> cancelStateList;

    private List<LabelValueBean> outCityList = new ArrayList<LabelValueBean>();

    private String outCity = "All";

    private int searchType = 0;

    public void setBookService(BookingService bookService)
    {
        this.bookService = bookService;
    }

    public String input() throws Exception
    {
        bookStateList = getCodeList("ebiz_book_state");
        cancelStateList = getCodeList("ebiz_cancel_state");
        bookState = "%";
        cancelState = "%";
        buildSysdate();

        return SUCCESS;
    }

    public void setBookingService(BookingService bookService)
    {
        this.bookService = bookService;
    }

    /**
     * 提交查询
     * 
     * @return
     */
    public String submit()
    {
        bookStateList = getCodeList("ebiz_book_state");
        cancelStateList = getCodeList("ebiz_cancel_state");
        outCityList = getCodeList("ebiz_outcity_sky");

        List<LabelValueBean> confirmStatus = getCodeList("ebiz_confirm_status");
        Map<String, String> map = ConvertUtils.beansToMap(confirmStatus);
        bookList = bookService.roFind(lineName, startDatePeriod, endDatePeriod,
                reserveStartDatePeriod, reserveStartDatePeriod, contractNo,
                invoiceNo, touristName, bookState, cancelState);
        for (Booking book : bookList)
        {
            book.setConfirmStatus(map.get(book.getConfirmStatus()));
        }

        buildSysdate();

        return SUCCESS;
    }

    public List<Booking> getBookList()
    {
        return bookList;
    }

    public String getRouteName()
    {
        return lineName;
    }

    public void setRouteName(String routeName)
    {
        this.lineName = routeName;
    }

    public Date getStartDatePeriod()
    {
        return startDatePeriod;
    }

    public void setStartDatePeriod(Date startDatePeriod)
    {
        this.startDatePeriod = startDatePeriod;
    }

    public Date getEndDatePeriod()
    {
        return endDatePeriod;
    }

    public void setEndDatePeriod(Date endDatePeriod)
    {
        this.endDatePeriod = endDatePeriod;
    }

    public Date getReserveEndDatePeriod()
    {
        return reserveEndDatePeriod;
    }

    public void setReserveEndDatePeriod(Date reserveEndDatePeriod)
    {
        this.reserveEndDatePeriod = reserveEndDatePeriod;
    }

    public Date getReserveStartDatePeriod()
    {
        return reserveStartDatePeriod;
    }

    public void setReserveStartDatePeriod(Date reserveStartDatePeriod)
    {
        this.reserveStartDatePeriod = reserveStartDatePeriod;
    }

    public String getContractNo()
    {
        return contractNo;
    }

    public void setContractNo(String contractNo)
    {
        this.contractNo = contractNo;
    }

    public String getInvoiceNo()
    {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo)
    {
        this.invoiceNo = invoiceNo;
    }

    public String getTouristName()
    {
        return touristName;
    }

    public void setTouristName(String touristName)
    {
        this.touristName = touristName;
    }

    public String getBookState()
    {
        return bookState;
    }

    public void setBookState(String bookState)
    {
        this.bookState = bookState;
    }

    public String getCancelState()
    {
        return cancelState;
    }

    public void setCancelState(String cancelState)
    {
        this.cancelState = cancelState;
    }

    public List<LabelValueBean> getBookStateList()
    {
        return bookStateList;
    }

    public List<LabelValueBean> getCancelStateList()
    {
        return cancelStateList;
    }

    public List<LabelValueBean> getOutCityList()
    {
        return outCityList;
    }

    public String getOutCity()
    {
        return outCity;
    }

    public int getSearchType()
    {
        return searchType;
    }

    private BookingService bookingService;

    private String kenOrderNo;

    private String kenTourist;

    private double kenAccount;

    private List<Booking> bookings;

    @Override
    public String execute() throws Exception
    {
        if (!StringUtils.hasLength(kenOrderNo)
                && !StringUtils.hasLength(kenTourist)
                || (!StringUtils.hasLength(kenOrderNo) && kenAccount == 0)
                || (!StringUtils.hasLength(kenTourist) && kenAccount == 0))
        {
            addActionMessage("至少填写两条搜索条件.");
            return INPUT;
        }

        bookings = bookingService.roSearch(kenOrderNo, kenTourist, kenAccount);
        return SUCCESS;
    }

    public String getKenOrderNo()
    {
        return kenOrderNo;
    }

    public void setKenOrderNo(String kenOrderNo)
    {
        this.kenOrderNo = kenOrderNo;
    }

    public String getKenTourist()
    {
        return kenTourist;
    }

    public void setKenTourist(String kenTourist)
    {
        this.kenTourist = kenTourist;
    }

    public double getKenAccount()
    {
        return kenAccount;
    }

    public void setKenAccount(double kenAccount)
    {
        this.kenAccount = kenAccount;
    }

    public List<Booking> getBookings()
    {
        return bookings;
    }
}
