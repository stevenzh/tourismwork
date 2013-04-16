package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.providers.TeamDao;
import com.opentravelsoft.util.PaginationSupport;

@Repository("TeamDao")
public class TeamDaoHibernate extends GenericDaoHibernate<Team, Integer>
    implements TeamDao {
  public TeamDaoHibernate() {
    super(Team.class);
  }

  @SuppressWarnings("unchecked")
  public List<Team> getTeam(int userId, TeamType type) {
    boolean isAdmin = false;
    HibernateTemplate template = getHibernateTemplate();
    Object obj = template.get(Employee.class, userId);
    List<Team> list = new ArrayList<Team>();
    if (null == obj)
      return list;
    else {
      Employee employee = (Employee) obj;
      isAdmin = employee.getIsAdmin();
      if (isAdmin) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);

        if (type == TeamType.Product)
          criteria.add(Restrictions.eq("isProduct", true));
        else if (type == TeamType.Sales)
          criteria.add(Restrictions.eq("isSales", true));
        else if (type == TeamType.Operator)
          criteria.add(Restrictions.eq("isOperator", true));

        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

        list = getHibernateTemplate().findByCriteria(criteria);
      } else {
        Set<Team> team = employee.getTeamMemberships();
        for (Team team2 : team) {
          if (type == TeamType.Operator && team2.getIsOperator())
            list.add(team2);
          else if (type == TeamType.Sales && team2.getIsSales())
            list.add(team2);
          else if (type == TeamType.Product && team2.getIsProduct())
            list.add(team2);
        }
      }

      return list;
    }
  }

  public PaginationSupport getTeamList(int fromRecord, int pageSize) {
    DetachedCriteria teamCriteria = DetachedCriteria.forClass(Team.class);
    PaginationSupport support = findPageByCriteria(teamCriteria, pageSize,
        fromRecord);

    return support;
  }

  @SuppressWarnings("unchecked")
  public List<Team> getTeamList(TeamType type) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Team.class);

    if (type == TeamType.Product)
      criteria.add(Restrictions.eq("isProduct", true));
    else if (type == TeamType.Sales)
      criteria.add(Restrictions.eq("isSales", true));
    else if (type == TeamType.Operator)
      criteria.add(Restrictions.eq("isOperator", true));

    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

}
