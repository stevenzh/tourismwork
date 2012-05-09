package com.opentravelsoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.opentravelsoft.common.EbizCommon;

/**
 * 员工
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.3 $ $Date: 2009/04/10 07:47:28 $
 */
public class Employee extends BaseObject implements Serializable, UserDetails {

  private static final long serialVersionUID = 2813004827824319294L;

  /** 用户ID */
  private long userId;

  /** 用户Code */
  private String uid;

  /** 密码 */
  private String passwd;

  /** 用户真实名称 */
  private String userName;

  private boolean isActive;

  private Collection<GrantedAuthority> authorities;

  // ---------------------------------------------------------------------------

  private String givenName;

  private String familyName;

  private String email;

  /** 用户类型 */
  private String userType;

  private Integer age;

  /** 出生年月 */
  private Date birthday;

  private String sex;

  /** 一个用户扮演一种或多种角色 */
  private Set<Role> memberships = new HashSet<Role>(0);

  private Group group;

  /** CTI 号码 */
  private String ctiNo;

  private String mobile;

  /** 电话 */
  private String phone;

  /** 传真 */
  private String fax;

  /** 营业部 */
  private String salesCd;

  private Integer discont;

  private String webKey;

  private String workFlg;

  /** 0-普通 2-销售 */
  private String workKey;

  private String msn;

  private String skype;

  private String opIp;

  /** 部门名 */
  private String departmentName;

  private boolean isAdmin;

  /** 权限 */
  private Set<String> roles;

  /** 权限 */
  private Set<Integer> roleids;

  /**
   * 所属目的地列表<BR>
   * 用于产品部门、和计调（OP）
   */
  private List<String> teamList;

  private Map<String, String> privilege = new TreeMap<String, String>();

  private Set<Line> lines = new HashSet<Line>(0);

  private Set<Integer> teams;

  private Set<Team> teamMemberships = new HashSet<Team>(0);

  // ---------------------------------------------------------------

  private Date lastLogindate = null;

  // -------------------------------------------------------------------------

  private String confirmPassword;

  /** 地址 */
  private String address;

  /** 邮政编码 */
  private String postcode;

  private String cardId;

  // -------------------------------------------------------------------------
  // 参团客人

  /**
   * 证件类型
   */
  private String cardType;

  // -------------------------------------------------------------------------

  // 旅游信息
  /** VIP客人 */
  private String vip;

  /** 等级 */
  private String grade;

  /** 旅游历史 */
  private List<Tourist> trips;

  private Date sendPwdDate;

  public Employee() {
    isActive = true;
    sex = "M";
    trips = new ArrayList<Tourist>();
    departmentName = "";
    roles = new TreeSet<String>();
    roleids = new HashSet<Integer>(0);
    teamList = new ArrayList<String>();
    teams = new HashSet<Integer>(0);
  }

  public Employee(long userId) {
    this();
    setUserId(userId);
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public String getUserCd() {
    return getUid();
  }

  public void setUserCd(String userCd) {
    setUid(userCd);
  }

  public Integer getDiscont() {
    return this.discont;
  }

  public void setDiscont(Integer discont) {
    this.discont = discont;
  }

  public String getWebKey() {
    return this.webKey;
  }

  public void setWebKey(String webKey) {
    this.webKey = webKey;
  }

  public String getWorkFlg() {
    return this.workFlg;
  }

  public void setWorkFlg(String workFlg) {
    this.workFlg = workFlg;
  }

  public String getMsn() {
    return this.msn;
  }

  public void setMsn(String msn) {
    this.msn = msn;
  }

  public String getSkype() {
    return this.skype;
  }

  public void setSkype(String skype) {
    this.skype = skype;
  }

  public String getOpIp() {
    return this.opIp;
  }

  public void setOpIp(String opIp) {
    this.opIp = opIp;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getSalesCd() {
    return salesCd;
  }

  public void setSalesCd(String salesCd) {
    this.salesCd = salesCd;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

  /**
   * 超级用户
   */
  public boolean isSuperUser() {
    return roles.contains(EbizCommon.ROLE_SUPERUSER);
  }

  public void setSuperUser(boolean hasRole) {
    roles.remove(EbizCommon.ROLE_SUPERUSER);
    if (hasRole)
      roles.add(EbizCommon.ROLE_SUPERUSER);
  }

  public Set<Integer> getRoleids() {
    return roleids;
  }

  public void setRoleids(Set<Integer> roleids) {
    this.roleids = roleids;
  }

  public String getCtiNo() {
    return ctiNo;
  }

  public void setCtiNo(String ctiNo) {
    this.ctiNo = ctiNo;
  }

  public List<String> getTeamList() {
    return teamList;
  }

  public void setTeamList(List<String> destinationList) {
    this.teamList = destinationList;
  }

  public Map<String, String> getPriv() {
    return privilege;
  }

  public void setPriv(Map<String, String> priv) {
    this.privilege = priv;
  }

  public Set<Line> getLines() {
    return lines;
  }

  public void setLines(Set<Line> lines) {
    this.lines = lines;
  }

  public String getWorkKey() {
    return workKey;
  }

  public void setWorkKey(String workKey) {
    this.workKey = workKey;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Set<Integer> getTeams() {
    return teams;
  }

  public void setTeams(Set<Integer> teams) {
    this.teams = teams;
  }

  public Set<Team> getTeamMemberships() {
    return teamMemberships;
  }

  public void setTeamMemberships(Set<Team> teamMemberships) {
    this.teamMemberships = teamMemberships;
  }

  public void addTeamMembership(Team membership) {
    if (teamMemberships == null)
      teamMemberships = new HashSet<Team>();
    teamMemberships.add(membership);
  }

  public boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public List<Tourist> getTrips() {
    return trips;
  }

  public void setTrips(List<Tourist> trips) {
    this.trips = trips;
  }

  public String getVip() {
    return vip;
  }

  public void setVip(String vip) {
    this.vip = vip;
  }

  public Date getLastLogindate() {
    return lastLogindate;
  }

  public void setLastLogindate(Date lastLogindate) {
    this.lastLogindate = lastLogindate;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public Date getSendPwdDate() {
    return sendPwdDate;
  }

  public void setSendPwdDate(Date sendPwdDate) {
    this.sendPwdDate = sendPwdDate;
  }

  public String getCardId() {
    return cardId;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean enabled) {
    this.isActive = enabled;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String firstName) {
    this.givenName = firstName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public Set<Role> getMemberships() {
    return memberships;
  }

  public void setMemberships(Set<Role> memberships) {
    this.memberships = memberships;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public void addMembership(Role membership) {
    if (memberships == null)
      memberships = new HashSet<Role>();
    memberships.add(membership);
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    return authorities;
  }
  
  public void setAuthorities(Collection<GrantedAuthority> has) {
    this.authorities = has;
  }
  

  @Override
  public String getPassword() {
    return passwd;
  }

  @Override
  public String getUsername() {
    return uid;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return isActive;
  }

  @Override
  public String toString() {
    return "User[UID:" + getUid() + " Enabled:" + getIsActive() + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Employee)) {
      return false;
    }

    final Employee user = (Employee) o;

    return this.hashCode() == user.hashCode();
  }

  @Override
  public int hashCode() {
    int result;
    result = (getUid() != null ? getUid().hashCode() : 0);
    result = 29 * result
        + (getUserName() != null ? getUserName().hashCode() : 0);
    return result;
  }

}
