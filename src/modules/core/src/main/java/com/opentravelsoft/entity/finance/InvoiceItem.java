package com.opentravelsoft.entity.finance;

/**
 * 发票细目
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $DatFe: 2009/02/07 14:11:58 $
 */
public class InvoiceItem
{
    private int id;

    /** 费用 */
    private double expense;

    /** 项目名 */
    private String item;

    public InvoiceItem()
    {
        id = 0;
        expense = 0d;
        item = "";
    }

    public InvoiceItem(String item, double expense)
    {
        this.expense = expense;
        this.item = item;
    }

    public double getExpense()
    {
        return expense;
    }

    public void setExpense(double expense)
    {
        this.expense = expense;
    }

    public String getItem()
    {
        return item;
    }

    public void setItem(String item)
    {
        this.item = item;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

}