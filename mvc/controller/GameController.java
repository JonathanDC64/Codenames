package mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.HashMap;

import generator.Generators;
import mvc.model.CardType;
import mvc.model.ClueModel;
import mvc.model.CodenameModel;
import mvc.model.GameModel;
import mvc.model.OperativeModel;
import mvc.model.SpymasterModel;
import mvc.model.Team;
import mvc.view.GameView;
import mvc.view.StartView;
import strategy.OperativeRandomStrategy;
import strategy.OperativeStrategy;
import strategy.SpymasterRandomStrategy;
import strategy.SpymasterStrategy;

public class GameController {
    private GameModel gameModel;
    private GameView gameView;

    private KeycardController keycardController;
    private LogController logController;
    private GridController gridController;

    private StartView startView;

    private SpymasterStrategy spymasterStrategy;
    private OperativeStrategy operativeStrategy;

    private enum GameState
    {
        BLUE_SPYMASTER_TURN,
        BLUE_OPERATIVE_TURN,
        RED_SPYMASTER_TURN,
        RED_OPERATIVE_TURN,
        GAME_OVER
    }

    private GameState gameState;

    private static final HashMap<CardType, String> cardTypeText = new HashMap<CardType, String>()
    {
        private static final long serialVersionUID = 1L;
        {
            put(CardType.BLUE_AGENT, "Blue Agent");
            put(CardType.RED_AGENT, "Red Agent");
            put(CardType.ASSASSIN, "Assassin");
            put(CardType.BYSTANDER, "Bystander");
        }
    };

    private static final HashMap<Team, String> teamText = new HashMap<Team, String>()
    {
        private static final long serialVersionUID = 1L;
        {
            put(Team.BLUE, "Blue Team");
            put(Team.RED, "Red Team");
        }
    };

    public GameController(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;

        this.startView = this.gameView.getStartView();

        this.keycardController = new KeycardController(this.gameModel.getKeycard(), this.gameView.getKeycardView());
        this.logController = new LogController(this.gameModel.getLog(), this.gameView.getLogView());
        this.gridController = new GridController(this.gameModel.getGrid(), this.gameView.getGridView());
        
        this.spymasterStrategy = new SpymasterRandomStrategy();
        this.operativeStrategy = new OperativeRandomStrategy();

        this.gameModel.setGameListener(this.startView);
        this.startView.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                start();
            }
        });

        this.startView.getStepButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(gameModel.isStarted())
                {
                    step();
                }
            }
        });
    }

    private void start() 
    {
        try 
        {
            Generators.generateKeycard(gameModel.getKeycard());
            Generators.generateGrid(gameModel.getGrid());
        }
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        
        if(gameModel.getKeycard().getStartTeam() == Team.BLUE)
        {
            gameState = GameState.BLUE_SPYMASTER_TURN;
        }
        else
        {
            gameState = GameState.RED_SPYMASTER_TURN;
        }
        guessCount = 0;
        blueAgentsFound = 0;
        redAgentsFound = 0;
        gameModel.getGrid().clear();
        gameModel.getLog().clearLog();
        gameModel.start();
    }

    private ClueModel currentClue;
    private int guessCount;
    private int blueAgentsFound;
    private int redAgentsFound;
    private Team winningTeam;

    private void step()
    {
        switch(gameState)
        {
            case BLUE_SPYMASTER_TURN:
            spymasterTurn(gameModel.getBlueSpymaster());
            break;

            case BLUE_OPERATIVE_TURN:
            operativeTurn(gameModel.getBlueOperative());
            break;

            case RED_SPYMASTER_TURN:
            spymasterTurn(gameModel.getRedSpymaster());
            break;

            case RED_OPERATIVE_TURN:
            operativeTurn(gameModel.getRedOperative());
            break;

            case GAME_OVER:
            log(String.format("%s wins", teamText.get(winningTeam)));
            gameModel.stop();
            break;
        }
    }

    private void spymasterTurn(SpymasterModel spymaster)
    {
        ClueModel clue = spymasterStrategy.execute(spymaster);
        currentClue = clue;

        log(String.format("%s Spymaster gives clue : %d %s", teamText.get(spymaster.getTeam()), clue.getNumber(), clue.getWord()));

        guessCount = 0;
        
        if(gameState == GameState.BLUE_SPYMASTER_TURN)
        {
            gameState = GameState.BLUE_OPERATIVE_TURN;
        }
        else if(gameState == GameState.RED_SPYMASTER_TURN)
        {
            gameState = GameState.RED_OPERATIVE_TURN;
        }
    }

    private void operativeTurn(OperativeModel operative)
    {
        CodenameModel codename = operativeStrategy.execute(operative, currentClue);

        // Team ends turn
        if(codename == null)
        {
            log(String.format("%s Operative ends turn", teamText.get(operative.getTeam())));
            if(operative.getTeam() == Team.BLUE)
            {
                gameState = GameState.RED_SPYMASTER_TURN;
            }
            else
            {
                gameState = GameState.BLUE_SPYMASTER_TURN;
            }
            return;
        }

        CardType cardType = gameModel.getKeycard().getCardType(codename.getCardPosition());
        codename.setCardType(cardType);
        codename.setRevealed(true);

        log(String.format("%s Operative guesses the Codename %s which is a %s", teamText.get(operative.getTeam()), codename.getName(), cardTypeText.get(cardType)));

        switch(cardType)
        {
            case BLUE_AGENT:
            if(operative.getTeam() == Team.BLUE && guessCount < currentClue.getNumber() + 1)
            {
                guessCount++;
                gameState = GameState.BLUE_OPERATIVE_TURN;
            }
            else
            {
                gameState = GameState.BLUE_SPYMASTER_TURN;
            }
            blueAgentsFound++;
            break;

            case RED_AGENT:
            if(operative.getTeam() == Team.RED && guessCount < currentClue.getNumber() + 1)
            {
                guessCount++;
                gameState = GameState.RED_OPERATIVE_TURN;
            }
            else
            {
                gameState = GameState.RED_SPYMASTER_TURN;
            }
            redAgentsFound++;
            break;

            case BYSTANDER:
            //guessCount = 0;
            if(operative.getTeam() == Team.BLUE)
            {
                gameState = GameState.RED_SPYMASTER_TURN;
            }
            else
            {
                gameState = GameState.BLUE_SPYMASTER_TURN;
            }
            break;

            case ASSASSIN:
            
            if(operative.getTeam() == Team.BLUE)
            {
                winningTeam = Team.RED;
            }
            else
            {
                winningTeam = Team.BLUE;
            }
            gameState = GameState.GAME_OVER;
            break;
        }

        if(blueAgentsFound == 8)
        {
            winningTeam = Team.BLUE;
            gameState = GameState.GAME_OVER;
        }

        if(redAgentsFound == 9)
        {
            winningTeam = Team.RED;
            gameState = GameState.GAME_OVER;
        }

        gameModel.getGrid().update();
    }

    public void initialize()
    {
        gameView.display();
    }

    private void log(String text)
    {
        logController.addLog(text);
    }
}