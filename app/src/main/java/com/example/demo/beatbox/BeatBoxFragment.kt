package com.example.demo.beatbox

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demo.beatbox.databinding.FragmentBeatBoxBinding
import android.support.v7.widget.GridLayoutManager
import com.example.demo.beatbox.databinding.ListItemSoundBinding
import android.support.v7.widget.RecyclerView


class BeatBoxFragment : Fragment() {
    private var mBeatBox: BeatBox? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        mBeatBox = BeatBox(activity!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding: FragmentBeatBoxBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(activity!!, 3)
        binding.recyclerView.adapter = SoundAdapter(mBeatBox!!.getSounds())
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBeatBox!!.release()
    }
    private inner class SoundHolder constructor(private val mBinding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        init {
            mBinding.viewModel = SoundViewModel(mBeatBox!!)
        }

        fun bind(sound: Sound) {
            mBinding.viewModel!!.setSound(sound)
            mBinding.executePendingBindings()
        }
    }

    private inner class SoundAdapter constructor(private val mSounds: List<Sound>) :
        RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val inflater = LayoutInflater.from(activity)
            val binding =
                DataBindingUtil.inflate<ListItemSoundBinding>(inflater, R.layout.list_item_sound, parent, false)
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = mSounds[position]
            holder.bind(sound)
        }

        override fun getItemCount(): Int {
            return mSounds.size
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return BeatBoxFragment()
        }
    }
}