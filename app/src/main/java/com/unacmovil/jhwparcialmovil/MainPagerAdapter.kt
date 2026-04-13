package com.unacmovil.jhwparcialmovil

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.unacmovil.jhwparcialmovil.ui.login.LoginFragment

class MainPagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity){

    override fun getItemCount(): Int = 3;

    override fun createFragment(position: Int): Fragment {
        if ( position == 0){
            return LoginFragment();
        }
        else if (position == 1 ){
            return Unit1ConverterFragment();
        } else {
            return Unit2ConverterFragment();
        }
    }

}