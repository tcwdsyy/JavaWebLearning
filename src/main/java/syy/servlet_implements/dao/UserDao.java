package syy.servlet_implements.dao;

import syy.servlet_implements.enity.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    User login(User user) throws SQLException;
}
