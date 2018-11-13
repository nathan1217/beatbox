package com.example.demo.beatbox

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import java.io.IOException
import android.media.AudioManager
import android.media.SoundPool

open class BeatBox(context: Context) {
    private val mSounds = ArrayList<Sound>()
    private val TAG = "BeatBox"
    private val SOUNDS_FOLDER = "sample_sounds"
    private val MAX_SOUNDS = 5
    private var mAssets: AssetManager? = context.assets
    private var mSoundPool: SoundPool = SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0)

    private fun loadSounds() {
        val soundNames: Array<String>
        try {
            soundNames = mAssets!!.list(SOUNDS_FOLDER)
            Log.i(TAG, "Found ${soundNames.size} sounds")
        } catch (ioe: IOException) {
            Log.e(TAG, "Could not list assets", ioe)
            return
        }
        for (filename in soundNames) {
            try {

                val assetPath = "$SOUNDS_FOLDER/$filename"
                val sound = Sound(assetPath)
                load(sound)
                mSounds.add(sound)
            } catch (ioe: IOException) {
                Log.e(TAG, "Could not load sound $filename", ioe)
            }
        }
    }

    fun getSounds(): List<Sound> {
        return mSounds
    }

    @Throws(IOException::class)
    private fun load(sound: Sound) {
        val afd = mAssets!!.openFd(sound.assetPath)
        val soundId = mSoundPool.load(afd, 1)
        sound.soundId = soundId
    }

    fun play(sound: Sound) {
        val soundId = sound.soundId ?: return
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    init {
        loadSounds()
    }

    fun release() {
        mSoundPool.release()
    }
}