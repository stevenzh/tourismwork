package com.opentravelsoft.util;

import java.net.URL;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Generic Property Accessor with Cache - Utilities for working with properties
 * files
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:41 $
 */
public class UtilProperties implements java.io.Serializable
{

    private static final long serialVersionUID = -3215438684670970638L;

    protected static Log log = LogFactory.getLog(UtilProperties.class);

    /**
     * Compares the specified property to the compareString, returns true if
     * they are the same, false otherwise
     * 
     * @param resource The name of the resource - if the properties file is
     *            'webevent.properties', the resource name is 'webevent'
     * @param name The name of the property in the properties file
     * @param compareString The String to compare the property value to
     * @return True if the strings are the same, false otherwise
     */
    public static boolean propertyValueEquals(String resource, String name,
            String compareString)
    {
        String value = getPropertyValue(resource, name);

        if (value == null)
            return false;
        return value.trim().equals(compareString);
    }

    /**
     * Compares Ignoring Case the specified property to the compareString,
     * returns true if they are the same, false otherwise
     * 
     * @param resource The name of the resource - if the properties file is
     *            'webevent.properties', the resource name is 'webevent'
     * @param name The name of the property in the properties file
     * @param compareString The String to compare the property value to
     * @return True if the strings are the same, false otherwise
     */
    public static boolean propertyValueEqualsIgnoreCase(String resource,
            String name, String compareString)
    {
        String value = getPropertyValue(resource, name);

        if (value == null)
            return false;
        return value.trim().equalsIgnoreCase(compareString);
    }

    /**
     * Returns the value of the specified property name from the specified
     * resource/properties file. If the specified property name or properties
     * file is not found, the defaultValue is returned.
     * 
     * @param resource The name of the resource - if the properties file is
     *            'webevent.properties', the resource name is 'webevent'
     * @param name The name of the property in the properties file
     * @param defaultValue The value to return if the property is not found
     * @return The value of the property in the properties file, or if not found
     *         then the defaultValue
     */
    public static String getPropertyValue(String resource, String name,
            String defaultValue)
    {
        String value = getPropertyValue(resource, name);

        if (value == null || value.length() == 0)
            return defaultValue;
        else
            return value;
    }

    public static double getPropertyNumber(String resource, String name)
    {
        String str = getPropertyValue(resource, name);
        double strValue = 0.00000;

        try
        {
            strValue = Double.parseDouble(str);
        } catch (NumberFormatException nfe)
        {
        }
        return strValue;
    }

    /**
     * Returns the value of the specified property name from the specified
     * resource/properties file
     * 
     * @param resource The name of the resource - can be a file, class, or URL
     * @param name The name of the property in the properties file
     * @return The value of the property in the properties file
     */
    public static String getPropertyValue(String resource, String name)
    {
        if (resource == null || resource.length() <= 0)
            return "";
        if (name == null || name.length() <= 0)
            return "";
        Properties properties = new Properties();
        // TODO
        if (properties == null)
        {
            log
                    .debug("[UtilProperties.getPropertyValue] could not find resource: "
                            + resource);
            return "";
        }

        String value = null;

        try
        {
            value = properties.getProperty(name);
        } catch (Exception e)
        {
            log.debug("", e);
        }
        return value == null ? "" : value.trim();
    }

    /**
     * Returns the specified resource/properties file
     * 
     * @param resource The name of the resource - can be a file, class, or URL
     * @return The properties file
     */
    public static Properties getProperties(String resource)
    {
        if (resource == null || resource.length() <= 0)
            return null;
        Properties properties = new Properties();
        // TODO
        if (properties == null)
        {
            log
                    .debug("[UtilProperties.getProperties] could not find resource: "
                            + resource);
            return null;
        }
        return properties;
    }

    /**
     * Returns the specified resource/properties file
     * 
     * @param url The URL to the resource
     * @return The properties file
     */
    public static Properties getProperties(URL url)
    {
        if (url == null)
            return null;
        Properties properties = new Properties();
        // TODO
        if (properties == null)
        {
            log
                    .debug("[UtilProperties.getProperties] could not find resource: "
                            + url);
            return null;
        }
        return properties;
    }

    // ========= URL Based Methods ==========

    /**
     * Compares the specified property to the compareString, returns true if
     * they are the same, false otherwise
     * 
     * @param url URL object specifying the location of the resource
     * @param name The name of the property in the properties file
     * @param compareString The String to compare the property value to
     * @return True if the strings are the same, false otherwise
     */
    public static boolean propertyValueEquals(URL url, String name,
            String compareString)
    {
        String value = getPropertyValue(url, name);

        if (value == null)
            return false;
        return value.trim().equals(compareString);
    }

    /**
     * Compares Ignoring Case the specified property to the compareString,
     * returns true if they are the same, false otherwise
     * 
     * @param url URL object specifying the location of the resource
     * @param name The name of the property in the properties file
     * @param compareString The String to compare the property value to
     * @return True if the strings are the same, false otherwise
     */
    public static boolean propertyValueEqualsIgnoreCase(URL url, String name,
            String compareString)
    {
        String value = getPropertyValue(url, name);

        if (value == null)
            return false;
        return value.trim().equalsIgnoreCase(compareString);
    }

