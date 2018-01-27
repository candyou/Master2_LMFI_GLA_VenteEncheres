/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.util;

import bean.UsersFacade;
import entity.Users;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Mohamed
 */
@Named(value = "authentController")
@RequestScoped
public class AuthentController {
    
    @EJB
    private UsersFacade userBean;
    private String login;
    private String password;
    private Users user;
    private String connecteduser;
    public String authenti(){
        user = userBean.findByUsernameAndPass(login, password);
        if (user != null){
        System.out.println("dao.util.AuthentController.authenti()"+user);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("iduser", user.getIdusers());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", user.getLogin());
        connecteduser = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString();
        System.out.println("-----------------" + connecteduser);
        }
        return "index?faces-redirect=true";
    }
    
    
    public void logout()throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.invalidateSession();
    ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
    }
    
    public String connecteduser(){
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString() == null)
            return "";
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username").toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnecteduser() {
        return connecteduser;
    }

    public void setConnecteduser(String connecteduser) {
        this.connecteduser = connecteduser;
    }
    
    
    
}
