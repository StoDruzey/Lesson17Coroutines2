package com.example.lesson17coroutines2

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson17coroutines2.databinding.FragmentTestBinding
import kotlinx.coroutines.*

class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTestBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            scope.launch {
                progress()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun progress() {
        var currentProgress = 0
        while (currentProgress <= 100) {
            withContext(Dispatchers.Main) {
                binding.progress.progress = currentProgress
            }
            delay(1000)
            currentProgress += 10
        }
    }
}