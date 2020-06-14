package com.lion.nanshancloudservice.impl;

import com.lion.nanshancloudservicedef.commom.interfaces.LambdaInterface;
import com.lion.nanshancloudservicedef.entity.UserInfo;
import com.lion.nanshancloudservicedef.service.LambdaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：Lion
 * @date ：2020/3/26 10:14 上午
 * @description ：lambad使用
 */
@Slf4j
@Service
public class LambdaServiceImpl implements LambdaService {

    private String str = "lion - lambda";

    /**
     * lambda用法
     */
    @Override
    public void methodUse() {
        List<String> list = getStringList();
        // 所有的Lambda的类型都是一个接口，而Lambda表达式(那段代码)，是这个接口的实现。

        //LambdaInterface lamTarget = (s) -> log.info(s);
        // 使用Method reference 代替lambda
        LambdaInterface lamTarget = log::info;

        // Predicate<T>和Consumer<T>,Function<T, R> 是Java8 定义好的函数接口
        Consumer<String> consumer = log::info;

        //checkAndExcute(list, p -> p.startsWith("c"), log::info);
        // 利用stream()替代函数：
        list.stream().filter(p -> p.startsWith("c")).forEach(log::info);
    }

    /**
     * 用方法函数作为参数, 一个方法可以实现不同的自定义功能
     */
    private void checkAndExcute(List<String> list, Predicate<String> predicate, Consumer<String> consumer){
        /*for (String s : list) {
            if (predicate.test(s)) {
                consumer.accept(s);
            }
        }*/
        // Iterable.forEach()取代foreach loop：
        list.forEach(p -> {if (predicate.test(p)) {
            consumer.accept(p);
        }
        });
    }

    // =============================== stream() ===============================

    /**
     * stream() 的用法
     */
    @Override
    public void streamUse() {
        // filter() 过滤
        // count() 统计
        List<Integer> list = getIntList();
        Long count = list.stream().filter(p->p>20).count();

        // collect() 从stream中, 生成列表
        List<String> lowerList = Stream.of("a","b","c")
                .collect(Collectors.toList());

        // map() map方法是一个惰性求值方法，用于把 Stream 中的值替换为新的值。
        List<String> upperList = Stream.of("a","b","c")
                //.map(value -> return value.toUpperCase())
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        //flatMap 可以用于把多个 Stream 连接成一个 Stream。
        List<String> charList = Stream.of(lowerList, upperList)
                //.flatMap(stream -> stream.stream())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // min,max 这两个方法接受一个 Comparator 对象作为参数，返回一个 Optional 对象。
        // Comparator 接口新增了一个静态的方法,从而可以使用 lambda 表达式来定义最大值和最小值的比较规则。
        Integer min = list.stream().min(Comparator.comparing(value-> -value)).get();
    }

    // =============================== Optional<T> ===============================

    @Override
    public void optionalUse() {
        // 两种方式创建
        // 1. 调用ofNullable()方法，传入的对象可以为null
        UserInfo userNull = null;
        Optional<UserInfo> optNull = Optional.ofNullable(userNull);
        // 2. 调用of()方法，传入的对象不可以为null，否则抛出NullPointerException
        UserInfo userNotNull = new UserInfo();
        userNotNull.setName("Lion");
        Optional<UserInfo> optNotNull = Optional.of(userNotNull);

        // 普通方法: isPresent(), get(), orElse(userNotNull)
        // 带函数式接口方法
        // 如果存在, 执行函数式接口的实现方法
        optNotNull.ifPresent(userInfo -> log.info(userInfo.getName()));
        optNotNull.orElseGet(UserInfo::new);
        // 对象存在,且条件成立,返回, 其余返回空的optional
        optNotNull.filter(userInfo -> "Lion".equals(userInfo.getName()));
        // 替换, 加上orELse对应成不知道
        optNotNull.map(userInfo -> userInfo.getName()).orElse("unknown");
    }

    // =============================== private method ===============================

    private List<String> getStringList(){
        List<String> list = new ArrayList<>();
        list.add("nan");
        list.add("shan");
        list.add("cloud");
        return list;
    }

    private List<Integer> getIntList(){
        List<Integer> list = new ArrayList<>();
        list.add(26);
        list.add(19);
        list.add(56);
        return list;
    }
}
