package com.opentravelsoft.report.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.service.product.LineService;

/**
 * 线路打印 （PDF输出）
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class LineReportAction extends ActionSupport {

  private static final long serialVersionUID = -7783734455602162689L;

  private LineService routeService;

  private String lineNo;

  @Autowired
  public void setRouteService(LineService routeService) {
    this.routeService = routeService;
  }

  public String getRouteNo() {
    return lineNo;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Override
  public String execute() throws Exception {
    Line route = routeService.roGetRouteInfo(lineNo);

    HttpServletResponse response = ServletActionContext.getResponse();
    Document document = new Document();

    try {
      response.setContentType("application/pdf");
      PdfWriter.getInstance(document, response.getOutputStream());
      BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
          BaseFont.NOT_EMBEDDED);
      Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);

      document.open();

      PdfPTable table = new PdfPTable(1);
      Paragraph p = new Paragraph(route.getLineName(), FontChinese);
      table.addCell(p);
      document.add(table);

      // step4
      table = new PdfPTable(3);
      List<LineSchedule> sc = route.getSchedule();
      PdfPCell cell = new PdfPCell(new Paragraph("行程：", FontChinese));
      cell.setColspan(3);

      table.addCell(cell);

      for (LineSchedule schedule : sc) {
        table.addCell(new Paragraph("第"
            + String.valueOf(schedule.getId().getDay()) + "天", FontChinese));
        table.addCell(new Paragraph(schedule.getTraffic(), FontChinese));
        table.addCell("1");

        table.addCell("1");
        cell = new PdfPCell(new Paragraph(schedule.getProgram(), FontChinese));
        cell.setColspan(2);
        table.addCell(cell);
      }

      document.add(new Paragraph("We add 2 tables:"));
      document.add(table);

    } catch (DocumentException de) {
      de.printStackTrace();
      System.err.println("document: " + de.getMessage());
    }

    document.close();

    return NONE;
  }

}
