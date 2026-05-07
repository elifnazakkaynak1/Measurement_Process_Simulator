public class User {
    private String username;
    private String school;
    private String sessionName;

    public User(String username, String school, String sessionName) {
        this.username = username;
        this.school = school;
        this.sessionName = sessionName;
    }

    public String getUsername() {
        return username;
    }
    public String getSchool() {
        return school;
    }
    public String getSessionName() {
        return sessionName;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setSchool(String school){
        this.school = school;
    }
    public void setSessionName(String sessionName){
        this.sessionName = sessionName;
    }


}
