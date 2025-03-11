package ro.mpp2024;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository{

    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public CarsDBRepository(Properties props) {
        logger.info("Initializing ro.mpp2024.CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        logger.traceEntry();
        List<Car> cars = new ArrayList<>();
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("select * from cars where manufacturer=?")) {
            preStmt.setString(1,manufacturerN);
            resultSetToCars(cars, preStmt);
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit(cars);
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
        logger.traceEntry();
        List<Car> cars = new ArrayList<>();
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("select * from cars where year<=? and year>=?")) {
            preStmt.setInt(1,max);
            preStmt.setInt(1,min);
            resultSetToCars(cars, preStmt);
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit(cars);
        return cars;
    }

    private void resultSetToCars(List<Car> cars, PreparedStatement preStmt) throws SQLException {
        try (ResultSet rs=preStmt.executeQuery()){
            while (rs.next()){
                int id=rs.getInt("id");
                String manufacturer=rs.getString("manufacturer");
                String model=rs.getString("model");
                int year=rs.getInt("year");
                Car car=new Car(manufacturer,model,year);
                car.setId(id);
                cars.add(car);
            }
        }
    }

    @Override
    public void add(Car elem) {
        logger.traceEntry("saving task {}",elem);
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("insert into Cars (manufacturer,model,year) values (?,?,?)")){
            preStmt.setString(1,elem.getManufacturer());
            preStmt.setString(2,elem.getModel());
            preStmt.setInt(3,elem.getYear());
            int result = preStmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Car elem) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("update Cars set manufacturer=?,model=?,year=? where id=?")){
            preStmt.setString(1,elem.getManufacturer());
            preStmt.setString(2,elem.getModel());
            preStmt.setInt(3,elem.getYear());
            preStmt.setInt(4,integer);
            int result = preStmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit();
    }

    @Override
    public Iterable<Car> findAll() {
         logger.traceEntry();
         Connection con= dbUtils.getConnection();
         List<Car> cars=new ArrayList<>();
         try (PreparedStatement preStmt=con.prepareStatement("select * from Cars")){
             resultSetToCars(cars, preStmt);
         }
         catch (SQLException e) {
             logger.error(e);
             System.err.println("Error DB "+e);
         }
         logger.traceExit(cars);
         return cars;
    }
}
