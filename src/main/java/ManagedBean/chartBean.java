package ManagedBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

import dao.ServerDaoLocal;
import entities.Position;
import entities.SmartPhone;
import entities.User;
import service.ServiceDao;
import org.primefaces.model.chart.PieChartModel;


@ManagedBean(name="chartbean")
@RequestScoped
public class chartBean {
	public SmartPhone smartphone=new SmartPhone();
	public Date date1;
	public Date date2;
    private static PieChartModel pieModel;
    


	@EJB
	private ServerDaoLocal local;

	
	public List<User> findallUsers() {
		return local.findAllUsers();
	}
	

	
	public SmartPhone getSmartphone() {
		return smartphone;
	}



	public void setSmartphone(SmartPhone smartphone) {
		this.smartphone = smartphone;
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

	
	public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries positions = new ChartSeries();
        positions.setLabel("Smartphones");
        model.setAnimate(true);
        for (Object[] m : local.nbpositions()) {
        	positions.set(m[1].toString(), Integer.parseInt(m[0].toString()));
        }
        model.addSeries(positions);
        
                
        return model;
    }
	
	
	public ChartModel initBarModelBymois() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries positions = new ChartSeries();
        positions.setLabel("Smartphones");
        model.setAnimate(true);
        for (Object[] m : local.nbpositionsBymois()) {
        	positions.set(m[1].toString(), Integer.parseInt(m[0].toString()));
        }
        model.addSeries(positions);
        
                
        return model;
    }
	
    public ChartModel initPieModel(){
        PieChartModel pieModel = new PieChartModel();
        for (Object[] m : local.nbpositions()) {
            pieModel.set(m[1].toString(), Integer.parseInt(m[0].toString()));
        }
        pieModel.setTitle("Position par Smartphone");
        pieModel.setLegendPosition("w");
        pieModel.setShadow(false);

       return pieModel;
    }
    
	public List<SmartPhone> findallSmartphones() {
		return local.findAllSmartPhones();
	}
	
	
	
	
	
}
