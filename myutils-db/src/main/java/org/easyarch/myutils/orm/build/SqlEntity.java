package org.easyarch.myutils.orm.build;

import java.util.ArrayList;
import java.util.List;

import static org.easyarch.myutils.orm.parser.Token.KEY;

/**
 * Description :
 * Created by xingtianyu on 17-1-21
 * 下午7:19
 * description:
 */

public class SqlEntity {

    private List<Object> vals;

    private List<String> names;

    public SqlEntity() {
        names = new ArrayList<>();
        vals = new ArrayList<>();
    }

    public List<Object> getVals() {
        return vals;
    }

    public void setVals(List<Object> vals) {
        this.vals = vals;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        for (String name:names){
            int beginIndex = KEY.length();
            String word = name.substring(beginIndex, name.length() - beginIndex);
            this.names.add(word);
        }
    }

    public void addName(String name){
        int beginIndex = KEY.length();
        String word = name.substring(beginIndex, name.length() - beginIndex);
        this.names.add(word);
    }

    public void addVal(Object val){
        this.vals.add(val);
    }
}
