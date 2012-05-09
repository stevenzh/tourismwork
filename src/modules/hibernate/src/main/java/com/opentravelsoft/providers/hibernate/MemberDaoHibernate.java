package com.opentravelsoft.providers.hibernate;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Member;
import com.opentravelsoft.providers.MemberDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

import javax.persistence.Table;

import java.util.Calendar;
import java.util.List;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 * 
 */
@Repository("memberDao")
public class MemberDaoHibernate extends GenericDaoHibernate<Member, Long>
    implements MemberDao
// , UserDetailsService
{

  /**
   * Constructor that sets the entity to User.class.
   */
  public MemberDaoHibernate() {
    super(Member.class);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public List<Member> getUsers() {
    return getHibernateTemplate().find(
        "from Member u order by upper(u.username)");
  }

  /**
   * {@inheritDoc}
   */
  public Member saveUser(Member user) {
    if (log.isDebugEnabled())
      log.debug("member's id: " + user.getId());
    getHibernateTemplate().saveOrUpdate(user);
    // necessary to throw a DataIntegrityViolation and catch it in
    // UserManager
    getHibernateTemplate().flush();
    return user;
  }

  /**
   * Overridden simply to call the saveUser method. This is happenening because
   * saveUser flushes the session and saveObject of BaseDaoHibernate does not.
   * 
   * @param member the user to save
   * @return the modified user (with a primary key set if they're new)
   */
  @Override
  public Member save(Member member) {
    return this.saveUser(member);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    List<Member> users = getHibernateTemplate().find(
        "from Member where username=?", username);
    if (users == null || users.isEmpty()) {
      throw new UsernameNotFoundException("member '" + username
          + "' not found...");
    } else {
      return (UserDetails) users.get(0);
    }
  }

  /**
   * {@inheritDoc}
   */
  public String getUserPassword(String username) {
    SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(
        SessionFactoryUtils.getDataSource(getSessionFactory()));
    Table table = AnnotationUtils.findAnnotation(Member.class, Table.class);
    return jdbcTemplate.queryForObject("select password from " + table.name()
        + " where username=?", String.class, username);

  }

  // ---------------------------------------------------------------
  public int register(Member customer) {
    Member member = new Member();

    member.setPassword(customer.getPassword());
    member.setUsername(customer.getUsername());
    member.setMobile(customer.getMobile());
    member.setEmail(customer.getEmail());
    member.setIdCard(customer.getIdCard());
    member.setSex(customer.getSex());
    // member.setAddress(RowDataUtil.getString(customer.getAddress()));

    member.setBirthday(customer.getBirthday());
    member.setPhone(RowDataUtil.getString(customer.getPhone()));
    member.setFax(RowDataUtil.getString(customer.getFax()));
    member.setNation(RowDataUtil.getString(customer.getNation()));
    member.setProvince(RowDataUtil.getString(customer.getProvince()));
    member.setCity(RowDataUtil.getString(customer.getCity()));
    member.setPostcode(RowDataUtil.getString(customer.getPostcode()));
    member.setReceiveMail(customer.getReceiveMail());
    member.setVocation(customer.getVocation());
    member.setEducate(customer.getEducate());
    member.setPersonalIncome(customer.getPersonalIncome());
    member.setHouseholdIncome(customer.getHouseholdIncome());
    member.setTripTimes(customer.getTripTimes());
    member.setExpendOneTrip(customer.getExpendOneTrip());
    member.setMemberKey("Y");

    // 会员卡号
    member.setCardNo(customer.getCardNo());

    getHibernateTemplate().save(member);

    return 0;
  }

  @SuppressWarnings("unchecked")
  public Member getMemberById(long uid) {
    Object[] obj1 = { uid };
    StringBuilder sql = new StringBuilder();
    sql.append("from Member where id = ?");
    List<Member> members = getHibernateTemplate().find(sql.toString(), obj1);

    Member customer = null;
    if (members.size() > 0) {
      customer = members.get(0);
    }

    return customer;
  }

  @SuppressWarnings("unchecked")
  public Member getMemberByName(String userName) {
    Object[] obj1 = { userName };
    StringBuilder sql = new StringBuilder();
    sql.append("from Member where username = ?");
    List<Member> members = getHibernateTemplate().find(sql.toString(), obj1);

    Member customer = null;
    if (members.size() > 0) {
      customer = members.get(0);
    }

    return customer;
  }

  @SuppressWarnings("unchecked")
  public int update(Member customer) {
    Object[] obj1 = { customer.getUsername() };
    StringBuilder sql = new StringBuilder();
    sql.append("from Member where username = ?");
    List<Member> members = getHibernateTemplate().find(sql.toString(), obj1);

    if (members.size() > 0) {

      Member member = members.get(0);
      if (StringUtil.hasLength(customer.getPassword()))
        member.setPassword(customer.getPassword());
      member.setUsername(customer.getUsername());
      member.setMobile(customer.getMobile());
      member.setEmail(customer.getEmail());
      member.setIdCard(customer.getIdCard());
      member.setSex(customer.getSex());
      // member.setAddress(RowDataUtil.getString(customer.getAddress()));

      member.setBirthday(customer.getBirthday());
      member.setPhone(RowDataUtil.getString(customer.getPhone()));
      member.setFax(RowDataUtil.getString(customer.getFax()));
      member.setNation(RowDataUtil.getString(customer.getNation()));
      member.setProvince(RowDataUtil.getString(customer.getProvince()));
      member.setCity(RowDataUtil.getString(customer.getCity()));
      member.setPostcode(RowDataUtil.getString(customer.getPostcode()));
      member.setReceiveMail(customer.getReceiveMail());
      member.setVocation(customer.getVocation());
      member.setEducate(customer.getEducate());
      member.setMemberKey("Y");

      // 会员卡号
      member.setCardNo(customer.getCardNo());

      getHibernateTemplate().update(member);
    }
    return 0;
  }

  @SuppressWarnings("unchecked")
  public String validUid(String uid) {
    Object[] obj1 = { uid };
    StringBuilder sql = new StringBuilder();
    sql.append("from Member where username = ?");
    List<Member> members = getHibernateTemplate().find(sql.toString(), obj1);

    if (members.size() > 0) {
      return "false";
    }
    return "true";
  }

  @SuppressWarnings("unchecked")
  public String getMemberPwd(String mobileNo) {
    Object[] params = { mobileNo };
    Member member = null;
    StringBuilder sql = new StringBuilder();
    sql.append("from Member where mobile = ?");
    List<Member> members = getHibernateTemplate().find(sql.toString(), params);
    if (members.size() > 0) {
      member = members.get(0);
      Calendar cal1 = Calendar.getInstance();
      Calendar cal2 = Calendar.getInstance();
      // Date sysdate = getSysdate(getSession());

      if (null == member.getSendPwdDate()) {
        // member.setSendPwdDate(sysdate);
        getHibernateTemplate().update(member);
        return member.getPassword();
      }

      cal1.setTime(member.getSendPwdDate());
      // cal2.setTime(sysdate);

      if (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
          && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
          && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
        // the same day
        return null;
      } else {
        // member.setSendPwdDate(sysdate);
        getHibernateTemplate().update(member);
        return member.getPassword();
      }
    }

    return null;
  }

  public int modifyPassword(Member customer) {
    Member member = new Member();
    member.setPassword(customer.getConfirmPassword());
    member.setMemberKey("Y");

    getHibernateTemplate().update(member);

    return 0;
  }

}
