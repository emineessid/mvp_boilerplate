package com.interco.e.daggerrxretrofit.base.activity


import android.app.ActivityManager
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.AnimRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.interco.e.daggerrxretrofit.R
import com.interco.e.daggerrxretrofit.base.presenter.BaseActivityPresenter
import com.interco.e.daggerrxretrofit.utils.DialogUtils
import com.interco.e.daggerrxretrofit.utils.KeybordUtils
import java.lang.reflect.ParameterizedType


abstract class BaseActivity<P : BaseActivityPresenter<*,*>, O : BaseActivityViewInterface> : AppCompatActivity(), BaseActivityViewInterface {
    protected lateinit var presenter: P
    protected lateinit var viewInterface: O
    private lateinit var dialog :  Dialog


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
        this.dialog = DialogUtils.progressDialog(this)
        this.setViewInterface()
        this.setPresenter()
        this.presenter.onCreated()
    }

    override fun onStart() {
        super.onStart()
        this.presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        this.presenter.onResume()
    }

    override fun onPause() {
        this.presenter.onPause()
        super.onPause()
    }

    override fun onStop() {
        this.presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        this.presenter.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun getLastFragment(fragmentManager: FragmentManager): Fragment? {
        if (fragmentManager.backStackEntryCount > 0) {
            var lastIndex = fragmentManager.backStackEntryCount - 1
            if (lastIndex < 0) {
                lastIndex = 0
            }

            val lastTag = fragmentManager.getBackStackEntryAt(lastIndex).name
            val fragment = fragmentManager.findFragmentByTag(lastTag)
            if (fragment != null) {
                return fragment
            }
        }

        return null
    }

    //    ********************** fragments *****************

    fun getFragment(fragmentManager: FragmentManager, Object: Class<*>): Fragment {
        return fragmentManager.findFragmentByTag(Object.name)!!
    }

    fun getBackStackEntryCount(fragmentManager: FragmentManager?): Int {
        return fragmentManager?.backStackEntryCount ?: 0
    }

    fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, isActivityRunning: Boolean) {
        if (isActivityRunning) {
            KeybordUtils.hideKeyboard(this)
            if (!fragment.javaClass.isInstance(this.getLastFragment(fragmentManager))) {
                val className = fragment.javaClass.name
                val ft = fragmentManager.beginTransaction()
                if (!fragment.isAdded()) {
                    ft.replace(frameId, fragment, className)
                    ft.addToBackStack(className)
                    ft.commit()
                } else {
                    ft.show(this.getFragment(fragmentManager, fragment.javaClass))
                }
            }

        }
    }


    fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, isActivityRunning: Boolean, @AnimRes enter: Int, @AnimRes exit: Int, @AnimRes popEnter: Int, @AnimRes popExit: Int) {
        if (isActivityRunning) {
            KeybordUtils.hideKeyboard(this)
            if (!fragment.javaClass.isInstance(this.getLastFragment(fragmentManager))) {
                val className = fragment.javaClass.name
                val ft = fragmentManager.beginTransaction()
                ft.setCustomAnimations(enter, exit, popEnter, popExit)
                if (!fragment.isAdded) {
                    ft.replace(frameId, fragment, className)
                    ft.addToBackStack(className)
                    ft.commit()
                } else {
                    ft.show(this.getFragment(fragmentManager, fragment.javaClass))
                }
            }

        }
    }

    fun replaceFragmentNoAddToStack(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, isActivityRunning: Boolean) {
        if (isActivityRunning) {
            KeybordUtils.hideKeyboard(this)
            val ft = fragmentManager.beginTransaction()
            if (!fragment.javaClass.isInstance(this.getLastFragment(fragmentManager))) {
                val className = fragment.javaClass.name
                if (!fragment.isAdded()) {
                    ft.replace(frameId, fragment, className)
                } else {
                    ft.show(this.getFragment(fragmentManager, fragment.javaClass))
                }
            } else {
                ft.show(this.getFragment(fragmentManager, fragment.javaClass))
            }

            ft.commit()
        }
    }

    fun replaceFragmentNoAddToStack(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, isActivityRunning: Boolean, @AnimRes enter: Int, @AnimRes exit: Int, @AnimRes popEnter: Int, @AnimRes popExit: Int) {
        if (isActivityRunning) {
            KeybordUtils.hideKeyboard(this)
            val ft = fragmentManager.beginTransaction()
            ft.setCustomAnimations(enter, exit, popEnter, popExit)
            if (!fragment.javaClass.isInstance(this.getLastFragment(fragmentManager))) {
                val className = fragment.javaClass.name
                if (!fragment.isAdded()) {
                    ft.replace(frameId, fragment, className)
                } else {
                    ft.show(this.getFragment(fragmentManager, fragment.javaClass))
                }
            } else {
                ft.show(this.getFragment(fragmentManager, fragment.javaClass))
            }

            ft.commit()
        }
    }

    fun addFragment(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, isActivityRunning: Boolean) {
        if (isActivityRunning) {
            KeybordUtils.hideKeyboard(this)
            if (!fragment.javaClass.isInstance(this.getLastFragment(fragmentManager))) {
                val className = fragment.javaClass.name
                val ft = fragmentManager.beginTransaction()
                if (!fragment.isAdded()) {
                    ft.add(frameId, fragment, className)
                    ft.addToBackStack(className)
                    ft.commit()
                } else {
                    ft.show(this.getFragment(fragmentManager, fragment.javaClass))
                }
            }

        }
    }

    fun addFragment(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, isActivityRunning: Boolean, @AnimRes enter: Int, @AnimRes exit: Int, @AnimRes popEnter: Int, @AnimRes popExit: Int) {
        if (isActivityRunning) {
            KeybordUtils.hideKeyboard(this)
            if (!fragment.javaClass.isInstance(this.getLastFragment(fragmentManager))) {
                val className = fragment.javaClass.name
                val ft = fragmentManager.beginTransaction()
                ft.setCustomAnimations(enter, exit, popEnter, popExit)
                if (!fragment.isAdded()) {
                    ft.add(frameId, fragment, className)
                    ft.addToBackStack(className)
                    ft.commit()
                } else {
                    ft.show(this.getFragment(fragmentManager, fragment.javaClass))
                }
            }

        }
    }

    fun addFragmentWithoutAddToStack(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, isActivityRunning: Boolean, @AnimRes enter: Int, @AnimRes exit: Int, @AnimRes popEnter: Int, @AnimRes popExit: Int) {
        if (isActivityRunning) {
            KeybordUtils.hideKeyboard(this)
            if (!fragment.javaClass.isInstance(this.getLastFragment(fragmentManager))) {
                val className = fragment.javaClass.name
                val ft = fragmentManager.beginTransaction()
                ft.setCustomAnimations(enter, exit, popEnter, popExit)
                if (!fragment.isAdded()) {
                    ft.add(frameId, fragment, className)
                    ft.commit()
                } else {
                    ft.show(this.getFragment(fragmentManager, fragment.javaClass))
                }
            }

        }
    }

    fun addFragmentWithoutAddToStack(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, isActivityRunning: Boolean) {
        if (isActivityRunning) {
            KeybordUtils.hideKeyboard(this)
            if (!fragment.javaClass.isInstance(this.getLastFragment(fragmentManager))) {
                val className = fragment.javaClass.name
                val ft = fragmentManager.beginTransaction()
                if (!fragment.isAdded()) {
                    ft.add(frameId, fragment, className)
                    ft.commit()
                } else {
                    ft.show(this.getFragment(fragmentManager, fragment.javaClass))
                }
            }

        }
    }

    fun removeFragment(fragmentManager: FragmentManager, fragment: Fragment, isActivityRunning: Boolean) {
        if (isActivityRunning) {
            KeybordUtils.hideKeyboard(this)
            val ft = fragmentManager.beginTransaction()
            ft.remove(fragment)
            ft.commit()
        }
    }

    fun addFragmentToClearStack(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, isActivityRunning: Boolean) {
        if (isActivityRunning) {
            KeybordUtils.hideKeyboard(this)
            this.clearFullStack(fragmentManager, isActivityRunning)
            val className = fragment.javaClass.name
            val ft = fragmentManager.beginTransaction()
            if (!fragment.isAdded()) {
                ft.replace(frameId, fragment, className)
                ft.addToBackStack(className)
                ft.commit()
            } else {
                ft.show(this.getFragment(fragmentManager, fragment.javaClass))
            }

        }
    }

    fun hideKeyboard() {
        KeybordUtils.hideKeyboard(this);
    }

    fun addFragmentToClearStack(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, isActivityRunning: Boolean, @AnimRes enter: Int, @AnimRes exit: Int, @AnimRes popEnter: Int, @AnimRes popExit: Int) {
        if (isActivityRunning) {
            KeybordUtils.hideKeyboard(this)
            this.clearFullStack(fragmentManager, isActivityRunning)
            val className = fragment.javaClass.name
            val ft = fragmentManager.beginTransaction()
            ft.setCustomAnimations(enter, exit, popEnter, popExit)
            if (!fragment.isAdded()) {
                ft.replace(frameId, fragment, className)
                ft.addToBackStack(className)
                ft.commit()
            } else {
                ft.show(this.getFragment(fragmentManager, fragment.javaClass))
            }

        }
    }

    fun clearFullStack(fragmentManager: FragmentManager, isActivityRunning: Boolean) {
        if (isActivityRunning) {
            fragmentManager.popBackStack(null, 1)
        }
    }

    override fun toggleLoading(yesNo: Boolean) {
        dialog.dismiss()
             if (yesNo) {
            dialog.show()
        } else {
            dialog.dismiss()
        }


    }


    fun isRunning(ctx: Context): Boolean {
        val activityManager = ctx.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val tasks = activityManager.getRunningTasks(Integer.MAX_VALUE)

        for (task in tasks) {
            if (ctx.getPackageName().equals(task.baseActivity.packageName))
                return true
        }

        return false
    }

    class ClassUtils {
        companion object {

            fun <T> instantiateTypeArgumentOf(clz: Class<*>, argsIndex: Int): T? {
                try {
                    return getTypeParameterClass<Any>(clz, argsIndex).newInstance() as T
                } catch (var3: Exception) {
                    var3.printStackTrace()
                } catch (var4: IllegalAccessException) {
                    var4.printStackTrace()
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