package com.opentravelsoft.util;


/**
 * STATUS項目のコードを管理するクラス
 * 
 */
public class EbizParams
{
	/** 画面の表示　*/
    public static final String LABEL_FLAG = "_LAB";

    /** メール配置ファイルのキー　*/
    public static final String EBIZ_MAIL    = "_Ebiz_Mail";

    /** テンプレートパス */
    public static final String VELOCITY_PATH   = "Velocity_fileloader_path";

    /** テンプレートのログ */
    public static final String VELOCITY_LOG    = "Velocity_runtime_log";

    /** テンプレートのINPUT　charset */
    public static final String VELOCITY_INPUT  = "Velocity_input_encoding";

    /**　テンプレートのOUTPUT　charset */
    public static final String VELOCITY_OUTPUT = "Velocity_output_encoding";

    /** SMTPのサーバ名称 */
    public static final String MAIL_SMTP_SERVER= "Mail_smtp_server";

    /** SMTPのユーザID */
    public static final String MAIL_AUTH_USER  = "Mail_auth_user";

    /** SMTPのパスワード */
    public static final String MAIL_AUTH_PWD   = "Mail_auth_pwd";

}
