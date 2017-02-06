package org.easyarch.myutils.orm.session;

import org.easyarch.myutils.file.FileUtils;
import org.easyarch.myutils.lang.StringUtils;
import org.easyarch.myutils.orm.entity.SqlEntity;
import org.easyarch.myutils.orm.mapping.MapperScanner;
import org.easyarch.myutils.orm.parser.JSParser;
import org.easyarch.myutils.pool.ds.DBCPool;
import org.easyarch.myutils.pool.ds.DBCPoolFactory;
import org.easyarch.myutils.reflection.ReflectUtils;

import javax.sql.DataSource;
import java.io.*;
import java.util.*;

/**
 * Description :
 * Created by xingtianyu on 17-1-23
 * 上午12:41
 * description:
 */

public class Configuration {

    public static final String JADE = "jade";
    public static final String INTERFACE = "interface";
    public static final String PACKAGE = "package";
    public static final String MAPPER = "mapper";
    public static final String LOCATION = "location";
    public static final String DATASOURCECLASS = "class";
    public static final String DATASOURCE = "datasource";

    public static final String CLASSPATH = "classpath:";
    public static final String JS = ".js";

    private volatile Map<String, Map<String, String>> mappedSqls = new HashMap<>();

    private volatile Map<String,Map<String,Object>> configMap;

    private String configFilePath;

    private DataSource dataSource;

    private List<Reader> sqlMapperReaders;

    public Configuration(String configPath,Map<String,Map<String,Object>> content) {
        this.configFilePath = configPath;
        this.configMap = content;
        sqlMapperReaders = new ArrayList<>();
    }

    private void initMapper(){
        try {
            Map<String,Object> mapper = configMap.get(MAPPER);
            String basePath = String.valueOf(mapper.get(LOCATION));
            File baseDir = new File(basePath);
            if (basePath.startsWith(CLASSPATH)){
                //去掉classpath:关键字
                basePath = basePath.replace(CLASSPATH,"");
                //连接classpath目录和用户输入目录
                basePath = MapperScanner.CLASSPATH + basePath;
                System.out.println("basePath:"+basePath);
                //遍历相对路径下的所有目录中的文件
                List<File> mapperFiles = FileUtils.listFileRecursive(basePath);
                for (File file : mapperFiles){
                    String filename = file.getPath();
                    if (!filename.endsWith(JS)){
                        continue;
                    }
                    //获得具体文件在classpath下的相对路径
                    String relativePath = filename.substring(MapperScanner.CLASSPATH.length(),filename.length());
                    Reader reader = new InputStreamReader(
                            getClass().getClassLoader().getResourceAsStream(relativePath));
                    sqlMapperReaders.add(reader);
                }
            }else if(baseDir.isAbsolute()){
                List<File> mapperFiles = FileUtils.listFileRecursive(baseDir);
                for (File file:mapperFiles){
                    if (!file.getPath().endsWith(JS)){
                        continue;
                    }
                    FileReader reader = new FileReader(file);
                    sqlMapperReaders.add(reader);
                }
            }else{
                //prefixPath 就是最底层目录路径
                String prefixPath = FileUtils.getBottomDir(configFilePath);
                basePath = prefixPath + basePath;
                List<File> mapperFiles = FileUtils.listFileRecursive(basePath);
                for (File file:mapperFiles){
                    if (!file.getPath().endsWith(JS)){
                        continue;
                    }
                    FileReader reader = new FileReader(file);
                    sqlMapperReaders.add(reader);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initDataSource(){
        Properties prop = new Properties();
        Map<String,Object> mapper = configMap.get(MAPPER);
        String classname = String.valueOf(mapper.get(DATASOURCECLASS));
        try {
            String baseFilePath = String.valueOf(mapper.get(LOCATION));
            File baseFile = new File(baseFilePath);
            if (baseFilePath.startsWith(CLASSPATH)){
                //去掉classpath:关键字
                baseFilePath = baseFilePath.replace(CLASSPATH,"");
                //连接classpath目录和用户输入目录
                baseFilePath = MapperScanner.CLASSPATH + baseFilePath;
                System.out.println("baseFilePath:"+baseFilePath);
            }else if(!baseFile.isAbsolute()){
                //prefixPath 就是最底层目录路径
                String prefixPath = FileUtils.getBottomDir(configFilePath);
                baseFilePath = prefixPath + baseFilePath;
            }
            prop.load(new FileInputStream(baseFilePath));
            //引用外部的数据库连接池
            if (StringUtils.isNotEmpty(classname)&&!classname.equals(DBCPool.class.getName())){
                Object dataSource = ReflectUtils.newInstance(classname);
                for (Object key:prop.keySet()){
                    ReflectUtils.setFieldValue(dataSource,String.valueOf(key),prop.get(key));
                }
                this.dataSource = (DataSource) dataSource;
                return ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dataSource = DBCPoolFactory.newConfigedDBCPool(prop);
    }

    public String getPacakge(){
        Map<String,Object> pkg = configMap.get(INTERFACE);
        String packagePath = String.valueOf(pkg.get(PACKAGE));
        return packagePath;
    }

    public String getMapperLocation(){
        Map<String,Object> mapper = configMap.get(MAPPER);
        String mapperLocation = String.valueOf(mapper.get(LOCATION));
        return mapperLocation;
    }

    public List<Reader> getSqlMapperReaders(){
        return sqlMapperReaders;
    }

    /**
     * 支持其他第三方连接池（配置文件信息和本框架不同）
     * @return
     */
    public DataSource getDataSource(){
        return this.dataSource;
    }

    public String getMappedSql(String namespace, String id) {
        if (this.mappedSqls.containsKey(namespace)) {
            return mappedSqls.get(namespace).get(id);
        }
        return "";
    }

    public void addMappedSql(String namespace, String id, String sql) {
        Map<String, String> newMap = new HashMap<>();
        newMap.put(id, sql);
        if (mappedSqls.containsKey(namespace)) {
            Map<String, String> sqlMap = mappedSqls.get(namespace);
            sqlMap.putAll(newMap);
        } else {
            mappedSqls.put(namespace,newMap);
        }
    }

    public void parseMappedSql(SqlEntity sqlEntity){
        for (Reader reader:sqlMapperReaders){
            JSParser parser = new JSParser(reader);
            parser.parse(sqlEntity);
            SqlEntity entity = parser.getContent(sqlEntity.getPrefix());
            Map<String,String> map = new HashMap<>();
            map.put(sqlEntity.getSuffix(),entity.getPreparedSql());
            mappedSqls.put(sqlEntity.getPrefix(),map);
        }
    }

    public static void main(String[] args) {
//        Configuration conf = new Configuration(null);
//        conf.getDataSource();
//        System.out.println(conf.sqlMapperReaders);
//        File baseDir = new File("D://mapper/dsds");
//        System.out.println(baseDir.isAbsolute());
        System.out.println("/mapper/dsds/aaaa".lastIndexOf("/"));
    }
}
