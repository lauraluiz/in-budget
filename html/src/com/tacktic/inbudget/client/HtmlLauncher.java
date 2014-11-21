package com.tacktic.inbudget.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.tacktic.inbudget.InBudget;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(640, 960);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new InBudget();
        }
}