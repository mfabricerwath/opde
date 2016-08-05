package controleur;



import javax.annotation.PostConstruct;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import pojo.Famillep;
import controleur.Message.TYPEMESSAGE;
import dao.Dao;

@ManagedBean
@ViewScoped
public class Famille extends Famillep{
	private static final long serialVersionUID = 1L;
	private Famillep selected;
	
	 public Famille(){
			
	 }
	@PostConstruct
	public void lister(){
		this.setListe(Dao.getInstance().getAllFamille());
	}
	
	public void enregistrer(){
	
		if(Dao.getInstance().insertUpdateFamille(this)){
			Message.getInstance().showMessage(TYPEMESSAGE.SAVE_SUCCESS);
		}else
			Message.getInstance().showMessage(TYPEMESSAGE.SAVE_ERROR);
		this.init();
	}
  public void supprimer(){
	  if(Dao.getInstance().delete("famille",this.getId())){
		  Message.getInstance().showMessage(TYPEMESSAGE.DELETE_SUCCESS);
	  }else
		  Message.getInstance().showMessage(TYPEMESSAGE.DELETE_ERROR);
	  this.init();
  }
	public void onRowSelect(SelectEvent e){
		selected=new Famille();
		selected=(Famillep)e.getObject();
		if(this.selected!=null){
			this.setId(selected.getId());
			this.setNom(selected.getNom()); 
		}
	}
	public void init(){
		this.lister();
		   this.setId(0);
		   this.setNom("");
		  
	}

	public Famillep getSelected() {
		return selected;
	}

	public void setSelected(Famillep selected) {
		this.selected = selected;
	}

}
