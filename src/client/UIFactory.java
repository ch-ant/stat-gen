package client;


public class UIFactory {

	
	public IUserInterface createUI(String UIType) {
		
		if(UIType.equals("UserInterface"))
			return new UIMethods();
		else
			return null;
	}
}
