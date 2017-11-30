package com.example.f0x.apibot.presentation.main

import android.content.pm.PackageManager
import android.graphics.Canvas
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.f0x.apibot.R
import com.example.f0x.apibot.app.AppController
import com.example.f0x.apibot.app.Const
import com.example.f0x.apibot.domain.models.ai.chat.ChatMessage
import com.example.f0x.apibot.presentation.common.AListAdapter
import com.example.f0x.apibot.presentation.common.Layout
import com.example.f0x.apibot.presentation.common.activiites.ABaseListActivity
import kotlinx.android.synthetic.main.activity_chat.*
import javax.inject.Inject


@Layout(id = R.layout.activity_chat)
class ChatActivity : ABaseListActivity<ChatMessage, AListAdapter.DefaultViewHolder<ChatMessage>>(), IChatView {


    @Inject
    @InjectPresenter
    lateinit var presenter: ChatPresenter
    var menu: Menu? = null

    override val emptyViewText: Int
        get() = R.string.no_data_found

    override val dividerDecoration: DividerItemDecoration
        get() = object : DividerItemDecoration(this, LinearLayoutManager.VERTICAL) {
            override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {}
        }

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat_menu, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.clear_messages) {
            presenter.clearChat()
            return true
        }
        if (item.itemId == R.id.mute) {
            presenter.onMuteClick()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    override fun initAdapter(): AListAdapter<ChatMessage, AListAdapter.DefaultViewHolder<ChatMessage>> {
        val chatAdapter = ChatAdapter()
        chatAdapter.onDataClickListener = { presenter.onMessageClick(it) }
        return chatAdapter
    }

    override fun initLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(this)
    }


    override fun initListeners() {

        btnMic.setOnTouchListener { v, event ->
            if (v.id != R.id.btnMic)
                return@setOnTouchListener false

            if (!btnMic.isEnabled)
                return@setOnTouchListener false

            if (event.action == MotionEvent.ACTION_DOWN) {
                presenter.micOn()
                return@setOnTouchListener true
            }

            if (event.action == MotionEvent.ACTION_UP) {
                presenter.micOff()
                return@setOnTouchListener true
            }
            return@setOnTouchListener false
        }

        btnSend.setOnClickListener {
            val query = etQuery.text.toString()

            if (TextUtils.isEmpty(query))
                return@setOnClickListener

            etQuery.setText("")
            presenter.onSendClick(query)
        }
    }

    override fun inject() {
        AppController.injectInMain(this)
        presenter.context = this
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == Const.REQUEST_RECORD_AUDIO_PERMISSION) {
            presenter.isAudioAccepted = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun addMessage(chatMessage: ChatMessage) {
        adapter.addItem(chatMessage)
        recyclerView.scrollToPosition(adapter.itemCount - 1)
    }

    override fun unMuteMenuItem() {
        menu?.findItem(R.id.mute)?.setTitle(R.string.sound_enabled)

    }

    override fun muteMenuItem() {
        menu?.findItem(R.id.mute)?.setTitle(R.string.sound_disabled)
    }

    override fun clearChat() {
        adapter.clearData()
    }

}
