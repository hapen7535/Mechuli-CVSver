package com.example.mechulicvs.ui.community

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mechulicvs.R
import com.example.mechulicvs.data.ApiState
import com.example.mechulicvs.data.remote.model.detailpost.RecipeRequest
import com.example.mechulicvs.databinding.FragmentWritePostBinding
import com.example.mechulicvs.di.MainApplication
import com.example.mechulicvs.ui.community.adapter.RecipeIngrAdapter
import com.example.mechulicvs.ui.viewmodel.community.RecipeCreateViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class WritePostFragment : Fragment() {

    lateinit var communityActivity: CommunityActivity

    private lateinit var recipeCreateViewModel: RecipeCreateViewModel

    private lateinit var recipeIngrAdapter: RecipeIngrAdapter

    private var _binding: FragmentWritePostBinding? = null
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

        val imagesList = mutableListOf<String>()
        val ingrList = mutableListOf<String>()

        recipeIngrAdapter =
            RecipeIngrAdapter(communityActivity, ingrList)

        binding.ingrListRv.adapter = recipeIngrAdapter

        val pickMultipleMedia =
            registerForActivityResult(
                ActivityResultContracts.PickMultipleVisualMedia(
                    MAX_IMAGE_NUMBER
                )
            ) { uris ->
                if (uris.isNotEmpty()) {
                    Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
                    Log.d("PhotoPicker", "uris : $uris")
                    for (i in uris) {
                        imagesList.add(i.toString())
                    }
                } else {
                    Log.d("PhotoPicker", "No media selected")
                }
            }

        binding.recipeIngrAddTv.setOnClickListener {
            val ingrToAdd = getChangedText(binding.recipeIngrEt.text)
            ingrList.plusAssign(ingrToAdd)
            Log.d("재료 추가", ingrToAdd)
            recipeIngrAdapter.notifyDataSetChanged()
        }

        binding.completePostBtn.setOnClickListener {
            val userId = MainApplication.prefs.getString("userId", "")
            val recipeTitle = getChangedText(binding.recipeTitleEt.text)
            val recipeCost = getChangedText(binding.recipeCostEt.text)
            val recipeDescription = getChangedText(binding.recipeContentsEt.text)
            val recipeIngr = ingrList.toString().removeSurrounding("[", "]")
            val recipeContent = RecipeRequest(
                id = userId,
                recipeTitle = recipeTitle,
                recipeIngr = recipeIngr,
                recipeCost = recipeCost.toInt(),
                recipeContent = recipeDescription,
                recipeImgUrl1 = imagesList[0],
                recipeImgUrl2 = imagesList[1],
                recipeImgUrl3 = imagesList[2],
                recipeImgUrl4 = imagesList[3],
                recipeImgUrl5 = imagesList[4]
            )
            recipeCreateViewModel.getRecipeCreateResult(recipeContent)
            recipeCreateViewModel.recipeResult.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is ApiState.Loading -> {
                        Log.d("Loading", it.toString())
                    }
                    is ApiState.Success -> {
                        Toast.makeText(communityActivity, "글 작성이 완료되었습니다.", Toast.LENGTH_LONG).show()
                        communityActivity.changeToMainCommunity()
                    }
                    is ApiState.Error -> {
                        Log.d("Error", it.msg.toString())
                    }
                }
            })
        }

        binding.recipePicturesTv.setOnClickListener {
            pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
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

    private fun getChangedText(inputContent: Editable): String {
        var content = ""
        if (inputContent.isNotEmpty()) {
            content = inputContent.toString()
            return content
        }
        return content
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MAX_IMAGE_NUMBER = 5
    }

}