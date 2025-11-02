package ddwu.com.mobile.flo_week2.fragment.banner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ddwu.com.mobile.flo_week2.databinding.FragmentBannerBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BannerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_IMG_ID = "image_id"
class BannerFragment : Fragment() {

    lateinit var binding: FragmentBannerBinding
    private var imageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageId = it.getInt(ARG_IMG_ID, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBannerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // imageId가 유효하다면 ImageView에 설정합니다.
        if (imageId != 0) {
            binding.homePannelBackgroundIv.setImageResource(imageId)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param imageId 표시할 이미지 리소스 ID (Int)
         * @return A new instance of fragment BannerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(imageId: Int) =
            BannerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_IMG_ID, imageId)
                }
            }
    }
}