package com.mozhimen.audiok_ffmpeg

import android.util.Log
import com.arthenica.mobileffmpeg.Config
import com.arthenica.mobileffmpeg.FFmpeg
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * @ClassName AudioKTrans
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/11/10 23:42
 * @Version 1.0
 */
object AudioKTrans {
    private const val TAG = "AudioKTrans>>>>>"

    @JvmStatic
    suspend fun speex2mp3(sourcePathWithName: String, destPathWithName: String): Boolean {
        return if (sourcePathWithName.isSpeexValid() && destPathWithName.isMp3Valid())
            trans(sourcePathWithName, destPathWithName)
        else {
            Log.e(TAG, "speex2mp3: 文件格式不正确")
            false
        }
    }

    @JvmStatic
    suspend fun mp32speex(sourcePathWithName: String, destPathWithName: String): Boolean {
        return if (sourcePathWithName.isMp3Valid() && destPathWithName.isSpeexValid())
            trans(sourcePathWithName, destPathWithName)
        else {
            Log.e(TAG, "speex2mp3: 文件格式不正确")
            false
        }
    }

    @JvmStatic
    suspend fun speex2wav(sourcePathWithName: String, destPathWithName: String): Boolean {
        return if (sourcePathWithName.isSpeexValid() && destPathWithName.isWavValid())
            trans(sourcePathWithName, destPathWithName)
        else {
            Log.e(TAG, "speex2mp3: 文件格式不正确")
            false
        }
    }

    @JvmStatic
    suspend fun wav2speex(sourcePathWithName: String, destPathWithName: String): Boolean {
        return if (sourcePathWithName.isWavValid() && destPathWithName.isSpeexValid())
            trans(sourcePathWithName, destPathWithName)
        else {
            Log.e(TAG, "speex2mp3: 文件格式不正确")
            false
        }
    }

    @JvmStatic
    suspend fun amr2wav(sourcePathWithName: String, destPathWithName: String): Boolean {
        return if (sourcePathWithName.isAmrValid() && destPathWithName.isWavValid())
            trans(sourcePathWithName, destPathWithName)
        else {
            Log.e(TAG, "speex2mp3: 文件格式不正确")
            false
        }
    }

    suspend fun trans(sourcePathWithName: String, destPathWithName: String): Boolean = suspendCancellableCoroutine { coroutine ->
        FFmpeg.executeAsync("-i $sourcePathWithName $destPathWithName") { _, returnCode ->
            when (returnCode) {
                Config.RETURN_CODE_SUCCESS -> {
                    Log.i(TAG, "trans command completed successfully")
                    coroutine.resume(true)
                }
                Config.RETURN_CODE_CANCEL -> {
                    Log.w(TAG, "trans command cancelled by user")
                    coroutine.resume(false)
                }
                else -> {
                    Log.e(TAG, "trans command failed with returnCode=$returnCode")
                    coroutine.resume(false)
                }
            }
        }
    }
}