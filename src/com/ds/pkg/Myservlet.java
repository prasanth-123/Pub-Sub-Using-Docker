package com.ds.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Servlet implementation class Myservlet
 */
public class Myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Myservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at:  ").append(request.getContextPath()).print("inside Myservlet");
		
		
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String msg = null ;
		
		if(request.getParameter("flag").equals("addSub")){
			System.out.println(request.getParameter("Subscriber"));   //echo for debugging
			System.out.println(request.getParameter("subsList")); 
			if(request.getParameter("Subscriber").equals(null)||request.getParameter("Subscriber").equals(""))
				msg = "Empty and null Subscriber Names are Not allowed.";
			else if(Mypubsub.subsTopics.containsKey(request.getParameter("Subscriber")))
				msg = "User already exists, try updating..";
			else
			msg = Mypubsub.addSubscriber(request.getParameter("Subscriber"), request.getParameter("subsList"));
		}
		else if(request.getParameter("flag").equals("publishMsg")){
			System.out.println(request.getParameter("Topic"));        //echo for debugging
			System.out.println(request.getParameter("Msg")); 
			msg = Mypubsub.Printtopicmsg(request.getParameter("Topic"), request.getParameter("Msg"));
			
			
			
			
			
			
			}
		else if(request.getParameter("flag").equals("updateSub")){
			System.out.println(request.getParameter("Subscriber"));   //echo for debugging
			System.out.println(request.getParameter("subsList")); 
			if(!Mypubsub.subsTopics.containsKey(request.getParameter("Subscriber")))
			msg = "User doen't exist Please add the User and then Update";	
			else
			msg = Mypubsub.updateSubscriber(request.getParameter("Subscriber"), request.getParameter("subsList"));
		}
			//Mypubsub.Printtopicmsg(request.getParameter("Topic"),request.getParameter("Msg"));
			//response.setContentType("application/json");
			//Mypubsub.addSubscriber(request.getParameter("Topic"),)
			PrintWriter out = response.getWriter();
			HashMap<String, String> hMap = new HashMap<String, String>();
			JSONArray arr = new JSONArray();
			JSONObject json = new JSONObject();
			
			
			try {
				json.put("msg", msg);
				arr.put(json);
				 System.out.println(Mypubsub.subsTopics.size() );
				Iterator it = Mypubsub.subsTopics.entrySet().iterator();
				while (it.hasNext()) {

					JSONObject hash = new JSONObject();
			        Map.Entry pair = (Map.Entry)it.next();
			        //hash.put(pair.getKey().toString(), pair.getValue().toString());
			        hash.put("sub_Name",pair.getKey().toString() );
			        hash.put("topic_list",pair.getValue().toString() );
			        arr.put(hash);

			    }
				//json.put("subscibers", hash);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(arr);
			response.setContentType("application/json");
			out.print(arr);  //appending the json to the getwriter() method and sending it to the index.jsp
			out.flush();
			out.close();
		
		doGet(request, response);
	}

}
