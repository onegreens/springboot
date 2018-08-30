package com.example.demo.timer;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

@Configuration
@EnableScheduling
public class TimerService {

    public static boolean dothis = true;

    @Scheduled(cron = "0/1 * * * * ? ")
    public void work() {
        if (dothis) {
            LocalTime lt = LocalTime.now();
            System.out.println("当前时间：" + lt);
            HandlerData.createData();

        }

    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void handler() {
        if (dothis) {
            LocalTime lt = LocalTime.now();
            System.out.println("当前结算时间：" + lt);
            HandlerData.handlerData();
        }
    }

    @Scheduled(cron = "0 4 * * * ? ")
    public void over() {
        dothis = false;
        LocalTime lt = LocalTime.now();
        System.out.println("当前结束时间：" + lt);
        print();
    }

    public void print() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        for (MinuteData md : CatchData.getInstance().getMomentDataList()) {
            System.out.println("=========================================");
            System.out.println("时间：" + sdf.format(md.getTime()));
            for (String key : md.getData().keySet()) {
                System.out.println(" KEY: " + key + "  || VALUE: " + md.getData().get(key));
            }
            System.out.println("=========================================");
        }
    }
}
