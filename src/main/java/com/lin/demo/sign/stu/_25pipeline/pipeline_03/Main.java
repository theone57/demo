package com.lin.demo.sign.stu._25pipeline.pipeline_03;

public class Main {
    public static void main(String[] args) {
//        DefaultPipeline pipeline = new DefaultPipeline();
//        pipeline.addStep(new StepOne());
//        pipeline.addStep(new StepTwo());
//        pipeline.addStep(new StepThree());
//        pipeline.execute();

//        Pipeline<Integer, Integer> intPipe = new Pipeline<>();
//        intPipe = intPipe.add(new StepOne()); // increment 100
//        intPipe = intPipe.add(new StepTwo()); // increment 500
//        Pipeline<Integer, String> add = intPipe.add(new StepThree());// convert
//
//        Pipeline<Integer, String> pipe = new Pipeline<Integer, Integer>()
//                .add(new StepOne()).add(new StepTwo()).add(new StepThree());
        Pipeline<Integer, Integer> pipe = new Pipeline<Integer, Integer>()
                .add(new StepOne()).add(new StepTwo());
        pipe.execute();
        Object o = pipe.getFirstStepInput();
        System.out.println("o = " + o);
        if (o instanceof String) {
            System.out.println(o.toString());
        }
    }
}