package org.easyarch.myutils.test.orm.parser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.ExpressionListItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.AddAliasesVisitor;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.easyarch.myutils.collection.CollectionUtils;
import org.easyarch.myutils.test.orm.bean.SqlBean;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-11
 * 上午12:41
 * description:
 */

public class SQLParser {

    @Test
    public void testUpdate() throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse("update user set username = ? ,age = ? where id = 111");
        Update update = (Update) statement;
        List<Expression> expressions = update.getExpressions();
        for (Expression exp:expressions){
            System.out.println(exp);
        }
        update.getWhere();
    }

    @Test
    public void testInsert() throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse("insert into user (id,username,age,phone) values(?,?,?,?),(?,?,?,?)" +
                "on duplicate key update username = $username$,id = id");
        Insert insert = (Insert) statement;
        System.out.println(insert.getColumns());
        MultiExpressionList itemsList = (MultiExpressionList) insert.getItemsList();
        List<ExpressionList> expressionLists = itemsList.getExprList();
//        for (ExpressionList list:expressionLists){
////            list.getExpressions()
//        }
//        List<Column> columns = insert.getDuplicateUpdateColumns();
//        for (Column column:columns){
//            System.out.println(column);
//        }
        List<Expression> expressions = insert.getDuplicateUpdateExpressionList();
        for (Expression exp:expressions){
            System.out.println(((Column)exp).getColumnName());
        }
    }

    @Test
    public void testParse() throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse("select a,b,c from test where test.id = ? and oid in (?,?,?) " +
                "and  user.age = ? and user.create_at between ? and ? and label like ? and col = ?");
        Select select = (Select) statement;
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where = plain.getWhere();
        List<SqlBean> columnNames = new ArrayList<>();
        getWhereColumnName(where,columnNames);
        System.out.println(columnNames);
    }

