package redis;

public interface SettlementService {

	public void pay();

	public void refund();
 
	public void setSettlementFail();

	public void secondSettlement();

	public void testTask();
}
