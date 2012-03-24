package com.opentravelsoft.security;

//import org.acegisecurity.captcha.CaptchaSecurityContextImpl;

public class FixedCaptchaSecurityContextImpl // extends
// CaptchaSecurityContextImpl
{
    private static final long serialVersionUID = 2904656089787606908L;

    public int hashCode()
    {
        // if (getAuthentication() == null)
        // {
        // return (int) System.currentTimeMillis();
        // } else
        // {
        // return this.getAuthentication().hashCode();
        // }
        return 1;
    }
}
