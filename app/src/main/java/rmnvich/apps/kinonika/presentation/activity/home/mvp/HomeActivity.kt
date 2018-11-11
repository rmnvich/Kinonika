package rmnvich.apps.kinonika.presentation.activity.home.mvp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import rmnvich.apps.kinonika.R
import rmnvich.apps.kinonika.app.App
import rmnvich.apps.kinonika.databinding.ActivityHomeBinding
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var mPresenter: HomeActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.handler = this

        App.getApp(this).componentsHolder
                .getComponent(javaClass).inject(this)
    }

    @Inject
    fun init() {
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            App.getApp(this).componentsHolder
                    .releaseComponent(javaClass)
        }
    }
}
