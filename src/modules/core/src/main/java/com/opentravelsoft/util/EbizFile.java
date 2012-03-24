package com.opentravelsoft.util;

import java.io.*;

import com.opentravelsoft.EbizException;

/**
 * 文件处理程序
 * 
 */
public class EbizFile extends File
{
    private static final long serialVersionUID = -7981241488583466754L;

    protected BufferedReader m_bufrReader = null;

    protected BufferedWriter m_bufrWriter = null;

    protected String strEncTypeName = "SHIFT_JIS";

    /**
     * 构造器<BR>
     * 
     * @param strFilePathname 文件完整路径
     */
    public EbizFile(String strFilePathname)
    {
        super(strFilePathname);
    }

    /**
     * 构造器<BR>
     * 
     * @param strFilePath 文件路径
     * @param strFileName 文件名
     */
    public EbizFile(String strFilePath, String strFileName)
    {
        super(strFilePath + File.separator + strFileName);
    }

    /**
     * ファイルを書き込み用にオープンする
     */
    public void openWriteFile() throws EbizException
    {
        FileOutputStream fileOutputS = null;

        try
        {
            fileOutputS = new FileOutputStream(getPath(), true);
            m_bufrWriter = new BufferedWriter(new OutputStreamWriter(
                    fileOutputS));
        } catch (FileNotFoundException e)
        {
            if (fileOutputS != null)
            {
                try
                {
                    fileOutputS.close();
                } catch (IOException ioe)
                {
                    // LOG出力
                }
            }
            // LOG出力
            throw new EbizException(e.toString());
        }
    }

    /**
     * ファイルを書き込み用にオープンする
     * 
     * @param append if <code>true</code>, then bytes will be written to the end
     *            of the file rather than the beginning
     */
    public void openWriteFile(boolean append) throws EbizException
    {
        FileOutputStream fileOutputS = null;

        try
        {
            fileOutputS = new FileOutputStream(getPath(), append);
            m_bufrWriter = new BufferedWriter(new OutputStreamWriter(
                    fileOutputS));
        } catch (FileNotFoundException e)
        {
            if (fileOutputS != null)
            {
                try
                {
                    fileOutputS.close();
                } catch (IOException ioe)
                {
                    // LOG出力
                }
            }
            // LOG出力
            throw new EbizException(e.getMessage());
        }
    }

    /**
     * ファイルを書き込み用にオープンする
     */
    public void openWriteFile(String strLan) throws EbizException
    {
        FileOutputStream fileOutputS = null;

        try
        {
            fileOutputS = new FileOutputStream(getPath(), true);
            m_bufrWriter = new BufferedWriter(new OutputStreamWriter(
                    fileOutputS, strLan));
        } catch (FileNotFoundException e)
        {
            if (fileOutputS != null)
            {
                try
                {
                    fileOutputS.close();
                } catch (IOException ioe)
                {
                    // LOG出力
                }
            }
            // LOG出力
            throw new EbizException(e.getMessage());
        } catch (UnsupportedEncodingException e)
        {
            try
            {
                fileOutputS.close();
            } catch (IOException ioe)
            {
                // LOG出力
            }
            // LOG出力
        }
    }

    /**
     * ファイルを書き込み用にオープンする
     * 
     * @param append if <code>true</code>, then bytes will be written to the end
     *            of the file rather than the beginning
     */
    public void openWriteFile(String strLan, boolean append)
            throws EbizException
    {
        FileOutputStream fileOutputS = null;

        try
        {
            fileOutputS = new FileOutputStream(getPath(), append);
            m_bufrWriter = new BufferedWriter(new OutputStreamWriter(
                    fileOutputS, strLan));
        } catch (FileNotFoundException e)
        {
            if (fileOutputS != null)
            {
                try
                {
                    fileOutputS.close();
                } catch (IOException ioe)
                {
                    // LOG出力
                }
            }
            // LOG出力
            throw new EbizException(e.toString());
        } catch (UnsupportedEncodingException e)
        {
            try
            {
                fileOutputS.close();
            } catch (IOException ioe)
            {
                // LOG出力
            }
            // LOG出力
        }
    }

    /**
     * ファイルを読み込み用にオープンする <BR>
     * 
     * @exception EbizException
     */
    public void openReadFile() throws EbizException
    {
        FileInputStream fileInputS = null;

        try
        {
            fileInputS = new FileInputStream(this);
            m_bufrReader = new BufferedReader(new InputStreamReader(fileInputS));
        } catch (FileNotFoundException e)
        {
            if (fileInputS != null)
            {
                try
                {
                    fileInputS.close();
                } catch (IOException ioe)
                {
                    // LOG出力
                }
            }
            // LOG出力
            throw new EbizException(e.getMessage());
        }
    }

