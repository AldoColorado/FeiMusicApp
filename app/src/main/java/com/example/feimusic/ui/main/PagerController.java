package com.example.feimusic.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.feimusic.AlbumsFragment;
import com.example.feimusic.ArtistsFragment;
import com.example.feimusic.LikesFragment;
import com.example.feimusic.PlaylistsFragment;

public class PagerController extends FragmentPagerAdapter {
    int numOfTabs;

    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LikesFragment();
            case 1:
                return new PlaylistsFragment();
            case 2:
                return new AlbumsFragment();
            case 3:
                return new ArtistsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
