package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zyuki.echoregistration.R;

/***************************************************************************************************
 * CardsHolder class extending RecyclerView.CardsHolder
 * Created by zyuki on 5/13/2016.
 *
 * Used in CardsAdapter as a viewholder to recycle views for the list of cads.
 **************************************************************************************************/
public class CardsHolder extends RecyclerView.ViewHolder {
    /***********************************************************************************************
     * GLOBAL VARIABLES
     **********************************************************************************************/
    public String cardTitle;

    public ImageView image;
    public TextView cardText;

    /***********************************************************************************************
     * CONSTRUCTORS
     **********************************************************************************************/
    public CardsHolder(View cardView) {
        super(cardView);

        image = (ImageView)cardView.findViewById(R.id.card_img_cardImg);
        cardText = (TextView)cardView.findViewById(R.id.card_text_cardText);
    }

    /***********************************************************************************************
     * PUBLIC METHODS
     **********************************************************************************************/
    public String getCardTitle() {return cardTitle;}
}