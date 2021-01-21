package com.example.commonnetwork

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import io.reactivex.disposables.Disposable


open class MyObserver<T>: BaseObserver<T>(){
    private var mShowDialog = false
    private var dialog: ProgressDialog? = null
    private var mContext: Context? = null
    private var d: Disposable? = null


    fun MyObserver(context: Context, showDialog: Boolean) {
        mContext = context
        mShowDialog = showDialog
    }



    fun MyObserver() {}

    override fun onSubscribe(d: Disposable) {
        this.d = d
        if (!isConnected(mContext!!)) {
            Toast.makeText(mContext, "未连接网络", Toast.LENGTH_SHORT).show()
            if (d.isDisposed) {
                d.dispose()
            }
        } else {
            if (dialog == null && mShowDialog == true) {
                dialog = ProgressDialog(mContext)
                dialog!!.setMessage("正在加载中")
                dialog!!.show()
            }
        }
    }

    override fun onError(e: Throwable) {

        if (d!!.isDisposed()) {
            d!!.dispose();
        }
        hidDialog();
        super.onError(e)

    }

    override fun onComplete() {
        if (d!!.isDisposed) {
            d!!.dispose()
        }
        hidDialog()
        super.onComplete()
    }


    fun hidDialog() {
        if (dialog != null && mShowDialog == true) dialog!!.dismiss()
        dialog = null
    }

    /**
     * 是否有网络连接，不管是wifi还是数据流量
     * @param context
     * @return
     */
    fun isConnected(context: Context): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo ?: return false
        return info.isAvailable
    }

    override fun onSuccess(result: T) {

    }

    override fun onFailure(e: Throwable?, errorMsg: String?) {

    }


}