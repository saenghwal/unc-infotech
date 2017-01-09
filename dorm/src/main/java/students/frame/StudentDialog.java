package students.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import students.logic.Room;
import students.logic.ManagementSystem;
import students.logic.Student;

public class StudentDialog extends JDialog implements ActionListener {

    private static final int D_HEIGHT = 220;   // высота окна
    private final static int D_WIDTH = 450;   // ширина окна
    private final static int L_X = 10;      // левая граница метки для поля
    private final static int L_W = 100;     // ширина метки для поля
    private final static int C_W = 150;     // ширина поля
    // Владелец нашего окна - вводим для вызова нужного нам метода
    private StudentsFrame owner;
    // Результат нажатия кнопок
    private boolean result = false;
    // Параметры студента
    private int studentId = 0;
    private JTextField firstName = new JTextField();
    private JTextField surname = new JTextField();
    private JTextField patronymic = new JTextField();
    private JSpinner dateOfBirth = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
    private ButtonGroup sex = new ButtonGroup();
    private JSpinner year = new JSpinner(new SpinnerNumberModel(2016, 2000, 2020, 1));
    private JComboBox groupList;

    private static int SOURCE_ID = -1;

    // Второй параметр содержит знак - добавление студента или исправление
    public StudentDialog(List<Room> rooms, boolean newStudent, StudentsFrame owner, int sourceId) {
        // После вставки студента без закрытия окна нам потребуется перегрузка списка
        // А для этого нам надо иметь доступ к этому методу в главной форме
        this.owner = owner;
        // Установить заголовок
        if (newStudent) {
            setTitle("Данные нового студента");
        } else {
            setTitle("Редактирование данных студента");
        }
        getContentPane().setLayout(new FlowLayout());

        groupList = new JComboBox(new Vector<Room>(rooms));

        JRadioButton m = new JRadioButton("Муж");
        JRadioButton w = new JRadioButton("Жен");
        // Сделаем имя такое же, как требуется в баще данных - М/Ж
        m.setActionCommand("М");
        w.setActionCommand("Ж");
        // Добавим радио-кнопки в группу
        sex.add(m);
        sex.add(w);

        // Не будем использовать layout совсем
        getContentPane().setLayout(null);

        // Разместим компоненты по абсолютным координатам
        // Фамилия
        JLabel l = new JLabel("Фамилия:", JLabel.RIGHT);
        l.setBounds(L_X, 10, L_W, 20);
        getContentPane().add(l);
        surname.setBounds(L_X + L_W + 10, 10, C_W, 20);
        getContentPane().add(surname);
        // Имя
        l = new JLabel("Имя:", JLabel.RIGHT);
        l.setBounds(L_X, 30, L_W, 20);
        getContentPane().add(l);
        firstName.setBounds(L_X + L_W + 10, 30, C_W, 20);
        getContentPane().add(firstName);
        // Отчество
        l = new JLabel("Отчество:", JLabel.RIGHT);
        l.setBounds(L_X, 50, L_W, 20);
        getContentPane().add(l);
        patronymic.setBounds(L_X + L_W + 10, 50, C_W, 20);
        getContentPane().add(patronymic);
        // Пол
        l = new JLabel("Пол:", JLabel.RIGHT);
        l.setBounds(L_X, 70, L_W, 20);
        getContentPane().add(l);
        m.setBounds(L_X + L_W + 10, 70, C_W / 2, 20);
        getContentPane().add(m);
        // Сделаем по умолчанию женщину - из уважения
        w.setBounds(L_X + L_W + 10 + C_W / 2, 70, C_W / 2, 20);
        w.setSelected(true);
        getContentPane().add(w);
        // Дата рождения
        l = new JLabel("Дата рождения:", JLabel.RIGHT);
        l.setBounds(L_X, 90, L_W, 20);
        getContentPane().add(l);
        dateOfBirth.setBounds(L_X + L_W + 10, 90, C_W, 20);
        getContentPane().add(dateOfBirth);
        // Группа
        l = new JLabel("Группа:", JLabel.RIGHT);
        l.setBounds(L_X, 115, L_W, 25);
        getContentPane().add(l);
        groupList.setBounds(L_X + L_W + 10, 115, C_W, 25);
        getContentPane().add(groupList);
        // Год обучения
        l = new JLabel("Год обучения:", JLabel.RIGHT);
        l.setBounds(L_X, 145, L_W, 20);
        getContentPane().add(l);
        year.setBounds(L_X + L_W + 10, 145, C_W, 20);
        getContentPane().add(year);

        JButton btnOk = new JButton("Применить");
        btnOk.setName("OK");
        btnOk.addActionListener(this);
        btnOk.setBounds(L_X + L_W + C_W + 10 + 50, 10, 100, 25);
        getContentPane().add(btnOk);

        JButton btnCancel = new JButton("Отмена");
        btnCancel.setName("Cancel");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(L_X + L_W + C_W + 10 + 50, 40, 100, 25);
        getContentPane().add(btnCancel);

        if (newStudent) {
            JButton btnNew = new JButton("Добавить");
            btnNew.setName("Add");
            btnNew.addActionListener(this);
            btnNew.setBounds(L_X + L_W + C_W + 10 + 50, 70, 100, 25);
            getContentPane().add(btnNew);
        }

        // Устанавливаем поведение формы при закрытии - не закрывать совсем.
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // Получаем размеры экрана
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        // А теперь просто помещаем его по центру, вычисляя координаты на основе полученной информации
        setBounds(((int) d.getWidth() - StudentDialog.D_WIDTH) / 2, ((int) d.getHeight() - StudentDialog.D_HEIGHT) / 2,
                StudentDialog.D_WIDTH, StudentDialog.D_HEIGHT);

        SOURCE_ID = sourceId;
    }

