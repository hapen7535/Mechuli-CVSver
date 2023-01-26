package com.example.mechulicvs.ui.community

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.FragmentPostingEditBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var communityActivity: CommunityActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communityActivity = context as CommunityActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posting_edit_bottom_sheet, container, false)
        val editBtn = view.findViewById<ConstraintLayout>(R.id.edit_layout)
        val deleteBtn = view.findViewById<ConstraintLayout>(R.id.delete_layout)

        editBtn.setOnClickListener {
            //레시피 상세 정보를 intent로 불러온다
            communityActivity.changeToDetail()
            //레시피 상세 정보도 intent로 전달
        }

        deleteBtn.setOnClickListener {

        }

        return view
    }



    companion object {
        const val TAG = "ModalBottomSheet"
    }
}