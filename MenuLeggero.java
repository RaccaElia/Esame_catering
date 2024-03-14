package catering;

public class MenuLeggero extends Menu {

	private int calorie;
	
	public MenuLeggero(String nome, String descrizione, double prezzoPersona, int calorie) {
		super(nome, descrizione, prezzoPersona);
		this.calorie = calorie;
	}

	public int getCalorie() {
		return calorie;
	} 
	
}
