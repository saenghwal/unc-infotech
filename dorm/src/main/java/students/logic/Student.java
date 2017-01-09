package students.logic;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import java.text.DateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.Date;
import java.util.Locale;

public class Student implements Comparable {

    private int studentId;
    private String firstName;
    private String surname;
    private String patronymic;
    private Date dateOfBirth;
    private char sex;
    private int roomId;
    private int educationYear;

    public Student() {
    }

    public Student(ResultSet rs) throws SQLException {
        setStudentId(rs.getInt(1));
        setFirstName(rs.getString(2));
        setPatronymic(rs.getString(3));
        setSurname(rs.getString(4));
        setSex(rs.getString(5).charAt(0));
        setDateOfBirth(rs.getDate(6));
        setRoomId(rs.getInt(7));
        setEducationYear(rs.getInt(8));
    }

    public Student(Element el, int roomId) throws DataConversionException {
        setStudentId(el.getAttribute("id").getIntValue());
        setSurname(el.getAttribute("surname").getValue());
        setFirstName(el.getAttribute("firstName").getValue());
        setPatronymic(el.getAttribute("patronymic").getValue());
        setDateOfBirth(new Date(el.getAttribute("dateOfBirth").getLongValue()));
        setSex(el.getAttribute("sex").getValue().charAt(0));
        setEducationYear(el.getAttribute("educationYear").getIntValue());
        setRoomId(roomId);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth != null) {
            this.dateOfBirth = dateOfBirth;
        } else {
            DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date());
        }

    }

    public int getEducationYear() {
        return educationYear;
    }

    public void setEducationYear(int educationYear) {
        this.educationYear = educationYear;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String toString() {
        return surname + " " + firstName + " " + patronymic + ", " + " Room ID=" + roomId + " Education Year:" + educationYear;
    }

    public int compareTo(Object obj) {
        Collator c = Collator.getInstance(new Locale("ru"));
        c.setStrength(Collator.PRIMARY);
        return c.compare(this.toString(), obj.toString());
    }
}