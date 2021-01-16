package com.sports.cricketclover.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sports.cricketclover.Fragments.Games.FragmentAllGames;
import com.sports.cricketclover.Fragments.Events.FragmentSeriesGames;
import com.sports.cricketclover.Fragments.Series.FragmentSeries;
import com.sports.cricketclover.Fragments.Standings.FragmentStandings;
import com.sports.cricketclover.Fragments.Teams.FragmentTeams;
import com.sports.cricketclover.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    FrameLayout frameLayout;

    String fragmentStatus = "";
    int exitCount = 0;

    // BOTTOM NAVIGATION LISTENER
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (fragmentStatus.equals("Home")) {
                        exitCount = 0;
                    } else {
                        fragmentStatus = "Home";
                        exitCount = 0;
                        FragmentSeriesGames fragmentSeriesGames = new FragmentSeriesGames();
                        FragmentManager managerhome = getSupportFragmentManager();
                        managerhome.beginTransaction().replace(R.id.dashboard_frameLayout, fragmentSeriesGames, fragmentSeriesGames.getTag()).commit();
                    }
                    return true;

                case R.id.navigation_teams:
                    if (fragmentStatus.equals("Teams")) {
                        exitCount = 0;
                    } else {
                        fragmentStatus = "Teams";
                        exitCount = 0;

                        FragmentTeams fragmentTeams = new FragmentTeams();
                        FragmentManager managerTeams = getSupportFragmentManager();
                        managerTeams.beginTransaction().replace(R.id.dashboard_frameLayout, fragmentTeams, fragmentTeams.getTag()).commit();
                    }
                    return true;

                case R.id.navigation_standings:
                    if (fragmentStatus.equals("Standings")) {
                        exitCount = 0;
                    } else {
                        fragmentStatus = "Standings";
                        exitCount = 0;
                        FragmentStandings fragmentStandings = new FragmentStandings();
                        FragmentManager managerStandings = getSupportFragmentManager();
                        managerStandings.beginTransaction().replace(R.id.dashboard_frameLayout, fragmentStandings, fragmentStandings.getTag()).commit();
                    }
                    return true;

                case R.id.navigation_series:
                    if (fragmentStatus.equals("Series")) {
                        exitCount = 0;
                    } else {
                        fragmentStatus = "Series";
                        exitCount = 0;
                        FragmentSeries fragmentSeries = new FragmentSeries();
                        FragmentManager managerAllSeries = getSupportFragmentManager();
                        managerAllSeries.beginTransaction().replace(R.id.dashboard_frameLayout, fragmentSeries, fragmentSeries.getTag()).commit();
                    }
                    return true;


            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        frameLayout = (FrameLayout) findViewById(R.id.dashboard_frameLayout);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bottomNavigation.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public void onBackPressed() {
        if (exitCount == 0) {
            exitCount++;
            Toast.makeText(this, "Press back to exit.", Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
    }
}