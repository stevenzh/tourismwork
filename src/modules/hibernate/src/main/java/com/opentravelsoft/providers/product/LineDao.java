package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.PortalCategory;
import com.opentravelsoft.providers.GenericDao;
import com.opentravelsoft.util.PaginationSupport;

/**
 * 线路基本信息维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
public interface LineDao extends GenericDao<Line, String> {

  /**
   * 
   * @param teamId
   * @param lineName
   * @param isActive
   * @param userId
   * @param destination
   * @param fromRecord
   * @param pageSize
   * @return
   */
  public PaginationSupport findLineList(int teamId, String lineName,
      String isActive, int userId, String destination, long fromRecord,
      int pageSize);

  /**
   * 取消线路
   * 
   * @param lineNo
   * @return
   */
  public int cancelLine(String lineNo);

  /**
   * 
   * @param line
   * @return
   */
  public int updateLine(Line line);

  /**
   * 
   * @param lineNo
   * @param newLineNo
   * @param newLineName
   * @param copyFeature
   * @param copySchedule
   * @param copyPrice
   * @param copyDestination
   * @param copySight
   * @param copyVisa
   * @param userId
   * @return
   */
  public int duplicateRoute(String lineNo, String newLineNo,
      String newLineName, String copyFeature, String copySchedule,
      String copyPrice, String copyDestination, String copySight,
      String copyVisa, Integer userId);

  public List<PortalCategory> getWebNavigation();

}
