package com.azmin.skelton.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import butterknife.ButterKnife
import butterknife.OnClick
import com.azmin.skelton.R
import com.azmin.skelton.databinding.ActivityLoginBinding
import com.azmin.skelton.main.MainActivity
import core.base.BaseActivity
import core.utility.ProgressDialogUtil
import core.utility.ToastStateEnum
import core.utility.ToastUtil
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var vm: LoginVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        var binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        ButterKnife.bind(this)
        binding.vm = vm
        binding.executePendingBindings()
        observables()
    }

    private fun observables() {
        vm.toastState.observe(this, Observer {
            when (it.status) {
                ToastStateEnum.SUCCESS -> {
                    ToastUtil.showSuccessToast(this, it.msgResource)
                }
                ToastStateEnum.INFO -> {
                    ToastUtil.showInfoToast(this, it.msgResource)
                }
                ToastStateEnum.Failure -> {
                    ToastUtil.showFailureToast(this, it.msgResource)
                }
            }
        })

        vm.showLoading.observe(this, Observer {
            if (it) {
                ProgressDialogUtil.showProgressDialog(this, false, getString(R.string.loading))
            } else {
                ProgressDialogUtil.dismissProgressDialog()
            }
        })

        vm.onBackPressed.observe(this, Observer {
            if (it) {
                onBackClicked()
            }
        })

        vm.loginSuccess.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }

    private fun onBackClicked() {
        finish()
    }

    @OnClick(R.id.btnLogin)
    fun onLogin(view: View) {
        vm.requestLogin()
    }

    @OnClick(R.id.tvForgetPassword)
    fun onForgotPassword(view: View) {
    }

    @OnClick(R.id.tvForgetPassword)
    fun onRegister(view: View) {

    }

}