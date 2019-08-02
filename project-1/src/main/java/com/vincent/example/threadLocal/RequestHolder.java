package com.vincent.example.threadLocal;

public class RequestHolder {
    //因为当前没有登录用户，我们用线程id来充当
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    /**
     * 请求进入到后端服务器，但是还没有实际处理的时候调用add，可以使用Filter
     * @param id
     */
    public static void add(Long id) {
        //虽然只传入id，但是threadLocal会取出当前线程id放到map中的key，value是传入的值
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    /**
     * 如果不做remove的话，会造成内存泄漏，数据永远不会释放掉
     * 需要在接口真正处理完成之后进行调用，可以使用interceptor
     */
    public static void remove() {
        requestHolder.remove();
    }
}
