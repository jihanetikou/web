package ManagedBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import dao.ServerDaoLocal;
import entities.Position;
import entities.SmartPhone;
import entities.User;


@ManagedBean(name="positionbean")
@RequestScoped
public class positionBean {
	public Date date1;
	public Date date2;
	public List<Position> list;
	public static final MapModel simpleModel = new DefaultMapModel();
    private Marker marker;
	@EJB
	private ServerDaoLocal local;

	
	public List<User> findallUsers() {
		return local.findAllUsers();
	}
	public void load() {
		findPositionByDate();
	}

	
	public void findPositionByDate() {
		
		this.list = local.findPositionByDate(this.date1,this.date2);
		for (Position h : this.list) {
            LatLng coord1 = new LatLng(h.getLatitude(), h.getLongitude());
            this.simpleModel.addOverlay(new Marker(coord1, h.getSmartphone().getImei()));
        }
		

	}
	
	 public void onMarkerSelect(OverlaySelectEvent event) {
	        marker = (Marker) event.getOverlay();
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Imei : ", marker.getTitle()));
	 }
	
	public Marker getMarker() {
		return marker;
	}
	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	public static MapModel getSimplemodel() {
		return simpleModel;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public List<Position> getList() {
		return list;
	}
	public void setList(List<Position> list) {
		this.list = list;
	}
	
	

}