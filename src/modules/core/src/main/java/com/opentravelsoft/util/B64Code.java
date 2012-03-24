package com.opentravelsoft.util;

import java.io.UnsupportedEncodingException;

public class B64Code
{

    public B64Code()
    {
    }

    public static String encode(String s)
    {
        try
        {
            return encode(s, null);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode(String s, String charEncoding)
            throws UnsupportedEncodingException
    {
        byte bytes[];
        if (charEncoding == null)
            bytes = s.getBytes("ISO_8859_1");
        else
            bytes = s.getBytes(charEncoding);
        return new String(encode(bytes));
    }

    public static char[] encode(byte b[])
    {
        if (b == null)
            return null;
        int bLen = b.length;
        char r[] = new char[((bLen + 2) / 3) * 4];
        int ri = 0;
        int bi = 0;
        for (int stop = (bLen / 3) * 3; bi < stop;)
        {
            byte b0 = b[bi++];
            byte b1 = b[bi++];
            byte b2 = b[bi++];
            r[ri++] = nibble2code[b0 >>> 2 & 0x3f];
            r[ri++] = nibble2code[b0 << 4 & 0x3f | b1 >>> 4 & 0xf];
            r[ri++] = nibble2code[b1 << 2 & 0x3f | b2 >>> 6 & 3];
            r[ri++] = nibble2code[b2 & 0x3f];
        }

        if (bLen != bi)
            switch (bLen % 3)
            {
            case 2: // '\002'
            {
                byte b0 = b[bi++];
                byte b1 = b[bi++];
                r[ri++] = nibble2code[b0 >>> 2 & 0x3f];
                r[ri++] = nibble2code[b0 << 4 & 0x3f | b1 >>> 4 & 0xf];
                r[ri++] = nibble2code[b1 << 2 & 0x3f];
                r[ri++] = '=';
                break;
            }

            case 1: // '\001'
            {
                byte b0 = b[bi++];
                r[ri++] = nibble2code[b0 >>> 2 & 0x3f];
                r[ri++] = nibble2code[b0 << 4 & 0x3f];
                r[ri++] = '=';
                r[ri++] = '=';
                break;
            }
            }
        return r;
    }

    public static String decode(String s)
    {
        try
        {
            return decode(s, "ISO_8859_1");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(String s, String charEncoding)
            throws UnsupportedEncodingException
    {
        byte decoded[] = decode(s.toCharArray());
        if (charEncoding == null)
            return new String(decoded);
        else
            return new String(decoded, charEncoding);
    }

    public static byte[] decode(char b[])
    {
        if (b == null)
            return null;
        int bLen = b.length;
        if (bLen % 4 != 0)
            throw new IllegalArgumentException("Input block size is not 4");
        int li;
        for (li = bLen - 1; li >= 0 && b[li] == '='; li--)
            ;
        if (li < 0)
            return new byte[0];
        int rLen = ((li + 1) * 3) / 4;
        byte r[] = new byte[rLen];
        int ri = 0;
        int bi = 0;
        int stop = (rLen / 3) * 3;
        try
        {
            while (ri < stop)
            {
                byte b0 = code2nibble[b[bi++]];
                byte b1 = code2nibble[b[bi++]];
                byte b2 = code2nibble[b[bi++]];
                byte b3 = code2nibble[b[bi++]];
                if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
                    throw new IllegalArgumentException("Not B64 encoded");
                r[ri++] = (byte) (b0 << 2 | b1 >>> 4);
                r[ri++] = (byte) (b1 << 4 | b2 >>> 2);
                r[ri++] = (byte) (b2 << 6 | b3);
            }
            if (rLen != ri)
                switch (rLen % 3)
                {
                default:
                    break;

                case 2: // '\002'
                {
                    byte b0 = code2nibble[b[bi++]];
                    byte b1 = code2nibble[b[bi++]];
                    byte b2 = code2nibble[b[bi++]];
                    if (b0 < 0 || b1 < 0 || b2 < 0)
                        throw new IllegalArgumentException("Not B64 encoded");
                    r[ri++] = (byte) (b0 << 2 | b1 >>> 4);
                    r[ri++] = (byte) (b1 << 4 | b2 >>> 2);
                    break;
                }

                case 1: // '\001'
                {
                    byte b0 = code2nibble[b[bi++]];
                    byte b1 = code2nibble[b[bi++]];
                    if (b0 < 0 || b1 < 0)
                        throw new IllegalArgumentException("Not B64 encoded");
                    r[ri++] = (byte) (b0 << 2 | b1 >>> 4);
                    break;
                }
                }
        } catch (IndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException("char " + bi
                    + " was not B64 encoded");
        }
        return r;
    }

    static final char pad = 61;

    static final char nibble2code[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', '+', '/' };

    static byte code2nibble[];

    static
    {
        code2nibble = null;
        code2nibble = new byte[256];
        for (int i = 0; i < 256; i++)
            code2nibble[i] = -1;

        for (byte b = 0; b < 64; b++)
            code2nibble[(byte) nibble2code[b]] = b;

        code2nibble[61] = 0;
    }
}