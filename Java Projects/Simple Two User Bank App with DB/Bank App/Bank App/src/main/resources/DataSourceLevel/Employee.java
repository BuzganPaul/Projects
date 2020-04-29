package DataSourceLevel;

public class Employee {
	private int idEmployee;
	private String nameEmployee;
	private String position;
	
	
	
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	public String getNameEmployee() {
		return nameEmployee;
	}
	public void setNameEmployee(String nameEmployee) {
		this.nameEmployee = nameEmployee;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
	public Employee(int idEmployee, String nameEmployee, String position) {
		super();
		this.idEmployee = idEmployee;
		this.nameEmployee = nameEmployee;
		this.position = position;
	}
	@Override
	public String toString() {
		return "Employee [idEmployee=" + idEmployee + ", nameEmployee=" + nameEmployee + ", position=" + position + "]";
	}
	
	
	

}
