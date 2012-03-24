package com.opentravelsoft.util;

import java.io.IOException;

import org.apache.commons.collections.ExtendedProperties;

/**
 * プロパティにアクセスするためのクラス．
 * 
 * @author nokami
 */
public class PropertyAccess
{

    private static PropertyAccess INSTANCE = null;

    private static ExtendedProperties prop = null;

    private static final String PROPERTY_FILE_NAME = "/eap-core.properties";

    private static final String PROP_ONETIMEPASS_LIMIT = "oneTimePass.limit";

    private static final String PROP_MAIL_FROM = "mail.from";

    private static final String PROP_MAIL_SMTP_IP = "mail.smtp.ip";

    private static final String PROP_MAIL_SMTP_PORT = "mail.smtp.port";

    private static final String PROP_MAIL_REGIST_SUBJECT = "mail.regist.subject";

    private static final String PROP_MAIL_UPDATE_SUBJECT = "mail.update.subject";

    private static final String PROP_MAIL_DELETE_SUBJECT = "mail.delete.subject";

    private static final String PROP_MAIL_CHANGE_SUBJECT = "mail.change.subject";

    private static final String PROP_JPORTAL_HOST = "jportal.host";

    private static final String PROP_SPORTAL_HOST = "sportal.host";

    private static final String PROP_KANRI_HOST = "kanri.host";

    private static final String PROP_SINSA_HOST = "sinsa.host";

    private static final String PROP_CHANGEPASS_PATH = "changePass.path";

    private static final String PROP_CHANGEPASS_LIMIT = "changePass.limit";

    /**
     * 連携用ワンタイムパスワードの有効期限を取得します．
     * 
     * @return ワンタイムパスワードの有効期限 ( 分 )
     */
    public int getOneTimePassLimit()
    {

        return prop.getInt(PROP_ONETIMEPASS_LIMIT);
    }

    /**
     * Fromのメールアドレスを取得します。
     * 
     * @return
     */
    public String getMailFrom()
    {
        return prop.getString(PROP_MAIL_FROM);
    }

    /**
     * mail送信のSMTPを取得します。
     * 
     * @return
     */
    public String getSmtpIp()
    {
        return prop.getString(PROP_MAIL_SMTP_IP);
    }

    /**
     * mail送信のport番号を取得します。
     * 
     * @return
     */
    public int getSmtpPort()
    {
        return prop.getInt(PROP_MAIL_SMTP_PORT);
    }

    /**
     * 利用者情報登録時のsubjectを取得します。
     * 
     * @return
     */
    public String getMailRegistSubject()
    {
        return prop.getString(PROP_MAIL_REGIST_SUBJECT);
    }

    /**
     * 利用者情報変更時のsubjectを取得します。
     * 
     * @return
     */
    public String getMailUpdateSubject()
    {
        return prop.getString(PROP_MAIL_UPDATE_SUBJECT);
    }

    /**
     * 利用者情報削除時のsubjectを取得します。
     * 
     * @return
     */
    public String getMailDeleteSubject()
    {
        return prop.getString(PROP_MAIL_DELETE_SUBJECT);
    }

    /**
     * パスワード変更・再設定時のsubjectを取得します。
     * 
     * @return
     */
    public String getMailChangeSubject()
    {
        return prop.getString(PROP_MAIL_CHANGE_SUBJECT);
    }

    /**
     * 住民ポータルサーバホスト名を取得します。
     * 
     * @return
     */
    public String getJportalHost()
    {
        return prop.getString(PROP_JPORTAL_HOST);
    }

    /**
     * 職員ポータルサーバホスト名を取得します。
     * 
     * @return
     */
    public String getSportalHost()
    {
        return prop.getString(PROP_SPORTAL_HOST);
    }

    /**
     * 管理サーバホスト名を取得します。
     * 
     * @return
     */
    public String getKanriHost()
    {
        return prop.getString(PROP_KANRI_HOST);
    }

    /**
     * 審査サーバホスト名を取得します。
     * 
     * @return
     */
    public String getSinsaHost()
    {
        return prop.getString(PROP_SINSA_HOST);
    }

    /**
     * パスワード変更時のＵＲＬのサーブレット名を取得します。
     * 
     * @return
     */
    public String getChangepassPath()
    {
        return prop.getString(PROP_CHANGEPASS_PATH);
    }

    /**
     * パスワード変更の有効期限（分）を取得します．
     * 
     * @return
     */
    public int getChangepassLimit()
    {
        return prop.getInt(PROP_CHANGEPASS_LIMIT);
    }

    /**
     * コンストラクタ．
     * 
     * @throws IOException
     */
    private PropertyAccess() throws IOException
    {

        prop = new ExtendedProperties();
        prop.load(PropertyAccess.class.getResourceAsStream(PROPERTY_FILE_NAME),
                "UTF-8");
    }

    /**
     * インスタンスを取得します．
     * 
     * @return
     */
    static public PropertyAccess getInstance()
    {

        if (INSTANCE == null)
        {
            synchronized (PropertyAccess.class)
            {
                if (INSTANCE == null)
                {
                    try
                    {
                        INSTANCE = new PropertyAccess();
                    } catch (IOException e)
                    {
                        // TODO
                    }
                }
            }
        }

        return INSTANCE;
    }
}
