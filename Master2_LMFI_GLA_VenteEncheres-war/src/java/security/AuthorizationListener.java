/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohamed
 */
public class AuthorizationListener implements PhaseListener {
 
public void afterPhase(PhaseEvent event) {
 
    FacesContext facesContext = event.getFacesContext();
    String currentPage = facesContext.getViewRoot().getViewId();
 
    boolean isRegisterPage = (currentPage.lastIndexOf("users/Create.xhtml") > -1);
    boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1);
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
 
    if(session==null){
    NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
    nh.handleNavigation(facesContext, null, "login");
    }
    else if (isRegisterPage) {}
    else{
    Object currentUser = session.getAttribute("username");
 
    if (!isLoginPage && (currentUser == null || currentUser == "")) {
        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
    nh.handleNavigation(facesContext, null, "login");
    }
    }
    }

    public void beforePhase(PhaseEvent event) {

    }

    public PhaseId getPhaseId() {
    return PhaseId.RESTORE_VIEW;
    }
}