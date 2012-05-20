package com.opentravelsoft.providers.hibernate;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Sequence;
import com.opentravelsoft.entity.SequenceId;
import com.opentravelsoft.providers.SequenceDao;
import com.opentravelsoft.util.StringUtil;

@Repository("SequenceDao")
public class SequenceDaoHibernate extends
    GenericDaoHibernate<Sequence, SequenceId> implements SequenceDao {
  public SequenceDaoHibernate() {
    super(Sequence.class);
  }

  /**
   * 
   * <ul>
   * <li>A - 报名单号 10位 YYMM999999
   * <li>B - 酒店、公司客户等 8位 YMM99999
   * <li>H - 名单号 10位 YYMM999999
   * <li>I - 送签登记表号 8位 YMM99999
   * <li>J - 现金支票流水帐号 9位 YYMM9999D
   * <li>K - 订票计划号 9位 YYMM999999
   * <li>L - 订票计划航段号 10位 YYMM999999
   * <li>M - 收据存根编号 10位 YYMM999999
   * <li>N - 客人名单打印编号 10位 YYMM999999
   * <li>O - 公告序号 10位 YYMM999999
   * <li>P - 领队序号 10位 LYYMM99999
   * <li>Q - 通用编号一 10位 YYMM999999
   * <li>T - 线路号 8位 YYMM9999
   * <li>R - 会员记录号 10位 YYMM999999
   * <li>V - 询价单号 10位 YYMM999999
   * <li>W - 订单号 10位 YYMM999999
   * <li>Y - 会员号（新） 10位 YYMM999999
   * <li>Z - 通用记录号二 10位 YYMM999999
   * </ul>
   */
  public String getComputerNo(String type, int groupId) {
    return getComputerNo(type, 1, groupId)[0];
  }

  public String[] getComputerNo(String type, int count, int groupId) {
    String[] cnos = new String[count];
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;

    String sYear = Integer.toString(year);
    String sMonth = Integer.toString(month);
    Integer num = 1;
    if (sMonth.trim().length() == 1) {
      sMonth = "0" + sMonth.trim();
    }
    if (type.equals("E") || type.equals("X")) {
      //
    } else {
      SequenceId tfj107Id = new SequenceId();
      tfj107Id.setRectype(type);
      tfj107Id.setYear(sYear);
      tfj107Id.setMonth(sMonth);

      HibernateTemplate template = getHibernateTemplate();

      Sequence seqList = (Sequence) template.get(Sequence.class, tfj107Id,
          LockMode.PESSIMISTIC_WRITE);

      if (null == seqList) {

        Sequence seq = new Sequence();

        seq.setId(tfj107Id);
        // seq.setRegPlcd('');
        seq.setCptno(num);
        getHibernateTemplate().save(seq);
      } else {
        num = seqList.getCptno() + 1;
        seqList.setCptno(seqList.getCptno() + count);
        template.saveOrUpdate(seqList);
      }
      sYear = sYear.substring(2, 4);

      for (int i = 0; i < count; i++) {
        String ComputerNo = "";
        if (type.equals("T")) { // 线路号
          ComputerNo = sYear + sMonth
              + StringUtil.padding(String.valueOf(num), 4);
        } else if (type.equals("B") || type.equals("I")) { // 送签登记表号、线路号
          String strMonth = "";
          if (sMonth.equals("01")) {
            strMonth = "A";
          } else if (sMonth.equals("02")) {
            strMonth = "B";
          } else if (sMonth.equals("03")) {
            strMonth = "C";
          } else if (sMonth.equals("04")) {
            strMonth = "D";
          } else if (sMonth.equals("05")) {
            strMonth = "E";
          } else if (sMonth.equals("06")) {
            strMonth = "F";
          } else if (sMonth.equals("07")) {
            strMonth = "G";
          } else if (sMonth.equals("08")) {
            strMonth = "H";
          } else if (sMonth.equals("09")) {
            strMonth = "I";
          } else if (sMonth.equals("10")) {
            strMonth = "J";
          } else if (sMonth.equals("11")) {
            strMonth = "K";
          } else if (sMonth.equals("12")) {
            strMonth = "M";
          }
          ComputerNo = sYear + strMonth
              + StringUtil.padding(String.valueOf(num), 5);
        } else if (type.equals("A") || type.equals("H") || type.equals("J")
            || type.equals("K") || type.equals("L") || type.equals("M")
            || type.equals("N") || type.equals("O") || type.equals("Q")
            || type.equals("R") || type.equals("V") || type.equals("W")
            || type.equals("Y") || type.equals("Z") || type.equals("D")) {
          ComputerNo = sYear + sMonth
              + StringUtil.padding(String.valueOf(num), 6);
        }
        num++;
        cnos[i] = ComputerNo;
      }
    }

    return cnos;
  }
}
