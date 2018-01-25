package dao.util;

import entity.*;
import dao.util.util.JsfUtil;
import dao.util.util.PaginationHelper;
import bean.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
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

@Named("articlesController")
@SessionScoped
public class ArticlesController implements Serializable {

    private Articles current;
    private DataModel items = null;
    @EJB
    private ArticlesFacade ejbFacade;
    @EJB 
    private CategorieFacade catFacade;
    @EJB 
    private UsersFacade userFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<String> checkedcategories;
    private List<Categorie> listcategories;
    public List<String> getCheckedcategories() {
        return checkedcategories;
    }

    public void setCheckedcategories(List<String> checkedcategories) {
        this.checkedcategories = checkedcategories;
    }

    public CategorieFacade getCatFacade() {
        return catFacade;
    }

    public void setCatFacade(CategorieFacade catFacade) {
        this.catFacade = catFacade;
    }
    
    
    
    
    public ArticlesController() {
    }

    public Articles getSelected() {
        if (current == null) {
            current = new Articles();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ArticlesFacade getFacade() {
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
        current = (Articles) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Articles();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            // Authentification doit etre faites ici . 
            UserArticle ua = new UserArticle();
           
           List<UserArticle> listuserarticles = new Vector<>();
          Users u = userFacade.find(1);// recuperer id de l'utilisateur connecter
           UserArticlePK upk=new UserArticlePK(u.getIdusers(), current.getIdarticle());
        
           ua.setUserArticlePK(upk);
           
           ua.setArticles(current);
           ua.setUsers(u);
          
          //  uaFacade.create(ua);
           ua.setDateCreate(new Date());
            listuserarticles.add(ua);
            listcategories=new Vector<>();
            if(checkedcategories.size()==0)checkedcategories.add("Autres");
            for(String cat : checkedcategories){
                listcategories.add(catFacade.categoriesbyname(cat));
            }
            
            System.out.println("dao.util.ArticlesController.create()"+listcategories.get(0).getNomCat());
              current.setCategorieList(listcategories);
        //  current.setUserArticleList(listuserarticles);
  
            getFacade().create(current);
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArticlesCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Articles) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
             listcategories=new Vector<>();
             ZonedDateTime zdt = ZonedDateTime.now().plusHours(1);

             java.util.Date date = java.util.Date.from( zdt.toInstant() );

            if(current.getDateLimite().before(date)){
                System.out.println("dao.util.ArticlesController.update()" + current.getDateLimite() + date);
                  for(String cat : checkedcategories){
                listcategories.add(catFacade.categoriesbyname(cat));
            }
                current.setCategorieList(listcategories);   
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArticlesUpdated"));
           }
            else JsfUtil.addErrorMessage("Vous ne pouvez pas modifier avant la date limite");
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Articles) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArticlesDeleted"));
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

    public Articles getArticles(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Articles.class)
    public static class ArticlesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArticlesController controller = (ArticlesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "articlesController");
            return controller.getArticles(getKey(value));
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
            if (object instanceof Articles) {
                Articles o = (Articles) object;
                return getStringKey(o.getIdarticle());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Articles.class.getName());
            }
        }

    }

}
