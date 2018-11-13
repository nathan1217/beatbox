package com.example.demo.beatbox

import android.databinding.BaseObservable
import android.databinding.Bindable

class SoundViewModel(private val mBeatBox: BeatBox) : BaseObservable() {
    private var _mSound: Sound? = null
    fun getSound(): Sound? {
        return _mSound
    }

    fun setSound(value: Sound) {
        _mSound = value
        notifyChange()
    }

    @Bindable
    fun getTitle(): String {
        return _mSound!!.name
    }

    fun onButtonClicked() {
        mBeatBox.play(_mSound!!)
    }
}