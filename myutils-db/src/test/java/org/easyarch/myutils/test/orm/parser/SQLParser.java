package org.easyarch.myutils.test.orm.parser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.AddAliasesVisitor;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-11
 * 上午12:41
 * description:
 */

public class SQLParser {

    @Test
    public void getColums() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("select a,b,c from test where id = ? and age = ?");
        Select select = (Select) stmt;
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where = plain.getWhere();
        where.accept(new ExpressionDeParser());
        System.out.println(where.getClass().getName());
    }

    public void testRightException() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("select a,b,c from test where id = ? and age = ?");
        Select select = (Select) stmt;
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where = plain.getWhere();
        BinaryExpression binaryExpression = (BinaryExpression) where;
        Expression rightExpression = binaryExpression.getRightExpression();

        System.out.println(rightExpression);
    }

    public void testLeftExpression() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("select a,b,c from test where id = ? and age = ?");
        Select select = (Select) stmt;
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where = plain.getWhere();
        Expression leftExpression = ((BinaryExpression) where).getLeftExpression();
        Expression condition = ((BinaryExpression) leftExpression).getLeftExpression();
        condition.accept(new ExpressionVisitorAdapter());
        System.out.println();
    }

    public void makeAlias() throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse("select a,b,c from test");
        final AddAliasesVisitor instance = new AddAliasesVisitor();
        instance.setPrefix("x");
        select.getSelectBody().accept(instance);
        System.out.println(select.getSelectBody());
    }

    public void testGetTables() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("SELECT * FROM users join orders WHERE username = ?");
        Select select = (Select) stmt;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(select);
        System.out.println(tableList);
    }

    public void testAddColumn() throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse("select a from mytable");
        SelectUtils.addExpression(select, new Column("b"));
        System.out.println(select.getSelectBody());
    }
}
