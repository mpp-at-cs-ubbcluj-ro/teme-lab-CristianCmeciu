package ro.mpp2024.Repository.DBRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.mpp2024.Repository.ProbaRepository;
import ro.mpp2024.Utils.JdbcUtils;
import ro.mpp2024.domain.Participant;
import ro.mpp2024.domain.Proba;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProbaDBRepository implements ProbaRepository {

    private final JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public ProbaDBRepository(Properties props) {
        logger.info("Initializing ro.mpp2024.CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Proba findById(Integer integer) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("select * from Proba where id=?")){
            preStmt.setInt(1,integer);
            try (ResultSet rs=preStmt.executeQuery()){
                Integer id = rs.getInt("id");
                String stil = rs.getString("stil");
                int distanta = rs.getInt("distanta");
                Proba proba = new Proba(distanta,stil);
                proba.setId(id);
                return proba;
            }
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        return null;
    }

    @Override
    public Iterable<Proba> findAll() {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Proba> probe=new ArrayList<>();
        try (PreparedStatement preStmt=con.prepareStatement("select * from Probe")){
            try (ResultSet rs=preStmt.executeQuery()){
                while (rs.next()){
                    int id=rs.getInt("id");
                    String stil=rs.getString("stil");
                    int distanta=rs.getInt("distanta");
                    Proba proba = new Proba(distanta,stil);
                    proba.setId(id);
                    probe.add(proba);
                }
            }
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit(probe);
        return probe;
    }

    @Override
    public void save(Proba entity) {
        logger.traceEntry("saving task {}",entity);
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("insert into Probe (stil,distanta) values (?,?)")){
            preStmt.setString(1,entity.getStil());
            preStmt.setInt(2,entity.getDistanta());
            int result = preStmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Proba entity) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
