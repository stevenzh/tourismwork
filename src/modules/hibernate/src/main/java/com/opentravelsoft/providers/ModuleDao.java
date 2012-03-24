package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.model.Module;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
public interface ModuleDao extends GenericDao<Module, Long>
{
    List<Module> getModuleList(boolean active);

    List<Module> queryModle(String keyword);

}
