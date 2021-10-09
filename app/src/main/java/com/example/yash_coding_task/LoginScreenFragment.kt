package com.example.yash_coding_task

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginScreenFragment : Fragment(R.layout.fragment_login) {

    private val invalidUsernameErrorMessage = "Enter proper user name"
    private val invalidPasswordErrorMessage = "Password not meeting the criteria"
    private val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!?_]).{5,20}\$"
    private val usernameRegex = "^[A-Za-z0-9]+$"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textWatcher = setTextWatcher(view = view)
        username_edit_text?.addTextChangedListener(textWatcher);
        password_edit_text?.addTextChangedListener(textWatcher);

        val navigationController = Navigation.findNavController(view)
        val model: SharedViewModel =
            ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        btn_login?.setOnClickListener {
            navigationController.navigate(R.id.action_loginFragment_to_homeFragment)
            model.setLoginDetails(
                username = getUserName(),
                password = getPassword()
            )
        }
    }

    private fun setTextWatcher(view: View): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
                checkFieldsForEmptyValues(view = view)
            }

            override fun afterTextChanged(editable: Editable) {}
        }
    }

    private fun checkFieldsForEmptyValues(view: View) {
        val username: String = getUserName()
        val password: String = getPassword()
        btn_login?.isEnabled = username.isNotEmpty() && password.isNotEmpty()
        handleInvalidDetails(username = username, password = password)
    }

    private fun handleInvalidDetails(username: String, password: String) {
        if (!isValidUsername(username = username)) {
            username_edit_text?.error = invalidUsernameErrorMessage
            setButtonDisable()
        } else if (!isValidPassword(password = password)) {
            password_edit_text?.error = invalidPasswordErrorMessage
            setButtonDisable()
        }
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.isEmpty())
            return true
        val pattern: Pattern = Pattern.compile(passwordRegex)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    private fun isValidUsername(username: String): Boolean {
        if (username.isEmpty())
            return true
        val pattern: Pattern = Pattern.compile(usernameRegex)
        val matcher: Matcher = pattern.matcher(username)
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