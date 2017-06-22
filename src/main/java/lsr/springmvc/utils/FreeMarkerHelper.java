package lsr.springmvc.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lsr on 2017/3/17.
 */
public class FreeMarkerHelper {

    static Configuration configuration;
    static String TemplateDirectory = "src/main/resources/templates/";

    public static String OUTPUT_CHARSET = "UTF-8";

    static{
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        try {
            configuration.setDirectoryForTemplateLoading(new File(TemplateDirectory));
            configuration.setDefaultEncoding(OUTPUT_CHARSET);
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getContent(String tplName,Map<String,Object> dataModel) throws IOException, TemplateException {
        Template tpl = configuration.getTemplate(tplName);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(bos);
        tpl.process(dataModel,writer);
        return bos.toString(OUTPUT_CHARSET);
    }

    public static void generateFile(String tplName,String outputPath,Map<String,Object> dataModel) throws IOException, TemplateException {
        String content = getContent(tplName,dataModel);
        if(content!=null){
            File file = new File(outputPath);
            if(!file.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
                file.getParentFile().mkdirs();				//不存在就全部创建
            }
            FileOutputStream output = new FileOutputStream(file);
            byte[] buff = content.getBytes();
            output.write(buff,0,buff.length);
        }
    }

    public static void main(String[] args){
        Map<String,Object> user = new HashMap<String, Object>();
        user.put("name","Anna");
        user.put("another","boss");
        try {
            String outputPath = "src/main/resources/templates/helloworld.html";
            String tplName = "helloworld.ftl";
            generateFile(tplName,outputPath,user);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
