package com.azmin.skelton.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import butterknife.ButterKnife
import com.azmin.skelton.R
import com.azmin.skelton.databinding.FragmentHomeBinding
import core.base.BaseFragment
import core.utility.ProgressDialogUtil
import core.utility.ToastStateEnum
import core.utility.ToastUtil
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var vm: HomeVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        AndroidSupportInjection.inject(this)
        var binding = FragmentHomeBinding.inflate(inflater,container, false)
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
        requireActivity().finish()
    }

}