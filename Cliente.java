package catering;

import java.util.LinkedList;

public class Cliente implements Comparable<Cliente>{
	
	private String codice;
	private String ragioneSociale;
	private String indirizzo;
	private String contatto;
	LinkedList<Servizio> prenotazioni;

	public Cliente(String codice, String ragioneSociale, String indirizzo, String contatto) {
		this.codice = codice;
		this.ragioneSociale = ragioneSociale;
		this.indirizzo = indirizzo;
		this.contatto = contatto;
		this.prenotazioni  = new LinkedList<Servizio>();
	}

	public String getCodice() {
		return codice;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getContatto() {
		return contatto;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setContatto(String contatto) {
		this.contatto = contatto;
	}

	public int compareTo(Cliente o) {
		return this.ragioneSociale.compareTo(o.getRagioneSociale());
	}
	
}
