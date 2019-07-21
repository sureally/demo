package com.demo.dao;

import com.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @InterfaceName com.demo.dao.UserDao
 * @Desciption dao 层数据库交互
 * @Author Shu WJ
 * @DateTime 2019-07-20 00:10
 * @Version 1.0
 **/
public interface UserDao {

  /**
   * 插入一条语句, 插入单一记录，返回自增主键
   * @param user
   * @return
   */
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @Insert({" insert into user(name, password) values(#{name}, #{password}) "})
  int insert(User user);

  /**
   * 根据Id查询，据某书说最好不要用*通配即可。
   * @param id
   * @return
   */
  @Select({" select * from user where id=#{id}"})
  User selectById(Long id);


  /** 部分更新，这种其实写xml比较舒服 */
  @Update({
    "<script>",
    " update user",
    " <trim prefix='SET' suffixOverrides=','> ",
    "   <if test='name != null'>name=#{name},</if>",
    "   <if test='password != null'>`password`=#{password},</if>",
    " </trim>",
    " where id=#{id} ",
    "</script>"
  })
  int updateSelection(User user);


  @Insert({" insert into user(id, name, password) values(#{id}, #{name}, #{password}" +
    " on duplicate key update password=#{password}"})
  int insertOrUpdate(User user);
}
