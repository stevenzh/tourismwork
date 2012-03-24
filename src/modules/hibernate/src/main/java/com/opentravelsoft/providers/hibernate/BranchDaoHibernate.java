package com.opentravelsoft.providers.hibernate;

import com.opentravelsoft.entity.Branch;
import com.opentravelsoft.providers.BranchDao;

import org.springframework.stereotype.Repository;

@Repository("BranchDao")
public class BranchDaoHibernate extends GenericDaoHibernate<Branch, Long>
    implements BranchDao {
  public BranchDaoHibernate() {
    super(Branch.class);
  }

}
