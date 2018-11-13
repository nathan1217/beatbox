package com.example.demo.beatbox

class Sound(val assetPath: String) {
    val name: String
    var soundId: Int? = null

    init {
        val components = assetPath.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val filename = components[components.size - 1]
        name = filename.replace(".wav", "")
    }
}