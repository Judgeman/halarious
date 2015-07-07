/*
 * Copyright 2015 Stefan Urech
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *    limitations under the License
 */

package ch.halarious.core.examples;

import ch.halarious.core.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * End-to-End-Test für die Serialisierung mit GSON
 */
public class GsonSerializerTest {

    private Gson gson;

    @Before
    public void before() {
        // GSON erstellen
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(HalResource.class, new HalSerializer());
        builder.registerTypeAdapter(HalResource.class, new HalDeserializer(TestResource.class));
        builder.setExclusionStrategies(new HalExclusionStrategy());
        this.gson = builder.create();
    }

    @Test
    @Ignore
    public void testEmbeddedList() {
        // Testdaten erstellen
        TestResource resource = new TestResource();
        resource.getResources().add(new TestResource());
        resource.getResources().add(new TestResource());

        // Test durchführen
        String result = gson.toJson(resource, HalResource.class);

        // Überprüfen
        Assert.assertEquals("{\"filledText\":\"test\",\"_links\":{\"self\":{\"href\":\"link\",\"title\":\"Test\"},\"reference\":{}},\"_embedded\":{\"test\":[{\"filledText\":\"test\",\"_links\":{\"self\":{\"href\":\"link\",\"title\":\"Test\"},\"reference\":{}}},{\"filledText\":\"test\",\"_links\":{\"self\":{\"href\":\"link\",\"title\":\"Test\"},\"reference\":{}}}]}}", result);
        System.out.println(result);
    }

    @Test
    @Ignore
    public void testEmbeddedListWithOneElement() {
        // Testdaten erstellen
        TestResource resource = new TestResource();
        resource.getResources().add(new TestResource());

        // Test durchführen
        String result = gson.toJson(resource, HalResource.class);

        // Überprüfen
        Assert.assertEquals("{\"filledText\":\"test\",\"_links\":{\"self\":{\"href\":\"link\",\"title\":\"Test\"},\"reference\":{}},\"_embedded\":{\"test\":[{\"filledText\":\"test\",\"_links\":{\"self\":{\"href\":\"link\",\"title\":\"Test\"},\"reference\":{}}}]}}", result);
        System.out.println(result);
    }
}