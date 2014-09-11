package swipe.android.beerFightDemo.model;

import swipe.android.beerFightDemo.BaseContainerFragment;
import swipe.android.beerFightDemo.ChangeFragmentInterface;
import swipe.android.beerFightDemo.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class QuestionFragment extends Fragment {
	/**
	 * Must be implemented by the parent activity.
	 */
	public interface QuestionListener {
		void onNextQuestion();

		void onQuestionAnswered(int answer);
	}

	public static int NO_ANSWER = -1;
	public static final String QUESTION_KEY = "question";
	public static final String ANSWER_KEY = "answer";
	public static final String ANSWERED_KEY = "answered";
	public static final String LAST_QUESTION_KEY = "lastQuestion";

	protected Question mQuestion;
	protected boolean mLastQuestion;
	protected int mAnswer = NO_ANSWER;


	/** implemented to provide layouts for subclasses */
	protected abstract int getLayoutResourceId();

	protected TextView mQuestionText;




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int layout = getLayoutResourceId();
		View rootView = inflater.inflate(layout, container, false);

		mQuestionText = (TextView) rootView.findViewById(R.id.questionText);

		// don't do anything here because injection hasn't happened yet
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mQuestion = getArguments().getParcelable(QUESTION_KEY);
		mLastQuestion = getArguments().getBoolean(LAST_QUESTION_KEY, false);
		if (savedInstanceState != null) {
			mAnswer = savedInstanceState.getInt(ANSWER_KEY, NO_ANSWER);
		} else {
			mAnswer = NO_ANSWER;
		}

		if(mQuestion != null){
		mQuestionText.setText(mQuestion.getmText());
		mQuestionText.setTextSize(20);
		}
		onAnswerChanged();
	}

	protected boolean isNextQuestionButtonEnabled() {
		return mAnswer != NO_ANSWER;
	}

	protected void onAnswerChanged() {
		if (mAnswer != NO_ANSWER ) {
	
		}
	}
	

	public void setAnswer(int answer) {
			mAnswer = answer;
			onAnswerChanged();
	}

	public boolean isLast(){
		return mLastQuestion;
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(ANSWER_KEY, mAnswer);
	}

	public static Bundle newArguments(Question question, boolean lastQuestion) {
		Bundle b = new Bundle();
		b.putParcelable(QUESTION_KEY, question);
		b.putBoolean(LAST_QUESTION_KEY, lastQuestion);
		return b;
	}
	ChangeFragmentInterface changer;
	public QuestionFragment(ChangeFragmentInterface changer){
		this.changer = changer;
	}
	
}