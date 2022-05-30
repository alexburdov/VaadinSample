package ru.onkor.example01.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonToXmlConverterImplTest {

    @Test
    void convert() {
        Converter converter = new JsonToXmlConverterImpl();
        String json = "{\n" +
                "\t\"roles\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\"position\": \"head\",\n" +
                "\t\t\t\"salary\": \"10k\",\n" +
                "\t\t\t\"persons\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\t\"name\": \"Red\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\"position\": \"manager\",\n" +
                "\t\t\t\"salary\": \"8k\",\n" +
                "\t\t\t\"persons\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\t\t\"name\": \"Green\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\t\"name\": \"Blue\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\"position\": \"employee\",\n" +
                "\t\t\t\"salary\": \"5k\",\n" +
                "\t\t\t\"persons\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\t\"name\": \"Yellow\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": \"5\",\n" +
                "\t\t\t\t\t\"name\": \"Brown\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t]\t\n" +
                "}";
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<java version=\"17.0.2\" class=\"java.beans.XMLDecoder\">\n" +
                " <object class=\"java.util.LinkedHashMap\">\n" +
                "  <void method=\"put\">\n" +
                "   <string>roles</string>\n" +
                "   <object class=\"java.util.ArrayList\">\n" +
                "    <void method=\"add\">\n" +
                "     <object class=\"java.util.LinkedHashMap\">\n" +
                "      <void method=\"put\">\n" +
                "       <string>id</string>\n" +
                "       <string>1</string>\n" +
                "      </void>\n" +
                "      <void method=\"put\">\n" +
                "       <string>position</string>\n" +
                "       <string>head</string>\n" +
                "      </void>\n" +
                "      <void method=\"put\">\n" +
                "       <string>salary</string>\n" +
                "       <string>10k</string>\n" +
                "      </void>\n" +
                "      <void method=\"put\">\n" +
                "       <string>persons</string>\n" +
                "       <object class=\"java.util.ArrayList\">\n" +
                "        <void method=\"add\">\n" +
                "         <object class=\"java.util.LinkedHashMap\">\n" +
                "          <void method=\"put\">\n" +
                "           <string>id</string>\n" +
                "           <string>1</string>\n" +
                "          </void>\n" +
                "          <void method=\"put\">\n" +
                "           <string>name</string>\n" +
                "           <string>Red</string>\n" +
                "          </void>\n" +
                "         </object>\n" +
                "        </void>\n" +
                "       </object>\n" +
                "      </void>\n" +
                "     </object>\n" +
                "    </void>\n" +
                "    <void method=\"add\">\n" +
                "     <object class=\"java.util.LinkedHashMap\">\n" +
                "      <void method=\"put\">\n" +
                "       <string>id</string>\n" +
                "       <string>2</string>\n" +
                "      </void>\n" +
                "      <void method=\"put\">\n" +
                "       <string>position</string>\n" +
                "       <string>manager</string>\n" +
                "      </void>\n" +
                "      <void method=\"put\">\n" +
                "       <string>salary</string>\n" +
                "       <string>8k</string>\n" +
                "      </void>\n" +
                "      <void method=\"put\">\n" +
                "       <string>persons</string>\n" +
                "       <object class=\"java.util.ArrayList\">\n" +
                "        <void method=\"add\">\n" +
                "         <object class=\"java.util.LinkedHashMap\">\n" +
                "          <void method=\"put\">\n" +
                "           <string>id</string>\n" +
                "           <string>2</string>\n" +
                "          </void>\n" +
                "          <void method=\"put\">\n" +
                "           <string>name</string>\n" +
                "           <string>Green</string>\n" +
                "          </void>\n" +
                "         </object>\n" +
                "        </void>\n" +
                "        <void method=\"add\">\n" +
                "         <object class=\"java.util.LinkedHashMap\">\n" +
                "          <void method=\"put\">\n" +
                "           <string>id</string>\n" +
                "           <string>3</string>\n" +
                "          </void>\n" +
                "          <void method=\"put\">\n" +
                "           <string>name</string>\n" +
                "           <string>Blue</string>\n" +
                "          </void>\n" +
                "         </object>\n" +
                "        </void>\n" +
                "       </object>\n" +
                "      </void>\n" +
                "     </object>\n" +
                "    </void>\n" +
                "    <void method=\"add\">\n" +
                "     <object class=\"java.util.LinkedHashMap\">\n" +
                "      <void method=\"put\">\n" +
                "       <string>id</string>\n" +
                "       <string>3</string>\n" +
                "      </void>\n" +
                "      <void method=\"put\">\n" +
                "       <string>position</string>\n" +
                "       <string>employee</string>\n" +
                "      </void>\n" +
                "      <void method=\"put\">\n" +
                "       <string>salary</string>\n" +
                "       <string>5k</string>\n" +
                "      </void>\n" +
                "      <void method=\"put\">\n" +
                "       <string>persons</string>\n" +
                "       <object class=\"java.util.ArrayList\">\n" +
                "        <void method=\"add\">\n" +
                "         <object class=\"java.util.LinkedHashMap\">\n" +
                "          <void method=\"put\">\n" +
                "           <string>id</string>\n" +
                "           <string>4</string>\n" +
                "          </void>\n" +
                "          <void method=\"put\">\n" +
                "           <string>name</string>\n" +
                "           <string>Yellow</string>\n" +
                "          </void>\n" +
                "         </object>\n" +
                "        </void>\n" +
                "        <void method=\"add\">\n" +
                "         <object class=\"java.util.LinkedHashMap\">\n" +
                "          <void method=\"put\">\n" +
                "           <string>id</string>\n" +
                "           <string>5</string>\n" +
                "          </void>\n" +
                "          <void method=\"put\">\n" +
                "           <string>name</string>\n" +
                "           <string>Brown</string>\n" +
                "          </void>\n" +
                "         </object>\n" +
                "        </void>\n" +
                "       </object>\n" +
                "      </void>\n" +
                "     </object>\n" +
                "    </void>\n" +
                "   </object>\n" +
                "  </void>\n" +
                " </object>\n" +
                "</java>\n";
        String result = converter.convert(json);
        Assertions.assertEquals(xml, result);
    }
}