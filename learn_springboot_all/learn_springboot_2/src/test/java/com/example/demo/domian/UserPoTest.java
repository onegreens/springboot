package com.example.demo.domian;

import com.example.demo.domain.UserPo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserPoTest {

    @Autowired
    private UserPo user;

    @Test
    public void test(){
        Assert.assertEquals(user.getName(), "chen");
        Assert.assertEquals(user.getAge(),new Integer(24) );
        System.out.println(user.getInfo());
    }
}
