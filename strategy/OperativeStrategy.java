package strategy;

import mvc.model.ClueModel;
import mvc.model.CodenameModel;
import mvc.model.OperativeModel;

public interface OperativeStrategy
{
    public CodenameModel execute(OperativeModel operative, ClueModel clue);
}