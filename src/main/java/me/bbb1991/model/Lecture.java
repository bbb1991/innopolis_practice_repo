package me.bbb1991.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by bbb1991 on 11/29/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */

@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column
    private Date beginDate;

    @Column
    private String location;

    @SuppressWarnings("UnusedDeclaration")
    public Lecture() {}

    public Lecture(Integer id, String name, Date beginDate, String location) {
        this.id = id;
        this.name = name;
        this.beginDate = beginDate;
        this.location = location;
    }

    public Lecture(String name, Date beginDate, String location) {
        this.name = name;
        this.beginDate = beginDate;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beginDate=" + beginDate +
                ", location='" + location + '\'' +
                '}';
    }
}
