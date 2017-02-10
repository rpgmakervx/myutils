/**
 * Description :
 * Created by code4j on 17-1-28
 *  上午1:35
 */
ctx.namespace = "org.easyarch.myutils.test.dao.UserMapper";

function findById(params){
    return "select * from user where client_id = $id$";
}

function findByUser(params) {
    var sql = "select * from user" + ctx.where;
    if (params.clientId != undefined){
        sql += "client_id = $clientId$ and";
    }
    if (params.phone != undefined){
        sql += "phone = phone";
    }
    return sql;
}