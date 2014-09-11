package swipe.android.beerFightDemo.model;

import java.util.ArrayList;
import java.util.Arrays;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
	private long mId;
	private String mText;
	private ArrayList<String> mChoices;
	private int mAnswer;

	/**
	 * The text of the question
	 * 
	 * @return the text
	 */
	public String getText() {
		return mText;
	}

	/**
	 * The index of the correct answer in {@link #getChoices()}.
	 * 
	 * @return the index
	 */
	public int getAnswer() {
		return mAnswer;
	}

	public long getId() {
		return mId;
	}

	/**
	 * Create a question from the database
	 */

	@Override
	public String toString() {
		return "Question [mText=" + mText + ", mChoices="
				+ Arrays.toString(listToArray(mChoices)) + ", mAnswer="
				+ mAnswer + "]";
	}

	/*
	 * Parcelable implementation
	 */
	public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
		public Question createFromParcel(Parcel source) {
			return new Question(source);
		}

		public Question[] newArray(int size) {
			return new Question[size];
		}
	};

	private Question(Parcel parcel) {
		mId = parcel.readLong();
		mText = parcel.readString();

		mChoices = arrayToList(parcel.createStringArray());
		mAnswer = parcel.readInt();
	}

	public Question() {
		mId = 0;
		mText = "";
		mChoices = new ArrayList<String>();
		mAnswer = 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(mId);
		dest.writeString(mText);

		dest.writeStringArray(listToArray(mChoices));
		dest.writeInt(mAnswer);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public String getmText() {
		return mText;
	}

	public void setmText(String mText) {
		this.mText = mText;
	}

	public String[] getChoices() {
		return listToArray(mChoices);
	}

	public void addChoices(String choice) {
		mChoices.add(choice);
	}

	public void setmChoices(String[] mChoices) {
		this.mChoices = arrayToList(mChoices);
	}

	public int getmAnswer() {
		return mAnswer;
	}

	public void setmAnswer(int mAnswer) {
		this.mAnswer = mAnswer;
	}

	private String[] listToArray(ArrayList<String> arrayList) {

		String[] stockArr = new String[arrayList.size()];
		return arrayList.toArray(stockArr);

	}

	private ArrayList<String> arrayToList(String[] array) {

		return new ArrayList<String>(Arrays.asList(array));

	}

}