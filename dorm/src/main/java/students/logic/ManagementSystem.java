package students.logic;


import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import students.frame.StudentsFrame;


public class ManagementSystem {

    private static Connection con;
    private static ManagementSystem instance;
    private static int maxId = 0;
    private static Document document;

    private static final String FILE_PATH = ".\\src\\main\\resources\\dorm.xml";
    private static File XML_FILE = null;

    private ManagementSystem() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/dorm";
            con = DriverManager.getConnection(url, "dormuser", "dormuser");
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public static synchronized ManagementSystem getInstance() throws Exception {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }

    public List<Room> getRooms(int sourceId) {
        List<Room> rooms = new ArrayList<Room>();

        if (sourceId == StudentsFrame.DB_SOURCE) {
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT room_id, room_number, capacity FROM room");
                while (rs.next()) {
                    Room room = new Room();
                    room.setRoomId(rs.getInt(1));
                    room.setRoomNumber(rs.getString(2));
                    room.setCapacity(rs.getInt(3));

                    rooms.add(room);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            try {
                Document document = getDocument();
                Element dorm = document.getRootElement();
                List<Element> roomNodes = dorm.getChildren("room");

                for (Element r : roomNodes) {
                    if (r.getAttribute("id") != null) {
                        Room foundedRoom = new Room();
                        foundedRoom.setRoomId(r.getAttribute("id").getIntValue());
                        foundedRoom.setRoomNumber(r.getAttribute("roomNumber").getValue());
                        foundedRoom.setCapacity(r.getAttribute("capacity").getIntValue());
                        rooms.add(foundedRoom);
                    }
                }
            } catch (DataConversionException e) {
                e.printStackTrace();
            }

        }

        return rooms;
    }

    public Collection<Student> getAllStudents(int sourceId) throws SQLException {
        Collection<Student> students = new ArrayList<Student>();

        if (sourceId == StudentsFrame.DB_SOURCE) {
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(
                        "SELECT student_id, first_name, patronymic, surname, " +
                                "sex, date_of_birth, room_id, education_year FROM student " +
                                "ORDER BY surname, first_name, patronymic");
                while (rs.next()) {
                    Student st = new Student(rs);
                    students.add(st);
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }
        }

        return students;
    }

    public Collection<Student> getStudentsByGroup(Room room, int year, int sourceId) throws SQLException {
        Collection<Student> students = new ArrayList<Student>();

        if (sourceId == StudentsFrame.DB_SOURCE) {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                stmt = con.prepareStatement(
                        "SELECT student_id, first_name, patronymic, surname, " +
                                "sex, date_of_birth, room_id, education_year FROM student " +
                                "WHERE room_id=? AND education_year=? " +
                                "ORDER BY surname, first_name, patronymic");
                stmt.setInt(1, room.getRoomId());
                stmt.setInt(2, year);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Student st = new Student(rs);
                    students.add(st);
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }
        } else {
            Element foundedRoom = null;
            try {
                Document document = getDocument();
                Element dorm = document.getRootElement();
                List<Element> rooms = dorm.getChildren("room");

                for (Element r : rooms) {
                    if (r.getAttribute("id") != null && room != null && r.getAttribute("id").getIntValue() == room.getRoomId()
                            && room.getRoomNumber().equals(r.getAttribute("roomNumber").getValue())) {
                        //нашли искомую комнату - выходим из цикла
                        foundedRoom = r;
                        break;
                    }
                }

                if (foundedRoom != null) {
                    List<Element> studentNodes= foundedRoom.getChildren("student");

                    if (studentNodes != null && !studentNodes.isEmpty()) {
                        for (Element sn : studentNodes) {
                            if (sn.getAttribute("educationYear").getIntValue() == year)
                                students.add(new Student(sn, foundedRoom.getAttribute("id").getIntValue()));
                        }
                    }
                }
            } catch (DataConversionException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException aio) {
                aio.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }

        }

        return students;
    }

