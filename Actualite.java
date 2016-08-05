package controleur;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import pojo.Actualitep;
import pojo.Agentp;
import controleur.Message.TYPEMESSAGE;
import dao.Dao;

@ManagedBean
@ViewScoped
public class Actualite extends Actualitep {
	private static final long serialVersionUID = 1L;
	private Actualitep selected;
	
	 public Actualite(){
			
	 }
	@PostConstruct
	public void lister(){
		this.setListe(Dao.getInstance().getAllActualiteAll());
		this.setL(Dao.getInstance().getAllActualite());
	}
	
	public void enregistrer(){
	    this.setAgent(Authentification.GetProperties());
		if(Dao.getInstance().insertUpdateActualite(this)){
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
		selected=new Actualitep();
		selected=(Actualitep)e.getObject();
		if(this.selected!=null){
			this.setId(selected.getId());
			this.setMessage(selected.getMessage()); 
			this.setAgent(selected.getAgent());
			this.setEtat(selected.getEtat());
			this.setPublie(selected.isPublie());
			this.setEntete(selected.getEntete());
			this.setDeadline(selected.getDeadline());
		}
	}
	public void init(){
		this.lister();
		    this.setId(0);
			this.setMessage(""); 
			this.setAgent(new Agentp());
			this.setEtat("");
			this.setPublie(true);
			this.setEntete("");
			this.setDeadline(null);
		  
	}
	public Actualitep getSelected() {
		return selected;
	}
	public void setSelected(Actualitep selected) {
		this.selected = selected;
	}

	

}
