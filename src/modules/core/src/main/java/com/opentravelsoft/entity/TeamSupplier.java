package com.opentravelsoft.entity;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:28 $
 */
public class TeamSupplier implements java.io.Serializable
{

    private static final long serialVersionUID = -708497038792746996L;

    private TeamSupplierId id;

    public TeamSupplier()
    {
    }

    public TeamSupplier(TeamSupplierId id)
    {
        this.id = id;
    }

    public TeamSupplierId getId()
    {
        return this.id;
    }

    public void setId(TeamSupplierId id)
    {
        this.id = id;
    }

}
