package dao;

import dbService.DBService;
import model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static helpers.Constants.ERROR_MSG_STATEMENT;

/**
 * Created by bbb1991 on 11/28/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class CourseDAO implements DAO<Course> {

    private DBService dbService;
    private static final Logger logger = LoggerFactory.getLogger(CourseDAO.class);

    public CourseDAO(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public Course findById(Integer id) {
        Connection connection = dbService.retrieveConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from course where id=?")) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String name = resultSet.getString("name");

            return new Course(id, name);

        } catch (SQLException e) {
            logger.error(ERROR_MSG_STATEMENT, e);
            throw new RuntimeException(e);
        } finally {
            dbService.putBack(connection);
        }
    }

    @Override
    public List<Course> findAll() {
        Connection connection = dbService.retrieveConnection();

        List<Course> courses = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            statement.executeQuery("SELECT * from course");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");

                courses.add(new Course(id, name));
            }
        } catch (SQLException e) {
            logger.error(ERROR_MSG_STATEMENT, e);
            throw new RuntimeException(e);
        } finally {
            dbService.putBack(connection);
        }

        return courses;
    }

    @Override
    public void save(Course model) {
        Connection connection = dbService.retrieveConnection();

        try (PreparedStatement statement = connection.prepareStatement("insert into course (name) VALUES (?)")) {

            statement.setString(1, model.getName());

            statement.execute();

        } catch (SQLException e) {
            logger.error(ERROR_MSG_STATEMENT, e);
            throw new RuntimeException(e);
        } finally {
            dbService.putBack(connection);
        }
    }

    @Override
    public void update(Course model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createTable() {
        Connection connection = dbService.retrieveConnection();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("create TABLE if NOT EXISTS course (id int auto_increment PRIMARY KEY , name VARCHAR2(60))");
        } catch (SQLException e) {
            logger.error(ERROR_MSG_STATEMENT, e);
            throw new RuntimeException(e);
        } finally {
            dbService.putBack(connection);
        }
        logger.info("Table STUDENT created");
    }

    @Override
    public void dropTable() {
        Connection connection = dbService.retrieveConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("drop TABLE if EXISTS course");
        } catch (SQLException e) {
            logger.error(ERROR_MSG_STATEMENT, e);
            throw new RuntimeException(e);
        } finally {
            dbService.putBack(connection);
        }
        logger.info("Table STUDENT dropped");
    }
}
