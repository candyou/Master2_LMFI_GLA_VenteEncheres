package dao.util;

import entity.UserAdresse;
import dao.util.util.JsfUtil;
import dao.util.util.PaginationHelper;
import bean.UserAdresseFacade;

import java.io.Serializable;
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

@Named("userAdresseController")
@SessionScoped
public class UserAdresseController implements Serializable {

    private UserAdresse current;
    private DataModel items = null;
    @EJB
    private UserAdresseFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public UserAdresseController() {
    }

    public UserAdresse getSelected() {
        if (current == null) {
            current = new UserAdresse();
            current.setUserAdressePK(new entity.UserAdressePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private UserAdresseFacade getFacade() {
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
        current = (UserAdresse) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new UserAdresse();
        current.setUserAdressePK(new entity.UserAdressePK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getUserAdressePK().setIdUser(current.getUsers().getIdusers());
            current.getUserAdressePK().setIdAdr(current.getAdresses().getIdadresse());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserAdresseCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (UserAdresse) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getUserAdressePK().setIdUser(current.getUsers().getIdusers());
            current.getUserAdressePK().setIdAdr(current.getAdresses().getIdadresse());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserAdresseUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (UserAdresse) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserAdresseDeleted"));
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

    public UserAdresse getUserAdresse(entity.UserAdressePK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = UserAdresse.class)
    public static class UserAdresseControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserAdresseController controller = (UserAdresseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userAdresseController");
            return controller.getUserAdresse(getKey(value));
        }

        entity.UserAdressePK getKey(String value) {
            entity.UserAdressePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.UserAdressePK();
            key.setIdUser(Integer.parseInt(values[0]));
            key.setIdAdr(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(entity.UserAdressePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdUser());
            sb.append(SEPARATOR);
            sb.append(value.getIdAdr());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UserAdresse) {
                UserAdresse o = (UserAdresse) object;
                return getStringKey(o.getUserAdressePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + UserAdresse.class.getName());
            }
        }

    }

}
