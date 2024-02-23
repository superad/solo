package org.b3log.solo.processor.tools;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.b3log.latke.http.RequestContext;
import org.b3log.latke.ioc.Inject;
import org.b3log.latke.ioc.Singleton;
import org.b3log.latke.service.ServiceException;
import org.b3log.solo.processor.ArticleProcessor;
import org.b3log.solo.processor.SkinRenderer;
import org.b3log.solo.service.DataModelService;
import org.b3log.solo.service.OptionQueryService;
import org.json.JSONObject;

import java.util.Map;

/**
 * @Auther: fgm0129
 * @Date: 2024/2/24 00:13
 * @Description
 */
@Singleton
public class ToolsProcessor {

    private static final Logger LOGGER = LogManager.getLogger(ToolsProcessor.class);

    /**
     * DataModelService.
     */
    @Inject
    private DataModelService dataModelService;

    @Inject
    private OptionQueryService optionQueryService;

    public void jsonFormat(final RequestContext context){
        context.setRenderer(new SkinRenderer(context, "tools/json/json-format.ftl"));
        Map<String, Object> dataModel = context.getDataModel();
        final JSONObject preference = optionQueryService.getPreference();
        try {
            dataModelService.fillCommon(context, dataModel, preference);
            dataModelService.fillFaviconURL(dataModel, preference);
            dataModelService.fillUsite(dataModel);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage(), e);
            context.sendError(404);
        }

    }




}
