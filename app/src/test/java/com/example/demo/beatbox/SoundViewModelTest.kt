package com.example.demo.beatbox

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SoundViewModelTest {

    private lateinit var mBeatBox: BeatBox

    private lateinit var mSound: Sound

    private lateinit var mSubject: SoundViewModel

    @Before
    fun setUp() {
        mBeatBox = mock(BeatBox::class.java)
        mSound = Sound("assetPath")
        mSubject = SoundViewModel(mBeatBox)
        mSubject.setSound(mSound)
    }

    @Test
    fun exposesSoundNameAsTitle() {
        assertThat(mSubject.getTitle(), `is`(mSound.name))
    }

    @Test
    fun callsBeatBoxPlayOnButtonClicked() {
        mSubject.onButtonClicked()
        verify(mBeatBox).play(mSound)
    }
}