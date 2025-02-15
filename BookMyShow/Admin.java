public class Admin {
    private String adminName;
    private String adminPassWord;

    public Admin(String adminName,String adminPassWord){
        this.adminName=adminName;
        this.adminPassWord=adminPassWord;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPassWord() {
        return adminPassWord;
    }
}
