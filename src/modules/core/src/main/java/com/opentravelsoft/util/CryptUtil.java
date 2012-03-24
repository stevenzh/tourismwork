package com.opentravelsoft.util;

import java.util.Random;


public class CryptUtil
{

    private CryptUtil()
    {
    }

    public static CryptUtil getInstance()
    {
        return cu != null ? cu : (cu = new CryptUtil());
    }

    public static String randomcrypt(int digistal[])
    {
        Random random = new Random();
        int radom = random.nextInt(1000);
        String plain = "";
        for (int i = 0; i < digistal.length; i++)
        {
            digistal[i] += radom;
            plain = plain + digistal[i] + "=";
        }

        plain = plain + radom;
        System.out.print("plain=" + plain);
        return Crypt.getInstance().encrypt(plain);
    }

    public static int[] decryptrandom(String encrypt)
    {
        String temp = Crypt.getInstance().decrypt(encrypt);
        System.out.println(temp);
        if (!temp.matches("^((\\d+)=){1,}(\\d+)$"))
            return null;
        String result[] = temp.split("=");
        int plain[] = new int[result.length - 1];
        for (int i = 0; i < plain.length; i++)
            plain[i] = Integer.parseInt(result[i])
                    - Integer.parseInt(result[result.length - 1]);

        return plain;
    }

    public static String encrypt(String str)
    {
        return Crypt.getInstance().encrypt(str);
    }

    public static String decrypt(String str)
    {
        return Crypt.getInstance().decrypt(str);
    }

    public static void main(String args[])
    {
        try
        {
            String info = randomcrypt(new int[] { 18, 22, 3, 7 });
            System.out.println("info=" + info);
            int result[] = decryptrandom(info);
            for (int i = 0; i < result.length; i++)
                System.out.println("result=" + result[i]);

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private static CryptUtil cu;
}