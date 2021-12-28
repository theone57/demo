package com.all.lin.spring.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 前端控制器Demo
 * <p>
 * https://juejin.cn/post/6844904184874172423
 *
 * @author alex
 */
public class FrontControllerDemo {

    public static void main(String[] args) {

        FrontController frontController = new FrontController();
        View view = frontController.forwardRequest("student");
        view.show();

    }

}

//前端控制器
class FrontController {

    private Dispatcher dispatcher;

    public FrontController() {
        this.dispatcher = new DispatcherImpl();
    }

    public View forwardRequest(String request) {
        if (dispatcher.isAuthenticUser(request)) {
            return dispatcher.dispatch(request);
        }
        return new ViewImpl();
    }

}

interface Dispatcher {

    View dispatch(String request);

    boolean isAuthenticUser(String request);

}

//调度器
class DispatcherImpl implements Dispatcher {
    /**
     * 执行器 chain
     */
    private List<Handler> handlers;

    public DispatcherImpl() {
        this.handlers = new ArrayList<>();
        handlers.add(new Handler1());
        handlers.add(new Handler2());
    }

    @Override
    public View dispatch(String request) {
        View view = null;
        for (Handler handler : handlers) {
            if (handler.supportHandler(request)) {
                view = handler.doRequest(request);
            }
        }
        return view;
    }

    @Override
    public boolean isAuthenticUser(String request) {
        System.out.println("校验");
        return true;
    }

}

interface Handler {

    boolean supportHandler(String request);

    View doRequest(String request);

}

abstract class AbstractHandler implements Handler {

    protected View view;

    abstract View getView();

    @Override
    public View doRequest(String request) {
        View view = getView();
        view.setModel(request);
        return view;
    }

}

//处理器
class Handler1 extends AbstractHandler {

    public Handler1() {
        super.view = new ViewImpl();
    }

    @Override
    public boolean supportHandler(String request) {
        return "STUDENT".equalsIgnoreCase(request);
    }

    @Override
    View getView() {
        return super.view;
    }

}

class Handler2 extends AbstractHandler {

    public Handler2() {
        super.view = new ViewImpl();
    }

    @Override
    public boolean supportHandler(String request) {
        return "TEACHER".equalsIgnoreCase(request);
    }

    @Override
    View getView() {
        return super.view;
    }

}

interface View {

    void setModel(String request);

    void show();

}

class ViewImpl implements View {

    private String model;

    @Override
    public void setModel(String request) {
        this.model = request;
    }

    @Override
    public void show() {
        System.out.println("show request: " + this.model);
    }
}
