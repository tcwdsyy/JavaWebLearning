package syy.servlet_implements.dao;

import syy.servlet_implements.entity.User;

import java.sql.SQLException;

public interface UserDao {
    User login(User user) throws SQLException;
}
