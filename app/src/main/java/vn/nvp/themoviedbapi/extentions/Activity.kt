package vn.nvp.themoviedbapi.extentions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import vn.nvp.themoviedbapi.R

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
fun FragmentActivity.replaceFragment(
    fragment: Fragment,
    isEnableAnim: Boolean = true,
    addBackStack: Boolean = true
) {
    supportFragmentManager.beginTransaction().apply {
        if (isEnableAnim) {
            setCustomAnimations(
                R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right
            )
        }
        replace(R.id.container, fragment, fragment.javaClass.simpleName)
        if (addBackStack) {
            addToBackStack(null)
        }
        commit()
    }
}
