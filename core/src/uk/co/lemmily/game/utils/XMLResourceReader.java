package uk.co.lemmily.game.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Emily on 27/10/2014.
 */
public class XMLResourceReader {

    private XmlReader reader;

    public XMLResourceReader() {
        reader = new XmlReader();
    }


    public HashMap<String, HashMap<String, Object>> getResources(FileHandle xmlString) {
        HashMap<String, HashMap<String, Object>> resources = new HashMap<String, HashMap<String, Object>>();
        try {
            Element root = reader.parse(xmlString);

            resources.putAll(processResources(root.getChildrenByName("raw")));
            resources.putAll(processResources(root.getChildrenByName("processed")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resources;
    }


    public HashMap<String, HashMap<String, Object>> processResources(Array<Element> resources) {
        HashMap<String, HashMap<String, Object>> processed = new HashMap<String, HashMap<String, Object>>();
        for (Element resource : resources)
        {
            HashMap<String, Object> curRes = new HashMap<String, Object>();
            curRes.put("category", resource.getName());

            System.out.println(resource.getName());

            ObjectMap<String, String> stuffs = resource.getAttributes();
            if(stuffs != null && stuffs.size > 0) {
                for (ObjectMap.Entry<String, String> stuff : stuffs) {
                    curRes.put(stuff.key, stuff.value);
                }
            }
            //make entry to main hashmap.
            processed.put(resource.getAttribute("keyName"), curRes);
            processNodes(curRes, resource);

        }
        return processed;
    }

    public void processNodes(HashMap curRes, Element resource) {
        for (int i = 0; i < resource.getChildCount(); i++) {
            Element child = resource.getChild(i);

            if (curRes.containsKey(child.getName())) {
                Array<Object> list;
                try {
                    list = (Array<Object>) curRes.get(child.getName());
                } catch (Exception e) {
                    list = new Array<Object>();
                    list.add(curRes.get(child.getName()));
                }
                if  (child.getAttributes() != null) list.add(mapifyAttribute(child));
                curRes.put(child.getName(), list);
            } else {
                if      (child.getAttributes() != null) curRes.put(child.getName(), mapifyAttribute(child));
                else    curRes.put(child.getName(), child.getText());
            }
        }
    }

    public HashMap<String, Object> mapifyAttribute(Element element) {
        ObjectMap<String, String> attributes = element.getAttributes();
        HashMap<String, Object> attribs = new HashMap<String, Object>();

        attribs.put(element.getName(), element.getText());
        for (String s : attributes.keys()) {
            attribs.put(s, attributes.get(s));
        }

        return attribs;
    }
}
