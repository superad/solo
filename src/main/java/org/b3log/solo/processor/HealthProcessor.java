package org.b3log.solo.processor;

import org.b3log.latke.http.RequestContext;
import org.b3log.latke.ioc.Singleton;

import java.util.Map;

/**
 * @Auther: fgm0129
 * @Date: 2024/2/18 16:39
 * @Description
 */
@Singleton
public class HealthProcessor {

    public void health(final RequestContext context) {
        context.setRenderer(new SkinRenderer(context, "health/health.ftl"));
        final Map<String, Object> dataModel = context.getRenderer().getRenderDataModel();
        dataModel.put("status", "success");
    }

}
