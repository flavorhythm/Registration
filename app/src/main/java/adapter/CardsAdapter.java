package adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zyuki.echoregistration.R;

import java.util.List;

import data.Card;

/***************************************************************************************************
 * CardsAdapter class extending RecyclerView.Adapter<CardsHolder>
 * Created by zyuki on 5/13/2016.
 *
 * Used in FragmentMain when constructing a new RecyclerViewMaterialPager object
 * This adapter processes a list of Card objects and displays each one on a separate CardView.
 **************************************************************************************************/
public class CardsAdapter extends RecyclerView.Adapter<CardsHolder> {
    /***********************************************************************************************
     * GLOBAL VARIABLES
     **********************************************************************************************/
    /**Private**/
    /**Stored list of Card objects to display on CardViews**/
    private List<Card> cardsList;
    /**Stored listener from an external object**/
    private View.OnClickListener listener;

    /***********************************************************************************************
     * CONSTRUCTORS
     **********************************************************************************************/
    /**Constructor that instantiates this class with a list of cards to display on CardViews**/
    public CardsAdapter(List<Card> cardsList) {
        this.cardsList = cardsList;
    }

    /***********************************************************************************************
     * OVERRIDE METHODS
     **********************************************************************************************/
    /**Override method that runs when a CardsHolder is created for the first time.**/
    @Override
    public CardsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutRes = R.layout.card_layout;
        View card = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
        card.setOnClickListener(listener);

        CardsHolder holder = new CardsHolder(card);
        card.setTag(holder);

        Log.v("AndroidTest", "created viewHolders");
        return holder;
    }

    /**Override method that runs when a CardsHolder is bound to the CardView**/
    @Override
    public void onBindViewHolder(CardsHolder holder, int position) {
        holder.cardTitle = cardsList.get(position).getCardTitle();

        holder.image.setImageResource(cardsList.get(position).getDrawableRes());
        holder.cardText.setText(cardsList.get(position).getCardText());
    }

    /**Retrieves the number of Card objects (stored in cardsList) to display on CardViews.**/
    @Override
    public int getItemCount() {return cardsList.size();}

    /***********************************************************************************************
     * PUBLIC METHODS
     **********************************************************************************************/
    /**Allows an instance of this class to retrieve and store a listener from other classes**/
    public void setListener(View.OnClickListener listener) {this.listener = listener;}
}
