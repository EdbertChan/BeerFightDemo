package swipe.android.beerFightDemo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.askerov.dynamicgrid.DynamicGridView;

import swipe.android.beerFightDemo.ChangeFragmentInterface;
import swipe.android.beerFightDemo.R;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class WordFragment extends QuestionFragment {

	String real;
	static Random random = new Random();
	ArrayList<Character> poem;
	DynamicGridView gridView;

	TextView instruction;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int layout = getLayoutResourceId();
		View rootView = inflater.inflate(layout, container, false);

		mQuestionText = (TextView) rootView.findViewById(R.id.questionText);
		gridView = (DynamicGridView) rootView.findViewById(R.id.dynamic_grid);
		instruction = (TextView) rootView.findViewById(R.id.questionText);
		done = (Button) rootView.findViewById(R.id.buttonDoneWord);
		return rootView;
	}

	WordDynamicAdapter adapter;
Button done;
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		String shuffle = getArguments().getString(EXTRA_SHUFFLE);
		real = getArguments().getString(EXTRA_REAL);
		super.mLastQuestion = getArguments().getBoolean(LAST_QUESTION_KEY);

		for (int i = 0; i < shuffle.length(); i++) {
			Log.d("Char", String.valueOf(shuffle.charAt(i)));
		}
		ArrayList<String> s = new ArrayList<String>(Arrays.asList(shuffle
				.split("")));
		s.remove(0);
		adapter = new WordDynamicAdapter(this.getActivity(), s,
				shuffle.length());
		// set our adapter

		gridView.setAdapter(adapter);
		gridView.setNumColumns(shuffle.length());

		instruction.setText("Rearrange tiles so that they spell "
				+ new String(real));
		setListeners();
		done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(adapter.currentString().equals(real)){
					changer.changeToNextFragment(WordFragment.this);
				}
			}
			
		});
		// return view;
	}

	public static WordFragment newInstance(ChangeFragmentInterface changer,
			String real, String shuffle, boolean last) {
		WordFragment myFragment = new WordFragment(changer);

		Bundle args = new Bundle();
		args.putString(EXTRA_REAL, real);
		args.putString(EXTRA_SHUFFLE, shuffle);
		args.putBoolean(LAST_QUESTION_KEY, last);
		myFragment.setArguments(args);
		return myFragment;
	}

	private static final String EXTRA_REAL = "REAL";
	private static final String EXTRA_SHUFFLE = "SHUFFLE";

	private void setListeners() {
		gridView.setOnDropListener(new DynamicGridView.OnDropListener() {
			@Override
			public void onActionDrop() {
				gridView.stopEditMode();
			}
		});
		
		gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				gridView.startEditMode(position);
				return true;
			}
		});
		   gridView.setOnDragListener(new DynamicGridView.OnDragListener() {
	            @Override
	            public void onDragStarted(int position) {
	             //   Log.d(TAG, "drag started at position " + position);
	            }

	            @Override
	            public void onDragPositionsChanged(int oldPosition, int newPosition) {
	                //Log.d(TAG, String.format("drag item position changed from %d to %d", oldPosition, newPosition));
	            }
	        });
	      

	      

	}



	public WordFragment(ChangeFragmentInterface changer) {
		super(changer);
	}

	@Override
	protected int getLayoutResourceId() {
		// TODO Auto-generated method stub
		return R.layout.word_fragment;
	}
}