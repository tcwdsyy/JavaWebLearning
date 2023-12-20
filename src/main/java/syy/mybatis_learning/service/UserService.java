package syy.mybatis_learning.service;


import com.github.pagehelper.PageInfo;
import syy.mybatis_learning.entity.User;

public interface UserService{
    PageInfo<User> selectUserByPage(int pageNum, int pageSize);
}
