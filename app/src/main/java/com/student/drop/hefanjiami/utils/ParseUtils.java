package com.student.drop.hefanjiami.utils;

import android.os.Bundle;
import android.util.Xml;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ParseUtils {
    public static Object parseJson(String str) {
        if (str.indexOf(91) != 0) {
            return str.indexOf(123) == 0 ? parseJsonToMap(str) : str;
        }
        try {
            return parseJsonToCollection(new JSONArray(str));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> parseJsonToIdentityHashMap(String str) throws JSONException {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        JSONObject jSONObject = new JSONObject(str);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object parseJson = parseJson(jSONObject.get(next).toString());
            PrintStream printStream = System.out;
            printStream.println("key:" + next + " value:" + parseJson.toString());
            identityHashMap.put(new String(next), parseJson);
        }
        return identityHashMap;
    }

    public static Map<String, Object> parseJsonToMap(String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next).toString();
                try {
                    if (obj.toString().indexOf(91) == 0) {
                        obj = parseJsonToCollection(jSONObject.getJSONArray(next));
                    } else {
                        new JSONObject(obj.toString());
                        obj = parseJsonToMap(obj.toString());
                    }
                } catch (Exception unused) {
                }
                linkedHashMap.put(next, obj);
            }
        } catch (JSONException unused2) {
        }
        return linkedHashMap;
    }

    public static Collection<Object> parseJsonToCollection(JSONArray jSONArray) {
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                linkedList.add(parseJsonToMap(jSONArray.getJSONObject(i).toString()));
            } catch (JSONException unused) {
                linkedList.clear();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    try {
                        linkedList.add(jSONArray.getString(i2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return linkedList;
    }

    public static void printObj(Object obj) {
        printObj(obj, "");
    }

    public static void printObj(Object obj, String str) {
        if (obj instanceof Map) {
            printMap((Map) obj, str);
        } else if (obj instanceof Collection) {
            printCollection((Collection) obj, str);
        } else if (obj instanceof Bundle) {
            printBundle((Bundle) obj, str);
        } else {
            System.out.println(obj);
        }
    }

    public static void printMap(Map map, String str) {
        for (Object obj : map.keySet()) {
            PrintStream printStream = System.out;
            printStream.print(str + obj + ":");
            if (map.get(obj) instanceof Map) {
                System.out.println();
                printMap((Map) map.get(obj), str + "\\\t");
            } else if (map.get(obj) instanceof Collection) {
                System.out.println();
                printCollection((Collection) map.get(obj), str + "\\\t");
            } else {
                System.out.println(map.get(obj));
            }
        }
    }

    public static void printBundle(Bundle bundle, String str) {
        for (String str2 : bundle.keySet()) {
            PrintStream printStream = System.out;
            printStream.print(str + str2 + ":");
            if (bundle.get(str2) instanceof Map) {
                System.out.println();
                printMap((Map) bundle.get(str2), str + "\\\t");
            } else if (bundle.get(str2) instanceof Collection) {
                System.out.println();
                printCollection((Collection) bundle.get(str2), str + "\\\t");
            } else {
                System.out.println(bundle.get(str2));
            }
        }
    }

    public static void printCollection(Collection collection, String str) {
        for (Object obj : collection) {
            System.out.println("\t");
            if (obj instanceof Map) {
                System.out.println();
                printMap((Map) obj, str + "\\\t");
            } else if (obj instanceof Collection) {
                System.out.println();
                printCollection((Collection) obj, str + "\\\t");
            } else {
                System.out.println(obj);
            }
        }
    }

    public static Map parseXml(String str) {
        HashMap hashMap = new HashMap();
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            newPullParser.setInput(new StringReader(str));
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        try {
                            String name = newPullParser.getName();
                            if (name != null) {
                                hashMap.put(name, parseJson(newPullParser.nextText()));
                            }
                        } catch (XmlPullParserException unused) {
                        }
                    }
                }
            }
            return hashMap;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable unused2) {
        }
        return hashMap;
    }

    public static Map parseXML(String str) {
        PersonParser personParser;
        ParserConfigurationException e;
        SAXException e2;
        IOException e3;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
            SAXParser newSAXParser = SAXParserFactory.newInstance().newSAXParser();
            personParser = new PersonParser();
            try {
                newSAXParser.parse(byteArrayInputStream, personParser);
                byteArrayInputStream.close();
            } catch (SAXException e5) {
                e2 = e5;
                e2.printStackTrace();
                return personParser.getResultMap();
            } catch (IOException e6) {
                e3 = e6;
                e3.printStackTrace();
                return personParser.getResultMap();
            }
        } catch (ParserConfigurationException e7) {
            e = e7;
            personParser = null;
            e.printStackTrace();
            return personParser.getResultMap();
        } catch (SAXException e8) {
            e2 = e8;
            personParser = null;
            e2.printStackTrace();
            return personParser.getResultMap();
        }
        return personParser.getResultMap();
    }

    private static final class PersonParser extends DefaultHandler {
        private Map resultMap;
        private String tag;

        private PersonParser() {
            this.resultMap = null;
            this.tag = null;
        }

        public Map getResultMap() {
            return this.resultMap;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startDocument() throws SAXException {
            this.resultMap = new HashMap();
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            this.tag = str2;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            this.tag = null;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] cArr, int i, int i2) throws SAXException {
            this.resultMap.put(this.tag, new String(cArr));
        }
    }
}
