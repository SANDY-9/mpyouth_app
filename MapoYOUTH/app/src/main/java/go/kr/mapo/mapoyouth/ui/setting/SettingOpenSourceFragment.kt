package go.kr.mapo.mapoyouth.ui.setting


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentSettingOpenSourceBinding
import go.kr.mapo.mapoyouth.ui.MainActivity

/**
 * @author LimSeulgi
 * @email sg21.lim@gamil.com
 * @created 2021-09-24
 * @desc
 **/

// 오픈소스 라이선스

class SettingOpenSourceFragment : Fragment() {

    private lateinit var mToolbar: Toolbar
    lateinit var binding : FragmentSettingOpenSourceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting_open_source, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        // Toolbar
        mToolbar = view.findViewById(R.id.setting_open_source_toolbar)

        val parent = activity as MainActivity
        with(parent){
            setSupportActionBar(mToolbar)
            supportActionBar!!.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
                title = null
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) findNavController().navigate(R.id.action_SettingOpenSourceFragment_to_settingFragment)
        return super.onOptionsItemSelected(item)
    }
}