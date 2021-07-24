/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.company.employee.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NAZIM
 */
@XmlRootElement(name = "DbConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class DbConfig {

    @XmlElement(name = "url")
    private String url;

    @XmlElement(name = "username")
    private String username;

    @XmlElement(name = "password")
    private String password;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    

}
