package com.example.mechulicvs.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mechulicvs.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CommentEditBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_comment_edit_bottom_sheet, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}

