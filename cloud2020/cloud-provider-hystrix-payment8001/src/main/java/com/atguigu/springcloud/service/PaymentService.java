package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_ok,id: " + id
                + "\t" + "笑脸";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    }
    )
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 13000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_TimeOut,id: " + id
                + "\t" + "笑脸" + "耗时" + timeNumber + "秒钟";

    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池: " + Thread.currentThread().getName() + " 8001系统繁忙或者运行出错,请稍后再试. id: " + id
                + "\t" ;
    }

}
