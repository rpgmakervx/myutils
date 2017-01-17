package org.easyarch.myutils.orm.parser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.easyarch.myutils.collection.CollectionUtils;
import org.easyarch.myutils.lang.StringUtils;
import org.easyarch.myutils.orm.bean.SqlBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.easyarch.myutils.orm.parser.Token.PLACEHOLDER;
import static org.easyarch.myutils.orm.parser.Token.SEPERTOR;

/**
 * Description :
 * Created by xingtianyu on 17-1-11
 * 上午12:41
 * description:
 */

public class SQLParser {

    private Statement statement;

    private String sql;

    private String finalSql;

    private Map<Integer,String> paramMapper;

    public SQLParser(String sql){
        this.sql = sql;
        finalSql = sql;
        try {
            statement = CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer,String> getSqlParamMapper(){
        paramMapper = new HashMap<>();
        Select select = (Select) statement;
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where = plain.getWhere();
        filterWhereColumns(where,paramMapper);
        for (Map.Entry<Integer,String> entry:paramMapper.entrySet()){
            finalSql = finalSql.replace(StringUtils.center(entry.getValue(),0,SEPERTOR),PLACEHOLDER);
        }
        return paramMapper;
    }

    /**
     * @param whereAfter
     * @param mapper
     * 注意：Column对象在getColumnName的时候会根据 . 做分割
     */
    private void filterWhereColumns(Expression whereAfter,Map<Integer,String> mapper){
        if (whereAfter instanceof Column){
            return;
        }
        if (whereAfter instanceof BinaryExpression){
            BinaryExpression binaryExpression = (BinaryExpression) whereAfter;
            Expression leftExpression = binaryExpression.getLeftExpression();
            Expression rightExpression = binaryExpression.getRightExpression();
            if (leftExpression instanceof Column &&rightExpression instanceof Column){
                String columnName = rightExpression.toString();
                mapper.put(mapper.size()+1,columnName);
            }
            // 访问左子树
            filterWhereColumns(leftExpression,mapper);
            // 访问右子树
            filterWhereColumns(rightExpression,mapper);
        }else if (whereAfter instanceof Between){
            Between between = (Between) whereAfter;
            //between 没有只有左子树有column，右子树没有
            Expression frontVal = between.getBetweenExpressionStart();
            Expression backVal = between.getBetweenExpressionEnd();
            mapper.put(mapper.size() + 1,frontVal.toString());
            mapper.put(mapper.size() + 1,backVal.toString());
            filterWhereColumns(between.getLeftExpression(),mapper);
        }else if (whereAfter instanceof InExpression){
            InExpression inExpression = (InExpression) whereAfter;
            ItemsList itemsList = inExpression.getRightItemsList();
            if (itemsList instanceof ExpressionList){
                ExpressionList expressionList = (ExpressionList) itemsList;
                List<Expression> expressions = expressionList.getExpressions();
                if (CollectionUtils.isNotEmpty(expressions)){
                    for (Expression e:expressions){
                        mapper.put(mapper.size()+1,e.toString());
                    }
                }
            }
            filterWhereColumns(inExpression.getLeftExpression(),mapper);
        }else if (whereAfter instanceof LikeExpression){
            LikeExpression likeExpression = (LikeExpression) whereAfter;
            Expression val = likeExpression.getRightExpression();
            mapper.put(mapper.size()+1,val.toString());
            filterWhereColumns(likeExpression,mapper);
        }
    }

    public String getOriginSql(){
        return sql;
    }

    public String getFinalSql(){
        return finalSql;
    }
    public static void main(String[] args) throws JSQLParserException {
//        Statement statement = CCJSqlParserUtil.parse("select a,b,c from test where test.id = ? and oid in (?,?,?) " +
//                "and  user.age = ? and user.create_at between ? and ? and label like ?");
//        Select select = (Select) statement;
//        PlainSelect plain = (PlainSelect) select.getSelectBody();
//        Expression where = plain.getWhere();
        List<SqlBean> columnNames = new ArrayList<>();
        SQLParser parser = new SQLParser("select a,b,c from test where id = $user.id$ and oid in ($map.pid$,$map.oid$,$map.mid$) " +
                "and age = $map.age$ and create_at between $map.begin$ and $map.end$ and label like $map.label$");
        for (Map.Entry<Integer,String> entry:parser.getSqlParamMapper().entrySet()){
            System.out.println(StringUtils.strip(entry.getValue(),SEPERTOR));;
        }
        System.out.println("finalSql:"+parser.getFinalSql());
//        Map<Integer,String> map = new HashMap<>();
//        map.put(2,"2");
//        map.put(3,"3");
//        map.put(1,"1");
//        System.out.println(map.get(map.size()));
//        System.out.println(parser.getCurrentIndex(map));
    }
}
