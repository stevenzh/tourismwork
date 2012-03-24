package com.opentravelsoft.validators;

import java.io.UnsupportedEncodingException;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

/**
 * <!-- START SNIPPET: javadoc --> StringLengthFieldValidator checks that a
 * String field is of a certain length. If the "minLength" parameter is
 * specified, it will make sure that the String has at least that many
 * characters. If the "maxLength" parameter is specified, it will make sure that
 * the String has at most that many characters. The "trim" parameter determines
 * whether it will {@link String#trim() trim} the String before performing the
 * length check. If unspecified, the String will be trimmed. <!-- END SNIPPET:
 * javadoc --> <p/>
 * 
 * 
 * <!-- START SNIPPET: parameters -->
 * <ul>
 * <li>fieldName - The field name this validator is validating. Required if
 * using Plain-Validator Syntax otherwise not required</li>
 * <li>maxLength - The max length of the field value. Default ignore.</li>
 * <li>minLength - The min length of the field value. Default ignore.</li>
 * <li>trim - Trim the field value before evaluating its min/max length.
 * Default true</li>
 * </ul>
 * <!-- END SNIPPET: parameters -->
 * 
 * 
 * <pre>
 * &lt;!--START SNIPPET: example --&gt;
 *      &lt;validators&gt;
 *          &lt;!-- Plain Validator Syntax --&gt;
 *          &lt;validator type=&quot;stringlength&quot;&gt;
 *              &lt;param name=&quot;fieldName&quot;&gt;myPurchaseCode&lt;/param&gt;
 *              &lt;param name=&quot;minLength&quot;&gt;10&lt;/param&gt;
 *              &lt;param name=&quot;maxLength&quot;&gt;10&lt;/param&gt;
 *              &lt;param name=&quot;trim&quot;&gt;true&lt;/param&gt;
 *              &lt;message&gt;Your purchase code needs to be 10 characters long&lt;/message&gt;        
 *          &lt;/validator&gt;
 * 
 *          &lt;!-- Field Validator Syntax --&gt;
 *          &lt;field name=&quot;myPurchaseCode&quot;&gt;
 *              &lt;param name=&quot;minLength&quot;&gt;10&lt;/param&gt;
 *              &lt;param name=&quot;maxLength&gt;10&lt;/param&gt;
 *              &lt;param name=&quot;trim&quot;&gt;true&lt;/param&gt;
 *              &lt;message&gt;Your purchase code needs to be 10 characters long&lt;/message&gt;
 *          &lt;/field-name&gt;
 *      &lt;/validators&gt;
 * &lt;!-- END SNIPPET: example --&gt;
 * </pre>
 * 
 * 
 * @author Jason Carreira
 * @author Mark Woon
 * @author tmjee
 * @version $Date: 2009/03/01 16:24:31 $ $Id:
 *          StringLengthFieldValidator.java 1152 2006-10-05 15:39:20Z tm_jee $
 */
public class StringLengthFieldValidator extends FieldValidatorSupport
{

    private static final String ENCODEING = "GBK";

    private boolean doTrim = true;

    private int maxLength = -1;

    private int minLength = -1;

    public void setMaxLength(int maxLength)
    {
        this.maxLength = maxLength;
    }

    public int getMaxLength()
    {
        return maxLength;
    }

    public void setMinLength(int minLength)
    {
        this.minLength = minLength;
    }

    public int getMinLength()
    {
        return minLength;
    }

    public void setTrim(boolean trim)
    {
        doTrim = trim;
    }

    public boolean getTrim()
    {
        return doTrim;
    }

    public void validate(Object object) throws ValidationException
    {
        String fieldName = getFieldName();
        String val = (String) getFieldValue(fieldName, object);

        if (val == null || val.length() <= 0)
        {
            // use a required validator for these
            return;
        }
        if (doTrim)
        {
            val = val.trim();
            if (val.length() <= 0)
            {
                // use a required validator
                return;
            }
        }

        try
        {
            if ((minLength > -1)
                    && (val.getBytes(ENCODEING).length < minLength))
            {
                addFieldError(fieldName, object);
            } else if ((maxLength > -1)
                    && (val.getBytes(ENCODEING).length > maxLength))
            {
                addFieldError(fieldName, object);
            }
        } catch (UnsupportedEncodingException e)
        {
        }
    }
}
