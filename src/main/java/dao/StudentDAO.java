package dao;

import dbService.DBService;
import model.Course;
import model.Student;
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
public class StudentDAO implements DAO<Student> {

    private static final Logger logger = LoggerFactory.getLogger(StudentDAO.class);
    private DBService dbService;

    public StudentDAO (DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public Student findById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Student> findAll() {
        Connection connection = dbService.retrieveConnection();

        List<Student> students = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            statement.executeQuery("select * from student join course ON student.course_id=course.id");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt("student.id");
                String surname = resultSet.getString("student.surname");
                String firstname = resultSet.getString("student.firstname");
                String secondname = resultSet.getString("student.secondname");
                int sexId = resultSet.getInt("student.sex_id");
                int groupId = resultSet.getInt("student.course_id");
                String groupName = resultSet.getString("course.name");

                Student student = new Student(id, surname, firstname, secondname, sexId, new Course(groupId, groupName));

                students.add(student);
            }
        } catch (SQLException e) {
            logger.error(ERROR_MSG_STATEMENT, e);
            throw new RuntimeException(e);
        } finally {
            dbService.putBack(connection);
        }

        logger.info("Returning students. Size is: {}" ,students.size());
        return students;
    }

    @Override
    public void save(Student model) {
        Connection connection = dbService.retrieveConnection();

        try (PreparedStatement statement = connection.prepareStatement("insert into student (surname, firstname, secondname, sex_id, course_id) values (?, ?, ?, ?, ?)")) {
            statement.setString(1, model.getSurname());
            statement.setString(2, model.getFirsname());
            statement.setString(3, model.getSecondname());
            statement.setInt(4, model.getSexId());
            statement.setInt(5, model.getCourse().getId());

            statement.execute();

        } catch (SQLException e) {
            logger.error(ERROR_MSG_STATEMENT, e);
            throw new RuntimeException(e);
        } finally {
            dbService.putBack(connection);
        }

        logger.info("Student {} successfully inserted", model);
    }

    @Override
    public void update(Student model) {
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
            statement.executeUpdate("CREATE TABLE if NOT EXISTS student (" +
                    "id int auto_increment PRIMARY KEY, " +
                    "surname VARCHAR2(60), " +
                    "firstname VARCHAr2(60), " +
                    "secondname VARCHAR2(60), " +
                    "sex_id int, " +
                    "course_id int" +
                    ")");

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
            statement.executeUpdate("DROP TABLE IF EXISTS student");
        } catch (SQLException e) {
            logger.error(ERROR_MSG_STATEMENT, e);
            throw new RuntimeException(e);
        } finally {
            dbService.putBack(connection);
        }
        logger.info("Table STUDENT dropped");
    }
}
