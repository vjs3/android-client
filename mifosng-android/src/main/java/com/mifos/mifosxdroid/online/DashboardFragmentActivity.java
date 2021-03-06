/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */

package com.mifos.mifosxdroid.online;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.mifos.mifosxdroid.OfflineCenterInputActivity;
import com.mifos.mifosxdroid.R;
import com.mifos.mifosxdroid.activity.PathTrackingActivity;
import com.mifos.mifosxdroid.core.MifosBaseActivity;
import com.mifos.objects.survey.Survey;

/**
 * Created by ishankhanna on 09/02/14.
 */
public class DashboardFragmentActivity extends MifosBaseActivity implements SurveyListFragment.OnFragmentInteractionListener {

    public final static String TAG = DashboardFragmentActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_container);
        replaceFragment(new ClientSearchFragment(), false, R.id.container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.client_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_centers:
                startActivity(new Intent(this, CentersActivity.class));
                break;
            case R.id.mItem_list:
                loadClientList();
                break;
            case R.id.group_list:
                loadGroupList();
                break;
            case R.id.item_survey:
                loadClientListForSurvey();
                break;
            case R.id.item_offline_centers:
                startActivity(new Intent(this, OfflineCenterInputActivity.class));
                break;
            case R.id.logout:
                logout();
                break;
            case R.id.mItem_create_new_client:
                openCreateClient();
                break;
            case R.id.mItem_create_new_center:
                openCreateCenter();
                break;
            case R.id.mItem_create_new_group:
                openCreateGroup();
                break;

            case R.id.track:
                startActivity(new Intent(this, PathTrackingActivity.class));
                break;

            default: //DO NOTHING
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadSurveyQuestion(Survey survey,int Clientid) {
        Intent myIntent = new Intent(DashboardFragmentActivity.this, SurveyQuestionViewPager.class);
        myIntent.putExtra("Survey", (new Gson()).toJson(survey));
        myIntent.putExtra("ClientId" , Clientid);
        startActivity(myIntent);
    }

    public void loadClientList() {
        replaceFragment(ClientListFragment.newInstance(null), true, R.id.container);
    }
    public void loadGroupList() {
        replaceFragment(GroupsListFragment.newInstance(null), true, R.id.container);
    }
    public void loadClientListForSurvey() {
        replaceFragment(ClientChooseFragment.newInstance(), true, R.id.container);
    }

    public void openCreateClient() {
        replaceFragment(CreateNewClientFragment.newInstance(), true, R.id.container);
    }

    public void openCreateCenter() {
        replaceFragment(CreateNewCenterFragment.newInstance(), true, R.id.container);

    }

    public void openCreateGroup() {
        replaceFragment(CreateNewGroupFragment.newInstance(), true, R.id.container);
    }
}


