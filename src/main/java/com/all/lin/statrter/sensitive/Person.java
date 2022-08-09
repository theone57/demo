package com.all.lin.statrter.sensitive;

import com.all.lin.statrter.sensitive.method1.Sensitive;
import com.all.lin.statrter.sensitive.method1.SensitiveStrategy;
import com.all.lin.statrter.sensitive.method2.DataMasking;
import com.all.lin.statrter.sensitive.method2.DataMaskingFunc;
import lombok.Data;

@Data
public class Person {
    /**
     * 真实姓名
     */
//    @Sensitive(strategy = SensitiveStrategy.USERNAME)
    @DataMasking(maskFunc = DataMaskingFunc.ALL_MASK)
    private String realName;
    /**
     * 地址
     */
    @Sensitive(strategy = SensitiveStrategy.ADDRESS)
    private String address;
    /**
     * 电话号码
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String phoneNumber;
    /**
     * 身份证号码
     */
    @Sensitive(strategy = SensitiveStrategy.ID_CARD)
    private String idCard;
}
