package park.pojo;

import java.util.Date;

public class User {
private String userId;
private String username;
private String password;
private String name;
private String sex;
private String telephone;
private String email;
private String balance;
private int state;
private Date createTime;
private String parkId;
public String getUserId() {
	return userId;
}
public void setUserId(String user_id) {
	this.userId = user_id;
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
	return createTime;
}
public void setCreate_time(Date create_time) {
	this.createTime = create_time;
}
public String getPark_id() {
	return parkId;
}
public void setPark_id(String park_id) {
	this.parkId = park_id;
}

}
