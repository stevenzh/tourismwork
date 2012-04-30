package com.opentravelsoft.service.account;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.model.Member;
import com.opentravelsoft.providers.ListDao;
import com.opentravelsoft.providers.MemberDao;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {
  
  @Autowired
  private MemberDao memberDao;

  @Autowired
  private ListDao listDao;

  public int txRegister(Member customer) {
    return memberDao.register(customer);
  }

  public List<LabelValueBean> roGetEducates() {
    return listDao.getList("Education");
  }

  public List<LabelValueBean> roGetVocations() {
    return listDao.getList("Occupation");
  }

  public Member roGetMemberById(long userId) {
    return memberDao.getMemberById(userId);
  }

  public Member roGetMemberByName(String userName) {
    return memberDao.getMemberByName(userName);
  }

  public int txInfoUpdate(Member customer) {
    return memberDao.update(customer);
  }

  public String txGetMemberPwd(String mobileNo) {
    return memberDao.getMemberPwd(mobileNo);
  }

  public int txModifyPassword(Member customer) {
    return memberDao.modifyPassword(customer);
  }

  public String validUid(String uid) {
    return memberDao.validUid(uid);
  }

}
