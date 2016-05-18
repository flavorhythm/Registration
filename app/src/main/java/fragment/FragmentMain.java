package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.zyuki.echoregistration.R;
import com.github.florent37.materialviewpager.MaterialViewPagerHeader;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.List;

import adapter.CardsAdapter;
import data.Card;

/**
 * Created by zyuki on 5/13/2016.
 */
public class FragmentMain extends Fragment {
    private RecyclerView recycler;

    private Callback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (Callback)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutRes = R.layout.fragment_main;
        View customView = inflater.inflate(layoutRes, container, false);

        CardsAdapter adapter = new CardsAdapter(callback.getCards());
        adapter.setListener(callback.getListener());

        recycler = (RecyclerView)customView.findViewById(R.id.frag_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new RecyclerViewMaterialAdapter(adapter));

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), recycler, null);

        return customView;
    }

    public interface Callback {
        List<Card> getCards();
        View.OnClickListener getListener();
    }
}
