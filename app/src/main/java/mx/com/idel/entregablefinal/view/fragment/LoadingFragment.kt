package mx.com.idel.entregablefinal.view.fragment

import android.graphics.Typeface
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.com.idel.entregablefinal.R
import mx.com.idel.entregablefinal.databinding.FragmentLoadingBinding

class LoadingFragment : Fragment() {

    private var _binding: FragmentLoadingBinding? = null
    private val binding get() = _binding!!

    private lateinit var anima: AnimatedVectorDrawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoadingBinding.inflate(inflater, container, false)
        val view = binding.root

        //ANIMACION
        binding.loadImg.setImageResource(R.drawable.load)
        anima = binding.loadImg.drawable as AnimatedVectorDrawable
        anima.start()

        val spannable = SpannableStringBuilder(getString(R.string.app_name))
        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.appname.text = spannable

        return view
    }

    override fun onResume() {
        super.onResume()
        anima.start()
    }

    override fun onPause() {
        super.onPause()
        anima.stop()
    }

}