package ddwu.com.mobile.flo_week2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import ddwu.com.mobile.flo_week2.adapter.LockerVPAdapter
import ddwu.com.mobile.flo_week2.databinding.FragmentLockerBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LockerFragment : Fragment() {
    private val information = arrayListOf("저장한 곡", "음악파일", "저장앨범")
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentLockerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater, container, false)

        val lockerAdapter = LockerVPAdapter(this@LockerFragment)
        binding.lockerVp.adapter = lockerAdapter
        TabLayoutMediator(binding.lockerContentTb, binding.lockerVp) { tab, position ->
            tab.text = information[position]
        }.attach()
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LockerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LockerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}