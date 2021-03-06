package com.azmin.skelton.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.azmin.skelton.R
import com.azmin.skelton.databinding.FragmentHomeBinding
import core.base.BaseFragment
import core.utility.ProgressDialogUtil
import core.utility.ToastStateEnum
import core.utility.ToastUtil
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeAdapter.OnItemClickListener {

    @Inject
    lateinit var vm: HomeVM
    var adapter: HomeAdapter? = null
    var list = ArrayList<String>()

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

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
        var binding = FragmentHomeBinding.inflate(inflater, container, false)
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

        vm.list.observe(viewLifecycleOwner, Observer {
            list.clear()
            list.addAll(it)
            setAdapter()
        })

    }

    private fun setAdapter() {
        if (adapter == null && list?.isNotEmpty()!!) {
            adapter = HomeAdapter(list, this)
            recyclerView.adapter = adapter
            adapter?.submitList(list)
        } else {
            adapter?.notifyDataSetChanged()
        }
    }

    private fun onBackClicked() {
        Timber.v("onBackClicked")
        requireActivity().finish()
    }

    override fun onItemClickListener(position: Int) {

    }

    override fun onDetailClick(position: Int, item: String) {
        var bundle = Bundle()
        bundle.putInt("value", 1)
        findNavController().navigate(R.id.to_detailFragment, bundle)
    }

}