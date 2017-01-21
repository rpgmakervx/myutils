package org.easyarch.myutils.orm.build;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-21
 * 下午7:19
 * description:
 */

public class SqlEntity {

    private List<Object> vals;

    private List<String> names;

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
        this.names = names;
    }

    public void addName(String name){
        if (names == null){
            names = new ArrayList<>();
        }
        this.names.add(name);
    }

    public void addVal(Object val){
        if (vals == null){
            this.vals = new ArrayList<>();
        }
        this.vals.add(val);
    }
}
