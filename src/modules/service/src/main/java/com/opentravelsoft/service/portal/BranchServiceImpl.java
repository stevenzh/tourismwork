package com.opentravelsoft.service.portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Branch;
import com.opentravelsoft.providers.BranchDao;

@Service("BranchService")
public class BranchServiceImpl implements BranchService {
  private BranchDao branchDao;

  @Autowired
  public void setBranchDao(BranchDao branchDao) {
    this.branchDao = branchDao;
  }

  public Branch roGetBranch(long branchId) {
    return branchDao.get(branchId);
  }

  public List<Branch> roGetBranchList() {
    return branchDao.getAll();
  }

}