    // Установить поля соответственно переданным данным о студенте
    public void setStudent(Student st) {
        studentId = st.getStudentId();
        firstName.setText(st.getFirstName());
        surname.setText(st.getSurname());
        patronymic.setText(st.getPatronymic());
        dateOfBirth.getModel().setValue(st.getDateOfBirth());
        for (Enumeration e = sex.getElements(); e.hasMoreElements();) {
            AbstractButton ab = (AbstractButton) e.nextElement();
            ab.setSelected(ab.getActionCommand().equals(new String("" + st.getSex())));
        }
        year.getModel().setValue(new Integer(st.getEducationYear()));
        for (int i = 0; i < groupList.getModel().getSize(); i++) {
            Room g = (Room) groupList.getModel().getElementAt(i);
            if (g.getRoomId() == st.getRoomId() && g.getStudentsCount() < g.getCapacity()) {
                groupList.setSelectedIndex(i);
                break;
            }
        }
    }

    // Вернуть данные в виде нового студента с соответствующими полями
    public Student getStudent() {
        Student st = new Student();
        st.setStudentId(studentId);
        st.setFirstName(firstName.getText());
        st.setSurname(surname.getText());
        st.setPatronymic(patronymic.getText());
        Date d = ((SpinnerDateModel) dateOfBirth.getModel()).getDate();
        st.setDateOfBirth(d);
        for (Enumeration e = sex.getElements(); e.hasMoreElements();) {
            AbstractButton ab = (AbstractButton) e.nextElement();
            if (ab.isSelected()) {
                st.setSex(ab.getActionCommand().charAt(0));
            }
        }
        int y = ((SpinnerNumberModel) year.getModel()).getNumber().intValue();
        st.setEducationYear(y);
        st.setRoomId(((Room) groupList.getSelectedItem()).getRoomId());
        return st;
    }

    // Получить результат, true - кнопка ОК, false - кнопка Cancel
    public boolean getResult() {
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        // Добавляем студента, но не закрываем окно
        // Здесь мы не будем вызывать в отдельном потоке сохранение.
        // Оно не занимаем много времени и лишние действия здесь не оправданы
        Student newStudent = getStudent();

        Room room = null;

        try {
            for (Room r : ManagementSystem.getInstance().getRooms(SOURCE_ID)) {
                if (r.getRoomId() == newStudent.getRoomId()) {
                    room = r;
                    room.setStudentsCount(ManagementSystem.getInstance().getStudentsByGroup(
                            room, getStudent().getEducationYear(), SOURCE_ID).size());
                    break;
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if (src.getName().equals("Cancel")) {
            result = false;
            setVisible(false);
            return;
        }

        if (room.getStudentsCount() <= room.getCapacity()) {
            if (src.getName().equals("Add") ) {
                try {
                    ManagementSystem.getInstance().insertStudent(getStudent(), SOURCE_ID);
                    owner.reloadStudents(SOURCE_ID);
                    firstName.setText("");
                    surname.setText("");
                    patronymic.setText("");
                    result = true;
                    setVisible(false);
                } catch (Exception sql_e) {
                    JOptionPane.showMessageDialog(this, sql_e.getMessage());
                }
                return;
            }
            if (src.getName().equals("OK")) {
                result = true;
                setVisible(false);
            }
        } else {
            result = false;
            JOptionPane.showMessageDialog(this, "Превышено допустимое количество студентов");
        }


    }
}