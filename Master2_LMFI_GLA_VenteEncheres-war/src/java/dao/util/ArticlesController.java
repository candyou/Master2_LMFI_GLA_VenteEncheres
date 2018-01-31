package dao.util;

import entity.*;
import dao.util.util.JsfUtil;
import dao.util.util.PaginationHelper;
import bean.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.nio.file.Files;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import javax.servlet.http.Part;

@Named("articlesController")
@SessionScoped
public class ArticlesController implements Serializable {

    private Articles current;
    private List<UserAdresse> adresseList;
    private Adresses adresse;
    private DataModel items = null;
    private List<ParticipeEnch> listparticip=null;
    @EJB
    private ArticlesFacade ejbFacade;
    @EJB 
    private CategorieFacade catFacade;
    @EJB 
    private UsersFacade userFacade;
    @EJB
    private ParticipeEnchFacade partienchFacade;
    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private UserArticleFacade usAdFacade;
    private FacturationFacade facturationFacade;
    private Facturation facturation;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<String> checkedcategories;
    private List<Categorie> listcategories;
    private Double prixprop;
    private Part img;
    private Categorie catRech;
    private String nomArtRech;
    private List<Articles> SearchedArt;
     private List<Articles> RandomArt;

    public Double getPrixprop() {
        return prixprop;
    }

