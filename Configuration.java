package controleur;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import controleur.Message.TYPEMESSAGE;
import dao.Dao;
import pojo.Configurationp;
@ManagedBean
@ViewScoped
public class Configuration extends Configurationp{

	private static final long serialVersionUID = 1L;
	private Configurationp selected;
	
	@PostConstruct
	public void init(){
		this.setListe(Dao.getInstance().getAllConfiguration());
	}
	public void save(){
		if(!this.getPrefixe().equalsIgnoreCase("")||!this.getPour().equalsIgnoreCase("")){
			if(Dao.getInstance().insertUpdateConfiguration(this)){
				Message.getInstance().showMessage(TYPEMESSAGE.SAVE_SUCCESS);
				reset();
			}
			else
				Message.getInstance().showMessage(TYPEMESSAGE.SAVE_ERROR);
		}
	}
	public void supprimer(){
			
		}
	public void reset(){
		this.setId(0);
		this.setPrefixe("");
		this.setPour("");
		this.setParDefaut(false);
		init();
	}
	
	
	public void onRowSelect(SelectEvent e){
		selected=(Configurationp)e.getObject();
		if(selected!=null){
			this.setId(selected.getId());
			this.setPrefixe(selected.getPrefixe());
			this.setPour(selected.getPour());
			this.setParDefaut(selected.isParDefaut());
		}
	}
	
public Configurationp getSelected() {
		return selected;
	}
	public void setSelected(Configurationp selected) {
		this.selected = selected;
	}
}
