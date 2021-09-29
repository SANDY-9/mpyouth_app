package go.kr.mapo.mapoyouth.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import go.kr.mapo.mapoyouth.R
import go.kr.mapo.mapoyouth.databinding.FragmentSettingnoticeBinding
import go.kr.mapo.mapoyouth.ui.MainActivity

/**
 * @author LimSeulgi
 * @email sg21.lim@gmail.com
 * @created 2021-09-17
 * @desc
 **/
class SettingNoticeFragment: Fragment(R.layout.fragment_settingnotice) {

    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    lateinit var binding : FragmentSettingnoticeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settingnotice, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        // Toolbar
        mToolbar = view.findViewById(R.id.notice_toolbar)

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

/*    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) findNavController().navigate(R.id.action_settingNoticeFragment_to_settingFragment)
            return super.onOptionsItemSelected(item)
        }*/
    }


