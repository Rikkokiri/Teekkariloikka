package graafinenteekkariloikka.gamestate;


public class GameStateManager {

	private static GameState state = GameState.ALOITUS;
	
	public static void setGamestate(GameState s){
		state = s;
	}
	
	public static GameState getGamestate(){
		return state;
	}
}
