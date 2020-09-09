package com.stiehl.testfcm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stiehl.testfcm.model.Message
import kotlinx.android.synthetic.main.fragment_show_message.view.*

class ShowMessageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_message, container, false)

        val message = arguments?.getSerializable("message") as Message

        view.txtMessageFrom.text = message.fromName
        view.txtMessageTitle.text = message.title
        view.txtMessageBody.text = message.body

        return view
    }

}