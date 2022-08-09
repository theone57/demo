package com.all.lin.statrter.controller;

import com.all.lin.exception.BusinessException;
import com.all.lin.statrter.async.completable.CompletableFutureImpl;
import com.all.lin.statrter.async.AsyncTestServiceImpl;
import com.all.lin.statrter.config.DateConverter;
import com.all.lin.statrter.listener.pojo.User;
import com.all.lin.statrter.validator.ValidatorUtils;
import com.all.lin.threadlocal.ThreadLocalProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author linpu
 * @dateTime 2022-05-06 16:02
 * @email im.linpu@qq.com
 * @description
 */
@RestController
//@Scope(value = "prototype")
@RequestMapping("common")
public class CommonController {

    private int num = 0;

    /**
     * 新增用户操作
     *
     * @param user
     */
    @PostMapping("/validate")
    public void test(@Validated User user) {
        try {
            ValidatorUtils.validateEntity(user);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
//        userService.addUser(user);
        System.out.println(" come in");
    }

    /**
     * Scope属性是用来声明IOC容器中的对象（Bean）允许存在的限定场景，或者说是对象的存活空间。
     * 在对象进入相应的使用场景之前，IOC容器会生成并装配这些对象；当该对象不再处于这些使用场景的限定时，容器通常会销毁这些对象。
     * Controller也是一个Bean，默认的 Scope 属性为Singleton ，也就是单例模式。
     * 如果Bean的 Scope 属性设置为 prototype 的话，容器在接受到该类型对象的请求时，每次都会重新生成一个新的对象给请求方。
     */
    @RequestMapping("/addNum")
    public void addNum() {
        System.out.println(++num);
    }

    public static void main(String[] args) throws IOException {
        String separator = File.separator;
        System.out.println("separator = " + separator);
//        File file = new File("/Users/alex/Desktop/audiData(2000-05-11).sql");
//        boolean newFile = file.createNewFile();


    }

    /**
     * threadlocal 异步线程传值 测试
     */
    @Autowired
    private ThreadLocalProblem threadLocalProblem;

    @RequestMapping("/threadlocal")
    public void threadLocalProblem() {
        threadLocalProblem.useTransmittableByPool();
    }

    @Autowired
    private AsyncTestServiceImpl asyncTestService;

    @RequestMapping("/async")
    public void asyncTest() {
        asyncTestService.executor1();
    }

    @Autowired
    private CompletableFutureImpl completableFuture;

    @RequestMapping("/completableFuture")
    public void completableFutureImplTest() {
        completableFuture.completableFuture();
    }

    /**
     * 测试转换器 {@link DateConverter}
     *
     * @param date
     * @return
     */
    @RequestMapping("/date")
    public LocalDateTime converter(LocalDateTime date) {
        System.out.println("date = " + date);
        return date;
    }


}
