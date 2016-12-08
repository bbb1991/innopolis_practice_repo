package me.bbb1991.dao;

import me.bbb1991.dbService.DBService;
import me.bbb1991.model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by bbb1991 on 11/28/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class StudentDAO implements DAO<Student> {

    private static final Logger logger = LoggerFactory.getLogger(StudentDAO.class);

    @Autowired
    private DBService dbService;

    @Override
    public Student findById(Integer id) {
        Session session = dbService.getSessionFactory().openSession();
        Student student =  session.find(Student.class, id);
        session.close();
        return student;
    }

    @Override
    public List<Student> findAll() {

        logger.info("Getting students list...");

        Session session =  dbService.getSessionFactory().openSession();

        List<Student> students = session.createQuery("select s from Student s").getResultList();

        session.close();

        logger.info("Got students! Size is: {}", students.size());

        return students;
    }

    @Override
    public void save(Student model) {
        logger.info("Saving new student: {}", model);
        Session session = dbService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(model);

        transaction.commit();
        session.close();

        logger.info("Student saved!");
    }

    @Override
    public void update(Student model) {
        Session session = dbService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(model);

        transaction.commit();

        session.close();
    }

    @Override
    public void delete(Integer id) {

        Session session = dbService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.find(Student.class, id);
        session.remove(student);

        transaction.commit();

        session.close();

    }


}