    public Adresses getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresses adresse) {
        this.adresse = adresse;
    }

    public FacturationFacade getFacturationFacade() {
        return facturationFacade;
    }

    public void setFacturationFacade(FacturationFacade facturationFacade) {
        this.facturationFacade = facturationFacade;
    }

    public Facturation getFacturation() {
        return facturation;
    }

    public void setFacturation(Facturation facturation) {
        this.facturation = facturation;
    }

    
    public void setPrixprop(Double prixprop) {
        this.prixprop = prixprop;
    }

    
    public UsersFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UsersFacade userFacade) {
        this.userFacade = userFacade;
    }

    public List<UserAdresse> getAdresseList() {
        return adresseList;
    }

    public void setAdresseList(List<UserAdresse> adresseList) {
        this.adresseList = adresseList;
    }
    
    
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

    public ArticlesFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ArticlesFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public ParticipeEnchFacade getPartienchFacade() {
        return partienchFacade;
    }

    public void setPartienchFacade(ParticipeEnchFacade partienchFacade) {
        this.partienchFacade = partienchFacade;
    }
    
    public List<Articles> getAllArt(){
        return ejbFacade.getValideArti((int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser"));
    }
    
    public String commandeList(){
        
        listparticip=partienchFacade.getMaxPart((int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser"));
        System.out.println(listparticip.size());
        return "Commande";
    }
    public String confirmerachat(){
         /* System.out.println("dao.util.ArticlesController.confirmerachat()"+ facturation.getNumCb());
        
        facturation.setIdUser(userFacade.find(1));
        facturationFacade.create(facturation);*/
        Commande c = new Commande();
        c.setIdAdresse(adresse);
        c.setIdFacturation(facturation);
        List<ParticipeEnch> listP = partienchFacade.getPanier((int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser"));
        for (ParticipeEnch participeEnch : listP) {
            participeEnch.setEtatAchat(Short.parseShort("2"));
            partienchFacade.edit(participeEnch);
        }
        c.setParticipeEnchList(listP);
        commandeFacade.create(c);
        return "Commande";
    }
    public void ajoutpanier(int id) {
        ParticipeEnch p ;
        p = partienchFacade.find(id);
     /*   List<Commande> commandeList = new Vector<>();
        Commande a = new Commande();
         commandeList.add(a);
        p.setCommandeList(commandeList);*/
        p.setEtatAchat(Short.parseShort("1"));
        partienchFacade.edit(p);
        try{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Master2_LMFI_GLA_VenteEncheres-war/articles/Panier.xhtml");
        }catch(Exception e){}
    }
    
    public void retirerPanier(int id){
        ParticipeEnch p = partienchFacade.find(id);
        p.setEtatAchat(Short.parseShort("0"));
        partienchFacade.edit(p);
         try{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Master2_LMFI_GLA_VenteEncheres-war/articles/Panier.xhtml");
        }catch(Exception e){}
    }

    public List<ParticipeEnch> getListparticip() {
        return listparticip;
    }

    public void setListparticip(List<ParticipeEnch> listparticip) {
        this.listparticip = listparticip;
    }

    public Part getImg() {
        return img;
    }

    public void setImg(Part img) {
        this.img = img;
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
         items=new ListDataModel(ejbFacade.findAll());
         System.out.println("dao.util.ArticlesController.prepareList()");
        return "List";
    }
    public String prepareListEnchere(){
         
         System.out.println("dao.util.ArticlesController.prepareListEnchere()");
         recreateModel();
        
         items=new ListDataModel(ejbFacade.contraintelimite());
         
          if(ejbFacade.contraintelimite().size()>0)
        return "Search";
          else return null;
     }
    public String prepareView() {
        current = (Articles) getItems().getRowData();
       
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        
        return "View";
    }

    
    public String prepareViewId(int id)  {
        current = (Articles) ejbFacade.find(id);
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Master2_LMFI_GLA_VenteEncheres-war/articles/View.xhtml");
        } catch (Exception e) {
        }
        return "";
    }
     public String prepareParticiper() {
        current = (Articles) getItems().getRowData();
       
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
         System.out.println("dao.util.ArticlesController.prepareParticiper()");
        return "Participer";
    }

    
    
    public String prepareCreate() {
        current = new Articles();
        selectedItemIndex = -1;
        return "Create";
    }
    public String participe(){
        ParticipeEnch p = new ParticipeEnch(1, prixprop, Short.parseShort("1"), Short.parseShort("0"), current, userFacade.find(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser")));
        getPartienchFacade().create(p);
          recreateModel();
          items=new ListDataModel(ejbFacade.contraintelimite());
         System.out.println("dao.util.ArticlesController.participe()");
          if(ejbFacade.contraintelimite().size()>0)
        return "Search";
          else return null;
    }

    public String create() {
        try {
            // Authentification doit etre faites ici . 
            UserArticle ua = new UserArticle();      
            Users u = userFacade.find(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser"));// recuperer id de l'utilisateur connecter   
            System.out.println("dao.util.ArticlesController.create()"+ u.toString());
            ua.setDateCreate(new Date());
            listcategories=new Vector<>();
            if(checkedcategories.size()==0)checkedcategories.add("Autres");
            for(String cat : checkedcategories){
                listcategories.add(catFacade.categoriesbyname(cat));
            }
            
            //System.out.println("dao.util.ArticlesController.create()"+listcategories.get(0).getNomCat());
            current.setCategorieList(listcategories);
        //  current.setUserArticleList(listuserarticles);
            getFacade().create(current);
            UserArticlePK upk = new UserArticlePK(u.getIdusers(), current.getIdarticle());
            ua.setUserArticlePK(upk);
            usAdFacade.create(ua);
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
    public String searchByName(){
        System.out.println(current.getNomArticle()+ "here");
        List<Articles> listArticles = ejbFacade.seachByName(current.getNomArticle());
        System.out.println("dao.util.ArticlesController.searchByName()");
        items = new ListDataModel(listArticles);
        return "Search";
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
    
    public void saveImage(){
        try (InputStream input = img.getInputStream()) {
            Files.copy(input, new File("/resources/img", img.getSubmittedFileName()).toPath());
    }
    catch (IOException e) {
        // Show faces message?
    }
    }
    
    public void modifierArticle(int idArt){
        current = ejbFacade.find(idArt);
        try{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Master2_LMFI_GLA_VenteEncheres-war/articles/Edit.xhtml");
        }catch(Exception e){}
    }
    
    public List<Categorie> getAllCat(){
        return catFacade.findAll();
    }

    public Categorie getCatRech() {
        return catRech;
    }

    public void setCatRech(Categorie catRech) {
        this.catRech = catRech;
    }

    public String getNomArtRech() {
        return nomArtRech;
    }

    public void setNomArtRech(String nomArtRech) {
        this.nomArtRech = nomArtRech;
    }
    
    public void rechNomCat(){
        System.out.println("dao.util.ArticlesController.rechNomCat()" + nomArtRech +" " +catRech);
        SearchedArt = ejbFacade.getByCatName(nomArtRech, catRech.getIdcategorie(),(int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser"));
        try{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Master2_LMFI_GLA_VenteEncheres-war/articles/Search.xhtml");
        }catch(Exception e){}
    }

    public List<Articles> getSearchedArt() {
        return SearchedArt;
    }

    public void setSearchedArt(List<Articles> SearchedArt) {
        this.SearchedArt = SearchedArt;
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
    
    public List<ParticipeEnch> getParti(int idArticle){
            return partienchFacade.getPartByArti(idArticle);
        }
    public double getPrixMax(int idArticle){
        return partienchFacade.getMaxProp(idArticle);
    }

    public List<UserAdresse> getUserAdresse(){
        return userFacade.find(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser")).getUserAdresseList();
    }
    
    public List<Facturation> getFactUser(){
        return userFacade.find(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("iduser")).getFacturationList();
    }
    
    public List<Articles> random(){
        Vector<Articles> rdm = new Vector<Articles>();
        List<Integer> nums = new Vector<Integer>();
        List<Articles> tmp = new Vector<>();
            tmp= getAllArt();
           int rnum;
              
          Random randomGenerator = new Random();
           for (int idx = 1; idx <= 3; ++idx){
                 
               int randomInt = randomGenerator.nextInt(tmp.size()-1);
               if(!nums.contains(randomInt)){ nums.add(randomInt);
               idx--;
               }
               }
           
           for(int j=0;j<3;j++ ){
               
               rdm.add(tmp.get(nums.get(j)));
           }
           
           return rdm;
        
    }

    public List<Articles> getRandomArt() {
        /*
        System.out.println("dao.util.ArticlesController.getRandomArt()" + ejbFacade.listRandoArt);
        return ejbFacade.listRandoArt;
       */
        return random();
    }

    public void setRandomArt(List<Articles> RandomArt) {
        this.RandomArt = RandomArt;
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
