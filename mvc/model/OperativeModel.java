package mvc.model;

public class OperativeModel
{
    private Team team;
    private GridModel grid;

    public OperativeModel()
    {

    }

    public OperativeModel(Team team, GridModel grid)
    {
        setTeam(team);
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

    public void setGrid(GridModel grid)
    {
        this.grid = grid;
    }

    public GridModel getGrid()
    {
        return this.grid;
    }
}