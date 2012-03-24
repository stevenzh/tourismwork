package com.opentravelsoft.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;

import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.entity.SysConfig;
import com.opentravelsoft.entity.product.ProductType;
import com.opentravelsoft.model.MenuItem;
import com.opentravelsoft.model.Module;
import com.opentravelsoft.model.Shortcut;
import com.opentravelsoft.service.ShortcutManager;
import com.opentravelsoft.service.module.ModuleService;
import com.opentravelsoft.service.product.ProductService;
import com.opentravelsoft.service.setting.SysConfigService;
import com.opentravelsoft.util.XMLUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

public class SysSettingInterceptor extends AbstractInterceptor {
  private static final long serialVersionUID = 7884275706687577353L;

  protected static final Log logger = LogFactory
      .getLog(SysSettingInterceptor.class);

  private ModuleService moduleService;

  private ProductService productService;

  private SysConfigService configService;

  private ShortcutManager shortcutManager;

  protected static final String XML_PATH = "WEB-INF/ebizConfig.xml";

  @Autowired
  public void setModuleService(ModuleService moduleService) {
    this.moduleService = moduleService;
  }

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  @Autowired
  public void setConfigService(SysConfigService configService) {
    this.configService = configService;
  }

  @Autowired
  public void setShortcutManager(ShortcutManager shortcutManager) {
    this.shortcutManager = shortcutManager;
  }

  public String intercept(ActionInvocation actionInvocation) throws Exception {
    ServletContext context = ServletActionContext.getServletContext();
    ActionContext act = actionInvocation.getInvocationContext();

    Map<String, Object> application = act.getApplication();

    String gingkgoHome = System.getProperty("gingkgo.home");
    if (null == gingkgoHome)
      System.setProperty("gingkgo.home", context.getRealPath(""));

    // 设置系统模块
    if (null == application.get(EbizCommon.EBIZ_APP_MODULES)) {
      List<Module> modules = moduleService.roGetModuleList(true);
      Set<String> ms = new HashSet<String>(0);
      for (Module module : modules) {
        ms.add(module.getModuleName());
      }
      application.put(EbizCommon.EBIZ_APP_MODULES, ms);
    }

    // 设置产品组成
    if (null == application.get(EbizCommon.EBIZ_APP_PACKAGE_ITEM)) {
      List<ProductItem> items = productService
          .getProductItems(ProductType.Package);
      application.put(EbizCommon.EBIZ_APP_PACKAGE_ITEM, items);
    }

    // 主菜单
    if (null == application.get(EbizCommon.EBIZ_APP_MAIN_MENU)) {
      List<MenuItem> items = new ArrayList<MenuItem>();
      items.add(new MenuItem("Desktop", "桌面", "", "ROLE_USER"));
      items.add(new MenuItem("Products", "产品资源", "", "ROLE_PRODUCT"));
      items.add(new MenuItem("Order", "订单管理", "", "ROLE_SALES"));
      items.add(new MenuItem("Operate", "计调操作", "", "ROLE_OPERATOR"));
      items.add(new MenuItem("Express", "配送管理", "", "ROLE_TRANSPORT"));
      items.add(new MenuItem("CRM", "客户管理", "", "ROLE_AGENT_MANAGER"));
      items.add(new MenuItem("Finance", "财务结算", "", "ROLE_FINANCE"));
      items.add(new MenuItem("Stat", "统计分析", "", "ROLE_SUPERUSER"));
      items.add(new MenuItem("System", "系统设置", "", "ROLE_SUPERUSER"));
      items.add(new MenuItem("Config", "设置", "", "ROLE_SUPERUSER"));
      items.add(new MenuItem("Company", "公司设置", "", "ROLE_SUPERUSER"));
      for (MenuItem menuItem : items) {
        List<Shortcut> cuts = shortcutManager.getShortcutByModule(menuItem
            .getItemName());
        for (Shortcut shortcut : cuts) {
          menuItem.getChild().add(
              new MenuItem(shortcut.getModuleName(), shortcut.getDisplayName(),
                  shortcut.getRelativePath(), shortcut.getRoles()));
        }
      }
      application.put(EbizCommon.EBIZ_APP_MAIN_MENU, items);
    }

    // 设置产品组成
    if (null == application.get(EbizCommon.EBIZ_SYS_CONFIG)) {
      List<SysConfig> items = configService.getAllConfig();
      for (SysConfig config : items) {
        application.put(config.getName(), config.getValue());
      }
      application.put(EbizCommon.EBIZ_SYS_CONFIG, "OK");
    }

    // XML设置参数
    if (null == application.get(EbizCommon.EBIZ_RES_CONFIG)) {
      try {
        application.put(EbizCommon.EBIZ_RES_CONFIG,
            XMLUtility.getInstance(context.getRealPath(XML_PATH)));
      } catch (ParserConfigurationException pce) {
        logger.error("", pce);
      } catch (IOException ioe) {
        logger.error("", ioe);
      } catch (SAXException saxe) {
        logger.error("", saxe);
      } catch (Exception e) {
        logger.error("", e);
      }
    }

    ActionContext.getContext().getValueStack().push(this);

    return actionInvocation.invoke();
  }

}
