package dbService;

import dao.CourseDAO;
import dao.DAO;
import dao.StudentDAO;
import model.Course;
import model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static helpers.Constants.ERROR_MSG_GENERAL;

/**
 * Created by bbb1991 on 11/28/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class DBService {

    private static final Logger logger = LoggerFactory.getLogger(DBService.class);
    private Connection connection;
    private DAO<Student> studentDAO;
    private DAO<Course> courseDAO;

    private DBService() {
        studentDAO = new StudentDAO(this);
        courseDAO = new CourseDAO(this);

        connection = getConnection();

        courseDAO.dropTable();
        courseDAO.createTable();

        studentDAO.dropTable();
        studentDAO.createTable();

        setUp();
    }

    private void setUp() {

        courseDAO.save(new Course(1, "Computer Science"));
        courseDAO.save(new Course(1, "Software Engineering"));

        studentDAO.save(new Student(1, "Smith", "John", "Jr", 1, new Course(1, "Software Engineering")));
        studentDAO.save(new Student(2, "Doe", "Jane", null, 2, new Course(1, "Computer Science")));
    }

    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    public Course getCourse(int id) {
        return courseDAO.findById(id);
    }

    public void saveStudent(String sn, String fn, String ln, int i, Course course) {
        studentDAO.save(new Student(sn, fn, ln, i, course));
    }

    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }

    private static class DBServiceHolder {
        private static DBService instance = new DBService();
    }

    public static DBService getInstance() {
        return DBServiceHolder.instance;
    }

    public Connection retrieveConnection() {
        return connection;
    }

    public void putBack(Connection connection) {

    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:" + System.getProperty("user.dir")+"/h2", "root", "root");
        } catch (SQLException e) {
            logger.error(ERROR_MSG_GENERAL, e);
            throw new RuntimeException(e);
        }
    }
}
