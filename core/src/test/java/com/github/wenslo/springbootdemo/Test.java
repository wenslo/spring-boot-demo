package com.github.wenslo.springbootdemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月11日 上午10:57
 * @description
 */
public class Test {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
//        Permission permission = new Permission("USER_ADD", "用户添加", true, "用户相关权限");
//        Permission permission1 = new Permission("USER_VIEW", "用户查看", true, "用户相关权限");
//        Permission permission2 = new Permission("USER_DELETE", "用户删除", true, "用户相关权限");
//        Permission permission3 = new Permission("USER_UPDATE", "用户修改", true, "用户相关权限");
//        List<Permission> list = Lists.newArrayList(permission, permission1, permission2, permission3);
//        System.out.println(gson.toJson(list));

//        List<Arr> list = Lists.newArrayList(new Arr("abc", 1, "dadssa"),
//                new Arr("abcd", 1, "dadssa"),
//                new Arr("abc", 2, "dadssa"),
//                new Arr("abcsdasa", 1, "dadssa")
//        );
//
//        Map<String, List<Arr>> collect = list.stream().collect(Collectors.groupingBy(Arr::getA));
//        collect.forEach((key, value) -> {
//            Arr arr = value.stream().max(Comparator.comparing(Arr::getB)).get();
//            System.out.println(" a is " + key + ", max b is " + arr.getB() + ", c is " + arr.getC());
//        });
        String msg = "'2123123123'是一个数字";
        String betweenString = StringUtils.substringBetween(msg, "'", "'");
        final String pattern = "{}";
        final String notExist = "{}已经存在，请检查后重新录入！";
        String result = StringUtils.replace(notExist, pattern, betweenString);
        System.out.println(result);
        System.out.println("12213123123123");
    }


}

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//class Arr {
//    public String a;
//    public Integer b;
//    public String c;
//}