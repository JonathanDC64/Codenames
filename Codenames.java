import mvc.controller.GameController;
import mvc.model.GameModel;
import mvc.view.GameView;

public class Codenames
{
    public static void main(String[] args)
    {
        GameView gameView = new GameView();
        GameModel gameModel = new GameModel();
        GameController gameController = new GameController(gameModel, gameView);
        gameController.initialize();
    }
}