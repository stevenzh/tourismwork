package com.opentravelsoft.action.region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.service.resource.DestinationService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 分类搜索
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class ClassifySearchAction extends PortalAction
{

    private static final long serialVersionUID = 592951370624770376L;

    private DestinationService destinationService;

    /** 区域目的地 */
    private List<Destination> classifyRegionList = new ArrayList<Destination>();

    private List<LabelValueBean> outCityList = new ArrayList<LabelValueBean>();

    private List<Line> RouteIndexList = new ArrayList<Line>();

    /** 查询字符串 */
    private String queryString;

    /** 被查询目的地的id */
    private String queryStringId;

    private int length;

    /** 区域灯型（1:境外,2:国内） */
    private int cType = 1;

    /** 搜索的是否是直接是目的地 */
    private String isRegion;

    public void setDestinationService(DestinationService destinationService)
    {
        this.destinationService = destinationService;
    }

    /**
     * 搜索初始化
     */
    public String input()
    {
        // 搜索目的地导航列表
        findRegionList(cType);
        // outCityList = getCodeList("ebiz_outcity_sky");

        return SUCCESS;
    }

    /**
     * 区域搜索
     * 
     * @return
     */
    public String classifyregionSearch()
    {
        // 搜索目的地导航列表
        findRegionList(cType);
        outCityList = getCodeList("ebiz_outcity_sky");
        isRegion = "N";

        return SUCCESS;
    }

    /**
     * 具体目的地搜索
     * 
     * @return
     */
    public String regionSearch()
    {
        // 搜索目的地导航列表
        findRegionList(cType);
        outCityList = getCodeList("ebiz_outcity_sky");
        isRegion = "Y";

        return SUCCESS;
    }

    /**
     * 查找得到目的地列表
     * 
     * @param type
     */
    public void findRegionList(int type)
    {
        List<Destination> regionList = destinationService.getRegionList();
        Destination classifyRegion;
        Map<String, String> tempMap = new HashMap<String, String>();
        // 按区域分类
        for (Destination obj1 : regionList)
        {
            if (obj1.getParent() == null)
                continue;
            String regionName = obj1.getParent().getCnName();
            Set<Destination> regionTempList = new TreeSet<Destination>();
            if (!tempMap.containsKey(regionName))
            {
                tempMap.put(regionName, regionName);
                for (Destination obj2 : regionList)
                {
                    if (obj2.getParent() == null)
                        continue;
                    if (regionName.equals(obj2.getParent().getCnName()))
                    {
                        regionTempList.add(obj2);
                    }
                }
                if (!regionTempList.isEmpty())
                {
                    classifyRegion = new Destination();
                    classifyRegion.setCnName(regionName);
                    classifyRegion.setCode(obj1.getParent().getCode());
                    classifyRegion.setChildren(regionTempList);
                    classifyRegionList.add(classifyRegion);
                }
            }
        }

    }

    public List<Destination> getClassifyRegionList()
    {
        return classifyRegionList;
    }

    public int getCType()
    {
        return cType;
    }

    public void setCType(int type)
    {
        this.cType = type;
    }

    public List<LabelValueBean> getOutCityList()
    {
        return outCityList;
    }

    public List<Line> getRouteIndexList()
    {
        return RouteIndexList;
    }

    public String getQueryString()
    {
        return queryString;
    }

    public void setQueryString(String queryString)
    {
        this.queryString = queryString;
    }

    public int getLength()
    {
        return length;
    }

    public String getIsRegion()
    {
        return isRegion;
    }

    public void setIsRegion(String isRegion)
    {
        this.isRegion = isRegion;
    }

    public String getQueryStringId()
    {
        return queryStringId;
    }

    public void setQueryStringId(String queryStringId)
    {
        this.queryStringId = queryStringId;
    }

}
