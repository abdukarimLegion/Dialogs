package com.naa.uz.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import android.view.ViewGroup
import com.naa.uz.dialogs.databinding.FragmentDialogBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CustomDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDialogBinding.inflate(inflater, container, false)

        binding.save.setOnClickListener {
            val email: String = binding.ed1.text.toString()
            val password: String = binding.ed2.text.toString()

            if (onSaveClick != null) {
                onSaveClick?.onClick("$email $password")
                dismiss()
            }

            Log.d("AAAA", "onfragment: $email")
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var onSaveClick: OnSaveClick? = null

    interface OnSaveClick {
        fun onClick(str: String)
    }

    fun setOnSaveClick(onSaveClick: OnSaveClick) {
        this.onSaveClick = onSaveClick
    }
}