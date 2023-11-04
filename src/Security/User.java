package Security;

public class User {
    private String userName;
    private String passWord;
    private String role;
    private int idUser; 
    
    public User(){

    }

    public User(String userName, String passWord, String role, int idUser) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.idUser = idUser;
    }

    
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return this.role;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdUser() {
        return this.idUser;
    }
    
    public boolean isAdmin(){
        return this.role == "manager";
    }
}
