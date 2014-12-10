package com.edu.untref.gcu.services;

import java.io.File;
import java.io.FileNotFoundException;

public interface CargadorDeDatosService {
	void evaluarXlsx (File inputFile) throws FileNotFoundException;
}
