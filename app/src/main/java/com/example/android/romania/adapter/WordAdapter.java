
package com.example.android.romania.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.romania.R;
import com.example.android.romania.fragments.ColorsFragment;
import com.example.android.romania.fragments.FamilyFragment;
import com.example.android.romania.fragments.NumbersFragment;
import com.example.android.romania.fragments.PhrasesFragment;
import com.example.android.romania.model.Word;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>  {

    private int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView romaniaTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        romaniaTextView.setText(currentWord.getRomaniaTranslationId());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);

        defaultTextView.setText(currentWord.getDefaultTranslationId());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }

    public static class CategoryAdapter extends FragmentPagerAdapter {
        private Context mContext;


        public CategoryAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new NumbersFragment();
            } else if (position == 1) {
                return new FamilyFragment();
            } else if (position == 2) {
                return new ColorsFragment();
            } else {
                return new PhrasesFragment();
            }
        }


        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return mContext.getString(R.string.category_numbers);
            } else if (position == 1) {
                return mContext.getString(R.string.category_family);
            } else if (position == 2) {
                return mContext.getString(R.string.category_colors);
            } else {
                return mContext.getString(R.string.category_phrases);
            }
        }
    }
}