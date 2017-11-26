package com.example.f0x.apibot.presentation.main

import android.content.pm.PackageManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.f0x.apibot.R
import com.example.f0x.apibot.app.AppController
import com.example.f0x.apibot.app.Const
import com.example.f0x.apibot.domain.models.ai.AIModel
import com.example.f0x.apibot.presentation.common.AListAdapter
import com.example.f0x.apibot.presentation.common.Layout
import com.example.f0x.apibot.presentation.common.activiites.ABaseListActivity
import com.example.f0x.apibot.presentation.common.activiites.ChatAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@Layout(id = R.layout.activity_main)
class MainActivity : ABaseListActivity<AIModel, AListAdapter.DefaultViewHolder<AIModel>>(), IMainView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    override val emptyViewText: Int
        get() = R.string.no_data_found

    @ProvidePresenter
    fun providePresenter() = presenter


    override fun initAdapter(): AListAdapter<AIModel, AListAdapter.DefaultViewHolder<AIModel>> {
        return ChatAdapter()
    }

    override fun initLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(this)
    }


    override fun initListeners() {
        btnMic.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                presenter.micOn()
            else
                presenter.micOff()
        }
        btnSend.setOnClickListener {
            val query = etQuery.text.toString()
//            etQuery.setText("")
            presenter.onSendClick(query)
        }
    }

    override fun inject() {
        AppController.injectInMain(this)
        presenter.context =this

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == Const.REQUEST_RECORD_AUDIO_PERMISSION) {
            presenter.isAudioAccepted = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun disableMic() {
        btnMic.isChecked = false

    }

}
