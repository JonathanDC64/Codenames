package generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import mvc.model.CardType;
import mvc.model.CodenameModel;
import mvc.model.GridModel;
import mvc.model.KeycardModel;
import mvc.model.Team;

public class Generators {
    public static void generateKeycard(KeycardModel keycardModel) {

        ArrayList<Integer> cardSet = new ArrayList<Integer>();

        for (int i = 0; i < 25; ++i) {
            cardSet.add(i);
        }

        for (int i = 0; i < 8; ++i) {
            keycardModel.setCardType(cardSet.remove((int) (Math.random() * cardSet.size())), CardType.BLUE_AGENT);
        }

        for (int i = 0; i < 9; ++i) {
            keycardModel.setCardType(cardSet.remove((int) (Math.random() * cardSet.size())), CardType.RED_AGENT);
        }

        keycardModel.setCardType(cardSet.remove((int) (Math.random() * cardSet.size())), CardType.ASSASSIN);

        for (int i = 0; i < cardSet.size(); ++i) {
            keycardModel.setCardType(cardSet.get(i), CardType.BYSTANDER);
        }

        Team team;

        if ((int) (Math.random() * 2.0) == 0) {
            team = Team.BLUE;
        } else {
            team = Team.RED;
        }

        keycardModel.setStartTeam(team);
    }

    public static void generateGrid(GridModel gridModel) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File("Codenames.txt"));

        ArrayList<String> codenames = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();

        int count = 0;

        while(scanner.hasNextLine())
        {
            codenames.add(scanner.nextLine());
            indexes.add(count);
            count++;
        }

        Collections.shuffle(codenames);
        Collections.shuffle(indexes);

        for(int i = 0 ; i < 25 ; ++i)
        {
            CodenameModel codenameModel = new CodenameModel(codenames.get(indexes.get(i)), i);
            gridModel.setCodename(i, codenameModel);
        }

        scanner.close();
    }
}