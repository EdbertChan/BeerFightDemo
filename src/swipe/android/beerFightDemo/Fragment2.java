package swipe.android.beerFightDemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import swipe.android.beerFightDemo.model.FragmentGenerator;
import swipe.android.beerFightDemo.model.MultipleChoiceFragment;
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

public class Fragment2 extends Fragment {
	private ChangeFragmentInterface changer;

	public Fragment2(ChangeFragmentInterface changer) {
		this.changer = changer;
	}

	public Fragment2() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.middle_fragment, container,
				false);

		Button trivia = (Button) rootView.findViewById(R.id.button1);
		trivia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Fragment wf = FragmentGenerator
						.generateQuestionFragment(Fragment2.this.getActivity()
								.getApplicationContext(),(ChangeFragmentInterface) Fragment2.this.getActivity());
				changer.changeFragment(TabsAdapter.CHANGE_POSITION, wf);
			}

		});
		Button word_scramble = (Button) rootView.findViewById(R.id.button2);
		word_scramble.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// replace with new fragment
				Fragment wf = FragmentGenerator
						.generateWordFragment(Fragment2.this.getActivity()
								.getApplicationContext(),(ChangeFragmentInterface) Fragment2.this.getActivity());
				changer.changeFragment(TabsAdapter.CHANGE_POSITION, wf);
			}

		});
		return rootView;
	}
}