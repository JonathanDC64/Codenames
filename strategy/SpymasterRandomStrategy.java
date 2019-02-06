package strategy;

import java.util.ArrayList;

import mvc.model.CardType;
import mvc.model.ClueModel;
import mvc.model.CodenameModel;
import mvc.model.GridModel;
import mvc.model.KeycardModel;
import mvc.model.SpymasterModel;
import mvc.model.Team;

public class SpymasterRandomStrategy implements SpymasterStrategy 
{
    @Override
    public ClueModel execute(SpymasterModel spymaster) 
    {
        ClueModel clue = new ClueModel();

        GridModel grid = spymaster.getGrid();

        KeycardModel keycard = spymaster.getKeycard();

        Team team = spymaster.getTeam();

        ArrayList<CodenameModel> teamCodenames = new ArrayList<CodenameModel>();
        
        // Find remaining agents
        for(int i = 0 ; i < 25 ; ++i)
        {
            CodenameModel codename = grid.getCodename(i);

            if((keycard.getCardType(i) == CardType.BLUE_AGENT && team == Team.BLUE && !codename.isRevealed()) || 
               (keycard.getCardType(i) == CardType.RED_AGENT  && team == Team.RED  && !codename.isRevealed())
            )
            {
                teamCodenames.add(codename);
            }
        }


        // Select random agent;
        CodenameModel startCodename = teamCodenames.get((int)(Math.random() * teamCodenames.size()));

        char startingChar = startCodename.getName().charAt(0);

        int numClues = 1;

        //Find other codenames that start with same character
        for(int i = 0 ; i < teamCodenames.size() ; ++i)
        {
            CodenameModel codename = teamCodenames.get(i);

            if(codename != startCodename && codename.getName().charAt(0) == startingChar)
            {
                numClues++;
            }
        }
        
        clue.setNumber(numClues);
        clue.setWord(Character.toString(startingChar));

        return clue;
    }
}