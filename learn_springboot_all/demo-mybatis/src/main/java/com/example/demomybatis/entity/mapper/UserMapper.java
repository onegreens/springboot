package com.example.demomybatis.entity.mapper;

import com.example.demomybatis.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    //正删改查
    @Select("select * from user where name = #{name}")
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO USER(NAME,AGE ) VALUES (#{name},#{age})")
    int insert(@Param("name") String name, @Param("age") int age);

    @Update("UPDATE user SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

    /**
     * 使用map
     *
     * @param map
     * @return
     */
    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    /**
     * 使用对象
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insertByUser(User user);

    /**
     * 返回结果的绑定
     * @return
     */
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT name, age FROM user")
    List<User> findAll();
}
