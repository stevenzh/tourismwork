package com.opentravelsoft;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:24 $
 */
public class EbizCommitableException extends Exception {

    private static final long serialVersionUID = 6653033036924219492L;

    public EbizCommitableException(String message) {
        super(message);
    }

    public EbizCommitableException(String message, Throwable e) {
        super(message, e);
    }

}
