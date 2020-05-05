
package com.example.android.romania.model;

public class Word {

    private int mDefaultTranslationId;
    private int mRomaniaTranslationId;
    private int mAudioResourceId;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    
    public Word(int defaultTranslationId, int romaniaTranslationId, int audioResourceId) {
        mDefaultTranslationId = defaultTranslationId;
        mRomaniaTranslationId = romaniaTranslationId;
        mAudioResourceId = audioResourceId;
    }
    
    public Word(int defaultTranslationId, int romaniaTranslationId, int imageResourceId,
                int audioResourceId) {
        mDefaultTranslationId = defaultTranslationId;
        mRomaniaTranslationId = romaniaTranslationId;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }
    
    public int getDefaultTranslationId() {
        return mDefaultTranslationId;
    }
    
    public int getRomaniaTranslationId() {
        return mRomaniaTranslationId;
    }
    
    public int getImageResourceId() {
        return mImageResourceId;
    }
    
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
    
    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}