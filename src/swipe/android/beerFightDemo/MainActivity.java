package swipe.android.beerFightDemo;

import swipe.android.beerFightDemo.model.FragmentGenerator;
import swipe.android.beerFightDemo.model.MultipleChoiceFragment;
import swipe.android.beerFightDemo.model.QuestionFragment;
import swipe.android.beerFightDemo.model.SurveyFragment;
import swipe.android.beerFightDemo.model.WordFragment;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class MainActivity extends FragmentActivity implements
		ChangeFragmentInterface {
	/* Beer Fight vars */
	ViewPager mViewPager;
	TabsAdapter mAppSectionsPagerAdapter;

	private static Context context;
	private static Activity activity;
	/* End Beer Fight vars */

	/*
	 * UI components
	 */

	private FragmentManager mFragmentManager;
	private ActionBar actionBar;

	/**
	 * Override Activity lifecycle method.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		activity = this;
		setContentView(R.layout.activity_main);

		mFragmentManager = getFragmentManager();

		actionBar = getActionBar();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setOffscreenPageLimit(2);
		mAppSectionsPagerAdapter = new TabsAdapter(this, mViewPager);
		
		
		//keep the above in that order.
		mAppSectionsPagerAdapter.addTab("0", new Fragment1());
		mAppSectionsPagerAdapter.addTab("1", new Fragment2(this));
		mAppSectionsPagerAdapter.addTab("2", new Fragment3());
	
		mViewPager.setAdapter(mAppSectionsPagerAdapter);

		// mAppSectionsPagerAdapter.replaceTab(1,new Fragment1(), null);
	}// onCreate

	@Override
	public void changeFragment(int position, Fragment f) {
		//mAppSectionsPagerAdapter.replaceTab(1, f, null);
		mAppSectionsPagerAdapter.replace(1,f);
	}

	@Override
	public void changeToNextFragment(Fragment currentFragment) {
		Fragment changeTo;
		if (currentFragment instanceof QuestionFragment) {
			QuestionFragment qf = (QuestionFragment) currentFragment;
			if(qf.isLast()){
				changeTo = new SurveyFragment(this);
			}
			else if (currentFragment instanceof WordFragment){
				changeTo =  FragmentGenerator.generateWordFragment(this, this);
			}else{
				changeTo =  FragmentGenerator.generateQuestionFragment(this, this);
			}
		} else {
			// assume we're done
			changeTo = new Fragment2(this);
		}
		changeFragment(1, changeTo);

	}
}