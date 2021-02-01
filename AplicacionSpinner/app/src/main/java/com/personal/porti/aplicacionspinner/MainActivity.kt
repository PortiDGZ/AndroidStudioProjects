package com.personal.porti.aplicacionspinner
import javax.swing.text.View

import java.awt.MenuItem

import java.awt.Menu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * This app demonstrates images used as buttons and a floating action button
 * to use an intent to launch a second activity. The app lets a user tap an
 * image to make a choice. The app displays a Toast message showing the user’s
 * choice.
 *
 * This version demonstrates various input controls.
 */
class MainActivity : AppCompatActivity() {
    // The order message, displayed in the Toast and sent to the new Activity.
    private var mOrderMessage: String? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener(object : View.OnClickListener() {
            override fun onClick(view: View?) {
                val intent = Intent(this@MainActivity,
                        OrderActivity::class.java)
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage)
                startActivity(intent)
            }
        })
    }

    /**
     * Inflates the menu, and adds items to the action bar if it is present.
     *
     * @param menu Menu to inflate.
     * @return Returns true if the menu inflated.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     * Handles app bar item clicks.
     *
     * @param item Item clicked.
     * @return True if one of the defined items was clicked.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.getItemId()

        // This comment suppresses the Android Studio warning about simplifying
        // the return statements.
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    /**
     * Displays a Toast with the message.
     *
     * @param message Message to display
     */
    fun displayToast(message: String?) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show()
    }

    /**
     * Shows a message that the donut image was clicked.
     */
    fun showDonutOrder(view: View?) {
        mOrderMessage = getString(R.string.donut_order_message)
        displayToast(mOrderMessage)
    }

    /**
     * Shows a message that the ice cream sandwich image was clicked.
     */
    fun showIceCreamOrder(view: View?) {
        mOrderMessage = getString(R.string.ice_cream_order_message)
        displayToast(mOrderMessage)
    }

    /**
     * Shows a message that the froyo image was clicked.
     */
    fun showFroyoOrder(view: View?) {
        mOrderMessage = getString(R.string.froyo_order_message)
        displayToast(mOrderMessage)
    }

    companion object {
        // Tag for the intent extra.
        const val EXTRA_MESSAGE = "com.example.android.droidcafe.extra.MESSAGE"
    }
}