    public void moveStudentsToRoom(Room oldRoom, int oldYear, Room newRoom, int newYear, int sourceId) throws SQLException {
        if (sourceId == StudentsFrame.DB_SOURCE) {
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement(
                        "UPDATE student SET room_id=?, education_year=? " +
                                "WHERE room_id=? AND education_year=?");
                stmt.setInt(1, newRoom.getRoomId());
                stmt.setInt(2, newYear);
                stmt.setInt(3, oldRoom.getRoomId());
                stmt.setInt(4, oldYear);
                stmt.execute();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } else {
            try {
                Element currentRoom = null;
                Element destRoomNode = null;

                List<Element> movedStudentNodes= new ArrayList<>();

                boolean toNewYear = true;
                boolean toNewRoom = true;

                document = getDocument();

                Element dorm = document.getRootElement();
                List<Element> roomNodes = dorm.getChildren("room");

                for (Element r : roomNodes) {
                    if (r.getAttribute("id") != null && r.getAttribute("id").getIntValue() == oldRoom.getRoomId()) {
                        currentRoom = r;
                        break;
                    }
                }

                for (Element r : roomNodes) {
                    if (r.getAttribute("id") != null && r.getAttribute("id").getIntValue() == newRoom.getRoomId()) {
                        destRoomNode = r;
                        break;
                    }
                }

                if (oldYear == newYear) {
                    toNewYear = false;
                }

                if (oldRoom.getRoomId() == newRoom.getRoomId()) {
                    toNewRoom = false;
                }

                if (currentRoom != null) {
                    List<Element> studentNodes= currentRoom.getChildren("student");

                    if (studentNodes != null && !studentNodes.isEmpty()) {

                        if (toNewYear && !toNewRoom) {
                            for (Element sn : studentNodes) {
                                sn.getAttribute("educationYear").setValue(String.valueOf(newYear));
                            }
                        }

                        if (!toNewYear && toNewRoom) {
                            if (studentNodes != null && !studentNodes.isEmpty()) {
                                for (Element sn : studentNodes) {
                                    if (sn.getAttribute("educationYear").getIntValue() == oldYear) {
                                        Element addedNode = new Element("student");

                                        addedNode.setAttribute("id", sn.getAttribute("id").getValue());
                                        addedNode.setAttribute("firstName", sn.getAttribute("firstName").getValue());
                                        addedNode.setAttribute("surname", sn.getAttribute("surname").getValue());
                                        addedNode.setAttribute("patronymic", sn.getAttribute("patronymic").getValue());
                                        addedNode.setAttribute("sex", sn.getAttribute("sex").getValue().substring(0, 1));
                                        addedNode.setAttribute("dateOfBirth", sn.getAttribute("dateOfBirth").getValue());
                                        addedNode.setAttribute("educationYear", sn.getAttribute("educationYear").getValue());

                                        destRoomNode.addContent(addedNode);
                                    }
                                }

                                while (!currentRoom.getChildren("student").isEmpty()) {
                                    currentRoom.removeChild("student");
                                }
                            }
                        }

                        if (toNewYear && toNewRoom) {
                            if (studentNodes != null && !studentNodes.isEmpty()) {
                                for (Element sn : studentNodes) {
                                    if (sn.getAttribute("educationYear").getIntValue() == oldYear) {
                                        Element addedNode = new Element("student");

                                        addedNode.setAttribute("id", sn.getAttribute("id").getValue());
                                        addedNode.setAttribute("firstName", sn.getAttribute("firstName").getValue());
                                        addedNode.setAttribute("surname", sn.getAttribute("surname").getValue());
                                        addedNode.setAttribute("patronymic", sn.getAttribute("patronymic").getValue());
                                        addedNode.setAttribute("sex", sn.getAttribute("sex").getValue().substring(0, 1));
                                        addedNode.setAttribute("dateOfBirth", sn.getAttribute("dateOfBirth").getValue());
                                        addedNode.setAttribute("educationYear", String.valueOf(newYear));

                                        destRoomNode.addContent(addedNode);
                                    }
                                }

                                while (!currentRoom.getChildren("student").isEmpty()) {
                                    currentRoom.removeChild("student");
                                }
                            }
                        }
                    }
                }

                XMLOutputter xmlOutputter = new XMLOutputter();
                xmlOutputter.setFormat(Format.getPrettyFormat());
                xmlOutputter.output(document, new FileOutputStream(XML_FILE.getAbsolutePath()));

            } catch (DataConversionException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeStudentsFromRoom(Room room, int year, int sourceId) throws SQLException {
        if (sourceId == StudentsFrame.DB_SOURCE) {
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement(
                        "DELETE FROM student WHERE room_id=? AND education_year=?");
                stmt.setInt(1, room.getRoomId());
                stmt.setInt(2, year);
                stmt.execute();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } else {
            try {
                Element foundedRoom = null;
                document = getDocument();

                Element dorm = document.getRootElement();
                List<Element> roomNodes = dorm.getChildren("room");

                for (Element r : roomNodes) {
                    if (r.getAttribute("id") != null && r.getAttribute("id").getIntValue() == room.getRoomId()) {
                        foundedRoom = r;
                        break;
                    }
                }

                if (foundedRoom != null) {
                    List<Element> studentNodes = foundedRoom.getChildren("student");

                    if (studentNodes != null && !studentNodes.isEmpty()) {
                        while (!foundedRoom.getChildren("student").isEmpty()) {
                            for (Element sn : studentNodes) {
                                if (sn.getAttribute("educationYear").getIntValue() == year) {
                                    foundedRoom.removeChild("student");
                                }
                            }
                        }
                    }
                }

                XMLOutputter xmlOutputter = new XMLOutputter();
                xmlOutputter.setFormat(Format.getPrettyFormat());
                xmlOutputter.output(document, new FileOutputStream(XML_FILE.getAbsolutePath()));

            } catch (DataConversionException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void insertStudent(Student student, int sourceId) throws SQLException {
        if (sourceId == StudentsFrame.DB_SOURCE) {
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement(
                        "INSERT INTO student " +
                                "(first_name, patronymic, surname, sex, date_of_birth, room_id, education_year) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, student.getFirstName());
                stmt.setString(2, student.getPatronymic());
                stmt.setString(3, student.getSurname());
                stmt.setString(4, new String(new char[]{student.getSex()}));
                stmt.setDate(5, new Date(student.getDateOfBirth().getTime()));
                stmt.setInt(6, student.getRoomId());
                stmt.setInt(7, student.getEducationYear());
                stmt.execute();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } else {
            try {
                Element newRoom = null;
                document = getDocument();

                Element dorm = document.getRootElement();
                List<Element> roomNodes = dorm.getChildren("room");

                for (Element r : roomNodes) {
                    if (r.getAttribute("id") != null && r.getAttribute("id").getIntValue() == student.getRoomId()) {
                        newRoom = r;
                        break;
                    }
                }

                if (newRoom != null) {
                    List<Element> studentNodes = newRoom.getChildren("student");

                    Element newStudent = new Element("student");

                    newStudent.setAttribute("id", String.valueOf(maxId+1));
                    newStudent.setAttribute("firstName", student.getFirstName());
                    newStudent.setAttribute("surname", student.getSurname());
                    newStudent.setAttribute("patronymic", student.getPatronymic());
                    newStudent.setAttribute("sex", new String(new char[]{student.getSex()}));
                    newStudent.setAttribute("dateOfBirth", String.valueOf(student.getDateOfBirth().getTime()));
                    newStudent.setAttribute("educationYear", String.valueOf(student.getEducationYear()));

                    newRoom.addContent(newStudent);
                }

                XMLOutputter xmlOutputter = new XMLOutputter();
                xmlOutputter.setFormat(Format.getPrettyFormat());
                xmlOutputter.output(document, new FileOutputStream(XML_FILE.getAbsolutePath()));

            } catch (DataConversionException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStudent(Student student, int sourceId, Student prevStudInfo) throws SQLException {
        if (sourceId == StudentsFrame.DB_SOURCE) {
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement(
                        "UPDATE student SET " +
                                "first_name=?, patronymic=?, surname=?, " +
                                "sex=?, date_of_birth=?, room_id=?, education_year=? " +
                                "WHERE student_id=?");
                stmt.setString(1, student.getFirstName());
                stmt.setString(2, student.getPatronymic());
                stmt.setString(3, student.getSurname());
                stmt.setString(4, new String(new char[]{student.getSex()}));
                stmt.setDate(5, new Date(student.getDateOfBirth().getTime()));
                stmt.setInt(6, student.getRoomId());
                stmt.setInt(7, student.getEducationYear());
                stmt.setInt(8, student.getStudentId());
                stmt.execute();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } else {
            try {
                Element oldRoom = null;
                Element newRoom = null;
                Element foundedStudent = null;

                boolean toNewRoom = true;
                boolean toNewYear= true;

                document = getDocument();

                Element dorm = document.getRootElement();
                List<Element> roomNodes = dorm.getChildren("room");

                for (Element r : roomNodes) {
                    if (r.getAttribute("id") != null && r.getAttribute("id").getIntValue() == prevStudInfo.getRoomId()) {
                        oldRoom = r;
                        break;
                    }
                }

                if (prevStudInfo.getRoomId() == student.getRoomId()) {
                    toNewRoom = false;
                }

                if (prevStudInfo.getEducationYear() == student.getEducationYear()) {
                    toNewYear = false;
                }

                if (oldRoom != null) {
                    List<Element> studentNodes= oldRoom.getChildren("student");

                    if (studentNodes != null && !studentNodes.isEmpty()) {
                        for (Element sn : studentNodes) {
                            if (sn.getAttribute("id").getIntValue() == prevStudInfo.getStudentId()) {
                                foundedStudent = sn;
                                break;
                            }
                        }
                    }
                }

                if (oldRoom != null && foundedStudent != null && !toNewRoom ) {
                    foundedStudent.getAttribute("firstName").setValue(student.getFirstName());
                    foundedStudent.getAttribute("surname").setValue(student.getSurname());
                    foundedStudent.getAttribute("patronymic").setValue(student.getPatronymic());
                    foundedStudent.getAttribute("sex").setValue(new String(new char[]{student.getSex()}));
                    foundedStudent.getAttribute("dateOfBirth").setValue(String.valueOf(student.getDateOfBirth().getTime()));
                    foundedStudent.getAttribute("educationYear").setValue(String.valueOf(student.getEducationYear()));
                }

                if (oldRoom != null && foundedStudent != null && toNewRoom) {
                    for (Element r : roomNodes) {
                        if (r.getAttribute("id") != null && r.getAttribute("id").getIntValue() == student.getRoomId()) {
                            newRoom = r;
                            break;
                        }
                    }

                    if (newRoom != null) {
                        Element newStudent = new Element("student");
                        newStudent.setAttribute("id", String.valueOf(student.getStudentId()));
                        newStudent.setAttribute("firstName", student.getFirstName());
                        newStudent.setAttribute("surname", student.getSurname());
                        newStudent.setAttribute("patronymic", student.getPatronymic());
                        newStudent.setAttribute("sex", new String(new char[]{student.getSex()}));
                        newStudent.setAttribute("dateOfBirth", String.valueOf(student.getDateOfBirth().getTime()));
                        newStudent.setAttribute("educationYear", String.valueOf(student.getEducationYear()));

                        newRoom.addContent(newStudent);

                        oldRoom.removeContent(foundedStudent);
                    }
                }

                XMLOutputter xmlOutputter = new XMLOutputter();
                xmlOutputter.setFormat(Format.getPrettyFormat());
                xmlOutputter.output(document, new FileOutputStream(XML_FILE.getAbsolutePath()));

            } catch (DataConversionException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteStudent(Student student, int sourceId) throws SQLException {
        if (sourceId == StudentsFrame.DB_SOURCE) {
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement(
                        "DELETE FROM student WHERE student_id=?");
                stmt.setInt(1, student.getStudentId());
                stmt.execute();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } else {
            try {
                Element foundedRoom = null;
                Element foundedStudent = null;
                document = getDocument();

                Element dorm = document.getRootElement();
                List<Element> roomNodes = dorm.getChildren("room");

                for (Element r : roomNodes) {
                    if (r.getAttribute("id") != null && r.getAttribute("id").getIntValue() == student.getRoomId()) {
                        foundedRoom = r;
                        break;
                    }
                }

                if (foundedRoom != null) {
                    List<Element> studentNodes = foundedRoom.getChildren("student");

                    if (studentNodes != null && !studentNodes.isEmpty()) {
                        for (Element sn : studentNodes) {
                            if (sn.getAttribute("id").getIntValue() == student.getStudentId()) {
                                foundedStudent = sn;
                                break;
                            }
                        }
                    }

                    if (foundedStudent != null) {
                        foundedRoom.removeContent(foundedStudent);
                    }
                }

                XMLOutputter xmlOutputter = new XMLOutputter();
                xmlOutputter.setFormat(Format.getPrettyFormat());
                xmlOutputter.output(document,
                        new FileOutputStream(XML_FILE.getAbsolutePath()));
            } catch (DataConversionException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Document getDocument() {
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            XML_FILE = new File(FILE_PATH);
            document = saxBuilder.build(XML_FILE);
            Element dorm = document.getRootElement();
            List<Element> roomNodes = dorm.getChildren("room");

            for (Element r : roomNodes) {
                List<Element> studentNodes= r.getChildren("student");

                if (studentNodes != null && !studentNodes.isEmpty()) {
                    for (Element sn : studentNodes) {
                        if (sn.getAttribute("id").getIntValue() > maxId)
                            maxId = sn.getAttribute("id").getIntValue();
                    }
                }
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        return document;
    }
}