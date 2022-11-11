package com.mozhimen.audiok_ffmpeg

import androidx.appcompat.app.AppCompatActivity
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
    private val _speexFilePath: String by lazy { this.filesDir.absolutePath + "/audiok_ffmpeg/test1.spx" }
    private val _mp3FilePath: String by lazy { this.filesDir.absolutePath + "/audiok_ffmpeg/test1.mp3" }
    override fun initData(savedInstanceState: Bundle?) {
        //拷贝
        vb.btnCopySpeex.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (UtilKFile.isFileExist(_speexFilePath)) {
                    "SPEEX已经拷贝到目的地址".showToast()
                    return@launch
                }
                val res: String
                withContext(Dispatchers.IO) {
                    res = UtilKAsset.asset2File("Test1.spx", _speexFilePath)
                }
                "拷贝完成 res: $res".showToast()
            }
        }

        //speex转MP3
        vb.btnSpeexToMp3.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (!UtilKFile.isFileExist(_speexFilePath)) {
                    "speex源文件不存在".showToast()
                    return@launch
                }
                if (UtilKFile.isFileExist(_mp3FilePath)) {
                    "转换后的mp3文件已经存在".showToast()
                    return@launch
                }
                val res: Boolean
                withContext(Dispatchers.IO) {
                    res = AudioKTrans.speex2mp3(_speexFilePath, _mp3FilePath)
                }
                "speex转MP3完成 res: $res".showToast()
            }
        }

        //播放
        vb.btnPlay.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (!UtilKFile.isFileExist(_mp3FilePath)) {
                    "转化后待播放的MP3文件不存在".showToast()
                    return@launch
                }
                AudioK.instance.addAudioToPlayList(MAudioK("01", _mp3FilePath))
            }
        }
    }
}