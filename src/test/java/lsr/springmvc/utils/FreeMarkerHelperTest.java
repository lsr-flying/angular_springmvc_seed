package lsr.springmvc.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lsr on 2017/3/19.
 */
public class FreeMarkerHelperTest {

    @Test
    public void testGenerateWebPage(){
        Map<String,Object> viewModel = new HashMap<String, Object>();
        viewModel.put("viewName","testView");
        viewModel.put("viewController","testViewCtrl");
        try {
            String outputPath = "src/main/resources/templates/webJavascriptTemplate.html";
            String tplName = "codeGenerator/webJavascriptTemplate.ftl";
            FreeMarkerHelper.generateFile(tplName,outputPath,viewModel);

            outputPath = "src/main/resources/templates/webPageTemplate.html";
            tplName = "codeGenerator/webPageTemplate.ftl";
            FreeMarkerHelper.generateFile(tplName,outputPath,viewModel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
