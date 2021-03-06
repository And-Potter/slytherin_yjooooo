package com.yjooooo.sopt28th.ui.userinfo.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.yjooooo.sopt28th.R
import com.yjooooo.sopt28th.data.model.UserAuthStorage
import com.yjooooo.sopt28th.databinding.FragmentUserBinding
import com.yjooooo.sopt28th.ui.base.BindingFragment
import com.yjooooo.sopt28th.ui.userinfo.viewmodel.UserInfoViewModel

class UserFragment : BindingFragment<FragmentUserBinding>(R.layout.fragment_user) {
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.userInfoViewModel = userInfoViewModel
        binding.lifecycleOwner = this
        userInfoViewModel.getUserInfo()
        setOnLogOutBtnClick()
        setOnOrganizationBtnClick()
        setOnFollowBtnClick()
        return binding.root
    }

    private fun setOnLogOutBtnClick() {
        binding.btnUserLogout.setOnClickListener {
            UserAuthStorage.clearData()
            userInfoViewModel.setIsLogout(true)
        }
    }

    private fun setOnOrganizationBtnClick() {
        binding.btnUserOrganization.setOnClickListener {
            startActivity(Intent(requireContext(), UserOrganizationActivity::class.java))
        }
    }

    private fun setOnFollowBtnClick() {
        binding.btnUserFollowing.setOnClickListener {
            startActivity(Intent(requireContext(), UserFollowActivity::class.java))
        }
    }
}