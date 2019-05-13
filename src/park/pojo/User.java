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
private double balance;
private int state=0;
private Date createTime;
private String clientId;
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
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
public int getState() {
	return state;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
public void setState(int state) {
	this.state = state;
}
public Date getCreateTime() {
//	SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
//    String date=temp.format(createTime);  
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
@Override
public String toString() {
	return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", name=" + name + ", sex="
			+ sex + ", telephone=" + telephone + ", email=" + email + ", balance=" + balance + ", state=" + state
			+ ", createTime=" + createTime + "]";
}
public String getClientId() {
	return clientId;
}
public void setClientId(String clientId) {
	this.clientId = clientId;
}
}
