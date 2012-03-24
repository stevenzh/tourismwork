package com.opentravelsoft.entity.finance;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:31 $
 */
public class InvoicePiece
{
    private int id;

    private String type;

    private double amount;

    public InvoicePiece()
    {

    }

    public InvoicePiece(String type, double expense)
    {
        this.type = type;
        this.amount = expense;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

}
