import java.util.ArrayList;

public class Student {
    int studentId;
    String name;
    private String Institution;
    int RollNo;
    private String password;
    ArrayList<Book> issuedBook;
    Student(int studentId, String name){
        this.name = name;
        this.studentId = studentId;
        this.Institution = null;
        this.RollNo = 0;
        this.issuedBook = new ArrayList<>();
        this.password = null;
    }
    Student(int studentId, String name, int RollNo, String Institution){
        this.studentId = studentId;
        this.name = name;
        this.Institution = Institution;
        this.RollNo = RollNo;
        issuedBook = null;
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
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(!(obj instanceof Student))
            return false;
        Student s = (Student) obj;
        if(this.studentId == s.studentId && this.name.equalsIgnoreCase(s.name) && this.RollNo == s.RollNo){
            return true;
        }
        return false;
    }
}
