package  core.base

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.azmin.skelton.R
import com.google.android.material.snackbar.Snackbar
import com.utility.ConnectionModel
import com.utility.InternetUtil
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    var view: View? = null
    private val noInternetSnackBar: Snackbar by lazy {
        Snackbar.make(
            view!!,
            getString(R.string.no_Internet),
            Snackbar.LENGTH_INDEFINITE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        internetConnectionListner()
    }

    private fun internetConnectionListner() {
        InternetUtil.observe(this, Observer {
            try {
                view = window?.decorView?.rootView
                if (view != null) {
                    // Position snackbar at top
                    val params = view?.getLayoutParams() as FrameLayout.LayoutParams
                    params.width = FrameLayout.LayoutParams.MATCH_PARENT
                    view?.setLayoutParams(params)
                    if (it.isConnected) {
                        Timber.v("Internet available")
                        noInternetSnackBar.dismiss()
                    } else {
                        Timber.v("Internet not available")
                        noInternetSnackBar.setAction(
                            getString(R.string.network_settings),
                            View.OnClickListener {
                                Timber.v("No internet connection try again clicked")
                            })
                        noInternetSnackBar.show()
                    }
                }
            } catch (e: Exception) {
                Timber.v(e.message)
            }
        })
    }

}