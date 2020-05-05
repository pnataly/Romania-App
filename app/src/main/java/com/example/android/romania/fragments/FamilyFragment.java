
package com.example.android.romania.fragments;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.romania.R;
import com.example.android.romania.adapter.WordAdapter;
import com.example.android.romania.model.Word;

import java.util.ArrayList;


public class FamilyFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    public FamilyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.string.family_father, R.string.ro_family_father,
                R.drawable.father, R.raw.father));
        words.add(new Word(R.string.family_mother, R.string.ro_family_mother,
                R.drawable.mother, R.raw.mother));
        words.add(new Word(R.string.family_son, R.string.ro_family_son,
                R.drawable.son, R.raw.sonn));
        words.add(new Word(R.string.family_daughter, R.string.ro_family_daughter,
                R.drawable.daugther, R.raw.dautherr));
        words.add(new Word(R.string.family_brother, R.string.ro_family_brother,
                R.drawable.brother, R.raw.brother));
        words.add(new Word(R.string.family_sister, R.string.ro_family_sister,
                R.drawable.sister, R.raw.sister));
        words.add(new Word(R.string.family_uncle, R.string.ro_family_uncle,
                R.drawable.uncle, R.raw.uncle));
        words.add(new Word(R.string.family_aunt, R.string.ro_family_aunt,
                R.drawable.aunt, R.raw.aunt));
        words.add(new Word(R.string.family_grandmother, R.string.ro_family_grandmother,
                R.drawable.grandmother, R.raw.grandmotherr));
        words.add(new Word(R.string.family_grandfather, R.string.ro_family_grandfather,
                R.drawable.grandfather, R.raw.grandfotherr));
        words.add(new Word(R.string.family_grandson, R.string.ro_family_grandson,
                R.drawable.grandson, R.raw.grandson));
        words.add(new Word(R.string.family_grandauther, R.string.ro_family_grandauther,
                R.drawable.granddaughter, R.raw.granddauther));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();
                Word word = words.get(position);
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {

            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
