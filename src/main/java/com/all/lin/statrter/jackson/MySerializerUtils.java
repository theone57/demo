package com.all.lin.statrter.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author linpu
 * @dateTime 2022-08-01 15:31
 * @email im.linpu@qq.com
 * @description
 */

public class MySerializerUtils extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer status, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String statusStr = "";
        switch (status) {
            case 0:
                statusStr = "新建状态";
                break;
            case 1:
                statusStr = "就绪状态";
                break;
            case 2:
                statusStr = "运行状态";
                break;
            case 3:
                statusStr = "阻塞和唤醒线程";
                break;
            case 4:
                statusStr = " 死亡状态";
                break;
            default:
                statusStr = "状态信息不符合";
        }
        jsonGenerator.writeString(statusStr);
    }
}
