package ro.mpp2024.Repository.DBRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.mpp2024.Repository.InscriereRepository;
import ro.mpp2024.Utils.JdbcUtils;
import ro.mpp2024.domain.Inscriere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class InscriereDBRepository implements InscriereRepository {

    private final JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public InscriereDBRepository(Properties props) {
        logger.info("Initializing ro.mpp2024.CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Inscriere findById(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Inscriere> findAll() {
        return null;
    }

    @Override
    public void save(Inscriere entity) {
        logger.traceEntry("saving task {}",entity);
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("insert into Inscrieri (idParticipant,idProba) values (?,?)")){
            preStmt.setInt(1,entity.getIdParticipant().getId());
            preStmt.setInt(2,entity.getIdProba().getId());
            int result = preStmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Inscriere entity) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
