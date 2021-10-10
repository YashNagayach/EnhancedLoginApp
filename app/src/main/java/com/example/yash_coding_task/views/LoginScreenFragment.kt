package com.example.yash_coding_task.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.yash_coding_task.R
import com.example.yash_coding_task.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginScreenFragment : Fragment(R.layout.fragment_login) {

    private val invalidUsernameErrorMessage = "Enter proper user name"
    private val invalidPasswordErrorMessage = "Password not meeting the criteria"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textWatcher = setTextWatcher()
        username_edit_text?.addTextChangedListener(textWatcher);
        password_edit_text?.addTextChangedListener(textWatcher);

        val navigationController = Navigation.findNavController(view)
        val model: SharedViewModel =
            ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        btn_login?.setOnClickListener {
            navigationController.navigate(R.id.action_loginFragment_to_homeFragment)
            model.setLoginDetails(
                username = getUserName()
            )
        }
    }

    private fun setTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
                checkFieldsForEmptyValues()
            }

            override fun afterTextChanged(editable: Editable) {}
        }
    }

    // This method will check for empty input fields.
    private fun checkFieldsForEmptyValues() {
        val username: String = getUserName()
        val password: String = getPassword()
        btn_login?.isEnabled = username.isNotEmpty() && password.isNotEmpty()
        handleInvalidDetails(username = username, password = password)
    }

    // Validating input on the basis of there respective regex.
    private fun handleInvalidDetails(username: String, password: String) {
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!?_]).{5,20}\$"
        val usernameRegex = "^[A-Za-z0-9]+$"

        if (!isValidInput(inputString = username, inputRegex = usernameRegex)) {
            username_edit_text?.error = invalidUsernameErrorMessage
            setButtonDisable()
        } else if (!isValidInput(inputString = password, inputRegex = passwordRegex)) {
            password_edit_text?.error = invalidPasswordErrorMessage
            setButtonDisable()
        }
    }

    private fun isValidInput(inputString: String, inputRegex: String): Boolean {
        if (inputString.isEmpty())
            return true
        val pattern: Pattern = Pattern.compile(inputRegex)
        val matcher: Matcher = pattern.matcher(inputString)
        return matcher.matches()
    }

    private fun setButtonDisable() {
        btn_login?.isEnabled = false
    }

    private fun getUserName(): String {
        return username_edit_text?.text.toString()
    }

    private fun getPassword(): String {
        return password_edit_text?.text.toString()
    }
}
