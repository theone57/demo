package com.lin.demo.juc.multithreading.select;

import cn.hutool.db.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author wfh
 * @Date 2021/12/1 16:16
 * @Version 1.0
 **/
@Slf4j
@Service
public class BigDataSelectServiceImpl {
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    public String handle1(String list) throws InterruptedException {
///        Integer  dataCount =    nbzhizhubiaoMapper.selectCount(Wrappers.emptyWrapper());
        // 总数量
        Integer dataCount = 1000000;
        CountDownLatch countDownLatch = new CountDownLatch(dataCount / 10000 + 1);
        System.out.println(dataCount);
        for (int i = 1; i <= (dataCount / 10000 + 1); i++) {
            final int pagesize = 10000;
            final int pageindex = i;
            threadPoolExecutor.execute(() -> {
//                List<Nbzhizhubiao> nList;
                try {
                    System.out.println(Thread.currentThread().getName() + "======执行开始执行");
                    Page page = new Page(pageindex, pagesize);
                    System.out.println("====="+"分页执行开始"+pageindex+"====="+pagesize);

                    /*执行业务 begin */
//                    nList =  nbzhizhubiaoMapper.selectByNumber(page);
//                    for (Nbzhizhubiao nbzhizhubiao : nList) {
//                        String centerXy = nbzhizhubiao.getCenterXy();
//                        String[] split = centerXy.split(";");
//                        double lng = Double.parseDouble(split[0]);
//                        double lat = Double.parseDouble(split[1]);
//                        if (GpsFilter.intrance(lng,lat,list)){
//                            System.out.println("===符合工具类条件 "+ lng +" ==  "+lat);
//                            NewNbzhizhubiao newNbtqb = BeanUtil.copyProperties(nbzhizhubiao, NewNbzhizhubiao.class);
//                            System.out.println(newNbzhizhubiaoMapper.insert(newNbtqb));
//                        }
//                }

                    countDownLatch.countDown();
                } catch (Exception e) {
                    System.out.println("线程池出现错误" + e.getMessage());
                }
                System.out.println("线程" + Thread.currentThread().getName() + "====执行了一万条记录");
            });
        }
        countDownLatch.await();
        log.info("success");
        return "success";
    }

}
