package com.opentravelsoft.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.opentravelsoft.util.LabelValueBean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents the basic "user" object in Opentravelsoft that allows
 * for authentication and user management. It implements Acegi Security's
 * UserDetails interface.
 * 
 */
@Entity
@Table(name = "tbl_member")
public class Member extends BaseObject implements Serializable, UserDetails {
  private static final long serialVersionUID = 3832626162173359411L;

  private Long id;

  private String username; // required

  private String password; // required

  private String confirmPassword;

  private String passwordHint;

  private String firstName; // required

  private String lastName; // required

  private String email; // required; unique

  private String phoneNumber;

  private String website;

  private Address address = new Address();

  private Integer version;

  private Set<PortalRole> roles = new HashSet<PortalRole>();

  private boolean enabled;

  private boolean accountExpired;

  private boolean accountLocked;

  private boolean credentialsExpired;

  /**
   * Default constructor - creates a new instance with no values set.
   */
  public Member() {
    receiveMail = "N";
    score = 0;
    memberKey = "N";
  }

  /**
   * Create a new instance and set the username.
   * 
   * @param username
   *          login name for user.
   */
  public Member(final String username) {
    this.username = username;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @SearchableId
  public Long getId() {
    return id;
  }

  @Column(nullable = false, length = 50, unique = true)
  @SearchableProperty
  public String getUsername() {
    return username;
  }

  @Column(nullable = false)
  public String getPassword() {
    return password;
  }

  @Transient
  public String getConfirmPassword() {
    return confirmPassword;
  }

  @Column(name = "password_hint")
  public String getPasswordHint() {
    return passwordHint;
  }

  @Column(name = "first_name", nullable = false, length = 50)
  @SearchableProperty
  public String getFirstName() {
    return firstName;
  }

  @Column(name = "last_name", nullable = false, length = 50)
  @SearchableProperty
  public String getLastName() {
    return lastName;
  }

  @Column(nullable = false, unique = true)
  @SearchableProperty
  public String getEmail() {
    return email;
  }

  @Column(name = "phone_number")
  @SearchableProperty
  public String getPhoneNumber() {
    return phoneNumber;
  }

  @SearchableProperty
  public String getWebsite() {
    return website;
  }

  /**
   * Returns the full name.
   * 
   * @return firstName + ' ' + lastName
   */
  @Transient
  public String getFullName() {
    return firstName + ' ' + lastName;
  }

  @Embedded
  @SearchableComponent
  public Address getAddress() {
    return address;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "tbl_member_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = @JoinColumn(name = "role_id"))
  public Set<PortalRole> getRoles() {
    return roles;
  }

  /**
   * Convert user roles to LabelValue objects for convenience.
   * 
   * @return a list of LabelValue objects with role information
   */
  @Transient
  public List<LabelValueBean> getRoleList() {
    List<LabelValueBean> userRoles = new ArrayList<LabelValueBean>();

    if (this.roles != null) {
      for (PortalRole role : roles) {
        // convert the user's roles to LabelValue Objects
        userRoles.add(new LabelValueBean(role.getName(), role.getName()));
      }
    }

    return userRoles;
  }

  /**
   * Adds a role for the user
   * 
   * @param role
   *          the fully instantiated role
   */
  public void addRole(PortalRole role) {
    getRoles().add(role);
  }

  /**
   * @return GrantedAuthority[] an array of roles.
   * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
   */
  @Transient
  public Collection<GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(
        roles.size());
    for (PortalRole role : roles) {
      grantedAuthorities.add(new GrantedAuthorityImpl(role.getName()));
    }
    return grantedAuthorities;

  }

  @Version
  public Integer getVersion() {
    return version;
  }

  @Column(name = "account_enabled")
  public boolean isEnabled() {
    return enabled;
  }

  @Column(name = "account_expired", nullable = false)
  public boolean isAccountExpired() {
    return accountExpired;
  }

  /**
   * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
   */
  @Transient
  public boolean isAccountNonExpired() {
    return !isAccountExpired();
  }

  @Column(name = "account_locked", nullable = false)
  public boolean isAccountLocked() {
    return accountLocked;
  }

  /**
   * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
   */
  @Transient
  public boolean isAccountNonLocked() {
    return !isAccountLocked();
  }

  @Column(name = "credentials_expired", nullable = false)
  public boolean isCredentialsExpired() {
    return credentialsExpired;
  }

  /**
   * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
   */
  @Transient
  public boolean isCredentialsNonExpired() {
    return !credentialsExpired;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public void setPasswordHint(String passwordHint) {
    this.passwordHint = passwordHint;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public void setRoles(Set<PortalRole> roles) {
    this.roles = roles;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setAccountExpired(boolean accountExpired) {
    this.accountExpired = accountExpired;
  }

  public void setAccountLocked(boolean accountLocked) {
    this.accountLocked = accountLocked;
  }

  public void setCredentialsExpired(boolean credentialsExpired) {
    this.credentialsExpired = credentialsExpired;
  }

  /**
   * {@inheritDoc}
   */
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Member)) {
      return false;
    }

    final Member user = (Member) o;

    return !(username != null ? !username.equals(user.getUsername()) : user
        .getUsername() != null);
  }

  /**
   * {@inheritDoc}
   */
  public int hashCode() {
    return (username != null ? username.hashCode() : 0);
  }

  /**
   * {@inheritDoc}
   */
  public String toString() {
    ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
        .append("username", this.username)
        .append("enabled", this.enabled)
        .append("accountExpired", this.accountExpired)
        .append("credentialsExpired", this.credentialsExpired)
        .append("accountLocked", this.accountLocked);

    Collection<GrantedAuthority> auths = this.getAuthorities();
    if (auths.size() != 0) {
      sb.append("Granted Authorities: ");

      int i = 0;
      for (GrantedAuthority grantedAuthority : auths) {
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(grantedAuthority.toString());
        i++;
      }
    } else {
      sb.append("No Granted Authorities");
    }
    return sb.toString();
  }

  // ---------------------------------------------------------------

  /** 会员卡号 */
  private String cardNo;

  /** 真实姓名 */
  private String realName;

  /** 姓名的拼音 */
  private String pinYin;

  /** 身份证号 */
  private String idCard;

  /** 性别 */
  private String sex;

  /** 出生年月 */
  private Date birthday;

  /** 出生地 */
  private String birthplace;

  private String birthplaceName;

  /** 年龄 */
  private int age;

  /** 电话 */
  private String phone;

  /** 手机号 */
  private String mobile;

  /** 传真 */
  private String fax;

  /** 邮政编码 */
  private String postcode;

  /** 国家 */
  private String nation;

  /** 省份 */
  private String province;

  /** 城市 */
  private String city;

  /** 职业 */
  private String vocation;

  /** 教育程度 */
  private String educate;

  /** 每月家庭收入 */
  private String householdIncome;

  /** 每月个人收入 */
  private String personalIncome;

  /** 平均每年外游次数 */
  private String tripTimes;

  /** 每次旅行的平均花费 */
  private String expendOneTrip;

  /** 喜欢的旅游地点 */
  private String yearningTo;

  /** 是否接收E-MAIL 社内即时的特价促销信息、新推线路等等 */
  private String receiveMail;

  /** 积分 */
  private int score;

  /** 是否是会员 */
  private String memberKey;

  // -------------------------------------------------------------------------
  // 参团客人

  /**
   * 证件类型
   */
  private String cardType;

  /** 证件号码 */
  private String card;

  /** 籍贯 */
  private String homeplace;

  /** 民族 */
  private String people;

  // -------------------------------------------------------------------------
  /** 护照类型 */
  private String passportType;

  /** 护照号 */
  private String passportNo;

  /** 护照签发日期 */
  private Date passportDate;

  /** 护照有效期 */
  private Date passportExpiry;

  /** 护照签发地ID */
  private String passportPlace;

  /** 护照签发地名称 */
  private String passportPlaceName;

  /** 护照说明 */
  private String passportAnnotation;

  // -------------------------------------------------------------------------
  // 旅游信息
  /** VIP客人 */
  private String vip;

  /** 等级 */
  private String grade;

  private Date sendPwdDate;

  private Date lastLogindate = null;

  // ---------------------------------------------------------------

  @Transient
  public String getCardNo() {
    return cardNo;
  }

  @Transient
  public String getCity() {
    return city;
  }

  @Transient
  public String getNation() {
    return nation;
  }

  @Transient
  public String getEducate() {
    return educate;
  }

  @Transient
  public String getFax() {
    return fax;
  }

  @Transient
  public String getIdCard() {
    return idCard;
  }

  @Transient
  public String getPhone() {
    return phone;
  }

  @Transient
  public String getMobile() {
    return mobile;
  }

  @Transient
  public String getPostcode() {
    return postcode;
  }

  @Transient
  public String getProvince() {
    return province;
  }

  @Transient
  public String getRealName() {
    return realName;
  }

  @Transient
  public String getVocation() {
    return vocation;
  }

  @Transient
  public String getSex() {
    return sex;
  }

  @Transient
  public Date getBirthday() {
    return birthday;
  }

  @Transient
  public String getBirthplace() {
    return birthplace;
  }

  @Transient
  public String getReceiveMail() {
    return receiveMail;
  }

  @Transient
  public String getCard() {
    return card;
  }

  @Transient
  public String getHomeplace() {
    return homeplace;
  }

  @Transient
  public Date getPassportDate() {
    return passportDate;
  }

  @Transient
  public String getPassportNo() {
    return passportNo;
  }

  @Transient
  public String getPassportPlace() {
    return passportPlace;
  }

  @Transient
  public String getPeople() {
    return people;
  }

  @Transient
  public int getScore() {
    return score;
  }

  @Transient
  public String getGrade() {
    return grade;
  }

  @Transient
  public String getVip() {
    return vip;
  }

  @Transient
  public Date getLastLogindate() {
    return lastLogindate;
  }

  @Transient
  public String getExpendOneTrip() {
    return expendOneTrip;
  }

  @Transient
  public String getHouseholdIncome() {
    return householdIncome;
  }

  @Transient
  public String getTripTimes() {
    return tripTimes;
  }

  @Transient
  public String getYearningTo() {
    return yearningTo;
  }

  @Transient
  public String getMemberKey() {
    return memberKey;
  }

  @Transient
  public String getPassportType() {
    return passportType;
  }

  @Transient
  public String getPassportAnnotation() {
    return passportAnnotation;
  }

  @Transient
  public String getPinYin() {
    return pinYin;
  }

  @Transient
  public String getCardType() {
    return cardType;
  }

  @Transient
  public int getAge() {
    return age;
  }

  @Transient
  public String getBirthplaceName() {
    return birthplaceName;
  }

  @Transient
  public String getPassportPlaceName() {
    return passportPlaceName;
  }

  @Transient
  public String getPersonalIncome() {
    return personalIncome;
  }

  @Transient
  public Date getPassportExpiry() {
    return passportExpiry;
  }

  @Transient
  public Date getSendPwdDate() {
    return sendPwdDate;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setNation(String nation) {
    this.nation = nation;
  }

  public void setEducate(String educate) {
    this.educate = educate;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public void setVocation(String vocation) {
    this.vocation = vocation;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public void setBirthplace(String birthplace) {
    this.birthplace = birthplace;
  }

  public void setReceiveMail(String receiveMail) {
    this.receiveMail = receiveMail;
  }

  public void setCard(String card) {
    this.card = card;
  }

  public void setHomeplace(String homeplace) {
    this.homeplace = homeplace;
  }

  public void setPassportDate(Date passportDate) {
    this.passportDate = passportDate;
  }

  public void setPassportNo(String passportNo) {
    this.passportNo = passportNo;
  }

  public void setPassportPlace(String passportPlace) {
    this.passportPlace = passportPlace;
  }

  public void setPeople(String people) {
    this.people = people;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public void setVip(String vip) {
    this.vip = vip;
  }

  public void setLastLogindate(Date lastLogindate) {
    this.lastLogindate = lastLogindate;
  }

  public void setExpendOneTrip(String expendOneTrip) {
    this.expendOneTrip = expendOneTrip;
  }

  public void setHouseholdIncome(String householdIncome) {
    this.householdIncome = householdIncome;
  }

  public void setPersonalIncome(String personalIncome) {
    this.personalIncome = personalIncome;
  }

  public void setTripTimes(String tripTimes) {
    this.tripTimes = tripTimes;
  }

  public void setYearningTo(String yearningTo) {
    this.yearningTo = yearningTo;
  }

  public void setMemberKey(String memberKey) {
    this.memberKey = memberKey;
  }

  public void setPassportType(String passportType) {
    this.passportType = passportType;
  }

  public void setPassportAnnotation(String passportAnnotation) {
    this.passportAnnotation = passportAnnotation;
  }

  public void setPinYin(String pinYin) {
    this.pinYin = pinYin;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setBirthplaceName(String birthplaceName) {
    this.birthplaceName = birthplaceName;
  }

  public void setPassportPlaceName(String passportPlaceName) {
    this.passportPlaceName = passportPlaceName;
  }

  public void setPassportExpiry(Date passportExpiry) {
    this.passportExpiry = passportExpiry;
  }

  public void setSendPwdDate(Date sendPwdDate) {
    this.sendPwdDate = sendPwdDate;
  }
}
