package mainengine;

public class MainEngineFactory {
	
	/**
 	 * Creates a MainEngine object
 	 * 
 	 * @return MainEngine object
 	 */

	public IMainEngine createMainEngine(String engineType) {
		if(engineType.equals("MainEngine"))
			return new MainEngine();
		else
			return null;
	}

}//end class
