package syy.mybatis_learning.dao;

import org.apache.ibatis.annotations.Param;
import syy.mybatis_learning.entity.User;

import java.util.List;

public interface UserDao {
    User selectUserById(@Param("id") int id);
    int insertUser(User user);
    List<User> selectUserByPage();
}
