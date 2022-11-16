package com.mozhimen.audiok_ffmpeg

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.mozhimen.audiok_ffmpeg.databinding.ActivityMainBinding
import com.mozhimen.basick.basek.BaseKActivityVB
import com.mozhimen.basick.extsk.showToast
import com.mozhimen.basick.utilk.UtilKAsset
import com.mozhimen.basick.utilk.UtilKFile
import com.mozhimen.componentk.audiok.AudioK
import com.mozhimen.componentk.audiok.mos.MAudioK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseKActivityVB<ActivityMainBinding>() {
    private val _amrFilePath: String by lazy { this.filesDir.absolutePath + "/audiok_ffmpeg/test.amr" }
    private val _wavFilePath: String by lazy { this.filesDir.absolutePath + "/audiok_ffmpeg/test.wav" }
    private val _speexFilePath: String by lazy { this.filesDir.absolutePath + "/audiok_ffmpeg/test.spx" }
    private val _wavFilePath1: String by lazy { this.filesDir.absolutePath + "/audiok_ffmpeg/test1.wav" }
    override fun initData(savedInstanceState: Bundle?) {
        //拷贝
        vb.btnCopy.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (UtilKFile.isFileExist(_amrFilePath)) {
                    "AMR已经拷贝到目的地址".showToast()
                    return@launch
                }
                val res: String
                withContext(Dispatchers.IO) {
                    res = UtilKAsset.asset2File("test.amr", _amrFilePath)
                }
                "拷贝完成 res: $res".showToast()
            }
        }

        //speex转MP3
        vb.btnAmrToWav.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (!UtilKFile.isFileExist(_amrFilePath)) {
                    "amr源文件不存在".showToast()
                    return@launch
                }
                if (UtilKFile.isFileExist(_wavFilePath)) {
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
        vb.btnPlay.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (!UtilKFile.isFileExist(_wavFilePath)) {
                    "转化后待播放的wav文件不存在".showToast()
                    return@launch
                }
                AudioK.instance.addAudioToPlayList(MAudioK("01", _wavFilePath))
            }
        }

        //拷贝
        vb.btnCopySpeex.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (UtilKFile.isFileExist(_speexFilePath)) {
                    "speex已经拷贝到目的地址".showToast()
                    return@launch
                }
                val res: String
                withContext(Dispatchers.IO) {
                    res = UtilKAsset.asset2File("test.spx", _speexFilePath)
                }
                "拷贝完成 res: $res".showToast()
            }
        }

        //speex转MP3
        vb.btnSpeexToWav.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (!UtilKFile.isFileExist(_speexFilePath)) {
                    "speex源文件不存在".showToast()
                    return@launch
                }
                if (UtilKFile.isFileExist(_wavFilePath1)) {
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
        vb.btnPlaySpeex.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (!UtilKFile.isFileExist(_wavFilePath1)) {
                    "转化后待播放的wav文件不存在".showToast()
                    return@launch
                }
                AudioK.instance.addAudioToPlayList(MAudioK("02", _wavFilePath1))
            }
        }
    }
}