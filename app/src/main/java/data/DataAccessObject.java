package data;

import com.example.zyuki.echoregistration.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyuki on 5/13/2016.
 */
public class DataAccessObject {
    private List<Card> cardsList;
    private String[] titleList;
    private String[] textList;
    private Integer[] drawableList;

    public DataAccessObject() {
        cardsList = new ArrayList<>();
        titleList = new String[] {
                "specs",
                "location",
                "unit_sale",
                "how_to",
                "fuel_sale",
                "recommendation"
        };

        textList = new String[] {
                "Get the specs of this unit",
                "Locate the nearest dealer",
                "Ongoing sale!",
                "How-to videos",
                "Get this fuel!",
                "Which unit should I buy?"
        };

        drawableList = new Integer[] {
                R.drawable.specs,
                R.drawable.locator,
                R.drawable.sale,
                R.drawable.videos,
                R.drawable.can,
                R.drawable.suggestions
        };

        buildList();
    }

    public List<Card> getCards() {
        return cardsList;
    }

    private void buildList() {
        for(int i = 0; i < textList.length; i++) {
            cardsList.add(
                    new Card(drawableList[i], titleList[i], textList[i])
            );
        }
    }
}
