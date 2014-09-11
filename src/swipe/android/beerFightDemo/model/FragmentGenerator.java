package swipe.android.beerFightDemo.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import swipe.android.beerFightDemo.ChangeFragmentInterface;
import swipe.android.beerFightDemo.R;
import android.content.Context;
import android.sax.Element;
import android.support.v4.app.Fragment;

public class FragmentGenerator {
	static int i = 0;
	public static Fragment generateWordFragment(Context ctx, ChangeFragmentInterface changer) {
		String[] categories = ctx.getResources().getStringArray(
				R.array.words_array);
		int length = categories.length;
		Random randomGenerator = new Random();
		int randomPos = randomGenerator.nextInt(length);
		String real = categories[randomPos];
		String shuffle = shuffleString(categories[randomPos]);

		return WordFragment.newInstance(changer, real, shuffle, true);

	}

	public static Fragment generateQuestionFragment(Context ctx, ChangeFragmentInterface changer) {
		// parse all xml

		List<Question> aLQ = parseXML(ctx);

		// get the next question
		boolean last = false;
		if(i == aLQ.size() -1){
			last = true;
		}
		MultipleChoiceFragment mf =  MultipleChoiceFragment.newInstance(changer, aLQ.get(i), last);
		// if this is the last one, we reset the counter
		if(!last){
			i++;
		}else{
			i=0;
		}
		return mf;

	}

	private static String shuffleString(String input1) {
		String input = new String(input1);
		List<Character> characters = new ArrayList<Character>();
		for (char c : input.toCharArray()) {
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(input.length());
		while (characters.size() != 0) {
			int randPicker = (int) (Math.random() * characters.size());
			output.append(characters.remove(randPicker));
		}
		return output.toString();

	}

	public static List<Question> parseXML(Context ctx) {
		try {
			return parse(ctx.getAssets().open("quiz-questions.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<Question> parse(InputStream is) {
		Question employee = new Question();
		List<Question> employees = new ArrayList<Question>();
		String text = "";
		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			parser = factory.newPullParser();

			parser.setInput(is, null);

			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tagname = parser.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (tagname.equalsIgnoreCase("question")) {
						// create a new instance of employee
						employee = new Question();
					}
					break;

				case XmlPullParser.TEXT:
					text = parser.getText();
					break;

				case XmlPullParser.END_TAG:
					if (tagname.equalsIgnoreCase("question")) {
						// add employee object to list
						employees.add(employee);
					} else if (tagname.equalsIgnoreCase("text")) {
						employee.setmText(text);
					} else if (tagname.equalsIgnoreCase("answer")) {
						employee.setmAnswer(Integer.parseInt(text));
					} else if (tagname.equalsIgnoreCase("choice")) {
						employee.addChoices(text);
					}
					break;

				default:
					break;
				}
				eventType = parser.next();
			}

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return employees;
	}

	/*
	 * private static ArrayList<Question> parseXML(XmlPullParser parser) throws
	 * XmlPullParserException,IOException { ArrayList<Question> products = null;
	 * int eventType = parser.getEventType(); Question currentProduct = null;
	 * 
	 * while (eventType != XmlPullParser.END_DOCUMENT){ String name = null;
	 * switch (eventType){ case XmlPullParser.START_DOCUMENT: products = new
	 * ArrayList(); break; case XmlPullParser.START_TAG: name =
	 * parser.getName(); if (name == "question"){ currentProduct = new
	 * Question(); } else if (currentProduct != null){ if (name == "text"){
	 * currentProduct.setmText(parser.nextText()); } else if (name == "choice"){
	 * 
	 * currentProduct.addChoices(parser.nextText()); } else if (name ==
	 * "answer"){ currentProduct.setmAnswer(Integer.valueOf(parser.nextText()));
	 * } } break; case XmlPullParser.END_TAG: name = parser.getName(); if
	 * (name.equalsIgnoreCase("question") && currentProduct != null){
	 * products.add(currentProduct); } } eventType = parser.next(); }
	 * 
	 * return products; }
	 */
}