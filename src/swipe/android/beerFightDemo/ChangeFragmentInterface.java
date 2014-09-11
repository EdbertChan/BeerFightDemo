package swipe.android.beerFightDemo;

import android.support.v4.app.Fragment;

public interface ChangeFragmentInterface{
	public void changeFragment(int position, Fragment f);
	public void changeToNextFragment(Fragment currentFragment);
}