    /**
     * ファイルを読み込み用にオープンする <BR>
     * 
     * @exception EbizException
     */
    public void openReadFile(String strLan) throws EbizException
    {
        FileInputStream fileInputS = null;

        try
        {
            fileInputS = new FileInputStream(this);
            m_bufrReader = new BufferedReader(new InputStreamReader(fileInputS,
                    strLan));
        } catch (FileNotFoundException e)
        {
            if (fileInputS != null)
            {
                try
                {
                    fileInputS.close();
                } catch (IOException ioe)
                {
                    // LOG出力
                }
            }
            // LOG出力
            throw new EbizException(e.getMessage());
        } catch (UnsupportedEncodingException e)
        {
            try
            {
                fileInputS.close();
            } catch (IOException ioe)
            {
                // LOG出力
            }
            // LOG出力
        }
    }

    /**
     * ファイルのクローズ <BR>
     */
    public void closeFile()
    {
        try
        {
            if (m_bufrWriter != null)
            {
                m_bufrWriter.close();
            }
            if (m_bufrReader != null)
            {
                m_bufrReader.close();
            }
        } catch (IOException e)
        {
            // LOG出力
        } finally
        {
            try
            {
                if (m_bufrReader != null)
                {
                    m_bufrReader.close();
                }
            } catch (IOException e)
            {
                // LOG出力
            }
        }
    }

    /**
     * ファイルのflush <BR>
     */
    public void flushWriteFile()
    {
        try
        {
            if (m_bufrWriter != null)
            {
                m_bufrWriter.flush();
            }
        } catch (IOException e)
        {
            // LOG出力
        }
    }

    /**
     * 指定されたファイルの作成 <BR>
     * 
     * @return true ファイルが作成できた場合 false ファイル作成に失敗
     * 
     * @exception EbizException
     */
    public boolean createFile() throws EbizException
    {
        boolean blCheckFile = false;
        String strDir;
        File fileParent;

        try
        {
            strDir = getParent();
            fileParent = new File(strDir);
            if (!fileParent.isDirectory())
            {
                fileParent.mkdirs();
            }
            blCheckFile = createNewFile();
        } catch (IOException e)
        {
            // LOG出力
            throw new EbizException(e.getMessage());
        }
        return blCheckFile;
    }

    /**
     * 指定されたファイルを削除する<BR>
     * 
     * @return true ファイルが削除された。false ファイルが削除できない。
     */
    public boolean deleteFile()
    {
        if (!exists())
        {
            return (false);
        }
        return (delete());
    }

    /**
     * ファイルの１行読み込み<BR>
     * 
     * @return 読み込んだファイルの１行
     * 
     * @exception EbizException
     */
    public String readLine() throws EbizException
    {
        String strReadLine = null;

        try
        {
            strReadLine = m_bufrReader.readLine();
        } catch (IOException e)
        {
            // LOG出力
            throw new EbizException(e.getMessage());
        }
        return (strReadLine);
    }

    /**
     * 行を読み飛ばす <BR>
     * 
     * @param nline 読み飛ばす行数
     * 
     * @exception EbizException
     */
    public void skipLine(int nline) throws EbizException
    {
        try
        {
            for (int i = 0; i < nline; i++)
            {
                m_bufrReader.readLine();
            }
        } catch (IOException e)
        {
            // LOG出力
            throw new EbizException(e.getMessage());
        }
    }

    /**
     * 指定された行をファイルに書き込む<BR>
     * 
     * @param strWrite 書き込む行
     * 
     * @exception EbizException
     */
    public void writeLine(String strWrite) throws EbizException
    {
        try
        {
            if (strEncTypeName != null)
            {
                strWrite = new String(strWrite.getBytes("SHIFT_JIS"),
                        strEncTypeName);
            }
            m_bufrWriter.write(strWrite, 0, strWrite.length());
            m_bufrWriter.newLine();
        } catch (IOException e)
        {
            // LOG出力
            throw new EbizException(e.getMessage());
        }
    }

