package catering;

import java.util.*;

public class Azienda {
	
	LinkedList<Cliente> clienti = new LinkedList<Cliente>();
	private int numClienti = 1;
	LinkedList<Menu> menus = new LinkedList<Menu>();
	LinkedList<Servizio> prenotazioni = new LinkedList<Servizio>();

	public Cliente registraCliente(String ragioneSociale, String indirizzo, String contatto) {
		for(Cliente c: clienti)	{
			if(c.getRagioneSociale().equals(ragioneSociale))	{
				c.setContatto(contatto);
				c.setIndirizzo(indirizzo);
				return c;
			}
		}
		String codice = "";
		if(numClienti<10)	{			
			codice = "C00"+numClienti;
			numClienti++;
		}
		else if (numClienti<100) {
			codice = "C0"+numClienti;
			numClienti++;
		}
		else if (numClienti<1000) {
			codice = "C"+numClienti;
			numClienti++;
		}
		else {
			return null;
		}
		Cliente ris = new Cliente(codice, ragioneSociale, indirizzo, contatto);
		clienti.add(ris);
		return ris;
	}
	
	public Cliente cercaClientePerRagioneSociale(String ragioneSociale) {
		for(Cliente c: clienti)	{
			if(c.getRagioneSociale().equals(ragioneSociale))	{
				return c;
			}
		}
		return null;
	}

	public Cliente cercaClientePerCodice(String codiceCliente) {
		for(Cliente c: clienti)	{
			if(c.getCodice().equals(codiceCliente))	{
				return c;
			}
		}
		return null;
	}

	public Collection<Cliente> cercaClienti(String daCercare) {
		LinkedList<Cliente> listaTemp = new LinkedList<Cliente>();
		for(Cliente c: clienti)	{
			if(c.getRagioneSociale().toLowerCase().contains(daCercare.toLowerCase()) || c.getIndirizzo().toLowerCase().contains(daCercare.toLowerCase()) || c.getContatto().toLowerCase().contains(daCercare.toLowerCase()))	{
				listaTemp.add(c);
			}
		}
		return listaTemp;
	}
	
	public Collection<Cliente> elencoClienti() {
		LinkedList<Cliente> listaTemp = new LinkedList<Cliente>(clienti);
		Collections.sort(listaTemp);
		return listaTemp;
	}
	
	public Menu definisciMenu(String nomeMenu, String descrizione, double prezzoPerPersona) throws EccezioneMenuGiaDefinito{
		for(Menu m: menus)	{
			if(m.getNome().equals(nomeMenu))	{
				throw new EccezioneMenuGiaDefinito();
			}
		}
		Menu menu = new Menu(nomeMenu, descrizione, prezzoPerPersona);
		menus.add(menu);
		return menu;
	}
	
	public MenuLeggero definisciMenu(String nomeMenu, String descrizione, double prezzoPerPersona, int calorie) throws EccezioneMenuGiaDefinito{
		for(Menu m: menus)	{
			if(m.getNome().equals(nomeMenu))	{
				throw new EccezioneMenuGiaDefinito();
			}
		}
		MenuLeggero menu = new MenuLeggero(nomeMenu, descrizione, prezzoPerPersona, calorie);
		menus.add(menu);
		return menu;
	}
	
	public Collection<Menu> elencoMenuPerPrezzo(){
		LinkedList<Menu> listaTemp = new LinkedList<Menu>(menus);
		Collections.sort(listaTemp);
		return listaTemp;
	}

	public boolean aggiungiVoceMenu(String nomeMenu, String voce) throws EccezioneMenuNonDefinito{
		for(Menu m: menus)	{
			if(m.getNome().equals(nomeMenu))	{
				for(String v: m.voci)	{
					if(v.equals(voce))	{
						return false;
					}
				}
				m.voci.add(voce);
				return true;
			}
		}
		throw new EccezioneMenuNonDefinito();
	}
	