    /**
     * Returns the value of the specified property name from the specified
     * resource/properties file. If the specified property name or properties
     * file is not found, the defaultValue is returned.
     * 
     * @param url URL object specifying the location of the resource
     * @param name The name of the property in the properties file
     * @param defaultValue The value to return if the property is not found
     * @return The value of the property in the properties file, or if not found
     *         then the defaultValue
     */
    public static String getPropertyValue(URL url, String name,
            String defaultValue)
    {
        String value = getPropertyValue(url, name);

        if (value == null || value.length() <= 0)
            return defaultValue;
        else
            return value;
    }

    public static double getPropertyNumber(URL url, String name)
    {
        String str = getPropertyValue(url, name);
        double strValue = 0.00000;

        try
        {
            strValue = Double.parseDouble(str);
        } catch (NumberFormatException nfe)
        {
        }
        return strValue;
    }

    /**
     * Returns the value of the specified property name from the specified
     * resource/properties file
     * 
     * @param url URL object specifying the location of the resource
     * @param name The name of the property in the properties file
     * @return The value of the property in the properties file
     */
    public static String getPropertyValue(URL url, String name)
    {
        if (url == null)
            return "";
        if (name == null || name.length() <= 0)
            return "";
        Properties properties = new Properties();
        // TODO
        if (properties == null)
        {
            log
                    .debug("[UtilProperties.getPropertyValue] could not find resource: "
                            + url);
            return null;
        }

        String value = null;

        try
        {
            value = properties.getProperty(name);
        } catch (Exception e)
        {
            log.debug(e.getMessage());
        }
        return value == null ? "" : value.trim();
    }

    /**
     * Returns the value of a split property name from the specified
     * resource/properties file Rather than specifying the property name the
     * value of a name.X property is specified which will correspond to a
     * value.X property whose value will be returned. X is a number from 1 to
     * whatever and all values are checked until a name.X for a certain X is not
     * found.
     * 
     * @param url URL object specifying the location of the resource
     * @param name The name of the split property in the properties file
     * @return The value of the split property from the properties file
     */
    public static String getSplitPropertyValue(URL url, String name)
    {
        if (url == null)
            return "";
        if (name == null || name.length() <= 0)
            return "";

        Properties properties = new Properties();
        // TODO
        if (properties == null)
        {
            log
                    .debug("[UtilProperties.getPropertyValue] could not find resource: "
                            + url);
            return null;
        }

        String value = null;

        try
        {
            int curIdx = 1;
            String curName = null;

            while ((curName = properties.getProperty("name." + curIdx)) != null)
            {
                if (name.equals(curName))
                {
                    value = properties.getProperty("value." + curIdx);
                    break;
                }
                curIdx++;
            }
        } catch (Exception e)
        {
            log.debug(e.getMessage());
        }
        return value == null ? "" : value.trim();
    }

    /**
     * Sets the specified value of the specified property name to the specified
     * resource/properties file
     * 
     * @param resource The name of the resource - must be a file
     * @param name The name of the property in the properties file
     * @param value The value of the property in the properties file
     */
    public static void setPropertyValue(String resource, String name,
            String value)
    {
        if (resource == null || resource.length() <= 0)
            return;
        if (name == null || name.length() <= 0)
            return;
        Properties properties = new Properties();
        // TODO
        if (properties == null)
        {
            log
                    .debug("[UtilProperties.setPropertyValue] could not find resource: "
                            + resource);
            return;
        }

        try
        {
            properties.setProperty(name, value);
            FileOutputStream propFile = new FileOutputStream(resource);
            properties
                    .store(
                            propFile,
                            "Dynamically modified by OFBiz Framework (org.ofbiz.base.util : UtilProperties.setPropertyValue) ");
            propFile.close();
        } catch (FileNotFoundException e)
        {
            log.error("Unable to located the resource file.", e);
        } catch (IOException e)
        {
            log.error("", e);
        }
    }

    // protected static ResourceBundle getBaseResourceBundle(String resource,
    // Locale locale) {
    // if (resource == null || resource.length() <= 0) return null;
    // if (locale == null) locale = Locale.getDefault();
    //
    // java.lang.ClassLoader loader =
    // Thread.currentThread().getContextClassLoader();
    // ResourceBundle bundle = null;
    // try {
    // bundle = ResourceBundle.getBundle(resource, locale, loader);
    // } catch (MissingResourceException e) {
    // String resourceFullName = resource + "_" + locale.toString();
    // if (!resourceNotFoundMessagesShown.contains(resourceFullName)) {
    // resourceNotFoundMessagesShown.add(resourceFullName);
    // Debug.log("[UtilProperties.getPropertyValue] could not find resource: " +
    // resource + " for locale " + locale.toString() + ": " + e.toString(),
    // module);
    // return null;
    // }
    // }
    // if (bundle == null) {
    // String resourceFullName = resource + "_" + locale.toString();
    // if (!resourceNotFoundMessagesShown.contains(resourceFullName)) {
    // resourceNotFoundMessagesShown.add(resourceFullName);
    // Debug.log("[UtilProperties.getPropertyValue] could not find resource: " +
    // resource + " for locale " + locale.toString(), module);
    // return null;
    // }
    // }
    //
    // return bundle;
    // }
}