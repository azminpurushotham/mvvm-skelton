package com.azmin.skelton.itemDetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import butterknife.ButterKnife
import com.azmin.skelton.R
import com.azmin.skelton.databinding.FragmentItemdetailBinding
import core.base.BaseFragment
import core.utility.ProgressDialogUtil
import core.utility.ToastStateEnum
import core.utility.ToastUtil
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

class ItemDetailFragment : BaseFragment() {

    @Inject
    lateinit var vm: ItemDetailVM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            /**
             * Callback for handling the [OnBackPressedDispatcher.onBackPressed] event.
             */
            override fun handleOnBackPressed() {
                onBackClicked()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        AndroidSupportInjection.inject(this)
        var binding = FragmentItemdetailBinding.inflate(inflater, container, false)
        val mView = binding.root
        ButterKnife.bind(this, mView)
        binding.vm = vm
        binding.executePendingBindings()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observables()
    }

    private fun observables() {
        vm.toastState.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ToastStateEnum.SUCCESS -> {
                    ToastUtil.showSuccessToast(requireActivity(), it.msgResource)
                }
                ToastStateEnum.INFO -> {
                    ToastUtil.showInfoToast(requireActivity(), it.msgResource)
                }
                ToastStateEnum.Failure -> {
                    ToastUtil.showFailureToast(requireActivity(), it.msgResource)
                }
            }
        })

        vm.showLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                ProgressDialogUtil.showProgressDialog(
                    requireActivity(),
                    false,
                    getString(R.string.loading)
                )
            } else {
                ProgressDialogUtil.dismissProgressDialog()
            }
        })

        vm.onBackPressed.observe(viewLifecycleOwner, Observer {
            if (it) {
                onBackClicked()
            }
        })

    }


    private fun onBackClicked() {
        Timber.v("onBackClicked")
        requireActivity().findNavController(R.id.fragment).popBackStack()
    }

}