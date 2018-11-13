package com.example.demo.beatbox

import android.support.v4.app.Fragment

class BeatBoxActivity : BaseFragmentActivity() {
    override fun createFragment(): Fragment {
        return BeatBoxFragment.newInstance()
    }
}