package fragment;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

/**
 * Created by zyuki on 5/16/2016.
 */
public class DialogRouter {
    private static final String DIALOG_TAG = "dialog";
    private DialogRouter() {}

    public static void showAddDialog(Activity activity) {
        FragmentTransaction fragTrans = clearFragments(activity);
        DialogAddUnit dialog = DialogAddUnit.newInstance();

        fragTrans.add(dialog, DIALOG_TAG);
        fragTrans.show(dialog);
        fragTrans.commit();
    }

    public static void showConfDialog(Activity activity) {
        FragmentTransaction fragTrans = clearFragments(activity);
        DialogConfirm dialog = DialogConfirm.newInstance();

        fragTrans.add(dialog, DIALOG_TAG);
        fragTrans.show(dialog);
        fragTrans.commit();
    }

    private static FragmentTransaction clearFragments(Activity activity) {
        final FragmentManager fManager = ((FragmentActivity)activity).getSupportFragmentManager();

        FragmentTransaction fragTrans = fManager.beginTransaction();

        Fragment previousFragment = fManager.findFragmentByTag(DIALOG_TAG);

        if(previousFragment != null) {
            Log.v("fragmentTags", previousFragment.getTag());
            fragTrans.remove(previousFragment);}

        fragTrans.addToBackStack(null);

        return fragTrans;
    }
}

