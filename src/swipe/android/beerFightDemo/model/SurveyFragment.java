package swipe.android.beerFightDemo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import swipe.android.beerFightDemo.BaseContainerFragment;
import swipe.android.beerFightDemo.ChangeFragmentInterface;
import swipe.android.beerFightDemo.Fragment2;
import swipe.android.beerFightDemo.R;
import swipe.android.beerFightDemo.model.FragmentGenerator;
import swipe.android.beerFightDemo.model.WordFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SurveyFragment extends Fragment {
	ChangeFragmentInterface changer;

	public SurveyFragment(ChangeFragmentInterface changer) {
		this.changer = changer;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.survey_fragment, container,
				false);

		Button survey = (Button) rootView.findViewById(R.id.button1);
		survey.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// ((BaseContainerFragment)getParentFragment()).replaceFragment(wf,
				// true);
			}

		});
		final Fragment f = this;
		Button returnButton = (Button) rootView.findViewById(R.id.button2);
		returnButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// replace with new fragment
				changer.changeToNextFragment(f);
			}

		});
		return rootView;
	}
}