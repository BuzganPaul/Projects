package DataSourceLevel;

public class Operation {
	private int idOperation;
	private String dataOperation;
	private int idEmployee;
	private String operationdetail;
	private int idAccount;
	
	
	public int getIdOperation() {
		return idOperation;
	}
	public void setIdOperation(int idOperation) {
		this.idOperation = idOperation;
	}
	public String getDataOperation() {
		return dataOperation;
	}
	public void setDataOperation(String dataOperation) {
		this.dataOperation = dataOperation;
	}
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	public String getOperationdetail() {
		return operationdetail;
	}
	public void setOperationdetail(String operationdetail) {
		this.operationdetail = operationdetail;
	}
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	
	public Operation(int idOperation, String dataOperation, int idEmployee, String operationdetail, int idAccount) {
		super();
		this.idOperation = idOperation;
		this.dataOperation = dataOperation;
		this.idEmployee = idEmployee;
		this.operationdetail = operationdetail;
		this.idAccount = idAccount;
	}
	@Override
	public String toString() {
		return "Operation [idOperation=" + idOperation + ", dataOperation=" + dataOperation + ", idEmployee="
				+ idEmployee + ", operationdetail=" + operationdetail + ", idAccount=" + idAccount + "]";
	}
	
	

}
