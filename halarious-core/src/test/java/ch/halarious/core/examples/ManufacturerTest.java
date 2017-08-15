/*
 * Copyright 2014 Stefan Urech
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
import org.junit.Test;

/**
 * Created by surech on 15.01.14.
 */
public class ManufacturerTest {

    private Gson gson;

    @Before
    public void before() {
        // GSON erstellen
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(HalResource.class, new HalSerializer());
        builder.registerTypeAdapter(HalResource.class, new HalDeserializer(ProductResource.class));
        builder.setExclusionStrategies(new HalExclusionStrategy());
        this.gson = builder.create();
    }

    public String toJson(ProductResource product) {
        return gson.toJson(product, HalResource.class);
    }

    public ProductResource fromJson(String json) {
        return (ProductResource) gson.fromJson(json, HalResource.class);
    }

    @Test
    public void testManufacturer(){
        ManufacturerResource manufacturer = new ManufacturerResource("/manufacturers/328764", "Manufacturer Inc.", "http://hoverdonkey.com");
        ManufacturerResource manufacturer2 = new ManufacturerResource("/manufacturers/458393", "Producer Inc.", "http://producer.com");
        ProductResource product = new ProductResource();
        product.setSelf(new Link("/product/987"));
        product.setDescription("A great product");
        product.setWeight(400);
        product.setName("A product");
        product.setManufacturer(manufacturer);
        product.getManufacturers().add(manufacturer);
        product.getManufacturers().add(manufacturer2);

        String json = toJson(product);
        System.out.println(json);

        ProductResource resource = fromJson(json);
        Assert.assertEquals(product.getSelf().getHref(), resource.getSelf().getHref());
    }
}
