package mvc.model;

public class CodenameModel
{
    private String name;
    private boolean revealed;
    private CardType cardType;
    private int cardPosition;

    public CodenameModel(String name, int position)
    {
        this.name = name;
        this.revealed = false;
        this.cardType = null;
        this.cardPosition = position;
    }

    public String getName()
    {
        return this.name;
    }

    public void setRevealed(boolean revealed)
    {
        this.revealed = revealed;
    }

    public boolean isRevealed()
    {
        return this.revealed;
    }

    public void setCardType(CardType cardType)
    {
        this.cardType = cardType;
    }

    public CardType getCardType()
    {
        return this.cardType;
    }

    public int getCardPosition()
    {
        return this.cardPosition;
    }
}