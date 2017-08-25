package ch.halarious.core;

import java.io.Serializable;

/**
 * Created by Paul Richter on 01/03/2017.
 */
public class Link implements HalResource, Serializable {

    public static String defaultMethod = "GET";

    private long id;

    private String href;

    // default value = GET
    private String method = defaultMethod;

    public Link() {
        // empty constructor
    }

    public Link(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public String getMethod() {
        return method;
    }

    public long getId() {
        return id;
    }

    public void setHref(String href) {
        // remove the first backslash
        href = removeFirstBackslash(href);

        this.href = href;
    }

    public void setMethod(String method) {
        if(method != null)
            this.method = method;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return getHref() + " [" + method  + "]";
    }

    public static String removeFirstBackslash(String href) {
        if(href != null && href.startsWith("/")) {
            href = href.substring(1, href.length());
        }

        return href;
    }
}
