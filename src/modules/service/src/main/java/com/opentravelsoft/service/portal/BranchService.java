package com.opentravelsoft.service.portal;

import java.util.List;

import com.opentravelsoft.entity.Branch;

public interface BranchService
{

    public List<Branch> roGetBranchList();

    public Branch roGetBranch(long branchId);
}
