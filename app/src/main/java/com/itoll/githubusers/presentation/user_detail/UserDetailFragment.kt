package com.itoll.githubusers.presentation.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itoll.githubusers.base.BaseFragment
import com.itoll.githubusers.common.ImageLoader
import com.itoll.githubusers.common.convertToK
import com.itoll.githubusers.common.toUserName
import com.itoll.githubusers.databinding.FragmentUserDetailBinding
import com.itoll.githubusers.presentation.adapter.UserAdapter
import com.itoll.githubusers.presentation.user_list.UserListFragmentDirections
import com.itoll.githubusers.presentation.user_list.UsersState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailFragment : BaseFragment() {

    private val viewModel: UserDetailViewModel by viewModel()
    lateinit var binding: FragmentUserDetailBinding
    private var userName: String? = null
    private var userListAdapter: UserAdapter? = null
    private val args: UserDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!this::binding.isInitialized)
            binding = FragmentUserDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun initViews() {
        userName = args.userName
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        userListAdapter = UserAdapter { user ->
            findNavController().navigate(
                UserDetailFragmentDirections.actionUserDetailFragmentSelf(
                    user.userName
                )
            )
        }
        binding.userListRv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = userListAdapter
        }
    }

    override fun clickViews() {
    }

    override fun executeInitTask() {
        userName?.let {
            viewModel.getUserDetail(it)
            viewModel.getFollowers(it)
        }
    }

    override fun observes() {
        lifecycleScope.launch {
            viewModel.userState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when (state) {
                        is UserDetailState.User -> {
                            state.userDetail?.apply {
                                ImageLoader.load(
                                    context,
                                    avatarUrl,
                                    binding.avatarImg,
                                    isCircle = true,
                                )
                                binding.NameTxt.text = name
                                binding.userNameTxt.text = userName.toUserName()
                                binding.followerTxt.text = followers.convertToK()
                                binding.followingTxt.text = following.convertToK()
                            }
                        }
                        is UserDetailState.Error -> {
                            showError(state.message)
                        }
                    }
                }

        }
        lifecycleScope.launch {
            viewModel.userList
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { users ->
                    userListAdapter?.addAll(users)
                }
        }
    }
}