package com.all.lin.sign.lp._4factory_interface;

import com.all.lin.sign.stu._4factory_interface.lin.ConvertFactoryComponent;
import com.all.lin.sign.stu._4factory_interface.lin.PlatformEnum;
import com.all.DemoApplication;
import com.all.lin.sign.stu._4factory_interface.lin.Handler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author linpu
 * @dateTime 2021/12/28 16:10
 * @email im.linpu@qq.com
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MyFactoryTest {

    @Test
    public void myFactory() {
        Handler handler = ConvertFactoryComponent.getConverter(PlatformEnum.FIRST_PLATFORM);
        handler.print();
        String out = handler.out();
        System.out.println("out = " + out);
    }
}
