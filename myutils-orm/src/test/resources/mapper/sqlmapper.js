/**
 * Description :
 * Created by code4j on 17-1-10
 *  上午1:15
 */
function findUserById(map){
    var sql = "select * from user where 1 = 1";
    if (map.id == null){
        sql += "and id = $id$"
    }
    if (map.username == null){
        sql += " and username = $username$"
    }
    return sql;
}



















