package com.all.lin.sign.stu._15command.lin.thrid;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * @author linpu
 * @dateTime 2021-10-19 14:23
 * @email im.linpu@qq.com
 * @description
 */
@Slf4j
public class MsgComponent {
    static HashMap<Object, Consumer<MsgParam>> MAP = Maps.newHashMap();

    static {
        MAP.put(MsgTypeEnum.Home, MsgComponent::sendHomeMsg);
        MAP.put(MsgTypeEnum.LIGHT, MsgComponent::sendLightMsg);
        MAP.put(MsgTypeEnum.TV, MsgComponent::sendTvMsg);
    }
    public static void sendMsg(MsgTypeEnum msgTypeEnum,MsgParam msgParam) {
        MAP.get(msgTypeEnum).accept(msgParam);
    }
    private static void sendHomeMsg(MsgParam msgParam) {
        log.info("type:打开房间所有设备, name:{}", msgParam.getName());
    }

    private static void sendLightMsg(MsgParam msgParam) {
        log.info("type:打开所有灯, name:{}", msgParam.getName());
    }

    private static void sendTvMsg(MsgParam msgParam) {
        log.info("type:打开电视机, name:{}, age:{}", msgParam.getName(), msgParam.getAge());
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class MsgParam {
        private String name;
        private String age;
    }
}

