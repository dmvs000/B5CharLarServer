/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greetingserver;


/**
 *
 * @author SHANKAR
 */
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
public class UserRegister {
 
	String fullname,lastname,DOB,email,password;
 
	public String getFullName() {
		return fullname;
	}
        public String getLastName() {
		return lastname;
	}
        public String getDOB() {
		return DOB;
	}
        public String getEmail() {
		return email;
	}
        public String getPassword() {
		return password;
	}
 
	@XmlElement
	public void setFullName(String fullname) {
		this.fullname = fullname;
	}
        @XmlElement
	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
        @XmlElement
	public void setDOB(String dob) {
		this.DOB = dob;
	}
        @XmlElement
	public void setEmail(String email) {
		this.email = email;
	}
        @XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
 
 
}
