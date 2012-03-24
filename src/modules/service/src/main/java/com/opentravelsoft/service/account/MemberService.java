package com.opentravelsoft.service.account;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.model.Member;

public interface MemberService {

  List<LabelValueBean> roGetVocations();

  int txRegister(Member customer);

  List<LabelValueBean> roGetEducates();

  Member roGetMemberById(long userId);

  Member roGetMemberByName(String userName);

  int txInfoUpdate(Member customer);

  String txGetMemberPwd(String mobileNo);

  int txModifyPassword(Member customer);

  public String validUid(String uid);

}
