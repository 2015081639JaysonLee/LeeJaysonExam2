package exam2.lee.jayson.com.finals_4itf_lee;

public class Student {
    private String Firstname;
    private String Lastname;
    private Integer ave;

    public Student(){

    }

    public Student(String lastname, String firstname, Integer ave){
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.ave = ave;
    }

    public Integer getAve() {
        return ave;
    }

    public void setAve(Integer ave) {
        this.ave = ave;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getFirstname() {

        return Firstname;
    }

    public void setFirstname(String Firstname) {

        this.Firstname = Firstname;
    }

}
