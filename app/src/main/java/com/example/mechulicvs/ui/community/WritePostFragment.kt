package com.example.mechulicvs.ui.community

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mechulicvs.R
import com.example.mechulicvs.databinding.FragmentWritePostBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class WritePostFragment : Fragment() {

    lateinit var communityActivity: CommunityActivity

    private lateinit var pickMultipleMediaLauncher: ActivityResultLauncher<Intent>

    private var _binding : FragmentWritePostBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communityActivity = context as CommunityActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_write_post, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab = activity?.findViewById<FloatingActionButton>(R.id.write_post_btn)
        fab?.visibility = View.GONE

//        val pickMultipleMedia =
//            registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
//
//                if (uris.isNotEmpty()) {
//                    Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
//                } else {
//                    Log.d("PhotoPicker", "No media selected")
//                }
//            }

        pickMultipleMediaLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode != Activity.RESULT_OK) {
                    Toast.makeText(communityActivity, "Failed picking media.", Toast.LENGTH_SHORT).show()
                } else {
                    val uris = it.data?.clipData ?: return@registerForActivityResult
                    var uriPaths = ""
                    for (index in 0 until uris.itemCount) {
                        uriPaths += uris.getItemAt(index).uri.path
                        uriPaths += "\n"
                    }
                    showSnackBar("SUCCESS: $uriPaths")
                }
            }

        binding.recipePicturesTv.setOnClickListener {
//            pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
            pickMultipleMediaLauncher.launch(
                Intent(MediaStore.ACTION_PICK_IMAGES)
                    .apply {
                        type = "image/*"
                        putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, MAX_IMAGE_NUMBER)
                    }
            )
        }

    }

    private fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(
            communityActivity.findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG,
        )
        snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines =
            10
        snackBar.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val MAX_IMAGE_NUMBER = 5
    }

}