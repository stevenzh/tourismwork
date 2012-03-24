package com.opentravelsoft.util;

import java.io.UnsupportedEncodingException;

public class Crypt
{

    public static Crypt getInstance()
    {
        return cp != null ? cp : (cp = new Crypt());
    }

    public Crypt()
    {
        keys = null;
        init(DEFAULT_KEYS);
    }

    public Crypt(String keystrs[])
    {
        keys = null;
        init(keystrs);
    }

    private void init(String keystrs[])
    {
        keys = new byte[keystrs.length][];
        for (int i = 0; i < keys.length; i++)
            keys[i] = fromString(keystrs[i]);

    }

    private String toString(byte ba[])
    {
        char buf[] = new char[ba.length * 2];
        int j = 0;
        for (int i = 0; i < ba.length; i++)
        {
            int k = ba[i];
            buf[j++] = hexDigits[k >>> 4 & 0xf];
            buf[j++] = hexDigits[k & 0xf];
        }

        return new String(buf);
    }

    private int fromDigit(char ch)
    {
        if (ch >= '0' && ch <= '9')
            return ch - 48;
        if (ch >= 'A' && ch <= 'Z')
            return (ch - 65) + 10;
        if (ch >= 'a' && ch <= 'z')
            return (ch - 97) + 10;
        else
            throw new IllegalArgumentException("invalid hex digit '" + ch + "'");
    }

    private byte[] fromString(String hex)
    {
        int len = hex.length();
        byte buf[] = new byte[(len + 1) / 2];
        int i = 0;
        int j = 0;
        if (len % 2 == 1)
            buf[j++] = (byte) fromDigit(hex.charAt(i++));
        while (i < len)
            buf[j++] = (byte) (fromDigit(hex.charAt(i++)) << 4 | fromDigit(hex
                    .charAt(i++)));
        return buf;
    }

    private byte encrypt(byte d, byte key[])
    {
        byte e = d;
        for (int i = 0; i < key.length; i++)
            e ^= key[i];

        return e;
    }

    private byte decrypt(byte e, byte key[])
    {
        byte d = e;
        for (int i = key.length - 1; i >= 0; i--)
            d ^= key[i];

        return d;
    }

    public String encrypt(String orig)
    {
        byte ect[] = (byte[]) null;
        byte origBytes[] = (byte[]) null;
        try
        {
            origBytes = orig.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.toString());
        }
        ect = new byte[origBytes.length];
        for (int i = 0; i < origBytes.length; i += keys.length)
        {
            for (int j = 0; j < keys.length; j++)
            {
                if (i + j >= origBytes.length)
                    break;
                ect[i + j] = encrypt(origBytes[i + j], keys[j]);
            }

        }

        return toString(ect);
    }

    public String decrypt(String ectstr)
    {
        byte ect[] = (byte[]) null;
        byte origBytes[] = (byte[]) null;
        String dctStr = null;
        ect = fromString(ectstr);
        origBytes = new byte[ect.length];
        for (int i = 0; i < origBytes.length; i += keys.length)
        {
            for (int j = 0; j < keys.length; j++)
            {
                if (i + j >= origBytes.length)
                    break;
                origBytes[i + j] = decrypt(ect[i + j], keys[j]);
            }

        }

        try
        {
            dctStr = new String(origBytes, "UTF-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.toString());
        }
        return dctStr;
    }

    private static Crypt cp;

    private static final String DEFAULT_KEYS[] = {
            "0tyuagatdeatakha45aay667a6atyka8",
            "2t5656u253AjDE39io2910389038p9za",
            "z91289126u3908hu098ji809nu098k89",
            "Aa6BcCDf01y201g20384ul37l6kj8069",
            "66aBcg7fjoy201g60fy4383ul8p9i103",
            "6dT6J8I9916J81g2juH89LO9k998K0hi",
            "67rgyu1kfhi6ukl2ji43lb7lokhuk0gy",
            "6dhhf1kf016u01g2ji43lo7lok9r10hi",
            "k7agjjkg5ghbh01ggnhgthghukn75huy",
            "66n7ihjy0jyu0er2jvr4yb57jkji10gj",
            "mj7aBf1kf016u01g2ji43lo7o99r10gy",
            "6dhhl98f016ii1g2ju7lo7u7ok9r10hi",
            "k7a29gmgt5ghb7UJvrnr7jJhukn75huy",
            "H8JHgmg5Tbg01GUnr7jJTUkn75JGJuyJ",
            "kN5G9g675G7bg05H6nr7jkFUTn75JIy6" };

    private static boolean updatedProps = false;

    private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'B', 'c', 'd', 'E', 'f' };

    private byte keys[][];

}