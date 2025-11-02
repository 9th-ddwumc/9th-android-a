package ddwu.com.mobile.flo_week2.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ddwu.com.mobile.flo_week2.fragment.banner.BannerFragment

class BannerVPAdapter(fragment: Fragment, private val imageList: List<Int>) :
    FragmentStateAdapter(fragment) {

    // 페이지 개수는 이미지 리스트의 크기입니다.
    override fun getItemCount(): Int = imageList.size

    override fun createFragment(position: Int): Fragment {
        val imageId = imageList[position]

        // 🚨 BannerFragment.newInstance를 호출하여 이미지 ID를 전달합니다.
        return BannerFragment.newInstance(imageId)
    }

    // 기존의 addFragment 메서드는 제거합니다.
}