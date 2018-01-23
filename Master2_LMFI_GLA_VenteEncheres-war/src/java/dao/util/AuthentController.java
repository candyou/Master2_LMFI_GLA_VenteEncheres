/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.util;

import bean.UsersFacade;
import entity.Users;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
    
    public String authenti(){
        user = userBean.findByUsernameAndPass(login, password);
        System.out.println("dao.util.AuthentController.authenti()"+user);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", user.getIdusers());
        return "index?faces-redirect=true";
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
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
    
    
    
}
