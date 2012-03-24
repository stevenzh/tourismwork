package com.opentravelsoft.action.manage.account;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.service.account.MemberService;

/**
 * 会员列表
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:15 $
 */
public class ListMemberAction extends ManageAction
{

    private static final long serialVersionUID = -7138389244051722L;

    private MemberService memberService;

    public void setMemberService(MemberService memberService)
    {
        this.memberService = memberService;
    }

}
