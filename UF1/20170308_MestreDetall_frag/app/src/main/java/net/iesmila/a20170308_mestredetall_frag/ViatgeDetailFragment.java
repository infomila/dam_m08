package net.iesmila.a20170308_mestredetall_frag;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.iesmila.a20170308_mestredetall_frag.dummy.ViatgesManager;

/**
 * A fragment representing a single Viatge detail screen.
 * This fragment is either contained in a {@link ViatgeListActivity}
 * in two-pane mode (on tablets) or a {@link ViatgeDetailActivity}
 * on handsets.
 */
public class ViatgeDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ID = "id";

    /**
     * The dummy content this fragment is presenting.
     */
    private ViatgesManager.Viatge mViatge;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ViatgeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            int id = getArguments().getInt(ARG_ID);
            Log.d("XXXX", "ID="+id);
            Log.d("XXXX", "ITEM_MAP="+ViatgesManager.ITEM_MAP);
            mViatge = ViatgesManager.ITEM_MAP.get(id);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mViatge.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.viatge_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mViatge != null) {
            ((TextView) rootView.findViewById(R.id.viatge_detail)).setText(mViatge.getDesc());
        }

        return rootView;
    }
}
