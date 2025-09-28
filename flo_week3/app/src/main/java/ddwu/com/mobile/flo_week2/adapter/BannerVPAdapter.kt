package ddwu.com.mobile.flo_week2.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ddwu.com.mobile.flo_week2.fragment.HomeFragment

class BannerVPAdapter(fa: HomeFragment) : FragmentStateAdapter(fa) {
    private var fragmentList : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
        notifyItemInserted(fragmentList.size - 1)
    }
}