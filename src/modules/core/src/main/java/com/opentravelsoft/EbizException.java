package com.opentravelsoft;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:24 $
 */
public class EbizException extends Exception {

    private static final long serialVersionUID = -8607341654881604650L;

    public EbizException(String message) {
        super(message);
    }

    public EbizException(String message, Throwable e) {
        super(message, e);
    }
}
