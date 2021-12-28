package com.all.lin.utils;

/**
 * Created by AnthonyPark on 2016/12/26.
 * 日志切面专用bean
 */
public class ParamData {

    private String sensorId;
    private Object sensorValue;

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public Object getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(Object sensorValue) {
        this.sensorValue = sensorValue;
    }

    @Override
    public String toString() {
        return "SensorData [sensorId=" + sensorId + ", sensorValue=" + sensorValue + "]";
    }
}