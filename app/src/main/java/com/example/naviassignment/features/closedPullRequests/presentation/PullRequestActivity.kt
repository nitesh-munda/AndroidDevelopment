package com.example.naviassignment.features.closedPullRequests.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.naviassignment.R
import com.example.naviassignment.features.closedPullRequests.data.api.model.RepoDetails
import com.example.naviassignment.features.closedPullRequests.domain.repository.GithubRepository
import com.example.naviassignment.features.closedPullRequests.domain.usecase.PullRequestUseCase
import com.example.naviassignment.features.closedPullRequests.presentation.view.PullRequestAdapter
import core.network.RetrofitService

class PullRequestActivity: AppCompatActivity() {

    //views
    private lateinit var root: ConstraintLayout
    private val pullRequestAdapter = PullRequestAdapter(emptyList())

    //view model
    private lateinit var viewModel: PullRequestViewModel

    //view model factory
    private lateinit var viewModelFactory: PullRequestViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
        initRootView()
        setupViewModel()
        initRecyclerView()
        fetchPullRequests()
    }

    private fun initRootView() {
        root = findViewById<ConstraintLayout>(R.id.root)
        root.findViewById<AppCompatButton>(R.id.btCta).setOnClickListener {
            fetchPullRequests()
        }
    }

    private fun fetchPullRequests() {
        viewModel.fetchClosedPullRequest()
    }

    private fun setupViewModel() {
        viewModelFactory =  PullRequestViewModelFactory(
            useCase = PullRequestUseCase(
                githubRepository = GithubRepository(
                    githubApi = RetrofitService
                        .getRetrofitInstance(),
                    repoDetails = RepoDetails(
                        username = "nitesh-munda",
                        repo = "AndroidDevelopment"
                    )
                )
            ),
            viewCallback = ViewCallbackImpl(
                view = root,
                adapter = pullRequestAdapter
            )
        )

        viewModel = viewModelFactory.create(PullRequestViewModel::class.java)
    }

    private fun initRecyclerView() {
        root.findViewById<RecyclerView>(R.id.rvContent).apply {
            adapter = pullRequestAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}