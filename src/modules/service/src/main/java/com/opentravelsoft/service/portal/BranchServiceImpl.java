package com.opentravelsoft.service.portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Branch;
import com.opentravelsoft.providers.BranchDao;

@Service("BranchService")
public class BranchServiceImpl implements BranchService {

  @Autowired
  private BranchDao branchDao;

  public Branch roGetBranch(int branchId) {
    return branchDao.get(branchId);
  }

  public List<Branch> roGetBranchList() {
    return branchDao.getAll();
  }

}
