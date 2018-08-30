package com.example.learn_springboot_more.timer;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@Component
@EnableScheduling
public class SchedledTask {
    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public void scheduleTest() {
        System.out.println("执行定时任务。。。。" + sdf.format(new Date()));
    }
}
