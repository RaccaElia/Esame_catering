package catering;

import java.util.Comparator;

public class ComparatorMenuRichieste implements Comparator<Menu>{

	public int compare(Menu o1, Menu o2) {
		return o2.richieste-o1.richieste;
	}

}
