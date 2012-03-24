package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.hibernate.LockMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.model.Role;
import com.opentravelsoft.providers.EmployeeDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

@Repository("EmployeeDao")
public class EmployeeDaoImpl extends GenericDaoHibernate<Employee, Long>
    implements EmployeeDao {

  public EmployeeDaoImpl() {
    super(Employee.class);
  }

  @SuppressWarnings("unchecked")
  public Employee getEmployeeByName(String userName) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Employee where userCd=? ");
    Object[] obj1 = { userName };
    List<Employee> list = getHibernateTemplate().find(sql.toString(), obj1);

    Employee emp = null;
    if (list.size() > 0) {
      emp = list.get(0);

      emp.setDepartmentName(emp.getGroup().getName());
      Set<String> st = new HashSet<String>();
      Set<Integer> in = new HashSet<Integer>();
      for (Role role : emp.getMemberships()) {
        in.add(RowDataUtil.getInt(role.getRoleId()));
        st.add(RowDataUtil.getString(role.getRoleCode()));
      }
      emp.setRoleids(in);
      emp.setRoles(st);

      // ----------------------------------------------------------------
      StringBuilder sql1 = new StringBuilder();
      sql1.append("select u.moduleName,a.permissionKey,p.allowAccess ");
      sql1.append("from ModulePermission p,");
      sql1.append("Employee m join m.memberships r,");
      sql1.append("Module u,");
      sql1.append("Permission a ");
      sql1.append("where r.roleId=p.roleId ");
      sql1.append("and u.moduleId=p.moduleId ");
      sql1.append("and a.permissionId=p.permissionId ");
      sql1.append("and m.userId=? ");

      Object[] param = { emp.getUserId() };
      List<Object[]> list2 = getHibernateTemplate()
          .find(sql1.toString(), param);
      Map<String, String> in1 = new TreeMap<String, String>();
      for (Object[] role : list2) {
        String key = RowDataUtil.getString(role[0]) + "_"
            + RowDataUtil.getString(role[1]).toLowerCase();
        if (in1.containsKey(key) && in1.get(key).equalsIgnoreCase("true"))
          continue;
        in1.put(key, role[2].toString());
      }
      emp.setPriv(in1);
    }

    return emp;
  }

  public Employee getEmployee(long userId) {
    Employee employee = getHibernateTemplate().get(Employee.class, userId);
    if (null != employee) {
      Set<String> st = new HashSet<String>();
      Set<Integer> in = new HashSet<Integer>();
      for (Role role : employee.getMemberships()) {
        in.add(RowDataUtil.getInt(role.getRoleId()));
        st.add(RowDataUtil.getString(role.getRoleCode()));
      }
      employee.setRoleids(in);
      employee.setRoles(st);

      Set<Integer> in1 = new HashSet<Integer>();
      for (Team team : employee.getTeamMemberships()) {
        in1.add(RowDataUtil.getInt(team.getTeamId()));
      }
      employee.setTeams(in1);
    }

    return employee;
  }

  @SuppressWarnings("unchecked")
  public List<Employee> getEmployees(int groupId, String userName) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
    if (groupId != 0)
      criteria.add(Restrictions.eq("group.groupId", groupId));
    if (StringUtil.hasLength(userName))
      criteria.add(Restrictions.like("userName", userName.trim(),
          MatchMode.ANYWHERE));

    criteria.addOrder(Order.asc("userCd"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  public int deleteEmployee(long userId) {
    Employee employee = (Employee) getHibernateTemplate().get(Employee.class,
        userId, LockMode.UPGRADE);

    if (employee == null) {
      return -1;
    }
    employee.setIsActive(false);
    getHibernateTemplate().update(employee);

    return 0;
  }

  @SuppressWarnings("unchecked")
  public int insertEmployee(Employee empl) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Employee where userCd=? ");
    Object[] obj1 = { empl.getUserName() };

    List<Employee> list = getHibernateTemplate().find(sql.toString(), obj1);

    if (list.size() == 0) {
      Employee employee = new Employee();
      employee.setUserId(empl.getUserId());
      employee.setUserCd(empl.getUid());
      employee.setUserName(empl.getUserName());
      employee.setPasswd(empl.getPasswd());
      employee.setPhone(empl.getPhone());
      employee.setFax(empl.getFax());
      employee.setGroup(empl.getGroup());

      // 0普通 2销售
      employee.setWorkKey(empl.getWorkKey());
      employee.setIsActive(empl.getIsActive());
      employee.setAge(empl.getAge());
      employee.setSex(empl.getSex());
      employee.setCardId(empl.getCardId());
      // 客服
      employee.setCtiNo(empl.getCtiNo());
      employee.setIsAdmin(empl.getIsAdmin());

      // Role
      for (Integer role_id : empl.getRoleids()) {
        employee.addMembership(new Role(role_id));
      }

      // Team
      for (Integer team_id : empl.getTeams()) {
        employee.addTeamMembership(new Team(team_id));
      }

      // 保存
      getHibernateTemplate().save(employee);
    } else {
      return -1;
    }
    return 0;
  }

  public int update(Employee employee) {
    Employee t105 = (Employee) getHibernateTemplate().get(Employee.class,
        employee.getUserId(), LockMode.UPGRADE);
    if (t105 == null) {
      log.error("The userId= " + employee.getUserId() + ",not found");
      return -1;
    }

    t105.setUserName(employee.getUserName());
    if (StringUtil.hasLength(employee.getPasswd()))
      t105.setPasswd(employee.getPasswd());
    t105.setPhone(employee.getPhone());
    t105.setFax(employee.getFax());
    t105.setGroup(employee.getGroup());
    t105.setWorkKey(employee.getWorkKey());
    t105.setIsActive(employee.getIsActive());
    t105.setAge(employee.getAge());
    t105.setSex(employee.getSex());
    t105.setCardId(employee.getCardId());
    // 客服部分
    t105.setCtiNo(employee.getCtiNo());
    t105.setIsAdmin(employee.getIsAdmin());

    // Role
    t105.setMemberships(new HashSet<Role>(0));
    for (Integer role_id : employee.getRoleids()) {
      t105.getMemberships().add(new Role(role_id));
    }

    // Team
    t105.setTeamMemberships(new HashSet<Team>(0));
    for (Integer team_id : employee.getTeams()) {
      t105.addTeamMembership(new Team(team_id));
    }

    getHibernateTemplate().update(t105);

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<Employee> getEmployees(boolean enabled) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
    if (enabled)
      criteria.add(Restrictions.eq("isActive", true));
    criteria.addOrder(Order.asc("userName"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  public int updateEmployee(Employee employee) {
    Employee t105 = (Employee) getHibernateTemplate().get(Employee.class,
        employee.getUserId(), LockMode.UPGRADE);
    if (t105 == null) {
      log.error("user not find.");
      return -1;
    }

    t105.setUserName(employee.getUserName());
    if (StringUtil.hasLength(employee.getPasswd()))
      t105.setPasswd(employee.getPasswd());

    t105.setPhone(employee.getPhone());
    t105.setFax(employee.getFax());
    getHibernateTemplate().update(t105);
    return 0;
  }

  @SuppressWarnings("unchecked")
  public Set<String> getAuthorities(long userId) {
    StringBuilder sql1 = new StringBuilder();
    sql1.append("select r.roleCode ");
    sql1.append("from Employee u join u.memberships r ");
    sql1.append("where u.userId=?");

    Object[] params = { userId };
    List<Object> list1 = getHibernateTemplate().find(sql1.toString(), params);
    Set<String> st = new HashSet<String>();

    for (Object authorities : list1) {
      st.add(RowDataUtil.getString(authorities));
    }
    return st;
  }

  @SuppressWarnings("unchecked")
  public List<Employee> getSalesmans(boolean active) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("from Employee where workKey='2' ");
    if (active)
      sql.append("and isActive=true ");
    List<Employee> list = template.find(sql.toString());

    return list;
  }

  @SuppressWarnings("unchecked")
  public List<Employee> getSalesByTeam(long teamId) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class, "u");
    criteria.createAlias("u.teamMemberships", "t");

    criteria.add(Restrictions.eq("t.teamId", teamId));
    criteria.add(Restrictions.eq("u.isActive", true));
    criteria.add(Restrictions.eq("u.workKey", "2"));
    criteria.addOrder(Order.asc("u.userName"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  @SuppressWarnings("unchecked")
  public List<Employee> getUserByTeam(long teamId) {
    StringBuilder sql = new StringBuilder();
    sql.append("select u.userId,u.userCd,u.userName ");
    sql.append("from Employee u join u.teamMemberships t ");
    sql.append("where t.teamId=? ");
    sql.append("and u.isActive=true order by u.userName");

    Object[] params = { teamId };

    List<Object[]> tfjs = getHibernateTemplate().find(sql.toString(), params);
    List<Employee> list = new ArrayList<Employee>();
    Employee empl = null;
    if (tfjs.size() > 0) {
      list = new ArrayList<Employee>();
      for (Object[] obj : tfjs) {
        empl = new Employee();
        empl.setUserId(RowDataUtil.getInt(obj[0]));
        empl.setUid(RowDataUtil.getString(obj[1]));
        empl.setUserName(RowDataUtil.getString(obj[2]));
        list.add(empl);
      }
    }

    return list;
  }
}
