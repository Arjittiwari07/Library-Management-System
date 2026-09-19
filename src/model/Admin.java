package model;

public class Admin {
    // Attributes Started
    private int adminId;
    private String name;
    private long phone;
    private String email;
    private String username;
    private String password;
    // Attributes Ended
    // Constructor
    public Admin(String name, long phone, String email, String username, String password){
        this.adminId = 0;   // Kept Default for a while only
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public Admin(int adminId, String name, long phone, String email, String username, String password){
        this.adminId = adminId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    // Helper Functions Started
    public void setAdminId(int adminId){
        this.adminId = adminId;
        System.out.println("Admin Id Updated Successfully!");
    }
    public int getAdminId(){
        return adminId;
    }
    public void setName(String name){
        this.name = name;
        System.out.println("Name Updated Successfully!");
    }
    public void setPhoneNo(long phone){
        this.phone = phone;
        System.out.println("Phone No. Updated Successfully!");
    }
    public void setEmail(String email){
        this.email = email;
        System.out.println("Email Updated Successfully!");
    }
    public String getName(){
        return name;
    }
    public long getPhoneNo(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
    public void setUsername(String username){
        this.username = username;
        System.out.println("Username Updated Successfully");
    }
    public void setPassword(String password){
        this.password = password;
        System.out.println("Password Updated Successfully");
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    // Helper Functions Ended
}
