import java.util.Vector;
import javax.swing.*;

public class AddModel {
	boolean flag;
	EmployerModel em_model;
	int length;
	public AddModel()
	{
		flag = false;
		em_model = new EmployerModel();
	}
	public boolean add(String texts[])
	{	
		boolean flag = em_model.insert(texts);
		return flag;
	}
}
