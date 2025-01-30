package com.mozhimen.audiok.ffmpeg

/**
 * @ClassName UtilKVerifyFormat
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/11/10 23:57
 * @Version 1.0
 */
object UtilKVerifyFormat {
    @JvmStatic
    fun isSpeexValid(filePathWithName: String): Boolean =
        filePathWithName.endsWith("spx") || filePathWithName.endsWith("speex")

    @JvmStatic
    fun isMp3Valid(filePathWithName: String): Boolean =
        filePathWithName.endsWith("mp3")

    @JvmStatic
    fun isWavValid(filePathWithName: String): Boolean =
        filePathWithName.endsWith("wav")

    @JvmStatic
    fun isAmrValid(filePathWithName: String): Boolean =
        filePathWithName.endsWith("amr")
}