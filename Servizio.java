package catering;

public class Servizio implements Comparable<Servizio>{
	private String codice;
	private Cliente cliente;
	private Menu menu;
	private String data;
	private String ora;
	private int numeroPersone;
	private double incasso;
	
	public Servizio(String codice, Cliente cliente, Menu menu, String data, String ora,int numeroPersone) {
		this.codice = codice;
		this.cliente = cliente;
		this.menu = menu;
		this.data = data;
		this.ora = ora;
		this.numeroPersone = numeroPersone;
		this.incasso = numeroPersone*menu.getPrezzoPerPersona();
	}
	
	public String getCodice() {
		return codice;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public Menu getMenu() {
		return menu;
	}
	public String getData() {
		return data;
	}
	public String getOra() {
		return ora;
	}
	public int getNumeroPersone() {
		return numeroPersone;
	}
	public double getIncasso() {
		return incasso;
	}

	public int compareTo(Servizio o) {
		if(this.incasso == o.getIncasso())	{
			return this.codice.compareTo(o.getCodice());
		}
		return (int) (this.incasso-o.getIncasso());
	}
	
	
}
