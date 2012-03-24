package com.opentravelsoft.entity.product;

import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;

public class ProductBase
{

    private String name;

    private Team team;

    private Employee assigned;

    public ProductBase()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Team getTeam()
    {
        return team;
    }

    public void setTeam(Team team)
    {
        this.team = team;
    }

    public Employee getAssigned()
    {
        return assigned;
    }

    public void setAssigned(Employee assigned)
    {
        this.assigned = assigned;
    }

}
