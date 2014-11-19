package com.edu.untref.gcu.domain;

import java.util.Comparator;

import com.edu.untref.gcu.dtos.ColisionMateriaDTO;

public class ColisionMateriaComparator implements Comparator<ColisionMateriaDTO>{

	@Override
	public int compare(ColisionMateriaDTO o1, ColisionMateriaDTO o2) {
		if (o1.getColisiones().size() > o2.getColisiones().size()){ 
			return -1;
		} else if (o1.getColisiones().size() < o2.getColisiones().size()) {
			return 1;
		} else {
			return 0;
		}
	}

}