package org.easyarch.myutils.orm.session;

import org.easyarch.myutils.orm.mapping.MapperScanner;
import org.easyarch.myutils.orm.parser.XmlParser;

import java.io.*;

/**
 * Description :
 * Created by xingtianyu on 17-1-26
 * 上午12:03
 * description:
 */

public class DBSessionFactoryBuilder {

    public DBSessionFactory build(InputStream is) throws Exception {
        return build(new InputStreamReader(is));
    }
    public DBSessionFactory build(Reader reader) throws Exception {
        XmlParser xmlParser = new XmlParser(reader);
        Configuration configuration = xmlParser.parse();
        MapperScanner scanner = new MapperScanner();
        scanner.scan(configuration.getPacakge());
        return new DBSessionFactory(configuration);
    }
    public DBSessionFactory build(File file) throws Exception {
        if (!file.exists()){
            throw new FileNotFoundException("configuration file not found");
        }
        XmlParser xmlParser = new XmlParser(file.getPath());
        Configuration configuration = xmlParser.parse();
        MapperScanner scanner = new MapperScanner();
        scanner.scan(configuration.getPacakge());
        return new DBSessionFactory(configuration);
    }
}
