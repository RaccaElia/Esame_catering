package catering;

import java.util.LinkedList;

public class Menu implements Comparable<Menu>{
	
	private String nome;
	private String descrizione;
	private double prezzoPersona;
	LinkedList<String> voci;
	LinkedList<Servizio> prenotazioni;
	int richieste;

	public Menu(String nome, String descrizione, double prezzoPersona) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzoPersona = prezzoPersona;
		this.voci = new LinkedList<String>();
		this.prenotazioni  = new LinkedList<Servizio>();
		this.richieste = 0;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public double getPrezzoPerPersona() {
		return prezzoPersona;
	}

	public int compareTo(Menu o) {
		return (int) (o.getPrezzoPerPersona()-this.prezzoPersona);
	}

}
