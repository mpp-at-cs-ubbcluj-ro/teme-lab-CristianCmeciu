package ro.mpp2024.Repository.DBRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.mpp2024.Repository.ParticipantRepository;
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

public class ParticipantDBRepository implements ParticipantRepository {

    private final JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public ParticipantDBRepository(Properties props) {
        logger.info("Initializing ro.mpp2024.ParticipantDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Participant findById(Integer anInt) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("select * from Participanti where id=?")){
            preStmt.setInt(1,anInt);
            try (ResultSet rs=preStmt.executeQuery()){
                Integer id = rs.getInt("id");
                String nume = rs.getString("nume");
                int varsta = rs.getInt("varsta");
                Participant participant = new Participant(nume, varsta);
                participant.setId(id);
                return participant;
            }
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        return null;
    }

    private void resultSetToParticipant(List<Participant> participanti, PreparedStatement preStmt) throws SQLException {
        try (ResultSet rs=preStmt.executeQuery()){
            while (rs.next()){
                int id=rs.getInt("id");
                String nume=rs.getString("nume");
                int varsta=rs.getInt("varsta");
                Participant participant = new Participant(nume,varsta);
                participant.setId(id);
                participanti.add(participant);
            }
        }
    }

    @Override
    public Iterable<Participant> findAll() {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Participant> participanti=new ArrayList<>();
        try (PreparedStatement preStmt=con.prepareStatement("select * from Participanti")){
            resultSetToParticipant(participanti, preStmt);
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit(participanti);
        return participanti;
    }

    @Override
    public void save(Participant entity) {
        logger.traceEntry("saving task {}",entity);
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("insert into Participanti (nume,varsta) values (?,?)")){
            preStmt.setString(1,entity.getNume());
            preStmt.setInt(2,entity.getVarsta());
            int result = preStmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Participant entity) {
    }

    @Override
    public void delete(Integer anInt) {
    }

    @Override
    public Iterable<Participant> findParticipantiByProba(Proba proba) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Participant> participanti=new ArrayList<>();
        try (PreparedStatement preStmt=con.prepareStatement("select * from Participanti as P join Inscrieri as I on I.idParticipant=P.id where I.idProba=?")){
            preStmt.setInt(1,proba.getId());
            resultSetToParticipant(participanti, preStmt);
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit(participanti);
        return participanti;
    }
}
