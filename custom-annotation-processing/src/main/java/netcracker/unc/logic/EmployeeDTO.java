package netcracker.unc.logic;


import java.util.Date;

import netcracker.unc.annotation.InjectRandomInt;

public class EmployeeDTO {
    private String firstName = "Mr.";
    private String lastName = "Smith";

    @InjectRandomInt(min=20, max=50)
    private Integer age;

    private String emailId;

    private String sex;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
