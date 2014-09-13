package swipe.android.beerFightDemo.model;

import swipe.android.beerFightDemo.ChangeFragmentInterface;
import swipe.android.beerFightDemo.R;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MultipleChoiceFragment extends QuestionFragment {
	private static final int[] sAnswerButtonIds = new int[] { R.id.answer1,
			R.id.answer2, R.id.answer3, R.id.answer4 };

	@Override
	protected int getLayoutResourceId() {
		return R.layout.multiple_choice_fragment_fixed_buttons;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		for (int i = 0; i < sAnswerButtonIds.length; i++)
			view.findViewById(sAnswerButtonIds[i]).setOnClickListener(
					new ButtonListener(i));
Button submit = (Button) view.findViewById(R.id.answer_done);
submit.setOnClickListener(new OnClickListener(){

	@Override
	public void onClick(View v) {
		if(mAnswer == mQuestion.getAnswer()){
			mAnswer = NO_ANSWER;
			changer.changeToNextFragment(MultipleChoiceFragment.this);
		}
	}
	
});
	}

	@Override
	protected void onAnswerChanged() {
		// super.onAnswerChanged();
		View view = getView();
		if (view == null)
			return;
		Question question = mQuestion;
		if (question == null)
			return;

		for (int i = 0; i < sAnswerButtonIds.length; i++) {
			QuizButton btn = (QuizButton) view
					.findViewById(sAnswerButtonIds[i]);
			boolean showingAnswer = mAnswer != NO_ANSWER;

			if (i < question.getChoices().length) {
				btn.setVisibility(View.VISIBLE);
				btn.setShowingAnswer(showingAnswer);
				btn.setCorrect(i == question.getAnswer());
				btn.setText(question.getChoices()[i]);
				// btn.setClickable(!showingAnswer);
			} else {
				btn.setVisibility(View.GONE);
			}
			
			if(i == mAnswer){
				btn.setBackgroundColor(Color.RED);
			}else{
				btn.setBackgroundColor(Color.BLUE);
			}
			//if this is the one we selected, then we should use it
			//if(mAnswer == btn.get)
		}
		//mAnswer is our position so we're just going to use that
	}

	private class ButtonListener implements OnClickListener {
		private final int mIndex;

		public ButtonListener(int index) {
			mIndex = index;
		}

		@Override
		public void onClick(View v) {
			setAnswer(mIndex);
		}
	}

	public static MultipleChoiceFragment newInstance(
			ChangeFragmentInterface changer, Question question,
			boolean isLastQuestion) {
		MultipleChoiceFragment fragment = new MultipleChoiceFragment(changer);
		fragment.setArguments(newArguments(question, isLastQuestion));
		return fragment;
	}

	public MultipleChoiceFragment(ChangeFragmentInterface changer) {
		super(changer);
	}

}