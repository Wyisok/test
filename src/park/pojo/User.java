package park.pojo;

import java.util.Date;

public class User {
private String user_id;
private String username;
private String password;
private String name;
private String sex;
private String telephone;
private String email;
private String balance;
private int state;
private Date create_time;
private String park_id;
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getBalance() {
	return balance;
}
public void setBalance(String balance) {
	this.balance = balance;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public Date getCreate_time() {
	return create_time;
}
public void setCreate_time(Date create_time) {
	this.create_time = create_time;
}
public String getPark_id() {
	return park_id;
}
public void setPark_id(String park_id) {
	this.park_id = park_id;
}

}
