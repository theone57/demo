package com.lin.demo.sign.stu._25pipeline.pipeline_04.pipeline;


import com.lin.demo.sign.stu._25pipeline.pipeline_04.model.Context;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.value.Value;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.node.ValidatorNode;


public class ValidatePipeLine implements Pipeline {

    public Context context;
    public ValidatorNode head;
    public ValidatorNode tail;

    public ValidatePipeLine(Context context) {
        this.context = context;
        head = new ValidatorNode();
        tail = head;
    }

    @Override
    public void addFirst(Value... validators) {
        ValidatorNode validatorNode;
        for (Value validator : validators) {
            ValidatorNode preNode = head.getNextNode();
            validatorNode = new ValidatorNode(validator);
            validatorNode.setNextNode(preNode);
            head.setNextNode(validatorNode);
        }
    }

    @Override
    public void addLast(Value... validators) {
        ValidatorNode tailNode = tail;
        ValidatorNode handlerNode;
        for (Value validator : validators) {

            handlerNode = new ValidatorNode(validator);
            tailNode.setNextNode(handlerNode);
            tailNode = handlerNode;
        }
        tail = tailNode;
    }

    @Override
    public void start() {
        head.getNextNode().execute(context);
    }

    @Override
    public void shutDown() {
    }


    @Override
    public <T extends Context> T getContext() {
        return (T) context;
    }
}
