package ro.mpp2024.Repository.DBRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.mpp2024.Repository.UserRepository;
import ro.mpp2024.Utils.JdbcUtils;
import ro.mpp2024.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserDBRepository implements UserRepository {

    private final JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public UserDBRepository(Properties props) {
        logger.info("Initializing ro.mpp2024.CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public User findById(Integer integer) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("select * from Users where id=?")){
            preStmt.setInt(1,integer);
            try (ResultSet rs=preStmt.executeQuery()){
                Integer id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(username,password);
                user.setId(id);
                return user;
            }
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {
        logger.traceEntry("saving task {}",entity);
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("insert into Users (username,password) values (?,?)")){
            preStmt.setString(2,entity.getUsername());
            preStmt.setString(3,entity.getPassword());
            int result = preStmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit();
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
