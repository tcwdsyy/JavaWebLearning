package syy.mybatis_learning.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import syy.mybatis_learning.entity.User;
import syy.mybatis_learning.utils.MyBatisUtil;
import syy.mybatis_learning.dao.UserDao;

import java.util.List;


public class UserServiceImpl implements UserService {
    @Override
    public PageInfo<User> selectUserByPage(int pageNum, int pageSize) {
        UserDao userDao = MyBatisUtil.getMapper(UserDao.class);

        PageHelper.startPage(pageNum,pageSize);

        List<User> userList = userDao.selectUserByPage();
        MyBatisUtil.closeSession();

        return new PageInfo<User>(userList);
    }
}