//    public List<SqlBean> getWhereColumnNames(Expression whereAfter){
//        if (whereAfter == null){
//            return new ArrayList<>();
//        }
//        Stack<>
//        while ()
//        if (whereAfter instanceof BinaryExpression){
//            BinaryExpression binaryExpression = (BinaryExpression) whereAfter;
//            Expression leftExpression = binaryExpression.getLeftExpression();
//            Expression rightExpression = binaryExpression.getRightExpression();
//        }else if (whereAfter instanceof Between){
//            Between between = (Between) whereAfter;
//            //between 没有只有左子树有column，右子树没有
//        }else if (whereAfter instanceof InExpression){
//            InExpression inExpression = (InExpression) whereAfter;
//        }else if (whereAfter instanceof LikeExpression){
//            LikeExpression likeExpression = (LikeExpression) whereAfter;
//        }
//    }


    public void getWhereColumnName(Expression whereAfter,List<SqlBean> columnNames){
        if (whereAfter instanceof Column){
            return;
        }
        if (whereAfter instanceof BinaryExpression){
            BinaryExpression binaryExpression = (BinaryExpression) whereAfter;
            Expression leftExpression = binaryExpression.getLeftExpression();
            Expression rightExpression = binaryExpression.getRightExpression();
            if (leftExpression instanceof Column &&rightExpression instanceof JdbcParameter){
                String columnName = ((Column) leftExpression).getColumnName();
                SqlBean sqlBean = new SqlBean();
                sqlBean.setColumnName(columnName);
                sqlBean.setParams(1);
                columnNames.add(sqlBean);
            }
            // 访问左子树
            getWhereColumnName(leftExpression,columnNames);
            // 访问右子树
            getWhereColumnName(rightExpression,columnNames);
        }else if (whereAfter instanceof Between){
            Between between = (Between) whereAfter;
            //between 没有只有左子树有column，右子树没有
            Column column = (Column)between.getLeftExpression();
            SqlBean sqlBean = new SqlBean();
            sqlBean.setColumnName(column.getColumnName());
            sqlBean.setParams(2);
            columnNames.add(sqlBean);
            getWhereColumnName(between.getLeftExpression(),columnNames);
        }else if (whereAfter instanceof InExpression){
            InExpression inExpression = (InExpression) whereAfter;
            Column column = (Column)inExpression.getLeftExpression();
            ItemsList itemsList = inExpression.getRightItemsList();
            if (itemsList instanceof ExpressionList){
                ExpressionList expressionList = (ExpressionList) itemsList;
                List<Expression> expressions = expressionList.getExpressions();
                if (CollectionUtils.isNotEmpty(expressions)){
                    SqlBean sqlBean = new SqlBean();
                    sqlBean.setColumnName(column.getColumnName());
                    sqlBean.setParams(expressions.size());
                    columnNames.add(sqlBean);
                }
            }
            getWhereColumnName(inExpression.getLeftExpression(),columnNames);
        }else if (whereAfter instanceof LikeExpression){
            LikeExpression likeExpression = (LikeExpression) whereAfter;
            Column column = (Column) likeExpression.getLeftExpression();
            SqlBean sqlBean = new SqlBean();
            sqlBean.setColumnName(column.getColumnName());
            sqlBean.setParams(1);
            columnNames.add(sqlBean);
            getWhereColumnName(likeExpression,columnNames);
        }
    }

    @Test
    public void testExisit() throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse("select a,b,c from test where id = ?");
        Select select = (Select) statement;
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where = plain.getWhere();
        System.out.println(where.getClass().getName());
        ExistsExpression existsExpression = (ExistsExpression) where;
        System.out.println(existsExpression.getRightExpression().getClass().getName());
        SubSelect subSelect = (SubSelect) existsExpression.getRightExpression();
        PlainSelect subPlain = (PlainSelect) subSelect.getSelectBody();
        System.out.println(subPlain.getWhere());;
    }

    @Test
    public void demo() throws JSQLParserException{
        Statement statement = CCJSqlParserUtil.parse("select sex from test where test.age = $user.age$ and id in ($user.age$,$user.age$)");
        Select select = (Select) statement;
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        BinaryExpression binaryExpression = (BinaryExpression) plainSelect.getWhere();
        InExpression inExpression = (InExpression) binaryExpression.getRightExpression();
        BinaryExpression leftExpression = (BinaryExpression) binaryExpression.getLeftExpression();
        ExpressionList expressionList = (ExpressionList) inExpression.getRightItemsList();
        List<Expression> expressions = expressionList.getExpressions();
        System.out.println(leftExpression.getRightExpression());
        System.out.println(expressions);
        for (Expression e:expressions){
            System.out.println(e);
        }
    }

    @Test
    private void getTotalParam() throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse("select sex from test where age = ? group by sex having sex = ?");
        Select select = (Select) statement;
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        System.out.println(plainSelect.getGroupByColumnReferences());
        System.out.println(plainSelect.getHaving());
        System.out.println(plainSelect.getWhere());
    }

    /**
     * 获得where条件字段中列名，以及对应的操作符
     * @Title: getColumnName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param expression
     * @param @param allColumnNames
     * @param @return 设定文件
     * @return StringBuffer 返回类型
     * @throws
     */
    private StringBuffer getWhereColumnName(Expression expression,StringBuffer allColumnNames) {

        String columnName = null;
        if(expression instanceof BinaryExpression){
            //获得左边表达式
            Expression leftExpression = ((BinaryExpression) expression).getLeftExpression();
            //如果左边表达式为Column对象，则直接获得列名
            if(leftExpression  instanceof Column){
                //获得列名
                columnName = ((Column) leftExpression).getColumnName();
                allColumnNames.append(columnName);
                allColumnNames.append(":");
                //拼接操作符
                allColumnNames.append(((BinaryExpression) expression).getStringExpression());
                //allColumnNames.append("-");
            }
            //否则，进行迭代
            else if(leftExpression instanceof BinaryExpression){
                getWhereColumnName(leftExpression,allColumnNames);
            }

            //获得右边表达式，并分解
            Expression rightExpression = ((BinaryExpression) expression).getRightExpression();
            if(rightExpression instanceof BinaryExpression){
                Expression leftExpression2 = ((BinaryExpression) rightExpression).getLeftExpression();
                if(leftExpression2 instanceof Column){
                    //获得列名
                    columnName = ((Column) leftExpression2).getColumnName();
                    allColumnNames.append("-");
                    allColumnNames.append(columnName);
                    allColumnNames.append(":");
                    //获得操作符
                    allColumnNames.append(((BinaryExpression) rightExpression).getStringExpression());
                }
            }
        }
        return allColumnNames;
    }

    @Test
    public void getWhereColumnName() throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse("select a,b,c from test where test.id = ? and test.username = ? and  user.age = ? and user.create_at between ? and ? and label like ?");
        Select select = (Select) statement;
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where = plain.getWhere();
        BinaryExpression binaryExpression = (BinaryExpression) where;
        BinaryExpression binaryExpression1 = (BinaryExpression) binaryExpression.getLeftExpression();
        System.out.println(binaryExpression1.getLeftExpression());
        BinaryExpression rightExpression = (BinaryExpression) binaryExpression1.getRightExpression();
        System.out.println(((Column)rightExpression.getLeftExpression()).getFullyQualifiedName());
        System.out.println(((BinaryExpression)((BinaryExpression)binaryExpression.getLeftExpression()).getRightExpression()).getRightExpression());
    }


    @Test
    public void testRightException() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("select a,b,c from test where id = ? and age = ?");
        Select select = (Select) stmt;
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where = plain.getWhere();
        BinaryExpression binaryExpression = (BinaryExpression) where;

        Expression rightExpression = binaryExpression.getRightExpression();

        System.out.println(binaryExpression.toString());
    }

    @Test
    public void testLeftExpression() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("select a,b,c from test where id = ? and age = ?");
        Select select = (Select) stmt;
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where = plain.getWhere();
        ItemsListVisitor itemsListVisitor = new ExpressionVisitorAdapter();
        ExpressionList expressionList;
        ExpressionListItem expressionListItem = new ExpressionListItem();
        System.out.println();
    }

    @Test
    public void makeAlias() throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse("select a,b,c from test");
        final AddAliasesVisitor instance = new AddAliasesVisitor();
        instance.setPrefix("x");
        select.getSelectBody().accept(instance);
        System.out.println(select.getSelectBody());
    }

    @Test
    public void testGetTables() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("SELECT * FROM users join orders WHERE username = ?");
        Select select = (Select) stmt;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(select);
        System.out.println(tableList);
    }

    @Test
    public void testAddColumn() throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse("select a from mytable");
        SelectUtils.addExpression(select, new Column("b"));
        System.out.println(select.getSelectBody());
    }
}
