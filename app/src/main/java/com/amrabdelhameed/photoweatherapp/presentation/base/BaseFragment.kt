package com.amrabdelhameed.photoweatherapp.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlin.reflect.KClass

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {
    private lateinit var viewDataBinding: T

    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.showToast.observe(
            this,
            { Toast.makeText(activity, it, Toast.LENGTH_LONG).show() }
        )
        viewModel.navigationCommand.observe(this, { command ->
            navigate(command)
        })
        viewModel.activityToStart.observe(this, { value ->
            navigate(value)
        })
    }

    fun getViewDataBinding(): T {
        return viewDataBinding
    }

    fun navigate(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To -> findNavController().navigate(command.directions)
            is NavigationCommand.Back -> findNavController().popBackStack()
            is NavigationCommand.BackTo -> findNavController().popBackStack(
                command.destinationId,
                false
            )
        }
    }

    fun navigateWithoutFinish(value: Pair<KClass<*>, Bundle?>) {
        val intent = Intent(activity, value.first.java)
        if (value.second != null)
            intent.putExtras(value.second!!)
        startActivity(intent)
    }

    private fun navigate(value: Pair<KClass<*>, Bundle?>) {
        val intent = Intent(activity, value.first.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        if (value.second != null)
            intent.putExtras(value.second!!)
        startActivity(intent)
        activity?.finish()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        getViewDataBinding().setVariable(bindingVariable, viewModel)
        getViewDataBinding().lifecycleOwner = this
        getViewDataBinding().executePendingBindings()
    }
}