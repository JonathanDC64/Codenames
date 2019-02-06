package mvc.controller;

import mvc.model.KeycardModel;
import mvc.view.KeycardView;

public class KeycardController
{
    private KeycardModel keycardModel;
    private KeycardView keycardView;

    public KeycardController(KeycardModel keycardModel, KeycardView keycardView)
    {
        this.keycardView = keycardView;
        setKeycardModel(keycardModel);
    }

    public void setKeycardModel(KeycardModel keycardModel)
    {
        this.keycardModel = keycardModel;
        this.keycardModel.setKeycardListener(this.keycardView);
    }
}