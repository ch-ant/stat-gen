package dataload;

import datamodel.MeasurementRecord;

public class LoaderFactory<E> {
	
	/**
 	 * Creates a Loader object
 	 * 
 	 * @return Loader object
 	 */

	public ILoader<MeasurementRecord> createLoader(String loaderType) {
		
		if(loaderType.equals("Loader"))
			return new Loader<E>();
		else
			return null;
	}

}
