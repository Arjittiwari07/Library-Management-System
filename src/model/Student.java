package model;

public class Student {
    // Attributes Started
    private int studentId;
    private String name;
    private String username;
    private String email;
    private long phone;
    private String course;
    private int semester;
    private String department;
    private String Institution;
    private String password;
    // Attributes Ended
    // Constructor
    public Student(String username, String name, String email, long phone, String course, int semester, String department, String Institution, String password){
        this.studentId = 0;     // Kept Default for a while only
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.semester = semester;
        this.department = department;
        this.Institution = Institution;
        this.password = password;
    }
    public Student(int studentId, String username, String name, String email, long phone, String course, int semester, String department, String Institution, String password){
        this.studentId = studentId;
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.semester = semester;
        this.department = department;
        this.Institution = Institution;
        this.password = password;
    }
    // Helper Funcitons Started
    public void setUsername(String username){
        this.username = username;
        System.out.println("Username Updated Successfully");
    }
    public String getUsername(){
        return username;
    }
    public void setStudentId(int StudentId){
        this.studentId = StudentId;
        System.out.println("Student Id Updated Successfully");
    }
    public int getStudentId(){
        return studentId;
    }
    public void setEmail(String email){
        this.email = email;
        System.out.println("Email Updated Successfully");
    }
    public String getEmail(){
        return email;
    }
    public void setPhoneNo(long phone){
        this.phone = phone;
        System.out.println("Phone No. Updated Successfully");
    }
    public long getPhoneNo(){
        return phone;
    }
    public void setCourse(String course){
        this.course = course;
        System.out.println("Course Updated Successfully");
    }
    public String getCourse(){
        return course;
    }
    public void setSemester(int semester){
        this.semester = semester;
        System.out.println("Semester Updated Successfully");
    }
    public int getSemester(){
        return semester;
    }
    public void setDepartment(String department){
        this.department = department;
        System.out.println("Department Updated Successfully");
    }
    public String getDepartment(){
        return department;
    }
    public void setName(String name){
        this.name = name;
        System.out.println("Student Id Updated Successfully");
    }
    public String getName(){
        return name;
    }
    public void setInstitution(String Institution){
        this.Institution = Institution;
        System.out.println("Institution Updated Successfully");
    }
    public String getInstitution(){
        return Institution;
    }
    public void setPassword(String password){
        this.password = password;
        System.out.println("Password Updated Successfully");
    }
    public String getPassword(){
        return password;
    }
    // Helper Funcitons Ended
}