	public String stampaMenu(String nomeMenu) throws EccezioneMenuNonDefinito{
		String ris = "";
		boolean flag = false;
		for(Menu m: menus)	{
			if(m.getNome().equals(nomeMenu))	{
				flag = true;
				for(String v: m.voci)	{
					ris += (v+"\n");
				}
			}
		}
		if(flag == false)	{
			throw new EccezioneMenuNonDefinito();
		}
		return ris.substring(0, ris.length()-1);
	}
	
	public String prenotaServizio(String codiceCliente, String nomeMenu, String data, String ora, int numeroPersone) throws EccezioneMenuNonDefinito {
		String codice = "";
		if(cercaClientePerCodice(codiceCliente)==null)	{
			return null;
		}
		Cliente cliente = cercaClientePerCodice(codiceCliente);
		Menu menu = null;
		boolean flag = false;
		for(Menu m: menus)	{
			if(m.getNome().equals(nomeMenu))	{
				flag = true;
				menu = m;
			}
		}
		if(flag == false)	{
			throw new EccezioneMenuNonDefinito();
		}
		codice = codiceCliente+"-"+data;
		int conta = 1;
		for(Servizio s: prenotazioni)	{
			if(s.getCodice().startsWith(codice))	{
				conta++;
			}
		}
		codice += "-"+conta;
		Servizio servizio = new Servizio(codice, cliente, menu, data, ora, numeroPersone);
		prenotazioni.add(servizio);
		cliente.prenotazioni.add(servizio);
		menu.prenotazioni.add(servizio);
		return codice;
	}
	
	public Cliente cercaClienteServizio(String codiceServizio) {
		Cliente ris = null;
		for(Servizio s: prenotazioni)	{
			if(s.getCodice().equals(codiceServizio))	{
				ris = s.getCliente();
			}
		}
		return ris;
	}
	
	public String stampaServiziPerIncasso(){
		LinkedList<Servizio> listaTemp = new LinkedList<Servizio>(prenotazioni);
		Collections.sort(listaTemp);
		String ris = "";
		for(Servizio s: listaTemp)	{
			ris+=s.getCodice()+" "+s.getMenu().getNome()+" "+s.getIncasso()+"\n";
		}
		return ris.substring(0, ris.length()-1);
	}
	
	public String stampaServiziPerDataOra() {
		LinkedList<Servizio> listaTemp = new LinkedList<Servizio>(prenotazioni);
		Collections.sort(listaTemp, new ComparatorServizioDataOra());
		String ris = "";
		for(Servizio s: listaTemp)	{
			ris+=s.getCodice()+" "+s.getMenu().getNome()+" "+s.getData()+" "+s.getOra()+"\n";
		}
		return ris.substring(0, ris.length()-1);
	}

	public double determinaIncassoMedioPerServizio() {
		double tot = 0.0;
		for(Servizio s: prenotazioni)	{
			tot+=s.getIncasso();
		}
		double media = tot/prenotazioni.size();
		return media;
	}

	public double determinaNumeroDiServiziMedioPerCliente() {
		double tot = 0.0;
		for(Cliente c: clienti)	{
			if(c.prenotazioni.isEmpty()==false)	{
				tot+=c.prenotazioni.size();
			}
		}
		double media = tot/clienti.size();
		return media;
	}
	
	public Collection<Menu> determinaMenuPiuRichiesto() {
		if(prenotazioni.isEmpty())	{
			return null;
		}
		for(Servizio s: prenotazioni)	{
			s.getMenu().richieste++;
		}
		LinkedList<Menu> listaTemp = new LinkedList<Menu>(menus);
		Collections.sort(listaTemp, new ComparatorMenuRichieste());
		LinkedList<Menu> ris = new LinkedList<Menu>();
		for(Menu m: listaTemp)	{
			if(m.richieste>=listaTemp.get(0).richieste)	{
				ris.add(m);
			}
		}
		return ris;
	}
	
}
