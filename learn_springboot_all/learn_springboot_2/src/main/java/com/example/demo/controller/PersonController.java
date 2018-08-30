package com.example.demo.controller;

import com.example.demo.domain.PersonPo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {
    // 创建线程安全的Map
    static Map<Long, PersonPo> persons = Collections.synchronizedMap(new HashMap<Long, PersonPo>());

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<PersonPo> getPersonPoList() {
        // 处理"/persons/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<PersonPo> r = new ArrayList<PersonPo>(persons.values());
        return r;
    }

    @ApiOperation(value = "创建用户", notes = "根据PersonPo对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postPersonPo(@ModelAttribute PersonPo user) {
        // 处理"/persons/"的POST请求，用来创建PersonPo
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        persons.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PersonPo getPersonPo(@PathVariable Long id) {
        // 处理"/persons/{id}"的GET请求，用来获取url中id值的PersonPo信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return persons.get(id);
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putPersonPo(@PathVariable Long id, @ModelAttribute PersonPo user) {
        // 处理"/persons/{id}"的PUT请求，用来更新PersonPo信息
        PersonPo u = persons.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        persons.put(id, u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deletePersonPo(@PathVariable Long id) {
        // 处理"/persons/{id}"的DELETE请求，用来删除PersonPo
        persons.remove(id);
        return "success";
    }
}
