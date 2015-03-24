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
public class UserAuth {
 
	String username,password,to,from,id;
 
	public String getUsername() {
		return username;
	}
        public String getPassword() {
		return password;
	}
 
	@XmlElement
	public void setUsername(String username) {
		this.username = username;
	}
        @XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
 
	
 
	
 
	public String getTo() {
		return to;
	}
        public String getFrom() {
		return from;
	}
        public String getId() {
		return id;
	}
 
	@XmlAttribute
	public void setTo(String to) {
		this.to = to;
	}
        @XmlAttribute
	public void setFrom(String from) {
		this.from = from;
	}
        @XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
 
}