    /**
     * 指定された行をファイルに書き込む<BR>
     * 
     * @param strWrite 書き込む行
     * 
     * @exception EbizException
     */
    public void writeLine(String strWrite, String strType) throws EbizException
    {
        try
        {
            strWrite = new String(strWrite.getBytes(strType));
            m_bufrWriter.write(strWrite, 0, strWrite.length());
            m_bufrWriter.newLine();
        } catch (IOException e)
        {
            // LOG出力
            throw new EbizException(e.getMessage());
        }
    }

    public static boolean copyFile(EbizFile fromTarget, EbizFile toTarget)
    {
        String str = "";
        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        if (!fromTarget.exists())
        {
            return false;
        }

        try
        {
            // file is null
            fromTarget.openReadFile();
            str = fromTarget.readLine();
            fromTarget.closeFile();
            if (ConvertUtils.isNull(str))
            {
                return false;
            }

            in = new BufferedInputStream(new FileInputStream(fromTarget));
            out = new BufferedOutputStream(new FileOutputStream(toTarget));
            int c;

            while ((c = in.read()) != -1)
            {
                out.write(c);
            }

            in.close();
            out.close();

        } catch (FileNotFoundException e)
        {
            // LOG出力
            // throw exception
            return false;
        } catch (IOException e)
        {
            // LOG出力
            // throw exception
            return false;
        } catch (EbizException e)
        {
            // LOG出力
            // throw exception
            return false;
        } finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException e)
            {
                // LOG出力
            }
            try
            {
                if (out != null)
                {
                    out.close();
                }
            } catch (IOException e)
            {
                // LOG出力
            }
        }

        return true;
    }

    public static boolean deleteFileOrDir(File file)
    {
        if (!file.exists())
        {
            return false;
        }

        if (file.isFile())
        {
            if (!file.canWrite())
            {
                String message = file + " is can not be write";
                // LOG出力
                throw new IllegalArgumentException(message);
            }

            if (!file.delete())
            {
                String message = "Unable to delete directory " + file + ".";
                // LOG出力
                throw new IllegalArgumentException(message);
                // return false;
            }
        } else
        {
            try
            {
                cleanDirectory(file);
            } catch (Exception e)
            {
                String message = "Unable to delete directory " + file + ".";
                // LOG出力
                throw new IllegalArgumentException(message);
            }
            if (!file.delete())
            {
                String message = "Unable to delete directory " + file + ".";
                // LOG出力
                throw new IllegalArgumentException(message);
            }

        }
        return true;
    }

    /**
     * Clean a directory without deleting it.
     * 
     * @param directory directory to clean
     * @throws IOException in case cleaning is unsuccessful
     */
    public static void cleanDirectory(File directory)
    {
        if (!directory.exists())
        {
            String message = directory + " does not exist";
            // LOG出力
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory())
        {
            String message = directory + " is not a directory";
            // LOG出力
            throw new IllegalArgumentException(message);
        }

        if (!directory.canWrite())
        {
            String message = directory + " is can not be write";
            // LOG出力
            throw new IllegalArgumentException(message);
        }

        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            File file = files[i];
            deleteFileOrDir(file);
        }
    }

    /**
     * メソッド名： sizeOfDirectory 機能概要： Recursively count size of a directory (sum *
     * of the length of all files).
     * 
     * @param directory directory directory to inspect
     * @return size of directory in bytes.
     */
    public static long sizeOfDirectory(File directory)
    {
        if (!directory.isDirectory())
        {
            String message = directory + " is not a directory";
            // LOG出力
            throw new IllegalArgumentException(message);
        }
        if (!directory.canRead())
        {
            String message = directory + " is can not be read";
            // LOG出力
            throw new IllegalArgumentException(message);
        }

        if (!canExec(directory))
        {
            String message = directory + " is can not be exec";
            // LOG出力
            throw new IllegalArgumentException(message);
        }

        long size = 0;

        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            File file = files[i];

            if (file.isDirectory())
            {
                size += sizeOfDirectory(file);
            } else
            {
                size += file.length();
            }
        }

        return size;
    }

    /**
     * Tests whether the application can execute the directory denoted by this
     * abstract pathname.
     * 
     * @param directory
     * @return
     */
    public static boolean canExec(File directory)
    {
        if (directory.canRead() && directory.isDirectory())
        {
            File[] files = directory.listFiles();
            if (files.length > 0)
            {
                if (!files[0].exists())
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * true: file size is zero
     */
    public boolean isNull() throws EbizException
    {
        String strLine = "";

        openReadFile();

        while (!ConvertUtils.isNull(strLine = readLine()))
        {
            closeFile();

            return false;
        }

        closeFile();

        return (strLine.length() != -1);
    }
}
