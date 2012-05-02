package com.opentravelsoft.webapp.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.entity.SysConfig;
import com.opentravelsoft.entity.product.ProductType;
import com.opentravelsoft.service.product.ProductService;
import com.opentravelsoft.service.setting.SysConfigService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class SysSettingInterceptor extends AbstractInterceptor {
  private static final long serialVersionUID = 7884275706687577353L;

  protected static final Log logger = LogFactory
      .getLog(SysSettingInterceptor.class);

  @Autowired
  private ProductService productService;

  @Autowired
  private SysConfigService configService;

  public String intercept(ActionInvocation actionInvocation) throws Exception {
    logger.debug("intercepter sys setting.");
    ServletContext context = ServletActionContext.getServletContext();
    ActionContext act = actionInvocation.getInvocationContext();
    Map<String, Object> application = act.getApplication();

    String gingkgoHome = System.getProperty("gingkgo.home");
    if (null == gingkgoHome)
      System.setProperty("gingkgo.home", context.getRealPath(""));

    // 设置产品组成
    if (null == application.get(EbizCommon.EBIZ_APP_PACKAGE_ITEM)) {
      List<ProductItem> items = productService
          .getProductItems(ProductType.Package);
      application.put(EbizCommon.EBIZ_APP_PACKAGE_ITEM, items);
    }

    // 设置产品组成
    if (null == application.get(EbizCommon.EBIZ_SYS_CONFIG)) {
      List<SysConfig> items = configService.getAllConfig();
      for (SysConfig config : items) {
        application.put(config.getName(), config.getValue());
      }
      application.put(EbizCommon.EBIZ_SYS_CONFIG, "OK");
    }

    ActionContext.getContext().getValueStack().push(this);

    return actionInvocation.invoke();
  }

}
