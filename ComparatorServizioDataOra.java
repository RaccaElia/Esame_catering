package catering;

import java.util.Comparator;

public class ComparatorServizioDataOra implements Comparator<Servizio>{

	public int compare(Servizio o1, Servizio o2) {
		if(o1.getData().equals(o2.getData()))	{
			if(o1.getOra().equals(o2.getOra()))	{
				return o1.getCodice().compareTo(o2.getCodice());
			}else {
				return o1.getOra().compareTo(o2.getOra());
			}
		}
		return o1.getData().compareTo(o2.getData());
	}

}
