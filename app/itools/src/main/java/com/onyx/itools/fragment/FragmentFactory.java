package com.onyx.itools.fragment;

import android.os.Bundle;

import com.onyx.itools.base.BaseFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/30 0030,0:39
 * @Version: V1.0
 * @Description: TODO
 */
public class FragmentFactory {
    public static final int MAIN_FRAGMENT = 1;
    public static final int APP_FRAGMENT = 2;
    public static final int FILE_FRAGMENT = 3;
    public static final int SETTINGS_FRAGMENT = 4;
    public static final int READER_FRAGMENT = 5;
    public static final int WEB_FRAGMENT = 6;
    public static final int CUSTOM_FRAGMENT = 7;

    private static Map<Integer, BaseFragment> mFragmentCache =
            new HashMap<Integer, BaseFragment>();

    public static BaseFragment newFragment(int position, List<String> menuLists) {

        BaseFragment fragment = mFragmentCache.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case MAIN_FRAGMENT:
                fragment = new MainFragment();
                break;
            case APP_FRAGMENT:
                fragment = new AppFragment();
                break;
            case FILE_FRAGMENT:
                fragment = new FileFragment();
                break;
            case SETTINGS_FRAGMENT:
                fragment = new SettingsFragment();
                break;
            case READER_FRAGMENT:
                fragment = new ReaderFragment();
                break;
            case WEB_FRAGMENT:
                fragment = new ExplorerbFragment();
                break;
            case CUSTOM_FRAGMENT:
                fragment = new CustomFragment();
                break;
        }
        Bundle b=new Bundle();
        b.putString("text",menuLists.get(position-1));
        fragment.setArguments(b);
        mFragmentCache.put(position, fragment);
        return fragment;
    }
}
