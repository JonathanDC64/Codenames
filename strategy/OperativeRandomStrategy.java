package strategy;

import java.util.ArrayList;

import mvc.model.ClueModel;
import mvc.model.CodenameModel;
import mvc.model.OperativeModel;

public class OperativeRandomStrategy implements OperativeStrategy 
{

    @Override
    public CodenameModel execute(OperativeModel operative, ClueModel clue) 
    {
        ArrayList<CodenameModel> possibleGuesses = new ArrayList<CodenameModel>();

        for(int i = 0 ; i < 25 ; ++i)
        {
            CodenameModel codename = operative.getGrid().getCodename(i);
            if(!codename.isRevealed() && codename.getName().charAt(0) == clue.getWord().charAt(0))
            {
                possibleGuesses.add(codename);
            }
        }
        
        if(possibleGuesses.isEmpty())
        {
            return null;
        }

        int guessIndex = (int)(Math.random() * possibleGuesses.size());

        CodenameModel codenameGuess = possibleGuesses.get(guessIndex);
        codenameGuess.setRevealed(true);

        return codenameGuess;
    }

}