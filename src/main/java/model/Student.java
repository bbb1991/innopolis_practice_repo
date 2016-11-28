package model;

/**
 * Created by bbb1991 on 11/28/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class Student {

    private Integer id;
    private String surname;
    private String firsname;
    private String secondname;
    private int sexId;
    private Course course;

    public Student(Integer id, String surname, String firsname, String secondname, int sexId, Course course) {
        this.id = id;
        this.surname = surname;
        this.firsname = firsname;
        this.secondname = secondname;
        this.sexId = sexId;
        this.course = course;
    }

    public Student(String sn, String fn, String ln, int i, Course course) {
        this.surname = sn;
        this.firsname = fn;
        this.secondname = ln;
        this.sexId = i;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirsname() {
        return firsname;
    }

    public void setFirsname(String firsname) {
        this.firsname = firsname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public int getSexId() {
        return sexId;
    }

    public void setSexId(int sexId) {
        this.sexId = sexId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return surname + " " + firsname + " " + secondname;
    }
}
