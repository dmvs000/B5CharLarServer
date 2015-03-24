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
public class Presence {
 
	String show,status,from;
	int id;
 
	public String getShow() {
		return show;
	}
        public String getStatus() {
		return status;
	}
        public String getFrom() {
		return from;
	}
 
	@XmlElement
	public void setShow(String show) {
		this.show = show;
	}
        @XmlElement
	public void setStatus(String status) {
		this.status = status;
	}
        @XmlAttribute
	public void setFrom(String from) {
		this.from = from;
	}
 
 
}
