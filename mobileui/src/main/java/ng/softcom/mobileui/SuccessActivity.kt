package ng.softcom.mobileui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_success.*

class SuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        button_take_another_survey.setOnClickListener {
            FormActivity.startActivity(this)
            finish()
        }
    }

    companion object {
        fun startActivity(context:Context){
            context.startActivity(Intent(context, SuccessActivity::class.java))
        }
    }
}
