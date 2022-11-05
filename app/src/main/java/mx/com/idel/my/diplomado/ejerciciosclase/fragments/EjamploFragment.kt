package mx.com.idel.my.diplomado.ejerciciosclase.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.com.idel.my.diplomado.R

class EjamploFragment : Fragment() {
    private var TAG = "EjampleFragment"

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "${getString(R.string.entre_en)} onStart")
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG, "${getString(R.string.entre_en)} onAttach")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e(TAG, "${getString(R.string.entre_en)} onActivityCreated")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "${getString(R.string.entre_en)} onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(TAG, "${getString(R.string.entre_en)} onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ejample, container, false)
    }



    override fun onResume() {
        super.onResume()
        Log.e(TAG, "${getString(R.string.entre_en)} onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "${getString(R.string.entre_en)} onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "${getString(R.string.entre_en)} onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "${getString(R.string.entre_en)} onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "${getString(R.string.entre_en)} onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG, "${getString(R.string.entre_en)} onDetach")
    }
}