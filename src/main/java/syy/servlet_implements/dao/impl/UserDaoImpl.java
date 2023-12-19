package syy.servlet_implements.dao.impl;

import syy.servlet_implements.dao.UserDao;
import syy.servlet_implements.entity.User;
import syy.servlet_implements.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User login(User user) throws SQLException {
        Connection connection = DBUtils.getConnection();
        //User result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,username,password FROM user WHERE username = ? AND password = ?");
            preparedStatement.setObject(1, user.getUsername());
            preparedStatement.setObject(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(connection,null,null);
        }
        return null;
    }
}
