/**
 * Description :
 * Created by code4j on 17-2-10
 *  上午1:35
 */
var namespace = org.easyarch.myutils.test.dao.UserMapper

function findById(params){
    return "select * from user where client_id = $id$";
}

function findByUser(params) {
    var sql = "select * from user" + where;
    if (params.clientId != undefined){
        sql += "client_id = $clientId$ and";
    }
    if (params.phone != undefined){
        sql += "phone = phone";
    }
    return sql;
}

function insert(params){
    return "insert into user(client_id,username,password,phone) " +
        "values($clientId$,$userName$,$password$,$phone$)"
}