package com.lin.demo.sign.lp.pipeline_02;

/**
 * @author linpu
 * @dateTime 2021-10-13 22:32
 * @email im.linpu@qq.com
 * @description
 */
public class Main {
    public static void main(String[] args) {
        String handling="aabb1122zzyy";
        StandardPipeline pipeline = new StandardPipeline();
        BasicValve basicValve = new BasicValve();
        SecondValve secondValve = new SecondValve();
        ThirdValve thirdValve = new ThirdValve();
        FourValve fourValve = new FourValve();
        pipeline.setBasic(basicValve);
        pipeline.addValve(secondValve);
        pipeline.addValve(thirdValve);
        pipeline.addValve(fourValve);

        pipeline.getFirst().invoke(handling);
    }
}


