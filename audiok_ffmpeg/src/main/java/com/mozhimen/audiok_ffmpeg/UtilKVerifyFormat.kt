package com.mozhimen.audiok_ffmpeg

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
        filePathWithName.endsWith("spx")

    @JvmStatic
    fun isMp3Valid(filePathWithName: String): Boolean =
        filePathWithName.endsWith("mp3")

    @JvmStatic
    fun isWavValid(filePathWithName: String): Boolean =
        filePathWithName.endsWith("wav")
}