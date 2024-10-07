package com.mozhimen.audiok.ffmpeg.test

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.mozhimen.audiok.ffmpeg.AudioKTrans
import com.mozhimen.audiok.ffmpeg.test.databinding.ActivityMainBinding
import com.mozhimen.kotlin.elemk.androidx.appcompat.bases.databinding.BaseActivityVDB
import com.mozhimen.kotlin.utilk.android.widget.showToast
import com.mozhimen.kotlin.utilk.kotlin.UtilKStrAsset
import com.mozhimen.kotlin.utilk.kotlin.UtilKStrFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.mozhimen.mediak.audio.MediaKAudio
import com.mozhimen.mediak.audio.mos.MAudioKInfo
import kotlinx.coroutines.withContext

class MainActivity : BaseActivityVDB<ActivityMainBinding>() {
    private val _amrFilePath: String by lazy { this.filesDir.absolutePath + "/audiok_ffmpeg/test.amr" }
    private val _wavFilePath: String by lazy { this.filesDir.absolutePath + "/audiok_ffmpeg/test.wav" }
    private val _speexFilePath: String by lazy { this.filesDir.absolutePath + "/audiok_ffmpeg/test.spx" }
    private val _wavFilePath1: String by lazy { this.filesDir.absolutePath + "/audiok_ffmpeg/test1.wav" }
    override fun initView(savedInstanceState: Bundle?) {
        //拷贝
        vdb.btnCopy.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (UtilKStrFile.isFileExist(_amrFilePath)) {
                    "AMR已经拷贝到目的地址".showToast()
                    return@launch
                }
                val res: String
                withContext(Dispatchers.IO) {
                    res = UtilKStrAsset.strAssetName2strFilePathName("test.amr", _amrFilePath)
                }
                "拷贝完成 res: $res".showToast()
            }
        }

        //speex转MP3
        vdb.btnAmrToWav.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (!UtilKStrFile.isFileExist(_amrFilePath)) {
                    "amr源文件不存在".showToast()
                    return@launch
                }
                if (UtilKStrFile.isFileExist(_wavFilePath)) {
                    "转换后的amr文件已经存在".showToast()
                    return@launch
                }
                val res: Boolean
                withContext(Dispatchers.IO) {
                    res = AudioKTrans.amr2wav(_amrFilePath, _wavFilePath)
                }
                "amr转wav完成 res: $res".showToast()
            }
        }

        //播放
        vdb.btnPlay.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (!UtilKStrFile.isFileExist(_wavFilePath)) {
                    "转化后待播放的wav文件不存在".showToast()
                    return@launch
                }
                MediaKAudio.instance.addAudioToPlayList(MAudioKInfo("01", _wavFilePath,0))
            }
        }

        //拷贝
        vdb.btnCopySpeex.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (UtilKStrFile.isFileExist(_speexFilePath)) {
                    "speex已经拷贝到目的地址".showToast()
                    return@launch
                }
                val res: String
                withContext(Dispatchers.IO) {
                    res = UtilKStrAsset.strAssetName2strFilePathName("test.spx", _speexFilePath)
                }
                "拷贝完成 res: $res".showToast()
            }
        }

        //speex转MP3
        vdb.btnSpeexToWav.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (!UtilKStrFile.isFileExist(_speexFilePath)) {
                    "speex源文件不存在".showToast()
                    return@launch
                }
                if (UtilKStrFile.isFileExist(_wavFilePath1)) {
                    "转换后的wav文件已经存在".showToast()
                    return@launch
                }
                val res: Boolean
                withContext(Dispatchers.IO) {
                    res = AudioKTrans.speex2wav(_speexFilePath, _wavFilePath1)
                }
                "speex转wav完成 res: $res".showToast()
            }
        }

        //播放
        vdb.btnPlaySpeex.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (!UtilKStrFile.isFileExist(_wavFilePath1)) {
                    "转化后待播放的wav文件不存在".showToast()
                    return@launch
                }
                MediaKAudio.instance.addAudioToPlayList(MAudioKInfo("02", _wavFilePath1,0))
            }
        }
    }
}