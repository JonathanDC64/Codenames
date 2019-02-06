package mvc.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class KeycardModel implements ViewNotifier
{
    private ArrayList<CardType> keycard;
    private Team startTeam;
    private PropertyChangeListener keycardListener;

    private static final int MAX_CARDS = 25;

    public KeycardModel() {
        keycard = new ArrayList<>();
        for(int i = 0 ; i < MAX_CARDS ; ++i)
        {
            addCardType(CardType.BYSTANDER);
        }
    }

    public void addCardType(CardType cardType)
    {
        keycard.add(cardType);
        this.notifyView(keycardListener, "CardAdded", null, this);
    }

    public CardType getCardType(int index)
    {
        return keycard.get(index);
    }

    public void setCardType(int index, CardType cardType)
    {
        keycard.set(index, cardType);
        this.notifyView(keycardListener, "CardSet", null, this);
    }

    public void setStartTeam(Team team)
    {
        startTeam = team;
        this.notifyView(keycardListener, "TeamSet", null, this);
    }

    public Team getStartTeam() 
    {
        return startTeam;
    }

    public void setKeycardListener(PropertyChangeListener keycardListener)
    {
        this.keycardListener = keycardListener;
    }
}