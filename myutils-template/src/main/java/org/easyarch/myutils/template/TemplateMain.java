package org.easyarch.myutils.template;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;

/**
 * Description :
 * Created by xingtianyu on 17-1-8
 * 下午11:38
 * description:
 */

public class TemplateMain {

    public static void main(String[] args) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File("/home/code4j/IDEAWorkspace/volunteer/template"));
        Template temp = cfg.getTemplate("Template.ftl");
        System.out.println(temp.getDefaultNS());
    }
}
