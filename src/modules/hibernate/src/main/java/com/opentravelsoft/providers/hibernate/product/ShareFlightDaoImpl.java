package com.opentravelsoft.providers.hibernate.product;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.ShareFlight;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.ShareFlightDao;
import com.opentravelsoft.util.RowDataUtil;

@Repository("ShareFlightDao")
public class ShareFlightDaoImpl extends GenericDaoHibernate<ShareFlight, Long>
    implements ShareFlightDao {

  public ShareFlightDaoImpl() {
    super(ShareFlight.class);
  }

  public int editShareFlight(ShareFlight shareFlight) {
    ShareFlight sf = (ShareFlight) getHibernateTemplate().get(
        ShareFlight.class, shareFlight.getShareFlightId(),
        LockMode.PESSIMISTIC_WRITE);
    if (null == sf) {
      sf = new ShareFlight();
    } else {
      sf.setShareFlightId(shareFlight.getShareFlightId());
    }
    sf.setFlightNo(shareFlight.getFlightNo());
    sf.setAirwaysCode(shareFlight.getAirwaysCode());
    sf.setDepartureDate(shareFlight.getDepartureDate());
    sf.setSeating(shareFlight.getSeating());
    sf.setHandle(shareFlight.getHandle());
    sf.setNote(shareFlight.getNote());

    getHibernateTemplate().saveOrUpdate(sf);
    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<ShareFlight> findAll() {
    StringBuilder sb = new StringBuilder();
    sb.append("select A.shareFlightId,A.flightNo,A.airwaysCode,");
    sb.append("A.departureDate,A.seating,A.handle,A.note,B.name ");
    sb.append("from ShareFlight A,");
    sb.append("Airways B ");
    sb.append("where A.airwaysCode = B.code ");
    sb.append("order by A.shareFlightId desc ");
    List<Object[]> list = getHibernateTemplate().find(sb.toString());
    List<ShareFlight> sf = new ArrayList<ShareFlight>();
    ShareFlight shareFlight;
    if (!list.isEmpty()) {
      for (Object[] obj : list) {
        shareFlight = new ShareFlight();
        shareFlight.setShareFlightId(RowDataUtil.getInt(obj[0]));
        shareFlight.setFlightNo(RowDataUtil.getString(obj[1]));
        shareFlight.setAirwaysCode(RowDataUtil.getString(obj[2]));
        shareFlight.setDepartureDate(RowDataUtil.getDate(obj[3]));
        shareFlight.setSeating(RowDataUtil.getInt(obj[4]));
        shareFlight.setHandle(RowDataUtil.getInt(obj[5]));
        shareFlight.setNote(RowDataUtil.getString(obj[6]));

        shareFlight.setAirwaysName(RowDataUtil.getString(obj[7]));

        sf.add(shareFlight);
      }
    }
    return sf;
  }

}
