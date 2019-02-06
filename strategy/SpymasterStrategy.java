package strategy;

import mvc.model.ClueModel;
import mvc.model.SpymasterModel;

public interface SpymasterStrategy
{
    public ClueModel execute(SpymasterModel spymaster);
}