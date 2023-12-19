package syy.mybatis_learning;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserMapperTest {
    @Test
    public void selectUserByIdTest() throws IOException {
        // 获得读取MyBatis核心配置文件的流对象
        InputStream input = Resources.getResourceAsStream("mybatis-config.xml");
        // 根据流对象构建SqlSession连接对象的工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(input);
        // 通过工厂获得连接对象sqlSession
        SqlSession sqlSession = factory.openSession();

        // 1 通过连接对象获得接口实现类对象
        UserDao userDaoImpl = sqlSession.getMapper(UserDao.class);
        // 打印结果
        System.out.println(userDaoImpl.selectUserById(1));

        // 2 通过连接对象直接调用接口中的方法
        Object o = sqlSession.selectOne("syy.mybatis_learning.UserDao.selectUserById", 1);
        // 打印结果
        System.out.println(o);
    }
}
