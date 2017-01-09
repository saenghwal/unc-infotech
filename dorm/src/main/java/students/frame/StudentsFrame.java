package students.frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.Vector;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import students.logic.Room;
import students.logic.ManagementSystem;
import students.logic.Student;

public class StudentsFrame extends JFrame implements ActionListener, ListSelectionListener, ChangeListener {
    // Введем сразу имена для кнопок - потом будем их использовать в обработчиках

    private static final String MOVE_GR = "moveRoom";
    private static final String CLEAR_GR = "clearRoom";
    private static final String INSERT_ST = "insertStudent";
    private static final String UPDATE_ST = "updateStudent";
    private static final String DELETE_ST = "deleteStudent";
    private static final String STUDENTS_DB = "studentsDB";
    private static final String STUDENTS_FILE = "studentsFile";

    public static final int DB_SOURCE = 0;
    public static final int FILE_SOURCE = 1;

    private static int currentTabSourceId = DB_SOURCE;

    private ManagementSystem ms = null;
    private JList roomsList;
    private JTable studentsList;
    private JSpinner yearSpinner;

    public StudentsFrame(String title) throws Exception {
        super(title);

        getContentPane().setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Ресурс");

        JMenuItem dbMenuItem = new JMenuItem("База данных");
        dbMenuItem.setName(STUDENTS_DB);
        JMenuItem xmlMenuItem = new JMenuItem("XML-файл");
        xmlMenuItem.setName(STUDENTS_FILE);

        dbMenuItem.addActionListener(this);

        menu.add(dbMenuItem);

        xmlMenuItem.addActionListener(this);

        menu.add(xmlMenuItem);
        menuBar.add(menu);

        menu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Выход");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setJMenuBar(menuBar);

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.LEFT));

        top.add(new JLabel("Год обучения:"));
        SpinnerModel sm = new SpinnerNumberModel(2016, 2006, 2026, 1);
        yearSpinner = new JSpinner(sm);
        yearSpinner.addChangeListener(this);
        top.add(yearSpinner);

        JPanel bot = new JPanel();
        bot.setLayout(new BorderLayout());

        GroupPanel left = new GroupPanel();
        left.setLayout(new BorderLayout());
        left.setBorder(new BevelBorder(BevelBorder.LOWERED));

        // Получаем коннект к базе и создаем объект ManagementSystem
        ms = ManagementSystem.getInstance();

        Vector<Room> rooms = new Vector<>(ms.getRooms(currentTabSourceId));

        left.add(new JLabel("Комнаты"), BorderLayout.NORTH);

        roomsList = new JList(rooms);
        roomsList.addListSelectionListener(this);
        roomsList.setSelectedIndex(0);
        left.add(new JScrollPane(roomsList), BorderLayout.CENTER);
        JButton btnMvGr = new JButton("Перезаселить");
        btnMvGr.setName(MOVE_GR);
        JButton btnClGr = new JButton("Освободить");
        btnClGr.setName(CLEAR_GR);
        btnMvGr.addActionListener(this);
        btnClGr.addActionListener(this);
        JPanel pnlBtnGr = new JPanel();
        pnlBtnGr.setLayout(new GridLayout(1, 2));
        pnlBtnGr.add(btnMvGr);
        pnlBtnGr.add(btnClGr);
        left.add(pnlBtnGr, BorderLayout.SOUTH);

        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());
        right.setBorder(new BevelBorder(BevelBorder.LOWERED));

        right.add(new JLabel("Студенты"), BorderLayout.NORTH);
        // Создаем таблицу и вставляем ее в скроллируемую
        // панель, которую в свою очередь уже кладем на панель right
        // Наша таблица пока ничего не умеет - просто положим ее как заготовку
        // Сделаем в ней 4 колонки - Фамилия, Имя, Отчество, Дата рождения
        studentsList = new JTable(1, 4);
        right.add(new JScrollPane(studentsList), BorderLayout.CENTER);
        JButton btnAddSt = new JButton("Добавить");
        btnAddSt.setName(INSERT_ST);
        btnAddSt.addActionListener(this);
        JButton btnUpdSt = new JButton("Редактировать");
        btnUpdSt.setName(UPDATE_ST);
        btnUpdSt.addActionListener(this);
        JButton btnDelSt = new JButton("Удалить");
        btnDelSt.setName(DELETE_ST);
        btnDelSt.addActionListener(this);
        JPanel pnlBtnSt = new JPanel();
        pnlBtnSt.setLayout(new GridLayout(1, 3));
        pnlBtnSt.add(btnAddSt);
        pnlBtnSt.add(btnUpdSt);
        pnlBtnSt.add(btnDelSt);
        right.add(pnlBtnSt, BorderLayout.SOUTH);

        bot.add(left, BorderLayout.WEST);
        bot.add(right, BorderLayout.CENTER);

        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(bot, BorderLayout.CENTER);

        setBounds(100, 100, 700, 500);

    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    // Мы сразу отменим продолжение работы, если не сможем получить
                    // коннект к базе данных
                    StudentsFrame sf = new StudentsFrame("Dorm`оеды");
                    sf.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    sf.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                            Object[] options = {"Да", "Нет"};
                            int n = JOptionPane.showOptionDialog(e.getWindow(),
                                    "Вы уверены, что хотите выйти?", "Dorm`оеды",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                    null, options, options[0]);
                            if (n == 0) {
                                e.getWindow().setVisible(false);
                                System.exit(0);
                            } else {

                            }
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {

                        }

                        @Override
                        public void windowIconified(WindowEvent e) {

                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {

                        }

                        @Override
                        public void windowActivated(WindowEvent e) {

                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {

                        }
                    });
                    sf.setVisible(true);
                    sf.reloadStudents(DB_SOURCE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    // Метод для обеспечения интерфейса ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Component) {
            Component c = (Component) e.getSource();
            if (c.getName().equals(MOVE_GR)) {
                moveRoom(currentTabSourceId);
            }
            if (c.getName().equals(CLEAR_GR)) {
                clearRoom(currentTabSourceId);
            }
            if (c.getName().equals(STUDENTS_DB)) {
                currentTabSourceId = DB_SOURCE;
                showAllStudents(currentTabSourceId);
            }
            if (c.getName().equals(STUDENTS_FILE)) {
                currentTabSourceId = FILE_SOURCE;
                showAllStudents(currentTabSourceId);
            }
            if (c.getName().equals(INSERT_ST)) {
                insertStudent(currentTabSourceId);
            }
            if (c.getName().equals(UPDATE_ST)) {
                updateStudent(currentTabSourceId);
            }
            if (c.getName().equals(DELETE_ST)) {
                deleteStudent(currentTabSourceId);
            }
        }
    }

    // Метод для обеспечения интерфейса ListSelectionListener
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            reloadStudents(currentTabSourceId);
        }
    }

    // Метод для обеспечения интерфейса ChangeListener
    public void stateChanged(ChangeEvent e) {
        reloadStudents(currentTabSourceId);
    }

    // метод для обновления списка студентов для определенной группы
    public void reloadStudents(final int sourceId) {
        Thread t = new Thread() {
            public void run() {
                if (studentsList != null) {
                    try {
                        int y = ((SpinnerNumberModel) yearSpinner.getModel()).getNumber().intValue();
                        Room room = (Room) roomsList.getSelectedValue();

                        if (room != null) {
                            Collection<Student> s = ms.getStudentsByGroup(room, y, sourceId);
                            studentsList.setModel(new StudentTableModel(new Vector<Student>(s)));
                            room.setStudentsCount(studentsList.getRowCount());
                        }

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(StudentsFrame.this, e.getMessage());
                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
                    }
                }
            }
        };

        t.start();
    }

    // метод для переноса группы
    private void moveRoom(final int sourceId) {
        Thread t = new Thread() {

            public void run() {
                if (roomsList.getSelectedValue() == null) {
                    return;
                }
                try {
                    Room g = (Room) roomsList.getSelectedValue();
                    int y = ((SpinnerNumberModel) yearSpinner.getModel()).getNumber().intValue();
                    GroupDialog gd = new GroupDialog(y, ms.getRooms(sourceId));
                    gd.setModal(true);
                    gd.setVisible(true);
                    // Если нажали кнопку OK - перемещаем в новую группу с новым годом
                    // и перегружаем список студентов

                    Room destinationRoom = null;
                    for (Room r : ms.getRooms(sourceId)) {
                        if (r.getRoomId() == gd.getGroup().getRoomId()) {
                            destinationRoom = r;
                            destinationRoom.setStudentsCount(ms.getStudentsByGroup(
                                    destinationRoom, gd.getYear(), sourceId).size());
                            break;
                        }
                    }

                    if (gd.getResult()) {
                        if ((g.getStudentsCount() + destinationRoom.getStudentsCount()) <= gd.getGroup().getCapacity()) {
                            ms.moveStudentsToRoom(g, y, gd.getGroup(), gd.getYear(), sourceId);
                            reloadStudents(sourceId);
                        } else {
                            JOptionPane.showMessageDialog(StudentsFrame.this,
                                    "Превышено допустимое количество студентов или комната уже заселена.");
                        }
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(StudentsFrame.this, e.getMessage());
                }

            }
        };
        t.start();
    }

    private void clearRoom(final int sourceId) {
        Thread t = new Thread() {

            public void run() {
                if (roomsList.getSelectedValue() != null) {
                    Object[] options = {"Да", "Нет"};

                    if (JOptionPane.showOptionDialog(StudentsFrame.this,
                            "Вы хотите удалить студентов из комнаты?", "Удаление студентов",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, options[0]) == JOptionPane.YES_OPTION) {

                        Room g = (Room) roomsList.getSelectedValue();
                        int y = ((SpinnerNumberModel) yearSpinner.getModel()).getNumber().intValue();
                        try {
                            ms.removeStudentsFromRoom(g, y, sourceId);
                            reloadStudents(sourceId);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(StudentsFrame.this, e.getMessage());
                        }
                    }
                }
            }
        };
        t.start();
    }

    private void insertStudent(final int sourceId) {
        Thread t = new Thread() {

            public void run() {
                try {
                    // Добавляем нового студента - поэтому true
                    // Также заметим, что необходимо указать не просто this, а StudentsFrame.this
                    // Иначе класс не будет воспринят - он же другой - анонимный
                    StudentDialog sd = new StudentDialog(ms.getRooms(sourceId), true, StudentsFrame.this, sourceId);

                    StudentTableModel stm = (StudentTableModel) studentsList.getModel();

                    sd.setModal(true);
                    sd.setVisible(true);
                    if (sd.getResult()) {
                        Student s = sd.getStudent();
                        ms.insertStudent(s, sourceId);
                        reloadStudents(sourceId);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(StudentsFrame.this, e.getMessage());
                }
            }
        };
        t.start();
    }

    private void updateStudent(final int sourceId) {
        Thread t = new Thread() {

            public void run() {
                if (studentsList != null) {
                    StudentTableModel stm = (StudentTableModel) studentsList.getModel();
                    if (studentsList.getSelectedRow() >= 0) {
                        // Вот где нам пригодился метод getStudent(int rowIndex)
                        Student s = stm.getStudent(studentsList.getSelectedRow());
                        try {
                            // Исправляем данные на студента - поэтому false
                            // Также заметим, что необходимо указать не просто this, а StudentsFrame.this
                            // Иначе класс не будет воспринят - он же другой - анонимный
                            StudentDialog sd = new StudentDialog(ms.getRooms(sourceId), false, StudentsFrame.this, currentTabSourceId);
                            sd.setStudent(s);
                            sd.setModal(true);
                            sd.setVisible(true);
                            if (sd.getResult()) {
                                Student us = sd.getStudent();
                                ms.updateStudent(us, sourceId, s);
                                reloadStudents(sourceId);
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(StudentsFrame.this, e.getMessage());
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(StudentsFrame.this,
                                "Необходимо выделить студента в списке");
                    }
                }
            }
        };
        t.start();
    }

    private void deleteStudent(final int sourceId) {
        Thread t = new Thread() {

            public void run() {
                Object[] options = {"Да", "Нет"};

                if (studentsList != null) {
                    StudentTableModel stm = (StudentTableModel) studentsList.getModel();
                    if (studentsList.getSelectedRow() >= 0) {
                        if (JOptionPane.showOptionDialog(StudentsFrame.this,
                                "Вы хотите удалить студента?", "Удаление студента",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, options, options[0]) == JOptionPane.YES_OPTION) {

                            // Вот где нам пригодился метод getStudent(int rowIndex)
                            Student s = stm.getStudent(studentsList.getSelectedRow());
                            try {
                                ms.deleteStudent(s, sourceId);
                                reloadStudents(sourceId);
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(StudentsFrame.this, e.getMessage());
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(StudentsFrame.this, "Необходимо выделить студента в списке");
                    }
                }
            }
        };
        t.start();
    }

    private void showAllStudents(int sourceType) {
        roomsList.setListData(new Vector<>(ms.getRooms(sourceType)));
        roomsList.setSelectedIndex(0);
        reloadStudents(sourceType);
    }

}

class GroupPanel extends JPanel {
    public Dimension getPreferredSize() {
        return new Dimension(250, 0);
    }
}