package com.tacktic.inbudget.sphere;

import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.Locale;

public class InternationalString implements Json.Serializable {
    private ArrayMap<Locale, String> translations = new ArrayMap<Locale, String>();

    public InternationalString() {
    }

    @Override
    public void write(final Json json) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void read(final Json json, final JsonValue jsonData) {
        JsonValue current = jsonData.child();
        while (current != null) {
            Locale key = new Locale(current.name());
            String value = current.asString();
            translations.put(key, value);
            current = current.next();
        }
    }

    public String get(final Locale locale) {
        return translations.get(locale);
    }

    @Override
    public String toString() {
        return "InternationalString{" +
                "translations=" + translations +
                '}';
    }
}
