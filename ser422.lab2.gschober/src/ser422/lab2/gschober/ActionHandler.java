package ser422.lab2.gschober;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ActionHandler {
	public String handleAction(HttpServletRequest req, HttpServletResponse response) throws IOException;
}
