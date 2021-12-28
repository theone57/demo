package com.all.lin.sign.stu._25pipeline.pipeline_04.pipeline;


import com.all.lin.sign.stu._25pipeline.pipeline_04.model.Context;
import com.all.lin.sign.stu._25pipeline.pipeline_04.node.DefaultNode;
import com.all.lin.sign.stu._25pipeline.pipeline_04.value.Value;


public class DefaultPipeLine implements Pipeline {

    public Context context;
    public DefaultNode head;
    public DefaultNode tail;

    public DefaultPipeLine(Context context) {
        this.context = context;
        head = new DefaultNode();
        tail = head;
    }

    @Override
    public void addFirst(Value... values) {
        DefaultNode handlerNode;
        for (Value value : values) {
            DefaultNode preNode = head.getNextNode();
            handlerNode = new DefaultNode(value);
            handlerNode.setNextNode(preNode);
            head.setNextNode(handlerNode);
        }
    }

    @Override
    public void addLast(Value... values) {
        DefaultNode tailNode = tail;
        DefaultNode handlerNode;
        for (Value value : values) {
            handlerNode = new DefaultNode(value);
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
