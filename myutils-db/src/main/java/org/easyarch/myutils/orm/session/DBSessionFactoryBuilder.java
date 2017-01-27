package org.easyarch.myutils.orm.session;

import org.easyarch.myutils.orm.parser.XmlParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Description :
 * Created by xingtianyu on 17-1-26
 * 上午12:03
 * description:
 */

public class DBSessionFactoryBuilder {

    public DBSessionFactory build(InputStream is){
        XmlParser xmlParser = new XmlParser(is);
        return new DBSessionFactory(xmlParser.parse());
    }
    public DBSessionFactory build(Reader reader){
        XmlParser xmlParser = new XmlParser(reader);
        return new DBSessionFactory(xmlParser.parse());
    }
    public DBSessionFactory build(File file){
        try {
            if (!file.exists()){
                throw new FileNotFoundException("configuration file not found");
            }
            XmlParser xmlParser = new XmlParser(file.getPath());
            return new DBSessionFactory(xmlParser.parse());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
