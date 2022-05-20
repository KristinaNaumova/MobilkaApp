package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
///gbhjnkml,


class CodeblocksActivity : AppCompatActivity() {
    private lateinit var blocksContainer: LinearLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_codeblocks)

        blocksContainer = findViewById(R.id.blocksContainer)
        drawerLayout = findViewById(R.id.drawerLayout)

        toggle = ActionBarDrawerToggle (this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener {
            when(it.itemId)
            {   R.id.nav_home->{ Toast.makeText(applicationContext,"Clicked Home",Toast.LENGTH_SHORT).show();   finish()}
                R.id.nav_intVar-> {
                    Toast.makeText(applicationContext,"Created Integer variable",Toast.LENGTH_SHORT).show();
                    addIntVarCodeblockView()
                }
                R.id.nav_assignValtoVar->{
                    Toast.makeText(applicationContext,"Created Assigning value",Toast.LENGTH_SHORT).show();
                    addInitCodeblockView()
                }
                R.id.nav_while-> {
                    Toast.makeText(applicationContext,"Created While Expression",Toast.LENGTH_SHORT).show();
                    addWhileCodeblockView()
                }
                R.id.nav_if-> {
                    Toast.makeText(applicationContext,"Created If",Toast.LENGTH_SHORT).show()
                    addIfCodeblockView()
                }
                R.id.nav_print-> {
                    Toast.makeText(applicationContext,"Created Output Expression",Toast.LENGTH_SHORT).show();
                    addPrintCodeblockView()
                }
            }
            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

    private fun addIntVarCodeblockView() {
        val subView = InitCodeblockWidget(this)
        blocksContainer.addView(subView.view)
    }

    private fun addIfCodeblockView() {
        val subView = IfCodeblockWidget(this)
        blocksContainer.addView(subView.view)
    }

    private fun addInitCodeblockView() {
        val subView = IntVarCodeblockWidget(this)
        blocksContainer.addView(subView.view)
    }
    private fun addWhileCodeblockView() {
        val subView = WhileCodeblockWidget(this)
        blocksContainer.addView(subView.view)
    }
    private fun addPrintCodeblockView() {
        val subView = PrintCodeblockWidget(this)
        blocksContainer.addView(subView.view)
    }
}