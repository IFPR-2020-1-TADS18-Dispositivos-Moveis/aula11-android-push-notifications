package com.stiehl.testfcm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stiehl.testfcm.adapter.UserAdapter
import com.stiehl.testfcm.api.inputs.MessageInput
import com.stiehl.testfcm.api.results.MessageResult
import com.stiehl.testfcm.util.FirebaseServiceGenerator
import com.stiehl.testfcm.util.LocalPersistence
import kotlinx.android.synthetic.main.fragment_message.view.*
import kotlinx.android.synthetic.main.fragment_message.view.txtTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageFragment : Fragment() {
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message, container, false)

        adapter = UserAdapter()
        view.listUsers.adapter = adapter
        view.listUsers.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        view.btSend.setOnClickListener {
            val title = view.txtTitle.text.toString()
            val body = view.txtBody.text.toString()

            sendMessage(title, body)
        }

        return view
    }

    private fun sendMessage(title: String, body: String) {
        val from = LocalPersistence(requireActivity()).userId!!
        val to = adapter.selectedUsers.map { user -> user.id }

        val service = FirebaseServiceGenerator.getService()
        service.sendMessage(MessageInput(from, to, title, body))
            .enqueue(object : Callback<MessageResult> {
                override fun onFailure(call: Call<MessageResult>, t: Throwable) {}

                override fun onResponse(
                    call: Call<MessageResult>,
                    response: Response<MessageResult>
                ) {
                    val result = response.body()!!
                    if (result.status == "success") {
                        Toast.makeText(activity, result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

}