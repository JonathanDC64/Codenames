package mvc.model;

public class SpymasterModel
{
    private Team team;
    private KeycardModel keycard;
    private GridModel grid;

    public SpymasterModel()
    {

    }

    public SpymasterModel(Team team, KeycardModel keycard, GridModel grid)
    {
        setTeam(team);
        setKeycard(keycard);
        setGrid(grid);
    }

    public void setTeam(Team team)
    {
        this.team = team;
    }

    public Team getTeam()
    {
        return this.team;
    }

    public void setKeycard(KeycardModel keycard)
    {
        this.keycard = keycard;
    }

    public KeycardModel getKeycard()
    {
        return keycard;
    }

    public void setGrid(GridModel grid)
    {
        this.grid = grid;
    }

    public GridModel getGrid()
    {
        return this.grid;
    }
}