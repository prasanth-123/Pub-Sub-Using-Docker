<!DOCTYPE html>
<html lang="en">
<head>
  <title>Publish-Subscribe Model</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script>
var resultobj1;
var resultobj2;
var resultobj3;
var newDetails2;
var Topic;
</script>
</head>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}

input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}

div {
    border-radius: 5px;
    background-color: #ffffff;
    padding: 20px;
}
</style>
<body>

<div class="container-fluid">
  <div class="row">
    <div class="col-sm-4" style="background-color:lavender;">
    <h1>Publisher</h1>
    <form action="">
  <label for="fname">Message</label>
  <input type="text" id="fname" name="firstname" placeholder="Paste your message here..">


    <b>Topic: </b>
    <select id="topic">
      <option value="Sports">Sports</option>
      <option value="Music">Music</option>
      <option value="Politics">Politics</option>
      </select>


  <input type="submit" value="Submit" onclick="fun1(); return false;">
</form></div>
    <div class="col-sm-4" style="background-color:lavenderblush;">
    <h1>Subscriber</h1>
     <form action="">
       <label for="clientmsg">Message</label>
       <input type="text" id="clientmsg" name="clientmsg" placeholder="Paste your subscriberID here">
    <b>Topic: </b><br>
    <input type="checkbox" id = "Sports"  name="Sports" value="Sports"> Sports<br>
    <input type="checkbox" id="Music" name="Music" value="Music"> Music<br>
    <input type="checkbox" id="Politics" name="Politics" value="Politics" checked> Politics<br><br>


  <input type="submit" value="Add Subscriber" onclick="fun2(); return false;">
  <input type="submit" value="Update Subscriber" onclick="fun3(); return false;">
</form></div>
    <div class="col-sm-4" style="background-color:lavender;height=100%">
    <h1>Subscriber list</h1>
    <table id="myTable" style="border: 1px solid black ;width:100% ">
    <tr>
        <th>sub_Name</th>
        <th>topic_list</th>

    </tr>
</table>
     </div>
  </div>
</div>
<script>
function fun1() {
	  var Topic = {flag : "publishMsg" , Topic: document.getElementById('topic').value, Msg: document.getElementById('fname').value  };
	  $.ajax({
		'async': false,
		  type: "POST",
	      url: "Myservlet",
	      data: Topic,
	   	  success: function(result){
	   		  resultobj1 = result;
	   		myFunction(result);
	   		var para = document.createElement("P");
	   		var t = document.createTextNode(result[0].msg);
	   		para.appendChild(t);
	   		document.body.appendChild(para);
	   		
	   	  }
	  });
};

function fun2() {
	var  subsList = new Array();
	var subsListstr = "";
	if(document.getElementById("Sports").checked)
	subsList.push('Sports');
	if(document.getElementById("Music").checked)
	subsList.push('Music');
	if(document.getElementById("Politics").checked)
	subsList.push('Politics');
	for (var i = 0 ; i < subsList.length; i++) {
		if(i== subsList.length-1)
			subsListstr += subsList[i];
		else
		subsListstr += subsList[i] + ", ";
	}
	var Topic = { flag : "addSub" , Subscriber: document.getElementById('clientmsg').value, subsList : subsListstr };
	
	$.ajax({
		'async': false,
		  type: "POST",
	      url: "Myservlet",
	      data: Topic,
	   	  success: function(result){
	   		  resultobj2 = result;
	   		myFunction(result);
	   		var para = document.createElement("P");
	   		var t = document.createTextNode(result[0].msg);
	   		para.appendChild(t);
	   		document.body.appendChild(para);
	   	  }
	  });
};


function fun3() {
	var  subsList = new Array();
	var subsListstr="";
	if(document.getElementById("Sports").checked)
	subsList.push('Sports');
	if(document.getElementById("Music").checked)
	subsList.push('Music');
	if(document.getElementById("Politics").checked)
	subsList.push('Politics');
	for (i = 0; i < subsList.length; i++) { 
		if(i==subsList.length-1)
			subsListstr += subsList[i];
		else
		subsListstr += subsList[i] + ", ";
	}
	var Topic = { flag : "updateSub" , Subscriber: document.getElementById('clientmsg').value, subsList : subsListstr };

	$.ajax({
		'async': false,
		  type: "POST",
	      url: "Myservlet",
	      data: Topic,
	   	  success: function(result){
	   		resultobj3 = result;
	   		myFunction(result);
	   		var para = document.createElement("P");
	   		var t = document.createTextNode(result[0].msg);
	   		para.appendChild(t);
	   		document.body.appendChild(para);
	   	  }
	  });
};


</script>
<script>
function myFunction(json){
 // var json = [{"Message":"so and so msg"},{"sub_Name":"Jane Smith","topic_list":"Sports,Music,News"},{"sub_Name":"Chuck Berry","topic_list":"Music,News"}];
        var tr;
        $("#myTable tbody tr").remove();
        tr = $('<tr/>');
        tr.append("<th>" + "sub_Name" + "</th>");
        tr.append("<th>" + "topic_list" + "</th>");
        $('table').append(tr);

        for (var i = 1; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].sub_Name + "</td>");
            tr.append("<td>" + json[i].topic_list + "</td>");
            $('table').append(tr);
        }
}
  </script>
</body>
</html>
