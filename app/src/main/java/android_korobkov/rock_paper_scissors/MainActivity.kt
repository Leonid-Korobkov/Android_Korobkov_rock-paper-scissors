package android_korobkov.rock_paper_scissors

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android_korobkov.rock_paper_scissors.databinding.ActivityMainBinding
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var computerChoice: Button
    private lateinit var userChoice: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация привязки ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Навешивание слушателя на кнопку "Начать"
        binding.btnStart.setOnClickListener {
            resetGame()
        }

        // Навешивание слушателя на все кнопки выбора (Бумага, Спок, Ящерица, Камень, Ножницы)
        binding.apply {
            listOf(btnPaper, btnSpok, btnLizard, btnRock, btnScissors).forEach { button ->
                button.setOnClickListener {
                    startGame(button)
                }
            }
        }

        // Отключение всех кнопок выбора
        disableChoices()
    }

    // Метод для отключения всех кнопок выбора
    private fun disableChoices() {
        with(binding) {
            btnPaper.isEnabled = false
            btnSpok.isEnabled = false
            btnLizard.isEnabled = false
            btnRock.isEnabled = false
            btnScissors.isEnabled = false
        }
    }

    // Метод для включения всех кнопок выбора
    private fun enableChoices() {
        with(binding) {
            btnPaper.isEnabled = true
            btnSpok.isEnabled = true
            btnLizard.isEnabled = true
            btnRock.isEnabled = true
            btnScissors.isEnabled = true
        }
    }

    // Метод для сброса игры
    private fun resetGame() {
        with(binding) {
            enableChoices()
            btnStart.isEnabled = true
            resultTextView.text = ""
            resetButtonBackgrounds()
        }
    }

    // Метод для сброса цвета фона всех кнопок выбора
    private fun resetButtonBackgrounds() {
        with(binding) {
            listOf(btnPaper, btnSpok, btnLizard, btnRock, btnScissors).forEach {
                it.setBackgroundColor(Color.MAGENTA)
            }
        }
    }

    // Метод для начала игры
    private fun startGame(chosenButton: Button) {
        userChoice = chosenButton
        userChoice.setBackgroundColor(Color.GREEN)

        computerChoice = getComputerChoice()
        computerChoice.setBackgroundColor(Color.RED)

        checkResult()
    }

    // Метод для определения случайного выбора компьютера
    private fun getComputerChoice(): Button {
        val choices = listOf(
            binding.btnLizard,
            binding.btnPaper,
            binding.btnRock,
            binding.btnSpok,
            binding.btnScissors
        )
        return choices.random()
    }

    // Метод для проверки результатов игры
    private fun checkResult() {
        val userText = userChoice.text.toString()
        val computerText = computerChoice.text.toString()

        val result = when (userText) {
            computerText -> "Ничья. Попробуйте еще"
            // Условия победы и поражения для различных комбинаций выборов
            else -> {
                // Вывод результата
                "Вы победили/проиграли"
            }
        }

        // Установка текста результата игры
        with(binding) {
            resultTextView.text = result
            btnStart.isEnabled = true
        }
    }
}