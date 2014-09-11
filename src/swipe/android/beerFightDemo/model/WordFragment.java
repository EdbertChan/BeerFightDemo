package swipe.android.beerFightDemo.model;

import java.util.ArrayList;
import java.util.Random;

import swipe.android.beerFightDemo.BaseContainerFragment;
import swipe.android.beerFightDemo.ChangeFragmentInterface;
import swipe.android.beerFightDemo.R;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.animoto.android.views.DraggableGridView;
import com.animoto.android.views.OnRearrangeListener;

public class WordFragment extends QuestionFragment {
	char[] shuffle;
	char[] real;
	static Random random = new Random();
	ArrayList<Character> poem;
	DraggableGridView dgv;

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.word_fragment, container,
				false);
	
	

		for (int i = 0; i < shuffle.length; i++) {
			char word = shuffle[i];
			ImageView view = new ImageView(this.getActivity());
			view.setImageBitmap(getThumb(String.valueOf(word)));
			dgv.addView(view);
			poem.add(word);
		}
		TextView instruction = (TextView) rootView
				.findViewById(R.id.questionText);
		instruction.setText("Rearrange tiles so that they spell "
				+ new String(real));
		setListeners();
		return rootView;
	}*/
	TextView instruction;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int layout = getLayoutResourceId();
		View rootView = inflater.inflate(layout, container, false);

		mQuestionText = (TextView) rootView.findViewById(R.id.questionText);
		 poem = new ArrayList<Character>();
			 instruction = (TextView) rootView
					.findViewById(R.id.questionText);
			dgv = ((DraggableGridView) rootView.findViewById(R.id.dgv));
		// don't do anything here because injection hasn't happened yet
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		shuffle = getArguments().getString(EXTRA_SHUFFLE).toCharArray();
		real = getArguments().getString(EXTRA_REAL).toCharArray();
		super.mLastQuestion = getArguments().getBoolean(LAST_QUESTION_KEY);
		for (int i = 0; i < shuffle.length; i++) {
			char word = shuffle[i];
			ImageView imageView = new ImageView(this.getActivity());
			imageView.setImageBitmap(getThumb(String.valueOf(word)));
			dgv.addView(imageView);
			poem.add(word);
		}

		instruction.setText("Rearrange tiles so that they spell "
				+ new String(real));
		setListeners();
		//return view;
	}
	public static WordFragment newInstance(ChangeFragmentInterface changer, String real, String shuffle, boolean last) {
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
		dgv.setOnRearrangeListener(new OnRearrangeListener() {
			public void onRearrange(int oldIndex, int newIndex) {
				char word = poem.remove(oldIndex);
				if (oldIndex < newIndex)
					poem.add(newIndex, word);
				else
					poem.add(newIndex, word);

				// itterate and see if poem matches
				String s = "";
				for (int i = 0; i < poem.size(); i++) {
					s += poem.get(i);
				}
				Log.i("Answer", s);
				if (s.equals(new String(real))) {
					// go to next

					changer.changeToNextFragment(WordFragment.this);
				}

			}
		});
		dgv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				dgv.removeViewAt(arg2);
				poem.remove(arg2);
			}
		});

	}

	private Bitmap getThumb(String s) {
		Bitmap bmp = Bitmap.createBitmap(150, 150, Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bmp);
		Paint paint = new Paint();

		paint.setColor(Color.rgb(random.nextInt(128), random.nextInt(128),
				random.nextInt(128)));
		paint.setTextSize(24);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		canvas.drawRect(new Rect(0, 0, 150, 150), paint);
		paint.setColor(Color.WHITE);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(s, 75, 75, paint);

		return bmp;
	}



	public WordFragment(ChangeFragmentInterface changer){
		super(changer);
	}

	@Override
	protected int getLayoutResourceId() {
		// TODO Auto-generated method stub
		return R.layout.word_fragment;
	}
}