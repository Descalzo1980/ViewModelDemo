package ru.stas.viewmodeldemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import ru.stas.viewmodeldemo.R
import ru.stas.viewmodeldemo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.application.let {
            val factory = SavedStateViewModelFactory(it, this)
            viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
            val resultObserver = Observer<Float> { result ->
                binding.resultText.text = result.toString()
            }
            viewModel.getResult().observe(viewLifecycleOwner, resultObserver)
        }

        binding.convertButton.setOnClickListener {
            if (binding.dollarText.text.isNotEmpty()) {
                viewModel.setAmount(binding.dollarText.text.toString())
            } else {
                binding.resultText.text = getString(R.string.no_value)
            }
        }
        binding.delResult.setOnClickListener {
            binding.resultText.text = viewModel.cleanResult().toString()
            binding.dollarText.setText("")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


