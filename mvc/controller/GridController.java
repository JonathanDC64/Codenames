package mvc.controller;

import mvc.model.GridModel;
import mvc.view.GridView;

public class GridController
{
    private GridModel gridModel;
    private GridView gridView;

    public GridController(GridModel gridModel, GridView gridView)
    {
        this.gridModel = gridModel;
        this.gridView = gridView;

        this.gridModel.setGridListener(this.gridView);
    }
}