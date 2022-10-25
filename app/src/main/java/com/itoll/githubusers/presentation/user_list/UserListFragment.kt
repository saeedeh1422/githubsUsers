package com.itoll.githubusers.presentation.user_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itoll.githubusers.base.BaseFragment
import com.itoll.githubusers.databinding.FragmentUserListBinding
import com.itoll.githubusers.presentation.adapter.UserAdapter
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : BaseFragment() {
    private var textWatcher: TextWatcher? = null
    private val viewModel: UserListViewModel by viewModel()
    private var userListAdapter: UserAdapter? = null
    private var searchUserAdapter: UserAdapter? = null
    private lateinit var binding: FragmentUserListBinding
    private var searchDelayJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!this::binding.isInitialized)
            binding = FragmentUserListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun initViews() {
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        userListAdapter = UserAdapter { user ->
            findNavController().navigate(
                UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(
                    user.userName
                )
            )
        }
        searchUserAdapter = UserAdapter { user ->
            findNavController().navigate(
                UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(
                    user.userName
                )
            )
        }
        binding.searchListRv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = searchUserAdapter
        }
        binding.userListRv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = userListAdapter
        }
    }

    override fun clickViews() {
        textWatcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0?.toString()?.takeIf { it.isNotEmpty() }?.also { q ->
                    searchDelayJob?.cancel()
                    searchDelayJob = CoroutineScope(Dispatchers.Main).launch {
                        delay(800)
                        viewModel.searchUser(q)
                    }
                } ?: kotlin.run {
                    showUserList()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        }
        binding.searchEdt.addTextChangedListener(textWatcher)
    }


    override fun executeInitTask() {
        viewModel.getUserList()
    }

    override fun observes() {

        lifecycleScope.launch {
            viewModel.userListState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when (state) {
                        is UsersState.Users -> {
                            hideProgressBar(binding.progressBar)
                            userListAdapter?.addAll(state.users)
                        }
                        is UsersState.Loading -> {
                            showProgressBar(binding.progressBar)
                        }
                        is UsersState.Error -> {
                            hideProgressBar(binding.progressBar)
                            showError(state.message)
                        }
                    }
                }

        }
        lifecycleScope.launch {
            viewModel.searchState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when (state) {
                        is SearchState.SearchUsers -> {
                            hideUserList()
                            searchUserAdapter?.clearAddAll(state.users)
                        }
                        is SearchState.Error -> {
                            showUserList()
                            showError(state.message)
                        }
                    }
                }
        }
    }

    private fun showUserList() {
        binding.userListRv.visibility = View.VISIBLE
        binding.searchListRv.visibility = View.GONE
    }

    private fun hideUserList() {
        binding.userListRv.visibility = View.GONE
        binding.searchListRv.visibility = View.VISIBLE
    }
}