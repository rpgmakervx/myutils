package org.easyarch.myutils.test.controlle;


import org.easyarch.myutils.test.service.UserService;

/**
 * Description :
 * Created by xingtianyu on 17-2-9
 * 上午2:23
 * description:
 */

public class UserController {

    public static void main(String[] args) {
        UserService service = new UserService();
//        System.out.println(ResourcesUtil.getResourceAsStream("/config.xml"));
        System.out.println(service.getUser("ewrgthgdsvng"));;
    }
}
