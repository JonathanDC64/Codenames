package mvc.model;

public class ClueModel
{
    private String word;
    private int number;

    public ClueModel()
    {

    }

    public ClueModel(String word, int number)
    {
        setWord(word);
        setNumber(number);
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    public String getWord()
    {
        return this.word;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getNumber()
    {
        return this.number;
    }
}