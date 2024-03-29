package dao.util;

import bean.ArticlesFacade;
import entity.ParticipeEnch;
import dao.util.util.JsfUtil;
import dao.util.util.PaginationHelper;
import bean.ParticipeEnchFacade;
import bean.UserAdresseFacade;
import bean.UsersFacade;
import entity.Articles;
import entity.UserAdresse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("participeEnchController")
@SessionScoped
public class ParticipeEnchController implements Serializable {

    private ParticipeEnch current;
    private Articles selectedArticle;
    private DataModel items = null;
    @EJB
    private ParticipeEnchFacade ejbFacade;
    @EJB
    private ArticlesFacade artFacade;
    @EJB 
    private UsersFacade usFacade;
    @EJB
    private UserAdresseFacade usAdFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ParticipeEnchController() {
    }

    public ParticipeEnch getSelected() {
        if (current == null) {
            current = new ParticipeEnch();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ParticipeEnchFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (ParticipeEnch) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ParticipeEnch();
        selectedItemIndex = -1;
        return "MesEnchere";
    }

    public String create() {
        try {
            current.setIdUser(usFacade.find(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser")));
            current.setIdArticle(artFacade.find(selectedArticle.getIdarticle()));
            current.setEtatAchat(Short.parseShort("0"));
            current.setEtatParticip(Short.parseShort("0"));
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ParticipeEnchCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ParticipeEnch) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ParticipeEnchUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (ParticipeEnch) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }
    
    public void destroyPart(int idPart){
        
    }
    
    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ParticipeEnchDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public ParticipeEnch getParticipeEnch(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    public Articles getSelectedArticle(){
        return selectedArticle;
    }
    public String prepareViewArt(int idArticle)  {
        try {
            selectedArticle = artFacade.find(idArticle);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Master2_LMFI_GLA_VenteEncheres-war/participeEnch/Participer.xhtml");
        } catch (Exception e) {
        }
        return "";
    }
    
    public List<ParticipeEnch> getMyEnchere(){
        List<ParticipeEnch> list = usFacade.find(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser")).getParticipeEnchList();
        return list;
    }
    
    public String getEtatEnch(int id){
        ParticipeEnch part = ejbFacade.find(id);
        if (part.getIdArticle().getDateLimite().after(new Date())){
            return "En cours";
        }
        else 
            return "Terminé";
    }
    
    public String getEtatAchat(int id){
        ParticipeEnch part = ejbFacade.find(id);
        if (getEtatEnch(id) == "Terminé" && ejbFacade.find(id).getEtatParticip() == Short.parseShort("1")){
            return "Gagnée !";
        }
        else if (getEtatEnch(id) == "En cours") return "En cours";
        return "Pas de chance";
    }
    
    public boolean getEtatButton(int id){
        ParticipeEnch part = ejbFacade.find(id);
        if (getEtatAchat(id) == "Gagnée !" && part.getEtatAchat()== Short.parseShort("0"))
            return true;
        return false;
    }
    
    public boolean getEtatVoirPanier(int id){
        ParticipeEnch part = ejbFacade.find(id);
        if (getEtatEnch(id) == "En cours" || part.getEtatAchat()== Short.parseShort("0"))
            return true;
        return false;
    }
    
    public void redirectPanier(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Master2_LMFI_GLA_VenteEncheres-war/articles/Panier.xhtml");
        } catch (Exception e) {
        }
    }
    
    public List<ParticipeEnch> panierList(){
        return ejbFacade.getPanier((int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser"));
    }
    
    public List<UserAdresse> getUserAdresses(){
        return usAdFacade.getUserAdresseById((int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser"));
    }

    @FacesConverter(forClass = ParticipeEnch.class)
    public static class ParticipeEnchControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ParticipeEnchController controller = (ParticipeEnchController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "participeEnchController");
            return controller.getParticipeEnch(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ParticipeEnch) {
                ParticipeEnch o = (ParticipeEnch) object;
                return getStringKey(o.getIdpartEnch());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ParticipeEnch.class.getName());
            }
        }

    }

}
