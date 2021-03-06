package com.lion.nanshancloudservicedef.commom.interfaces;

/**
 * @author ：Lion
 * @date ：2020/3/26 11:26 上午
 * @description ：
 */
@FunctionalInterface
public interface LambdaInterface {
    // 只有一个接口函数需要被实现的接口类型，我们叫它”函数式接口“.
    // 为了避免后来的人在这个接口中增加接口函数导致其有多个接口函数需要被实现，变成"非函数接口”，加上一个声明@FunctionalInterface
    void justPrint(String str);

}
