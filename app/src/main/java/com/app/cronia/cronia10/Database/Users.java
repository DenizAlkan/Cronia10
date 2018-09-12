package com.app.cronia.cronia10.Database;

public class Users {
    
    public static final String TAG = Users.class.getSimpleName();
    public static final String TABLE = "Users";
    
    //Kolonlar
    public static final String KEY_UserID = "User_ID";
    public static final String KEY_UserName = "User_Name";
    public static final String KEY_Mail = "Mail";
    public static final String KEY_Password = "Password";
    public static final String KEY_RegisterDate = "Register_Date";
    public static final String KEY_UserTypeID = "User_Type_ID";
    public static final String KEY_StatusID = "Status_ID";
    
    private String UserID;
    private String UserName;
    private String Mail;
    private String Password;
    private String RegisterDate;
    private String UserTypeID;
    private String StatusID;


    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }

    public String getUserTypeID() {
        return UserTypeID;
    }

    public void setUserTypeID(String userTypeID) {
        UserTypeID = userTypeID;
    }

    public String getStatusID() {
        return StatusID;
    }

    public void setStatusID(String statusID) {
        StatusID = statusID;
    }
}
