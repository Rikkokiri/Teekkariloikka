package graafinenteekkariloikka.main;

import graafinenteekkariloikka.view.*;
import graafinenteekkariloikka.model.*;
import graafinenteekkariloikka.controller.*;

public class GraafinenTeekkariloikkaMain {

	public static void main(String[] args){
		
		//Luodaan view, model ja controller
		TLView view = new TLView();
		TLModel model = new TLModel();
		
		TLController controller = new TLController(view, model);
		
		view.setNewGameButtonListener(controller);
		view.setInputNumberListener(controller);
		view.setInputNamesListener(controller);
		view.setLaunchGameButtonListener(controller);
		
		view.setDrawCardButtonListener(controller);
		
		view.setGameBoardButtonListeners(controller);
		view.setPawnListener(controller);
		
		view.setSaveGameListener(controller);
		view.setQuitGameListener(controller);
		view.setLoadSaveListener(controller);
		
		view.setVisible(true);
		
	}
}
