package me.bbb1991.dao;

import me.bbb1991.dbService.DBService;
import me.bbb1991.model.Lecture;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by bbb1991 on 11/29/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class LectureDAO implements DAO<Lecture> {

    @Autowired
    private DBService dbService;
    private static final Logger logger = LoggerFactory.getLogger(LectureDAO.class);

    @Override
    public Lecture findById(Integer id) {
        Session session = dbService.getSessionFactory().openSession();
        Lecture lecture = session.find(Lecture.class, id);

        session.close();

        return lecture;
    }

    @Override
    public List<Lecture> findAll() {
        Session session = dbService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Lecture> lectures =  session.createQuery("select l from Lecture l").getResultList();

        session.close();

        return lectures;
    }

    @Override
    public void save(Lecture model) {

        Session session = dbService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(model);

        transaction.commit();

        session.close();
    }

    @Override
    public void update(Lecture model) {

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
        Lecture lecture = session.find(Lecture.class, id);
        session.remove(lecture);

        transaction.commit();

        session.close();


    }

}
