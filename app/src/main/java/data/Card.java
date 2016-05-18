package data;

/**
 * Created by zyuki on 5/13/2016.
 */
public class Card {
    int drawableRes;
    String cardTitle;
    String cardText;

    public Card() {}

    public Card(int drawableRes, String cardTitle, String cardText) {
        this.drawableRes = drawableRes;
        this.cardTitle = cardTitle;
        this.cardText = cardText;
    }

    public void setDrawableRes(int drawableRes) {this.drawableRes = drawableRes;}
    public void setCardTitle(String cardTitle) {this.cardTitle = cardTitle;}
    public void setCardText(String cardText) {this.cardText = cardText;}

    public int getDrawableRes() {return drawableRes;}
    public String getCardTitle() {return cardTitle;}
    public String getCardText() {return cardText;}
}
