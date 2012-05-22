package com.opentravelsoft.service.account;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Member;
import com.opentravelsoft.providers.ListDao;
import com.opentravelsoft.providers.MemberDao;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberDao memberDao;

  @Autowired
  private ListDao listDao;

  public int txRegister(Member member) {
    return memberDao.register(member);
  }

  public List<LabelValueBean> roGetEducates() {
    return listDao.getList("Education");
  }

  public List<LabelValueBean> roGetVocations() {
    return listDao.getList("Occupation");
  }

  public Member roGetMemberById(long memberId) {
    return memberDao.getMemberById(memberId);
  }

  public Member roGetMemberByName(String userName) {
    return memberDao.getMemberByName(userName);
  }

  public int txInfoUpdate(Member member) {
    return memberDao.update(member);
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
