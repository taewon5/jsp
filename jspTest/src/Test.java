

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.XML;
import org.json.simple.JSONObject;


/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
             
        String urlstr = "http://apis.data.go.kr/1360000/AsosDalyInfoService/getWthrDataList"
                + "?serviceKey=i3kUJI8RKhH8OX4kPZ5jGWNs%2B1IndpeWLSKWYWpW3yT%2BPy3U9PM%2F%2BrORbVpSicAZ7MbZ9mPTfGvRbi1zCsc%2BPg%3D%3D"
                + "&numOfRows=10&pageNo=1&dataCd=ASOS&dateCd=DAY&startDt=20100101&endDt=20100102&stnIds=108";
        URL url = new URL(urlstr);
        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
        urlconnection.setRequestMethod("GET");
        urlconnection.setRequestProperty("Content-type", "application/json");
        // 7. 통신 응답 코드 확인.
        System.out.println("Response code: " + urlconnection.getResponseCode());
     
        BufferedReader rd;
        if(urlconnection.getResponseCode() >= 200 && urlconnection.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(urlconnection.getErrorStream()));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        // 10. 객체 해제.
        rd.close();
        urlconnection.disconnect();
        // 11. 전달받은 데이터 확인.
        String xmlstr = sb.toString();
        org.json.JSONObject json = XML.toJSONObject(xmlstr);
        String jsonStr = json.toString(4);
        System.out.println(jsonStr);
		response.setContentType("text/html;charaset=utf-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
