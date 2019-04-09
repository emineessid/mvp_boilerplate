package com.interco.e.daggerrxretrofit.base.presenter

import android.os.Bundle
import android.view.View
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import com.interco.e.daggerrxretrofit.base.activity.BaseActivityViewInterface
import java.lang.reflect.ParameterizedType

@Keep
open abstract class BasePresenterFragment<P : BaseActivityPresenter<*,*>, O : BaseActivityViewInterface> : Fragment() {
    protected lateinit var presenter: P
    protected open lateinit var viewInterface: O

    private fun setPresenter() {
        this.presenter = ClassUtils.instantiateTypeArgumentOf<P>(this.javaClass, 0)!!
        this.presenter.attachViewCallback(this.viewInterface)

    }

    abstract fun createViewInterface(): O

    private fun setViewInterface() {
        this.viewInterface = this.createViewInterface()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setViewInterface()
        this.setPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.presenter.onCreated()
    }

    override fun onResume() {
        this.presenter.onResume()
        super.onResume()
    }

    override fun onPause() {
        this.presenter.onPause()
        super.onPause()
    }

    override fun onDestroyView() {
        this.presenter.onDestroy()
        super.onDestroyView()
    }

    class ClassUtils {
        companion object {

            fun <T> instantiateTypeArgumentOf(clz: Class<*>, argsIndex: Int): T? {
                try {
                    return getTypeParameterClass<Any>(clz, argsIndex).newInstance() as T
                } catch (var3: Fragment.InstantiationException) {
                    var3.printStackTrace()
                } catch (var4: IllegalAccessException) {
                    var4.printStackTrace()
                } catch (e: java.lang.InstantiationException) {
                    e.printStackTrace()
                }

                return null
            }

            private fun <T> getTypeParameterClass(clz: Class<*>, argsIndex: Int): Class<T> {
                val type = clz.genericSuperclass
                val paramType = type as ParameterizedType
                return paramType.actualTypeArguments[argsIndex] as Class<T>
            }
        }
    }
}