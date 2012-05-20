package com.opentravelsoft.workflow.activiti;

import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.identity.Account;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;

public class IdentitySessionImpl implements IdentityService {

  protected Session session;

  public void setSession(Session session) {
    this.session = session;
  }

  public void deleteUser(String userId) {
    // lookup the user
    User user = findUserById(userId);

    // delete the user
    session.delete(user);
  }

  public User findUserById(String userId) {
    Employee employee = (Employee) session.createCriteria(Employee.class)
        .add(Restrictions.eq("userCd", userId)).uniqueResult();
    if (employee != null) {
      return getUserInfo(employee);
    }
    return null;
  }

  // public List<User> findUsersByGroupId(String groupId) {
  // List<Employee> list = session.createCriteria(Employee.class)
  // .createAlias("teamMemberships", "g")
  // .add(Restrictions.eq("g.id", groupId))
  // .setProjection(Projections.property("user")).list();
  // List<User> users = new ArrayList<User>();
  // for (Employee employee : list) {
  // users.add(getUserInfo(employee));
  // }
  //
  // return users;
  // }

  public void deleteGroup(String groupId) {
    // look up the group
    Team group = findTeamById(groupId);

    // delete the group
    session.delete(group);
  }

  // public Group findGroupById(String groupId) {
  // return getGroupInfo(findTeamById(groupId));
  // }
  //
  // @SuppressWarnings("unchecked")
  // public List<Group> findGroupsByUser(String userId) {
  // List<Team> teams = session
  // .createQuery(
  // "select distinct m.group" + " from " + Employee.class.getName()
  // + " as m where m.user.id = :userId")
  // .setString("userId", userId).list();
  //
  // List<Group> groups = new ArrayList<Group>();
  // for (Team team : teams) {
  // groups.add(getGroupInfo(team));
  // }
  // return groups;
  // }

  private User getUserInfo(Employee employee) {
    User user = new UserEntity(employee.getUserCd());
    user.setFirstName(employee.getGivenName());
    user.setLastName(employee.getFamilyName());
    user.setEmail(employee.getEmail());
    user.setPassword(employee.getPasswd());
    return user;
  }

  private Group getGroupInfo(Team team) {
    Group user = new GroupEntity(team.getName());
    user.setName(team.getName());
    return user;
  }

  private Team findTeamById(String groupId) {
    return (Team) session.createCriteria(Team.class)
        .add(Restrictions.eq("id", groupId)).uniqueResult();
  }

  @Override
  public User newUser(String userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void saveUser(User user) {
    // TODO Auto-generated method stub

  }

  @Override
  public UserQuery createUserQuery() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Group newGroup(String groupId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public GroupQuery createGroupQuery() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void saveGroup(Group group) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean checkPassword(String userId, String password) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setAuthenticatedUserId(String authenticatedUserId) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setUserPicture(String userId, Picture picture) {
    // TODO Auto-generated method stub

  }

  @Override
  public Picture getUserPicture(String userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setUserInfo(String userId, String key, String value) {
    // TODO Auto-generated method stub

  }

  @Override
  public String getUserInfo(String userId, String key) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String> getUserInfoKeys(String userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteUserInfo(String userId, String key) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setUserAccount(String userId, String userPassword,
      String accountName, String accountUsername, String accountPassword,
      Map<String, String> accountDetails) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<String> getUserAccountNames(String userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Account getUserAccount(String userId, String userPassword,
      String accountName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteUserAccount(String userId, String accountName) {
    // TODO Auto-generated method stub

  }

  @Override
  public void createMembership(String userId, String groupId) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteMembership(String userId, String groupId) {
    // TODO Auto-generated method stub

  }
}
