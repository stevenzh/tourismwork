package com.opentravelsoft.entity;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class TraitType implements java.io.Serializable
{

    private static final long serialVersionUID = -6085267107524676624L;

    private int traitId;

    private String name;

    public TraitType()
    {
    }

    public TraitType(int traitId)
    {
        this.traitId = traitId;
    }

    public TraitType(int traitId, String name)
    {
        this.traitId = traitId;
        this.name = name;
    }

    public int getTraitId()
    {
        return this.traitId;
    }

    public void setTraitId(int traitId)
    {
        this.traitId = traitId;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
