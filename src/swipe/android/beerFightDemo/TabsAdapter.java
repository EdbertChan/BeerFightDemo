package swipe.android.beerFightDemo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class TabsAdapter extends FragmentStatePagerAdapter implements
		ActionBar.TabListener, ViewPager.OnPageChangeListener {

	/** The sherlock fragment activity. */
	private final FragmentActivity mActivity;

	/** The action bar. */
	private final ActionBar mActionBar;

	/** The pager. */
	private final ViewPager mPager;

	/** The tabs. */
	private List<TabInfo> mTabs = new LinkedList<TabInfo>();

	/** The total number of tabs. */
	private int TOTAL_TABS;

public static final int CHANGE_POSITION = 1;
	public TabsAdapter(FragmentActivity activity, ViewPager pager) {
		super(activity.getSupportFragmentManager());
		activity.getSupportFragmentManager();
		this.mActivity = activity;
		this.mActionBar = activity.getActionBar();
		this.mPager = pager;
		mPager.setOnPageChangeListener(this);
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	public void addTab(String title,  Fragment f) {
		final TabInfo tabInfo = new TabInfo(f, title);
		final ActionBar.Tab tab = mActionBar.newTab().setText(title);
		tab.setTabListener(this);
		tab.setTag(tabInfo);

		mTabs.add(tabInfo);
		mActionBar.addTab(tab);

		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(final int position) {
		final TabInfo tabInfo = mTabs.get(position);
		return tabInfo.f;
	}

	@Override
	public int getItemPosition(final Object object) {
		/* Get the current position. */
		int position = mActionBar.getSelectedTab().getPosition();

		/* The default value. */
		int pos = POSITION_NONE;
		
		return pos;
	}

	@Override
	public int getCount() {
		return mTabs.size();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int position) {
		mActionBar.setSelectedNavigationItem(position);
	}

	@Override
	public void onTabSelected(final ActionBar.Tab tab,
			final FragmentTransaction ft) {
		TabInfo tabInfo = (TabInfo) tab.getTag();
		for (int i = 0; i < mTabs.size(); i++) {
			if (mTabs.get(i).equals(tabInfo)) {
				mPager.setCurrentItem(i);
			}
		}
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
	}

	public void replace(final int position, final Fragment f) {
		/* Save the fragment to the history. */
	mActivity.getSupportFragmentManager().beginTransaction()
				.addToBackStack(null).commit();

		/* Update the tabs. */
		
		TabInfo i = (TabInfo) mActionBar.getTabAt(position).getTag();
		
		updateTabs(new TabInfo(f,  i.title), position);

		/* Updates the history. */
	

		notifyDataSetChanged();
	}

	/**
	 * Updates the tabs.
	 * 
	 * @param tabInfo
	 *            the new tab info
	 * @param position
	 *            the position
	 */
	private void updateTabs(final TabInfo tabInfo, final int position) {
		mTabs.remove(position);
		mTabs.add(position, tabInfo);
		mActionBar.getTabAt(position).setTag(tabInfo);
	}



	/** The tab info class */
	private static class TabInfo {

	public String title;
public Fragment f;

		public TabInfo(Fragment f, String title) {
			this.f = f;
			this.title = title;
		}

		@Override
		public boolean equals(final Object o) {
			TabInfo info = (TabInfo) o;
			return title.equals(info.title);
		}

		
	}
}