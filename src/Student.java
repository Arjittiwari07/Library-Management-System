import java.util.ArrayList;
import java.io.Serializable;

public class Student implements Serializable {
    private int studentId;
    private String name;
    private String Institution;
    private int RollNo;
    private String password;
    private ArrayList<Book> issuedBooks;
    Student(int studentId, String name){
        this.name = name;
        this.studentId = studentId;
        this.Institution = null;
        this.RollNo = 0;
        this.issuedBooks = new ArrayList<>();
        this.password = null;
    }
    Student(int studentId, String name, int RollNo, String Institution){
        this.studentId = studentId;
        this.name = name;
        this.Institution = Institution;
        this.RollNo = RollNo;
        this.issuedBooks = new ArrayList<>();
        this.password = null;
    }
    Student(int studentId, String name, int RollNo, String Institution, ArrayList<Book> list, String password){
        this.studentId = studentId;
        this.name = name;
        this.Institution = Institution;
        this.RollNo = RollNo;
        this.issuedBooks = list;
        this.password = password;
    }
    void setStudentId(int StudentId){
        this.studentId = StudentId;
    }
    int getStudentId(){
        return studentId;
    }
    void setName(String name){
        this.name = name;
    }
    String getName(){
        return name;
    }
    void setRollNo(int RollNo){
        this.RollNo = RollNo;
    }
    int getRollNo(){
        return RollNo;
    }
    void setInstitution(String Institution){
        this.Institution = Institution;
    }
    String getInstitution(){
        return Institution;
    }
    void setPassword(String password){
        this.password = password;
    }
    String getPassword(){
        return password;
    }
    ArrayList<Book> getIssuedBooks(){
        return issuedBooks;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(!(obj instanceof Student))
            return false;
        Student s = (Student) obj;
        if(this.studentId == s.studentId){
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return name+"-"+Institution;
    }
}
