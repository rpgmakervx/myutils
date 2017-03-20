package org.easyarch.myutils.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.easyarch.myutils.json.JSONUtils;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 * Created by xingtianyu on 17-1-8
 * 下午11:38
 * description:
 */

public class TemplateMain {

    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File("/home/code4j/IDEAWorkspace/myutils/myutils-lang/src/main/resources/"));
        Map<String,String> mapper = new HashMap<>();
        mapper.put("usernae","xingtianyu");
        StringWriter writer = new StringWriter();
        Template temp = cfg.getTemplate("mapper.ftl");
        temp.process(mapper,writer);
        System.out.println(JSONUtils.str2Json(writer.toString()));

    }
}
